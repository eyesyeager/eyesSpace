import { Ref } from "vue";

export interface WindowInterface extends
    WindowSizeInterface,
    WindowDistanceInterface { }

/*
 ***************************************************************************************
 *                                    size
 ***************************************************************************************
 */
export interface WindowSizeInterface {
    width: Ref<number>;
    height: Ref<number>;
    initSize: () => void;
}

export interface WindowDistanceInterface {
    scrollTop: Ref<number>;
    initDistance: () => void;
}
