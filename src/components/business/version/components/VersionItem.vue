<template>
  <div class="versionItem">
    <div class="header">
        {{type + "版本更新 v" + version}}
    </div>
    <div class="content">
      <common-md :content="content" :commentId="commentId" />
    </div>
    <div class="imgList" v-if="picList.length">
      <div class="versionImg" v-for="item in picList" :key="item">
        <Image :imgArray="picList" :url="item" size="97px" />
      </div>
    </div>
    <div class="footer">{{createTime}}</div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Image from "@/components/common/image/Image.vue";
import { versionTypeConvert } from "../config";
import CommonMd from "@/components/common/md/CommonMd.vue";

export default defineComponent({
  components: { Image, CommonMd },
  props: ["data"],
  setup(props) {
    return {
        type: versionTypeConvert[props.data.type],
        version: props.data.version,
        content: props.data.description,
        commentId: (props.data.type + "-" + props.data.version).replace(/\./g, "-"),
        picList: props.data.picList,
        createTime: props.data.createTime
    };
  },
});
</script>

<style lang="scss" scoped>

.versionItem {
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
    box-shadow: var(--shadow-card)-hover;
    -webkit-box-shadow: var(--shadow-card)-hover;
    -moz-box-shadow: var(--shadow-card)-hover;
  }

  .header {
    font-size: 22px;
    font-weight: 600;
    margin-bottom: 12px;
    text-align: center;
    color: var(--color-title);
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
    .versionImg {
    }
  }
  .footer {
    text-align: right;
    font-size: 13px;
    color: var(--color-assist);
    margin-top: 8px;
  }
}
</style>