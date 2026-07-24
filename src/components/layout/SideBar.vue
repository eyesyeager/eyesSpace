<template>
  <div class="sideBar">
    <owner-card class="ownerCard" />
    <div class="navBox">
      <template v-for="item in headerConfig" :key="item.word">
        <div class="option" v-if="!item.children" @click="pageJump(item.path)">
          <img :src="item.icon" />
          <div class="word">{{ item.word }}</div>
        </div>
        <div class="option" v-for="child in item.children" :key="child.word" @click="pageJump(child.path)" v-else>
          <img :src="child.icon" />
          <div class="word">{{ child.word }}</div>
        </div>
      </template>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, inject, watch } from "vue";
import { WindowInterface } from "@/types";
import { OwnerCard } from "@/components/common/card";
import { useRouter } from "vue-router";
import { siteConfig } from "@/config/program";
import { headerConfig } from "@/config/site";

export default defineComponent({
  emits: ["closeSideBar"],
  components: { OwnerCard },
  setup(prop, ctx) {
    const router = useRouter();
    const $window = inject<WindowInterface>("$window")!;

    function pageJump(path: string) {
      router.push(path);
      ctx.emit("closeSideBar");
    }

    watch(
      () => $window.width.value,
      (value) => {
        if (value > siteConfig.mpThreshold) {
          ctx.emit("closeSideBar");
        }
      }
    );

    return {
      headerConfig,
      pageJump
    };
  },
});
</script>

<style lang="scss" scoped>

.sideBar {
  position: fixed;
  z-index: var(--z-sidebar);
  top: 0;
  right: 0;
  width: 300px;
  height: 100vh;
  background: rgba(255, 255, 255, 0.98);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  padding: 0 20px;
  overflow-y: auto;
  .ownerCard {
    margin: 20px auto;
  }
  .navBox {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    .option {
      box-shadow: var(--shadow-card);
      -webkit-box-shadow: var(--shadow-card);
      -moz-box-shadow: var(--shadow-card);
      border-radius: 8px;
      background: var(--color-white);
      display: flex;
      justify-content: center;
      align-items: center;
      width: 80px;
      height: 50px;
      margin-left: calc(20px / 6);
      margin-right: calc(20px / 6);
      margin-bottom: 10px;
      cursor: pointer;
      transition: box-shadow var(--transition-normal), transform var(--transition-normal), background var(--transition-fast);
      img {
        display: block;
        width: 25px;
        height: 25px;
        margin-right: 5px;
        transition: transform var(--transition-normal);
      }
      .word {
        font-size: 15px;
        text-align: center;
        color: var(--color-normal);
      }
      &:hover {
        box-shadow: var(--shadow-card-hover);
        -webkit-box-shadow: var(--shadow-card-hover);
        -moz-box-shadow: var(--shadow-card-hover);
        transform: translateY(-1px);
        background: rgba(127, 200, 248, 0.05);
        img {
          transform: scale(1.1);
        }
      }
      &:active {
        transform: translateY(0);
      }
    }
  }
}
</style>