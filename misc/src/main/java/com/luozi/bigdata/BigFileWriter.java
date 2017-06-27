package com.luozi.bigdata;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by luoziyihao on 6/27/17.
 */
@Slf4j
public class BigFileWriter {
    private static final String DES_FILE = "/data/test/bigfile/dest1";

    public void makeBigFile() {
        File file = ensureFile(DES_FILE);
        writeBigData(file);
    }

    private static final int M_OF_ONE_G = 1024;
    private static final int BYTE_OF_ONE_M = 1024 * 1024;

    private void writeBigData(File file) {
        try {
            try (
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    PrintWriter printWriter = new PrintWriter(fileOutputStream, true)
            ) {
                long i = 0;
                while (dums(file) < M_OF_ONE_G) {
                    i++;
                    long freeMemory =  Runtime.getRuntime().freeMemory() / BYTE_OF_ONE_M;
                    long totalMemory = Runtime.getRuntime().totalMemory() / BYTE_OF_ONE_M;
                    String newLine = String.format("i am line %s. haha, time=%s, freeMemory=%sM, totalMemory=%sM\n"
                            , i
                            , new Date()
                            , freeMemory
                            , totalMemory
                    );
                    checkCapacity(totalMemory, 100);
                    printWriter.write(newLine);
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }


    }

    private void checkCapacity(long totalMemory, long destCapacity) {
        if (totalMemory > destCapacity) {
            log.warn("totalMemory is bigger then {}", destCapacity);
        }
    }

    private int dums(File file) {
        return Long.valueOf(file.length() / BYTE_OF_ONE_M).intValue();
    }

    private File ensureFile(String desFile) {
        File file = new File(desFile);
        if (!file.exists()) {
            try {
                boolean success = file.createNewFile();
                if (success) {
                    return file;
                }
                throw new IllegalStateException("create file failed");
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return file;
    }


    public void readByLine(){

    }
}
