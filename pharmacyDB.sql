-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: pharmacy
-- ------------------------------------------------------
-- Server version	8.0.20-0ubuntu0.20.04.1

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
-- Table structure for table `buyinfo1`
--

DROP TABLE IF EXISTS `buyinfo1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyinfo1` (
  `bill_id` varchar(10) NOT NULL,
  `s_id` varchar(10) NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `s_id` (`s_id`),
  CONSTRAINT `buyinfo1_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `suppliers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyinfo1`
--

LOCK TABLES `buyinfo1` WRITE;
/*!40000 ALTER TABLE `buyinfo1` DISABLE KEYS */;
/*!40000 ALTER TABLE `buyinfo1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyinfo2`
--

DROP TABLE IF EXISTS `buyinfo2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyinfo2` (
  `bill_id` varchar(10) NOT NULL,
  `s_no` int NOT NULL,
  `p_id` varchar(10) NOT NULL,
  `b_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `quantity` int NOT NULL,
  `cost` float NOT NULL,
  PRIMARY KEY (`bill_id`,`s_no`),
  KEY `p_id` (`p_id`),
  KEY `buyinfo2_ibfk_2` (`b_id`),
  CONSTRAINT `buyinfo2_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `buyinfo2_ibfk_2` FOREIGN KEY (`b_id`) REFERENCES `pp_branches` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyinfo2`
--

LOCK TABLES `buyinfo2` WRITE;
/*!40000 ALTER TABLE `buyinfo2` DISABLE KEYS */;
/*!40000 ALTER TABLE `buyinfo2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` varchar(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `card` varchar(20) NOT NULL,
  `phone` bigint NOT NULL,
  `address` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('C1','Thanos','platinum',4575487548,'Titan'),('C2','Joker','platinum',9856895689,'Gotham City, DC Universe'),('C3','Darth Vader','platinum',9856895474,'Tatooine'),('C4','Warden Norton','gold',8758478548,'Mansfield, Ohio'),('C5','Lord Voldemort','gold',9857412542,'Hogwarts'),('C6','Bhallaladeva','gold',9856857451,'Mahishmati');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dis` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES ('gold',0.1),('platinum',0.12),('silver',0.05);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` varchar(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `b_id` varchar(10) NOT NULL,
  `address` varchar(80) NOT NULL,
  `phone` bigint NOT NULL,
  `salary` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `b_id` (`b_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`b_id`) REFERENCES `pp_branches` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('E1','Tony Stark aka Iron Man','B1','Stark Tower',4857845125,15000),('E2','Thor Odinson','B2','Asgard',9855685945,15000),('E3','Natasha Romanoff aka Black Widow','B2','Shield Agency-1',7878487578,15000),('E4','Bruce Banner','B1','Kolkata, India',7874755845,14500),('E5','Luke Hobbs','B4','Mexico, North America',8754875151,14500),('E6','Deckard Shaw','B3','Mexico, North America',6523451254,14500),('E7','Bruce Wayne aka Batman','B5','Gotham City, DC Universe',7875487548,15000),('E8','Baahubali','B6','Mahishmati',7875544875,15000),('E9','Steve Rogers aka Captain America','B6','Brooklyn, New York',4578457845,15000);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pp_branches`
--

DROP TABLE IF EXISTS `pp_branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pp_branches` (
  `id` varchar(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(80) NOT NULL,
  `Phone` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pp_branches`
--

LOCK TABLES `pp_branches` WRITE;
/*!40000 ALTER TABLE `pp_branches` DISABLE KEYS */;
INSERT INTO `pp_branches` VALUES ('B1','PP Bhopal','Near Mata Mandir, Bhopal, MP, India','6666666696'),('B2','PP Chennai','Near Government Museum, Chennai, TN, India','7777447585'),('B3','PP Delhi','East side of Red Fort, Delhi, India','8758875875'),('B4','PP Bangalore','Near Cubbon Park, Bangalore, Karnataka, India','9999777797'),('B5','PP Jaipur','Near Kanak Vrindavan Park, Jaipur, Rajasthan, India','9969696696'),('B6','PP Vijayawada','Besides TrendSet Mall, Benz Circle, Vijayawada, AP, India','7878758757');
/*!40000 ALTER TABLE `pp_branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` varchar(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(15) NOT NULL,
  `cost` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('P1','Paracetmol','Tablet',50),('P10','Alfusin','Tablet',132),('P2','Imipramine Hydrochloride','Tablet',15.35),('P3','Rifaximin','Tablet',250.25),('P4','Cefpodixime and Clauvulanic Acid','Tablet',290.56),('P5','Ondansetron Hydrochloride','Tablet',50.84),('P6','Cilnidipine','Tablet',68.35),('P7','Trypsin Chymotripsin','Tablet',125.5),('P8','Ofloxacin','Tablet',49),('P9','Azithromycin','Tablet',56);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `records` (
  `balance` float NOT NULL,
  `sellTrsId` int NOT NULL,
  `buyTrsId` int NOT NULL,
  PRIMARY KEY (`balance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES (15000,1,1);
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellinfo1`
--

DROP TABLE IF EXISTS `sellinfo1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sellinfo1` (
  `bill_id` varchar(10) NOT NULL,
  `c_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `sellinfo1_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `customers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellinfo1`
--

LOCK TABLES `sellinfo1` WRITE;
/*!40000 ALTER TABLE `sellinfo1` DISABLE KEYS */;
/*!40000 ALTER TABLE `sellinfo1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellinfo2`
--

DROP TABLE IF EXISTS `sellinfo2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sellinfo2` (
  `bill_id` varchar(10) NOT NULL,
  `s_no` int NOT NULL,
  `b_id` varchar(10) NOT NULL,
  `p_id` varchar(10) NOT NULL,
  `quantity` int NOT NULL,
  `cost` float NOT NULL,
  PRIMARY KEY (`bill_id`,`s_no`),
  KEY `b_id` (`b_id`),
  KEY `sellinfo2_ibfk_2` (`p_id`),
  CONSTRAINT `sellinfo2_ibfk_1` FOREIGN KEY (`b_id`) REFERENCES `pp_branches` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sellinfo2_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellinfo2`
--

LOCK TABLES `sellinfo2` WRITE;
/*!40000 ALTER TABLE `sellinfo2` DISABLE KEYS */;
/*!40000 ALTER TABLE `sellinfo2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `id` varchar(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(80) NOT NULL,
  `Phone` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES ('S1','Marvel','New York, United States.','9797958442'),('S2','Disney','Washington DC, USA','5487548754'),('S3','Media Mix','Tokyo, Japan','8787854545'),('S4','Warner Bros.','Los Angeles, California, United States','5485745874');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-29 12:39:45
