import { get } from "../http";
import type { ProductInterface } from "@/types/api/common";

const productApi: ProductInterface = {
    getProductListInfo: async () => {
        return await get("/product/getProductListInfo");
    },
    getProductPage: async (req) => {
        return await get("/product/getProductPage", req);
    }
};

export default productApi;
