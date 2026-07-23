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
import { ContextInterface } from "@/types";

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

.ownerCard {
  padding-top: 10px;
  .avatar {
    width: 110px;
    height: 110px;
    background-size: 100% 100%;
    border-radius: 50%;
    margin: 0px auto;
    margin-bottom: 20px;
    box-shadow: var(--shadow-avatar);
    -webkit-box-shadow: var(--shadow-avatar);
    -moz-box-shadow: var(--shadow-avatar);
    transition: transform var(--transition-slow), box-shadow var(--transition-slow);
    &:hover {
      transform: scale(1.05);
      box-shadow: var(--shadow-card)-hover;
      -webkit-box-shadow: var(--shadow-card)-hover;
      -moz-box-shadow: var(--shadow-card)-hover;
    }
  }
  .words {
    .name {
      font-size: 20px;
      font-weight: 600;
      color: var(--color-title);
      text-align: center;
      margin-bottom: 8px;
    }
    .motto {
      color: var(--color-normal);
      text-align: center;
      font-size: 13px;
      line-height: 1.6;
    }
  }
}
</style>