/*
 * $Id: SaveTask.java,v 1.6 2005/11/16 16:12:08 pdoubleya Exp $
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

package org.jdesktop.databuffer.provider;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;

import org.jdesktop.databuffer.DataTable;

/**
 *
 * @author rbair
 */
public abstract class SaveTask extends AbstractTask {
    /** The Logger */
    private static final Logger LOG = Logger.getLogger(SaveTask.class.getName());
    
    private DataTable[] tables;

    public SaveTask(DataTable[] tables) {
        this.tables = tables == null ? new DataTable[0] : tables;
    }

    public void run() {
        setIndeterminate(true);
        try {
            saveData(tables);
            setProgress(getMaximum());
        } catch (Exception e) {
            final Throwable error = e;
            LOG.log(Level.WARNING, "Failed to save data from tables {0}", Arrays.asList(tables));
	    LOG.log(Level.WARNING, e.getMessage(), e);
            setProgress(getMaximum());
        }
    }

    protected abstract void saveData(DataTable[] tables) throws Exception;

    /**
     * @inheritDoc
     */
    // TODO: this is not a logging message, it's for the user--so should be in a ResourceBundle.
    public String getDescription() {
        return "<html><h3>Saving data</h3></html>";
    }

    /**
     * @inheritDoc
     */
    public Icon getIcon() {
        return null;
    }

    /**
     * @inheritDoc
     */
    // TODO: this is not a logging message, it's for the user--so should be in a ResourceBundle.
    public String getMessage() {
        return "Saving item " + (getProgress() + 1) + " of " + getMaximum();
    }

    /**
     * @inheritDoc
     */
    public boolean cancel() throws Exception {
        return false;
    }
}