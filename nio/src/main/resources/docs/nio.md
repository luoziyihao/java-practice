# io的局限与nio的改进

当InputStream读取输入流中的数据时, 如果没有读到有效的数据, 程序将在此处阻塞该线程的执行?

同时传统的输入输出流一次只能处理一个字节, 导致效率低下

nio使用内存映射文件的方式来处理输入输出, nio直接把文件的一段区域映射到内存中, 这样就可以通过访问内存的方式来访问文件了, 类似于虚拟内存

传统的io是面向流的处理, nio是面向块的处理

# nio核心类

## buffer 数据块

相比较io引入的间接层, 支持文件块的批量读

ByteBuffer  与  DirectByteBuf

## channel

类似于传统的流对象, 但是支持把文件批量映射到byteBuffer中

## Selector 支持非阻塞的输入输出

register一个非阻塞的channel, 一般是网络套接字,  FileChannel是阻塞的

# 相关

1.4
http://ifeve.com/java-nio-channel-to-channel/