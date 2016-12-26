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

package metricsanalyzer.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Utility functions for dealing with Files.
 */
public class Files {
  public static String readFile(File file) throws Exception {
    // TBD: This can be optimized
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuffer result = new StringBuffer();

    for (int c = reader.read(); -1 != c; c = reader.read())
      result.append((char)c);

    return result.toString();
  }

  public static void writeFile(File file, String data) throws Exception {
    FileWriter fw = new FileWriter(file);
    fw.write(data);
    fw.close();
  }

  public static String nameWithoutSuffix(String fileName) {
    int dot = fileName.indexOf('.');
    return fileName.substring(0, -1 != dot ? dot : fileName.length());
  }
}
