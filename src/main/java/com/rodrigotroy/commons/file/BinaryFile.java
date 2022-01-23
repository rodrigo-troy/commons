package com.rodrigotroy.commons.file;

import com.rodrigotroy.commons.util.Validator;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class BinaryFile {
    private final byte[] fileContent;
    private final String fileName;
    private String fileExtension;

    public BinaryFile(InputStream inputStream,
                      String fileName) throws
                                       Exception {
        if (Validator.validateString(fileName)) {
            this.fileName = fileName;

            if (fileName.contains(".")) {
                this.fileExtension = fileName.substring(fileName.lastIndexOf("."));
            }
        } else {
            throw new Exception("EL NOMBRE DEL ARCHIVO ES INVALIDO");
        }

        if (inputStream != null) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = inputStream.read(data,
                                             0,
                                             data.length)) != -1) {
                buffer.write(data,
                             0,
                             nRead);
            }

            buffer.flush();

            this.fileContent = buffer.toByteArray();
        } else {
            throw new Exception("EL ARCHIVO ES INVALIDO");
        }
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
