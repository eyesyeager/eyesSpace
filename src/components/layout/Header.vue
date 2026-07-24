<template>
  <div class="header">
    <div class="space" @click="pageJump('/')">{{ spaceName }}</div>
    <div class="bar">
      <HeaderItem v-if="mpSwitch" />
      <div class="menuBar" :style="{backgroundImage: 'url(' + menuBar + ')' }" @click="openSideBar" v-else></div>
      <div class="avatar" @click="avatarClick" :style="{ backgroundImage: 'url(' + avatar + ')' }"></div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, inject, onMounted, ref, watch } from "vue";
import { ContextInterface, WindowInterface, UserInterface, ProcessInterface } from "@/types";
import { useRouter } from "vue-router";
import { siteConfig } from "@/config/program";
import { headerConfig, userCenterContext } from "@/config/site";
import resource from "@/config/resource";

import useMouseWheel from "@/composables/useMouseWheel";
import HeaderItem from "./components/HeaderItem.vue";

export default defineComponent({
  components: { HeaderItem },
  emits: ['openSideBar'],
  setup(prop, ctx) {
    const $context = inject<ContextInterface>("$context")!;
    const $user = inject<UserInterface>("$user")!;
    const $window = inject<WindowInterface>("$window")!;
    const $process = inject<ProcessInterface>("$process")!;
    const router = useRouter();

    let spaceName = ref($context.data.spaceName);
    let mpSwitch = ref(true);
    let lock = true; // 显示锁

    function pageJump(path: string) {
      router.push(path);
    }

    function openSideBar() {
      ctx.emit("openSideBar");
    }

    function avatarClick() {
      if($user.status) {
        window.open(`${userCenterContext.info}?clientId=${$context.data.spaceClientId}`);
      } else {
        window.open(`${userCenterContext.auth}?clientId=${$context.data.spaceClientId}&redirectUrl=${process.env.VITE_SITE_URL + userCenterContext.redirectUrl}`);
      }
    }

    watch(
      () => $window.width.value,
      (value) => {
        mpSwitch.value = value > siteConfig.mpThreshold;
      },
      { immediate: true }
    );

    watch(
      () => $window.scrollTop.value,
      (value) => {
        if(value == 0) {
          lock = true;
          (document.querySelector(".header") as HTMLElement).style.top = "0px";
          $process.headerCollapse.value = false;
        }
      }
    );

    // 滚轮与滑动监听
    onMounted(() => {
      useMouseWheel(() => {
        if(lock) return;
        (document.querySelector(".header") as HTMLElement).style.top = "0px";
        lock = true;
        $process.headerCollapse.value = false;
      }, () => {
        if(!lock || $process.headerCheckLock.value) return;
        (document.querySelector(".header") as HTMLElement).style.top = "-60px";
        lock = false;
        $process.headerCollapse.value = true;
      });
    });

    return {
      spaceName,
      avatar: $user.data.avatar,
      headerConfig,
      menuBar: resource.menu,
      mpSwitch,
      pageJump,
      openSideBar,
      avatarClick,
    };
  },
});
</script>

<style lang="scss" scoped>

.header {
  min-width: 370px;
  width: 100%;
  max-width: 2500px;
  margin: 0 auto;
  height: 60px;
  padding: 20px 40px 20px 40px;
  background: var(--glass-bg);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  color: var(--color-normal);
  transition: top 0.5s, box-shadow var(--transition-normal);
  position: fixed;
  z-index: var(--z-header);
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 1px 0 rgba(0, 0, 0, 0.04);
  .space {
    width: 140px;
    height: 20px;
    color: var(--color-title);
    line-height: 20px;
    font-size: 20px;
    font-weight: 600;
    cursor: pointer;
    transition: opacity var(--transition-fast);
    &:hover {
      opacity: 0.7;
    }
  }
  .bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    .menuBar {
      width: 30px;
      height: 30px;
      background-size: 100% 100%;
      transform: translate(-10px, 0);
      cursor: pointer;
      border-radius: 4px;
      transition: background-color var(--transition-fast);
      &:hover {
        background-color: rgba(0, 0, 0, 0.05);
      }
    }
    .avatar {
      width: 40px;
      height: 40px;
      background-size: 100% 100%;
      border-radius: 50%;
      box-shadow: var(--shadow-avatar);
      -webkit-box-shadow: var(--shadow-avatar);
      -moz-box-shadow: var(--shadow-avatar);
      cursor: pointer;
      transition: transform var(--transition-normal), box-shadow var(--transition-normal);
      &:hover {
        transform: scale(1.1);
        box-shadow: var(--shadow-card-hover);
        -webkit-box-shadow: var(--shadow-card-hover);
        -moz-box-shadow: var(--shadow-card-hover);
      }
    }
  }
}

@media screen and (max-width: 800px) {
  .header {
    padding: 20px !important;
  }
}
</style>