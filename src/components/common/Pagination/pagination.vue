<template>
  <div class="pagination">
    <div class="pager prev" @click="pageChange(currentPage - 1)">&#60;</div>
    <template v-for="item in leftPage" :key="item">
        <div 
            v-if="typeof item == 'string' || item > 0"
            :class="{current: currentPage == item, number: typeof item == 'number', ellipsis: typeof item == 'string'}" 
            @click="pageChange(item)"
        >
            {{item}}
        </div>
    </template>
    <template v-for="item in rightPage" :key="item">
        <div 
            v-if="typeof item == 'string' || item <= pages"
            :class="{current: currentPage == item, number: typeof item == 'number', ellipsis: typeof item == 'string'}" 
            @click="pageChange(item)"
        >
            {{item}}
        </div>
    </template>
    <div class="pager next" @click="pageChange(currentPage + 1)">&#62;</div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";

export default defineComponent({
  components: { },
  emits: ["pageChange"],
  props: ["total", "size", "initPage"],
  setup(props, ctx) {
    let width = ref("200px");
    let total = props.total || 0;
    let size = props.size || 6;
    let pages = Math.ceil(total / size);
    let currentPage = ref(props.initPage || 1);
    let prevCursor = ref("default");
    let nextCursor = ref("default");
    let leftPage = ref<(number | string)[]>([]);
    let rightPage = ref<(number | string)[]>([]);

    function pageChange(target: number | string) {
        if(typeof target == 'string' || target < 1 || target > pages) return;
        currentPage.value = target;
        pageListChange(target);
        pagerCursor(target)
        ctx.emit("pageChange", target);
    }

    function pagerCursor(target: number) {
        prevCursor.value = target == 1 ? "default" : "pointer";
        nextCursor.value = target == pages ? "default" : "pointer";
    }

    function pageListChange(target: number) {
        leftPage.value = target - 1 > 3
                       ? leftPage.value = [1, "…", target - 2, target - 1, target]
                       : leftPage.value = [target - 3, target - 2, target - 1, target];
        rightPage.value = pages - target > 3
                        ? [target + 1, target + 2, "…", pages]
                        : [target + 1, target + 2, target + 3];
    }

    onMounted(() => {
        if(pages > 5) {
            width.value = "300px";
        }
        pageListChange(currentPage.value);
        pagerCursor(currentPage.value);
    })

    return {
        width,
        pages,
        currentPage,
        prevCursor,
        nextCursor,
        leftPage,
        rightPage,
        pageChange
    };
  },
});
</script>

<style lang="scss" scoped>

.pagination {
    width: v-bind(width);
    margin: 20px auto;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 4px;
    color: var(--color-normal);
    font-size: 14px;
    .pager {
        width: 32px;
        height: 32px;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 6px;
        cursor: pointer;
        transition: background var(--transition-fast), color var(--transition-fast);
        &:hover {
            background: rgba(127, 200, 248, 0.08);
            color: var(--color-accent);
        }
    }
    .prev {
        cursor: v-bind(prevCursor);
    }
    .next {
        cursor: v-bind(nextCursor);
    }
    .number {
        width: 32px;
        height: 32px;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 6px;
        cursor: pointer;
        transition: background var(--transition-fast), color var(--transition-fast);
        &:hover {
            background: rgba(127, 200, 248, 0.08);
            color: var(--color-accent);
        }
    }
}

.current {
    background: rgba(107, 158, 255, 0.1) !important;
    color: var(--color-primary) !important;
    font-weight: 600;
    cursor: default !important;
    border-radius: 6px;
}

.ellipsis {
    width: 32px;
    height: 32px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: default !important;
}
</style>