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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.swing.table.TableModel;
import javax.xml.parsers.SAXParserFactory;
import metricsanalyzer.util.io.Files;
import metricsanalyzer.util.xml.SAXDispatchHandler;
import metricsanalyzer.util.xml.XMLWriter;
import org.xml.sax.Attributes;

/**
 * Represents a metrics analyzer project.
 */
public class Project {

  public static final String DTD
    = "<!DOCTYPE metrics_analyzer_project [\n"
    + "  <!ELEMENT metrics_analyzer_project (imports?, queries?, database?)>\n"
    + "    <!ELEMENT imports                (import*)>\n"
    + "      <!ELEMENT import               (file_name, class_name)>\n"
    + "        <!ELEMENT file_name          (#PCDATA)>\n"
    + "        <!ELEMENT class_name         (#PCDATA)>\n"
    + "    <!ELEMENT queries                (query*)>\n"
    + "      <!ELEMENT query                (file_name)>\n"
    + "    <!ELEMENT database               (driver, url, user, password?, basename)>\n"
    + "      <!ELEMENT driver               (#PCDATA)>\n"
    + "      <!ELEMENT url                  (#PCDATA)>\n"
    + "      <!ELEMENT user                 (#PCDATA)>\n"
    + "      <!ELEMENT password             (#PCDATA)>\n"
    + "      <!ELEMENT basename             (#PCDATA)>\n"
    + "]>\n"
    ;

  // *** Facade interface ***

  public static Project load(File file) throws Exception {
    Project project = new Project();
    Handler handler = new Handler(project);
    SAXParserFactory.newInstance().newSAXParser().parse(file, handler);
    if (!handler.valid)
      throw new RuntimeException("Not a valid project file");
    return project;
  }

  public void save(File file) throws Exception {
    XMLWriter writer = new XMLWriter(new PrintWriter(new BufferedOutputStream(new FileOutputStream(file))));

    writer.open();
    writer.printComment("This is generated XML. Do not edit!");
    writer.printDTD(DTD);

    writer.beginElement("metrics_analyzer_project");

    // MetricsImportList
    if (0 != importList.getSize()) {
      writer.beginElement("imports");

      for (int i=0; i<importList.getSize(); ++i) {
        writer.beginElement("import");

        ImportList.Entry entry = (ImportList.Entry)importList.getElementAt(i);
        writer.printElement("file_name", entry.file.getPath());
        writer.printElement("class_name", entry.importer.getClass().getName());

        writer.endElement();
      }
      writer.endElement();
    }

    // QueryList
    if (0 < queryList.getSize()) {
      writer.beginElement("queries");

      for (int i=0; i<queryList.getSize(); ++i) {
        writer.beginElement("query");
        writer.printElement("file_name", ((QueryList.Entry)queryList.getElementAt(i)).file.getPath());
        writer.endElement();
      }
      writer.endElement();
    }

    // Database
    if (null != database) {
      writer.beginElement("database");

      writer.printElement("driver", database.driver);
      writer.printElement("url", database.url);
      writer.printElement("user", database.user);
      if (null != database.password)
        writer.printElement("password", database.password);
      writer.printElement("basename", database.basename);

      writer.endElement();
    }

    writer.endElement();
    writer.close();
  }

  public void addImporter(File file, String importerClassName) throws Exception {
    getImportList().add(file, importerClassName);
  }

  public void removeImporters(int[] selectedIndices) {
    getImportList().remove(selectedIndices);
  }

  public void createTables() throws Exception {
    getDatabase().createTables();
  }

  public void dropTables() throws Exception {
    getDatabase().dropTables();
  }

  public void importAs(String name) throws Exception {
    getDatabase().historyList.add(name, getImportList());
  }

  public void removeSnapshots(int[] selectedIndices) throws Exception {
    getDatabase().historyList.remove(selectedIndices);
  }

  public void loadQuery(File file) throws Exception {
    getQueryList().add(new QueryList.Entry(file,
                                           Files.readFile(file)));
  }

  public void removeQueries(int[] selectedIndices) {
    getQueryList().remove(selectedIndices);
  }

  public TableModel[] executeQuery(String query,
                                   int selectedIndex,
                                   int[] selectedIndices) throws Exception {
    return getDatabase().executeQuery(query,
                                      selectedIndex,
                                      selectedIndices);
  }

  public void setLogging(boolean onOff) {
    getDatabase().setLogging(onOff);
  }

  // *** Accessors and Mutators ***

  public ImportList getImportList() {
    return importList;
  }
  
  public QueryList getQueryList() {
    return queryList;
  }

  public Database getDatabase() {
    return database;
  }

  public void setDatabase(Database database) {
    this.database = database;
  }

  // *** Implementation ***

  private ImportList importList = new ImportList();
  private QueryList queryList = new QueryList();
  private Database database = null;

  public static class Handler extends SAXDispatchHandler {
    Handler(Project project) throws Exception {
      this.project = project;
    }

    public void start_metrics_analyzer_project(Attributes attr) {
      valid = true;
    }

    // ImportList

    public void chars_file_name_import(String data) {
      importFile = data;
    }

    public void chars_class_name_import(String data) {
      importClass = data;
    }

    public void end_import() throws Exception {
      project.importList.add(new File(importFile), importClass);
    }

    private String importFile;
    private String importClass;

    // QueryList

    public void chars_file_name_query(String data) {
      try {
        project.loadQuery(new File(data));
      } catch (Exception e) {
        System.err.print("While reading query: ");
        e.printStackTrace();
      }
    }

    // Database

    public void chars_driver_database(String data) {
      databaseDriver = data;
    }

    public void chars_url_database(String data) {
      databaseURL = data;
    }

    public void chars_user_database(String data) {
      databaseUser = data;
    }

    public void chars_password_database(String data) {
      databasePassword = data;
    }

    public void chars_basename_database(String data) {
      databaseBasename = data;
    }

    public void end_database() throws Exception {
      project.database = new Database(databaseDriver,
                                      databaseURL,
                                      databaseUser,
                                      databasePassword,
                                      databaseBasename);
    }

    private String databaseDriver;
    private String databaseURL;
    private String databaseUser;
    private String databasePassword;
    private String databaseBasename;

    boolean valid = false;
    private Project project;
  }
}
