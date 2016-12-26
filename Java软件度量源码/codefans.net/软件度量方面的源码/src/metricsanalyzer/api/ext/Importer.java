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

package metricsanalyzer.api.ext;

import java.io.File;
import metricsanalyzer.api.data.Relation;
import metricsanalyzer.api.data.TableBuilder;

/**
 * Imports Relational data and adds it to the TableBuilder.
 */
public abstract class Importer {
  public abstract void doImport(File file,
                                TableBuilder tableBuilder) throws Exception;

  public abstract Relation[] getRelations();
}
