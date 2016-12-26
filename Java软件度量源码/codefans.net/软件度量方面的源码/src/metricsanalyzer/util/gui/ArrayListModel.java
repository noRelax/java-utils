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

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * Concrete ArrayListModel for building UIs.
 */
public class ArrayListModel extends AbstractListModel {
  public int getSize() {
    return list.size();
  }
  
  public Object getElementAt(int index) {
    return list.get(index);
  }

  protected void addObject(Object entry) {
    list.add(list.size(), entry);
  }

  protected void addObject(int index, Object entry) {
    list.add(index, entry);
    fireIntervalAdded(this, index, index);
  }

  public void remove(int index) {
    list.remove(index);
    fireIntervalRemoved(this,index,index);
  }

  public void remove(int[] indices) {
    // TBD: Only fire one event!
    for (int i=indices.length-1; 0<=i; --i)
      remove(indices[i]);
  }

  private ArrayList list = new ArrayList();
}
