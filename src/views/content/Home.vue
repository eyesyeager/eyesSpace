<template>
    <div class="home">
        <div class="content">
            <div class="avatar" :style="{ backgroundImage: 'url(' + context.avatar + ')' }"></div>
            <div class="words">
                <p class="name">{{ context.name }}</p>
                <p class="motto">{{ context.motto }}</p>
            </div>
            <div class="link">
                <template v-for="item in headerConfig" :key="item.word">
                    <div class="option" v-if="!item.children">
                        <span @click="pageJump(item.path)">{{ item.word }}</span>
                    </div>
                    <div class="option" v-for="child in item.children" :key="child.word" v-else>
                        <span @click="pageJump(child.path)">{{ child.word }}</span>
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import {
    defineComponent,
    inject,
    onActivated,
} from "vue";
import { ContextInterface } from "@/types";
import useProcessControl from "@/composables/useProcessControl";
import { useRouter } from "vue-router";
import { headerConfig } from "@/config/site";

export default defineComponent({
    name: "Home",
    components: {},
    setup() {
        const router = useRouter();
        const $context = inject<ContextInterface>("$context")!;

        let context = {
            avatar: $context.data.ownerAvatar,
            name: $context.data.ownerName,
            motto: $context.data.ownerMotto,
        };

        function pageJump(path: string) {
            router.push(path);
        }


        onActivated(() => {
            useProcessControl(false, false, false);
        });

        return {
            context,
            headerConfig,
            pageJump
        };
    },
});
</script>

<style lang="scss" scoped>

.home {
    width: 100%;
    min-height: 100vh;

    .content {
        width: 370px;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);

        .avatar {
            width: 110px;
            height: 110px;
            background-size: 100% 100%;
            border-radius: 50%;
            margin: 0px auto;
            margin-bottom: 20px;
            box-shadow: var(--shadow-avatar);
            -webkit-box-shadow: var(--shadow-avatar);
            -moz-box-shadow: var(--shadow-avatar);
            transition: transform var(--transition-slow), box-shadow var(--transition-slow);
            &:hover {
                transform: scale(1.05);
                box-shadow: var(--shadow-card-hover);
                -webkit-box-shadow: var(--shadow-card-hover);
                -moz-box-shadow: var(--shadow-card-hover);
            }
        }

        .words {
            margin-bottom: 20px;

            .name {
                font-size: 22px;
                font-weight: 600;
                color: var(--color-title);
                text-align: center;
                margin-bottom: 10px;
            }

            .motto {
                color: var(--color-normal);
                text-align: center;
                font-size: 14px;
                line-height: 1.6;
            }
        }

        .link {
            color: var(--color-normal);
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 4px;
            margin: 0 40px;
            margin-top: 30px;

            .option {
                text-align: center;

                span {
                    display: inline-block;
                    padding: 6px 14px;
                    border-radius: 6px;
                    cursor: pointer;
                    font-size: 14px;
                    transition: color var(--transition-fast), background var(--transition-fast);

                    &:hover {
                        color: var(--color-accent);
                        background: rgba(127, 200, 248, 0.08);
                    }
                }
            }
        }
    }
}
</style>