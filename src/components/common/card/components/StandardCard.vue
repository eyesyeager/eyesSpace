<template>
<base-card>
  <div class="standardCard">
    <div class="header">
      <img :src="icon">
      <div class="title">{{title}}</div>
    </div>
    <slot />
  </div>
</base-card>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import BaseCard from "./BaseCard.vue";

export default defineComponent({
  props: ["icon", "title"],
  components: { BaseCard },
  setup() {
    return {
    };
  },
});
</script>

<style lang="scss" scoped>

.standardCard {
  .header {
    margin-bottom: 20px;
    padding-bottom: 12px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.06);
    display: flex;
    align-items: center;
    img {
      width: 20px;
      height: 20px;
      display: block;
      margin-right: 8px;
      opacity: 0.7;
    }
    .title {
      height: 20px;
      line-height: 20px;
      font-size: 18px;
      font-weight: 600;
      color: var(--color-title);
      letter-spacing: 0.5px;
    }
  }
}
</style>