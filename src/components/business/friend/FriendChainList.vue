<template>
    <div class="friendChainList">
        <div
            class="chainItem"
            v-for="item in dataList"
            :key="item.id"
            @click="jump(item.address)"
        >
            <div
                class="cover"
                :style="{ backgroundImage: 'url(' + item.avatar + ')' }"
            ></div>
            <div class="box">
                <div class="name">{{ item.name }}</div>
                <div class="introduce">{{ item.introduce }}</div>
                <div class="status">{{ statusConvert[item.status] }}</div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { statusConvert } from "./config";

export default defineComponent({
    props: ["data"],
    setup(props) {
        function jump(address: string) {
            window.open(address);
        }

        return {
            dataList: props.data,
            statusConvert,
            jump,
        };
    },
});
</script>

<style lang="scss" scoped>

.friendChainList {
    display: flex;
    flex-wrap: wrap;
    color: var(--color-normal);
    .chainItem {
        width: 42%;
        height: 120px;
        margin: 20px 4%;
        display: flex;
        background: var(--color-white);
        border-radius: 8px;
        overflow: hidden;
        box-shadow: var(--shadow-card);
        -webkit-box-shadow: var(--shadow-card);
        -moz-box-shadow: var(--shadow-card);
        cursor: pointer;
        transition: box-shadow var(--transition-normal), transform var(--transition-normal);
        &:hover {
            box-shadow: var(--shadow-card-hover);
            -webkit-box-shadow: var(--shadow-card-hover);
            -moz-box-shadow: var(--shadow-card-hover);
            transform: translateY(-2px);
        }
        .cover {
            border-radius: 8px 0 0 8px;
            background-size: cover;
            background-position: center;
            flex: 0 0 120px;
        }
        .box {
            padding: 12px;
            flex: 1;
            .name {
                height: 30px;
                line-height: 30px;
                font-size: 18px;
                font-weight: 600;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                color: var(--color-title);
            }
            .introduce {
                height: 45px;
                overflow: hidden;
                font-size: 13px;
                line-height: 1.6;
                color: var(--color-normal);
            }
            .status {
                height: 20px;
                line-height: 20px;
                text-align: right;
                font-size: 12px;
                color: var(--color-assist);
            }
        }
    }
}

@media screen and (max-width: 1040px) {
    .chainItem {
        width: calc(100% - 4px) !important;
        margin: 10px 2px !important;
    }
}
</style>