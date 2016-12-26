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

package metricsanalyzer.util.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.EventListener;
import javax.swing.JComponent;
import javax.swing.border.Border;
import metricsanalyzer.util.fun.ObjectToObject;

/**
 * Helper for building hardcoded components.
 */
public class Comp {
  public Comp(Component component,
              EventListener listener) {
    this.component = component;
    this.elements = new Object[]{listener};
  }
  // TBD: Consider not handling constraints as a special case.
  public Comp(Component component,
              Object constraints) {
    this.component = component;
    this.constraints = constraints;
  }
  public Comp(Component component,
              Object[] elements) {
    this.component = component;
    this.elements = elements;
  }

  public static JComponent limitMaxSizeByPrefSize(JComponent jc) {
    return limitMaxSizeByPrefSize(jc, true, true);
  }

  public static JComponent limitMaxSizeByPrefSize(JComponent jc, boolean onX, boolean onY) {
    Dimension max = jc.getMaximumSize();
    Dimension pref = jc.getPreferredSize();

    jc.setMaximumSize(new Dimension((int)(onX ? pref.getWidth() : max.getWidth()),
                                    (int)(onY ? pref.getHeight() : max.getHeight())));

    return jc;
  }
  
  public Component build() throws Exception {
    return build(component);
  }

  public Component build(Component root) throws Exception {
    for (int i=0; i<elements.length; ++i) {
      Object e = elements[i];

      if (e instanceof Comp) {
        Comp childComp = (Comp)e;
        childComp.build(root);

        if (null != childComp.constraints)
          ((Container)component).add(childComp.component, childComp.constraints);
        else
          ((Container)component).add(childComp.component);
      }
      else if (e instanceof Component)
        ((Container)component).add((Component)e);
      // Must handle FailableActionListener separately from other Listeners.
      else if (e instanceof FailableActionListener)
        add(component, new DefaultFailableAction(root, (FailableActionListener)e));
      else if (e instanceof EventListener)
        add(component, (EventListener)e);
      else if (e instanceof Border)
        ((JComponent)component).setBorder((Border)e);
      else if (e instanceof String)
        component.setName((String)e);
      else if (e instanceof ObjectToObject)
        component = (Component)((ObjectToObject)e).with(component);
      else
        throw new RuntimeException("Unrecognized element: " + e);
    }
    return component;
  }

  private static void add(Component c,
                          EventListener el) throws Exception {
    int cnt = 0;

    for (Class cl = el.getClass(); null != cl; cl = cl.getSuperclass())
      cnt += addSubInterfaces(c, el, cl);

    if (0 == cnt)
      throw new RuntimeException("Failed to add listener!");
  }

  private static int addSubInterfaces(Component c,
                                      EventListener el,
                                      Class cl) throws Exception {
    int cnt = 0;

    Class[] interfaces = cl.getInterfaces();
    for (int i=0; i<interfaces.length; ++i) {
      Class ic = interfaces[i];
      
      if (ic.getName().endsWith("Listener") &&
          !ic.getName().endsWith(".EventListener")) {
        cnt += 1;
        Method method = c.getClass().getMethod(addMethodName(ic.getName()), new Class[]{ic});
        method.invoke(c, new Object[]{el});
      } else {
        cnt += addSubInterfaces(c, el, ic);
      }
    }
    return cnt;
  }

  private static String addMethodName(String listenerTypeName) {
    return "add" + listenerTypeName.substring(listenerTypeName.lastIndexOf('.') + 1);
  }

  private Component component = null;
  private Object constraints = null;
  private Object[] elements = new Object[]{};
}
