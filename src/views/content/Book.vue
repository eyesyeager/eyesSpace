<template>
    <div class="book">
        <Wait :show="show" :fail="isFail" height="400px">
            <book-list :key="bookSentry" :bookListData="bookListData" />
        </Wait>
        <Pagination class="pagination" :key="bookSentry" :total="total" :size="pageSize" :initPage="page" @pageChange="pageChange" />
    </div>
</template>

<script lang="ts">
import { defineComponent, inject, onActivated, ref, onBeforeMount } from 'vue';
import { ProcessInterface, ApiObject } from "@/types";
import useProcessControl from "@/composables/useProcessControl";
import { CardDirection, CardType, CardList, Cards } from "@/constant";
import { codeConfig } from "@/config/program";
import { goBoth, GoBothType } from "@/composables/useGoBoth";
import { Wait } from "@/components/common/popup";
import Pagination from "@/components/common/Pagination/pagination.vue";
import { BookList } from "@/components/business/book/index";

export default defineComponent({
    name: "Book",
    components: { Pagination, Wait, BookList },
    setup() {
        const $api = inject<ApiObject>("$api")!;
        const $process = inject<ProcessInterface>("$process")!;

        let show = ref(true);
        let isFail = ref(false);
        let page = ref(1);
        let pageSize = ref(6);
        let total = ref(0);
        let bookListData = ref([]);
        let bookSentry = ref(0);

        async function getBookList() {
            $api.getBookList({page: page.value}).then(({ code, msg, data }) => {
                if (code == codeConfig.success) {
                    bookListData.value = data.data;
                    total.value = data.total;
                    show.value = false;
                    bookSentry.value++;
                    goBoth(GoBothType.TopSpeed);
                } else {
                    $process.tipShow.error(msg);
                    isFail.value = true;
                }
            });
        }

        function pageChange(target: number) {
            page.value = target;
            getBookList();
        }

        onBeforeMount(() => {
            getBookList();
        })

        onActivated(() => {
            useProcessControl(true, {
                direction: CardDirection.row,
                cardType: CardType.CardList,
                cardList: CardList.BookCardList,
            });
        })

        return {
            show,
            isFail,
            total,
            page,
            pageSize,
            bookListData,
            bookSentry,
            pageChange
        };
    },
});
</script>

<style lang="scss" scoped>
.book {
    width: 100%;
    .pagination {
        margin: 20px auto;
    }
}
</style>