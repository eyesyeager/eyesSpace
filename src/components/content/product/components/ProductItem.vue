<template>
    <div class="productItem">
        <div class="name">{{ data.name }}</div>
        <div class="meta">
            <span>{{ data.type }}</span>
        </div>
        <div class="introduce"><common-md :mdId="data.title" :content="data.introduce" /></div>
        <div class="footer">
            <div class="time">{{ data.createTime }}</div>
            <div class="url">
                <div class="target codeUrl" @click="jumpPage(data.codeUrl)" v-if="data.codeUrl">项目</div>
                <div class="target viewUrl" @click="jumpPage(data.viewUrl)" v-if="data.viewUrl">体验</div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import CommonMd from "@/components/general/md/CommonMd.vue";

export default defineComponent({
    components: { CommonMd },
    props: ["data"],
    setup(props) {
        function jumpPage(url: string) {
            window.open(url, "_blank");
        }

        return {
            data: props.data,
            jumpPage
        };
    },
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";

.productItem {
    background: $white;
    border-radius: 8px;
    box-shadow: $shadow-card;
    -webkit-box-shadow: $shadow-card;
    -moz-box-shadow: $shadow-card;
    color: $normal;
    padding: 16px 20px;
    display: flex;
    flex-direction: column;
    transition: box-shadow $transition-normal, transform $transition-normal;

    &:hover {
        box-shadow: $shadow-card-hover;
        -webkit-box-shadow: $shadow-card-hover;
        -moz-box-shadow: $shadow-card-hover;
        transform: translateY(-2px);
    }

    .name {
        font-size: 20px;
        font-weight: 600;
        margin: 4px 0;
        color: $title;
    }

    .meta {
        text-align: right;
        font-size: 13px;
        color: $assist;
        margin-bottom: 8px;
    }

    .introduce {
        line-height: 1.6;
        flex: 1;
        font-size: 14px;
    }

    .footer {
        height: 20px;
        line-height: 20px;
        display: flex;
        justify-content: space-between;
        margin-top: 12px;
        font-size: 13px;
        color: $assist;

        .url {
            display: flex;
            justify-content: space-between;

            .target {
                margin-left: 10px;
                cursor: pointer;
                transition: color $transition-fast;
                &:hover {
                    color: $accent;
                }
            }
        }
    }
}
</style>