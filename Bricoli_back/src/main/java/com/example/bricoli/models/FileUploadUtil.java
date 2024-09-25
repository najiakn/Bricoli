package com.example.bricoli.models;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class FileUploadUtil {

    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    public static final String IMAGE_PATTERN = ".*\\.(jpg|jpeg|png|gif|bmp|tif|webp|svg|heic|heif|raw|cr2|nef|arw|dng|orf|rw2)$";
    public static final String VIDEO_PATTERN = ".*\\.(mp4|avi|mov|mkv|webm|flv|wmv|m4v|mpg|mpeg|3gp|3g2|mxf|roq|nsv|f4v|f4p|f4a|f4b)$";
    public static final String DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String UPLOAD_DIR = "upload";
    public static final String FILE_NAME_FORMAT = "%s.%s";

    public static boolean isAllowedExtension(final String fileName, final String pattern) {
        final Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        boolean matches = matcher.matches();
        System.out.println("Filename: " + fileName + ", Pattern: " + pattern + ", Matches: " + matches);
        return matches;
    }

    public static void assertAllowed(MultipartFile file, String pattern) {
        final long size = file.getSize();
        if (size > MAX_FILE_SIZE) {
            System.out.println("Max file size is 5MB");
        }

        final String fileName = file.getOriginalFilename();
        if (!isAllowedExtension(fileName, pattern)) {
            System.out.println("Only allowed file types are: " + pattern);
        }
    }

    public static String getFileName(final String name, final String extension) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final String date = dateFormat.format(System.currentTimeMillis());
        return String.format(FILE_NAME_FORMAT, name + "_" + date, extension);
    }




    public static String getFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IllegalArgumentException("Original filename is missing.");
        }
        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
        return baseName + "_" + System.currentTimeMillis() + "." + extension; // Add a timestamp to avoid name clashes
    }





}