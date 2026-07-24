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

.iconBtn {
    width: 95px;
    height: 35px;
    background: var(--color-white);
    border-radius: 8px;
    box-shadow: var(--shadow-card);
    -webkit-box-shadow: var(--shadow-card);
    -moz-box-shadow: var(--shadow-card);
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: box-shadow var(--transition-normal), transform var(--transition-normal), background var(--transition-fast);
    user-select: none;
    .icon {
        width: 20px;
        height: 20px;
        display: block;
        margin-right: 4px;
        transition: transform var(--transition-normal);
    }
    &:hover {
        box-shadow: var(--shadow-card-hover);
        -webkit-box-shadow: var(--shadow-card-hover);
        -moz-box-shadow: var(--shadow-card-hover);
        transform: translateY(-1px);
        .icon {
            transform: scale(1.1);
        }
    }
    &:active {
        transform: translateY(0);
        box-shadow: var(--shadow-card);
        -webkit-box-shadow: var(--shadow-card);
        -moz-box-shadow: var(--shadow-card);
    }
}
</style>