package com.rodrigotroy.commons.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFile {
    private static final int BUFFER_SIZE = 2048;
    private final InputStream zipContentAsInputStream;

    public ZipFile(InputStream zipContentAsInputStream) {
        this.zipContentAsInputStream = zipContentAsInputStream;
    }

    public Map<ZipEntry, byte[]> getZipContent() throws
                                                 IOException {
        Map<ZipEntry, byte[]> zipEntryMap = new HashMap<>();

        int count;
        ZipEntry zipEntry;
        byte[] data = new byte[BUFFER_SIZE];
        ZipInputStream zipInputStream = new ZipInputStream(zipContentAsInputStream);

        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            if (!zipEntry.isDirectory()) {
                while ((count = zipInputStream.read(data,
                                                    0,
                                                    BUFFER_SIZE)) != -1) {
                    outputStream.write(data,
                                       0,
                                       count);
                }
            }

            outputStream.flush();
            outputStream.close();

            zipEntryMap.put(zipEntry,
                            outputStream.toByteArray());
        }

        zipInputStream.close();

        return zipEntryMap;
    }
}
