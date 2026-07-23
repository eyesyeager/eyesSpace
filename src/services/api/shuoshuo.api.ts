import { get } from "../http";
import type { ShuoshuoInterface } from "@/types/api/common";

const shuoshuoApi: ShuoshuoInterface = {
    getShuoshuoList: async (req) => {
        return await get("/shuo/getShuoList", req);
    },
    getShuoshuoListInfo: async () => {
        return await get("/shuo/getShuoListInfo");
    },
};

export default shuoshuoApi;
