import {User} from "./user";
import {PopularTvShow} from "./PopularTvShow";

export interface WatchedShow {
    type: 'show',
    id: number,
    user: User,
    show: PopularTvShow,
    date: Date
}
