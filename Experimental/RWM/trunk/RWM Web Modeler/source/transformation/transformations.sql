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
  `idTargetMM` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idTransformation`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `transformations`
--

LOCK TABLES `transformations` WRITE;
/*!40000 ALTER TABLE `transformations` DISABLE KEYS */;
INSERT INTO `transformations` VALUES (1,'BlueXML Model','Get a BlueXML model conforms to the metamodel BlueXML and readable with BlueXML Developer Studio','./bluexml/RWM2BlueXML.asm',1),(2,'MindMap : Agent List','Get a mind map with all agent.','./MindMap/agentList/transformation/RWM2AgentList.asm',2),(3,'MindMap : Entity List by agent','Get a mind map by agent to see the viewpoint of this agent on the information structure.','./MindMap/entityListByAgent/transformation/RWM2EntityListByAgent.asm',2),(7,'MindMap : Entity List','','./MindMap/entityList/transformation/RWM2EntityList.asm',2),(4,'Prototype','','./WebProject/transformation/RWM2WebProject.asm',3),(5,'Cahier des charges fonctionnelles','Le cahier des charges fonctionnel (CdCF) est un document formulant le besoin du client, au moyen de fonctions détaillant les services rendus par le produit et les contraintes auxquelles il est soumis.','./CdCF/transformation/RWM2CdCF.asm',4),(6,'Risk Evaluation','','./Risk/transformation/RWM2Risk.asm',5),(8,'MindMap : Goal List','','./MindMap/goalList/transformation/RWM2GoalList.asm',2),(9,'MindMap : Goal List by agent','','./MindMap/goalListByAgent/transformation/RWM2GoalListByAgent.asm',2),(10,'Diagnostic','','./Problem/transformation/RWM2Diagnostic.asm',6),(11,'Export to picture','','./Picture/transformation/RWM2Picture.asm',5);
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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `metamodels`
--

LOCK TABLES `metamodels` WRITE;
/*!40000 ALTER TABLE `metamodels` DISABLE KEYS */;
INSERT INTO `metamodels` VALUES (1,'BLUEXML','bluexml/bluexml.ecore'),(2,'MindMap','mindmap/mindmap.ecore'),(3,'WebProject','webproject/webproject.ecore'),(4,'Documentation','documentation/documentation.ecore'),(5,'RWM','rwm/rwm.ecore'),(6,'Diagnostic','diagnostic/diagnostic.ecore');
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
  `mimetype` varchar(126) collate latin1_bin NOT NULL,
  `idMM` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idGenerator`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `generator`
--

LOCK TABLES `generator` WRITE;
/*!40000 ALTER TABLE `generator` DISABLE KEYS */;
INSERT INTO `generator` VALUES (1,'Generator for FreeMind',1,'application/freemind',2),(2,'Web Site',1,'',3),(3,'PDF File generated from DocBook file',1,'application/pdf',4),(4,'Risk Evaluation',1,'image/png',5),(5,'HTML File generated from DocBook file',0,'',4),(6,'Office SpreadSheet',1,'application/application/vnd.excel',6),(7,'Export to picture',1,'image/png',5);
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
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `templateModels`
--

LOCK TABLES `templateModels` WRITE;
/*!40000 ALTER TABLE `templateModels` DISABLE KEYS */;
INSERT INTO `templateModels` VALUES (1,'FreeMind template','./mindmap/templates/freemind.emt',1),(2,'Main template','./webproject/templates/main.emt',2),(3,'Main edit template','./webproject/templates/main_edit.emt',2),(4,'data','./webproject/templates/data.emt',2),(5,'Docbook template','./documentation/templates/docbook.emt',3),(6,'SVG File','./rwm/templates/risk/svg.emt',4),(7,'Docbook template','./documentation/templates/docbook.emt',5),(8,'Main template','./diagnostic/templates/spreadsheet.emt',6),(9,'Main template','./rwm/templates/picture/svg.emt',7);
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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `antScript`
--

LOCK TABLES `antScript` WRITE;
/*!40000 ALTER TABLE `antScript` DISABLE KEYS */;
INSERT INTO `antScript` VALUES (1,'Add minimun files','./webproject/build/build.xml',2),(2,'Generation to PDF','./documentation/build/docbook-build-pdf.xml',3),(3,'Conversion to JPEG','./rwm/build/svg/build.xml',4),(4,'Generation to HTML','./documentation/build/docbook-build-html.xml',5),(5,'Conversion to JPEG','./rwm/build/svg/build.xml',7);
/*!40000 ALTER TABLE `antScript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transformations_has_generator`
--

DROP TABLE IF EXISTS `transformations_has_generator`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `transformations_has_generator` (
  `idTransformation` int(10) unsigned NOT NULL,
  `idGenerator` int(11) NOT NULL,
  PRIMARY KEY  (`idTransformation`,`idGenerator`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `transformations_has_generator`
--

LOCK TABLES `transformations_has_generator` WRITE;
/*!40000 ALTER TABLE `transformations_has_generator` DISABLE KEYS */;
INSERT INTO `transformations_has_generator` VALUES (2,1),(3,1),(4,2),(5,3),(5,5),(6,4),(7,1),(8,1),(9,1),(10,6),(11,7);
/*!40000 ALTER TABLE `transformations_has_generator` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-11-24 15:15:53
