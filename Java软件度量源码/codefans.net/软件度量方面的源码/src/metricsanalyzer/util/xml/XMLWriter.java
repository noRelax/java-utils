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

import java.io.PrintWriter;
import metricsanalyzer.util.Stack;

/**
 * Helper for writing XML files.
 *
 * The main purpose of this class is to automatically handle indentation and end tags.
 */
public class XMLWriter {
  public XMLWriter(PrintWriter pw) {
    this.pw = pw;
  }

  public void open() {
    pw.println("<?xml version=\"1.0\"?>");
  }

  public void printDTD(String dtd) {
    pw.print(dtd);
  }

  public void printComment(String comment) {
    println("<!-- " + comment + " -->");
  }

  public void beginElement(String element) {
    println("<" + element + ">");
    elementStack.push(element);
  }

  public void printElement(String element, String content) {
    println("<" + element + ">" + content + "</" + element + ">");
  }

  public void endElement() {
    println("</" + elementStack.pop() + ">");
  }

  public void close() {
    if (0 != elementStack.size())
      throw new RuntimeException("Open tags");

    pw.close();
  }

  private void println(String s) {
    for (int i=0; i<elementStack.size(); ++i)
      pw.print("  ");

    pw.println(s);
  }

  private Stack elementStack = new Stack();
  private PrintWriter pw;
}
