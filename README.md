# 说明文档

## mysql 表
```
CREATE TABLE `credit_limit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` int NOT NULL,
  `amount` double NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastmodified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx__userId_type` (`user_id`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

## 接口文档

---

#### 查询用户额度
**api**：GET#/credit-limit/user-limit  
**参数**（Query Params）：
  - userId： 用户id   
  - type 额度类型

**结果**
```
{
    "c": 0,
    "m": "",
    "d": 100
}
```
----

#### 初始化用户额度
**api**：POST#/credit-limit/user-limit/init  
**参数**（Body）：
```
{
    "userId" : 1,
    "type" : 3
}
```
**结果**
```
{
    "c": 0,
    "m": "",
    "d": null
}
```

---

#### 新增、扣减用户额度
**api**：PUT#/credit-limit/user-limit/update
**参数**（Query Params）：
```
{
    "userId" : 1,
    "type" : 1,
    # 新增或扣减的额度
    "incr" : 100 
}
```
**结果**
```
{
    "c": 0,
    "m": "",
    "d": null
}
```
    