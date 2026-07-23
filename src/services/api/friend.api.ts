import { get } from "../http";
import type { FriendInterface } from "@/types/api/common";

const friendApi: FriendInterface = {
    getFriendListData: async () => {
        return await get("/friend/getFriendListData");
    },
    getFriendList: async () => {
        return await get("/friend/getFriendList");
    },
};

export default friendApi;
