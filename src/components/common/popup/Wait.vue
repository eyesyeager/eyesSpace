<template>
  <div class="wait">
    <div class="mask" v-if="waitSwitch">
      <div :class="{ circle: waitTypeSwitch, error: !waitTypeSwitch }"></div>
    </div>
    <slot v-else />
  </div>
</template>

<script lang="ts">
import { defineComponent, inject, watch, ref } from "vue";
import Resource from "@/config/resource";

export default defineComponent({
  components: {},
  props: {
    show: Boolean,
    fail: Boolean,
    width: String,
    height: String,
    color: String,
    size: String,
    failColor: String,
    failSize: String
  },
  emits: ["callback"],
  setup(props, ctx) {
    const defaultColor = "rgba(0, 0, 0, 0)";
    const defaultSize = "50px";

    let waitSwitch = ref(true);
    let waitTypeSwitch = ref(true);
    let backColor = ref("");
    let size = ref("");

    watch(
      () => props.show,
      (value) => {
        waitSwitch.value = value;
        if(!value) ctx.emit("callback");
      },
      { immediate: true }
    )

    watch(
      () => props.fail,
      (value) => {
        waitTypeSwitch.value = !value;
        if(value) {
          backColor.value = props.failColor || defaultColor;
          size.value = props.failSize || defaultSize;
        } else {
          backColor.value = props.color || defaultColor;
          size.value = props.size || defaultSize;
        }
      },
      { immediate: true }
    )

    return {
      waitSwitch,
      waitTypeSwitch,
      backColor,
      size,
      maskWidth: props.width || "100%",
      maskHeight: props.height || "100%",
      failImg: "url(" + Resource.fail + ")"
    };
  },
});
</script>

<style lang="scss" scoped>

.mask {
  width: v-bind(maskWidth);
  height: v-bind(maskHeight);
  background: v-bind(backColor);
  position: relative;
  .circle, .error {
    width: v-bind(size);
    height: v-bind(size);
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
  .circle {
    border: 2px solid rgba(64, 158, 255, 0.15);
    border-top-color: #409eff;
    border-radius: 50%;
    animation: circleSpin 0.8s linear infinite;
  }
  .error {
    background-image: v-bind(failImg);
    background-size: 100% 100%;
  }
}

@keyframes circleSpin {
  from {
    transform: translate(-50%, -50%) rotate(0deg);
  }
  to {
    transform: translate(-50%, -50%) rotate(360deg);
  }
}
</style>
