<template>
  <div class="sideCard" id="sideCard" :class="{ 'sideCard-follow': shouldStickyRoot }" :style="stickyOffsetStyle">
    <!-- Cards 模式 -->
    <div class="cards" v-if="type == CardType.Cards">
      <component class="card" v-for="item in cardChoices" :key="item" :is="cardComponents[item]"/>
    </div>

    <!-- 博客列表页拆分：Owner/Data 正常滚动，Category/Label 跟随 -->
    <template v-else-if="isBlogCardListSplit">
      <owner-card class="card" />
      <data-card class="card" title="博客数据" api="getBlogListInfo" :cardConfig="blogDataCardConfig" />
      <div class="sticky-group" :style="stickyOffsetStyle">
        <blog-category-card class="card" />
        <blog-label-card class="card" />
      </div>
    </template>

    <!-- 博客详情页拆分：Owner/BlogData 正常滚动，Catalog/CRCard 跟随 -->
    <template v-else-if="isBlogDetailSplit">
      <owner-card class="card" />
      <blog-data-card class="card" />
      <div class="sticky-group" :style="stickyOffsetStyle">
        <blog-catalog class="card" />
        <blog-c-r-card class="card" />
      </div>
    </template>

    <!-- 普通 CardList 模式 -->
    <div class="cardList" v-else-if="type == CardType.CardList">
      <component :is="cardListComponents[cardListChoice]"/>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, inject, computed } from "vue";
import { ProcessInterface } from "@/types";
import { CardType, CardList } from "@/constant";
import { AnnounceCard, OwnerCard, DataCard } from "@/components/common/card";
import { ShuoCardList } from "@/components/business/shuoshuo";
import { BlogCardList } from "@/components/business/blog";
import { BlogCategoryCard, BlogLabelCard } from "@/components/business/blog/components/cards";
import { dataCardConfig as blogDataCardConfig } from "@/components/business/blog/config";
import { BlogDetailCardList } from "@/components/business/blogDetail";
import { BlogDataCard, BlogCatalog, BlogCRCard } from "@/components/business/blogDetail/components/cards";
import { AnimeCardList } from "@/components/business/anime";
import { FriendCardList } from "@/components/business/friend";
import { VersionCardList } from "@/components/business/version";
import { JokeCardList } from "@/components/business/joke";
import { BookCardList } from "@/components/business/book";
import { FootprintCardList } from "@/components/business/footprint";
import { ProductCardList } from "@/components/business/product";

export default defineComponent({
  components: { 
    AnnounceCard, OwnerCard, DataCard,
    BlogCategoryCard, BlogLabelCard,
    BlogDataCard, BlogCatalog, BlogCRCard,
    ShuoCardList, BlogCardList, BlogDetailCardList, AnimeCardList,
    FriendCardList, VersionCardList, JokeCardList,
    BookCardList, FootprintCardList, ProductCardList
  },
  setup() {
    const $process = inject<ProcessInterface>("$process")!;
    const cardComponents = [AnnounceCard, OwnerCard];
    const cardListComponents = [ShuoCardList, BlogCardList, BlogDetailCardList, AnimeCardList, FriendCardList, VersionCardList, JokeCardList, BookCardList, FootprintCardList, ProductCardList];

    // followIndex=0：根元素整体 sticky
    const shouldStickyRoot = computed(() =>
      $process.sideCardFollow.value && $process.sideCardFollowIndex.value === 0
    );

    // 跟随偏移：header 收起时只保留间距 10px，否则 60px(header) + 10px = 70px
    const stickyOffsetStyle = computed(() => ({
      top: $process.headerCollapse.value ? "10px" : "70px",
    }));

    // 博客列表页拆分渲染条件
    const isBlogCardListSplit = computed(() =>
      $process.sideCardType.value === CardType.CardList
      && $process.sideCardFollow.value
      && $process.sideCardFollowIndex.value === 2
      && $process.sideCardList.value === CardList.BlogCardList
    );

    // 博客详情页拆分渲染条件
    const isBlogDetailSplit = computed(() =>
      $process.sideCardType.value === CardType.CardList
      && $process.sideCardFollow.value
      && $process.sideCardFollowIndex.value === 2
      && $process.sideCardList.value === CardList.BlogDetailCardList
    );

    return {
      CardType,
      cardComponents,
      cardListComponents,
      type: $process.sideCardType,
      cardChoices: $process.sideCardChoice,
      cardListChoice: $process.sideCardList,
      sideCardFollow: $process.sideCardFollow,
      shouldStickyRoot,
      isBlogCardListSplit,
      isBlogDetailSplit,
      stickyOffsetStyle,
      blogDataCardConfig,
    };
  },
});
</script>

<style lang="scss" scoped>
.sideCard {
  padding: 20px;
  padding-top: 0;
  .card {
    margin-bottom: 20px;
  }
}

/* PC 端滚动跟随 */
@media screen and (min-width: 801px) {
  .sideCard-follow {
    position: sticky;
    top: 70px;
    /* 覆盖父级 align-items: stretch，否则侧栏被撑满容器高度后 sticky 失效 */
    align-self: flex-start;
  }

  .sticky-group {
    position: sticky;
    top: 70px;
  }

  /* header 收起时，sticky 偏移随之下调，避免侧栏与页面顶部留白过大 */
  .sideCard-follow,
  .sticky-group {
    transition: top 0.5s cubic-bezier(0.4, 0, 0.2, 1);
  }
}
</style>
