import { reactive, ref } from "vue";

// 等待层
const Wait: Wait = {
    status: ref(false),
    show() {
        Wait.status.value = true;
    },
    hide() {
        Wait.status.value = false;
    },
};

// 文案配置
const CV: CV = {
    context: {},
    init: <T>(context: T) => {
        CV.context = context;
    },
};

// 用户信息
const User: U = {
    info: reactive({ detail: {} }),
    init: <T>(userInfo: T) => {
        User.info.detail = userInfo;
    },
};

// 流程逻辑控制
const Ext: ext = {
    // kinds: ref(1), //1.抽奖 2.设铃音 3.我的奖品
    // setKinds(kinds) {
    //     Ext.kinds.value = kinds;
    // },
    // openLogin: ref(false), //是否打开登录弹窗
    // setOpenLogin(openLogin) {
    //     Ext.openLogin.value = openLogin;
    // }
};

export { User, CV, Wait, Ext };