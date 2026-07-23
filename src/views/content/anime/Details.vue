<template>
  <div class="details">
    <anime-item class="animeItem" :data="animeData" :key="animeSentry" />
    <word-card class="wordCard" :word="animeData.word" :key="animeSentry" />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, inject, ref } from "vue";
import { useRouter } from "vue-router";
import useProcessControl from "@/composables/useProcessControl";
import { ProcessInterface, ApiObject } from "@/types";
import { CardDirection, CardList, CardType } from "@/constant";
import { codeConfig } from "@/config/program";
import { AnimeItem } from "@/components/business/anime";
import { WordCard } from "@/components/common/card"; 
import utils from "@/utils/helper";

export default defineComponent({
  components: { AnimeItem, WordCard },
  setup() {
    const router = useRouter();
    const $process = inject<ProcessInterface>("$process")!;
    const $api = inject<ApiObject>("$api")!;

    let animeId = ref(router.currentRoute.value.params.id);
    let animeData = ref({
      title: "",
      introduce: "",
      word: ""
    });
    let animeSentry = ref(0);

    async function getAnimeInfo() {
      $api.getAnimeInfo([animeId.value]).then(({code, msg, data}) => {
        if (code == codeConfig.success) {
          if (data.isPrivate) {
            data.word = utils.localDecryptContent(data.word);
          }
          animeData.value = data;
          animeSentry.value++;
        } else {
          $process.tipShow.error(msg);
        }
      })
    }

    onMounted(() => {
      getAnimeInfo();
      useProcessControl(true, {
        direction: CardDirection.row,
        cardType: CardType.CardList,
        cardList: CardList.AnimeCardList,
      });
    });

    return {
      animeId,
      animeData,
      animeSentry
    };
  },
});
</script>

<style lang="scss" scoped>
.details {
  width: 100%;
  .wordCard {
    width: calc(100% - 6px);
    min-height: 100px;
    margin: 0 auto;
    margin-bottom: 15px;
  }
}
</style>