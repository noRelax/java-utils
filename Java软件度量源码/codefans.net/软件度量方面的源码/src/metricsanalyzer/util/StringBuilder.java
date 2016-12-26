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

/**
 * Helper to build many strings quickly.
 *
 * In case you wonder, `p' is short for `plus'.
 */
public final class StringBuilder {
  public final StringBuilder p(String x) {
    sb.append(x);
    return this;
  }

  public final StringBuilder p(int x) {
    sb.append(x);
    return this;
  }

  public final StringBuilder p(Integer x) {
    sb.append(x);
    return this;
  }

  public final StringBuilder p(Boolean x) {
    sb.append(x);
    return this;
  }

  public final StringBuilder p(Object x) {
    sb.append(x);
    return this;
  }

  public final StringBuilder p(char[] ch, int offset, int length) {
    sb.append(ch, offset, length);
    return this;
  }

  public final String toString() {
    return sb.toString();
  }

  public final String extract() {
    String result = sb.toString();
    sb.setLength(0);
    return result;
  }

  private final StringBuffer sb = new StringBuffer();
}
