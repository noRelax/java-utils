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

package metricsanalyzer.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * JUnit test for Strings.
 */
public class StringsTest extends TestCase {
  public StringsTest(String name) {
    super(name);
  }
  public static void main (String[] args) {
    junit.textui.TestRunner.run (suite());
  }
  public static Test suite() {
    return new TestSuite(StringsTest.class);
  }

  public void testSplitAndTrim() throws Exception {
    {
      String[] result = Strings.splitAndTrim("a;bb;cdf", ';');
      assertEquals(3, result.length);
      assertEquals("a",result[0]);
      assertEquals("bb",result[1]);
      assertEquals("cdf",result[2]);
    }
    {
      String[] result = Strings.splitAndTrim("a; ;\nbb;cdf;;", ';');
      assertEquals(3, result.length);
      assertEquals("a",result[0]);
      assertEquals("bb",result[1]);
      assertEquals("cdf",result[2]);
    }
  }
}
