import {User} from "./user";
import {MovieItem} from "./MovieItem";

export interface WatchedMovie {
    type: 'movie',
    id: number,
    user: User,
    movie: MovieItem,
    date: Date
}

