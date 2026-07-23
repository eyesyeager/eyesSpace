<template>
    <div class="footprintHead">
        <div class="divider"></div>
        <div class="content">{{ props.country }} - {{ props.province }} - {{ props.city }}</div>
        <div class="divider"></div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
    props: ["country", "province", "city"],
    setup(props) {
        return {
            props
        };
    },
});
</script>

<style lang="scss" scoped>

.footprintHead {
    display: flex;
    align-items: center;

    .divider {
        height: 1px;
        background-color: rgba(102, 102, 102, 0.5);
        flex: 1;
    }

    .content {
        height: 24px;
        font-size: 24px;
        color: var(--color-normal);
        margin: 0 20px;
    }
}
</style>