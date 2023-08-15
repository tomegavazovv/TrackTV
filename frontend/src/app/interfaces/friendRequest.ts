import { User } from './User';

export interface FriendRequest {
    id: number;
    senderId: User;
    receiverId: User;
}
