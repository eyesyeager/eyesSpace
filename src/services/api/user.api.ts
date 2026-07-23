import { get } from "../http";
import type { UserInterface } from "@/types/api/common";

const userApi: UserInterface = {
    getUserInfo: async () => {
        return await get("/user/getUserInfo");
    }
};

export default userApi;
