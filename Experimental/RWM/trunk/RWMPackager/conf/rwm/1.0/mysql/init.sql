-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Ven 09 Janvier 2009 à 10:31
-- Version du serveur: 5.0.51
-- Version de PHP: 5.2.5

CREATE DATABASE IF NOT EXISTS @DataBase@;

USE @DataBase@;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `rwm`
--

-- --------------------------------------------------------

--
-- Structure de la table `antScript`
--

CREATE TABLE IF NOT EXISTS `antScript` (
  `idScript` int(10) unsigned NOT NULL auto_increment,
  `description` varchar(256) collate latin1_bin NOT NULL,
  `fileName` varchar(256) collate latin1_bin NOT NULL,
  `idGenerator` int(11) NOT NULL,
  PRIMARY KEY  (`idScript`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Structure de la table `generator`
--

CREATE TABLE IF NOT EXISTS `generator` (
  `idGenerator` int(11) NOT NULL auto_increment,
  `label` varchar(256) collate latin1_bin NOT NULL,
  `generationMustBeDownloaded` tinyint(4) NOT NULL,
  `mimetype` varchar(126) collate latin1_bin NOT NULL,
  `idMM` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idGenerator`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Structure de la table `metamodels`
--

CREATE TABLE IF NOT EXISTS `metamodels` (
  `idMM` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(256) collate latin1_bin NOT NULL,
  `mm_file` varchar(256) collate latin1_bin NOT NULL,
  PRIMARY KEY  (`idMM`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `models`
--

CREATE TABLE IF NOT EXISTS `models` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(254) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=66 ;

-- --------------------------------------------------------

--
-- Structure de la table `templateModels`
--

CREATE TABLE IF NOT EXISTS `templateModels` (
  `idTemplate` int(10) unsigned NOT NULL auto_increment,
  `label` varchar(256) collate latin1_bin NOT NULL,
  `fileName` varchar(256) collate latin1_bin NOT NULL,
  `idGenerator` int(11) NOT NULL,
  PRIMARY KEY  (`idTemplate`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Structure de la table `transformations`
--

CREATE TABLE IF NOT EXISTS `transformations` (
  `idTransformation` int(10) unsigned NOT NULL auto_increment,
  `label` varchar(256) collate latin1_bin NOT NULL,
  `description` text collate latin1_bin NOT NULL,
  `asm` varchar(256) collate latin1_bin NOT NULL,
  `idTargetMM` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idTransformation`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Structure de la table `transformations_has_generator`
--

CREATE TABLE IF NOT EXISTS `transformations_has_generator` (
  `idTransformation` int(10) unsigned NOT NULL,
  `idGenerator` int(11) NOT NULL,
  PRIMARY KEY  (`idTransformation`,`idGenerator`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Structure de la table `versions`
--

CREATE TABLE IF NOT EXISTS `versions` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `majorVersion` int(11) NOT NULL,
  `minorVersion` int(11) NOT NULL,
  `comment` text character set latin1 NOT NULL,
  `idModel` int(10) unsigned NOT NULL,
  `value` longtext NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=276 ;
