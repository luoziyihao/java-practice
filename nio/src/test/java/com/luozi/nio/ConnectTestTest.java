package com.luozi.nio;

import com.luozi.nio.tcp.PlainNioEchoServer;
import com.luozi.nio.tcp.TCPEchoClientNonblocking;
import org.junit.Test;

/**
 * @author xiang.rao created on 11/5/17 5:28 PM
 * @version $Id$
 */
public class ConnectTestTest {

    @Test
    public void server() throws Exception {
        PlainNioEchoServer server = new PlainNioEchoServer();
        server.serve(9003);
    }

    @Test
    public void client() throws Exception {
        TCPEchoClientNonblocking client = new TCPEchoClientNonblocking();
        client.connect(new String[] {
                "127.0.0.1",
                "9003",
                "9003"
        });
    }

}