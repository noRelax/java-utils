package news;

/**
 * <p>Title: ������������</p>
 * <p>Description: ��ҵ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author �����99630 ��
 * @version 1.0
 * @Download:http://www.codefans.net
 */

import java.io.IOException;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class Index {

  IndexWriter _writer = null;
  Index() throws Exception {
    _writer = new IndexWriter("c:\\News\\index",
                              new ChineseAnalyzer(), true);
  }

  /**
   * ��ÿ�����ż���������
   * @param url ���ŵ�url
   * @param title ���ŵı���
   * @throws java.lang.Exception
   */
  void AddNews(String url, String title) throws Exception {
    Document _doc = new Document();
    _doc.add(Field.Text("title", title));
    _doc.add(Field.UnIndexed("url", url));
    _writer.addDocument(_doc);
  }

  /**
   * �Ż�����������Դ
   * @throws java.lang.Exception
   */
  void close() throws Exception {
    _writer.optimize();
    _writer.close();
  }
}
