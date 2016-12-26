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
import metricsanalyzer.util.ArraysExt;

/**
 * Describes a single Relation.
 */
public class Relation {
  public Relation(String name,
                  Attr[] keys,
                  Attr[] values) {
    this.name = name;
    this.keys = keys;
    this.values = values;
  }

  public int hashCode() {
    return Objects.hashCode(name) + ArraysExt.hashCode(keys) + ArraysExt.hashCode(values);
  }

  public boolean equals(Object o) {
    return o instanceof Relation &&
      Objects.equals(name, ((Relation)o).name) &&
      ArraysExt.equals(keys, ((Relation)o).keys) &&
      ArraysExt.equals(values, ((Relation)o).values);
  }

  public final String name;
  public final Attr[] keys;
  public final Attr[] values;
}
