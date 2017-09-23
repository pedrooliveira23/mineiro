alter table mineiro.TipoDemanda ADD COLUMN deflator decimal(19,9) DEFAULT 1.0;
update mineiro.TipoDemanda set deflator = 0.25 where nome ='Verificação de Erro';