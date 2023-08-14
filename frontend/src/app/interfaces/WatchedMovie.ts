import { MovieItem } from './MovieItem';

export interface WatchedMovie {
    type: 'movie';
    data: MovieItem;
    date: Date;
}
