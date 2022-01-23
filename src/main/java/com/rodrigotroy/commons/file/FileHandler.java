package com.rodrigotroy.commons.file;

import java.io.*;

/**
 * CLASE DE AYUDA PARA MANIPULACION DE ARCHIVOS.
 */
public class FileHandler {

    private FileHandler() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * COPIA UN ARCHIVO DESDE UNA RUTA ORIGEN A OTRA RUTA DESTINO.
     *
     * @param inFile  STRING
     * @param outFile STRING
     * @throws IOException
     */
    public synchronized static void copy(String inFile,
                                         String outFile) throws
                                                         IOException {
        File fileIN = new File(inFile);
        File fileOUT = new File(outFile);

        FileHandler.copy(fileIN,
                         fileOUT);
    }

    /**
     * COPIA UN ARCHIVO DESDE UNA RUTA ORIGEN A OTRA RUTA DESTINO.
     *
     * @param inFile  java.io.File
     * @param outFile java.io.File
     * @throws IOException
     */
    public synchronized static void copy(File inFile,
                                         File outFile) throws
                                                       IOException {

        if (!inFile.exists()) {
            return;
        }

        if (inFile.getCanonicalPath().equals(outFile.getCanonicalPath())) {
            // inFile and outFile are the same;
            // hence no copying is required.
            return;
        }

        InputStream in = null;
        OutputStream out = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(inFile);
            in = new BufferedInputStream(fis);
            fos = new FileOutputStream(outFile);
            out = new BufferedOutputStream(fos);

            for (int c = in.read(); c != -1; c = in.read()) {
                out.write(c);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (in != null) {
                in.close();
            }

            if (fos != null) {
                fos.close();
            }

            if (out != null) {
                out.close();
            }
        }
    }
}
