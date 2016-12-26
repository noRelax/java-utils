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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * Catches any exceptions from the given FailableActionListener and reports
 * them using a message dialog. ActionCancelledExceptions without a message
 * are simply discarded.
 */
public class DefaultFailableAction implements ActionListener {
  public DefaultFailableAction(Component parent,
                               FailableActionListener failable) {
    this.parent = parent;
    this.failable = failable;
  }

  public void actionPerformed(ActionEvent e) {
    try {
      failable.actionPerformed(e);
    } catch (ActionCancelledException ex) {
      if (null != ex.message)
        JOptionPane.showMessageDialog(parent,
                                      ex.message,
                                      ex.title,
                                      ex.messageType);
    } catch (Exception ex) {
      ex.printStackTrace();

      JOptionPane.showMessageDialog(parent,
                                    ex.toString(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

  private Component parent;
  private FailableActionListener failable;
}
