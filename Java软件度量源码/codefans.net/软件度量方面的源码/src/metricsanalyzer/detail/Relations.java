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

package metricsanalyzer.detail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import metricsanalyzer.api.data.Attr;
import metricsanalyzer.api.data.Relation;
import metricsanalyzer.util.ArraysExt;
import metricsanalyzer.util.Maps;

public class Relations {

  public static Relation[] merge(Relation[] relations) {
    return sort(makeRelations(makeRelationMap(relations)));
  }

  private static Relation[] makeRelations(TreeMap relationToValues) {
    Relation[] result = new Relation[relationToValues.size()];

    int i = 0;
    Iterator ite = relationToValues.entrySet().iterator();

    while (ite.hasNext()) {
      Map.Entry entry = (Map.Entry)ite.next();
      Relation relation = (Relation)entry.getKey();
      TreeSet values = (TreeSet)entry.getValue();

      result[i++] = new Relation(relation.name,
                                 relation.keys,
                                 (Attr[])values.toArray(new Attr[values.size()]));
    }
    return result;
  }

  private static TreeMap makeRelationMap(Relation[] relations) {
    TreeMap relationToValues = new TreeMap(RELATION_KEY_CMP);

    for (int i=0; i<relations.length; ++i)
      ((TreeSet)Maps.putOnce(relationToValues,
                             relations[i],
                             new TreeSet(ATTR_CMP))).addAll(Arrays.asList(relations[i].values));

    return relationToValues;
  }

  public static Relation[] sort(Relation[] relations) {
    for (int before=0; before<relations.length; ++before)
      for (int after=before+1; after<relations.length; ++after)
        if (ArraysExt.isSubSuperSet(relations[after].keys, relations[before].keys, ATTR_KEY_CMP)) {
          Relation tmp = relations[after];
          relations[after] = relations[before];
          relations[before] = tmp;
        }

    return relations;
  }

  public static final Comparator
    RELATION_KEY_CMP = new Comparator() {
        public int compare(Object o1, Object o2) {
          final Relation r1 = (Relation)o1;
          final Relation r2 = (Relation)o2;

          int result = r1.name.compareTo(r2.name);
          if (0 != result) return result;

          result = r1.keys.length - r2.keys.length;
          if (0 != result) return result;

          for (int i=0; i<r1.keys.length; ++i) {
            result = ATTR_CMP.compare(r1.keys[i], r2.keys[i]);
            if (0 != result) return result;
          }

          return result; }};
  public static final Comparator
    ATTR_CMP = new Comparator() {
        public int compare(Object o1, Object o2) {
          final Attr f1 = (Attr)o1;
          final Attr f2 = (Attr)o2;

          int result = f1.name.compareTo(f2.name);
          if (0 != result)
            return result;
          
          return f1.type.compareTo(f2.type); }};
  public static final Comparator
    ATTR_KEY_CMP = new Comparator() {
        public int compare(Object subSet, Object superSet) {
          return ((Attr)superSet).name.endsWith(((Attr)subSet).name) ? 0 : 1; }};
}
