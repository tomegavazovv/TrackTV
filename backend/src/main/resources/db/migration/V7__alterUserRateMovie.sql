alter table user_rate_movie
drop column comment;

create table user_comment_movie(
   id               SERIAL PRIMARY KEY,
   watched_movie_id INT NOT NULL,
   comment          text NOT NULL,
   FOREIGN KEY (watched_movie_id) REFERENCES user_watched_movie (id)
);