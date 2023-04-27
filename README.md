
# 一：关于结构

我根据执行时机将入口分为了四部分：sync、async、queue、job
+ sync：同步任务，也就是正常的接口
+ async：异步任务，用以执行轻量且不紧要的的任务
+ queue：消息队列，用以执行耗时且不紧要的的任务
+ job：定时任务

# 二：技术栈

很明显，SpringBoot，这也标志着项目的返璞归真。

我会专注于业务、性能和可维护性。