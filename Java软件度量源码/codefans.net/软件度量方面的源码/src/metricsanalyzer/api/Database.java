/* MetricsAnalyzer
 * Copyright (C) 2002  TIKE (tike.mmm.fi)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 59
 * Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package metricsanalyzer.api;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import metricsanalyzer.api.data.Attr;
import metricsanalyzer.api.data.Relation;
import metricsanalyzer.detail.MetricsBuilder;
import metricsanalyzer.detail.Relations;
import metricsanalyzer.util.ArraysExt;
import metricsanalyzer.util.StringBuilder;
import metricsanalyzer.util.Strings;
import metricsanalyzer.util.fun.ObjectToObject;
import metricsanalyzer.util.fun.VoidToVoid;
import metricsanalyzer.util.gui.AbstractCachedListModel;
import metricsanalyzer.util.gui.ActionCancelledException;
import metricsanalyzer.util.sql.JDBC;
import metricsanalyzer.util.sql.ResultSetTable;
import minijlisp.interpreter.Lisp;
import minijlisp.util.Env;
import minijlisp.util.Pair;
import minijlisp.util.Symbol;

/**
 * Provides access to a relational metrics database.
 */
public class Database extends metricsanalyzer.util.sql.Database {
  public Database(String driver,
                  String url,
                  String user,
                  String password,
                  String basename) throws Exception {
    super(driver,url,user,password);
    this.basename = basename;
  }

  public class HistoryList extends AbstractCachedListModel {

    public class Entry {
      Entry(int idx,
            Timestamp timestamp,
            String name) {
        this.idx = idx;
        this.timestamp = timestamp;
        this.name = name;
      }

      public String toString() {
        return timestamp + " -- " + name;
      }

      public final int idx;
      public final Timestamp timestamp;
      public final String name;
    }

    protected void fillCache() throws Exception {
      for (ResultSet rs = query("SELECT * FROM " + withBasename(HISTORY_RELATION) + " ORDER BY " + IDX_ATTR.name + " DESC");
           rs.next();)
        cache.add(new Entry(rs.getInt(1),
                            rs.getTimestamp(2),
                            rs.getString(3)));
    }

    public void add(final String name,
                    final ImportList importList) throws Exception {
      updateCache();

      final MetricsBuilder mb = doImport(importList);
      final Relation[] mergedRelations = mb.getMergedRelations();
      final Map relationToTable = mb.getRelationToTable();

      final int idx = getNextIdx();

      final StrTable strTable = new StrTable();
      final StringBuilder sb = new StringBuilder();

      executeTransaction(new VoidToVoid() {
          public void with() throws Exception {
            update("INSERT INTO " + withBasename(HISTORY_RELATION) + " VALUES " +
                   "( " + idx +
                   ", " + JDBC.formatLit(new Timestamp(System.currentTimeMillis())) +
                   ", '" + name + "'" +
                   ")");

            for (int i=0; i<mergedRelations.length; ++i) {
              update(formatCreateTable(mergedRelations,
                                       mergedRelations[i],
                                       idx));

              MetricsBuilder.Table table = (MetricsBuilder.Table)relationToTable.get(mergedRelations[i]);

              String prefix = "INSERT INTO " + withBasenameAndIdx(table.relation, idx) + " VALUES (";

              for (Iterator ite = table.keysToValues.entrySet().iterator(); ite.hasNext();) {
                Map.Entry entry = (Map.Entry)ite.next();

                sb.p(prefix);
                appendCols(  "", ", ", (Object[])entry.getKey(), table.relation.keys);
                appendCols(", ", ", ", (Object[])entry.getValue(), table.relation.values);
                sb.p(")");

                update(sb.extract());
              }
            }
          }

          private final void appendCols(String firstSep,
                                        String restSep,
                                        Object[] values,
                                        Attr[] attrs) throws Exception {
            for (int i=0; i<values.length; ++i) {
              sb.p(0==i ? firstSep : restSep);

              if ("shared_str".equals(attrs[i].type))
                sb.p(strTable.put((String)values[i]));
              else
                sb.p(JDBC.formatLit(values[i]));
            }
          }
        });

      updateCache();
    }

    private int getNextIdx() {
      return 0 != cache.size() ? ((Entry)cache.get(0)).idx + 1 : 1;
    }

    public void remove(final int[] indices) throws Exception {
      executeTransaction(new VoidToVoid() {
          public void with() throws Exception {
            for (int ii=0; ii<indices.length; ++ii) {
              int idx = ((Entry)cache.get(indices[ii])).idx;

              update("DELETE FROM " + withBasename(HISTORY_RELATION) + " WHERE " + IDX_ATTR.name + " = " + idx);

              String[] tableNames = findTableNames(withBasenameAndIdx("%", idx));

              for (int i=0; i<tableNames.length; ++i)
                update("DROP TABLE " + tableNames[i]);
            }
          }
        });

      updateCache();
    }
  }

  public TableModel[] executeQuery(String unpreparedQuery,
                                   int selectedIndex,
                                   int[] selectedIndices) throws Exception {
    return executeQueries(prepareQueries(unpreparedQuery,
                                         selectedIndex,
                                         selectedIndices));
  }

  public void createTables() throws Exception {
    executeTransaction(new VoidToVoid() {
        public void with() throws Exception {
          for (int i=0; i<RELATIONS.length; ++i)
            update(formatCreateTable(RELATIONS, RELATIONS[i], -1));
        }
      });
  }

  public void dropTables() throws Exception {
    executeTransaction(new VoidToVoid() {
        public void with() throws Exception {
          if (0 != queryInt("SELECT COUNT(*) FROM " + withBasename(HISTORY_RELATION)))
            throw new ActionCancelledException("The history table is not empty. Remove all snapshots first.",
                                               "Error",
                                               JOptionPane.ERROR_MESSAGE);

          for (int i=0; i<RELATIONS.length; ++i)
            update("DROP TABLE " + withBasename(RELATIONS[i].name));
        }
      });
  }

  // *** Utility functions ***

  private TableModel[] executeQueries(String[] queries) throws Exception {
    TableModel[] results = new TableModel[queries.length];
    for (int i=0; i<queries.length; ++i) {
      log(queries[i] + ";\n");
      results[i] = new ResultSetTable(createStatement().executeQuery(queries[i]));
    }
    return results;
  }

  private String[] prepareQueries(String unpreparedQuery,
                                  int selectedIndex,
                                  int[] selectedIndices) throws Exception {
    StringBuffer result = new StringBuffer();
    Lisp.eval(markup(unpreparedQuery,
                     "<#",
                     "#>"),
              createEnv(result,
                        selectedIndex,
                        selectedIndices));
    return Strings.splitAndTrim(result.toString(),
                                ';');
  }

  private static MetricsBuilder doImport(ImportList importList) throws Exception {
    MetricsBuilder mb = new MetricsBuilder(importList.getRelations());
    importList.doImport(mb);
    return mb;
  }

  private String formatCreateTable(Relation[] possibleSubSets,
                                   Relation relation,
                                   int idx) throws Exception {
    StringBuilder result = new StringBuilder();

    result.p("CREATE TABLE ").p(withBasenameAndIdx(relation, idx)).p(" (");

    appendWithSep(result, "\n  ", "\n, ", relation.keys, FORMAT_TYPED_ATTR);
    appendWithSep(result, "\n, ", "\n, ", relation.values, FORMAT_TYPED_ATTR);

    result.p("\n, PRIMARY KEY (");
    appendWithSep(result, "", ", ", relation.keys, FORMAT_UNTYPED_ATTR);
    result.p(")");

    appendForeignKeys(result, possibleSubSets, relation, idx);
    if (possibleSubSets != RELATIONS)
      appendForeignKeys(result, RELATIONS, relation, -1);

    result.p("\n)");

    return result.extract();
  }

  private void appendForeignKeys(StringBuilder result,
                                 Relation[] possibleSubSets,
                                 Relation superSet,
                                 int idx) throws Exception {
    for (int i=0; i<possibleSubSets.length && possibleSubSets[i] != superSet; ++i)
      if (ArraysExt.isSubSuperSet(possibleSubSets[i].keys, superSet.keys, Relations.ATTR_KEY_CMP))
        appendForeignKeys(result, possibleSubSets[i], superSet, idx);
  }

  private void appendForeignKeys(StringBuilder result,
                                 Relation subSet,
                                 Relation superSet,
                                 int idx) throws Exception {
    int[][] mappings = ArraysExt.mapSubToAllSuperSets(subSet.keys, superSet.keys, Relations.ATTR_KEY_CMP);

    for (int i=0; i<mappings.length; ++i) {
      result.p("\n, FOREIGN KEY (");
      appendWithSep(result, "", ", ", ArraysExt.pick(mappings[i], superSet.keys), FORMAT_UNTYPED_ATTR);
      result.p(") REFERENCES ").p(withBasenameAndIdx(subSet, idx)).p(" (");
      appendWithSep(result, "", ", ", subSet.keys, FORMAT_UNTYPED_ATTR);
      result.p(")");
    }
  }

  private static final ObjectToObject
    FORMAT_TYPED_ATTR = new ObjectToObject() {
        public Object with(Object attrO) {
          Attr attr = (Attr)attrO;
          return attr.name + " " + ("shared_str".equals(attr.type) ? "int" : attr.type); }};
  private static final ObjectToObject
    FORMAT_UNTYPED_ATTR = new ObjectToObject() {
        public Object with(Object attr) {
          return ((Attr)attr).name; }};

  private static void appendWithSep(StringBuilder result,
                                    String firstSep,
                                    String restSep,
                                    Object[] elems,
                                    ObjectToObject formatter) throws Exception {
    for (int i=0; i<elems.length; ++i)
      result.p(0 == i ? firstSep : restSep).p(formatter.with(elems[i]));
  }

  private String[] findTableNames(String pattern) throws Exception {
    ArrayList result = new ArrayList();

    for (ResultSet rs = getMetaData().getTables(null, "", pattern, null);
         rs.next();)
      result.add(rs.getString(3));

    return (String[])result.toArray(new String[result.size()]);
  }

  private Env createEnv(final StringBuffer result,
                        int selectedIndex,
                        int[] selectedIndices) throws Exception {
    Env env = Lisp.createEnv();

    // Provide default indices (if there is anything to select from)
    if ((-1 == selectedIndex || 0 == selectedIndices.length) && 0 < historyList.getSize()) {
      selectedIndex = 0;
      selectedIndices = new int[]{selectedIndex};
    }

    env.add(Symbol.create("selected-index"), indexToInteger(selectedIndex));

    Object[] indices = new Object[selectedIndices.length];
    for (int i=0; i<indices.length; ++i)
      indices[i] = indexToInteger(selectedIndices[i]);

    env.add(Symbol.create("selected-indices"), indices);

    env.add(Symbol.create("emit"),
            new Lisp.Function() {
              public Object with(Object arg, Env env) throws Exception {
                Pair.map(arg,
                         new minijlisp.util.ObjectToObject() {
                           public Object with(Object o) {
                             return result.append(o);
                           }
                         });
                return null;
              }});

    env.add(Symbol.create("failwith"),
            new Lisp.Function() {
              public Object with(Object arg, Env env) throws Exception {
                final StringBuffer message = new StringBuffer();
                Pair.map(arg,
                         new minijlisp.util.ObjectToObject() {
                           public Object with(Object o) {
                             return message.append(o);
                           }
                         });
                throw new ActionCancelledException(message.toString(),
                                                   "Query failed!",
                                                   JOptionPane.ERROR_MESSAGE);
              }});

    env.add(Symbol.create("shared-tbl-name"),
            new Lisp.Function() {
              public Object with(Object rest, Env env) throws Exception {
                return withBasename(Pair.firstAt(rest,0).toString());
              }
            });

    env.add(Symbol.create("snapshot-tbl-name"),
            new Lisp.Function() {
              public Object with(Object rest, Env env) throws Exception {
                return withBasenameAndIdx(Pair.firstAt(rest,0).toString(), ((Integer)Pair.firstAt(rest,1)).intValue());
              }
            });

    return env;
  }

  private Integer indexToInteger(int index) {
    return new Integer(0 <= index && index < historyList.getSize()
                       ? ((HistoryList.Entry)historyList.getElementAt(index)).idx
                       : -1);
  }

  private String markup(final String code,
                        final String markupOpen,
                        final String markupClose) throws Exception {
    final StringBuffer result = new StringBuffer();

    new VoidToVoid() {
      int directBegin = 0;
      int offset = 0;

      public void with() {
        while (more()) {
          copyUntil(markupOpen);
          endDirect();

          if (!more())
            break;

          copyUntil(markupClose);
          beginDirect();
        }
      }

      boolean more() {
        return offset < code.length();
      }

      void copyUntil(String marker) {
        while (!matches(marker) && more())
          copy();
      }

      boolean matches(String prefix) {
        return code.regionMatches(offset, prefix, 0, prefix.length());
      }

      void copy() {
        if (directBegin == offset)
          result.append("(emit \"");
        result.append(code.charAt(offset));
        offset += 1;
      }

      void beginDirect() {
        offset += markupClose.length();
        directBegin = offset;
      }

      void endDirect() {
        if (more() && !matches(markupOpen))
          throw new RuntimeException("Error: `" + markupOpen + "' was not matched by an `" + markupClose + "'.");

        if (directBegin < offset)
          result.append("\")");
        offset += markupOpen.length();
      }
    }.with();

    return result.toString();
  }

  private class StrTable {
    StrTable() throws Exception {
      for (ResultSet rs = query("SELECT * FROM " + withBasename(STR_RELATION));
           rs.next();)
        strToId.put(rs.getString(2), new Integer(rs.getInt(1)));
    }

    int put(String str) throws Exception {
      Object result = strToId.get(str);

      if (null != result) {
        return ((Integer)result).intValue();
      } else {
        int next = strToId.size();
        update("INSERT INTO " + withBasename(STR_RELATION) + " VALUES (" + next + ", '" + str + "')");
        strToId.put(str, new Integer(next));
        return next;
      }
    }

    private HashMap strToId = new HashMap();
  }

  private String withBasename(Relation relation) {
    return withBasename(relation.name);
  }

  private String withBasenameAndIdx(Relation relation, int idx) {
    return withBasenameAndIdx(relation.name, idx);
  }

  private String withBasename(String name) {
    return basename + "_" + name;
  }

  private String withBasenameAndIdx(String name, int idx) {
    return withBasename(name) + (-1 == idx ? "" : "_" + idx);
  }

  private static final Attr
    ID_ATTR   = new Attr("id",   "int"),
    STR_ATTR  = new Attr("str",  "varchar(511) UNIQUE NOT NULL"),
    IDX_ATTR  = new Attr("idx",  "int"),
    TS_ATTR   = new Attr("ts",   "timestamp UNIQUE NOT NULL"),
    NAME_ATTR = new Attr("name", "varchar(80)");

  private static final Relation
    STR_RELATION     = new Relation("str",
                                    new Attr[]{ID_ATTR},
                                    new Attr[]{STR_ATTR}),
    HISTORY_RELATION = new Relation("history",
                                    new Attr[]{IDX_ATTR},
                                    new Attr[]{TS_ATTR,
                                               NAME_ATTR});

  private static final Relation[] RELATIONS = {
    STR_RELATION,
    HISTORY_RELATION};

  public final HistoryList historyList = new HistoryList();
  public final String basename;
}
