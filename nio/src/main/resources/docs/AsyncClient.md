借用了netty的react线程机制, 当有消息返回时才触发一个回调

在这之上可以借用downlatch封装Promise, 实现await 和 get, 实际上是基于 wait, notify

asyncClient 是基于netty封装的, 选用了nio的io方式

使用了 nio, 基于byteBuf的缓存能够提高读写的效率, 

基于非阻塞的selector组件,实现单个线程管理多个连接

基于netty的Reactor线程模型,  

一般netty应用初始化时会指定两个线程池,BossGroup 和 wokerGroup. 前者处理连接, 后者处理io和task队列.

对于服务端而言, 可以分表设置初始线程的大小, 一般选用NioEventLoopGroup

#  NioEventLoop

一般选用NioEventLoopGroup的核心是 NioEventLoop数组, 它其实是和一个java thread一一绑定的



NioEventLoop在初始化的时候就会执行一个run方法, run方法其实是执行一个for循环, for循环里面先处理io事件, 再处理持有的任务队列

```
   protected void run() {
        while(true) {
            while(true) {
                try {
                   
                    default:
                        this.cancelledKeys = 0;
                        this.needsToSelectAgain = false;
                        int ioRatio = this.ioRatio;
                        if (ioRatio == 100) {
                            try {
                                this.processSelectedKeys();
                            } finally {
                                this.runAllTasks();
                            }
                        } else {
                        }
                    }
                } catch (Throwable var21) {
                    handleLoopException(var21);
                }

            }
        }
    }
```

Netty 中, 每个 Channel 都有且仅有一个 NioEventLoop 与之关联, NioEventLoop可以理解成nio中selector的封装, 同时加了一些其他功能;

可以把不同的channel都和同一个 NioEventLoop绑定

# nio的 http请求中发生了什么
http://www.voidcn.com/article/p-zbiwjueg-tr.html
对单线程池模型, 只有一个EventGroup

每当有新的连接产生, 生成一个nio channel, 然后从EventGroup选择一个 EventLoop和channel绑定, 如果该EventLoop没有启动过, 则第一次启动. 
启动后会为单个EventLoop新建一个线程, 行使selector的功能循环监听io事件和处理task.

如果是io, 会从channelPipeline的上游往下传递, 如果是task, 直接执行

# 使用netty的异步和事件驱动

异步事件驱动至少包含两个线程, 主线程, 任务线程, 结合wait和notity()

http://lingnanlu.github.io/2016/08/16/netty-asyc-callback

# 浅谈 AsyncClient

构造一DefaultAsyncHttpClient, 建造DefaultAsyncHttpClientConfig, 通过config建造 DefaultAsyncHttpClient

在构造方法中中通过构造 ChannelManager 构造一个netty的bootstrap对象, 里面绑定了NioEventLoopGroup线程池

在请求时, controller线程每新建一个请求, 生成一个nio channel 然后分配给一个EventLoop, EventLoop内部的线程会监听channel的事件, 等到有响应的时候执行对应
的回调, 通知controller线程

nio + 线程池


# 其他
io | nio |nio2
netty 
线程池
asyncClient

