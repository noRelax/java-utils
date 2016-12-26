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

import java.awt.event.ActionEvent;
import java.util.EventListener;

/**
 * When a FailableActionListener is used with Comp it is automatically
 * replaced by a DefaultFailableAction that catches any exceptions and
 * reports them through using a message dialog.
 */
public interface FailableActionListener extends EventListener {
  void actionPerformed(ActionEvent e) throws Exception;
}
