CREATE TABLE show_comment
(
    id      BIGSERIAL,
    user_id INT REFERENCES tracktv_user (id),
    show_id INT REFERENCES show (id),
    date    TIMESTAMP,
    comment TEXT
);