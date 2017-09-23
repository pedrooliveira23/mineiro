-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: tiziu    Database: mineiro
-- ------------------------------------------------------
-- Server version	5.5.31-log



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
-- Table structure for table `Contrato`
--

DROP TABLE IF EXISTS `Contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contrato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contratada` varchar(255) NOT NULL,
  `fimExpediente` time NOT NULL,
  `inicioExpediente` time NOT NULL,
  `numero` varchar(255) NOT NULL,
  `quantitativo` decimal(19,2) NOT NULL,
  `valorTotal` decimal(19,2) NOT NULL,
  `valorUnitario` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Demanda`
--

DROP TABLE IF EXISTS `Demanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Demanda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cancelada` tinyint(1) DEFAULT NULL,
  `concluida` tinyint(1) DEFAULT NULL,
  `conformidade` decimal(19,2) DEFAULT NULL,
  `contagemDetalhada` decimal(19,2) DEFAULT NULL,
  `contagemEstimada` decimal(19,2) DEFAULT NULL,
  `dataAtualizacao` datetime NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataFinalizacao` datetime DEFAULT NULL,
  `descricao` text,
  `tipoDuracao_atraso` int(11) DEFAULT NULL,
  `duracaoAtraso` varchar(255) DEFAULT NULL,
  `tipoDuracao_contandoTempoCJF` int(11) DEFAULT NULL,
  `duracao_contandoTempoCJF` varchar(255) DEFAULT NULL,
  `tipoDuracao_contandoTempoContratada` int(11) DEFAULT NULL,
  `duracao_contandoTempoContratada` varchar(255) DEFAULT NULL,
  `tipoDuracao_contandoTempoOS` int(11) DEFAULT NULL,
  `duracao_contandoTempoOS` varchar(255) DEFAULT NULL,
  `tipoDuracao_prazoMaximo` int(11) DEFAULT NULL,
  `duracao_prazoMaximo` varchar(255) DEFAULT NULL,
  `tipoDuracao_total` int(11) DEFAULT NULL,
  `duracao_total` varchar(255) DEFAULT NULL,
  `incompleta` tinyint(1) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `percentualAtraso` decimal(19,2) DEFAULT NULL,
  `quantidadeRecusas` int(11) DEFAULT NULL,
  `redmineIssueId` int(11) NOT NULL,
  `tipoSistema` int(11) DEFAULT NULL,
  `tipoDemanda_id` int(11) DEFAULT NULL,
  `dataPrevista` datetime DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `redmineIssueId` (`redmineIssueId`),
  KEY `idx_contagemDetalhada` (`contagemDetalhada`),
  KEY `idx_contagemEstimada` (`contagemEstimada`),
  KEY `idx_redmineIssueId` (`redmineIssueId`),
  KEY `FKBFAEF7568A169BA7` (`tipoDemanda_id`)
) ENGINE=MyISAM AUTO_INCREMENT=244 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DiaNaoUtil`
--

DROP TABLE IF EXISTS `DiaNaoUtil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DiaNaoUtil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anual` tinyint(1) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `dia` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dia` (`dia`),
  KEY `idx_dia` (`dia`)
) ENGINE=MyISAM AUTO_INCREMENT=119 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Estado`
--

DROP TABLE IF EXISTS `Estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concluido` tinyint(1) DEFAULT NULL,
  `contaRecusaOS` tinyint(1) DEFAULT NULL,
  `contaTempoOS` tinyint(1) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `redmineStatusId` int(11) DEFAULT NULL,
  `mediaTempoPorPF` float DEFAULT NULL COMMENT 'Coluna para armazenar o tempo médio que as OSs gastam no estado por ponto de função.',
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`),
  UNIQUE KEY `redmineStatusId` (`redmineStatusId`),
  KEY `idx_contaRecusaOS` (`contaRecusaOS`),
  KEY `idx_contaTempoOS` (`contaTempoOS`),
  KEY `idx_redmineStatusId` (`redmineStatusId`)
) ENGINE=MyISAM AUTO_INCREMENT=541 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Indicador`
--

DROP TABLE IF EXISTS `Indicador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Indicador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clausula` varchar(255) NOT NULL,
  `contrato_id` int(11) DEFAULT NULL,
  `tipoIndicador_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4D01A1DF721B960D` (`contrato_id`),
  KEY `FK4D01A1DF7AC6EBC7` (`tipoIndicador_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Inspecao`
--

DROP TABLE IF EXISTS `Inspecao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Inspecao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artefatoInspecionado` varchar(255) DEFAULT NULL,
  `dia` datetime DEFAULT NULL,
  `percentualAprovacao` decimal(19,2) DEFAULT NULL,
  `demanda_id` int(11) DEFAULT NULL,
  `tipoInspecao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24BA42AED85A0EED` (`tipoInspecao_id`),
  KEY `FK24BA42AEBB9265C7` (`demanda_id`)
) ENGINE=MyISAM AUTO_INCREMENT=365 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `NivelServico`
--

DROP TABLE IF EXISTS `NivelServico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NivelServico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoriaNivelServico` int(11) NOT NULL,
  `fim` decimal(19,2) DEFAULT NULL,
  `fimDentroIntervalo` tinyint(1) DEFAULT NULL,
  `inicio` decimal(19,2) DEFAULT NULL,
  `inicioDentroIntervalo` tinyint(1) DEFAULT NULL,
  `tipoFaixa` int(11) DEFAULT NULL,
  `indicador_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2F42F07D3C78ADE7` (`indicador_id`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Nota`
--

DROP TABLE IF EXISTS `Nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Nota` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `autor` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `dataCriacao` datetime NOT NULL,
  `nota` longtext CHARACTER SET latin1,
  `redmineJournalId` int(11) NOT NULL,
  `transicao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `redmineJournalId` (`redmineJournalId`),
  KEY `FK25240E7920AC47` (`transicao_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3978 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OrdemServico`
--

DROP TABLE IF EXISTS `OrdemServico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrdemServico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `GlosaAtraso` decimal(19,2) DEFAULT NULL,
  `GlosaConformidade` decimal(19,2) DEFAULT NULL,
  `GlosaRecusa` decimal(19,2) DEFAULT NULL,
  `autorFinalizacao` varchar(255) DEFAULT NULL,
  `multaAtraso` decimal(19,2) DEFAULT NULL,
  `nivelServicoAtraso` varchar(255) DEFAULT NULL,
  `nivelServicoConformidade` varchar(255) DEFAULT NULL,
  `nivelServicoRecusa` varchar(255) DEFAULT NULL,
  `situacao` varchar(255) DEFAULT NULL,
  `valorBruto` decimal(19,2) DEFAULT NULL,
  `valorBrutoDeflacionado` decimal(19,2) DEFAULT NULL,
  `valorGlosaAtraso` decimal(19,2) DEFAULT NULL,
  `valorGlosaConformidade` decimal(19,2) DEFAULT NULL,
  `valorGlosaRecusa` decimal(19,2) DEFAULT NULL,
  `valorMultaAtraso` decimal(19,2) DEFAULT NULL,
  `valorTotal` decimal(19,2) DEFAULT NULL,
  `contrato_id` int(11) DEFAULT NULL,
  `demanda_id` int(11) DEFAULT NULL,
  `projeto_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKADB61756E4FDEAA7` (`projeto_id`),
  KEY `FKADB61756721B960D` (`contrato_id`),
  KEY `FKADB61756BB9265C7` (`demanda_id`)
) ENGINE=MyISAM AUTO_INCREMENT=243 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Parametro`
--

DROP TABLE IF EXISTS `Parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Parametro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `valor` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Penalidade`
--

DROP TABLE IF EXISTS `Penalidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Penalidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clausula` varchar(255) NOT NULL,
  `percentual` decimal(19,2) NOT NULL,
  `tipoPenalidade` int(11) NOT NULL,
  `contrato_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4232D543721B960D` (`contrato_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Projeto`
--

DROP TABLE IF EXISTS `Projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Projeto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `linguagem` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `redmineProjectId` int(11) NOT NULL,
  `redmineProjectParentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `redmineProjectId` (`redmineProjectId`),
  KEY `idx_redmineProjectId` (`redmineProjectId`)
) ENGINE=MyISAM AUTO_INCREMENT=418 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `sigla` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TempoRolesDemanda`
--

DROP TABLE IF EXISTS `TempoRolesDemanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TempoRolesDemanda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `demanda_id` int(11) NOT NULL,
  `tempo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=214 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TipoDemanda`
--

DROP TABLE IF EXISTS `TipoDemanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoDemanda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `redmineTrackerId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`),
  UNIQUE KEY `redmineTrackerId` (`redmineTrackerId`),
  KEY `idx_redmineTrackerId` (`redmineTrackerId`)
) ENGINE=MyISAM AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TipoIndicador`
--

DROP TABLE IF EXISTS `TipoIndicador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoIndicador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TipoInspecao`
--

DROP TABLE IF EXISTS `TipoInspecao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoInspecao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=MyISAM AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Transicao`
--

DROP TABLE IF EXISTS `Transicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Transicao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contaRecusaOS` tinyint(1) DEFAULT NULL,
  `contaTempoOS` tinyint(1) DEFAULT NULL,
  `dataFim` datetime DEFAULT NULL,
  `dataInicio` datetime NOT NULL,
  `tipoDuracao_contandoTempo_transicao` int(11) DEFAULT NULL,
  `duracao_contandoTempo_transicao` varchar(255) DEFAULT NULL,
  `tipoDuracao__transicao` int(11) DEFAULT NULL,
  `duracao_transicao` varchar(255) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `descricao` longtext,
  `demanda_id` int(11) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_contaRecusaOS` (`contaRecusaOS`),
  KEY `idx_contaTempoOS` (`contaTempoOS`),
  KEY `FKB631B95038C1D28D` (`estado_id`),
  KEY `FKB631B950BB9265C7` (`demanda_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6297 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ValoresContrato`
--

DROP TABLE IF EXISTS `ValoresContrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ValoresContrato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contrato_id` int(11) NOT NULL,
  `quantitativo` decimal(19,2) NOT NULL,
  `valorUnitario` decimal(19,2) NOT NULL,
  `valorTotal` decimal(19,2) NOT NULL,
  `dataInicioVigencia` datetime NOT NULL,
  `dataFimVigencia` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ValoresContrato` (`contrato_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-04 18:47:12
