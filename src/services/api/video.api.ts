import { get } from "../http";
import type { VideoInterface } from "@/types/api/common";

const videoApi: VideoInterface = {
    getVideoInfo: async () => {
        return await get("/video/getVideoInfo");
    },
};

export default videoApi;
