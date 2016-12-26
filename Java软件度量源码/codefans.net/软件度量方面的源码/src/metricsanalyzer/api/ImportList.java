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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import metricsanalyzer.api.data.Relation;
import metricsanalyzer.api.data.TableBuilder;
import metricsanalyzer.api.ext.Importer;
import metricsanalyzer.util.Classes;
import metricsanalyzer.util.gui.ActionCancelledException;
import metricsanalyzer.util.gui.ArrayListModel;

/**
 * Keeps track of multiple Importers.
 */
public class ImportList extends ArrayListModel {

  public static class Entry {
    public Entry(File file,
                 Importer importer) {
      this.file = file;
      this.importer = importer;
    }

    public String toString() {
      return file.getName() + " : " + Classes.getClassPart(importer.getClass().getName());
    }

    public final File file;
    public final Importer importer;
  }

  public void add(File file, String className) throws Exception {
    addObject(new Entry(file, (Importer)Classes.newInstanceForName(className)));
  }

  public Relation[] getRelations() {
    ArrayList relations = new ArrayList();
    for (int i=0; i<getSize(); ++i)
      relations.addAll(Arrays.asList(((Entry)getElementAt(i)).importer.getRelations()));
    return (Relation[])relations.toArray(new Relation[relations.size()]);
  }

  public void doImport(TableBuilder builder) throws Exception {
    if (0 == getSize())
      throw new ActionCancelledException("Importer list is empty!",
                                         "Error",
                                         JOptionPane.ERROR_MESSAGE);

    for (int i=0; i<getSize(); ++i) {
      Entry entry = (Entry)getElementAt(i);

      entry.importer.doImport(entry.file, builder);
    }
  }
}
