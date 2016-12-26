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
 * An unsynchronized Stack.
 */
public final class Stack implements Cloneable {
  public Stack() {
    this(new ArrayList());
  }

  public boolean isEmpty() {
    return 0 == list.size();
  }

  public int size() {
    return list.size();
  }

  public void push(Object o) {
    list.add(o);
  }

  public Object pop() {
    return list.remove(size()-1);
  }

  public Object top() {
    return top(1);
  }

  public Object top(int offset) {
    return list.get(size()-offset);
  }

  public void set(Object o) {
    set(1, o);
  }

  public void set(int offset, Object o) {
    list.set(size()-offset, o);
  }

  public Object clone() {
    return new Stack((ArrayList)list.clone());
  }

  private Stack(ArrayList list) {
    this.list = list;
  }

  private ArrayList list;
}
