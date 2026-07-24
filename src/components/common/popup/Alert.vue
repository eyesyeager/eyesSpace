<template>
  <transition name="alertAnimate">
    <div class="alert" v-show="status">
      <div class="title">
        <div class="close" @click="close">×</div>
        <div class="content">{{ msg.title }}</div>
      </div>
      <div class="value">{{ msg.content }}</div>
    </div>
  </transition>
</template>

<script lang="ts">
import { defineComponent, ref, reactive, watch, inject } from "vue";
import { ProcessInterface } from "@/types/store/process";

export default defineComponent({
  components: {},
  setup() {
    const $process = inject<ProcessInterface>("$process")!;

    let status = ref($process.alertStatus.value);
    let msg = reactive({
      title: "",
      content: "",
    });

    watch(
      () => $process.alertStatus.value,
      (value) => {
        status.value = value;
        if (value) {
          msg.title = $process.alertMsg.title;
          msg.content = $process.alertMsg.content;
        }
      }
    );

    function close() {
      $process.alertHide();
    }

    return {
      status,
      msg,
      close,
    };
  },
});
</script>

<style lang="scss" scoped>

.alertAnimate-enter-active {
  animation: slideInRight 0.4s cubic-bezier(0.34, 1.56, 0.64, 1);
}
.alertAnimate-leave-active {
  animation: slideOutRight 0.3s ease;
}

@keyframes slideInRight {
  from {
    opacity: 0;
    transform: translateX(40px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

@keyframes slideOutRight {
  from {
    opacity: 1;
    transform: translateX(0);
  }
  to {
    opacity: 0;
    transform: translateX(40px);
  }
}

.alert {
  width: 280px;
  min-height: 90px;
  padding: 16px;
  box-sizing: border-box;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-radius: 12px;
  box-shadow: var(--shadow-xl);
  -webkit-box-shadow: var(--shadow-xl);
  -moz-box-shadow: var(--shadow-xl);
  position: fixed;
  z-index: var(--z-popup);
  top: 70px;
  right: 24px;
  .title {
    display: flex;
    align-items: center;
    height: 24px;
    .close {
      width: 22px;
      height: 22px;
      border-radius: 50%;
      background: var(--color-danger);
      color: #fff;
      text-align: center;
      line-height: 22px;
      font-size: 14px;
      cursor: pointer;
      transition: transform var(--transition-fast), opacity var(--transition-fast);
      flex-shrink: 0;
      &:hover {
        transform: scale(1.1);
        opacity: 0.9;
      }
    }
    .content {
      line-height: 24px;
      margin-left: 10px;
      color: var(--color-title);
      font-size: 15px;
      font-weight: 600;
    }
  }
  .value {
    margin-left: 32px;
    margin-top: 10px;
    color: var(--color-normal);
    font-size: 13px;
    line-height: 1.6;
  }
}

@media screen and (max-width: 800px) {
  .alert {
    right: 10px;
  }
}
</style>
