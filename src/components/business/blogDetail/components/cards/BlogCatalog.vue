<template>
    <standard-card title="博客目录" :icon="icon">
        <div class="blogCatalog">
          <Wait :show="show" :fail="isFail" height="100px">
            <md-atalog v-if="hasHeadings" :editor-id="editorId" :scroll-element="scrollElement" />
            <div v-else class="no-catalog">
              <span class="no-catalog-text">暂无</span>
            </div>
          </Wait>
        </div>
    </standard-card>
</template>

<script lang="ts">
import { defineComponent, computed } from "vue";
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import StandardCard from "@/components/common/card/components/StandardCard.vue";
import blogDetailProcess from "@/components/business/blogDetail/businessTs/blogDetailProcess";
import { blogDetailContext } from "@/components/business/blogDetail/businessTs/blogDetailContext";
import { Wait } from "@/components/common/popup";
import { blogDetailConfig } from "@/components/business/blogDetail/config";
import resource from "@/config/resource";
const MdAtalog = MdEditor.MdCatalog;
const scrollElement = document.documentElement;

export default defineComponent({
  components: { StandardCard, MdEditor, Wait, MdAtalog },
  props: ["content"],
  setup() {
    const hasHeadings = computed(() => {
      const content = blogDetailContext.data.content || "";
      return /^#{1,6}\s/m.test(content);
    });

    return {
      show: blogDetailProcess.cardInitLoad,
      isFail: blogDetailProcess.cardInitFail,
      editorId: blogDetailConfig.editorId,
      scrollElement,
      icon: resource.catalogue,
      hasHeadings,
    };
  },
});
</script>

<style lang="scss" scoped>

::deep.blogCatalog{
  .md-editor-catalog-link {
    padding: 2.5px 0;
    span {
      color: var(--color-normal);
    }
  }
}

.no-catalog {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;

  .no-catalog-text {
    color: var(--color-secondary);
    font-size: 13px;
    letter-spacing: 1px;
  }
}
</style>
