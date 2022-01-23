package com.rodrigotroy.commons.util;

public class MimeTypeHandler {
    private MimeTypeHandler() {
        throw new IllegalStateException("Utility class");
    }

    public static String getMime(String ext) {
        if (ext.trim().indexOf("pdf") > 0) {
            return "application/pdf";
        } else if (ext.trim().indexOf("xls") > 0) {
            return "application/excel";
        } else if (ext.trim().indexOf("doc") > 0) {
            return "application/msword";
        } else if (ext.trim().indexOf("gif") > 0) {
            return "image/gif";
        } else if (ext.trim().indexOf("xml") > 0) {
            return "application/xml";
        } else if (ext.trim().indexOf("jpg") > 0) {
            return "image/jpeg";
        } else if (ext.trim().indexOf("jpeg") > 0) {
            return "image/jpeg";
        } else if (ext.trim().indexOf("png") > 0) {
            return "image/png";
        } else if (ext.trim().indexOf("ppt") > 0) {
            return "application/powerpoint";
        } else {
            return "text/plain";
        }
    }
}
