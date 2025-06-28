-- Insert into users
INSERT INTO users (email, password) VALUES
                                        ('admin@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi');

-- Insert roles
INSERT INTO roles(name, admin)
VALUES
    ('ROLE_ADMIN', 1);

-- Assign roles automatically based on user type
INSERT INTO user_roles (user_id, role_id)
select 1,id from roles where admin=1;