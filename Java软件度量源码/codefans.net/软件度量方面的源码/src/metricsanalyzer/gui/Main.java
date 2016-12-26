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

import java.io.File;
import javax.swing.JFrame;

/**
 * Launches the MetricsAnalyzerFrame.
 */
public class Main {
  public static void main(String[] args) {
    try {
      File projectFile = null;

      if (1 == args.length)
        projectFile = new File(args[args.length-1]);

      JFrame frame = new MetricsAnalyzerFrame(projectFile);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.show();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
