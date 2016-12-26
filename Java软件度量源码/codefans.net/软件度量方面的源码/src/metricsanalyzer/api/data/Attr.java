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

package metricsanalyzer.api.data;

import metricsanalyzer.util.Objects;

/**
 * Describes a single Attribute.
 */
public class Attr {
  public Attr(String name,
              String type) {
    this.name = name;
    this.type = type;
  }

  public int hashCode() {
    return Objects.hashCode(name) + Objects.hashCode(type);
  }

  public boolean equals(Object o) {
    return o instanceof Attr &&
      Objects.equals(name, ((Attr)o).name) &&
      Objects.equals(type, ((Attr)o).type);
  }

  public final String name;
  public final String type;
}
