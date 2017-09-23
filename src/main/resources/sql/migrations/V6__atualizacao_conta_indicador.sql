update  mineiro.Estado SET contaIndicador = 0 
WHERE nome in (	'Contagem Detalhada - Aprovada',
				'Contagem Detalhada - Em Elaboração',
				'Contagem Detalhada - Em Validação',
				'Contagem Detalhada - Rejeitada',
				'Contagem Detalhada - Validação Solicitada',
				'Produção - Disponibilizada')