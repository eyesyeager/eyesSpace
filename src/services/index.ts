import siteApi from "./api/site.api";
import userApi from "./api/user.api";
import blogApi from "./api/blog.api";
import shuoshuoApi from "./api/shuoshuo.api";
import footprintApi from "./api/footprint.api";
import musicApi from "./api/music.api";
import videoApi from "./api/video.api";
import jokeApi from "./api/joke.api";
import animeApi from "./api/anime.api";
import bookApi from "./api/book.api";
import productApi from "./api/product.api";
import friendApi from "./api/friend.api";
import versionApi from "./api/version.api";
import type { ApiObject } from "@/types/api/common";

const api: ApiObject = {
    ...siteApi,
    ...userApi,
    ...blogApi,
    ...shuoshuoApi,
    ...footprintApi,
    ...musicApi,
    ...videoApi,
    ...jokeApi,
    ...bookApi,
    ...productApi,
    ...animeApi,
    ...friendApi,
    ...versionApi
};

export default api;

// 单独导出领域 API 模块，方便按需引用
export {
    siteApi,
    userApi,
    blogApi,
    shuoshuoApi,
    footprintApi,
    musicApi,
    videoApi,
    jokeApi,
    animeApi,
    bookApi,
    productApi,
    friendApi,
    versionApi,
};
