package com.almin.freecomic.module.common.datasource.apiservice

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Almin on 2018/6/19.
 */
interface IOSComicApiService{

//    URL	https://v2api.dmzj.com/UCenter/subscribe?type=0&letter=all&sub_type=1&page=0&uid=102018822&channel=ios&version=2.5.1
//    type=0&letter=all&sub_type=1&page=0&uid=102018822&channel=ios&version=2.5.1
//    sub_type: 1 全部  2 未读 3 已读 4 完结

//    [{
//        "name": "Last Geass",
//        "sub_update": "\u7b2c03\u8bdd",
//        "sub_img": "https:\/\/images.dmzj.com\/webpic\/2\/lg0406l.jpg",
//        "sub_uptime": 1529299474,
//        "sub_first_letter": "l",
//        "sub_readed": 1,
//        "id": 43732,
//        "status": "\u8fde\u8f7d\u4e2d"
//    }, {
//        "name": "\u8db3\u7403\u9a91\u58eb",
//        "sub_update": "\u7b2c418\u8bdd",
//        "sub_img": "https:\/\/images.dmzj.com\/webpic\/8\/zhuqiuqishil.jpg",
//        "sub_uptime": 1492249312,
//        "sub_first_letter": "z",
//        "sub_readed": 1,
//        "id": 3410,
//        "status": "\u8fde\u8f7d\u4e2d"
//    }]
    @GET
    fun getPersonalSubscription() : Observable<String>


//    URL	https://v2api.dmzj.com/subscribe/add
//    channel=ios&obj_ids=40431&type=mh&uid=102018822&version=2.5.1
//    {
//        "code": 0,
//        "msg": "OK"
//    }

    @POST()
    fun subcribeComic() : Observable<String>


//    URL	https://v2api.dmzj.com/subscribe/cancel?type=mh&uid=102018822&obj_ids=40431&channel=ios&version=2.5.1
    // 暂时服务器有问题，请求不成功
    @GET
    fun unSubcribeComic() : Observable<String>


//    URL	https://v2api.dmzj.com/comic/40431.json?channel=ios&version=2.5.1
     // 40431.json   ->  漫画id.json  做key传入
//    {
//        "id": 43732,
//        "islong": 2,
//        "direction": 1,
//        "title": "Last Geass",
//        "is_dmzj": 0,
//        "cover": "https:\/\/images.dmzj.com\/webpic\/2\/lg0406l.jpg",
//        "description": "\u4e00\u76f4\u6697\u604b\u7740\u9752\u6885\u7af9\u9a6c\u7acb\u590f\u7684\u8349\u592a\uff0c\u4e3a\u4e86\u5f97\u5230\u5fc3\u4e0a\u4eba\u7684\u7231\u63a5\u89e6\u5230\u4e86\u7981\u5fcc\u3002\u5728\u524d\u65b9\u7b49\u5f85\u7740\u4ed6\u7684\u7a76\u7adf\u662f\u5065\u5168\u7684\u9752\u6625\u751f\u6d3b\uff0c\u8fd8\u662f\u4e00\u53bb\u4e0d\u8fd4\u7684\u6df1\u6e0a.....",
//        "last_updatetime": 1529299474,
//        "copyright": 0,
//        "first_letter": "l",
//        "hot_num": 13182947,
//        "hit_num": 1481096,
//        "uid": null,
//        "types": [{
//        "tag_id": 8,
//        "tag_name": "\u7231\u60c5"
//    }, {
//        "tag_id": 13,
//        "tag_name": "\u6821\u56ed"
//    }],
//        "authors": [{
//        "tag_id": 461,
//        "tag_name": "\u9ad8\u6865\u4fee"
//    }],
//        "status": [{
//        "tag_id": 2309,
//        "tag_name": "\u8fde\u8f7d\u4e2d"
//    }],
//        "subscribe_num": 65794,
//        "chapters": [{
//        "title": "\u8fde\u8f7d",
//        "data": [{
//        "chapter_id": 77678,
//        "chapter_title": "3\u8bdd",
//        "updatetime": 1529299474,
//        "filesize": 3734266,
//        "chapter_order": 30
//    }, {
//        "chapter_id": 77287,
//        "chapter_title": "2\u8bdd",
//        "updatetime": 1528211564,
//        "filesize": 8260324,
//        "chapter_order": 20
//    }, {
//        "chapter_id": 75210,
//        "chapter_title": "1\u8bdd",
//        "updatetime": 1523003189,
//        "filesize": 7334682,
//        "chapter_order": 10
//    }]
//    }],
//        "comment": {
//        "comment_count": 1252,
//        "latest_comment": [{
//        "comment_id": 5172046,
//        "uid": 100266585,
//        "content": "\u5988\u7684\u88e4\u5b50\u90fd\u6ca1\u8131\u5c31\u5b8c\u4e86\uff1f",
//        "createtime": 1529329683,
//        "nickname": "\uffe0\u591c\u4e36\u72ec\u594f\u3044",
//        "avatar": "https:\/\/avatar.dmzj.com\/75\/27\/7527e4972f8a6c8ca8886751c73ed14c.png"
//    }, {
//        "comment_id": 5172012,
//        "uid": 100305811,
//        "content": "\u6c42\u4e00\u5bb6\u4e66\u5e97\u5730\u5740\uff0c\u6025\uff01",
//        "createtime": 1529329532,
//        "nickname": "\u8449\u843d\u53c8\u4e00\u751f",
//        "avatar": "https:\/\/avatar.dmzj.com\/1f\/22\/1f2278698919c9e116eebf4e8bacd7f9.png"
//    }]
//    }
//    }

    @GET
    fun getComicInfo() : Observable<String>



//    URl	https://imgzip.dmzj.com/h/43596/74197.zip
//    comic id 40431 , down file -> 64328.zip

//    GET /k/40431/64471.zip HTTP/1.1
//    Referer: http://images.dmzj.com/
//    Content-Length: 0
//    Host: imgzip.dmzj.com
//    Connection: Keep-Alive
//    User-Agent: Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; HTC D820u Build/KOT49H) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1
//    Accept-Encoding: gzip

    fun downloadComic() : Observable<String>
}