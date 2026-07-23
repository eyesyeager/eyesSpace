<template>
  <div class="details">
    <head-meta />
    <md-editor />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, inject, ref } from "vue";
import { useRouter } from "vue-router";
import useProcessControl from "@/composables/useProcessControl";
import { ProcessInterface, ApiObject } from "@/types";
import { CardDirection, CardList, CardType } from "@/constant";
import { codeConfig } from "@/config/program";
import { MdEditor, HeadMeta } from "@/components/business/blogDetail";
import { goBoth, GoBothType } from "@/composables/useGoBoth";
import { blogDetailContext } from "@/components/business/blogDetail/businessTs/blogDetailContext";
import blogDetailProcess from "@/components/business/blogDetail/businessTs/blogDetailProcess";
import utils from "@/utils/helper";

export default defineComponent({
  components: { MdEditor, HeadMeta },
  setup() {
    const router = useRouter();
    const $process = inject<ProcessInterface>("$process")!;
    const $api = inject<ApiObject>("$api")!;
    let blogId = ref(router.currentRoute.value.params.id);

    async function getBlogInfo() {
      blogDetailProcess.cardInitLoad.value = true;
      blogDetailProcess.cardInitFail.value = false;
      await $api.getBlogInfo([blogId.value]).then(({code, msg, data}) => {
        if(code == codeConfig.success) {
          if (data.isPrivate) {
            data.content = utils.localDecryptContent(data.content);
          }
          blogDetailContext.init(data);
          blogDetailProcess.cardInitLoad.value = false;
        } else {
          $process.tipShow.error(msg);
          blogDetailProcess.cardInitFail.value = true;
        }
      });
    }

    onMounted(() => {
      goBoth(GoBothType.TopSpeed);
      getBlogInfo();
      useProcessControl(true, {
        direction: CardDirection.row,
        cardType: CardType.CardList,
        cardList: CardList.BlogDetailCardList,
        follow: true,
        followIndex: 2,
      }, true);
    });

    return {
      blogId
    };
  },
});
</script>

<style lang="scss" scoped>
.details {
  width: 100%;
}
</style>