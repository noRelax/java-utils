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

import java.awt.GridBagConstraints;

/**
 * Helper for working with GridBagConstraints.
 */
public class GridBagConstraintsHelper extends GridBagConstraints {
  public GridBagConstraintsHelper() {
    setWH(RELATIVE, RELATIVE);
  }

  public GridBagConstraintsHelper(int x, int y) {
    setXY(x, y);
  }

  public GridBagConstraintsHelper setAnchor(int value) {
    anchor = value;
    return this;
  }

  public GridBagConstraintsHelper setX(int x) {
    gridx = x;
    return this;
  }

  public GridBagConstraintsHelper setY(int y) {
    gridy = y;
    return this;
  }

  public GridBagConstraintsHelper setXY(int x, int y) {
    gridx = x;
    gridy = y;
    return this;
  }

  public GridBagConstraintsHelper setFill(int fill) {
    this.fill = fill;
    return this;
  }

  public GridBagConstraintsHelper setWX(double wx) {
    weightx = wx;
    return this;
  }

  public GridBagConstraintsHelper setWY(double wy) {
    weighty = wy;
    return this;
  }

  public GridBagConstraintsHelper setWXWY(double wx, double wy) {
    weightx = wx;
    weighty = wy;
    return this;
  }

  public GridBagConstraintsHelper setW(int w) {
    gridwidth = w;
    return this;
  }

  public GridBagConstraintsHelper setH(int h) {
    gridheight = h;
    return this;
  }

  public GridBagConstraintsHelper setWH(int w, int h) {
    gridwidth = w;
    gridheight = h;
    return this;
  }
}
