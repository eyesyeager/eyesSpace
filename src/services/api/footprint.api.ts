import { get } from "../http";
import type { FootprintInterface } from "@/types/api/common";

const footprintApi: FootprintInterface = {
    getFootprintList: async () => {
        return await get("/footprint/getFootprintList");
    },
    getFootprintListInfo: async () => {
        return await get("/footprint/getFootprintListInfo");
    },
    getFootprintContentList: async (req) => {
        return await get("/footprint/getFootprintContentList", req);
    },
};

export default footprintApi;
