CREATE DATABASE `stackoverflow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `answer` (
  `answer_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FKsdj8jab9t00diflkysw22k7bv` (`user_id`),
  KEY `FK7mluqeoggakn10mca9veouh1p` (`question_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `questions` (
  `question_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FKjoo8hp6d3gfwctr68dl2iaemj` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tag_question` (
  `tag_question_id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` bigint DEFAULT NULL,
  `tag_id` bigint DEFAULT NULL,
  PRIMARY KEY (`tag_question_id`),
  KEY `FKdayp7xvvgcu976mm052cm69y1` (`question_id`),
  KEY `FKc61prg4h0x3ol0u0oju342lc1` (`tag_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tags` (
  `tag_id` bigint NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_enabled` bit(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vote` (
  `vote_id` bigint NOT NULL AUTO_INCREMENT,
  `vote_type` bit(1) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vote_id`),
  KEY `FKkmvvqilx49120p47nr9t56omf` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vote_answer` (
  `vote_answer_id` bigint NOT NULL AUTO_INCREMENT,
  `answer_id` bigint DEFAULT NULL,
  `vote_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vote_answer_id`),
  KEY `FK5wdq3ayatvyvpljr5me27fvv3` (`answer_id`),
  KEY `FK1kybt69s4519jdndsq0lkxtt` (`vote_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vote_question` (
  `vote_question_id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` bigint DEFAULT NULL,
  `vote_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vote_question_id`),
  KEY `FKoad4c873oevct9mydfsn9gu5e` (`question_id`),
  KEY `FKiw5jby5bhcox8hqbsngbscmi4` (`vote_id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
