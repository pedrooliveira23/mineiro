/*******Script da Página Inicial*******/


             
                        $(document).ready(function() {
                           
                         
                            // div atraso
                            
                              var atraso = {
                                
                               chart: {
                               renderTo: 'div_atraso',
                               plotBackgroundColor: null,
                               plotBorderWidth: 1,//null,
                               plotShadow: false,
                           },
                           
                          
                           
                           
                           title: {
                               text: null
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
                                    '#16a085',
                                    '#f39c12',
                                    '#e74c3c'
                                ],
                           
                           
                               series: [{}]
                                
                                
                            };

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/nivelServico/atraso", function(data) {
                             
                                atraso.series = [{
                               type: 'pie',
                               name: 'Nível de Serviço de atraso Global',
                               data: [
                                   {
                                    name: 'Desejável',
                                    y: data.dados.desejavel,
                                    sliced: true,
                                    selected: true
                                },
                                 
                                 
                                   ['Aceitável', data.dados.aceitavel],
                                   ['Inaceitável', data.dados.inaceitavel]
                               ]
                           }];
                                var chart2 = new Highcharts.Chart(atraso);
                            });
                            // div atraso
                            
                             // div recusa
                            
                              var recusa = {
                                
                               chart: {
                               renderTo: 'div_recusa',
                               plotBackgroundColor: null,
                               plotBorderWidth: 1,//null,
                               plotShadow: false
                           },
                           title: {
                               text: null
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
                                    '#16a085',
                                    '#f39c12',
                                    '#e74c3c'
                                ],
                               series: [{}]
                                
                                
                            };

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/nivelServico/recusa", function(data) {
                              
                                recusa.series = [{
                               type: 'pie',
                               name: 'Nível de Serviço de recusa Global',
                               data: [
                                   {
                                    name: 'Desejável',
                                    y: data.dados.desejavel,
                                    sliced: true,
                                    selected: true
                                },
                                 
                                 
                                   ['Aceitável', data.dados.aceitavel],
                                   ['Inaceitável', data.dados.inaceitavel]
                               ]
                           }];
                                var chart2 = new Highcharts.Chart(recusa);
                            });
                            // div recusa
                            
                              // div conformidade
                            var conformidade = {
                                
                               chart: {
                               renderTo: 'div_conformidade',
                               plotBackgroundColor: null,
                               plotBorderWidth: 1,//null,
                               plotShadow: false,
                           },
                           title: {
                               text: null
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
                                    '#16a085',
                                    '#f39c12',
                                    '#e74c3c'
                                ],
                               series: [{}]
                                
                                
                            };

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/nivelServico/conformidade", function(data) {
                              
                                conformidade.series = [{
                               type: 'pie',
                               name: 'Nível de Serviço de Conformidade Global',
                                
                               data: [
                                   {
                                    name: 'Desejável',
                                    y: data.dados.desejavel,
                                    sliced: true,
                                    selected: true
                                },
                                 
                                 
                                   ['Aceitável', data.dados.aceitavel],
                                   ['Inaceitável', data.dados.inaceitavel]
                               ]
                           }];
                                var chart = new Highcharts.Chart(conformidade);
                            });
                            // div conformidade
                            
                            
                             // div pf consumo
                            var consumo = {
                                
                               chart: {
                               renderTo: 'div_pf_consumo',
                               plotBackgroundColor: null,
                               plotBorderWidth: 1,//null,
                               plotShadow: false
                           },
                           title: {
                               text: null
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
                                    '#34495E',
                                    '#e74c3c',
                                    '#f39c12'
                                ],
                               series: [{}]
                                
                                
                            };

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/pontosFuncao/consumo", function(data) {
                              
                                consumo.series = [{
                               type: 'pie',
                               name: 'Quantitativo de Consumo de Pontos de Função',
                             
                               data: [
                                   {
                                    name: 'Consumido ('+data.dados.consumido+' PF)',
                                    y: data.dados.consumido,
                                    sliced: true,
                                    selected: true
                                },
                                 
                                 
                                   ['Previsto ('+ data.dados.previsto+' PF)', data.dados.previsto],
                                   ['Não Consumido ('+data.dados.naoConsumido+' PF)', data.dados.naoConsumido]
                               ]
                           }];
                                var chart = new Highcharts.Chart(consumo);
                            });
                            // div consumo
                            
                            
                            
                             // div pf linguagem
                            var linguagem = {
                                
                               chart: {
                               type: 'column',
                               renderTo: 'div_pf_linguagem',
                               plotBackgroundColor: null,
                               plotBorderWidth: 1,//null,
                               plotShadow: false
                           },
                           title: {
                               text: null
                           },
                           
                           xAxis: {
                                type: 'category'
                              
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: 'Pontos de Função'
                                }
                            },
                            legend: {
                                        enabled: false
                                    },
                           tooltip: {
                                    
                                   
                              pointFormat: '{series.name}: <b>{point.y:.1f} pf</b>'
                                },
                           plotOptions: {
                               column: {
                                          
                                           borderWidth: 0,
                                           pointWidth: 50,
                                            dataLabels: {
                                                            enabled: true,
                                                            format: '{point.y:.1f} pf'
                                                        }
                                        }
                           },
                           
                           colors: [
                                    
                                    '#E74C3C',
                                    '#F39C12',
                                    '#16A085',
                                    '#34495E'
                                    
                                    
                                ],
                               series: [{}]
                                
                                
                            };

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/pontosFuncao/linguagem", function(data) {
                              
                                linguagem.series = [
                                  
                                    {                              
                                       name: 'Linguagem',
                                       colorByPoint: true,
                                       data: [
                                           ['Php',data.dados.php],
                                           ['Java',data.dados.java],
                                           ['Mumps',data.dados.mumps],
                                           ['Delphi',data.dados.delphi]
                                       ]
                                   }
                       
                                ];
                                var chart = new Highcharts.Chart(linguagem);
                            });
                            // div linguagem
                            
                            
                            
                            
                            
                            
                            
                            // div pf por estado
                            var estado = {
                                
                               chart: {
                               type: 'column',
                               renderTo: 'div_pf_estado',
                               defaultSeriesType: 'column',
                               zoomType: 'xy',
                               //width: 1200,
                               //height:500
                           },
                           title: {
                               text: null
                           },
                           
                           xAxis: {
                                type: 'category',
                                labels: {
                                    rotation: -45,
                                    formatter: function () {
                                        return this.value.toString().substring(0, 25);
                                    }
                                },
                                min:0,
                                 max: 5
                              
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: 'Pontos de Função'
                                }
                            },
                            legend: {
                                        enabled: false
                                    },
                           tooltip: {
                                    
                                   
                              pointFormat: '{series.name}: <b>{point.y:.1f} pf</b>'
                                },
                           plotOptions: {
                               column: {
                                         pointWidth: 20,    
                                      pointPadding: 0.9,
                                      borderWidth: 0.5,
                                        shadow: true,
                                            dataLabels: {
                                                            enabled: true,
                                                            
                                                            color: '#000000',
                                                            format: '{point.y:.1f} pf',
                                                            
                                                            y: -1, // 10 pixels down from the top
                                                            style: {
                                                                fontSize: '11px',
                                                                fontFamily: 'Verdana, sans-serif'
                                                            }
                                                        }
                                        }

                           },
                           
                          
                            series: [{}],
                            scrollbar: {
                                    enabled:true,
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

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/pontosFuncao/estado", function(data) {
                                
                                var nome = $.map(data.dados, function (value, key) {
                                    return [key];
                                });
                                var valor = $.map(data.dados, function (value, key) {
                                    return [value];
                                });
                                
                                var mySeries = [];
                                for (var i = 0; i < valor.length; i++) {
                                    var nom = nome[i];//.substring(0,10);
                                    mySeries.push([nom, valor[i]]);

                                }
                                ;
                              
                                estado.series = [
                                  
                                    {                              
                                       name: 'Estado',
                                       color: '#16A085',
                                       data: mySeries
                                   }
                       
                                ];
                                var chart = new Highcharts.Chart(estado);
                            });
                            // div pf por estado
                            
                            
                            
                            
                            
                            
                            
                            
                            
                             // div pf tempo
                            var tempo = {
                                
                               chart: {
                               renderTo: 'div_pf_mes',
                               type: 'column',
                               defaultSeriesType: 'column',
                               zoomType: 'xy'

                           },
                      
                           title: {
                               text: null
                           },
                           
          
                           
                           xAxis: {
                                                             
                                type: 'category',
                                labels: {
                                    rotation: -45,
                                    style: {
                                        fontSize: '13px',
                                        fontFamily: 'Verdana, sans-serif'
                                    }
                                },
                                 min:0,
                                 max: 5
                              
                            },
                         scrollbar: {
                                    enabled: true
                                },
                            
                            yAxis: {
                                min: 0,
                                title: {
                                    text: 'Pontos de Função'
                                }
                            },
                            
                            
                            legend: {
                                        enabled: false
                                    },
                           tooltip: {
                                    
                                   
                              pointFormat: '{series.name}: <b>{point.y:.1f} pf</b>'
                                },
                           plotOptions: {
                               column: {
                                      pointWidth: 20,    
                                      pointPadding: 0.9,
                                      borderWidth: 0.5,
                                        shadow: true,
                                            dataLabels: {
                                                            enabled: true,
                                                            
                                                            color: '#000000',
                                                            format: '{point.y:.1f} pf',
                                                            
                                                            y: -1, // 10 pixels down from the top
                                                            style: {
                                                                fontSize: '11px',
                                                                fontFamily: 'Verdana, sans-serif'
                                                            }
                                                        }
                                        }
                           },
                           
                          
                               series: [{}],
                               
                               scrollbar: {
                        enabled:true,
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

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/pontosFuncao/tempo", function(data) {
                                           
                                                var mesAno = $.map(data.dados, function(value, key) {
						    return [key];
						});
						var valor = $.map(data.dados, function(value, key) {
						    return [value];
						});
                                                
                                                
                                                var mySeries = [];
                                                 for (var i = 0; i < valor.length; i++) {
                                                     var mesano = mesAno[i].substring(5,7)+"/"+mesAno[i].substring(0,4);
                                                    mySeries.push([mesano, valor[i]]);
                                                        
                                                };
                                               
                                               
                                tempo.series = [
                                  
                                    {                              
                                       name: 'PF por Mês',
                                       colorByPoint: false,
                                       color: '#34495E',
                                       data: mySeries
                                      
                                   }
                                   
                       
                                ];
                                
                             
                               
                                var chart = new Highcharts.Chart(tempo);
                            });
                            // div tempo
                            
                            
                            
                            
                             // div tempo_situacao
                            var tempoSituacao = {
                                
                               chart: {
                               type: 'column',
                               renderTo: 'div_media_tempo_estado',
                               defaultSeriesType: 'column',
                               zoomType: 'xy',
                               //width: 1200,
                               //height:500
                           },
                           title: {
                               text: null
                           },
                           
                           xAxis: {
                                type: 'category',
                                labels: {
                                    rotation: -45,
                                    formatter: function () {
                                        return this.value.toString().substring(0, 25);
                                    }
                                },
                                min:0,
                                 max: 15
                              
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: 'Dias Úteis'
                                }
                            },
                            legend: {
                                        enabled: false
                                    },
                           tooltip: {
                                    
                                   
                              pointFormat: '{series.name}: <b>{point.y:.1f} </b>'
                                },
                           plotOptions: {
                               column: {
                                         pointWidth: 20,    
                                      pointPadding: 0.0,
                                      borderWidth: 0.5,
                                        shadow: true,
                                            dataLabels: {
                                                            enabled: true,
                                                            
                                                            color: '#000000',
                                                            format: '{point.y:.2f} ',
                                                            
                                                            y: -1, // 10 pixels down from the top
                                                            style: {
                                                                fontSize: '11px',
                                                                fontFamily: 'Verdana, sans-serif'
                                                            }
                                                        }
                                        }

                           },
                           
                          
                            series: [{}],
                            scrollbar: {
                                    enabled:true,
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

                            $.getJSON($("#input-urlRaiz").val()+"grafico/ordemServico/situacao/tempoMedio", function(data) {
                                
                                var nome = $.map(data.dados, function (value, key) {
                                    return [key];
                                });
                                var valor = $.map(data.dados, function (value, key) {
                                    return [value];
                                });
                                
                                var mySeries = [];
                                for (var i = 0; i < valor.length; i++) {
                                    var nom = nome[i];//.substring(0,10);
                                    mySeries.push([nom, valor[i]]);

                                }
                                ;
                              
                                tempoSituacao.series = [
                                  
                                    {                              
                                       name: 'Estado',
                                       color: '#16A085',
                                       data: mySeries
                                   }
                       
                                ];
                                var chart = new Highcharts.Chart(tempoSituacao);
                            });
                            // div tempo_situacao
                            
                            
                            
                            
                            
                            
                            
                            
                            

                        });
             
             
             
       
             
             
           