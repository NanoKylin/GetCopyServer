# GetCopyServer Protocol V1.0

### 0x00 Tips

此协议为临时协议 应标记为**不安全**

正确协议为：https://github.com/NanoKylin/Fast-WebSocket-Packet-Protocol

版本稳定后将换用 FastWebSocketPacketProtocol  因为它将更完整更安全



### 0x01 Packet

| 数据包头      | 功能序号    | 数据块        | 数据包尾    |
| ------------- | ----------- | ------------- | ----------- |
| GCSS (String) | 00 (String) | DATA (String) | EE (String) |

**如表所示 所有的内容将会转换为String传输**



### 0x02 Login

| 数据包头 | 功能序号 | 数据块 | 数据包尾 | 描述                     |
| -------- | -------- | ------ | -------- | ------------------------ |
| GCSC     | 01       | JSON   | EE       | 向服务端发出一个登陆请求 |
| GCSS     | 01       | JSON   | EE       | 向客户端返回一个登陆状态 |



**GCSC 01** 

| 字段     | 内容   | 描述   |
| -------- | ------ | ------ |
| username | String | 用户名 |
| password | String | 密码   |

**GCSS 01**

| 字段    | 内容   | 描述     |
| ------- | ------ | -------- |
| context | String | 登陆结果 |





### **0x03 Content**

| 数据包头 | 功能序号 | 数据块 | 数据包尾 | 描述                                    |
| -------- | -------- | ------ | -------- | --------------------------------------- |
| GCSC     | 02       | JSON   | EE       | 向服务端发出新的内容                    |
| GCSS     | 02       | JSON   | EE       | 向客户端返回内容                        |
| GCSC     | 03       | JSON   | EE       | 获取全部内容 **此数据包回复为02数据包** |

**GCSC 02 / GCSS 02 ** 

| 字段     | 内容          | 描述          |
| -------- | ------------- | ------------- |
| username | String        | 用户名        |
| context  | String        | 内容          |
| picture  | String Base64 | (如果有) 图片 |

**GCSC 03**

| 字段     | 内容   | 描述   |
| -------- | ------ | ------ |
| username | String | 用户名 |



### 0x04 Login Out

| 数据包头 | 功能序号 | 数据块 | 数据包尾 | 描述                     |
| -------- | -------- | ------ | -------- | ------------------------ |
| GCSC     | 04       | JSON   | EE       | 向服务端发出一个登出请求 |

**GCSC 04**

| 字段     | 内容   | 描述   |
| -------- | ------ | ------ |
| username | String | 用户名 |



### 0x05 Again

在换用FastWebSocketPacketProtocol之前 此软件十分不安全

**请勿用于生产环境**