-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: pokemon_db
-- ------------------------------------------------------
-- Server version	8.4.5

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (1,'Searched for: 水箭龟','2026-07-03 16:41:02','1'),(2,'Searched for: Blastoise','2026-07-03 16:41:09','1'),(3,'Searched for: カメックス','2026-07-03 16:41:14','1'),(4,'Saved team: 111','2026-07-03 16:41:33','1'),(5,'Saved team: 123','2026-07-03 16:41:40','1'),(6,'Saved team: 333','2026-07-03 16:41:43','1'),(7,'Searched for: 喷火龙','2026-07-03 16:43:51','1'),(8,'Searched for: Blastoise','2026-07-03 16:44:19','1'),(9,'Saved team: 3','2026-07-03 16:44:25','1'),(10,'Searched for: 水箭龟','2026-07-03 16:50:09','1'),(11,'Searched for: 水箭龟','2026-07-03 16:50:22','1'),(12,'Searched for: 勾魂眼','2026-07-03 16:50:34','1'),(13,'Searched for: Sableye','2026-07-03 16:50:45','1'),(14,'Saved team: 111','2026-07-03 16:50:49','1'),(15,'Searched for: Blastoise','2026-07-03 17:08:22','2'),(16,'Searched for: Blastoise','2026-07-03 17:08:42','2'),(17,'Searched for: Blastoise','2026-07-03 17:11:01','2'),(18,'Searched for: Lopunny','2026-07-03 17:11:15','2'),(19,'Searched for: Blastoise','2026-07-03 17:11:55','2'),(20,'Searched for: Blastoise','2026-07-03 17:11:59','2'),(21,'Searched for: Lopunny','2026-07-03 17:14:53','2'),(22,'Saved team: 111','2026-07-03 17:14:59','2'),(23,'Searched for: Rhyperior','2026-07-03 17:15:29','2'),(24,'Saved team: 222','2026-07-03 17:15:32','2'),(25,'Searched for: Lapras','2026-07-03 21:26:54','2'),(26,'Saved team: ','2026-07-03 21:26:57','2'),(27,'Saved team: 11','2026-07-03 21:27:01','2'),(28,'Searched for: pikachu','2026-07-03 23:23:12','2'),(29,'Saved team: 11','2026-07-03 23:23:17','2'),(30,'Saved team: 11','2026-07-04 00:20:26','2'),(31,'Saved team: 11','2026-07-04 00:21:14','2'),(32,'Searched for: Charizard','2026-07-04 01:43:38','2'),(33,'Saved team: 222','2026-07-04 01:43:42','2'),(34,'Searched for: Charizard','2026-07-04 01:44:02','2'),(35,'Searched for: Charizard','2026-07-04 01:44:04','2'),(36,'Searched for: Charizard','2026-07-04 01:44:06','2'),(37,'Saved team: 222','2026-07-04 01:44:12','2'),(38,'Saved team: 11','2026-07-04 01:47:28','2'),(39,'Searched for: Gengar','2026-07-04 01:47:49','2'),(40,'Searched for: Gengar','2026-07-04 01:53:36','2'),(41,'Saved team: 11','2026-07-04 01:53:40','2'),(42,'Saved team: 11','2026-07-04 01:58:12','2'),(43,'Searched for: Gengar','2026-07-04 01:58:14','2'),(44,'Saved team: 11','2026-07-04 01:58:18','2'),(45,'Searched for: Gengar','2026-07-04 01:58:38','2'),(46,'Searched for: Charizard','2026-07-04 01:59:38','2'),(47,'Saved team: 11','2026-07-04 01:59:43','2'),(48,'Searched for: Incineroar','2026-07-04 02:00:11','2'),(49,'Saved team: 11','2026-07-04 02:00:16','2'),(50,'Searched for: Charizard','2026-07-04 02:02:06','2'),(51,'Searched for: Charizard','2026-07-04 02:02:11','2'),(52,'Searched for: Urshifu','2026-07-04 02:02:53','2'),(53,'Searched for: Urshifu','2026-07-04 02:02:56','2'),(54,'Searched for: Urshifu','2026-07-04 02:03:04','2'),(55,'Searched for: Squirtle','2026-07-04 02:03:14','2'),(56,'Saved team: 11','2026-07-04 02:03:19','2'),(57,'Searched for: Charizard','2026-07-04 02:06:25','2'),(58,'Saved team: 11','2026-07-04 02:06:28','2'),(59,'Searched for: gengar ','2026-07-04 02:16:24','2'),(60,'Saved team: 11','2026-07-04 02:16:36','2'),(61,'Searched for: blastoise','2026-07-04 02:26:38','2'),(62,'Saved team: 11','2026-07-04 02:26:47','2');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pokemons`
--

LOCK TABLES `pokemons` WRITE;
/*!40000 ALTER TABLE `pokemons` DISABLE KEYS */;
INSERT INTO `pokemons` VALUES (1,'Bulbasaur','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png',NULL,NULL),(5,'charmeleon','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png',NULL,NULL),(6,'charizard','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png','fire','flying'),(7,'squirtle','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png','water',NULL),(9,'blastoise','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png','water',NULL),(25,'pikachu','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png',NULL,NULL),(94,'gengar','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/94.png','ghost','poison'),(131,'lapras','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/131.png',NULL,NULL),(302,'sableye','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/302.png',NULL,NULL),(428,'lopunny','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/428.png',NULL,NULL),(464,'rhyperior','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/464.png',NULL,NULL),(727,'incineroar','https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/727.png','fire','dark');
/*!40000 ALTER TABLE `pokemons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_members`
--

LOCK TABLES `team_members` WRITE;
/*!40000 ALTER TABLE `team_members` DISABLE KEYS */;
INSERT INTO `team_members` VALUES (20,2,1),(22,3,5),(23,3,9),(24,1,6),(25,1,302),(33,5,464),(34,5,6),(35,5,6),(36,5,6),(37,5,6),(38,5,6),(57,4,94),(58,4,727),(59,4,7),(60,4,6),(61,4,9);
/*!40000 ALTER TABLE `team_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,2,'111',0),(2,2,'123',1),(3,2,'3',2),(4,3,'11',0),(5,3,'222',1);
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$PkDvTTlRJ6n1GYBCHwtlEOu4DP9HbKKOmJgIZ/.Kpt/i62NkNooRW','admin'),(2,'1','$2a$10$rM1rMWUO0j13MENJjkp3qetv7.E3OdEbfAKT1bD/fCLa16yFsdj12','USER'),(3,'2','$2a$10$Ezydsb3c17muEPhz1RA91unKcQ3P/PIOMsXqHWVS9qs7oxys7Firu','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pokemon_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-04  2:46:42
