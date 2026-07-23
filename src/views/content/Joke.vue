<template>
  <div class="joke">
    <Wait :show="show" :fail="isFail" height="400px">
      <joke-list :key="jokeSentry" :jokeListData="jokeListData" />
    </Wait>
    <Pagination
      :key="jokeSentry"
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
import { JokeList } from "@/components/business/joke";

export default defineComponent({
  name: "Joke",
  components: { Pagination, Wait, JokeList },
  setup() {
    const $api = inject<ApiObject>("$api")!;
    const $process = inject<ProcessInterface>("$process")!;

    let show = ref(true);
    let isFail = ref(false);
    let page = ref(1);
    let pageSize = ref(20);
    let total = ref(0);
    let jokeListData = ref([]);
    let jokeSentry = ref(0);

    async function getJokeList() {
      $api.getJokeList({page: page.value}).then(({code, msg, data}) => {
        if (code == codeConfig.success) {
          jokeListData.value = data.data;
          total.value = data.total;
          show.value = false;
          jokeSentry.value++;
          goBoth(GoBothType.TopSpeed);
        } else {
          $process.tipShow.error("获取数据失败");
          isFail.value = true;
        }
      });
    }

    function pageChange(target: number) {
      page.value = target;
      getJokeList();
    }

    onBeforeMount(() => {
      getJokeList();
    })

    onActivated(() => {
      useProcessControl(true, {
        direction: CardDirection.row,
        cardType: CardType.CardList,
        cardList: CardList.JokeCardList,
      });
    })

    return {
      show,
      isFail,
      total,
      page,
      pageSize,
      jokeListData,
      jokeSentry,
      pageChange
    };
  },
});
</script>

<style lang="scss" scoped>
.joke {
  width: 100%;
}
</style>