<template>
  <div class="animeItem">
    <div class="cover"></div>
    <div class="content">
        <div class="title">{{data.title}}</div>
        <div class="info">
            <span>时期: {{data.period}}</span>
            <span>类型: {{data.type}}</span>
        </div>
        <div class="introduce">{{data.introduce}}</div>
        <div class="footer">
            <div class="time">{{data.createTime}}</div>
            <div class="data">点击量: {{data.view}}</div>
        </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  components: { },
  props: ["data", "index"],
  setup(props) {
    return {
        data: props.data,
        direction: props.index % 2 ? "row" : "row-reverse",
        cover: "url(" + props.data.cover + ")"
    };
  },
});
</script>

<style lang="scss" scoped>

.animeItem {
  width: calc(100% - 5px);
  min-height: 200px;
  margin: 0 auto;
  margin-bottom: 16px;
  background: var(--color-white);
  border-radius: 8px;
  overflow: hidden;
  box-shadow: var(--shadow-card);
  -webkit-box-shadow: var(--shadow-card);
  -moz-box-shadow: var(--shadow-card);
  color: var(--color-normal);
  display: flex;
  flex-direction: v-bind(direction);
  transition: box-shadow var(--transition-normal), transform var(--transition-normal);

  &:hover {
    box-shadow: var(--shadow-card-hover);
    -webkit-box-shadow: var(--shadow-card-hover);
    -moz-box-shadow: var(--shadow-card-hover);
    transform: translateY(-2px);
    .cover {
        background-size: auto 110%;
    }
  }
  .cover {
    width: 40%;
    background-image: v-bind(cover);
    background-size: auto 100%;
    background-position: center center;
    background-repeat: no-repeat;
    transition: 0.4s ease;
  }
  .content {
    width: 60%;
    padding: 16px;
    .title {
        min-height: 40px;
        font-size: 22px;
        font-weight: 600;
        text-align: center;
        line-height: 30px;
        color: var(--color-title);
    }
    .info span {
        height: 20px;
        line-height: 20px;
        margin-right: 10px;
        font-size: 13px;
        color: var(--color-assist);
    }
    .introduce {
        margin: 10px 0;
        text-indent: 28px;
        line-height: 1.6;
        font-size: 14px;
    }
    .footer {
        height: 20px;
        line-height: 20px;
        display: flex;
        justify-content: space-between;
        font-size: 13px;
        color: var(--color-assist);
    }
  }
}

@media screen and (max-width: 1040px) {
  .animeItem {
    flex-direction: column;
    &:hover {
      .cover {
        background-size: 110% auto;
      }
    }    
    .cover {
      height: 150px;
      width: 100%;
      background-size: 100% auto;
      background-position: center top;
    }
    .content {
      width: 100%;
    }
  }
}
</style>