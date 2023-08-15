export interface Movie {
    data: {
        id: number;
        title: string;
        imageUrl: string;
        popularity: number;
        releaseYear: string;
        duration: string;
    };
    watched: boolean;
    rating: Number;
}
