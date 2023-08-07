CREATE VIEW top_five_cast_of_movie_view AS
SELECT c.id, uwm.id as movie_id, c.role, c.name, c.image_url, COUNT(uwm.movie_id) AS movie_count
FROM user_favorite_movie_cast AS fm
         LEFT JOIN user_watched_movie AS uwm ON fm.user_movie_id = uwm.id
         LEFT JOIN tracktv_cast AS c ON fm.cast_id = c.id
GROUP BY c.id, uwm.id, c.role, c.name, c.image_url
ORDER BY movie_count DESC
LIMIT 5;

CREATE VIEW top_five_cast_of_show_view AS
SELECT c.id, uws.show_id as show_id,c.role, c.name, c.image_url, COUNT(uws.show_id) AS show_count
FROM user_favorite_show_cast AS fs
         LEFT JOIN user_watch_show AS uws ON fs.user_show_id = uws.id
         LEFT JOIN tracktv_cast AS c ON fs.cast_id = c.id
GROUP BY c.id, uws.show_id, c.role, c.name, c.image_url
ORDER BY show_count DESC
LIMIT 5;