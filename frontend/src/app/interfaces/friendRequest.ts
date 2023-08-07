import {User} from "./user";

export interface FriendRequest {
    id: number;
    senderId: User;
    receiverId: User;
}
