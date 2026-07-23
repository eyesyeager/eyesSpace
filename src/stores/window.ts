import { defineStore } from "pinia";
import { ref } from "vue";

export const useWindowStore = defineStore("window", () => {
    const width = ref(0);
    const height = ref(0);
    const scrollTop = ref(0);

    function initSize() {
        width.value = document.documentElement.clientWidth;
        height.value = document.documentElement.clientHeight;
    }

    function initDistance() {
        scrollTop.value = document.documentElement.scrollTop || document.body.scrollTop;
    }

    return { width, height, scrollTop, initSize, initDistance };
});
