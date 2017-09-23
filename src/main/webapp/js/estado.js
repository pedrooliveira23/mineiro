/** *****Script da Página de dição de Estado****** */	
		
		

		validaEstado();
		$('.btn-submit').live('click', function(event) {
			var valido = true;

			if(valido === true){
				$("#form-estado").submit();
			}
					
			
			event.preventDefault();
		});
		$('.input-checkbox-contaTempoOS').live('change', function(event) {
			validaEstado();
			event.preventDefault();
		});
		
		$('.input-checkbox-contaRecusaOS').live('change', function(event) {
			validaEstado();
			event.preventDefault();	
		});
		

		
		

		
		
		function validaEstado(){
			var valido = true;
			$("tr").each(function( index ) {
				if($('.input-checkbox-contaTempoOS', $(this)).prop("checked") === false && $('.input-checkbox-contaRecusaOS', $(this)).prop("checked") === true){
					$(".table-estado-td",$(this)).css("color", "red");
					valido = false;

					if($(".alert-error").length < 1 ){
						$(".alert-info").remove();
						$("<div class='alert alert-error'><strong>Existem estados não válido!</strong> Somente será possível atualizar os estados após a correção dessas inconsistências. </div>").appendTo("#div-alert");
					}
					
				}
				else
				{
					$(".table-estado-td",$(this)).css("color", "black");
				}
			});
			if(valido === true){
				
				if($(".alert-info").length < 1 ){
					$(".alert-error").remove();
					$("<div class='alert alert-info'><strong>Cuidado!</strong> As alterações desses estados irão impactar todo o sistema. </div>").appendTo("#div-alert");
				}
				
	

			}
			return valido;
			
		}