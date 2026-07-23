import { get } from "../http";
import { UrlReqType } from "@/constant";
import type { BlogInterface } from "@/types/api/common";

const blogApi: BlogInterface = {
    getBlogListInfo: async () => {
        return await get("/blog/getBlogListInfo");
    },
    getBlogList: async (req) => {
        return await get("/blog/getBlogList", req);
    },
    getBlogInfo: async (req) => {
        return await get("/blog/getBlogInfo", req, UrlReqType.path);
    },
    getBlogCategory: async () => {
        return await get("/blog/getBlogCategory");
    },
    getBlogLabel: async () => {
        return await get("/blog/getBlogLabel");
    },
};

export default blogApi;
