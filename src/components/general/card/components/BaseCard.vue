<template>
  <div class="card baseCard">
    <slot />
  </div>
</template>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";

.baseCard {
    width: 100%;
    padding: 20px;
    background: $white;
    border-radius: 8px;
    box-shadow: $shadow-card;
    -webkit-box-shadow: $shadow-card;
    -moz-box-shadow: $shadow-card;
    transition: box-shadow $transition-normal, transform $transition-normal;
    will-change: box-shadow, transform;

    &:hover {
        box-shadow: $shadow-card-hover;
        -webkit-box-shadow: $shadow-card-hover;
        -moz-box-shadow: $shadow-card-hover;
        transform: translateY(-1px);
    }
}
</style>
