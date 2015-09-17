package com.vali.util;

import java.io.File;

/**
 * Created by vali on 15-9-17.
 */
public class FileUtil {

    public static void createFolder(String path) {

        File file = new File(path);

        if (file.exists()) {
            return;
        }

        file.mkdir();
    }

}
