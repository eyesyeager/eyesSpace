import { get, post } from "../http";
import { UrlReqType } from "@/constant";
import type { SiteInterface } from "@/types/api/common";

const siteApi: SiteInterface = {
    getContext: async () => {
        return await get("/context/getContext");
    },
    getContextItem: async (req) => {
        return await get("/context/getContextItem", req, UrlReqType.path);
    },
    getBatchContextItem: async (req) => {
        return await post("/context/getBatchContextItem", req);
    }
};

export default siteApi;
