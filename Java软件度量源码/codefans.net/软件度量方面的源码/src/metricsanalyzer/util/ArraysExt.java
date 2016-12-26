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

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Utility functions for dealing with arrays.
 */
public class ArraysExt {
  public static Object[] pick(int[] indices, Object[] from) {
    Object[] result = new Object[indices.length];
    for (int i=0; i<indices.length; ++i)
      result[i] = from[indices[i]];
    return result;
  }

  public static boolean isSubSuperSet(Object[] subSet, Object[] superSet, Comparator cmp) {
    return null != mapSubToSuperSet(subSet, superSet, cmp);
  }

  public static int[] mapSubToSuperSet(Object[] subSet, Object[] superSet, Comparator cmp) {
    int[] subSetToSuperSet = new int[subSet.length];

    for (int subSetI=0; subSetI<subSet.length; ++subSetI) {
      subSetToSuperSet[subSetI] = search(subSet[subSetI], superSet, 0, cmp);
      if (-1 == subSetToSuperSet[subSetI])
        return null;
    }
    return subSetToSuperSet;
  }

  public static boolean isIn(Object element, Object[] set, Comparator cmp) {
    return -1 != search(element, set, 0, cmp);
  }

  public static int search(Object element, Object[] set, int i, Comparator cmp) {
    for (; i<set.length; ++i)
      if (0 == cmp.compare(element, set[i]))
        return i;
    return -1;
  }

  public static int[][] mapSubToAllSuperSets(Object[] subSet, Object[] superSet, Comparator cmp) {
    ArrayList result = new ArrayList();

    for (int superSetI=0; superSetI<superSet.length; ++superSetI) {
      if (0 == cmp.compare(subSet[0], superSet[superSetI])) {
        int[] mapping = mapSubToAllSuperSetsHelper(subSet, superSet, superSetI, cmp);
        if (null == mapping)
          break;
        else
          result.add(mapping);
      }
    }
    return (int[][])result.toArray(new int[result.size()][]);
  }

  private static int[] mapSubToAllSuperSetsHelper(Object[] subSet, Object[] superSet, int superSetI, Comparator cmp) {
    int[] subSetToSuperSet = new int[subSet.length];

    for (int subSetI=0; subSetI<subSet.length; ++subSetI) {
      superSetI = search(subSet[subSetI], superSet, superSetI, cmp);

      if (-1 == superSetI)
        return null;

      subSetToSuperSet[subSetI] = superSetI;
    }
    return subSetToSuperSet;
  }

  public static int hashCode(Object[] array) {
    int result = 0;

    if (null != array)
      for (int i=0; i<array.length; ++i)
        result += Objects.hashCode(array[i]);

    return result;
  }

  public static boolean equals(Object[] a1, Object[] a2) {
    return
      a1 == a2 ||
      a1 != null && a2 != null &&
      a1.length == a2.length &&
      equalsHelper(a1,a2);
  }

  private static boolean equalsHelper(Object[] a1, Object[] a2) {
    for (int i=0; i<a1.length; ++i)
      if (!Objects.equals(a1,a2))
        return false;
    return true;
  }

  public static class Range {
    public Range(int lower, int upper) {
      this.lower = lower;
      this.upper = upper;
    }
    public Range(Object[] array) {
      lower = 0;
      upper = array.length;
    }
    public int lower;
    public int upper;
  }

  public static void equalRange(Object key, Object[] array, Comparator cmp, Range initialAndResult) {
    int lower = initialAndResult.lower;
    int length = initialAndResult.upper - lower;

    while (length > 0) {
      int half = length >> 1;
      int middle = lower + half;

      int c = cmp.compare(key, array[middle]);
      if (c > 0) {
        lower = middle + 1;
        length -= half + 1;
      } else if (c < 0) {
        length = half;
      } else {
        initialAndResult.lower = lowerBound(key, array, cmp, new Range(lower, middle));
        initialAndResult.upper = upperBound(key, array, cmp, new Range(middle+1, lower+length));
        return;
      }
    }
    
    initialAndResult.lower = lower;
    initialAndResult.upper = lower;
  }

  public static int lowerBound(Object key, Object[] array, Comparator cmp, Range initial) {
    int lower = initial.lower;
    int length = initial.upper - lower;

    while (length > 0) {
      int half = length >> 1;
      int middle = lower + half;

      if (cmp.compare(key, array[middle]) > 0) {
        lower = middle + 1;
        length -= half + 1;
      } else {
        length = half;
      }
    }
    return lower;
  }

  public static int upperBound(Object key, Object[] array, Comparator cmp, Range initial) {
    int lower = initial.lower;
    int length = initial.upper - lower;

    while (length > 0) {
      int half = length >> 1;
      int middle = lower + half;

      if (cmp.compare(key, array[middle]) < 0) {
        length = half;
      } else {
        lower = middle + 1;
        length -= half + 1;
      }
    }
    return lower;
  }
}
