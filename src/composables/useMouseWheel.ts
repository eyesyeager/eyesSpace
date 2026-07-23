import touchUtils from "@/utils/touch";

type UseMouseWheelType = (
    wheelUp: () => void,
    wheelDown: () => void
) => void;

const useMouseWheel: UseMouseWheelType = (wheelUp, wheelDown) => {
    // 使用原生 wheel 事件替代 jQuery mousewheel/DOMMouseScroll
    document.addEventListener('wheel', (event: WheelEvent) => {
        // deltaY > 0 表示向下滚动，< 0 表示向上滚动
        if (event.deltaY < 0) {
            wheelUp();
        } else if (event.deltaY > 0) {
            wheelDown();
        }
    }, { passive: true });

    // 兼容移动端滑动事件
    touchUtils.listenTouchDirection(document, false, wheelDown, wheelUp);
};

export default useMouseWheel;
