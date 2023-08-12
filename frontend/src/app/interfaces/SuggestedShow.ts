import {User} from "./user";
import {PopularTvShow} from "./PopularTvShow";

export interface SuggestedShow {
    id: Number,
    showId: PopularTvShow,
    suggestedFromUserId: User,
    suggestedToUserId: User
}
