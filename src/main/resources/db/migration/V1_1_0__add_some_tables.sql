CREATE TABLE IF NOT EXISTS `users` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255),
    `last_name` varchar(255),
    `login` varchar(255),
    `password` varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `files` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `adress` varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `events` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uploaded` datetime,
    `user_id` int,
    `file_id` int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (file_id) REFERENCES files(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;





