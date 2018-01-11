# Hotchpotch
大杂烩，工作中经常用到的工具等等

## common
目前期中放的是参数校验工具。支持接口上直接打上注解来处理，支持很多种类的判断（判断空、手机号、邮箱校验等等）

## log
包括异步日志处理和dubbo的AccessLogRotateFilter。

## redis
包含了堆jedis的相关封装

## http
封装了一些基本的http发送接收方法

## date
处理日期的util，java的date绝对是败笔。

## secrets
加密算法，用于生成八位随机字符，使用凯撒加密算法
另外还有加密手机号使用的Util，能够使用相同的手机号加密出不同的密文，不同的密文反解出相同的手机号。

## image
剪切图片使用的工具类

## 统一配置
用于统一配置spring文件

## rocket-mq
用于处理rocketmq消息堆积

## exception
用于从异常堆栈中，找出特定的包名前缀的异常，过滤切面、spring框架等的堆栈信息

## tcp
tcp/ip学习笔记

## 排序
一些排序算法的基本思想和部分代码(java)

## check-nginx
检查nginx状态的脚本

## batch
批次任务执行工具。应用场景为：单台JVM中，需要多线程处理List数据（只能是List，因为List有分片方法）

## class
处理class的工具类

## executor
当cores线程数全都使用的情况下，默认线程池会把任务放入到队列中。队列满则再创建线程（总数不会超过Max线程数）
增强线程池：比fixed更好的应对请求量大的情况
特性：cores线程全部使用的情况下，优先创建线程（总数不会超过max），当max个线程全都在忙的情况下，才将任务放入队列。请求量下降时，线程池会自动维持cores个线程，多余的线程退出。