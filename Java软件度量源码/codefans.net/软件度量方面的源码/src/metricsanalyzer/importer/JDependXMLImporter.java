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

package metricsanalyzer.importer;

import java.io.File;
import javax.xml.parsers.SAXParserFactory;
import metricsanalyzer.api.data.Attr;
import metricsanalyzer.api.data.Relation;
import metricsanalyzer.api.data.TableBuilder;
import metricsanalyzer.api.ext.Importer;
import metricsanalyzer.util.Classes;
import metricsanalyzer.util.xml.SAXDispatchHandler;
import org.xml.sax.Attributes;

/**
 * Imports metrics from XML files generated by JDepend.
 */
public class JDependXMLImporter extends Importer {
  public void doImport(File file,
                       TableBuilder tableBuilder) throws Exception {
    SAXParserFactory.newInstance().newSAXParser().parse(file, new Handler(tableBuilder));
  }

  public Relation[] getRelations() {
    return RELATIONS;
  }

  private static final Attr
    PACKAGE_NAME_ATTR      = new Attr("package_id",      "shared_str"),
    CLASS_NAME_ATTR        = new Attr("class_id",        "shared_str"),
    IS_ABSTRACT_ATTR       = new Attr("is_abstract",     "boolean"),
    FROM_PACKAGE_NAME_ATTR = new Attr("from_package_id", "shared_str"),
    TO_PACKAGE_NAME_ATTR   = new Attr("to_package_id",   "shared_str");

  private static final Relation
    PACKAGE_RELATION    = new Relation("package",
                                       new Attr[]{PACKAGE_NAME_ATTR},
                                       new Attr[]{}),
    CLASS_RELATION      = new Relation("class",
                                       new Attr[]{CLASS_NAME_ATTR,
                                                  PACKAGE_NAME_ATTR},
                                       new Attr[]{IS_ABSTRACT_ATTR}),
    DEPENDENCY_RELATION = new Relation("dependency",
                                       new Attr[]{FROM_PACKAGE_NAME_ATTR,
                                                  TO_PACKAGE_NAME_ATTR},
                                       new Attr[]{});

  private static final Relation[] RELATIONS = {
    PACKAGE_RELATION,
    CLASS_RELATION,
    DEPENDENCY_RELATION};

  public static class Handler extends SAXDispatchHandler {
    Handler(TableBuilder tableBuilder) throws Exception {
      this.tableBuilder = tableBuilder;
    }

    public void start_Package(Attributes attr) {
      if (1 == attr.getLength())
        packageName = attr.getValue(0);
    }

    public void chars_Class_AbstractClasses(String data) throws Exception {
      tableBuilder.add(CLASS_RELATION,
                       new Object[]{Classes.getClassPart(data), packageName},
                       new Object[]{new Boolean(true)});
    }

    public void chars_Class_ConcreteClasses(String data) throws Exception {
      tableBuilder.add(CLASS_RELATION,
                       new Object[]{Classes.getClassPart(data), packageName},
                       new Object[]{new Boolean(false)});
    }

    public void chars_Package_DependsUpon(String data) throws Exception {
      tableBuilder.add(DEPENDENCY_RELATION,
                       new Object[]{packageName, data},
                       new Object[]{});
    }

    public void chars_Package_UsedBy(String data) throws Exception {
      tableBuilder.add(DEPENDENCY_RELATION,
                       new Object[]{data, packageName},
                       new Object[]{});
    }

    private String packageName;
    private TableBuilder tableBuilder;
  }
}
