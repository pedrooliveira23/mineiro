(function($) {
    
	$(document).ready(function() {	

            $('[data-toggle="tooltip"]').tooltip(); 
		$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );

		$(".input-data").datepicker(); 
		var templatePopoverOrdemServicoIncompleta = "<div class='popover popover-ordemServico-incompleta-width'><div class='arrow'></div>" +
		"<div class='popover-inner'><h3 class='popover-title'></h3><div class='popover-content'><p></p>" +
		"</div></div></div>";
		$('.table-ordemServico-td-titulo-os-incompleta').popover({title:"<strong>Ordem de serviço sem contagem detalhada</strong>",html:true,
			content:"Somente após a inserção da contagem detalhada no redmine será possível gerar o relatório.",trigger:"hover", 
			template: templatePopoverOrdemServicoIncompleta});

		$('#table-ordemServico-th-indicador-ac').popover({title:"<strong>I<sub>AC</sub></strong>",html:true,content:"Indicador de Atraso na Conclusão da OS",trigger:"hover"});
		$('#table-ordemServico-th-indicador-c').popover({title:"<strong>I<sub>C</sub></strong>",html:true,content:"Indicador de Conformidade da OS",trigger:"hover"});
		$('#table-ordemServico-th-indicador-r').popover({title:"<strong>I<sub>R</sub></strong>",html:true,content:"Indicador de Recusa da OS",trigger:"hover"});
		
		$('#table-ordemServico-th-estimativa').popover({title:"<strong>Estimativa</strong>",html:true,content:"Data estimada de finalização da OS.",trigger:"hover"});
        $('#table-ordemServico-th-abertura').popover({title:"<strong>Abertura</strong>",html:true,content:"Data em que a OS foi demandada.",trigger:"hover"});
        $('#table-ordemServico-th-conclusao').popover({title:"<strong>Conclusão</strong>",html:true,content:"Data conclusão da OS.",trigger:"hover"});
        
        $('.table-btn-detalhar').popover({title:"<strong>Exibir Relatório Gerencial.</strong>",html:true,trigger:"hover"});
        $('.table-btn-editar').popover({title:"<strong>Editar Transições.</strong>",html:true,trigger:"hover"});
        $('.table-btn-inspecionar').popover({title:"<strong>Inspecionar OS</strong>",html:true,trigger:"hover"});
        $('.table-btn-atualizar').popover({title:"<strong>Atualizar OS</strong>",html:true,content:"Reextrair OS do SGD e recalcular níveis de serviço.",trigger:"hover"});
        
		
		var templatePopoverIndicador = "<div class='popover popover-indicador-width'><div class='arrow'></div>" +
				"<div class='popover-inner'><h3 class='popover-title'></h3><div class='popover-content'><p></p>" +
				"</div></div></div>";
		$(".table-ordemServico-td-indicador[data-nivelServico='DESEJAVEL']").popover({title:"<strong>Nível de Serviço</strong>", html:true,content:"Desejável",
			trigger:"hover", template: templatePopoverIndicador});
		$(".table-ordemServico-td-indicador[data-nivelServico='ACEITAVEL']").popover({title:"<strong>Nível de Serviço</strong>", html:true,content:"Aceitável",
			trigger:"hover", template: templatePopoverIndicador});
		$(".table-ordemServico-td-indicador[data-nivelServico='INACEITAVEL']").popover({title:"<strong>Nível de Serviço</strong>", html:true,content:"Inaceitável",
			trigger:"hover", template: templatePopoverIndicador});
		/** *****Script de autosize das textarea****** */
				
		$('textarea').autosize(); 
		
		/*******Script da Página de edição de Ordem de Serviço*******/		
		mudarCoresNiveisServico();
		
	
		var descricaoRevogadaTemplate = 
			"<fieldset id='fieldset-descricao-revogada' >" +
			"<label>Descrição Revogada</label>" +
			"<textarea id='textarea-descricao-revogada' name='descricaoRevogada' rows='3'>{{= descricao}}</textarea>" +
			"</fieldset>";
			$.template( "descricaoRevogadaTemplate", descricaoRevogadaTemplate );
	
		$("#btn-atualizarNiveisServico").live('click', function() {
	
			$("#btn-atualizarNiveisServico").submit();
			
		});	
		

		
		$("#btn-pesquisar").live('click', function() {
			
			
			$("#form-ordemServico-tempo").submit();
		});	
		
		function mudarCoresNiveisServico(){
			$(".table-ordemServico-td-indicador[data-nivelServico='DESEJAVEL']").css("background-color","#16A085");
			$(".table-ordemServico-td-indicador[data-nivelServico='ACEITAVEL']").css("background-color","#F39C12");
			$(".table-ordemServico-td-indicador[data-nivelServico='INACEITAVEL']").css("background-color","#E74C3C");
		}
		
		/*******Script da Página de edição de cronometro*******/
	
		
			
			$('.btn-selectAll').live('click', function() {
			
			$("input:checkbox").attr("checked","checked");
			$('.btn-selectAll').addClass("disabled");
			$('.btn-deselectAll').removeClass("disabled");
	
			});
			
			$('.btn-deselectAll').live('click', function() {
				
				$("input:checkbox").removeAttr("checked");
				$('.btn-deselectAll').addClass("disabled");
				$('.btn-selectAll').removeClass("disabled");
	
			});
	
	
	/** *****Script da Página de Relatório Ordem de Serviço****** */	
		
		$('#btn-refShow').live('click', function(event) {
			if($(this).attr("data-estado")==="hide"){
		
				$(this).text("Esconder Referências");
				$(this).attr("data-estado","show");
				$(".rlt-referencias").removeClass("hide");	
		
			}
			else{
				$(this).text("Exibir Referências");
				$(this).attr("data-estado","hide");
				$(".rlt-referencias").addClass("hide");
			}
			
			event.preventDefault();
		});
		
					
	
	
	
		/** *****Script da Página de Relatório Ordem de Serviço****** */	
		
		$('#btn-tipoRelatorio').live('click', function(event) {
			if($(this).attr("data-estado")==="gerencial"){
		
				$(this).text("Exibir Relatório Gerencial");
				$(this).attr("data-estado","detalhado");
				$(".span-tipoRelatorio").text("Relatório Detalhado");
				$(".div-cronometroOrdemServico").removeClass("hide");
				$(".div-inspecaoOrdemServico").removeClass("hide");
				if($("#btn-finalizar").length > 0){
					$(this).removeClass("btn-primary");
					$("#btn-finalizar").removeClass("hide");
				}
		
				
		
			}
			else{
				$(this).text("Exibir Relatório Detalhado");
				$(this).attr("data-estado","gerencial");
				$(".span-tipoRelatorio").text("Relatório Gerencial");
				$(".div-cronometroOrdemServico").addClass("hide");
				$(".div-inspecaoOrdemServico").addClass("hide");
				if($("#btn-finalizar").length > 0){
					$(this).addClass("btn-primary");
					$("#btn-finalizar").addClass("hide");
				}
		
			}
			
			event.preventDefault();
		});
		
		$('#btn-finalizar').live('click', function() {
			
			$('#modal-alerta').modal('show');
		
		
		});
		
		$('#btn-modal-confirmar').live('click', function() {
			
			$("#form-ordemServico-relatorio").submit();
		
		});
		
		/*******Script da Página de transição de Ordem de Serviço*******/	
		$('.button-expandir-tr').live('click', function() {
		
			var tr = $(this).closest('tr');
			var notaClass = $(tr).find("."+$(tr).attr("data-nota-class"));

			
			if($("."+$(tr).attr("data-nota-class")).first().hasClass('invisivel')){
				
				$("."+$(tr).attr("data-nota-class")).removeClass("invisivel");
			}
			else{
				$("."+$(tr).attr("data-nota-class")).addClass("invisivel");
			}
			
		
		});
		
		$('.button-expandir-trs').live('click', function() {
			
			var tr = $(this).closest('tr');
			var notaClass = $(".table-transicao-tr-nota");

			
			if($(".table-transicao-tr-nota").first().hasClass('invisivel')){
				
				$(".table-transicao-tr-nota").removeClass("invisivel");
			}
			else{
				$(".table-transicao-tr-nota").addClass("invisivel");
			}
			
		
		});
	
	});
	
        
        
        
    // div pf tempo
    var tempo = {
        chart: {
            renderTo: 'div_tempo_situacao',
            type: 'column',
            defaultSeriesType: 'column',
            zoomType: 'xy',
            width: 1200

        },
        title: {
            text: null
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -75,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                },
                formatter: function () {
                    return '';//this.value.toString().substring(0, 20);
                }
            }//,
            //min:0,
            //max: 50

        },
        scrollbar: {
            enabled: false
        },
        yAxis: [{// Primary yAxis
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                title: {
                    text: '',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
            }, {
                min: 0,
                title: {
                    text: 'Dias úteis'
                }
            }
        ],
        legend: {
            enabled: false
        },
        tooltip: {
            shared: true
        },
        plotOptions: {
            showInLegend: true,
            column: {
                pointWidth: null,
                borderWidth: 0,
                groupPadding: -0.1,
                shadow: true,
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    format: '{point.y:.1f} ',
                    y: -1, // 10 pixels down from the top
                    style: {
                        fontSize: '11px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            x: 120,
            verticalAlign: 'top',
            y: 100,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
        },
        series: [{}],
        scrollbar: {
            enabled: false,
            barBackgroundColor: 'gray',
            barBorderRadius: 7,
            barBorderWidth: 0,
            buttonBackgroundColor: 'gray',
            buttonBorderWidth: 0,
            buttonArrowColor: 'yellow',
            buttonBorderRadius: 7,
            rifleColor: 'yellow',
            trackBackgroundColor: 'white',
            trackBorderWidth: 1,
            trackBorderColor: 'silver',
            trackBorderRadius: 7

        }


    };

    $.getJSON($("#input-urlRaiz").val() + "grafico/ordemServico/" + $('#jsonRedmineIssueId').val() + "/situacao/tempo", function (data) {

        var nome = $.map(data.dados, function (value, key) {
            return [key];
        });
        var valor = $.map(data.dados, function (value, key) {
            return [value];
        });
        var valor2 = $.map(data.dados2, function (value, key) {
            return [value];
        });

        var nome2 = $.map(data.dados2, function (value, key) {
            return [key];
        });

        var mySeries = [];
        for (var i = 0; i < valor.length; i++) {
            var nom = nome[i];//.substring(0,6);
            mySeries.push([nom, valor[i]]);

        }
        ;

        var mySeries2 = [];
        for (var i = 0; i < valor2.length; i++) {

            var nom = nome2[i];//.substring(0,6);
            mySeries2.push([nom, valor2[i]]);
            //mySeries2.push( valor2[i]);

        }
        ;


        tempo.series = [
            {
                name: 'Tempo Gasto',
                colorByPoint: false,
                color: '#34495E',
                data: mySeries,
                tooltip: {
                    valueSuffix: ' Dias'
                }

            }, {
                name: 'Tempo Médio',
                type: 'spline',
                color: '#E74C3C',
                data: mySeries2, 
                tooltip: {
                    valueSuffix: ' Dias'
                }

            }
        ];



        var chart = new Highcharts.Chart(tempo);
    });
    // div tempo
        
        
        
        
        
        
        
           // div tempo por area
    var tempo2 = {
        chart: {
            renderTo: 'div_tempo_area',
            type: 'pie',
            plotBackgroundColor: null,
            plotBorderWidth: 1,//null,
            plotShadow: false,
            width: 1200
        },
        title: {
            text: null
        },
        
        scrollbar: {
            enabled: false
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false

                },
                showInLegend: true
            }
        },
        colors: [
                                    '#F39C12',
                                    '#E74C3C',
                                    '#16A085',
                                    '#34495E',
                                    '#9957B6',
                                    '#E67E1D'
                                    
                                ],
        series: [{}],



    };

    $.getJSON($("#input-urlRaiz").val() + "grafico/ordemServico/" + $('#jsonRedmineIssueId').val() + "/area/tempo", function (data) {

        var nome = $.map(data.dados, function (value, key) {
            return [key];
        });
        var valor = $.map(data.dados, function (value, key) {
            return [value];
        });


        var mySeries = [];
        for (var i = 0; i < valor.length; i++) {
            var nom = nome[i];
            mySeries.push([nom, valor[i]]);

        }
        ;



        tempo2.series = [
            {
                name: 'Tempo Gasto',
                type: 'pie',
                data: mySeries
            }
        ];



        var chart = new Highcharts.Chart(tempo2);
    });
    // div tempo
        
        
        
        
        
        
        
        
        
        
	
})(jQuery);
