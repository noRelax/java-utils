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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * ResultSet cast as a TableModel.
 */
public class ResultSetTable extends AbstractTableModel {
  private static final int FETCH_SIZE = 50;

  public ResultSetTable(ResultSet rs) throws Exception {
    this.rs = rs;

    colCnt = rs.getMetaData().getColumnCount();

    hasMore = rs.next();
    fetch(FETCH_SIZE);
  }

  public int getRowCount() {
    return hasMore ? cache.size() + 1 : cache.size();
  }

  public int getColumnCount() {
    return colCnt;
  }

  public String getColumnName(int col) {
    try {
      return rs.getMetaData().getColumnLabel(col+1);
    } catch (Exception e) {
      e.printStackTrace();
      return "failed to fetch column name!";
    }
  }

  public Class getColumnClass(int col) {
    try {
      switch (rs.getMetaData().getColumnType(col+1)) {
        //case Types.BOOLEAN:   return Boolean.class; // Requires Java 1.4
      case Types.INTEGER:   return Integer.class;
      case Types.SMALLINT:  return Integer.class;
      case Types.TIMESTAMP: return Timestamp.class;
      case Types.VARCHAR:   return String.class;
      default:              return Object.class;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return Object.class;
    }
  }

  public Object getValueAt(int row, int col) {
    if (cache.size() <= row)
      fetch(row+FETCH_SIZE);

    return ((Object[])cache.get(row))[col];
  }

  private void fetch(int row) {
    try {
      int sizeOnEntry = cache.size();

      while (hasMore && cache.size() <= row) {
        Object[] newRow = new Object[colCnt];
        for (int i=0; i<colCnt; ++i)
          newRow[i] = rs.getObject(i+1);
        cache.add(newRow);

        hasMore = rs.next();
      }

      fireTableRowsInserted(sizeOnEntry, cache.size()-1);
    } catch (Exception e) {
      hasMore = false;
      e.printStackTrace();
    }
  }

  private ArrayList cache = new ArrayList();
  private ResultSet rs;
  private final int colCnt;
  private boolean hasMore;
}
