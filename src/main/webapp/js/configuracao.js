$('#btn-enviar-email').live('click', function() {			

		$.getJSON($(this).attr("data-url"), function(data) {
		
			$("#div-alert").empty();
			if(data.sucesso===true){
				$("<div class='alert alert-success'>"+data.mensagem+"</div>").appendTo("#div-alert");
			}
			else{
				$("<div class='alert alert-error'>"+data.mensagem+"</div>").appendTo("#div-alert");
			}
		});
});		