/*
 * $Id: DataSetUtils.java,v 1.13.2.3 2006/01/21 02:52:33 david_hall Exp $
 *
 * Copyright 2005 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.jdesktop.databuffer;

/**
 *
 * @author rbair
 * @deprecated This class will be removed in mid-November, 2005, being replaced by {@link org.jdesktop.databuffer.util.DataSetUtility}
 */
public class DataSetUtils {
    /** Creates a new instance of DataSetUtils */
    private DataSetUtils() {
    }
    
    /**
     * Checks to see if the given name is valid. If not, then return false.<br>
     * A valid name is one that follows the Java naming rules for
     * indentifiers, <b>except</b> that Java reserved words can
     * be used, and the name may begin with a number.
     */
    static boolean isValidName(String name) {
        return name != null && name.trim().length() > 0 && !name.matches(".*[\\s]");
    }
}
