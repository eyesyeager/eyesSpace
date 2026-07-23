<template>
  <div class="tip">
    <transition-group name="tipAnimate">
      <div class="tipItem" v-for="item in msgList" :key="item">
        <img :src="tipType[item.type]" />
        <div class="word">{{ item.msg }}</div>
      </div>
    </transition-group>
  </div>
</template>

<script lang="ts">
import { defineComponent, watch, inject, ref, Ref, UnwrapNestedRefs } from "vue";
import { ProcessInterface } from "@/types/store/process";
import { tipType } from "@/config/site";

export default defineComponent({
  components: {},
  setup() {
    const $process = inject<ProcessInterface>("$process")!;

    let msgList: Ref<Array<UnwrapNestedRefs<{ msg: string; type: number }>>> = ref([]);

    watch(
      () => $process.tipSentry.value,
      () => {
        msgList.value.push({
          msg: $process.tipList.msg,
          type: $process.tipList.type,
        });
        setTimeout(() => {
          msgList.value.splice(0, 1);
        }, 3000);
      }
    );

    return {
      msgList,
      tipType,
    };
  },
});
</script>

<style lang="scss" scoped>

.tipAnimate-enter-active {
  animation: flipInX 0.8s;
}
.tipAnimate-leave-active {
  animation: flipOutX 0.8s;
}

.tip {
  position: fixed;
  z-index: 1000;
  top: 80px;
  left: 50%;
  transform: translateX(-50%);
  pointer-events: none;
  .tipItem {
    min-width: 150px;
    max-width: 320px;
    min-height: 36px;
    background: rgba(255, 255, 255, 0.95);
    backdrop-filter: saturate(180%) blur(20px);
    -webkit-backdrop-filter: saturate(180%) blur(20px);
    box-shadow: var(--shadow-lg);
    -webkit-box-shadow: var(--shadow-lg);
    -moz-box-shadow: var(--shadow-lg);
    border-radius: 8px;
    padding: 10px 16px;
    margin-bottom: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: var(--color-normal);
    font-size: 14px;
    img {
      width: 20px;
      height: 20px;
      display: block;
      margin-right: 8px;
    }
  }
}
</style>
