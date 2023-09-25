// 接口返回格式
export declare interface RespInterface {
    code: number;
    msg: string;
    [propName: string]: any;
}

// 统一api管理
export declare interface ApiObject extends 
    SiteInterface, 
    UserInterface, 
    BlogInterface,
    ShuoshuoInterface,
    MusicInterface, 
    VideoInterface,
    JokeInterface,
    AnimeInterface,
    FriendInterface,
    VersionInterface
{}

// 分页
interface PageInterface {
    [page]: number;
    [pageSize]: number;
}

/*
 ***************************************************************************************
 *                               composition——comment
 ***************************************************************************************
 */

export declare interface PublishCommentInterface {
    objectId: number | string;
    landlord?: number;
    replyId?: number;
    originalComment: string;
    comment: string;
}

export declare interface GetCommentListInterface extends PageInterface {
    id: number | string;
}

export declare type DelCommentInterface = Array<number>; 

/*
 ***************************************************************************************
 *                                    site
 ***************************************************************************************
 */
export declare interface SiteInterface {
    getContext: () => Promise<RespInterface>;
    getHomeList: (req: GetHomeListReqInterface) => Promise<RespInterface>;
    getSiteData: () => Promise<RespInterface>;
    addSpaceVisit: (req: AddSpaceVisitReqInterface) => Promise<RespInterface>;
    getAboutContent: () => Promise<RespInterface>;
    doAboutComment: (req: PublishCommentInterface) => Promise<RespInterface>;
    getAboutCommentList: (req: GetCommentListInterface) => Promise<RespInterface>;
    delAboutComment: (req: DelCommentInterface) => Promise<RespInterface>;
}

interface GetHomeListReqInterface extends PageInterface {}

interface AddSpaceVisitReqInterface {
    path: string;
}

/*
 ***************************************************************************************
 *                                    user
 ***************************************************************************************
 */
export declare interface UserInterface {
    getUserInfo: () => Promise<RespInterface>;
}

/*
 ***************************************************************************************
 *                                    blog
 ***************************************************************************************
 */
export declare interface BlogInterface {
    getBlogListInfo: () => Promise<RespInterface>;
    getBlogList:(req: BlogListReqInterface) => Promise<RespInterface>;
    getBlogInfo: (req: Array<any>) => Promise<RespInterface>;
    getBlogCategory: () => Promise<RespInterface>;
    getBlogLabel: () => Promise<RespInterface>;
    doBlogLike: (req: DoBlogLikeReqInterface) => Promise<RespInterface>;
    doBlogCollect: (req: DoBlogCollectReqInterface) => Promise<RespInterface>;
    doBlogComment: (req: PublishCommentInterface) => Promise<RespInterface>;
    getBlogCommentList: (req: GetCommentListInterface) => Promise<RespInterface>;
    delBlogComment: (req: DelCommentInterface) => Promise<RespInterface>;
}

interface BlogListReqInterface extends PageInterface {}

interface DoBlogLikeReqInterface {
    id: any;
}

interface DoBlogCollectReqInterface {
    id: any;
}

/*
 ***************************************************************************************
 *                                    shuoshuo
 ***************************************************************************************
 */
export declare interface ShuoshuoInterface {
    getShuoshuoList: (req: ShuoshuoListReqInterface) => Promise<RespInterface>;
    getShuoshuoListInfo: () => Promise<RespInterface>;
    getShuoshuoSingleInfo: (req: ShuoshuoSingleInfoReqInterface) => Promise<RespInterface>;
    doShuoshuoComment: (req: PublishCommentInterface) => Promise<RespInterface>;
    getShuoshuoCommentList: (req: GetCommentListInterface) => Promise<RespInterface>;
    delShuoshuoComment: (req: DelCommentInterface) => Promise<RespInterface>;
}

interface ShuoshuoListReqInterface extends PageInterface {}

interface ShuoshuoSingleInfoReqInterface {
    id: string;
}

/*
 ***************************************************************************************
 *                                    music
 ***************************************************************************************
 */
export declare interface MusicInterface {
    getMusicInfo: () => Promise<RespInterface>;
    doMusicUserLike: (req: DoMusicUserLikeReqInterface) => Promise<RespInterface>;
}

interface DoMusicUserLikeReqInterface {
    id: string;
}

/*
 ***************************************************************************************
 *                                    video
 ***************************************************************************************
 */
export declare interface VideoInterface {
    getVideoInfo: () => Promise<RespInterface>;
    doVideoUserLike: (req: DoVideoUserLikeReqInterface) => Promise<RespInterface>;
}

interface DoVideoUserLikeReqInterface {
    id: string;
}

/*
 ***************************************************************************************
 *                                    joke
 ***************************************************************************************
 */
export declare interface JokeInterface {
    getJokeNotice: () => Promise<RespInterface>;
    getJokeList: (req: GetJokeListReqInterface) => Promise<RespInterface>;
    jokeVisit: () => Promise<RespInterface>;
}

interface GetJokeListReqInterface extends PageInterface {}

/*
 ***************************************************************************************
 *                                    anime
 ***************************************************************************************
 */

export declare interface AnimeInterface {
    getAnimeNotice: () => Promise<RespInterface>;
    getAnimeListInfo: () => Promise<RespInterface>;
    getAnimeList: (req: GetAnimeListReqInterface) => Promise<RespInterface>;
    getAnimeInfo: (req: Array<any>) => Promise<RespInterface>;
    doAnimeComment: (req: PublishCommentInterface) => Promise<RespInterface>;
    getAnimeCommentList: (req: GetCommentListInterface) => Promise<RespInterface>;
    delAnimeComment: (req: DelCommentInterface) => Promise<RespInterface>;
}

interface GetAnimeListReqInterface extends PageInterface {}

/*
 ***************************************************************************************
 *                                    friend
 ***************************************************************************************
 */
export declare interface FriendInterface {
    applyFriendChain: (req: ApplyFriendChainReqInterface) => Promise<RespInterface>;
    getFriendListData: () => Promise<RespInterface>;
    getFriendList: (req: GetFriendListReqInterface) => Promise<RespInterface>;
    getFriendPreamble: () => Promise<RespInterface>;
}

interface ApplyFriendChainReqInterface {

}

interface GetFriendListReqInterface extends PageInterface {}

/*
 ***************************************************************************************
 *                                    version
 ***************************************************************************************
 */

export declare interface VersionInterface {
    getVersionInfo: () => Promise<RespInterface>;
    getVersionList: (req: GetVersionListReqInterface) => Promise<RespInterface>;
}

interface GetVersionListReqInterface extends PageInterface {}