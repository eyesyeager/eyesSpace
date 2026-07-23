<template>
  <standard-card title="博客标签" :icon="icon">
    <Wait :show="show" :fail="isFail" height="100px">
      <div class="card blogLabelCard">
        <div class="label" :class="'label_' + item.label" v-for="item in labelData" :key="item.label" @click="getBlogList(item.label)">
          <div class="name">{{item.label}}</div>
          <div class="num">{{item.num}}</div>
        </div>
      </div>
    </Wait>
  </standard-card>
</template>

<script lang="ts">
import { defineComponent, ref, inject, onMounted, nextTick } from "vue";
import { ProcessInterface, ApiObject } from "@/types";
import { codeConfig } from "@/config/program";
import { useRouter } from "vue-router";
import StandardCard from "@/components/common/card/components/StandardCard.vue";
import resource from "@/config/resource";
import { Wait } from "@/components/common/popup";

export default defineComponent({
  components: { StandardCard, Wait },
  setup() {
    const router = useRouter();
    const $process = inject<ProcessInterface>("$process")!;
    const $api = inject<ApiObject>("$api")!;

    let labelData = ref();
    let show = ref(true);
    let isFail = ref(false);

    async function getBlogLabel() {
      show.value = true;
      isFail.value = false;
      await $api.getBlogLabel().then(({code, msg, data}) => {
        if(code == codeConfig.success) {
          labelData.value = data;
          show.value = false;
          initActive();
        } else {
          $process.tipShow.error("获取博客标签失败");
          isFail.value = true;
        }
      })
    }

    function getBlogList(label: string) {
      let query: Partial<any> = {};
      query.page = 1;
      if(router.currentRoute.value.query.category) query.category = <string>router.currentRoute.value.query.category;
      if(!document.querySelector(".label_" + label)!.classList.contains("active")) {
        document.querySelectorAll(".label").forEach((v) => {
          v.classList.remove("active");
        })
        document.querySelector(".label_" + label)!.classList.add("active");
        query.label = label;
      } else {
        document.querySelector(".label_" + label)!.classList.remove("active");
      }
      router.push({
        path: "/blog",
        query
      });
    }

    function initActive() {
      nextTick(() => {
        if(router.currentRoute.value.query.label) {
          document.querySelector(".label_" + <string>router.currentRoute.value.query.label)!.classList.add("active");
        }
      })
    }

    onMounted(() => {
      getBlogLabel();
    })
    
    return {
      icon: resource.label,
      labelData,
      show,
      isFail,
      getBlogList
    };
  },
});
</script>

<style lang="scss" scoped>

.blogLabelCard {
  display: flex;
  flex-wrap: wrap;
  color: var(--color-normal);
  gap: 8px;
  .label {
    height: 30px;
    line-height: 20px;
    padding: 5px 10px;
    background: rgba(0, 0, 0, 0.03);
    border-radius: 6px;
    display: flex;
    align-items: center;
    cursor: pointer;
    transition: all var(--transition-normal);
    &:hover {
      color: var(--color-white);
      background: var(--color-accent);
      box-shadow: var(--shadow-card);
      -webkit-box-shadow: var(--shadow-card);
      -moz-box-shadow: var(--shadow-card);
    }
    .name {
      margin-right: 5px;
    }
    .num {
      font-size: 12px;
      opacity: 0.7;
    }
  }
}

.active {
  color: var(--color-white) !important;
  background: rgb(127, 200, 248) !important;
  box-shadow: var(--shadow-card) !important;
  -webkit-box-shadow: var(--shadow-card) !important;
  -moz-box-shadow: var(--shadow-card) !important;
  &:hover {
    background: var(--color-accent-hover) !important;
  }
}
</style>