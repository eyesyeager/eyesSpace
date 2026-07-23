import { defineStore } from "pinia";
import { reactive, ref, type Ref, type UnwrapNestedRefs } from "vue";
import type { Cards, CardList, CardType, Dialogs } from "@/constant";
import { RollType } from "@/composables/useGoBoth";

export const useProcessStore = defineStore("process", () => {
    // 弹出层逻辑控制

    // 黑色幕布
    const maskStatus = ref(false);
    const maskClickFunc: Ref<() => void> = ref(() => { });

    function maskShow(clickFunc?: (() => void) | null) {
        maskClickFunc.value = clickFunc || maskClickFunc.value;
        maskStatus.value = true;
    }

    function maskHide() {
        maskClickFunc.value = () => { };
        maskStatus.value = false;
    }

    // 页面跳转加载页面
    const loadStatus = ref(true);

    function loadShow() { loadStatus.value = true; }
    function loadHide() { loadStatus.value = false; }

    // 轻提示
    const tipSentry = ref(false);
    const tipList = reactive({
        msg: "",
        type: 0
    });

    const tipShow = {
        success: (msg: string) => {
            tipList.msg = msg;
            tipList.type = 0;
            tipSentry.value = !tipSentry.value;
        },
        info: (msg: string) => {
            tipList.msg = msg;
            tipList.type = 1;
            tipSentry.value = !tipSentry.value;
        },
        warn: (msg: string) => {
            tipList.msg = msg;
            tipList.type = 2;
            tipSentry.value = !tipSentry.value;
        },
        error: (msg: string) => {
            tipList.msg = msg;
            tipList.type = 3;
            tipSentry.value = !tipSentry.value;
        }
    };

    // 提示
    const alertStatus = ref(false);
    const alertMsg = reactive({
        title: "",
        content: ""
    });

    function alertShow(msg: UnwrapNestedRefs<{ title: string; content: string }>) {
        if (alertStatus.value === true) {
            alertStatus.value = false;
        }
        setTimeout(() => {
            alertMsg.title = msg.title;
            alertMsg.content = msg.content;
            alertStatus.value = true;
        }, 500);
    }

    function alertHide() {
        alertStatus.value = false;
    }

    // 弹窗
    const dialogStatus = ref(false);
    const dialogChoice = ref(0);

    function dialogShow(dialog: Dialogs) {
        dialogChoice.value = dialog;
        dialogStatus.value = true;
    }

    function dialogHide() {
        dialogStatus.value = false;
    }

    /*
     * 组件控制
     */

    // 顶部导航栏
    const headerStatus = ref(true);
    const headerCheckLock = ref(true);

    function headerCheckSwitch(clientHeight: number, HTMLHeight: number) {
        headerCheckLock.value = HTMLHeight < clientHeight;
    }

    // 侧栏卡片
    const sideCardStatus = ref(true);
    const sideCardPosition = ref("row");
    const sideCardType = ref(0);
    const sideCardChoice = ref<Array<Cards>>([]);
    const sideCardList = ref(0);
    const sideCardFollow = ref(false);
    const sideCardFollowIndex = ref(0);

    // 底部组件
    const footerStatus = ref(true);
    const footerPosition = ref(true);

    function footerPositionSwitch(clientHeight: number, HTMLHeight: number) {
        const foot = document.querySelector("#footer");
        if (!foot) {
            return;
        }
        footerPosition.value = clientHeight < HTMLHeight + (foot as HTMLElement).clientHeight;
    }

    // 全局工具按钮
    const rollType = ref(RollType.speed);
    const rollTime = ref(1);

    return {
        // popup
        maskStatus,
        maskClickFunc,
        maskShow,
        maskHide,
        loadStatus,
        loadShow,
        loadHide,
        tipSentry,
        tipList,
        tipShow,
        alertStatus,
        alertMsg,
        alertShow,
        alertHide,
        dialogStatus,
        dialogChoice,
        dialogShow,
        dialogHide,
        // component
        headerStatus,
        headerCheckLock,
        headerCheckSwitch,
        sideCardStatus,
        sideCardPosition,
        sideCardType,
        sideCardChoice,
        sideCardList,
        sideCardFollow,
        sideCardFollowIndex,
        footerStatus,
        footerPosition,
        footerPositionSwitch,
        rollType,
        rollTime,
    };
});
