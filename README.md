# levyx-mongo-service
操作MongoDB，提供hessian接口


# hessian和spring集成，提供hessian服务需要注意的地方：
    1.传输数据用到的自定义model必须实现序列化接口，包含类也必须实现
    2.client和server传输的类包要一致，本例子中提供了公共的model工程供调用
    3.在applicationContext.xml配置
    4.在调试的过程中，接口一直调不通，所以配置了controller测试本工程自身是否可以正常调用，结果发现也调不通
        后来发现自己犯了个愚蠢的错误（没有配置web.xml...）

# 本例子中service服务提供的是对MongoDB的调用，包括基本的保存，单个获取，分页模糊查询等方法
