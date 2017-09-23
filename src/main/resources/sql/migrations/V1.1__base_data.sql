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
-- Dumping data for table `Contrato`
--

LOCK TABLES `Contrato` WRITE;
/*!40000 ALTER TABLE `Contrato` DISABLE KEYS */;
INSERT INTO `Contrato` VALUES (2,'Outros','19:00:00','09:00:00','001/2010',10000.00,1000000.00,100.00);
/*!40000 ALTER TABLE `Contrato` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `DiaNaoUtil`
--

LOCK TABLES `DiaNaoUtil` WRITE;
/*!40000 ALTER TABLE `DiaNaoUtil` DISABLE KEYS */;
INSERT INTO `DiaNaoUtil` VALUES (71,1,1,'Confraternização Universal','2013-01-01 14:00:00'),(72,1,1,'Recesso forense','2013-01-02 14:00:00'),(73,1,1,'Recesso forense','2013-01-03 14:00:00'),(74,1,1,'Recesso forense','2013-01-04 14:00:00'),(75,1,1,'Recesso forense','2013-01-05 14:00:00'),(76,1,1,'Recesso forense','2013-01-06 14:00:00'),(77,0,1,'Carnaval','2013-02-11 14:00:00'),(78,0,1,'Carnaval','2013-02-12 14:00:00'),(79,0,1,'Semana Santa','2013-03-27 15:00:00'),(80,0,1,'Semana Santa','2013-03-28 15:00:00'),(81,0,1,'Semana Santa','2013-03-29 15:00:00'),(82,0,1,'Semana Santa','2013-03-30 15:00:00'),(83,0,1,'Semana Santa','2013-03-31 15:00:00'),(84,1,1,'Tiradentes','2013-04-21 15:00:00'),(85,1,1,'Dia do Trabalho','2013-05-01 15:00:00'),(86,0,1,'Corpus Christi','2013-05-30 15:00:00'),(87,1,1,'Criação dos primeiros cursos jurídicos no Brasil','2013-08-11 15:00:00'),(88,1,1,'Independência do Brasil','2013-09-07 15:00:00'),(89,1,1,'Nossa Senhora Aparecida','2013-10-12 15:00:00'),(90,1,1,'Lei 5010/1966','2013-11-01 14:00:00'),(91,1,1,'Finados','2013-11-02 14:00:00'),(92,1,1,'Proclamação da República','2013-11-15 14:00:00'),(93,1,1,'Dia da Justiça','2013-12-08 14:00:00'),(94,1,1,'Recesso forense','2013-12-20 14:00:00'),(95,1,1,'Recesso forense','2013-12-21 14:00:00'),(96,1,1,'Recesso forense','2013-12-22 14:00:00'),(97,1,1,'Recesso forense','2013-12-23 14:00:00'),(98,1,1,'Recesso forense','2013-12-24 14:00:00'),(99,1,1,'Natal','2013-12-25 14:00:00'),(100,1,1,'Recesso forense','2013-12-26 14:00:00'),(101,1,1,'Recesso forense','2013-12-27 14:00:00'),(102,1,1,'Recesso forense','2013-12-28 14:00:00'),(103,1,1,'Recesso forense','2013-12-29 14:00:00'),(104,1,1,'Recesso forense','2013-12-30 14:00:00'),(105,1,1,'Recesso forense','2013-12-31 14:00:00'),(106,0,1,'Carnaval 2015','2015-02-16 02:00:00'),(107,0,1,'Carnaval 2015','2015-02-17 02:00:00'),(108,0,1,'Semana Santa 2015','2015-04-01 03:00:00'),(109,0,1,'Semana Santa 2015','2015-04-02 03:00:00'),(110,0,1,'Semana Santa 2015','2015-04-03 03:00:00'),(111,0,1,'Corpus Christi 2015','2015-06-04 03:00:00'),(112,0,1,'Dia do Servidor','2015-10-30 02:00:00'),(113,0,1,'Carnaval 2016','2016-02-08 02:00:00'),(114,0,1,'Carnaval 2016','2016-02-09 02:00:00'),(115,0,1,'Semana Santa 2016','2016-03-23 03:00:00'),(116,0,1,'Semana Santa 2016','2016-03-24 03:00:00'),(117,0,1,'Semana Santa 2016','2016-03-25 03:00:00'),(118,0,1,'Corpus Christi 2016','2016-05-26 03:00:00');
/*!40000 ALTER TABLE `DiaNaoUtil` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `Indicador`
--

LOCK TABLES `Indicador` WRITE;
/*!40000 ALTER TABLE `Indicador` DISABLE KEYS */;
INSERT INTO `Indicador` VALUES (4,'6.6.3, id 1',2,7),(5,'6.6.1, id 2',2,8),(6,'6.6.2, id 3',2,9);
/*!40000 ALTER TABLE `Indicador` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `NivelServico`
--

LOCK TABLES `NivelServico` WRITE;
/*!40000 ALTER TABLE `NivelServico` DISABLE KEYS */;
INSERT INTO `NivelServico` VALUES (45,2,NULL,NULL,1.00,0,3,6),(44,1,NULL,NULL,1.00,NULL,1,6),(43,0,NULL,NULL,0.00,NULL,1,6),(42,2,97.00,0,0.00,1,0,5),(41,1,100.00,0,97.00,1,0,5),(40,0,NULL,NULL,100.00,NULL,1,5),(39,2,NULL,NULL,7.00,0,3,4),(38,1,7.00,1,0.00,0,0,4),(37,0,NULL,NULL,0.00,NULL,1,4);
/*!40000 ALTER TABLE `NivelServico` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `Parametro`
--

LOCK TABLES `Parametro` WRITE;
/*!40000 ALTER TABLE `Parametro` DISABLE KEYS */;
INSERT INTO `Parametro` VALUES (1,'porcentagem_minima_os','0.05');
/*!40000 ALTER TABLE `Parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Penalidade`
--

LOCK TABLES `Penalidade` WRITE;
/*!40000 ALTER TABLE `Penalidade` DISABLE KEYS */;
INSERT INTO `Penalidade` VALUES (11,'11.1, id 17',4.00,1,2),(12,'11.1, id 18',0.00,1,2),(13,'11.1, id 21',5.00,1,2),(14,'11.1, id 19',0.25,0,2),(15,'11.1, id 20',0.25,0,2),(16,'XIV_B',1.00,0,2),(17,'XIV_C',1.00,0,2),(18,'XIV_F',0.25,0,2);
/*!40000 ALTER TABLE `Penalidade` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES (1,'Gestor Técnico','GT'),(2,'Gestor do Sistema','GS'),(3,'Gestor de Qualidade','GQ'),(4,'Fábrica de Software','FS');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `TipoIndicador`
--

LOCK TABLES `TipoIndicador` WRITE;
/*!40000 ALTER TABLE `TipoIndicador` DISABLE KEYS */;
INSERT INTO `TipoIndicador` VALUES (7,'Indicador de Atraso'),(8,'Indicador de Conformidade'),(9,'Indicador de Recusa');
/*!40000 ALTER TABLE `TipoIndicador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TipoInspecao`
--

LOCK TABLES `TipoInspecao` WRITE;
/*!40000 ALTER TABLE `TipoInspecao` DISABLE KEYS */;
INSERT INTO `TipoInspecao` VALUES (45,'Proposta de Execução'),(46,'Ata de Reunião'),(47,'Documentos de Mensagem'),(48,'Documento de Visão'),(49,'Especificacão de Regras de Negócio Gerais'),(50,'Especificação Suplementar'),(51,'Especificacões de Caso de Uso e Regras de Negócio'),(52,'Glossário'),(53,'Matriz de Acesso'),(54,'Matriz de Rastreabilidade'),(55,'Modelos de casos de uso'),(56,'Arquitetura'),(57,'Modelo Entidade e Relacionamento e Dicionário de Dados'),(58,'Build'),(59,'Fontes em Java'),(60,'Fontes em PHP'),(61,'Fontes em Delphi'),(62,'Evidência de Sucesso'),(63,'Plano de Teste'),(64,'Roteiro de Teste'),(65,'Manual de Implantacao'),(66,'Notas de Release');
/*!40000 ALTER TABLE `TipoInspecao` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `ValoresContrato`
--

LOCK TABLES `ValoresContrato` WRITE;
/*!40000 ALTER TABLE `ValoresContrato` DISABLE KEYS */;
INSERT INTO `ValoresContrato` VALUES (1,2,4000.00,442.83,1771320.00,'2015-08-27 03:00:00',NULL);
/*!40000 ALTER TABLE `ValoresContrato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-04 18:47:48
