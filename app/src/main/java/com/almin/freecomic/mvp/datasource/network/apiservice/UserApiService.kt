package com.almin.freecomic.mvp.datasource.network.apiservice

import com.almin.freecomic.mvp.datasource.network.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Almin on 2018/6/19.
 */
interface UserApiService{

//      URL	https://user.dmzj.com/loginV2/m_confirm
    //  channel=ios&nickname=13660096015&passwd=168888mmiscc&version=2.5.1
//      {
//        "result": 1,
//        "msg": "OK",
//        "data": {
//            "uid": "102018822",
//            "nickname": "Crazy90554",
//            "dmzj_token": "97de3098ebda29311386940a372c34d3",
//            "photo": "http:\/\/images.dmzj.com\/user\/fe\/5e\/fe5e07796c3ca491983b1697d6b84356.png",
//            "bind_phone": "13660096015",
//            "email": "",
//            "passwd": "cbaa86fccd0c20f438b056c444b0f36e"
//            }
//        }
    @FormUrlEncoded
    @POST("/user_api_key/loginV2/m_confirm")
    fun login(@Field("nickname") username: String, @Field("passwd") password: String) : Observable<LoginResponse>


//     URL	https://v2api.dmzj.com/UCenter/comicsv2/102018822.json?channel=ios&version=2.5.1
    // uid.json

//    {
//        "nickname": "Crazy90554",
//        "description": "",
//        "birthday": "0000-00-00",
//        "sex": 2,
//        "cover": "https:\/\/avatar.dmzj.com\/fe\/5e\/fe5e07796c3ca491983b1697d6b84356.png",
//        "blood": 0,
//        "constellation": "",
//        "bind_phone": 13660096015,
//        "email": "",
//        "channel": "wechat",
//        "is_verify": 0,
//        "is_modify_name": 1,
//        "data": [],
//        "amount": 0,
//        "is_set_pwd": 1,
//        "bind": [{
//        "type": "tel",
//        "name": 13660096015
//    }, {
//        "type": "wechat",
//        "name": "Crazy90554"
//    }]
//    }

    @GET()
    fun loadUserCenterInfo() : Observable<String>

//    URL	https://v2api.dmzj.com/account/sendtelcode?tel=13660096015&type=1&channel=ios&version=2.5.1
//    {
//        "code": 0,
//        "msg": "\u53d1\u9001\u6210\u529f"
//    }
    @POST()
    fun sendForgetMess() : Observable<String>



//    URL	https://v2api.dmzj.com/account/setpwdbytel
//    channel=ios&dmzj_token=97de3098ebda29311386940a372c34d3&pwd=168888mmiscc&tel=13660096015&valid_code=986384&version=2.5.1

//    {
//        "code": 0,
//        "msg": "\u8bbe\u7f6e\u5bc6\u7801\u6210\u529f"
//    }

    @POST()
    fun resetPasswordByTel() : Observable<String>



//    URL	https://v2api.dmzj.com/account/modifyname
//    channel=ios&dmzj_token=97de3098ebda29311386940a372c34d3&nickname=Crazy&version=2.5.1
//    {
//        "code": 4,
//        "msg": "\u8be5\u7528\u6237\u540d\u5df2\u7ecf\u5b58\u5728"
//    }
    // 4已存在 2成功

    @POST()
    fun updateNickName(name: String) : Observable<String>
}