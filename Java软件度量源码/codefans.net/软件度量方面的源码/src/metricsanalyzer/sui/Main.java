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

package metricsanalyzer.sui;

import java.io.File;
import javax.swing.JOptionPane;
import metricsanalyzer.api.Project;
import metricsanalyzer.util.gui.ActionCancelledException;

/**
 * Scriptable UI for MetricsAnalyzer.
 */
public class Main {
  public static final String USAGE
    = "Usage: java metricsanalyzer.sui.Main <project-file> <command ...>\n"
    + "\n"
    + "The project file must be the first parameter. The rest of the parameters are\n"
    + "interpreted as commands.\n"
    + "\n"
    + "Available commands:\n"
    + "\n"
    + "  -import-as 'name string'     Imports a new snapshot with the given name.\n"
    + "  -logging                     Log all operations.\n"
    ;

  public static void main(String[] args) {
    try {
      if (args.length < 3) {
        System.out.println(USAGE);
        System.exit(1);
      }

      Project project = Project.load(new File(args[0]));

      for (int argI=1; argI<args.length; ++argI) {
        if ("-import-as".equals(args[argI])) {
          argI += 1;
          if (args.length <= argI)
            throw new ActionCancelledException("The import-as command requires a name.",
                                               "Error",
                                               JOptionPane.ERROR_MESSAGE);

          project.importAs(args[argI]);
        } else if ("-logging".equals(args[argI])) {
          project.setLogging(true);
        } else {
          throw new ActionCancelledException("Unrecognized command:" + args[argI],
                                             "Error",
                                             JOptionPane.ERROR_MESSAGE);
        }
      }
    } catch (ActionCancelledException e) {
      if (null != e.message)
        System.err.println(e.title + ": " + e.message);
      System.exit(1);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
