package com.clouddeploy.util;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {

    public static void unzip(String zipFilePath, String destDirectory)
            throws IOException {

        Path destPath = Paths.get(destDirectory);

        if (!Files.exists(destPath)) {
            Files.createDirectories(destPath);
        }

        try (ZipInputStream zipInputStream =
                     new ZipInputStream(
                             new FileInputStream(zipFilePath))) {

            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {

                Path entryPath =
                        destPath.resolve(entry.getName()).normalize();

                // ZIP Slip protection
                if (!entryPath.startsWith(destPath)) {
                    throw new IOException(
                            "Invalid ZIP entry: " + entry.getName()
                    );
                }

                if (entry.isDirectory()) {

                    Files.createDirectories(entryPath);

                } else {

                    Files.createDirectories(entryPath.getParent());

                    Files.copy(
                            zipInputStream,
                            entryPath,
                            StandardCopyOption.REPLACE_EXISTING
                    );
                }

                zipInputStream.closeEntry();
            }
        }
    }
}