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

import javax.swing.AbstractListModel;
import java.util.ArrayList;

/**
 * Base for implementing cached ListModels.
 */
public abstract class AbstractCachedListModel extends AbstractListModel {
  public int getSize() {
    return cache.size();
  }

  public Object getElementAt(int index) {
    return cache.get(index);
  }

  public void updateCache() throws Exception {
    int oldSize = cache.size();
    cache.clear();
    fireIntervalRemoved(this, 0, oldSize);

    fillCache();

    fireIntervalAdded(this, 0, cache.size());
  }

  abstract protected void fillCache() throws Exception;

  protected ArrayList cache = new ArrayList();
}
