<template>
  <div class="jokeList">
    <div class="jokeItem" v-for="item in jokeListData" :key="item">
      <Image :imgArray="item" :url="item[0]" size="130px"/>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Image from "@/components/general/image/Image.vue";

export default defineComponent({
  props: ["jokeListData"],
  components: { Image },
  setup(props) {
    return {
      jokeListData: props.jokeListData
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";

.jokeList {
  width: 100%;
  min-width: 335px;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  .jokeItem {
    width: 130px;
    height: 130px;
    margin: 0 10px 20px 10px;
  }
}
</style>