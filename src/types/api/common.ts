// 通用接口返回格式
export interface RespInterface {
    code: number;
    msg: string;
    [propName: string]: any;
}

// 分页请求接口
export interface PageInterface {
    page?: number;
    pageSize?: number;
}

// 统一 API 聚合类型
export interface ApiObject extends
    SiteInterface,
    UserInterface,
    BlogInterface,
    ShuoshuoInterface,
    ProductInterface,
    MusicInterface,
    VideoInterface,
    JokeInterface,
    BookInterface,
    FootprintInterface,
    AnimeInterface,
    FriendInterface,
    VersionInterface {
    // 评论相关方法（运行时动态挂载）
    doBlogComment?: (req: any) => Promise<RespInterface>;
    getBlogCommentList?: (req: any) => Promise<RespInterface>;
    delBlogComment?: (req: any) => Promise<RespInterface>;
    doShuoshuoComment?: (req: any) => Promise<RespInterface>;
    getShuoshuoCommentList?: (req: any) => Promise<RespInterface>;
    delShuoshuoComment?: (req: any) => Promise<RespInterface>;
    doAboutComment?: (req: any) => Promise<RespInterface>;
    getAboutCommentList?: (req: any) => Promise<RespInterface>;
    delAboutComment?: (req: any) => Promise<RespInterface>;
    doAnimeComment?: (req: any) => Promise<RespInterface>;
    getAnimeCommentList?: (req: any) => Promise<RespInterface>;
    delAnimeComment?: (req: any) => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    site
 ***************************************************************************************
 */
export interface SiteInterface {
    getContext: () => Promise<RespInterface>;
    getContextItem: (req: any) => Promise<RespInterface>;
    getBatchContextItem: (req: any) => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    user
 ***************************************************************************************
 */
export interface UserInterface {
    getUserInfo: () => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    blog
 ***************************************************************************************
 */
export interface BlogInterface {
    getBlogListInfo: () => Promise<RespInterface>;
    getBlogList: (req: any) => Promise<RespInterface>;
    getBlogInfo: (req: Array<any>) => Promise<RespInterface>;
    getBlogCategory: () => Promise<RespInterface>;
    getBlogLabel: () => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    shuoshuo
 ***************************************************************************************
 */
export interface ShuoshuoInterface {
    getShuoshuoList: (req: ShuoshuoListReqInterface) => Promise<RespInterface>;
    getShuoshuoListInfo: () => Promise<RespInterface>;
}

export interface ShuoshuoListReqInterface extends PageInterface { }

/*
 ***************************************************************************************
 *                                    footprint
 ***************************************************************************************
 */
export interface FootprintInterface {
    getFootprintList: () => Promise<RespInterface>;
    getFootprintListInfo: () => Promise<RespInterface>;
    getFootprintContentList: (req: any) => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    music
 ***************************************************************************************
 */
export interface MusicInterface {
    getMusicList: () => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    video
 ***************************************************************************************
 */
export interface VideoInterface {
    getVideoInfo: () => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    joke
 ***************************************************************************************
 */
export interface JokeInterface {
    getJokeList: (req: any) => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    anime
 ***************************************************************************************
 */
export interface AnimeInterface {
    getAnimeListInfo: () => Promise<RespInterface>;
    getAnimeList: (req: any) => Promise<RespInterface>;
    getAnimeInfo: (req: Array<any>) => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    book
 ***************************************************************************************
 */
export interface BookInterface {
    getBookListInfo: () => Promise<RespInterface>;
    getBookList: (req: any) => Promise<RespInterface>;
    getBookInfo: (req: Array<any>) => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    product
 ***************************************************************************************
 */
export interface ProductInterface {
    getProductListInfo: () => Promise<RespInterface>;
    getProductPage: (req: any) => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    friend
 ***************************************************************************************
 */
export interface FriendInterface {
    getFriendListData: () => Promise<RespInterface>;
    getFriendList: () => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    version
 ***************************************************************************************
 */
export interface VersionInterface {
    getVersionInfo: () => Promise<RespInterface>;
    getVersionList: (req: any) => Promise<RespInterface>;
}
