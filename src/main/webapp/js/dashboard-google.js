/*******Script da Página Inicial*******/

 // Load the Visualization API and the piechart package.
	      google.load('visualization', '1.0', {'packages':['corechart']});
           
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawVisualization);

	      // Callback that creates and populates a data table,
	      // instantiates the pie chart, passes in the data and
	      // draws it.
	      		function drawVisualization() {
			 
	 			
					$.getJSON($("#input-urlRaiz").val()+"/grafico/ordemServico/nivelServico/conformidade", function(data) {
						
						 var wrapperConformidade= new google.visualization.ChartWrapper({
							    chartType: 'PieChart',
							    dataTable: [['Nível de Serviço','Quantidade de OSs'],['Desejável', data.dados.desejavel ], ['Aceitável', data.dados.aceitavel], ['Inaceitável', data.dados.inaceitavel]],
							    options: {sliceVisibilityThreshold: 0, fontSize: 14, colors: ['#00B200', 'orange', '#DF0000'], legend: {position: 'bottom'}, 'legendFontSize':14, 'titleFontSize':14, 'tooltipFontSize':14, 'width':370, 'height':300, 'chartArea': {'width': '100%', 'height': '80%'}},
							    containerId: 'div_conformidade'
							  });
							  wrapperConformidade.draw();  
					});	
					
					$.getJSON($("#input-urlRaiz").val()+"/grafico/ordemServico/nivelServico/atraso", function(data) {
						
						 var wrapperAtraso= new google.visualization.ChartWrapper({
							    chartType: 'PieChart',
							    dataTable: [['Nível de Serviço','Quantidade de OSs'],['Desejável', data.dados.desejavel ], ['Aceitável', data.dados.aceitavel], ['Inaceitável', data.dados.inaceitavel]],
							    options: {sliceVisibilityThreshold: 0, fontSize: 14, colors: ['#00B200', 'orange', '#DF0000'], legend: {position: 'bottom'}, 'legendFontSize':14, 'titleFontSize':14, 'tooltipFontSize':14, 'width':370, 'height':300, 'chartArea': {'width': '100%', 'height': '80%'}},
							    containerId: 'div_atraso'
							  });
							  wrapperAtraso.draw();  
					});
				 	 
					$.getJSON($("#input-urlRaiz").val()+"/grafico/ordemServico/nivelServico/recusa", function(data) {
						
					
						 var wrapperRecusa= new google.visualization.ChartWrapper({
							    chartType: 'PieChart',
							    dataTable: [['Nível de Serviço','Quantidade de OSs'],['Desejável', data.dados.desejavel ], ['Aceitável', data.dados.aceitavel], ['Inaceitável', data.dados.inaceitavel]],
							    options: {sliceVisibilityThreshold: 0, fontSize: 14, colors: ['#00B200', 'orange', '#DF0000'], legend: {position: 'bottom'}, 'legendFontSize':14, 'titleFontSize':14, 'tooltipFontSize':14, 'width':370, 'height':300, 'chartArea': {'width': '100%', 'height': '80%'}},
							    containerId: 'div_recusa'
							  });
							  wrapperRecusa.draw();  
					});
				 	 
					$.getJSON($("#input-urlRaiz").val()+"/grafico/ordemServico/pontosFuncao/consumo", function(data) {
						
						
						 var wrapperRecusa= new google.visualization.ChartWrapper({
							    chartType: 'PieChart',
							    dataTable: [['',''],['Consumido', data.dados.consumido ], ['Previsto', data.dados.previsto], ['Não Consumido', data.dados.naoConsumido]],
							    options: {sliceVisibilityThreshold: 0, fontSize: 14, legend: {position: 'bottom'}, 'legendFontSize':14, 'titleFontSize':14, 'tooltipFontSize':14, 'width':370, 'height':300, 'chartArea': {'width': '100%', 'height': '80%'}},
							    containerId: 'div_pf_consumo'
							  });
							  wrapperRecusa.draw();  
					});
					
					$.getJSON($("#input-urlRaiz").val()+"/grafico/ordemServico/pontosFuncao/linguagem", function(data) {
						
						
						 var wrapperPF= new google.visualization.ChartWrapper({
							    chartType: 'ColumnChart',
							    dataTable: [['Linguagem', 'Php', 'Java', 'Mumps', 'Delphi'],
							                ['Quantitativo de Pontos de Função', data.dados.php, data.dados.java, data.dados.mumps, data.dados.delphi]],
										    options: {legend: {position: 'bottom'}, vAxis: {title: "Pontos de Função",titleTextStyle:{fontSize:14},gridlines:{count:4}}, 'axisFontSize':14, 'legendFontSize':14, 'titleFontSize':14, 'tooltipFontSize':14, 'width':400, 'height':300, 'chartArea': {'width': '80%', 'height': '80%'}},							    
										    containerId: 'div_pf_linguagem'
							  });
						wrapperPF.draw();  
					});
					
					
					$.getJSON($("#input-urlRaiz").val()+"/grafico/ordemServico/pontosFuncao/tempo", function(data) {
						
						var mesAno = $.map(data.dados, function(value, key) {
						    return [key];
						});
						var valor = $.map(data.dados, function(value, key) {
						    return [value];
						});
						mesAno.unshift("Ano/Mês");
						valor.unshift("Quantitativo de Pontos de Função");
						console.log(mesAno);
						console.log(valor);
						
						 var wrapperPF= new google.visualization.ChartWrapper({
							    chartType: 'ColumnChart',
							    dataTable: [mesAno, valor],
										    options: {legend: {position: 'bottom'}, vAxis: {title: "Pontos de Função",titleTextStyle:{fontSize:14},gridlines:{count:4}}, 'axisFontSize':14, 'legendFontSize':14, 'titleFontSize':14, 'tooltipFontSize':14, 'width':400, 'height':300, 'chartArea': {'width': '80%', 'height': '80%'}},							    
										    containerId: 'div_pf_mes'
							  });
						wrapperPF.draw();  
				
					});
		

				
			
					  
	      }