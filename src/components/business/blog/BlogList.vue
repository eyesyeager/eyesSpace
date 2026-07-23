<template>
  <div class="blogList">
    <blog-item
      v-for="item in blogListData" :key="item.id"
      :id="item.id"
      :title="item.title"
      :category="item.category"
      :words="item.words"
      :summary="item.summary"
      :date="item.createTime"
      :view="item.views"
    />
    <div class="noDataTip" v-if="!blogListData.length">暂无数据</div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import BlogItem from "@/components/business/blog/components/BlogItem.vue";
import { blogContext } from "@/components/business/blog/businessTs/blogContext";

interface BlogItem {
  id: number | string;
  title: string;
  category: string;
  words: number;
  summary: string;
  createTime: string;
  views: number;
}

export default defineComponent({
  components: { BlogItem },
  setup() {

    return {
      blogListData: blogContext.data as BlogItem[],
    };
  },
});
</script>

<style lang="scss" scoped>

.blogList {
  width: 100%;
  min-width: 335px;
  margin: 0 auto;
  .noDataTip {
    height: 400px;
    color: var(--color-normal);
    line-height: 400px;
    text-align: center;
  }
}
</style>