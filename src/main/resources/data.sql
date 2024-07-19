INSERT INTO roles VALUES (1, 'USER'), (2, 'ADMIN'), (3,'MECHANIC');

INSERT INTO `users` (`id`,`username`,`password`,`first_name`,`last_name`,`email`,`phone`)
VALUES
    ('c813d441-3d09-4c54-911f-3b9306c2b253','admin','ea094156204c067cbf28f8c90a88d6fb231a782c01e48c44eb7f49919ff1e58c3e53d7e0f0d78c3143745dfe2ca9be5b','Ivan','Ivanov','admin@carservice.bg','0887123123');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
    ('c813d441-3d09-4c54-911f-3b9306c2b253', 1),
    ('c813d441-3d09-4c54-911f-3b9306c2b253', 2),
    ('c813d441-3d09-4c54-911f-3b9306c2b253', 3);