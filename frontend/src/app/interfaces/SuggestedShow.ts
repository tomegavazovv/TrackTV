import { User } from './User';
import { PopularTvShow } from './PopularTvShow';

export interface SuggestedShow {
    id: Number;
    showId: PopularTvShow;
    suggestedFromUserId: User;
    suggestedToUserId: User;
}
