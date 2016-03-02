-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: pranvera_app
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.15.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `el_author`
--

DROP TABLE IF EXISTS `el_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_author` (
  `AUTHOR_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FULL_NAME` varchar(255) NOT NULL,
  `TWITTER_URL` varchar(255) DEFAULT NULL,
  `IMG_URL` varchar(255) NOT NULL DEFAULT '/resources/images/profile/account.jpg',
  `AUTHOR_URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AUTHOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_author`
--

LOCK TABLES `el_author` WRITE;
/*!40000 ALTER TABLE `el_author` DISABLE KEYS */;
INSERT INTO `el_author` VALUES (1,'Elion Haxhi','@haxhielion','/resources/images/profile/elion.jpg','/news/author/elionhaxhi'),(2,'Dorian Haxhi','@dorianhaxhi','/resources/images/profile/dorian.jpg','/news/author/dorianhaxhi'),(3,'Taulant Papa','@taulantpapa','/resources/images/profile/default.jpg','/news/author/taulantpapa');
/*!40000 ALTER TABLE `el_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_category`
--

DROP TABLE IF EXISTS `el_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_category` (
  `CATEGORY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `URL` varchar(255) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  KEY `CATEGORY_NAME_INDEX` (`NAME`),
  KEY `CATEGORY_URL_INDEX` (`URL`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_category`
--

LOCK TABLES `el_category` WRITE;
/*!40000 ALTER TABLE `el_category` DISABLE KEYS */;
INSERT INTO `el_category` VALUES (1,'Business','/news/category/business'),(2,'Development','/news/category/development'),(3,'News','/news/category/news'),(4,'ALL Features','/news/'),(5,'ALL Categories','/news/category/');
/*!40000 ALTER TABLE `el_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_customer`
--

DROP TABLE IF EXISTS `el_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_customer` (
  `CUSTOMER_ID` bigint(20) NOT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PASSWORD_CHANGE_REQUIRED` tinyint(1) DEFAULT NULL,
  `DEACTIVATED` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_customer`
--

LOCK TABLES `el_customer` WRITE;
/*!40000 ALTER TABLE `el_customer` DISABLE KEYS */;
INSERT INTO `el_customer` VALUES (0,'elion','elion',0,NULL);
/*!40000 ALTER TABLE `el_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_customer_role`
--

DROP TABLE IF EXISTS `el_customer_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_customer_role` (
  `CUSTOMER_ROLE_ID` bigint(20) NOT NULL,
  `CUSTOMER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ROLE_ID`),
  KEY `fk_el_customer_role_el_customer1_idx` (`CUSTOMER_ID`),
  KEY `fk_el_customer_role_el_role1_idx` (`ROLE_ID`),
  KEY `CUSTROLE_CUSTOMER_INDEX` (`CUSTOMER_ID`),
  KEY `CUSTROLE_ROLE_INDEX` (`ROLE_ID`),
  CONSTRAINT `fk_el_customer_role_el_customer1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `el_customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_el_customer_role_el_role1` FOREIGN KEY (`ROLE_ID`) REFERENCES `el_role` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_customer_role`
--

LOCK TABLES `el_customer_role` WRITE;
/*!40000 ALTER TABLE `el_customer_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `el_customer_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_email_tracking`
--

DROP TABLE IF EXISTS `el_email_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_email_tracking` (
  `EMAIL_TRACKING_ID` bigint(20) NOT NULL,
  `DATE_SENT` datetime DEFAULT NULL,
  `EMAIL_ADDRESS` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EMAIL_TRACKING_ID`),
  KEY `EMAILTRACKING_INDEX` (`EMAIL_ADDRESS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_email_tracking`
--

LOCK TABLES `el_email_tracking` WRITE;
/*!40000 ALTER TABLE `el_email_tracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `el_email_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_email_tracking_clicks`
--

DROP TABLE IF EXISTS `el_email_tracking_clicks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_email_tracking_clicks` (
  `CLICK_ID` bigint(20) NOT NULL,
  `CUSTOMER_ID` varchar(255) DEFAULT NULL,
  `DATE_CLICKED` datetime NOT NULL,
  `DESTINATION_URI` varchar(255) DEFAULT NULL,
  `QUERY_STRING` varchar(255) DEFAULT NULL,
  `EMAIL_TRACKING_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`CLICK_ID`),
  KEY `TRACKINGCLICKS_CUSTOMER_INDEX` (`CUSTOMER_ID`),
  KEY `TRACKINGCLICKS_TRACKING_INDEX` (`EMAIL_TRACKING_ID`),
  KEY `FKFDF9F52AFA1E5D61` (`EMAIL_TRACKING_ID`),
  CONSTRAINT `FKFDF9F52AFA1E5D61` FOREIGN KEY (`EMAIL_TRACKING_ID`) REFERENCES `el_email_tracking` (`EMAIL_TRACKING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_email_tracking_clicks`
--

LOCK TABLES `el_email_tracking_clicks` WRITE;
/*!40000 ALTER TABLE `el_email_tracking_clicks` DISABLE KEYS */;
/*!40000 ALTER TABLE `el_email_tracking_clicks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_email_tracking_opens`
--

DROP TABLE IF EXISTS `el_email_tracking_opens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_email_tracking_opens` (
  `OPEN_ID` bigint(20) NOT NULL,
  `DATE_OPENED` datetime DEFAULT NULL,
  `USER_AGENT` varchar(255) DEFAULT NULL,
  `EMAIL_TRACKING_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`OPEN_ID`),
  KEY `TRACKINGOPEN_TRACKING` (`EMAIL_TRACKING_ID`),
  KEY `FKA5C3722AFA1E5D61` (`EMAIL_TRACKING_ID`),
  CONSTRAINT `FKA5C3722AFA1E5D61` FOREIGN KEY (`EMAIL_TRACKING_ID`) REFERENCES `el_email_tracking` (`EMAIL_TRACKING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_email_tracking_opens`
--

LOCK TABLES `el_email_tracking_opens` WRITE;
/*!40000 ALTER TABLE `el_email_tracking_opens` DISABLE KEYS */;
/*!40000 ALTER TABLE `el_email_tracking_opens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_locale`
--

DROP TABLE IF EXISTS `el_locale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_locale` (
  `LOCALE_CODE` varchar(255) NOT NULL,
  `DEFAULT_FLAG` tinyint(1) DEFAULT NULL,
  `FRIENDLY_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOCALE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_locale`
--

LOCK TABLES `el_locale` WRITE;
/*!40000 ALTER TABLE `el_locale` DISABLE KEYS */;
INSERT INTO `el_locale` VALUES ('al',0,'Albania'),('al_AL',0,'Albania (Shqiperia)'),('it',0,'Italy     '),('it_IT',1,'Italy (Italia)');
/*!40000 ALTER TABLE `el_locale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_post`
--

DROP TABLE IF EXISTS `el_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_post` (
  `POST_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) NOT NULL,
  `RENDEREDCONTENT` text NOT NULL,
  `RENDEREDSUMMARY` text NOT NULL,
  `PUBLISH_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IMG_URL` varchar(255) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `CATEGORY_ID` bigint(20) NOT NULL,
  `AUTHOR_ID` bigint(20) NOT NULL,
  `TIME_TO_READ_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`POST_ID`),
  KEY `fk_el_post_el_category1_idx` (`CATEGORY_ID`),
  KEY `fk_el_post_el_author1_idx` (`AUTHOR_ID`),
  KEY `fk_el_post_el_time_to_read1_idx` (`TIME_TO_READ_ID`),
  CONSTRAINT `fk_el_post_el_author1` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `el_author` (`AUTHOR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_el_post_el_category1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `el_category` (`CATEGORY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_el_post_el_time_to_read1` FOREIGN KEY (`TIME_TO_READ_ID`) REFERENCES `el_time_to_read` (`TIME_TO_READ_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_post`
--

LOCK TABLES `el_post` WRITE;
/*!40000 ALTER TABLE `el_post` DISABLE KEYS */;
INSERT INTO `el_post` VALUES (1,'Finalmente','Porgo un caloroso saluto a tutti voi. Siamo riusciti a mettere in piedi questa realta perche crediamo in quello che facciamo. Oggi giorno tutti sono interesati ad avere un sito su internet. Ed é per questo he ci\nsono tante aziende che stano fiorendo come i funghi in questo contesto compresa la nostra. Ma noi abbiamo qualcosa in piu ed è quella di essere convenienti in confronto al raporto qualita prezzo. Siamo convenienti non perche disperati me perche siamo appena nati ed e guisto cosi per il momento. Noi siamo in grado di creare dei prodotti unici nell loro stile che rapresentino il breand è i vari colori che del sito di un azienda. Non usiamo jommla ,wordpress e tanti pacchetti gia fatti, noi creiamo tutto da capo. Per ora siamo in grado di fare dei siti come questo che state leggendo, con alto livello di navigazione, responsive con multi lingua, e con una loro rapresentazione su google. Quanto costa un sito del genere mi direte? ...posso dirvi hmm ...la metà. Utiliziamo le migliori tecnologie esistenti ad oggi giorno, se non ci credete fate qualche ricercha e poi mi direte. Per concludere vi posso dire che questo blog sarra ricco di informazioni nel ambito dello sviluppo di applicazioni web utilizando java. E per i appasionati come me posso dire che troverano pane per i loro denti   \n  ','Porgo un caloroso saluto a tutti voi. Siamo riusciti a mettere in piedi questa realta perche crediamo in quello che facciamo. Oggi giorno tutti sono interesati ad avere un sito su internet. Ed é per questo he ci\nsono tante aziende che stano fiorendo come i funghi in questo contesto compresa la nostra. Ma noi abbiamo qualcosa in piu ... ','2016-02-29 11:27:22','/resources/images/portfolio/grid/f1.jpg','/news/finalmente',3,1,1);
/*!40000 ALTER TABLE `el_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_post_tag_xref`
--

DROP TABLE IF EXISTS `el_post_tag_xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_post_tag_xref` (
  `POST_TAG_XREF_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POST_ID` bigint(20) DEFAULT NULL,
  `TAG_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`POST_TAG_XREF_ID`),
  KEY `fk_el_post_tag_xref_el_post1_idx` (`POST_ID`),
  KEY `fk_el_post_tag_xref_el_tag1_idx` (`TAG_ID`),
  CONSTRAINT `fk_el_post_tag_xref_el_post1` FOREIGN KEY (`POST_ID`) REFERENCES `el_post` (`POST_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_el_post_tag_xref_el_tag1` FOREIGN KEY (`TAG_ID`) REFERENCES `el_tag` (`TAG_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_post_tag_xref`
--

LOCK TABLES `el_post_tag_xref` WRITE;
/*!40000 ALTER TABLE `el_post_tag_xref` DISABLE KEYS */;
INSERT INTO `el_post_tag_xref` VALUES (1,1,1),(2,1,3),(3,1,4);
/*!40000 ALTER TABLE `el_post_tag_xref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_role`
--

DROP TABLE IF EXISTS `el_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_role` (
  `ROLE_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_role`
--

LOCK TABLES `el_role` WRITE;
/*!40000 ALTER TABLE `el_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `el_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_tag`
--

DROP TABLE IF EXISTS `el_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_tag` (
  `TAG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `TAG_URL` varchar(255) NOT NULL,
  PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_tag`
--

LOCK TABLES `el_tag` WRITE;
/*!40000 ALTER TABLE `el_tag` DISABLE KEYS */;
INSERT INTO `el_tag` VALUES (1,'Open Source','/news/tag/opensource/'),(2,'Hibernate','/news/tag/hibernate/'),(3,'Partners','/news/tag/partners/'),(4,'Announcement','/news/tag/announcement/'),(5,'Comunity','/news/tag/comunity/'),(6,'Spring','/news/tag/spring/'),(7,'Seo','/news/tag/seo/'),(8,'All','/news/tag/');
/*!40000 ALTER TABLE `el_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_time_to_read`
--

DROP TABLE IF EXISTS `el_time_to_read`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_time_to_read` (
  `TIME_TO_READ_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VALUE` int(11) NOT NULL,
  PRIMARY KEY (`TIME_TO_READ_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_time_to_read`
--

LOCK TABLES `el_time_to_read` WRITE;
/*!40000 ALTER TABLE `el_time_to_read` DISABLE KEYS */;
INSERT INTO `el_time_to_read` VALUES (1,2),(2,4),(3,8),(4,10),(5,12);
/*!40000 ALTER TABLE `el_time_to_read` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `el_translate`
--

DROP TABLE IF EXISTS `el_translate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `el_translate` (
  `TRANSLATE_ID` bigint(20) NOT NULL,
  `ENTITY_ID` varchar(255) NOT NULL,
  `ENTITY_TYPE` varchar(255) NOT NULL,
  `FIELD_NAME` varchar(255) NOT NULL,
  `LOCALE_CODE` varchar(255) NOT NULL,
  `TRANSLATED_VALUE` longtext NOT NULL,
  PRIMARY KEY (`TRANSLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `el_translate`
--

LOCK TABLES `el_translate` WRITE;
/*!40000 ALTER TABLE `el_translate` DISABLE KEYS */;
INSERT INTO `el_translate` VALUES (97,'1','Post','title','al','me nefund'),(98,'1','Post','renderedContent','al','tekst i gjate ffhfgg gggggggggggggggggggggggggggggggggggggggggggggggg ggggggggggggg gggggggggggggggggg gggggggggggggggggggggggggggggggggg   ggggggggggggg ggggggg g ggggggggggggggggggggggggggggg ggggggggggggg ggggggggggggg ggggggggggggggggggggggg  gggggggggggg g'),(99,'1','Post','renderedSummary','al','tekst ishkurt&eumlr foi   ihèierigjerebnieob dbeito riehge0g fieorhei erihgo erihor erthruo');
/*!40000 ALTER TABLE `el_translate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-29 15:49:31
