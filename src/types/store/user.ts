export interface UserInterface {
    readonly data: Partial<UserType>;
    status: number;
    init: (info: Partial<UserType>) => void;
    isLogin: () => boolean;
}

export interface UserType {
    id: number;
    email: string;
    username: string;
    avatar: string;
    createTime: string;
}
