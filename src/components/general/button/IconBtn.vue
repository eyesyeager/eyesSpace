<template>
  <div class="iconBtn">
    <img class="icon" :src="icon">
    <div class="word">{{word}}</div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";

export default defineComponent({
  components: {},
  props: ["icon", "word", "width", "height"],
  setup(props) {

    return {
        icon: props.icon,
        word: props.word,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";

.iconBtn {
    width: 95px;
    height: 35px;
    background: $white;
    border-radius: 8px;
    box-shadow: $shadow-card;
    -webkit-box-shadow: $shadow-card;
    -moz-box-shadow: $shadow-card;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: box-shadow $transition-normal, transform $transition-normal, background $transition-fast;
    user-select: none;
    .icon {
        width: 20px;
        height: 20px;
        display: block;
        margin-right: 4px;
        transition: transform $transition-normal;
    }
    &:hover {
        box-shadow: $shadow-card-hover;
        -webkit-box-shadow: $shadow-card-hover;
        -moz-box-shadow: $shadow-card-hover;
        transform: translateY(-1px);
        .icon {
            transform: scale(1.1);
        }
    }
    &:active {
        transform: translateY(0);
        box-shadow: $shadow-card;
        -webkit-box-shadow: $shadow-card;
        -moz-box-shadow: $shadow-card;
    }
}
</style>