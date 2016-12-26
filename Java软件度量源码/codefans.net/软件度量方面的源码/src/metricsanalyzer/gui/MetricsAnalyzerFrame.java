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

package metricsanalyzer.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import metricsanalyzer.api.Database;
import metricsanalyzer.api.Project;
import metricsanalyzer.api.QueryList;
import metricsanalyzer.util.fun.ObjectToObject;
import metricsanalyzer.util.gui.AbstractFileChooserAction;
import metricsanalyzer.util.gui.ActionCancelledException;
import metricsanalyzer.util.gui.Comp;
import metricsanalyzer.util.gui.FailableActionListener;
import metricsanalyzer.util.gui.GridBagConstraintsHelper;
import metricsanalyzer.util.io.Files;

/**
 * GUI to the MetricsAnalyzer.
 */
public class MetricsAnalyzerFrame extends JFrame {
  private JFileChooser projectFileChooser = new JFileChooser(new File("."));
  private JFileChooser queryFileChooser = new JFileChooser(new File("."));

  private File projectFile;
  private Project project;

  private JTabbedPane tabbedPane;
  private Component metricsTab;

  private JList importJList = new JList();
  private JList historyJList = new JList();
  private JList queryJList = new JList();

  private JTextField driverField = new JTextField(28);
  private JTextField basenameField = new JTextField(28);
  private JTextField urlField = new JTextField(28);
  private JTextField userField = new JTextField(28);
  private JTextField passwordField = new JTextField(28);

  private void setProject(File projectFile, Project project) throws Exception {
    this.projectFile = projectFile;

    setTitle((null != projectFile ? projectFile.getName() : "no project file") + " - MetricsAnalyzer");

    this.project = project;

    importJList.setModel(project.getImportList());

    if (null != project.getDatabase()) {
      historyJList.setModel(project.getDatabase().historyList);
      queryJList.setModel(project.getQueryList());

      if (-1 == tabbedPane.indexOfComponent(metricsTab))
        tabbedPane.add(metricsTab);

      driverField.setText(project.getDatabase().driver);
      urlField.setText(project.getDatabase().url);
      userField.setText(project.getDatabase().user);
      passwordField.setText(project.getDatabase().password);
      basenameField.setText(project.getDatabase().basename);
    } else {
      if (-1 != tabbedPane.indexOfComponent(metricsTab))
        tabbedPane.remove(metricsTab);

      driverField.setText("");
      urlField.setText("");
      userField.setText("");
      passwordField.setText("");
      basenameField.setText("");
    }
  }

  public MetricsAnalyzerFrame(File initialProjectFile) throws Exception {
    setSize(400,500);

    Component settingsTab = 
      new Comp(new Box(BoxLayout.Y_AXIS),
               new Object[]{
                 "Settings",
                 new Comp(newPanelWithBoxLayout(BoxLayout.Y_AXIS),
                          new Object[]{
                            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Imports"),
                            new JScrollPane(importJList),
                            new Comp(new Box(BoxLayout.X_AXIS),
                                     new Object[]{
                                       new Comp(new JButton("Add..."),
                                                new AbstractFileChooserAction(getContentPane()) {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    File file = openFile();
                                                    project.addImporter(file, chooseImporter(file));
                                                  }

                                                  String chooseImporter(File file) throws Exception {
                                                    String className =
                                                      JOptionPane.showInputDialog(getContentPane(),
                                                                                  "Give fully qualified Importer class name:",
                                                                                  file.getName(),
                                                                                  JOptionPane.PLAIN_MESSAGE);

                                                    if (null == className)
                                                      throw new ActionCancelledException();

                                                    return className;
                                                  }
                                                }),
                                       new Comp(new JButton("Remove"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) {
                                                    project.removeImporters(importJList.getSelectedIndices());
                                                  }
                                                })})}),
                 new Comp(newPanelWithBoxLayout(BoxLayout.Y_AXIS),
                          new Object[]{
                            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Database"),
                            new Comp(new JPanel(new GridBagLayout()),
                                     new Object[]{
                                       new Comp(new JLabel("Driver: "),
                                                new GridBagConstraintsHelper(0,0).setAnchor(GridBagConstraints.EAST)),
                                       new Comp(driverField,
                                                new GridBagConstraintsHelper(1,0).setFill(GridBagConstraints.HORIZONTAL).setWX(1)),
                                       new Comp(new JLabel("URL: "),
                                                new GridBagConstraintsHelper(0,1).setAnchor(GridBagConstraints.EAST)),
                                       new Comp(urlField,
                                                new GridBagConstraintsHelper(1,1).setFill(GridBagConstraints.HORIZONTAL).setWX(1)),
                                       new Comp(new JLabel("User: "),
                                                new GridBagConstraintsHelper(0,2).setAnchor(GridBagConstraints.EAST)),
                                       new Comp(userField,
                                                new GridBagConstraintsHelper(1,2).setFill(GridBagConstraints.HORIZONTAL).setWX(1)),
                                       new Comp(new JLabel("Password: "),
                                                new GridBagConstraintsHelper(0,3).setAnchor(GridBagConstraints.EAST)),
                                       new Comp(passwordField,
                                                new GridBagConstraintsHelper(1,3).setFill(GridBagConstraints.HORIZONTAL).setWX(1)),
                                       new Comp(new JLabel("Basename: "),
                                                new GridBagConstraintsHelper(0,4).setAnchor(GridBagConstraints.EAST)),
                                       new Comp(basenameField,
                                                new GridBagConstraintsHelper(1,4).setFill(GridBagConstraints.HORIZONTAL).setWX(1)),
                                       new ObjectToObject() {
                                         public Object with(Object o) {
                                           Comp.limitMaxSizeByPrefSize((JPanel)o, false, true);
                                           return o;
                                         }}}),
                            new Comp(new Box(BoxLayout.X_AXIS),
                                     new Object[]{
                                       new Comp(new JButton("Set"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    project.setDatabase(new Database(driverField.getText(),
                                                                                     urlField.getText(),
                                                                                     userField.getText(),
                                                                                     passwordField.getText(),
                                                                                     basenameField.getText()));

                                                    setProject(projectFile, project);
                                                  }
                                                }),
                                       new Comp(new JButton("Create tables"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    if (null == project.getDatabase())
                                                      throw new ActionCancelledException("Database has not been set!",
                                                                                         "Database table creation",
                                                                                         JOptionPane.ERROR_MESSAGE);

                                                    project.createTables();
                                                  }
                                                }),
                                       new Comp(new JButton("Drop tables"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    if (null == project.getDatabase())
                                                      throw new ActionCancelledException("Database has not been set!",
                                                                                         "Database table creation",
                                                                                         JOptionPane.ERROR_MESSAGE);

                                                    project.dropTables();
                                                  }
                                                })})})}).build(getContentPane());

    metricsTab =
      new Comp(new Box(BoxLayout.Y_AXIS),
               new Object[]{
                 "Metrics",
                 new Comp(newPanelWithBoxLayout(BoxLayout.Y_AXIS),
                          new Object[]{
                            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "History"),
                            new JScrollPane(historyJList),
                            new Comp(new Box(BoxLayout.X_AXIS),
                                     new Object[]{
                                       new Comp(new JButton("Import as..."),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    project.importAs(chooseName());
                                                  }
                                         
                                                  String chooseName() throws Exception {
                                                    String name =
                                                      JOptionPane.showInputDialog(getContentPane(),
                                                                                  "Give name for metrics:",
                                                                                  "Importing metrics...",
                                                                                  JOptionPane.PLAIN_MESSAGE);

                                                    if (null == name)
                                                      throw new ActionCancelledException();

                                                    return name;
                                                  }
                                                }),
                                       new Comp(new JButton("Remove"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    if (JOptionPane.YES_OPTION != 
                                                        JOptionPane.showConfirmDialog(getContentPane(),
                                                                                      "Permanently remove selected snapshots?",
                                                                                      "Remove confirmation",
                                                                                      JOptionPane.YES_NO_OPTION,
                                                                                      JOptionPane.WARNING_MESSAGE))
                                                      throw new ActionCancelledException();
                                                    
                                                    project.removeSnapshots(historyJList.getSelectedIndices());
                                                  }
                                                })})}),
                 new Comp(newPanelWithBoxLayout(BoxLayout.Y_AXIS),
                          new Object[]{
                            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Query"),
                            new JScrollPane(queryJList),
                            new Comp(new Box(BoxLayout.X_AXIS),
                                     new Object[]{
                                       new Comp(new JButton("Execute"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    QueryList.Entry queryEntry = getQueryEntry();

                                                    new TableModelBrowserFrame(queryEntry.toString(),
                                                                               project.executeQuery(queryEntry.query,
                                                                                                    historyJList.getSelectedIndex(),
                                                                                                    historyJList.getSelectedIndices()));
                                                  }

                                                  QueryList.Entry getQueryEntry() throws Exception {
                                                    int i = queryJList.getSelectedIndex();
                                                    if (-1 == i)
                                                      throw new ActionCancelledException("Please select a query.",
                                                                                         "Error",
                                                                                         JOptionPane.ERROR_MESSAGE);

                                                    return (QueryList.Entry)project.getQueryList().getElementAt(i);
                                                  }
                                                }),
                                       new Comp(new JButton("Edit"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    int i = queryJList.getSelectedIndex();
                                                    QueryList.Entry entry = -1 == i
                                                      ? new QueryList.Entry(new File(""),"")
                                                      : (QueryList.Entry)project.getQueryList().getElementAt(i);

                                                    new QueryEditorFrame(entry.file.getPath(), entry.query);
                                                  }
                                                }),
                                       new Comp(new JButton("New"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    new QueryEditorFrame("", "");
                                                  }
                                                }),
                                       new Comp(new JButton("Load..."),
                                                new AbstractFileChooserAction(getContentPane(), queryFileChooser) {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    project.loadQuery(openFile());
                                                  }
                                                }),
                                       new Comp(new JButton("Remove"),
                                                new FailableActionListener() {
                                                  public void actionPerformed(ActionEvent e) throws Exception {
                                                    project.removeQueries(queryJList.getSelectedIndices());
                                                  }
                                                })})})}).build(getContentPane());

    tabbedPane = (JTabbedPane)
      new Comp(new JTabbedPane(),
               new Object[]{
                 new ChangeListener() {
                   public void stateChanged(ChangeEvent ev) {
                     if (null != tabbedPane && tabbedPane.getSelectedComponent() == metricsTab) {
                       try {
                         project.getDatabase().historyList.updateCache();
                       } catch (Exception ex) {
                         JOptionPane.showMessageDialog(getContentPane(),
                                                       ex.toString(),
                                                       "Error",
                                                       JOptionPane.ERROR_MESSAGE);
                       }
                     }
                   }},
                 settingsTab}).build(getContentPane());
    
    getContentPane().add(tabbedPane);

    final Component menu =
      new Comp(new JMenuBar(),
               new Object[]{
                 new Comp(new JMenu("Project"),
                          new Object[]{
                            new Comp(new JMenuItem("Open.."),
                                     new AbstractFileChooserAction(getContentPane(), projectFileChooser) {
                                       public void actionPerformed(ActionEvent e) throws Exception {
                                         File projectFile = openFile();
                                         setProject(projectFile, Project.load(projectFile));
                                         ((JTabbedPane)tabbedPane).setSelectedComponent(metricsTab);
                                       }
                                     }),
                            new Comp(new JMenuItem("Save"),
                                     new AbstractFileChooserAction(getContentPane(), projectFileChooser) {
                                       public void actionPerformed(ActionEvent e) throws Exception {
                                         File theProjectFile = projectFile;

                                         if (null == theProjectFile)
                                           theProjectFile = saveFile();

                                         project.save(theProjectFile);
                                         setProject(theProjectFile, project);
                                       }
                                     }),
                            new Comp(new JMenuItem("Save as..."),
                                     new AbstractFileChooserAction(getContentPane(), projectFileChooser) {
                                       public void actionPerformed(ActionEvent e) throws Exception {
                                         File projectFile = saveFile();
                                         project.save(projectFile);
                                         setProject(projectFile, project);
                                       }
                                     })})}).build(getContentPane());
    setJMenuBar((JMenuBar)menu);

    if (null != initialProjectFile)
      setProject(initialProjectFile, Project.load(initialProjectFile));
    else
      setProject(null, new Project());
  }

  static private JPanel newPanelWithBoxLayout(int axis) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, axis));
    return panel;
  }

  private class QueryEditorFrame extends JFrame {
    private JTextArea queryArea = new JTextArea();
    private JTextField fileNameField = new JTextField(32);

    QueryEditorFrame(String fileName, String query) throws Exception {
      setSize(640,480);
      setTitle("QueryEditor - MetricsAnalyzer");
      queryArea.setText(query);
      fileNameField.setText(fileName);
      
      Component component =
        new Comp(new Box(BoxLayout.Y_AXIS),
                 new Object[]{
                   new Comp(new Box(BoxLayout.X_AXIS),
                            new Object[]{
                              new Comp(new JButton("File..."),
                                       new AbstractFileChooserAction(getContentPane(), queryFileChooser) {
                                         public void actionPerformed(ActionEvent e) throws Exception {
                                           fileNameField.setText(saveFile().getPath());
                                         }
                                       }),
                              Comp.limitMaxSizeByPrefSize(fileNameField, false, true)}),
                   new JScrollPane(queryArea),
                   new Comp(new Box(BoxLayout.X_AXIS),
                            new Object[]{
                              new Comp(new JButton("Save"),
                                       new FailableActionListener() {
                                         public void actionPerformed(ActionEvent e) throws Exception {
                                           File file = new File(fileNameField.getText());
                                           String query = queryArea.getText();

                                           if (0 == file.getPath().length() || 0 == query.length())
                                             throw new ActionCancelledException("File or query is missing!",
                                                                                "Error",
                                                                                JOptionPane.ERROR_MESSAGE);

                                           if (file.exists() && 
                                               JOptionPane.YES_OPTION != 
                                               JOptionPane.showConfirmDialog(getContentPane(),
                                                                             "A file by the name:\n" +
                                                                             "\n" +
                                                                             "  " + file.getPath() + "\n" +
                                                                             "\n" +
                                                                             "already exists. Do you wish to overwrite?",
                                                                             "File overwrite confirmation",
                                                                             JOptionPane.YES_NO_OPTION,
                                                                             JOptionPane.WARNING_MESSAGE))
                                             throw new ActionCancelledException();

                                           Files.writeFile(file, query);
                                           project.loadQuery(file);
                                           dispose();
                                         }
                                       })})}).build();
      
      getContentPane().add(component);

      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      show();
    }
  }

  private static class TableModelBrowserFrame extends JFrame {
    TableModelBrowserFrame(String queryName, TableModel[] results) throws Exception {
      setSize(640,480);
      setTitle(queryName + " - MetricsAnalyzer");
      
      Box container = new Box(BoxLayout.Y_AXIS);

      for (int i=0; i<results.length; ++i)
        container.add(new JScrollPane(new JTable(results[i])));

      getContentPane().add(container);

      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      show();
    }
  }
}
