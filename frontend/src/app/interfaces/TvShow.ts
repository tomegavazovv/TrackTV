export interface TvShow {
    imdb_id: Number;
    title: String;
    image_url: String;
}

export interface Response {
    results: TvShow;
}
