import { defineStore } from "pinia";
import { reactive, ref } from "vue";
import type { UserType } from "@/types/store/user";

export const useUserStore = defineStore("user", () => {
    const data: Partial<UserType> = reactive({});
    const status = ref<number>(0); // 0：未登录，1：登录

    function init(userInfo: Partial<UserType>) {
        Object.assign(data, userInfo);
    }

    function isLogin(): boolean {
        return status.value == 1;
    }

    return { data, status, init, isLogin };
});
