package de.mlessmann.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static de.mlessmann.common.Common.copyStream;

/**
 * Created by Life4YourGames on 08.10.16.
 */
public class FileUtils {

    public static void copyFile(File f1, File f2) throws IOException {
        FileInputStream in = new FileInputStream(f1);
        FileOutputStream out = new FileOutputStream(f2);
        copyStream(in, out, null);
    }

    public static boolean deleteRecursive(File f) {
        if (!f.exists()) return true;
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files!=null && files.length>0) {
                for (File file : files) {
                    deleteRecursive(file);
                }
            }
            return f.delete();
        } else {
            return f.delete();
        }
    }

}
