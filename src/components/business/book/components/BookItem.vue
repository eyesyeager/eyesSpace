<template>
  <div class="bookItem">
    <div class="title">{{ data.title }}</div>
    <div class="meta">
      <span>作者: {{ data.author }}</span>
      <span> | </span>
      <span>类型: {{ data.type }}</span>
      <span> | </span>
      <span>时期: {{ data.period }}</span>
    </div>
    <div class="introduce">{{ data.introduce }}</div>
    <div class="footer">
      <div class="time">{{ data.createTime }}</div>
      <div class="view">点击:{{ view }}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import utils from "@/utils/helper";

export default defineComponent({
  props: ["data"],
  setup(props) {
    return {
      data: props.data,
      view: utils.simplifyNum(props.data.view)
    };
  },
});
</script>

<style lang="scss" scoped>

.bookItem {
  background: var(--color-white);
  border-radius: 8px;
  box-shadow: var(--shadow-card);
  -webkit-box-shadow: var(--shadow-card);
  -moz-box-shadow: var(--shadow-card);
  color: var(--color-normal);
  padding: 16px 20px;
  display: flex;
  flex-direction: column;
  transition: box-shadow var(--transition-normal), transform var(--transition-normal);

  &:hover {
    box-shadow: var(--shadow-card)-hover;
    -webkit-box-shadow: var(--shadow-card)-hover;
    -moz-box-shadow: var(--shadow-card)-hover;
    transform: translateY(-2px);
  }

  .title {
    font-size: 20px;
    font-weight: 600;
    color: var(--color-title);
    margin-bottom: 4px;
  }

  .meta {
    text-align: right;
    margin: 8px 0;
    font-size: 13px;
    color: var(--color-assist);
  }

  .introduce {
    line-height: 1.6;
    flex: 1;
    font-size: 14px;
  }

  .footer {
    height: 20px;
    line-height: 20px;
    display: flex;
    justify-content: space-between;
    margin-top: 12px;
    font-size: 13px;
    color: var(--color-assist);
  }
}
</style>