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

package metricsanalyzer.detail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import metricsanalyzer.api.data.Attr;
import metricsanalyzer.api.data.Relation;
import metricsanalyzer.api.data.TableBuilder;
import metricsanalyzer.util.ArraysExt;
import metricsanalyzer.util.Maps;

/**
 * Helps gather metrics data from multiple sources.
 */
public class MetricsBuilder implements TableBuilder {
  public MetricsBuilder(Relation[] givenRelations) throws Exception {
    this.mergedRelations = Relations.merge(givenRelations);

    for (int i=0; i<mergedRelations.length; ++i)
      relationToTable.put(mergedRelations[i], new Table(mergedRelations[i]));

    for (int i=0; i<givenRelations.length; ++i)
      relationToMapping.put(givenRelations[i], new Mapping(givenRelations[i]));
  }

  public void add(Relation relation,
                  Object[] key,
                  Object[] data) throws Exception {
    ((Mapping)relationToMapping.get(relation)).add(key, data);
  }

  public Map getRelationToTable() {
    return relationToTable;
  }

  public Relation[] getMergedRelations() {
    return mergedRelations;
  }

  public class Table {
    public Table(Relation relation) {
      this.relation = relation;

      ArrayList refs = new ArrayList();
      for (int i=0; i<mergedRelations.length && mergedRelations[i] != relation; ++i)
        if (ArraysExt.isSubSuperSet(mergedRelations[i].keys, relation.keys, Relations.ATTR_KEY_CMP))
          refs.add(new Ref((Table)relationToTable.get(mergedRelations[i]),
                           ArraysExt.mapSubToAllSuperSets(mergedRelations[i].keys,
                                                          relation.keys,
                                                          Relations.ATTR_KEY_CMP)));
      this.refs = (Ref[])refs.toArray(new Ref[refs.size()]);
    }

    public Object[] visitRow(Object[] key) {
      for (int refI=0; refI<refs.length; ++refI)
        for (int mappingI=0; mappingI<refs[refI].mappings.length; ++mappingI)
          refs[refI].table.visitRow(ArraysExt.pick(refs[refI].mappings[mappingI],
                                                   key));

      return (Object[])Maps.putOnce(keysToValues, key, new Object[relation.values.length]);
    }

    public final Relation relation;
    public final Ref[] refs;
    public final Map keysToValues = new TreeMap(ARRAY_CMP);
  }

  private static class Ref {
    public Ref(Table table,
               int[][] mappings) {
      this.table = table;
      this.mappings = mappings;
    }

    public final Table table;
    public final int[][] mappings;
  }

  private class Mapping {
    public Mapping(Relation relation) throws Exception {
      table = (Table)relationToTable.get(relation);
      subToSuperSet = ArraysExt.mapSubToSuperSet(relation.values, table.relation.values, Relations.ATTR_CMP);
    }

    public void add(Object[] key, Object[] data) throws Exception {
      merge(table.visitRow(key), data);
    }

    private void merge(Object[] superSet, Object[] subSet) throws Exception {
      for (int subSetIdx=0; subSetIdx<subToSuperSet.length; ++subSetIdx) {
        int superSetIdx = subToSuperSet[subSetIdx];
        superSet[superSetIdx] = MetricsBuilder.merge(superSet[superSetIdx], subSet[subSetIdx]);
      }
    }

    private Table table;
    private int[] subToSuperSet;
  }

  private static Object merge(Object a,
                              Object b) throws Exception {
    if (null == a) a = b;
    if (null == b) b = a;

    if (null != a && !a.equals(b))
      throw new RuntimeException("Conflicting values: " + a + " and " + b);

    return a;
  }

  private static final Comparator
    ARRAY_CMP = new Comparator() {
        public int compare(Object o1, Object o2) {
          Object[] a1 = (Object[])o1;
          Object[] a2 = (Object[])o2;

          if (a1.length != a2.length)
            throw new RuntimeException("Can't compare arrays of different length.");

          for (int i=0; i<a1.length; ++i) {
            int result = ((Comparable)a1[i]).compareTo(a2[i]);
            if (0 != result)
              return result;
          }
          return 0; }};

  private Relation[] mergedRelations;
  private Map relationToTable = new TreeMap(Relations.RELATION_KEY_CMP);
  private Map relationToMapping = new HashMap();
}
