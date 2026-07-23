import { get } from "../http";
import type { JokeInterface } from "@/types/api/common";

const jokeApi: JokeInterface = {
    getJokeList: async (req) => {
        return await get("/joke/getJokeList", req);
    },
};

export default jokeApi;
