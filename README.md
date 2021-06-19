# yeju

椰居项目是个人的毕业设计作品的后端程序。它采用了微服务架构和前后端分离设计，并参考了Restful接口设计规范。

## yeju rest web api （gateway）

http://127.0.0.1:81/swagger-ui/index.html

-----------------------------------
椰居后端代码库

## 更新配置

拉取代码后，需要刷SQL脚本并更新Nacos服务注册中心、配置中心的IP和Port。 当然，刷完SQL脚本后，需要在Nacos服务配置当中更新Mysql、Redis等数据库、中间件的地址端口(文档会继续更新具体的配置步骤...)

## 打包步骤

## 服务端口规划(http、dubbo)

### 微服务关端口 (http)

yeju-gateway ---- > 81

### 服务提供方端口规划(dubbo)

#### 单节点部署

yeju-all-provider-----20882

#### 日志服务提供方

yeju-log-service-provider ----> 20883

#### 对象存储服务提供方

yeju-oos-service-provider ----> 20884

#### 认证服务提供方

yeju-auth-service-provider ----> 20885

#### 平台服务提供方

yeju-platform-service-provider ----> 20886

#### 客户服务提供方

yeju-customer-service-provider ----> 20887

#### 产品服务提供方

yeju-product-service-provider ----> 20888

#### 消息服务提供方

yeju-message-service-provider -----20889

#### 定时任务

yeju-job-provider ------------------>20890

#### 支付服务提供方

yeju-payment-provider---------------20891

#### 交易服务提供方

yeju-trade-provider-----------------20892

#### 邮件服务提供者

yeju-mail-provider------------------20893

### 服务消费方端口规划(http)

#### minio对象存储端口

yeju-oos ---- > 9000

#### 认证服务

yeju-auth-api ----> 9002

#### all-api

yeju-all-rest-api ----> 9010

#### 消息通知微服务

yeju-notice -----------> 9003

#### 产商品服务 web ------> 9005

#### 客户服务 web --------> 9006

#### 交易服务 web --------> 9007

#### 支付服务 web --------->9008


