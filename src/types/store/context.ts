export interface ContextInterface {
    readonly data: Partial<ContextType>;
    init: (context: Partial<ContextType>) => void;
}

export interface ContextType {
    spaceName: string;
    spaceClientId: string;
    ownerName: string;
    ownerAvatar: string;
    ownerMotto: string;
    ownerEmail: string;
}

export interface IconLinkType {
    icon: string;
    name: string;
    url: string;
}
