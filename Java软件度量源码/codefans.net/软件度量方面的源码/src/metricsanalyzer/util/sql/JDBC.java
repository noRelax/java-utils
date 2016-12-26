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

package metricsanalyzer.util.sql;

import java.sql.Connection;
import java.sql.Timestamp;
import metricsanalyzer.util.fun.VoidToVoid;

/**
 * Utility functions for dealing with JDBC.
 */
public class JDBC {
  public static final void executeTransaction(Connection connection,
                                              VoidToVoid transaction) throws Exception {
    if (!connection.getAutoCommit())
      throw new RuntimeException("Attempted to perform a nested transactions!");
   
    connection.setAutoCommit(false);

    try {
      transaction.with();
      connection.commit();
    } catch (Exception e) {
      connection.rollback();
      throw e;
    } finally {
      connection.setAutoCommit(true);
    }
  }

  public static final String formatLit(Object x) {
    if (null == x)
      return "null";
    else if (x instanceof String)
      return "'" + x + "'";
    else if (x instanceof Timestamp)
      return "'" + x + "'";
    else
      return x.toString();
  }
}
