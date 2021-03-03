use practicum;
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`) values("84fcc89e-9957-4371-a847-268b6410d73b","passant",md5("passant"),"passant@gmail.com","100");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("854e67db-cfa4-4b53-97a6-077b2769ff90","mohamed",md5("mohamed"),"mohamed@gmail.com","12");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("8692092d-558a-4aa0-a379-012e2abc285e","noah",md5("noah"),"noah@gmail.com","30.5");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("89864c78-fdf5-4232-96c4-72e0360e9c2b","ahmed",md5("ahmed"),"ahmed@gmail.com","100");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("8a91bd45-72e2-407a-b6ed-305a642a4ff6","seif",md5("seif"),"seif@gmail.com","1");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("8bd088ef-fdc8-495a-a743-7b282b7a0772","esraa",md5("esraa"),"esraa@gmail.com","1");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("ad4a92c1-3a92-4bcb-a443-845cadf09c14","aliaa",md5("aliaa"),"aliaa@gmail.com","1");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("c69d28e8-b04e-4191-8e45-2040e0766ab8","hamza",md5("hamza"),"hamza@gmail.com","1");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("e90adfb9-3eff-48f1-8be6-3ab932a7e218","soha",md5("soha"),"soha@gmail.com","1");
INSERT IGNORE INTO `practicum`.`user`(`id`,`name`,`password`,`email`,`balance`)VALUES("fb4bc080-cb6e-41c1-8822-f830f3008223","joe",md5("joe"),"joe.com","10");
INSERT IGNORE INTO `practicum`.`transaction`(`sender_email`,`receiver_email`,`amount`)VALUES("passant@gmail.com","mohamed@gmail.com",9);
INSERT IGNORE INTO `practicum`.`transaction`(`sender_email`,`receiver_email`,`amount`)VALUES("passant@gmail.com","aliaa@gmail.com",11);
INSERT IGNORE INTO `practicum`.`transaction`(`sender_email`,`receiver_email`,`amount`)VALUES("aliaa@gmail.com","passant@gmail.com",11);


