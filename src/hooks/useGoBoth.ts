function goTopSpeed() {
    window.scrollTo({ top: 0, behavior: 'instant' as ScrollBehavior });
}

function goTopTime(time = 1) {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

function goBottomSpeed() {
    window.scrollTo({ top: document.documentElement.scrollHeight, behavior: 'instant' as ScrollBehavior });
}

function goBottomTime(time = 1) {
    window.scrollTo({ top: document.documentElement.scrollHeight, behavior: 'smooth' });
}

enum GoBothType {
    "TopSpeed",
    "TopTime",
    "BottomSpeed",
    "BottomTime"
}

enum RollType {
    "speed",
    "time"
}

function goBoth (type: GoBothType, time = 1) {
    let funcArray = [goTopSpeed, goTopTime, goBottomSpeed, goBottomTime];
    if(type == GoBothType.TopTime || type == GoBothType.BottomTime) {
        funcArray[type](time);
    } else {
        funcArray[type]();
    }
}

export { goBoth, GoBothType, RollType };