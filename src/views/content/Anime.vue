<template>
  <div class="anime">
    <Wait :show="show" :fail="isFail" height="400px">
      <anime-list :key="animeSentry" :animeListData="animeListData" />
    </Wait>
    <Pagination
      :key="animeSentry"
      :total="total"
      :size="pageSize"
      :initPage="page"
      @pageChange="pageChange"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent, inject, onActivated, ref, onBeforeMount } from 'vue';
import { ProcessInterface, ApiObject } from "@/types";
import useProcessControl from "@/composables/useProcessControl";
import { CardDirection, CardType, CardList } from "@/constant";
import { codeConfig } from "@/config/program";
import { goBoth, GoBothType } from "@/composables/useGoBoth";
import { Wait } from "@/components/common/popup";
import Pagination from "@/components/common/Pagination/pagination.vue";
import { AnimeList } from "@/components/business/anime";

export default defineComponent({
  name: "Anime",
  components: { Pagination, Wait, AnimeList },
  setup() {
    const $api = inject<ApiObject>("$api")!;
    const $process = inject<ProcessInterface>("$process")!;

    let show = ref(true);
    let isFail = ref(false);
    let page = ref(1);
    let pageSize = ref(6);
    let total = ref(0);
    let animeSentry = ref(0);
    let animeListData = ref([]);

    async function getAnimeList() {
      $api.getAnimeList({page: page.value}).then(({code, data}) => {
        if (code == codeConfig.success) {
          animeListData.value = data.data;
          total.value = data.total;
          show.value = false;
          animeSentry.value++;
          goBoth(GoBothType.TopSpeed);
        } else {
          $process.tipShow.error("获取数据失败");
          isFail.value = true;
        }
      })
    }

    function pageChange(target: number) {
      page.value = target;
      getAnimeList();
    }

    onBeforeMount(() => {
      getAnimeList();
    })

    onActivated(() => {
      useProcessControl(true, {
        direction: CardDirection.row,
        cardType: CardType.CardList,
        cardList: CardList.AnimeCardList,
        follow: true,
        followIndex: 0,
      });
    })

    return {
      show,
      isFail,
      total,
      page,
      pageSize,
      animeSentry,
      animeListData,
      pageChange
    };
  },
});
</script>

<style lang="scss" scoped>
.anime {
  width: 100%;
}
</style>