import { 
    HeaderConfigType, 
    SiteDataType, 
    UserCenterContextInterface,
    FooterConfigInterface, 
    ErrorPathInterface, 
    ErrorConfigInterface, 
    SiteContextInterface,
    ContactMeConfigType
} from "@/d.ts/config/site";
import resource from "./resource";
import { pathConfig } from "@/config/program";

/*
 ***************************************************************************************
 *                                    site
 ***************************************************************************************
 */
const siteContext: SiteContextInterface = {
    clientId: 198344,
    siteName: "耶瞳空间",
    siteNameEn: "eyesSpace",
    spaceVersion: "3.5.1",
    ownerEmail: "eyesyeager@gmail.com",
    siteVideoBV: "BV1fg411b7sF",
    commentMaxLen: 1000
};

/*
 ***************************************************************************************
 *                                    UserCenter
 ***************************************************************************************
 */
const userCenterContext: UserCenterContextInterface = {
    info: "http://user.eyescode.top/",
    auth: "http://user.eyescode.top/OAuth2",
    redirectUrl: "/auth",
}

/*
 ***************************************************************************************
 *                                    meta
 * 
 * 对于单页面应用，这玩意儿对SEO一点用也没有，但还是写着玩玩吧
 ***************************************************************************************
 */
const metaInfo = {
    home: {
        title: siteContext.siteName + " | 首页",
        data: [
            { keywords: `${siteContext.siteName}，个人网站，耶瞳，eyes，eyesyeager，程序员，博客，说说，音乐，视频，动漫，留言，友联，版本，关于` },
            { description: `这是${siteContext.siteName}，是一个个人网站` }
        ]
    },
    blog: {
        title: siteContext.siteName + " | 博客",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，程序员，博客，Java，JavaScript，Flutter，Python，PHP，Unity` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是博客页面` }
        ]
    },
    shuoshuo: {
        title: siteContext.siteName + " | 说说",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，说说` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是说说页面` }
        ]
    },
    music: {
        title: siteContext.siteName + " | 音乐",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，音乐` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是音乐页面` }
        ]
    },
    video: {
        title: siteContext.siteName + " | 视频",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，视频` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是视频页面` }
        ]
    },
    joke: {
        title: siteContext.siteName + " | 梗图",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，梗图` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是梗图页面` }
        ]
    },
    anime: {
        title: siteContext.siteName + " | 动漫",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，动漫` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是动漫页面` }
        ]
    },
    message: {
        title: siteContext.siteName + " | 留言",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，留言` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是留言页面` }
        ]
    },
    friend: {
        title: siteContext.siteName + " | 友链",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，友链` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是友链页面` }
        ] 
    },
    version: {
        title: siteContext.siteName + " | 版本信息",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活，版本，更新，日志` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是版本信息页面` }
        ]
    },
    about: {
        title: siteContext.siteName + " | 关于",
        data: [
            { keywords: `${siteContext.siteName}，耶瞳，eyes，eyesyeager，生活` },
            { description: `这是${siteContext.siteName}，是一个个人网站，这是关于页面` }
        ]
    }
}

/*
 ***************************************************************************************
 *                                    popup
 ***************************************************************************************
 */

// 轻提示图标配置
const tipType: Array<string> = [
    resource.success,
    resource.info,
    resource.warn,
    resource.error
];

// 联系站长弹窗配置
const contactMeConfig: ContactMeConfigType = [
    {
        title: "可以发邮件",
        content: siteContext.ownerEmail,
        btnIcon: resource.copy,
        btnWord: "点击复制",
        clickFunc: "copy"
    },
    {
        title: "也可以去B站",
        content: siteContext.siteVideoBV,
        btnIcon: resource.bilibili,
        btnWord: "点击前往",
        clickFunc: "goBilibli"
    },
]

/*
 ***************************************************************************************
 *                                    header
 ***************************************************************************************
 */
const headerConfig: HeaderConfigType = [
    {
        path: pathConfig.home,
        icon: resource.home,
        word: "主页"
    },
    {
        path: pathConfig.blog,
        icon: resource.blog,
        word: "博客"
    },
    {
        path: pathConfig.shuoshuo,
        icon: resource.shuoshuo,
        word: "说说"
    },
    {
        icon: resource.entertainment,
        word: "娱乐",
        children: [
            {
                path: pathConfig.music,
                icon: resource.music,
                word: "音乐"
            },
            {
                path: pathConfig.video,
                icon: resource.video,
                word: "视频"
            },
            {
                path: pathConfig.joke,
                icon: resource.joke,
                word: "梗图"
            }
        ]
    },
    {
        icon: resource.warehouse,
        word: "仓库",
        children: [
            {
                path: pathConfig.anime,
                icon: resource.anime,
                word: "动漫"
            }
        ]
    },
    {
        icon: resource.other,
        word: "其他",
        children: [
            {
                path: pathConfig.message,
                icon: resource.message,
                word: "留言"
            },
            {
                path: pathConfig.friend,
                icon: resource.friend,
                word: "友链"
            },
            {
                path: pathConfig.version,
                icon: resource.version,
                word: "版本"
            },
            {
                path: pathConfig.about,
                icon: resource.about,
                word: "关于",
            }
        ]
    }
];

/*
 ***************************************************************************************
 *                                    Card
 ***************************************************************************************
 */
const siteData: SiteDataType = [
    {
        key: 0,
        name: "本站运行时间"
    },
    {
        key: 1,
        name: "本站访问量"
    },
    {
        key: 2,
        name: "本站总访客"
    }
]

/*
 ***************************************************************************************
 *                                    footer
 ***************************************************************************************
 */
const footerConfig: FooterConfigInterface = {
    copyright: "©2022 By 耶瞳",
    theme: "eyes",
    techStack: "vue+spring",
    zwfwCode: "赣ICP备2022006255号"
}

/*
 ***************************************************************************************
 *                                    preload
 ***************************************************************************************
 */
const preloadList: Array<string> = [
    resource.loading
]

/*
 ***************************************************************************************
 *                                    error
 ***************************************************************************************
 */
const errorPath: ErrorPathInterface = pathConfig.errorPath;

const errorConfig: ErrorConfigInterface = {
    errorRoute: {
        title: "未找到该页面",
        items: [
            {
                icon: resource.before,
                word: "返回前页",
                clickFunc: "goBack"
            },
            {
                icon: resource.home,
                word: "返回首页",
                clickFunc: "goIndex"
            }
        ],
        process: []
    },
    errorContext: {
        title: "服务器好像宕机了 <br> 请稍后重试，或者联系站长",
        items: [
            {
                icon: resource.refresh,
                word: "刷新页面",
                clickFunc: "refreshPage"
            },
            {
                icon: resource.email,
                word: "联系站长",
                clickFunc: "contactMe"
            }
        ],
        process: [false]
    }
}

export { siteContext, userCenterContext, metaInfo, tipType, contactMeConfig, headerConfig, siteData, footerConfig, preloadList, errorPath, errorConfig };