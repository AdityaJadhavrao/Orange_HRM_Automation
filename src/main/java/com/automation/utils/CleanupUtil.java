package com.automation.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CleanupUtil {

    public static void clearArtifacts() {

       cleanFolder("Screenshots");
       cleanFolder("test-output");
       cleanFolder("Logs");  // --> Folder is getting deleted but its not recreating it.
    }

    private static void cleanFolder(String folderName)
    {
        File folder = new File(folderName);

        if(folder.exists())
        {
            try
            {
                FileUtils.cleanDirectory(folder);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}