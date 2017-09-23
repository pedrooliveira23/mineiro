/** *****Script da Página de Inspecao****** */	 
$(document).ready(function() {		 

			if($('#select-tipoInspecao').length > 0)
					$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );

  					$(".input-data").datepicker(); 
	      			
	      			
	      			$('#btn-adicionar-diaNaoUtil').live('click', function() {
	      				
	      				$('#modal-diaNaoUtil').modal('show');

	      			});
	      			

	      			setQuantidadeItensInspecao();	
	    		$('#select-tipoInspecao').live("change", function() {
	    					setQuantidadeItensInspecao()
	    		});	  
	    		function setQuantidadeItensInspecao(){
	    			
	    	

					$.getJSON($("#input-urlRaiz").val()+"tipoInspecao/"+$('#select-tipoInspecao').val(), function(data) {
					
						$("#span-quantidadeItensInspecao").text(" / "+data.quantidadeItens);
					});
				}
				

	});