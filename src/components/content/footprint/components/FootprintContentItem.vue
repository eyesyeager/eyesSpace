<template>
    <div class="footprintItem">
        <div class="content">
            <common-md :content="props.content" :mdId="props.id" />
        </div>
        <div class="foot">
            <div class="type">{{ props.type }}</div>
            <div class="time">{{ props.timeFrom }} - {{ props.timeTo }}</div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import CommonMd from "@/components/general/md/CommonMd.vue";

export default defineComponent({
    components: { CommonMd },
    props: {
        id: Number,
        type: String,
        content: String,
        timeFrom: String,
        timeTo: String
    },
    setup(props) {
        return {
            props,
        };
    },
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";

.footprintItem {
    width: calc(100% - 5px);
    margin: 0 auto;
    margin-bottom: 16px;
    padding: 16px 20px;
    background: $white;
    border-radius: 8px;
    box-shadow: $shadow-card;
    -webkit-box-shadow: $shadow-card;
    -moz-box-shadow: $shadow-card;
    color: $normal;
    transition: box-shadow $transition-normal;

    &:hover {
        box-shadow: $shadow-card-hover;
        -webkit-box-shadow: $shadow-card-hover;
        -moz-box-shadow: $shadow-card-hover;
    }

    .content {
        margin-bottom: 10px;
        line-height: 1.8;
    }

    .foot {
        font-size: 13px;
        height: 36px;
        margin-top: 12px;
        padding-top: 8px;
        line-height: 36px;
        border-top: 1px dashed rgba($color: $black, $alpha: 0.12);
        display: flex;
        justify-content: space-between;
        color: $assist;
    }
}
</style>