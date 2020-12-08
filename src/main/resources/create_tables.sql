create database kryptotipy_db;
CREATE TABLE `post` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`birthday` date DEFAULT NULL,
`content` longtext DEFAULT NULL,
`disabled` bit(1) DEFAULT NULL,
`external` bit(1) DEFAULT NULL,
`i18n` varchar(255) DEFAULT NULL,
`img_src` varchar(255) DEFAULT NULL,
`source` varchar(255) DEFAULT NULL,
`source_render` varchar(255) DEFAULT NULL,
`title` varchar(500) DEFAULT NULL,
`url` varchar(500) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UK_ppmqe9x5n4ha6pud801evcr8s` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=245841 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `label` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UK_defh0r2wr6e5g7vu7vanv4pxa` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14028 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `post_labels` (
`post_id` bigint(20) NOT NULL,
`label_id` bigint(20) NOT NULL,
KEY `post_id` (`post_id`),
KEY `label_id` (`label_id`),
CONSTRAINT `FKjj16h83j6ke5vljjlv26driyr` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
CONSTRAINT `FKn50lp4uxki348v0rmqe08lc8h` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`),
CONSTRAINT `post_labels_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
CONSTRAINT `post_labels_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

