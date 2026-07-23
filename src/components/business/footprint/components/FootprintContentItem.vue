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
import CommonMd from "@/components/common/md/CommonMd.vue";

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

.footprintItem {
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
        border-top: 1px dashed rgba(0, 0, 0, 0.12);
        display: flex;
        justify-content: space-between;
        color: var(--color-assist);
    }
}
</style>