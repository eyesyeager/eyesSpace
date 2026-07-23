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
@import "@/assets/scss/index.scss";

.wordCard {
    min-height: 100px;
    padding: 16px;
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

    .title {
        height: 30px;
        line-height: 30px;
        text-align: center;
        font-size: 20px;
        font-weight: 600;
        color: $title;
        margin-bottom: 8px;
    }
}

:deep.md {
    .md-editor-content .md-editor-preview-wrapper {
        padding: 0;
    }
}
</style>