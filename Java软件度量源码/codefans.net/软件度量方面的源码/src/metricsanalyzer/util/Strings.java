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

package metricsanalyzer.util;

import java.util.ArrayList;

/**
 * String manipulation functions.
 */
public class Strings {
  public static boolean hasUpperCaseChar(String s) {
    char[] a = s.toCharArray();

    for (int i=0; i<a.length; ++i)
      if (Character.isUpperCase(a[i]))
        return true;

    return false;
  }

  public static String[] splitAndTrim(String str, char separator) {
    ArrayList result = new ArrayList();

    int begin = 0;
    int end = str.indexOf(separator);

    while (-1 != end) {
      addIfNotJustWhitespace(result, str.substring(begin, end));

      begin = end + 1;
      end = str.indexOf(separator, end + 1);
    }
    addIfNotJustWhitespace(result, str.substring(begin));
    
    return (String[])result.toArray(new String[result.size()]);
  }

  private static void addIfNotJustWhitespace(ArrayList result, String str) {
    str = str.trim();
    if (0 < str.length())
      result.add(str);
  }
}
