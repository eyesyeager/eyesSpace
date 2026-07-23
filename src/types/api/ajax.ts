import { UrlReqType } from "@/constant";
import { RespInterface } from "./common";

export type AddHeaderType = (existent?: any) => any;

export type BuildGetUrlType = (baseUrl: string, req: any) => string;

export type BuildMixGetUrlType = (baseUrl: string, req: MixGetUrlReqInterface) => string;

export interface MixGetUrlReqInterface {
    path: Array<any>;
    param: any;
}

export type GetType = (url: string, req?: any, type?: UrlReqType) => Promise<RespInterface>;

export type PostType = (url: string, req?: any) => Promise<RespInterface>;

export type PutType = (url: string, req?: any) => Promise<RespInterface>;

export type DelType = (url: string, req?: any, type?: UrlReqType) => Promise<RespInterface>;
