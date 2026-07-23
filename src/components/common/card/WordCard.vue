<template>
    <div class="wordCard">
        <div class="title">站长说</div>
        <md-editor class="md" v-model="word" editor-id="id" preview-theme="vuepress" previewOnly showCodeRowNumber />
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';

export default defineComponent({
    components: { MdEditor },
    props: ["word"],
    setup(props) {
        return {
            word: props.word,
        };
    },
});
</script>

<style lang="scss" scoped>

.wordCard {
    min-height: 100px;
    padding: 16px;
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

    .title {
        height: 30px;
        line-height: 30px;
        text-align: center;
        font-size: 20px;
        font-weight: 600;
        color: var(--color-title);
        margin-bottom: 8px;
    }
}

:deep.md {
    .md-editor-content .md-editor-preview-wrapper {
        padding: 0;
    }
}
</style>