package de.mlessmann.common.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Life4YourGames on 11.09.16.
 */
public class Unzip {

    private List<String> files;
    private String fromFile;
    private String toDir;

    public Unzip(String fromFile, String toDir) {
        this.fromFile = fromFile;
        this.toDir = toDir;
    }

    public boolean unzip() throws IOException {

        byte[] buffer = new byte[1024];

        File dir = new File(toDir);
        if (!dir.isDirectory())
            dir.mkdirs();

        File file = new File(fromFile);
        if (!file.isFile())
            return false;

        ZipInputStream ziStrm = new ZipInputStream(new FileInputStream(file));

        ZipEntry e = ziStrm.getNextEntry();

        while (e != null) {
            String name = e.getName();
            File fil = new File(dir, name);

            if (!e.isDirectory()) {
                FileOutputStream outStrm = new FileOutputStream(fil);

                int len;
                while ((len = ziStrm.read(buffer)) > 0)
                    outStrm.write(buffer, 0, len);
                outStrm.close();
            } else {
                fil.mkdirs();
            }
            ziStrm.closeEntry();
            e = ziStrm.getNextEntry();
        }
        ziStrm.close();
        return true;
    }

}
