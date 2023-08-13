export interface Movie {
    data: {
        id: number;
        title: string;
        imageUrl: string;
        popularity: number;
    };
    watched: boolean;
    rating: Number;
}
