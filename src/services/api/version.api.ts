import { get } from "../http";
import type { VersionInterface } from "@/types/api/common";

const versionApi: VersionInterface = {
    getVersionInfo: async () => {
        return await get("/version/getVersionInfo");
    },
    getVersionList: async (req) => {
        return await get("/version/getVersionList", req);
    }
};

export default versionApi;
