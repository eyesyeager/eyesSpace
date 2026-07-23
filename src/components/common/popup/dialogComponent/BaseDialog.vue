<template>
  <transition name="baseDialogAnimate">
    <div class="baseDialog">
      <div class="close" v-if="closeSwitch" @click="close">x</div>
      <div class="title" v-if="titleSwitch">{{title}}</div>
      <slot />
    </div>
  </transition>
</template>

<script lang="ts">
import { defineComponent, inject, onMounted } from "vue";
import { ProcessInterface } from "@/types";

export default defineComponent({
  props: {
    title: String,
    close: Boolean
  },
  emits: ["close"],
  setup(props, ctx) {
    const $process = inject<ProcessInterface>("$process")!;

    function close() {
      $process.dialogHide();
      $process.maskHide();
      ctx.emit("close");
    }

    onMounted(() => {
      $process.maskShow(close);
    })

    return {
      closeSwitch: props.close,
      titleSwitch: props.title != null,
      title: props.title,
      close
    };
  },
});
</script>

<style lang="scss" scoped>

.baseDialogAnimate-enter-active {
  animation: dialogIn 0.35s cubic-bezier(0.34, 1.56, 0.64, 1);
}
.baseDialogAnimate-leave-active {
  animation: dialogOut 0.2s ease;
}

@keyframes dialogIn {
  from {
    opacity: 0;
    transform: translate(-50%, -50%) scale(0.92);
  }
  to {
    opacity: 1;
    transform: translate(-50%, -50%) scale(1);
  }
}

@keyframes dialogOut {
  from {
    opacity: 1;
    transform: translate(-50%, -50%) scale(1);
  }
  to {
    opacity: 0;
    transform: translate(-50%, -50%) scale(0.95);
  }
}

.baseDialog {
  width: 500px;
  max-width: calc(100vw - 40px);
  padding: 24px;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: saturate(180%) blur(20px);
  -webkit-backdrop-filter: saturate(180%) blur(20px);
  border-radius: 12px;
  box-shadow: var(--shadow-xl);
  -webkit-box-shadow: var(--shadow-xl);
  -moz-box-shadow: var(--shadow-xl);
  position: fixed;
  z-index: 1000;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  .close {
    width: 28px;
    height: 28px;
    border-radius: 50%;
    position: absolute;
    top: 12px;
    right: 12px;
    color: rgba(0, 0, 0, 0.4);
    font-size: 18px;
    text-align: center;
    line-height: 28px;
    cursor: pointer;
    transition: color var(--transition-fast), background var(--transition-fast);
    &:hover {
      color: rgba(0, 0, 0, 0.7);
      background: rgba(0, 0, 0, 0.06);
    }
  }
  .title {
    height: 24px;
    font-size: 18px;
    font-weight: 600;
    line-height: 24px;
    margin-bottom: 20px;
    color: var(--color-title);
  }
}

@media screen and (max-width: 800px) {
  .baseDialog {
    width: 80% !important;
    min-width: 350px;
  }
}
</style>
