<template>
    <div class="blog">
        <Wait :show="show" :fail="isFail" height="400px">
            <blog-list :key="blogSentry" />
        </Wait>
        <Pagination
            :key="blogSentry"
            :total="total"
            :size="pageSize"
            :initPage="page"
            @pageChange="pageChange"
        />
    </div>
</template>

<script lang="ts">
import {
    defineComponent,
    inject,
    onActivated,
    onBeforeMount,
    ref,
    watch,
} from "vue";
import { ProcessInterface, ApiObject } from "@/types";
import useProcessControl from "@/composables/useProcessControl";
import { useRouter } from "vue-router";
import { CardDirection, CardType, CardList } from "@/constant";
import { codeConfig } from "@/config/program";
import { Wait } from "@/components/common/popup";
import { BlogList } from "@/components/business/blog";
import { blogContext } from "@/components/business/blog/businessTs/blogContext";
import Pagination from "@/components/common/Pagination/pagination.vue";
import { goBoth, GoBothType } from "@/composables/useGoBoth";

export default defineComponent({
    name: "Blog",
    components: { BlogList, Wait, Pagination },
    setup() {
        const router = useRouter();
        const $process = inject<ProcessInterface>("$process")!;
        const $api = inject<ApiObject>("$api")!;

        let page = ref(1);
        let total = ref(1);
        let pageSize = ref(10);
        let category = ref<string>();
        let label = ref<string>();

        let show = ref(true);
        let isFail = ref(false);
        let blogSentry = ref(0);

        async function getBlogList() {
            return await $api
                .getBlogList({
                    page: page.value,
                    pageSize: pageSize.value,
                    category: category.value,
                    label: label.value,
                })
                .then(({ code, data }) => {
                    if (code == codeConfig.success) {
                        blogContext.init(data.data);
                        total.value = data.total;
                        blogSentry.value++;
                        goBoth(GoBothType.TopSpeed);
                        return true;
                    } else {
                        $process.tipShow.error("博客列表获取失败");
                        return false;
                    }
                });
        }

        async function initData() {
            page.value =
                Number(<string>router.currentRoute.value.query.page) || 1;
            category.value = <string>router.currentRoute.value.query.category;
            label.value = <string>router.currentRoute.value.query.label;

            await getBlogList().then((flag) => {
                show.value = isFail.value = !flag;
            });
        }

        async function pageChange(target: number) {
            category.value = <string>router.currentRoute.value.query.category;
            label.value = <string>router.currentRoute.value.query.label;
            let query: any = { page: target };
            if (category.value) {
                query.category = category.value;
            }
            if (label.value) {
                query.label = label.value;
            }
            router.push({
                path: "/blog",
                query,
            });
        }

        watch(
            () => router.currentRoute.value.query,
            () => {
                if (router.currentRoute.value.path != "/blog") return;
                page.value = Number(router.currentRoute.value.query.page) || 1;
                category.value = <string>(
                    router.currentRoute.value.query.category
                );
                label.value = <string>router.currentRoute.value.query.label;
                getBlogList();
            }
        );

        onActivated(() => {
            useProcessControl(true, {
                direction: CardDirection.row,
                cardType: CardType.CardList,
                cardList: CardList.BlogCardList,
                follow: true,
                followIndex: 2,
            });
        });

        onBeforeMount(() => {
            initData();
        });

        return {
            show,
            isFail,
            total,
            page,
            pageSize,
            blogSentry,
            pageChange,
        };
    },
});
</script>

<style lang="scss" scoped>
.blog {
    width: 100%;
}
</style>