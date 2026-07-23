<template>
  <div class="blogItem" @click="jumpDetail(props.id)">
    <div class="title">{{props.title}}</div>
    <div class="meta">
        <span>类别: {{props.category}}</span>
        <span> | </span>
        <span>字数: {{props.words}}</span>
        <span> | </span>
        <span>阅读时长: {{time}}</span>
    </div>
    <div class="summary">{{props.summary}}</div>
    <div class="footer">
        <div class="time">{{props.date}}</div>
        <div class="view">阅读:{{view}}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import utils from "@/utils/helper";

export default defineComponent({
  components: { },
  props: ["id", "title", "category", "words", "summary", "date", "view"],
  setup(props) {
    let router = useRouter();

    function jumpDetail(id: number) {
      window.open(router.resolve(`/blog/details/${id}`).href, "_blank");
    }

    return {
      props,
      time: utils.estimateReadTime(props.words),
      view: utils.simplifyNum(props.view),
      jumpDetail
    };
  },
});
</script>

<style lang="scss" scoped>

.blogItem {
  width: calc(100% - 5px);
  margin: 0 auto;
  margin-bottom: 16px;
  padding: 16px 20px;
  background: var(--color-white);
  border-radius: 8px;
  box-shadow: var(--shadow-card);
  -webkit-box-shadow: var(--shadow-card);
  -moz-box-shadow: var(--shadow-card);
  color: var(--color-normal);
  cursor: pointer;
  transition: box-shadow var(--transition-normal), transform var(--transition-normal);

  &:hover {
    box-shadow: var(--shadow-card)-hover;
    -webkit-box-shadow: var(--shadow-card)-hover;
    -moz-box-shadow: var(--shadow-card)-hover;
    transform: translateY(-2px);
  }

  .title {
    min-height: 35px;
    font-size: 20px;
    font-weight: 600;
    line-height: 35px;
    color: var(--color-title);
    margin-bottom: 6px;
    transition: color var(--transition-fast);

    &:hover {
      color: var(--color-accent);
    }
  }
  .meta {
    font-size: 13px;
    height: 20px;
    line-height: 20px;
    margin-bottom: 10px;
    color: var(--color-assist);
  }
  .summary {
    min-height: 40px;
    margin-bottom: 10px;
    font-size: 15px;
    line-height: 1.6;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
  }
  .footer {
    height: 20px;
    line-height: 20px;
    display: flex;
    justify-content: space-between;
    font-size: 13px;
    color: var(--color-assist);
    .data {
      width: 200px;
      display: flex;
      justify-content: flex-end;
    }
  }
}
</style>