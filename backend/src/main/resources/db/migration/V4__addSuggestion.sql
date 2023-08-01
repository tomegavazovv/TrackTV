CREATE TABLE suggest_movie
(
    id                     BIGSERIAL,
    suggested_from_user_id INT REFERENCES tracktv_user (id),
    suggested_to_user_id    INT REFERENCES tracktv_user (id),
    movie_id INT REFERENCES movie (id)
);
CREATE TABLE suggest_show
(
    id                     BIGSERIAL,
    suggested_from_user_id INT REFERENCES tracktv_user (id),
    suggested_to_user_id   INT REFERENCES tracktv_user (id),
    show_id INT REFERENCES show (id)
);