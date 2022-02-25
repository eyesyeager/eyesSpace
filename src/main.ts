import { createApp } from 'vue'
import App from './App.vue'
import 'lib-flexible/flexible.js'

/**
 * 全局样式
 */
import "@/assets/scss/theme/default/global.scss";

/**
 * 插件注册
 */
import { $router, $service, $context, $user, $wait, $ext, $utils } from './plugins';
const plugins = [$router, $service, $context, $user, $wait, $ext, $utils];

const app = createApp(App)
plugins.forEach(plugin => {
    app.use(plugin);
});

app.mount('#app')