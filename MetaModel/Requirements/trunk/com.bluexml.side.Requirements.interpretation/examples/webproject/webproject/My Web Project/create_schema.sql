-- Script generated
-- Database : reqs_prototype

-- --------------------------------------------------------
--
-- Table structure for table `Nomenclature`
--

CREATE TABLE IF NOT EXISTS `Nomenclature` (
  `id_7Sarf3VL5T` int(10) auto_increment,
  `quantitparcomposant` real ,
  PRIMARY KEY  (`id_7Sarf3VL5T`)
);

--
-- Table structure for table `Composant`
--

CREATE TABLE IF NOT EXISTS `Composant` (
  `id_T9OM7CuvcS` int(10) auto_increment,
  `description` varchar(256) ,
  `fournisseur` varchar(256) ,
  `rfrencefournisseur` varchar(256) ,
  `rfrenceinterne` varchar(256) ,
  PRIMARY KEY  (`id_T9OM7CuvcS`)
);

--
-- Table structure for table `NomenclatureERP`
--

CREATE TABLE IF NOT EXISTS `NomenclatureERP` (
  `id_1e1eT4uesR` int(10) auto_increment,
  `eststable` varchar(256) ,
  `quantitparcomposant` real ,
  PRIMARY KEY  (`id_1e1eT4uesR`)
);

--
-- Table structure for table `ComposantERP`
--

CREATE TABLE IF NOT EXISTS `ComposantERP` (
  `id_cVQ46PIOKe` int(10) auto_increment,
  `description` varchar(256) ,
  `reffournisseur` varchar(256) ,
  `refinterne` varchar(256) ,
  `fournisseur` varchar(256) ,
  PRIMARY KEY  (`id_cVQ46PIOKe`)
);

--
-- Table structure for table `Nomenclature2Composant`
--

CREATE TABLE IF NOT EXISTS `Nomenclature2Composant` (
  `id_7Sarf3VL5T` int(10) ,
  `id_T9OM7CuvcS` int(10) ,
  PRIMARY KEY  (`id_7Sarf3VL5T`,`id_T9OM7CuvcS`)
);

--
-- Table structure for table `NomenclatureERP2ComposantERP`
--

CREATE TABLE IF NOT EXISTS `NomenclatureERP2ComposantERP` (
  `id_1e1eT4uesR` int(10) ,
  `id_cVQ46PIOKe` int(10) ,
  PRIMARY KEY  (`id_1e1eT4uesR`,`id_cVQ46PIOKe`)
);

