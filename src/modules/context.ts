import type { ContextInterface } from "@/types/store/context";

const context: ContextInterface = {
    data: {},
    init: (cx) => {
        Object.assign(context.data, cx);
    },
}

export { context };