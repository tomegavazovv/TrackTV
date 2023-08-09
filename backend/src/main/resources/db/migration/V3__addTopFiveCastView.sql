CREATE VIEW top_five_cast_of_movie_view AS
SELECT
            ROW_NUMBER() OVER () AS id,
            mc.movie_id,
            tc.role AS ROLE,
            tc.name AS name,
            tc.image_url,
            COUNT(ufmc.id) AS favorite_count
FROM "movie_cast" mc
         JOIN "user_favorite_movie_cast" ufmc ON mc.id = ufmc.cast_id
         JOIN "tracktv_cast" tc ON mc.cast_id = tc.id
GROUP BY mc.movie_id, tc.role, tc.name, tc.image_url
ORDER BY mc.movie_id, favorite_count DESC
LIMIT 5;

CREATE VIEW top_five_cast_of_show_view AS
SELECT
            ROW_NUMBER() OVER () AS id,
            sc.show_id,
            tc.role AS ROLE,
            tc.name AS name,
            tc.image_url,
            COUNT(ufsc.id) AS favorite_count
FROM "show_cast" sc
         JOIN "user_favorite_show_cast" ufsc ON sc.id = ufsc.cast_id
         JOIN "tracktv_cast" tc ON sc.cast_id = tc.id
GROUP BY sc.show_id, tc.role, tc.name, tc.image_url
ORDER BY sc.show_id, favorite_count DESC
LIMIT 5;