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

package metricsanalyzer.util.gui;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Base class for implementing actions that open up a file chooser dialog.
 */
public abstract class AbstractFileChooserAction implements FailableActionListener {
  public AbstractFileChooserAction(Component parent) {
    this(parent, new JFileChooser(new File(".")));
  }

  public AbstractFileChooserAction(Component parent,
                                   JFileChooser fileChooser) {
    this.parent = parent;
    this.fileChooser = fileChooser;
  }

  public File openFile() throws ActionCancelledException {
    return getFileOrCancel(fileChooser.showOpenDialog(parent));
  }

  public File saveFile() throws ActionCancelledException {
    return getFileOrCancel(fileChooser.showSaveDialog(parent));
  }

  private File getFileOrCancel(int option) throws ActionCancelledException {
    if (JFileChooser.APPROVE_OPTION != option)
      throw new ActionCancelledException();

    return fileChooser.getSelectedFile();
  }

  private Component parent;
  private JFileChooser fileChooser;
}
