package com.yuehai.data.bean

/**
 * Created by zhaoyuehai 2018/8/2
 */
data class UserBean(val access_token: String,
                    val token_type: String,
                    val refresh_token: String,
                    val expires_in: Int,
                    val scope: String,
                    val sub: String,
                    val userEnterpriseStatus: String,
                    val auto: String,
                    val userStatus: Int,
                    val userFullName: String,
                    val enterpriseId: String,
                    val right: String,
                    val enterpriseName: String,
                    val username: String,
                    val jti: String
)

//    {
//        "code": "10000",
//            "message": "登陆成功",
//            "data": {
//        "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ6aGFuZ3NhbiIsInVzZXJFbnRlcnByaXNlU3RhdHVzIjpudWxsLCJhdXRvIjoiMCIsInVzZXJTdGF0dXMiOjEsInVzZXJfbmFtZSI6InpoYW5nc2FuIiwidXNlckZ1bGxOYW1lIjpudWxsLCJyaWdodCI6bnVsbCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwiY2xpZW50X2lkIjoiY25lZ3JvdXAiLCJzY29wZSI6WyJhbGwiLCJyZWFkIiwid3JpdGUiXSwiZW50ZXJwcmlzZUlkIjpudWxsLCJleHAiOjE1MzI5NjA5OTcsImVudGVycHJpc2VOYW1lIjpudWxsLCJqdGkiOiI1YWI4ZmI1Ni1hZjc4LTQ0ZDItYWM3NC1kNzMzYzE0ZDg0ODAiLCJ1c2VybmFtZSI6InpoYW5nc2FuIn0.oHCRviwx2QYZINiRFlNlBCBZWWgllZZRcFiT-s8MDkk",
//                "token_type": "bearer",
//                "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ6aGFuZ3NhbiIsInVzZXJFbnRlcnByaXNlU3RhdHVzIjpudWxsLCJhdXRvIjoiMCIsInVzZXJTdGF0dXMiOjEsInVzZXJfbmFtZSI6InpoYW5nc2FuIiwidXNlckZ1bGxOYW1lIjpudWxsLCJyaWdodCI6bnVsbCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwiY2xpZW50X2lkIjoiY25lZ3JvdXAiLCJzY29wZSI6WyJhbGwiLCJyZWFkIiwid3JpdGUiXSwiYXRpIjoiNWFiOGZiNTYtYWY3OC00NGQyLWFjNzQtZDczM2MxNGQ4NDgwIiwiZW50ZXJwcmlzZUlkIjpudWxsLCJleHAiOjE1MzM1MzY5OTcsImVudGVycHJpc2VOYW1lIjpudWxsLCJqdGkiOiJlMTdhOGFkZC1hYWIzLTQ3OWItOGU2Mi01NTM2ZThhZmQzZGMiLCJ1c2VybmFtZSI6InpoYW5nc2FuIn0.hJybFW6E0w_xNwvwC9bOiwIVqD5sutKbeJ4VFJQ2RMM",
//                "expires_in": 28799,
//                "scope": "all read write",
//                "sub": "zhangsan",
//                "userEnterpriseStatus": null,
//                "auto": "0",
//                "userStatus": 1,
//                "userFullName": null,
//                "enterpriseId": null,
//                "right": null,
//                "enterpriseName": null,
//                "username": "zhangsan",
//                "jti": "5ab8fb56-af78-44d2-ac74-d733c14d8480"
//    },
//        "serviceCode": null
//    }