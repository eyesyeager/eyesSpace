import { get } from "../http";
import type { MusicInterface } from "@/types/api/common";

const musicApi: MusicInterface = {
    getMusicList: async () => {
        return await get("/music/getMusicList");
    },
};

export default musicApi;
