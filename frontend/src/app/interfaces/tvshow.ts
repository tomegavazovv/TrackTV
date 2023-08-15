export interface TvShow {
    data: {
        id: Number;
        title: String;
        imageUrl: String;
        numOfSeasons: Number;
        releaseYear: String;
    };

    watched: Boolean;
}
