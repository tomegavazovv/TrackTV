import {User} from "./user";
import {MovieItem} from "./MovieItem";

export interface WatchedMovie {
    id: number,
    user: User,
    movie: MovieItem,
    date: Date
}

//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     val id: Long = 0,
//
//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     val user: User,
//
//     @ManyToOne
//     @JoinColumn(name = "movie_id")
//     val movie: Movie ,
//
//     @Column(name = "date")
//     val date: LocalDate = LocalDate.now()
