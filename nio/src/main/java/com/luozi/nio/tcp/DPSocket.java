package com.luozi.nio.tcp;

import java.util.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class DPSocket {
    private boolean debug;
    private String host;
    private int port;
    private String charset;
    private ByteArrayOutputStream inBuffer;
    private ByteBuffer buf;
    private Selector selector;
    private SocketChannel channel;

    public DPSocket(String host, int port, String charset) {
        this.charset = charset==null || charset.equals("") ? "UTF-8" : charset;
        this.host = host;
        this.port = port;
    }

    public boolean isDebug() { return debug; }
    public void setDebug(boolean b) { debug=b; }

    public void open(long timeout) throws IOException {
        selector = Selector.open();
        channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress(host, port));
        inBuffer = new ByteArrayOutputStream(1024);
        buf = ByteBuffer.allocate(1*1024);
        long sleep = Math.min(timeout, 1000);
        while(timeout > 0) {
            if (selector.select(sleep) < 1) {
                timeout-=sleep;
                continue;
            }
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while(keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (!key.isValid() || !key.isConnectable()) continue;
                SocketChannel channel = (SocketChannel)key.channel();
                if (channel.isConnectionPending()) {
                    channel.finishConnect();
                    channel.configureBlocking(false);
                    if (debug) System.out.println("finishConnect");
                    return; // we are ready to receive bytes
                }
            }
        }
        throw new IOException("Connection timed out");
    }

    public void close() {
        try { if(channel!=null) channel.close(); } catch(Exception ex) { }
        try { if(selector!=null) selector.close(); } catch(Exception ex) { }
        inBuffer=null;
        buf=null;
    }   

    public void write(String data, long timeout) throws IOException {
        write(data.getBytes(charset), timeout);
    }

    public void write(byte[] bytes, long timeout) throws IOException {
        ByteBuffer outBuffer = ByteBuffer.wrap(bytes);
        channel.register(selector, SelectionKey.OP_WRITE);
        long sleep = Math.min(timeout, 1000);
        while(timeout > 0) {
            if (selector.select(sleep) < 1) {
                timeout-=sleep;
                continue;
            }
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while(keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (!key.isValid() || !key.isWritable()) continue;
                SocketChannel channel = (SocketChannel)key.channel();
                if (debug) System.out.println("write remaining="+outBuffer.remaining());
                channel.write(outBuffer);
                if (debug) System.out.println("write remaining="+outBuffer.remaining());
                if (outBuffer.remaining()<1)
                    return;
            }
        }
        throw new IOException("Write timed out");
    }

    public List<String> readUntil(String terminator, long timeout, boolean trimLines) throws IOException {
        return readUntil(new String[]{terminator}, timeout, trimLines);
    }

    public List<String> readUntil(String[] terminators, long timeout, boolean trimLines) throws IOException {
        List<String> lines = new ArrayList<String>(12);
        inBuffer.reset();

        // End of packet terminator strings, line startsWith "aabbcc" string.
        byte[][] arrTerminators = new byte[terminators.length][];
        int[] idxTerminators = new int[terminators.length];
        for(int idx=0; idx < terminators.length; idx++) {
            arrTerminators[idx] = terminators[idx].getBytes(charset);
            idxTerminators[idx] = 0;
        }
        int idxLineByte=-1;

        channel.register(selector, SelectionKey.OP_READ);
        long sleep = Math.min(timeout, 1000);
        while(timeout>0) {
            if (selector.select(sleep) < 1) {
                timeout-=sleep;
                continue;
            }
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while(keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (!key.isValid() || !key.isReadable()) continue;
                SocketChannel channel = (SocketChannel)key.channel();
                buf.clear();
                int len = channel.read(buf);
                if (len == -1) throw new IOException("Socket disconnected");
                buf.flip();
                for(int idx=0; idx<len; idx++) {
                    byte cb = buf.get(idx);
                    if (cb!='\n') {
                        idxLineByte++;
                        inBuffer.write(cb);
                        for(int idxter=0; idxter < arrTerminators.length; idxter++) {
                            byte[] arrTerminator = arrTerminators[idxter];
                            if (idxLineByte==idxTerminators[idxter]
                                    && arrTerminator[ idxTerminators[idxter] ]==cb) {
                                idxTerminators[idxter]++;
                                if (idxTerminators[idxter]==arrTerminator.length)
                                    return lines;
                            } else idxTerminators[idxter]=0;
                        }
                    } else  {
                        String line = inBuffer.toString(charset);
                        lines.add(trimLines ? line.trim() : line);
                        inBuffer.reset();
                        idxLineByte=-1;
                        for(int idxter=0; idxter<arrTerminators.length; idxter++)
                            idxTerminators[idxter]=0;
                    }
                }
            }
        }
        throw new IOException("Read timed out");
    }

    // **************************
    // *** test socket client ***
    // **************************

    public static void main(String[] args) throws Exception {
        String NEWLINE = "\n";
        int TIMEOUT=5000;

        DPSocket dps = new DPSocket("myserver.com", 1234, "UTF-8");
        dps.setDebug(true);

        try {
            List<String> lines;
            dps.open(15000);

            dps.write("Command1 arg1 arg2"+NEWLINE, TIMEOUT);
            lines = dps.readUntil(">>", TIMEOUT, true);

            dps.write("Command2 arg1 arg2"+NEWLINE, TIMEOUT);
            lines = dps.readUntil(">>", TIMEOUT, true);
        } catch (Exception ex) {
            String msg = ex.getMessage();
            if (msg==null) msg = ex.getClass().getName();
            if (msg.contains("timed out") || msg.contains("Invalid command ")) {
                System.out.println("ERROR: " + ex.getMessage());
            } else {
                System.out.print("ERROR: ");
                ex.printStackTrace();
            }
        } finally {
            dps.close();
        }
    }