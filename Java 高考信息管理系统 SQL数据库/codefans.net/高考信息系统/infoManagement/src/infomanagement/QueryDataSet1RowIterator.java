package infomanagement;
//download by http://www.codefans.net
import com.borland.dx.dataset.*;

public class QueryDataSet1RowIterator {
  private RowIterator rowIterator = new RowIterator();

  public QueryDataSet1RowIterator() {
  }
  void bind(DataSet dataset) throws DataSetException {
    rowIterator.bind(dataset);
  }
  void bind(ReadRow readRow) throws DataSetException {
    rowIterator.bind(readRow);
  }
  void bind(ReadWriteRow readWriteRow) throws DataSetException {
    rowIterator.bind(readWriteRow);
  }
  void bind(RowIterator ri) throws DataSetException {
    rowIterator.bind(ri);
  }
  public void first() throws DataSetException {
    rowIterator.first();
  }
  public void last() throws DataSetException {
    rowIterator.last();
  }
  public boolean next() throws DataSetException {
    return rowIterator.next();
  }
  public boolean prior() throws DataSetException {
    return rowIterator.prior();
  }
  public int getCardID() throws DataSetException {
    return rowIterator.getInt("cardID");
  }
  public void setCardID(int value) throws DataSetException {
    rowIterator.setInt("cardID", value);
  }
  public String getStudentName() throws DataSetException {
    return rowIterator.getString("studentName");
  }
  public void setStudentName(String value) throws DataSetException {
    rowIterator.setString("studentName", value);
  }
  public String getSchoolBefore() throws DataSetException {
    return rowIterator.getString("schoolBefore");
  }
  public void setSchoolBefore(String value) throws DataSetException {
    rowIterator.setString("schoolBefore", value);
  }
}