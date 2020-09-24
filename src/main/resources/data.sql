INSERT INTO users (email, username, password)
VALUES ('no-email@example.com', 'admin', 'admin') ON CONFLICT(username) DO NOTHING;
INSERT INTO user_roles (user_id, user_roles)
SELECT (
        SELECT id
        FROM users
        WHERE username = 'admin'
    ),
    'ADMIN'
WHERE NOT EXISTS(
        SELECT *
        FROM user_roles
        WHERE user_id =(
                SELECT id
                FROM users
                WHERE username = 'admin'
            )
            and user_roles = 'ADMIN'
    );