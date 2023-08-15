import { User } from './User';
import { MovieItem } from './MovieItem';

export interface SuggestedMovie {
    id: Number;
    movieId: MovieItem;
    suggestedFromUserId: User;
    suggestedToUserId: User;
}
