export declare interface ContextInterface {
    readonly data: Partial<ContextType>;
    init: (context: Partial<ContextType>) => void;
}

export declare interface ContextType {
    spaceName: string;
    ownerName: string;
    ownerAvatar: string;
    ownerMotto: string;
    footprint: Partial<IconLinkType>;
}

export declare interface IconLinkType {
    icon: string;
    name: string;
    url: string;
}