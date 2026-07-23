import { defineStore } from "pinia";
import { reactive } from "vue";
import type { ContextInterface, ContextType } from "@/types/store/context";

export const useContextStore = defineStore("context", () => {
    const data: Partial<ContextType> = reactive({});

    function init(cx: Partial<ContextType>) {
        Object.assign(data, cx);
    }

    return { data, init };
});
