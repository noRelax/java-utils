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

package metricsanalyzer.util.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import metricsanalyzer.util.ArraysExt;
import metricsanalyzer.util.Stack;
import metricsanalyzer.util.StringBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Helper for writing SAXParser handlers.
 */
public class SAXDispatchHandler extends DefaultHandler {
  public SAXDispatchHandler() throws Exception {
    if (0 == installMethods(collectMethods(getClass().getMethods(), buildLists())))
      throw new RuntimeException("Must define some hooks!");
  }

  public void error(SAXParseException e) throws SAXException {
    throw e;
  }

  public void warning(SAXParseException e) throws SAXException {
    throw e;
  }

  public void startElement(String uri,
                           String localName,
                           String qName,
                           Attributes attributes) throws SAXException {
    invoke(chars_Methods, new Object[]{charsBuilder.extract()});

    stateStack.push(qName);
    invoke(start_Methods, new Object[]{attributes});
  }

  public void characters(char[] ch,
                         int start,
                         int length) {
    charsBuilder.p(ch, start, length);
  }

  public void endElement(String uri,
                         String localName,
                         String qName) throws SAXException {
    invoke(chars_Methods, new Object[]{charsBuilder.extract()});

    invoke(end_Methods, new Object[]{});
    stateStack.pop();
  }

  private void invoke(NameMethodPair[] methods, Object[] args) {
    try {
      ArraysExt.Range range = new ArraysExt.Range(methods);
      StringBuffer prefixBuffer = new StringBuffer();

      for (int i=1; i<=stateStack.size(); ++i) {
        prefixBuffer.append("_").append(stateStack.top(i));
        String prefix = prefixBuffer.toString();

        ArraysExt.equalRange(prefix, methods, prefixComparator, range);

        if (range.lower == range.upper)
          break;

        if (prefix.equals(methods[range.lower].name))
          methods[range.lower].method.invoke(this, args);
      }
    } catch (Exception e) {
      e.printStackTrace();

      System.err.print("State stack:");
      for (int i=1; i<=stateStack.size(); ++i)
        System.err.print(" " + stateStack.top(i));
      System.err.print("\n");
    }
  }

  private static Comparator prefixComparator =
    new Comparator() {
      public int compare(Object o1, Object o2) {
        String prefix = (String)o1;
        NameMethodPair nmp = (NameMethodPair)o2;
        int result = prefix.compareTo(nmp.name);
        return 0 != result && nmp.name.startsWith(prefix) ? 0 : result;
      }};

  private static class NameMethodPair implements Comparable {
    NameMethodPair(String name, Method method) {
      this.name = name;
      this.method = method;
    }

    public int compareTo(Object o) {
      return name.compareTo(((NameMethodPair)o).name);
    }

    public final String name;
    public final Method method;
  }

  private static ArrayList[] buildLists() {
    ArrayList[] lists = new ArrayList[PATTERNS.length];
    for (int i=0; i<lists.length; ++i)
      lists[i] = new ArrayList();
    return lists;
  }

  private ArrayList[] collectMethods(Method[] methods, ArrayList[] lists) {
    for (int i=0; i<methods.length; ++i)
      for (int j=0; j<PATTERNS.length; ++j)
        if (methods[i].getName().startsWith(PATTERNS[j]))
          lists[j].add(new NameMethodPair(methods[i].getName().substring(PATTERNS[j].length()-1),
                                          methods[i]));
    return lists;
  }

  private int installMethods(ArrayList[] lists) throws Exception {
    int methodCnt = 0;
    for (int i=0; i<PATTERNS.length; ++i) {
      Object[] array = lists[i].toArray(new NameMethodPair[lists[i].size()]);
      Arrays.sort(array);
      SAXDispatchHandler.class.getDeclaredField(PATTERNS[i]+"Methods").set(this, array);

      methodCnt += array.length;
    }
    return methodCnt;
  }

  protected final Stack stateStack = new Stack();

  private static final String[] PATTERNS = {"chars_", "start_", "end_"};
  private NameMethodPair[] chars_Methods, start_Methods, end_Methods;

  private StringBuilder charsBuilder = new StringBuilder();
}
