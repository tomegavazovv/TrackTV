CREATE TABLE tracktv_user
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE "movie"
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255),
    image_url text
);

CREATE TABLE tracktv_cast
(
    id        SERIAL PRIMARY KEY,
    role      VARCHAR(255),
    name      VARCHAR(255),
    image_url text
);

CREATE TABLE "movie_cast"
(
    id       SERIAL PRIMARY KEY,
    movie_id INT NOT NULL,
    cast_id  INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (cast_id) REFERENCES tracktv_cast (id)
);

CREATE TABLE "show"
(
    id              SERIAL PRIMARY KEY,
    title           VARCHAR(255),
    image_url       text,
    num_of_seasons  INT,
    num_of_episodes INT
);

CREATE TABLE "season"
(
    number SERIAL PRIMARY KEY
);

CREATE TABLE "episode"
(
    id            SERIAL PRIMARY KEY,
    season_number INT  NOT NULL,
    show_id       INT  NOT NULL,
    num           INT  NOT NULL,
    title         text NOT NULL,
    FOREIGN KEY (show_id) REFERENCES show (id),
    FOREIGN KEY (season_number) REFERENCES season (number)
);

CREATE TABLE "show_cast"
(
    id      SERIAL PRIMARY KEY,
    show_id INT NOT NULL,
    cast_id INT NOT NULL,
    FOREIGN KEY (show_id) REFERENCES show (id),
    FOREIGN KEY (cast_id) REFERENCES tracktv_cast (id)
);

CREATE TABLE "user_watched_movie"
(
    id       SERIAL PRIMARY KEY,
    user_id  INT,
    movie_id INT,
    date     DATE,
    FOREIGN KEY (user_id) REFERENCES tracktv_user (id),
    FOREIGN KEY (movie_id) REFERENCES movie (id)
);

CREATE TABLE "user_rate_movie"
(
    id               SERIAL PRIMARY KEY,
    watched_movie_id INT,
    rating           INT NOT NULL,
    comment          text,
    FOREIGN KEY (watched_movie_id) REFERENCES user_watched_movie (id)
);


CREATE TABLE "user_favorite_movie_cast"
(
    id            SERIAL PRIMARY KEY,
    user_movie_id INT NOT NULL,
    cast_id       INT NOT NULL,
    FOREIGN KEY (user_movie_id) REFERENCES user_watched_movie (id),
    FOREIGN KEY (cast_id) REFERENCES tracktv_cast (id)
);

CREATE TABLE "user_watch_show"
(
    id      SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    show_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tracktv_user (id),
    FOREIGN KEY (show_id) REFERENCES show (id)
);

CREATE TABLE "user_watched_episode"
(
    id         SERIAL PRIMARY KEY,
    user_id    INT NOT NULL,
    episode_id INT NOT NULL,
    date       DATE,
    FOREIGN KEY (user_id) REFERENCES tracktv_user (id),
    FOREIGN KEY (episode_id) REFERENCES episode (id)
);

CREATE TABLE "user_rate_episode"
(
    id              SERIAL PRIMARY KEY,
    user_episode_id INT NOT NULL,
    rating          INT NOT NULL,
    comment         text,
    FOREIGN KEY (user_episode_id) REFERENCES user_watched_episode (id)
);

CREATE TABLE "user_favorite_show_cast"
(
    id           SERIAL PRIMARY KEY,
    user_show_id INT NOT NULL,
    cast_id      INT NOT NULL,
    FOREIGN KEY (user_show_id) REFERENCES user_watch_show (id),
    FOREIGN KEY (cast_id) REFERENCES tracktv_cast (id)
);

CREATE TABLE "friends"
(
    id        SERIAL PRIMARY KEY,
    user_id   INT,
    friend_id INT,
    FOREIGN KEY (user_id) REFERENCES tracktv_user (id),
    FOREIGN KEY (friend_id) REFERENCES tracktv_user (id)
);

CREATE TABLE "friend_request"
(
    id          SERIAL PRIMARY KEY,
    sender_id   INT,
    reciever_id INT,
    FOREIGN KEY (sender_id) REFERENCES tracktv_user (id),
    FOREIGN KEY (reciever_id) REFERENCES tracktv_user (id)
);