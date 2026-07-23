import { get } from "../http";
import { UrlReqType } from "@/constant";
import type { AnimeInterface } from "@/types/api/common";

const animeApi: AnimeInterface = {
    getAnimeListInfo: async () => {
        return await get("/anime/getAnimeListInfo");
    },
    getAnimeList: async (req) => {
        return await get("/anime/getAnimeList", req);
    },
    getAnimeInfo: async (req) => {
        return await get("/anime/getAnimeInfo", req, UrlReqType.path);
    },
};

export default animeApi;
