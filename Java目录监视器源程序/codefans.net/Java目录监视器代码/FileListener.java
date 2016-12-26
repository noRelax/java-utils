package com.lightspeedleader.directorywatcher;

import java.io.File;

public class FileListener extends BaseListener implements IFileListener {


    /**
     * Connstructor
     */
    public FileListener() {
        super();
    }

    public void onStart(Object monitoredResource) {
        // On startup
        if (monitoredResource instanceof File) {
            File resource = (File) monitoredResource;
            if (resource.isDirectory()) {

                System.out.println("Start to monitor " + resource.getAbsolutePath());

                /*File[] files = resource.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File f = (File) files[i];
                    onAdd(f);
                }*/
            }
        }
    }

    public void onStop(Object notMonitoredResource) {

    }

    public void onAdd(Object newResource) {
        if (newResource instanceof File) {
            File file = (File) newResource;
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath() + " is added");
            }
        }
    }

    public void onChange(Object changedResource) {
        if (changedResource instanceof File) {
            File file = (File) changedResource;
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath() + " is changed");
            }

        }
    }

    public void onDelete(Object deletedResource) {
        if (deletedResource instanceof String) {
            String deletedFile = (String) deletedResource;
            System.out.println(deletedFile + " is deleted");
        }
    }
}

