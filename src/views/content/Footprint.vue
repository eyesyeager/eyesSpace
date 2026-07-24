<template>
    <div class="footprint" id="footprint"></div>
</template>

<script lang="ts">
import { defineComponent, onActivated, ref, inject, onMounted, onUnmounted } from "vue";
import useProcessControl from "@/composables/useProcessControl";
import { ApiObject, ProcessInterface } from "@/types";
import { codeConfig, contextConfig } from "@/config/program";
import { useRouter } from "vue-router";
import * as maplibregl from 'maplibre-gl';
import 'maplibre-gl/dist/maplibre-gl.css';

export default defineComponent({
    name: "Footprint",
    setup() {
        const $api = inject<ApiObject>("$api")!;
        const $process = inject<ProcessInterface>("$process")!;
        const router = useRouter();
        const map = ref<any>(null);

        function initMap() {
            $api.getContextItem([contextConfig.footprintInit]).then(({ code, msg, data }) => {
                if (code == codeConfig.success) {
                    const config = JSON.parse(data.content);
                    const leafletOptions = JSON.parse(config.mapInit);
                    // Leaflet 的 center 是 [lat, lng]，MapLibre 需要 [lng, lat]
                    const center: [number, number] = leafletOptions.center
                        ? [leafletOptions.center[1], leafletOptions.center[0]]
                        : [105, 35];
                    const zoom = leafletOptions.zoom ?? 2;

                    map.value = new maplibregl.Map({
                        container: 'footprint',
                        minZoom: 2,
                        maxZoom: 6,
                        attributionControl: false,
                        style: {
                            version: 8,
                            sources: {
                                'raster-tiles': {
                                    type: 'raster',
                                    tiles: [config.layer],
                                    tileSize: 256,
                                    attribution: config.attribution || '',
                                }
                            },
                            layers: [{
                                id: 'raster-layer',
                                type: 'raster',
                                source: 'raster-tiles',
                            }]
                        },
                        center,
                        zoom,
                    });

                    // 添加球形投影切换控件
                    map.value.addControl(new maplibregl.GlobeControl(), 'top-right');

                    // 样式加载完成后设置球形投影
                    map.value.on('style.load', () => {
                        map.value.setProjection({ type: 'globe' });
                        getFootprint();
                    });
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
            if (!map.value) return;
            const marker = new maplibregl.Marker()
                .setLngLat([longitude, latitude])
                .addTo(map.value);

            const el = marker.getElement();
            el.title = city;
            el.style.cursor = 'pointer';
            el.addEventListener('click', () => {
                window.open(router.resolve(`/footprint/details/${id}`).href, "_blank");
            });
        }

        onActivated(() => {
            useProcessControl(false, false, false);
            map.value?.resize();
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
    background: #000000;
}
</style>
