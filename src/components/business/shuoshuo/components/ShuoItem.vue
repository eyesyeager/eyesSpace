<template>
  <div class="shuoItem">
    <div class="content">
      <common-md :content="content" :mdId="props.id" />
    </div>
    <div class="imgList" v-if="props.picList?.length">
      <div class="shuoImg" v-for="item in props.picList" :key="item">
        <Image :imgArray="props.picList" :url="item" size="97px" />
      </div>
    </div>
    <div class="foot">
      <div class="time">{{props.createTime}}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Image from "@/components/common/image/Image.vue";
import CommonMd from "@/components/common/md/CommonMd.vue";
import utils from "@/utils/helper";

export default defineComponent({
  components: { Image, CommonMd },
  props: {
    id: Number,
    content: String,
    picList: Array<string>,
    isPrivate: Boolean,
    createTime: String
  },

  setup(props) {
    return {
      props,
      content: props.isPrivate ? utils.localDecryptContent(props.content!) : props.content,
    };
  },
});
</script>

<style lang="scss" scoped>

.shuoItem {
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
  transition: box-shadow var(--transition-normal);

  &:hover {
    box-shadow: var(--shadow-card-hover);
    -webkit-box-shadow: var(--shadow-card-hover);
    -moz-box-shadow: var(--shadow-card-hover);
  }

  .content {
    margin-bottom: 10px;
    line-height: 1.8;
  }
  .imgList {
    display: flex;
    justify-content: start;
    flex-wrap: wrap;
    gap: 6px;
    .shuoImg {
    }
  }
  .foot {
    font-size: 13px;
    height: 36px;
    margin-top: 12px;
    padding-top: 8px;
    line-height: 36px;
    text-align: right;
    color: var(--color-assist);
    border-top: 1px dashed rgba(0, 0, 0, 0.12);
  }
}
</style>