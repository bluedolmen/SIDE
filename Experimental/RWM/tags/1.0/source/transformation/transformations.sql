-- MySQL dump 10.11
--
-- Host: localhost    Database: rwm
-- ------------------------------------------------------
-- Server version	5.0.51a

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
-- Table structure for table `transformations`
--

DROP TABLE IF EXISTS `transformations`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `transformations` (
  `idTransformation` int(10) unsigned NOT NULL auto_increment,
  `label` varchar(256) collate latin1_bin NOT NULL,
  `description` text collate latin1_bin NOT NULL,
  `asm` varchar(256) collate latin1_bin NOT NULL,
  `idTargetMM` int(11) NOT NULL,
  PRIMARY KEY  (`idTransformation`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `transformations`
--

LOCK TABLES `transformations` WRITE;
/*!40000 ALTER TABLE `transformations` DISABLE KEYS */;
INSERT INTO `transformations` VALUES (1,'BlueXML Model','Get a BlueXML model conforms to the metamodel BlueXML and readable with BlueXML Developer Studio','./transformations/bluexml/RWM2BlueXML.asm',1),(2,'FreeMind : List of agent','Get a mind map (FreeMind) with all agent.','./transformations/freemind/freemind_listofagents.asm',2),(3,'FreeMind : List of entities by agent','Get a mind map (FreeMind) by agent to see the viewpoint of this agent on the information structure.','./transformations/freemind/freemind_listofentitiesbyagent.asm',2),(4,'Get prototype','Get prototype','./transformations/prototype/prototype.asm',3),(5,'DocBook','DocBook is a semantic markup language for technical documentation. It was originally intended for writing technical documents related to computer hardware and software but it can be used for any other sort of documentation.','./transformations/docbook/RWM2DocBook.asm',4),(6,'Risk Evaluation','','./transformations/svg/risk/rwm2risk.asm',5);
/*!40000 ALTER TABLE `transformations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metamodels`
--

DROP TABLE IF EXISTS `metamodels`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `metamodels` (
  `idMM` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(256) collate latin1_bin NOT NULL,
  `mm_file` varchar(256) collate latin1_bin NOT NULL,
  PRIMARY KEY  (`idMM`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `metamodels`
--

LOCK TABLES `metamodels` WRITE;
/*!40000 ALTER TABLE `metamodels` DISABLE KEYS */;
INSERT INTO `metamodels` VALUES (1,'BLUEXML','./metamodels/bluexml.ecore'),(2,'FreeMind','./metamodels/freemind.ecore'),(3,'WebSite','./metamodels/website.ecore'),(4,'DocBook','./metamodels/docbook.ecore'),(5,'SVG','./metamodels/SVG.ecore');
/*!40000 ALTER TABLE `metamodels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generator`
--

DROP TABLE IF EXISTS `generator`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `generator` (
  `idGenerator` int(11) NOT NULL auto_increment,
  `label` varchar(256) collate latin1_bin NOT NULL,
  `generationMustBeDownloaded` tinyint(4) NOT NULL,
  `idMM` int(11) NOT NULL,
  PRIMARY KEY  (`idGenerator`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `generator`
--

LOCK TABLES `generator` WRITE;
/*!40000 ALTER TABLE `generator` DISABLE KEYS */;
INSERT INTO `generator` VALUES (1,'Generator for FreeMind',1,2),(2,'Web Site',1,3),(3,'DocBook File',1,4),(4,'Risk Evaluation',1,5);
/*!40000 ALTER TABLE `generator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `templateModels`
--

DROP TABLE IF EXISTS `templateModels`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `templateModels` (
  `idTemplate` int(10) unsigned NOT NULL auto_increment,
  `label` varchar(256) collate latin1_bin NOT NULL,
  `fileName` varchar(256) collate latin1_bin NOT NULL,
  `idGenerator` int(11) NOT NULL,
  PRIMARY KEY  (`idTemplate`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `templateModels`
--

LOCK TABLES `templateModels` WRITE;
/*!40000 ALTER TABLE `templateModels` DISABLE KEYS */;
INSERT INTO `templateModels` VALUES (1,'Main template','./transformations/freemind/freemind.emt',1),(2,'Main template','./transformations/prototype/main.emt',2),(3,'Main edit template','./transformations/prototype/main_edit.emt',2),(4,'data','./transformations/prototype/data.emt',2),(5,'Main template','./transformations/docbook/docbook.emt',3),(6,'SVG File','./transformations/svg/toSVG.emt',4);
/*!40000 ALTER TABLE `templateModels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `antScript`
--

DROP TABLE IF EXISTS `antScript`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `antScript` (
  `idScript` int(10) unsigned NOT NULL auto_increment,
  `description` varchar(256) collate latin1_bin NOT NULL,
  `fileName` varchar(256) collate latin1_bin NOT NULL,
  `idGenerator` int(11) NOT NULL,
  PRIMARY KEY  (`idScript`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `antScript`
--

LOCK TABLES `antScript` WRITE;
/*!40000 ALTER TABLE `antScript` DISABLE KEYS */;
INSERT INTO `antScript` VALUES (1,'Add minimun files','../transformations/prototype/min_configuration/build.xml',2),(2,'Generation to PDF','../transformations/docbook/build.xml',3),(3,'Conversion to JPEG','../transformations/svg/build.xml',4);
/*!40000 ALTER TABLE `antScript` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-10-23  9:48:09
