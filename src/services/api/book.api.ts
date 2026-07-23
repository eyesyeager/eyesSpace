import { get } from "../http";
import { UrlReqType } from "@/constant";
import type { BookInterface } from "@/types/api/common";

const bookApi: BookInterface = {
    getBookListInfo: async () => {
        return await get("/book/getBookListInfo");
    },
    getBookList: async (req) => {
        return await get("/book/getBookList", req);
    },
    getBookInfo: async (req) => {
        return await get("/book/getBookInfo", req, UrlReqType.path);
    }
};

export default bookApi;
