# io是什么

java把不同的输入输出源(键盘, 文件, 网络链接)抽象为流, 通过流的方式允许java程序访问不同的输入, 输出源

io, 即InputStream, OutputStream, 输入输出流. 输入输出流是针对于内存说的

# 字节流与字符流

InputStream/OutputStream Byte

Reader/Writer   Char = 2Byte

# api

## 字节流 

```
int read()
int read(byte[] b)
int read(byte[] b, int off, int size)
```

## 字符流替换成 Char

字节流和字符流操纵的不是内存中的资源, 所以虚拟机无法释放, 需要手动释放

# 访问文件的节点流

FileReader
FileInputStream
FileWriter
FileOutputStream

`case`

# 处理流

封装节点流

不同的流及其case <<疯狂java讲义>> p706

## special 

缓冲流, 可以减少实际读写次数?, 提高效率, 但是需要手动flush 

转化流,字符流到字符流

推回输入流?

子进程输入输出 ?

输入流读取输出流 ?

RandomAccessFile 随机读写

## 对象序列化
基本类型, String, 实现Serilizable的引用类型; 

transient忽略; 

序列化钩子; 

自定义序列化;

serialVersionUID的必要性
 


 