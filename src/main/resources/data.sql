INSERT INTO roles VALUES (1, 'USER'), (2, 'ADMIN'), (3, 'MECHANIC');

INSERT INTO users (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `phone`)
VALUES
    ('c813d441-3d09-4c54-911f-3b9306c2b253', 'admin', 'ea094156204c067cbf28f8c90a88d6fb231a782c01e48c44eb7f49919ff1e58c3e53d7e0f0d78c3143745dfe2ca9be5b', 'Ivan', 'Ivanov', 'admin@carservice.bg', '0887123123'),
    ('9914095b-eead-4950-b355-b5fa9b75f1d7', 'mechanic', '926b48ff99410fd19efebc6a07b43ce6d1ca179434ab0bcfb6986320b087e4db3451d99e6909003dc49c7cdadb87a475','Stoyan','Stoyanov', 'syncro6363@yahoo.com', '0887123122');

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES
    ('c813d441-3d09-4c54-911f-3b9306c2b253', 1),
    ('c813d441-3d09-4c54-911f-3b9306c2b253', 2),
    ('c813d441-3d09-4c54-911f-3b9306c2b253', 3),
    ('9914095b-eead-4950-b355-b5fa9b75f1d7', 1),
    ('9914095b-eead-4950-b355-b5fa9b75f1d7', 3);

INSERT INTO suppliers (`id`, `name`, `address`, `phone_number`, `email`, `information`)
VALUES
    ('5c96658d-2058-43d5-a1cd-2abe7f2bce9f', 'Auto +', 'Sofia, Manastirski Livadi 5', '0887565656', 'autoplus@abv.bg', 'Auto parts and accessories'),
    ('f3b3b3b3-1b3b-4b3b-8b3b-3b3b3b3b3b3b', 'Auto Parts', 'Sofia, Mladost 1A', '0887565657', 'autoparts@abv.bg', 'Auto parts');