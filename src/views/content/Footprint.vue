<template>
    <div class="footprint" id="footprint"></div>
</template>

<script lang="ts">
import { defineComponent, onActivated, ref, inject, onMounted, onUnmounted } from "vue";
import useProcessControl from "@/composables/useProcessControl";
import { ApiObject, ProcessInterface } from "@/types";
import { codeConfig, contextConfig } from "@/config/program";
import { useRouter } from "vue-router";
import L from 'leaflet'
import 'leaflet/dist/leaflet.css'

// 修复 Leaflet 默认 marker 图标在 Vite 打包后的路径问题
import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png'
import markerIcon from 'leaflet/dist/images/marker-icon.png'
import markerShadow from 'leaflet/dist/images/marker-shadow.png'

delete (L.Icon.Default.prototype as any)._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: markerIcon2x,
    iconUrl: markerIcon,
    shadowUrl: markerShadow,
});

export default defineComponent({
    name: "Footprint",
    components: {},
    setup() {
        const $api = inject<ApiObject>("$api")!;
        const $process = inject<ProcessInterface>("$process")!;
        const router = useRouter();
        const map = ref<any>();

        function initMap() {
            $api.getContextItem([contextConfig.footprintInit]).then(({ code, msg, data }) => {
                if (code == codeConfig.success) {
                    // 初始化地图
                    const config = JSON.parse(data.content);
                    map.value = L.map("footprint", JSON.parse(config.mapInit));
                    L.tileLayer(
                        config.layer,
                        {
                            attribution: config.attribution,
                        }
                    ).addTo(map.value);

                    // 获取并填充足迹信息
                    getFootprint();
                } else {
                    $process.tipShow.error("地图初始化失败！" + msg);
                }
            })
        }

        function getFootprint() {
            $api.getFootprintList().then(({ code, msg, data }) => {
                if (code == codeConfig.success) {
                    data.forEach((element: any) => {
                        addMarker(element.id, element.city, element.latitude, element.longitude);
                    });
                } else {
                    $process.tipShow.error("获取足迹信息失败！" + msg);
                }
            })
        }

        function addMarker(id: number, city: string, latitude: number, longitude: number) {
            const labelMarker = L.marker([latitude, longitude], {
                zIndexOffset: 0,
                riseOnHover: true,
                title: city,
            });
            labelMarker.on("click", () => {
                window.open(router.resolve(`/footprint/details/${id}`).href, "_blank");
            });
            labelMarker.addTo(map.value);
        }

        onActivated(() => {
            useProcessControl(false, false, false);
            // Keep-alive 缓存恢复后，更新地图尺寸以防止瓦片错位
            map.value?.invalidateSize();
        });

        onMounted(() => {
            initMap();
        })

        onUnmounted(() => {
            if (map.value) {
                map.value.remove();
                map.value = null;
            }
        });
    },
});
</script>

<style lang="scss" scoped>
#footprint {
    position: fixed;
    top: 0;
    left: 0;
    margin: 0;
    width: 100vw;
    height: 100vh;
}
</style>