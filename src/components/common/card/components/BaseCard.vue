<template>
  <div class="card baseCard">
    <slot />
  </div>
</template>

<style lang="scss" scoped>

.baseCard {
    width: 100%;
    padding: 20px;
    background: var(--color-white);
    border-radius: 8px;
    box-shadow: var(--shadow-card);
    -webkit-box-shadow: var(--shadow-card);
    -moz-box-shadow: var(--shadow-card);
    transition: box-shadow var(--transition-normal), transform var(--transition-normal);
    will-change: box-shadow, transform;

    &:hover {
        box-shadow: var(--shadow-card)-hover;
        -webkit-box-shadow: var(--shadow-card)-hover;
        -moz-box-shadow: var(--shadow-card)-hover;
        transform: translateY(-1px);
    }
}
</style>
