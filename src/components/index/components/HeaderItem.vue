<template>
  <div class="headerItem">
    <div class="barItem" v-for="item in headerConfig" :key="item.word">
      <div class="option" v-if="!item.children" @click="pageJump(item.path)">
        <img :src="item.icon" />
        <div class="word">{{ item.word }}</div>
      </div>
      <div class="dropDown" v-else>
        <div class="link">
          <img :src="item.icon" />
          <div class="word">{{ item.word }}</div>
        </div>
        <div class="list">
          <div class="child" v-for="child in item.children" :key="child.word" @click="pageJump(child.path)">
            <img :src="child.icon" />
            <div class="word">{{ child.word }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import { headerConfig } from "@/config/site";

export default defineComponent({
  setup() {
    const router = useRouter();
    
    function pageJump(path: string) {
      router.push(path);
    }

    return {
      headerConfig,
      pageJump
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";

.headerItem {
  display: flex;
  .barItem {
    width: 60px;
    margin-right: 24px;
    .option, .link, .child {
      display: flex;
      justify-content: center;
      align-items: center;
      transition: opacity $transition-fast;
      img {
        width: 16px;
        height: 16px;
        display: block;
      }
      .word {
        line-height: 20px;
        margin-left: 5px;
      }
      &:hover {
        opacity: 0.7;
      }
    }
    .option {
      cursor: pointer;
    }
    .dropDown {
      position: relative;
      cursor: default;
      .link:hover {
        opacity: 0.7;
      }
      .list {
        position: absolute;
        top: 100%;
        left: 50%;
        transform: translateX(-50%);
        padding: 8px 0;
        background: rgba($white, 0.95);
        backdrop-filter: saturate(180%) blur(20px);
        -webkit-backdrop-filter: saturate(180%) blur(20px);
        border-radius: 8px;
        box-shadow: $shadow-dropdown;
        -webkit-box-shadow: $shadow-dropdown;
        opacity: 0;
        visibility: hidden;
        transition: opacity $transition-normal, visibility $transition-normal;
      }
      &:hover .list {
        opacity: 1;
        visibility: visible;
      }
      .child {
        margin-top: 0;
        padding: 8px 16px;
        white-space: nowrap;
        cursor: pointer;
        transition: background $transition-fast;
        &:hover {
          background: rgba($accent, 0.08);
          opacity: 1;
        }
      }
    }
  }
}
</style>