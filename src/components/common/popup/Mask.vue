<template>
  <transition name="maskAnimate">
    <div class="mask" v-show="status" @click="clickMask"></div>
  </transition>
</template>

<script lang="ts">
import { defineComponent, inject, ref, watch } from "vue";
import { ProcessInterface } from "@/types/store/process";

export default defineComponent({
  setup() {
    const $process = inject<ProcessInterface>("$process")!;
    let status = ref(false);
    let clickMask = ref(() => {});

    watch(
      () => $process.maskStatus.value,
      (value) => {
        status.value = value;
        if(value) {
          clickMask.value = $process.maskClickFunc.value;
        }
      }
    );

    return {
      status,
      clickMask
    };
  },
});
</script>

<style lang="scss" scoped>

.maskAnimate-enter-active {
  transition: opacity 0.3s ease;
}
.maskAnimate-leave-active {
  transition: opacity 0.25s ease;
}
.maskAnimate-enter-from,
.maskAnimate-leave-to {
  opacity: 0;
}

.mask {
    position: fixed;
    z-index: 999;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: rgba(0, 0, 0, 0.45);
    backdrop-filter: blur(2px);
    -webkit-backdrop-filter: blur(2px);
}
</style>