netty线程模型

reactor单线程: 处理连接, 处理读写使用单个线程
reactor多线程: nio线程监听服务器, 处理连接; 读写操作由一个线程池处理, 它包含任务队列和Ｎ个可用的线程
主从reactor多线程模型, 处理连接和处理io都使用线程池

netty具体使用的时候由配置而定

EventLoop负责一个channel, 是处理事件的最小单元

NioEventLoop在初始化的时候就会执行一个run方法, run方法其实是执行一个for循环, for循环里面先处理io事件, 再处理持有的任务队列