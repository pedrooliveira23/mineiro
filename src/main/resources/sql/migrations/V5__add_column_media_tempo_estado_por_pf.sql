alter table mineiro.Estado ADD COLUMN mediaTempoPorPFInd float DEFAULT  0.0  COMMENT 'Coluna para armazenar o tempo médio que as OSs gastam no estado por ponto de função considerando todas OSs, mesmo aquelas que não passaram pelo status.' ;
alter table mineiro.Estado ADD COLUMN contaIndicador tinyint(1) DEFAULT 1 COMMENT 'Contabilizar ou não o status para efeito do índice.' ;