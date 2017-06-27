package com.luozi.bigdata;

import org.junit.Test;

/**
 * Created by luoziyihao on 6/27/17.
 */
public class BigFileReaderTest {

    BigFileWriter bigFileReader = new BigFileWriter();

    @Test
    public void makeBigFile() throws Exception {
        bigFileReader.makeBigFile();
    }

}