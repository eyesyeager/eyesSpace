<template>
  <base-card>
    <div class="ownerCard">
      <div class="avatar" :style="{ backgroundImage: 'url(' + context.avatar + ')' }"></div>
      <div class="words">
        <p class="name">{{ context.name }}</p>
        <p class="motto">{{ context.motto }}</p>
      </div>
    </div>
  </base-card>
</template>

<script lang="ts">
import { defineComponent, inject } from "vue";
import BaseCard from "./components/BaseCard.vue";
import { ContextInterface } from "@/d.ts/plugin";

export default defineComponent({
  components: { BaseCard },
  setup() {
    const $context = inject<ContextInterface>("$context")!;

    let context: any = {
      avatar: $context.data.ownerAvatar,
      name: $context.data.ownerName,
      motto: $context.data.ownerMotto,
    };

    return {
      context
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";

.ownerCard {
  padding-top: 10px;
  .avatar {
    width: 110px;
    height: 110px;
    background-size: 100% 100%;
    border-radius: 50%;
    margin: 0px auto;
    margin-bottom: 20px;
    box-shadow: $shadow-avatar;
    -webkit-box-shadow: $shadow-avatar;
    -moz-box-shadow: $shadow-avatar;
    transition: transform $transition-slow, box-shadow $transition-slow;
    &:hover {
      transform: scale(1.05);
      box-shadow: $shadow-card-hover;
      -webkit-box-shadow: $shadow-card-hover;
      -moz-box-shadow: $shadow-card-hover;
    }
  }
  .words {
    .name {
      font-size: 20px;
      font-weight: 600;
      color: $title;
      text-align: center;
      margin-bottom: 8px;
    }
    .motto {
      color: $normal;
      text-align: center;
      font-size: 13px;
      line-height: 1.6;
    }
  }
}
</style>