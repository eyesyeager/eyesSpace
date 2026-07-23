import axios from "axios";
import { GetType, PostType, PutType, DelType } from "@/types/api/ajax";
import { UrlReqType } from "@/constant";
import requestFilter from "./filter/request";
import responseFilter from "./filter/response";
import urlRequest from "@/services/helper/url";
import { addHeader } from "@/services/helper/auth";

const service = axios.create({
    baseURL: process.env.VITE_API_DOMAIN,
    timeout: 15 * 1000,
});

service.interceptors.request.use(
    config => {
        requestFilter(config);
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

service.interceptors.response.use(
    response => {
        responseFilter(response);
        return response.data;
    },
    error => {
        return Promise.reject(error);
    }
);

const get: GetType = (url, req, type = UrlReqType.param) => {
    return service.get(urlRequest[type](url, req), addHeader());
};

const post: PostType = (url, req) => {
    return service.post(url, req, addHeader());
};

const put: PutType = (url, req) => {
    return service.put(url, req, addHeader());
};

const del: DelType = (url, req, type = UrlReqType.param) => {
    return service.delete(urlRequest[type](url, req), addHeader());
};

export { get, post, put, del };
