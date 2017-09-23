package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.dao.OrdemServicoDao;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.model.CategoriaNivelServico;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Estado;
import br.jus.cjf.mineiro.model.Grafico;
import br.jus.cjf.mineiro.model.Linguagem;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.TempoRolesDemanda;
import br.jus.cjf.mineiro.model.Transicao;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import org.springframework.transaction.annotation.Propagation;

@Service
public class GraficoServiceImpl implements GraficoService{

	private final OrdemServicoService ordemServicoService;
	
	private final DemandaService demandaService;
        
        private final EstadoService estadoService;
	
	private List<OrdemServico> ordensServico;
        
        private  long timeLastUpdate=System.currentTimeMillis();
	
	private static Logger logger = LoggerFactory
	.getLogger(GraficoServiceImpl.class);
	
	@Autowired
	public GraficoServiceImpl(OrdemServicoService ordemServicoService,
			DemandaService demandaService,EstadoService estadoService) {
		super();
		this.ordemServicoService = ordemServicoService;
		this.demandaService = demandaService;
                this.estadoService = estadoService;
	}
	
	public synchronized List<OrdemServico>  getOrdensServico(){
		if(ordensServico==null || ((System.currentTimeMillis()-timeLastUpdate)>=1000*600)){
                        timeLastUpdate = System.currentTimeMillis();
			ordensServico = ordemServicoService.listarOrdemServicoGrafico();
	         }
		return ordensServico;
		
	}
        
        @Override
        public synchronized void setTimeLastUpdate(){
            timeLastUpdate=0;
        }
	@Override
	@Transactional("mineiroTransactionManager")
	public Grafico getGraficoConformidadeOrdemServico() {
		

		Grafico grafico = new Grafico();
		HashMap<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
		grafico.setNome("Conformidade das Ordens de Serviço");
		List<OrdemServico> ordensServico =  getOrdensServico();
		
		BigDecimal quantidadeConformidadeDesejavel = BigDecimal.ZERO;
		BigDecimal quantidadeConformidadeAceitavel = BigDecimal.ZERO;
		BigDecimal quantidadeConformidadeInaceitavel = BigDecimal.ZERO;


		for (OrdemServico ordemServico : ordensServico) {
			if(ordemServico.getNivelServicoConformidade().equals(CategoriaNivelServico.DESEJAVEL)){
				quantidadeConformidadeDesejavel = quantidadeConformidadeDesejavel.add(BigDecimal.ONE);
				
			}
			else if(ordemServico.getNivelServicoConformidade().equals(CategoriaNivelServico.ACEITAVEL)){
				quantidadeConformidadeAceitavel = quantidadeConformidadeAceitavel.add(BigDecimal.ONE);
			}
			else if(ordemServico.getNivelServicoConformidade().equals(CategoriaNivelServico.INACEITAVEL)){
				quantidadeConformidadeInaceitavel = quantidadeConformidadeInaceitavel.add(BigDecimal.ONE);
			}
			
			
		}
		
		dados.put("desejavel", quantidadeConformidadeDesejavel);
		dados.put("aceitavel", quantidadeConformidadeAceitavel);
		dados.put("inaceitavel", quantidadeConformidadeInaceitavel);
		grafico.setDados(dados);
		
		
		return grafico;
	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public Grafico getGraficoAtrasoOrdemServico() {
		

		Grafico grafico = new Grafico();
		HashMap<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
		grafico.setNome("Atraso das Ordens de Serviço");
		List<OrdemServico> ordensServico =  getOrdensServico();
		
		BigDecimal quantidadeAtrasoDesejavel = BigDecimal.ZERO;
		BigDecimal quantidadeAtrasoAceitavel = BigDecimal.ZERO;
		BigDecimal quantidadeAtrasoInaceitavel = BigDecimal.ZERO;


		for (OrdemServico ordemServico : ordensServico) {
			if(ordemServico.getNivelServicoAtraso().equals(CategoriaNivelServico.DESEJAVEL)){
				quantidadeAtrasoDesejavel= quantidadeAtrasoDesejavel.add(BigDecimal.ONE);
			}
			else if(ordemServico.getNivelServicoAtraso().equals(CategoriaNivelServico.ACEITAVEL)){
				quantidadeAtrasoAceitavel = quantidadeAtrasoAceitavel.add(BigDecimal.ONE);
			}
			else if(ordemServico.getNivelServicoAtraso().equals(CategoriaNivelServico.INACEITAVEL)){
				quantidadeAtrasoInaceitavel = quantidadeAtrasoInaceitavel.add(BigDecimal.ONE);
			}
			
			
		}
		
		dados.put("desejavel", quantidadeAtrasoDesejavel);
		dados.put("aceitavel", quantidadeAtrasoAceitavel);
		dados.put("inaceitavel", quantidadeAtrasoInaceitavel);
		grafico.setDados(dados);
		
		
		return grafico;
	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public Grafico getGraficoRecusaOrdemServico() {
		

		Grafico grafico = new Grafico();
		HashMap<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
		grafico.setNome("Recusa das Ordens de Serviço");
		List<OrdemServico> ordensServico =  getOrdensServico();
		
		BigDecimal quantidadeRecusaDesejavel = BigDecimal.ZERO;
		BigDecimal quantidadeRecusaAceitavel = BigDecimal.ZERO;
		BigDecimal quantidadeRecusaInaceitavel = BigDecimal.ZERO;


		for (OrdemServico ordemServico : ordensServico) {
			if(ordemServico.getNivelServicoRecusa().equals(CategoriaNivelServico.DESEJAVEL)){
				quantidadeRecusaDesejavel = quantidadeRecusaDesejavel.add(BigDecimal.ONE);
			}
			else if(ordemServico.getNivelServicoRecusa().equals(CategoriaNivelServico.ACEITAVEL)){
				quantidadeRecusaAceitavel = quantidadeRecusaAceitavel.add(BigDecimal.ONE);
			}
			else if(ordemServico.getNivelServicoRecusa().equals(CategoriaNivelServico.INACEITAVEL)){
				quantidadeRecusaInaceitavel = quantidadeRecusaInaceitavel.add(BigDecimal.ONE);
			}
			
			
		}
		
		dados.put("desejavel", quantidadeRecusaDesejavel);
		dados.put("aceitavel", quantidadeRecusaAceitavel);
		dados.put("inaceitavel", quantidadeRecusaInaceitavel);
		grafico.setDados(dados);
		
		
		return grafico;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Grafico getGraficoQuantitativoPontosFuncaoPorLinguagem() {
		
		Grafico grafico = new Grafico();
		HashMap<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
		grafico.setNome("Quantidade de Pontos de Função Consumidos por Linguagem");
		List<OrdemServico> ordensServico =  getOrdensServico();
		BigDecimal quantidadePontosFuncaoJava = BigDecimal.ZERO;
		BigDecimal quantidadePontosFuncaoMumps = BigDecimal.ZERO;
		BigDecimal quantidadePontosFuncaoDelphi = BigDecimal.ZERO;
		BigDecimal quantidadePontosFuncaoPhp = BigDecimal.ZERO;
		
		for (OrdemServico ordemServico : ordensServico) {
			BigDecimal quantidadePF = ordemServico.getDemanda().getContagemDetalhada();
			if(quantidadePF.compareTo(BigDecimal.ZERO) == 0)
			{
				quantidadePF = ordemServico.getDemanda().getContagemEstimada();
			}
			quantidadePF = quantidadePF.setScale(2, BigDecimal.ROUND_DOWN);
			
			logger.debug("#"+ordemServico.getDemanda().getRedmineIssueId()+" - Quantidade PF:"+quantidadePF);
			if(ordemServico.getProjeto().getLinguagem()==null){
				
			}
			else if(ordemServico.getProjeto().getLinguagem().equals(Linguagem.JAVA)){
			
				quantidadePontosFuncaoJava = quantidadePontosFuncaoJava.add(quantidadePF);
				
			}
			else if(ordemServico.getProjeto().getLinguagem().equals(Linguagem.MUMPS)){

				quantidadePontosFuncaoMumps = quantidadePontosFuncaoMumps.add(quantidadePF);

			}
			else if(ordemServico.getProjeto().getLinguagem().equals(Linguagem.DELPHI)){

				quantidadePontosFuncaoDelphi = quantidadePontosFuncaoDelphi.add(quantidadePF);
				
			}
			else if(ordemServico.getProjeto().getLinguagem().equals(Linguagem.PHP)){
				
				quantidadePontosFuncaoPhp = quantidadePontosFuncaoPhp.add(quantidadePF);
				
			}

			
			
		}
		
		dados.put("java", quantidadePontosFuncaoJava);
		dados.put("mumps", quantidadePontosFuncaoMumps);
		dados.put("delphi", quantidadePontosFuncaoDelphi);
		dados.put("php", quantidadePontosFuncaoPhp);
		grafico.setDados(dados);
		
		return grafico;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Grafico getGraficoQuantitativoPontosFuncaoPorConsumo() {
		
		Grafico grafico = new Grafico();
		HashMap<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
		grafico.setNome("Quantidade de Pontos de Função");
		List<OrdemServico> ordensServico =  getOrdensServico();
		BigDecimal quantidadePontosFuncaoConsumido = BigDecimal.ZERO;
		BigDecimal quantidadePontosFuncaoPrevisto = BigDecimal.ZERO;
		BigDecimal quantidadePontosFuncaoNaoConsumido = BigDecimal.ZERO;
		
		for (OrdemServico ordemServico : ordensServico) {
			BigDecimal quantidadePF = ordemServico.getDemanda().getContagemDetalhada();
			
			

			

			if(ordemServico.getDemanda().getConcluida()==true){
				logger.debug("#"+ordemServico.getDemanda().getRedmineIssueId()+" - Consumido - Quantidade PF:"+quantidadePF);

                                if(quantidadePF.compareTo(BigDecimal.ZERO) == 0)
                                {
                                    quantidadePF = ordemServico.getDemanda().getContagemEstimada();
                                }
				quantidadePontosFuncaoConsumido = quantidadePontosFuncaoConsumido.add(quantidadePF);
				
			}
			else{
                                if(quantidadePF.compareTo(BigDecimal.ZERO) == 0)
                                {
                                    quantidadePF = ordemServico.getDemanda().getContagemEstimada();
                                }
                            
                            
				//quantidadePF = ordemServico.getDemanda().getContagemEstimada();
				quantidadePontosFuncaoPrevisto = quantidadePontosFuncaoPrevisto.add(quantidadePF);

			}
	
			
			
		}
                

		quantidadePontosFuncaoNaoConsumido = ordemServicoService.QuantitativoPontosFuncao().subtract(quantidadePontosFuncaoConsumido).subtract(quantidadePontosFuncaoPrevisto); 
		
		dados.put("consumido", quantidadePontosFuncaoConsumido);
		dados.put("previsto", quantidadePontosFuncaoPrevisto);
		dados.put("naoConsumido", quantidadePontosFuncaoNaoConsumido);
		grafico.setDados(dados);
		
		return grafico;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Grafico getGraficoQuantitativoPontosFuncaoPorMes() {
		Grafico grafico = new Grafico();
		Map<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
                
		grafico.setNome("Quantidade de Pontos de Função Consumidos por Mês");
		List<Object[]>  quantitativosPontosFuncaoPorMes = demandaService.listarQuantidadePontosFuncaoPorMes();
	
		for(Object[] quantitativoPontosFuncaoPorMes:quantitativosPontosFuncaoPorMes){
			BigDecimal contagemDetalhada = new BigDecimal(quantitativoPontosFuncaoPorMes[2].toString());
                        
                        String mes = "00"+quantitativoPontosFuncaoPorMes[0].toString();
			String mesAno = quantitativoPontosFuncaoPorMes[1].toString()+"/"+mes.substring(mes.length()-2);
			
			dados.put(mesAno , contagemDetalhada);
			
		}

                Map<String, BigDecimal> treeMap = new TreeMap<String, BigDecimal>(dados);

		grafico.setDados(treeMap);
		
		return grafico;
	}
        
        
        @Override
	public Grafico getGraficoTempoPorSituacao(int redmineIssueId) {
            
            
		Grafico grafico = new Grafico();
		Map<String, BigDecimal> dados = new LinkedHashMap<String, BigDecimal>();
                Map<String, BigDecimal> dados2 = new LinkedHashMap<String, BigDecimal>();
                OrdemServico ordemServico = ordemServicoService.getOrdemServicoPrecificadaByRedmineIssueId(redmineIssueId);
                
		grafico.setNome("Tempo por Situação");
		//List<Object[]>  quantitativosPontosFuncaoPorMes = demandaService.listarQuantidadePontosFuncaoPorMes();
                
                BigDecimal pf = ordemServico.getDemanda().getContagemDetalhada();
                if(!(pf.compareTo(BigDecimal.ZERO)>0))
                    pf =ordemServico.getDemanda().getContagemEstimada();
	
		for(Transicao transicao :ordemServico.getDemanda().getTransicoes()){
			BigDecimal tempo = new BigDecimal(transicao.getDuracaoContandoTempo().getValor().getStandardMinutes());
                        
                        String nome = transicao.getEstado().getNome();
			float media = transicao.getEstado().getMediaTempoPorPF();
                        if(dados.containsKey(nome)){
                           dados.put(nome,  dados.get(nome).add(tempo));
                        }else{
                            dados.put(nome , tempo);
                            dados2.put(nome, pf.multiply(new BigDecimal(media)).divide(new BigDecimal(600.00),2,RoundingMode.CEILING));
                        }
                        
                        
			
			
		}
                Map<String, BigDecimal> treeMap = new LinkedHashMap<String, BigDecimal>();
                for(Map.Entry<String, BigDecimal> t : dados.entrySet()){
                    treeMap.put(t.getKey(), t.getValue().divide(new BigDecimal(600.00),2,RoundingMode.CEILING));
                }

               // Map<String, BigDecimal> treeMap = new TreeMap<String, BigDecimal>(dados);

		grafico.setDados(treeMap);
                grafico.setDados2(dados2);
		
		return grafico;
	}
	
        
        
        @Override        
	public Grafico getGraficoTempoPorArea(int redmineIssueId) {
            
            
		Grafico grafico = new Grafico();
		Map<String, BigDecimal> dados = new LinkedHashMap<String, BigDecimal>();
                OrdemServico ordemServico = ordemServicoService.getOrdemServicoPrecificadaByRedmineIssueId(redmineIssueId);
                
		grafico.setNome("Tempo por Área");
            
                float total=0;
		for(TempoRolesDemanda trd :ordemServico.getDemanda().getTempoRoles()){
			total+=trd.getTempo();
                }
                for(TempoRolesDemanda trd :ordemServico.getDemanda().getTempoRoles()){        
                        String nome = trd.getRole().getNome();
			float valor = trd.getTempo();
                        dados.put(nome, new BigDecimal(valor*100.0).divide(new BigDecimal(total),2,RoundingMode.CEILING));	
                }

		grafico.setDados(dados);
		
		return grafico;
	}
  
        
        @Override        
	public Grafico getGraficoPFPorEstado() {
            
            
		Grafico grafico = new Grafico();
		HashMap<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
		grafico.setNome("PF Por Estado");
		List<OrdemServico> ordensServico =  getOrdensServico();
                
		for(OrdemServico s: ordensServico){
                    if(s.getDemanda().getCancelada() )
                        continue;

                    if(s.getDemanda().getConcluida())
                        continue;
                    
                    if(s.getDemanda().getContagemEstimada()==null || s.getDemanda().getContagemEstimada().compareTo(BigDecimal.ZERO)==0)
                        continue;
                    
                    int size = s.getDemanda().getTransicoes().size();
                    String estado = s.getDemanda().getTransicoes().get(size-1).getEstado().getNome();
                    
                    if(dados.containsKey(estado)){
                        BigDecimal b = dados.get(estado);
                        b=b.add(s.getDemanda().getContagemEstimada());
                        dados.put(estado, b);
                    }else{
                        dados.put(estado, s.getDemanda().getContagemEstimada());
                    }
                }
                
                //Comparator<String> comparator = new ValueComparator(dados);
                Map<String, BigDecimal> dados2 = sortByValues(dados);
                dados2.putAll(dados);
           
                  //  for(Map.Entry<String, BigDecimal> t : dados.entrySet()){
                //        dados2.put(t.getKey(), t.getValue());
                 //   }
                
                
                
		grafico.setDados(dados2);
                
		return grafico;
	}
        
        
        
        @Override 
	public Grafico getGraficoMediaTempoPorSituacao() {
            
            
		Grafico grafico = new Grafico();
		HashMap<String, BigDecimal> dados = new HashMap<String, BigDecimal>();
                
                List<Estado> lista = estadoService.getEstadosOrderByStatusName();
                
		grafico.setNome("Tempo por Situação");
                
		for(Estado estado :lista){
                    
                        if(estado.getRole()==null)
                            continue;
                        
                        float media = estado.getMediaTempoPorPF();
                        String nome = estado.getNome();
			
                        dados.put(nome,  new BigDecimal(media).divide(new BigDecimal(600.00),2,RoundingMode.CEILING));
		}
                

                Map<String, BigDecimal> map = sortByValues(dados); 

		grafico.setDados(map);
		
		return grafico;
	}
        
        
        @Override 
	public BigDecimal getIndiceDiasPF() {
            
            
		 BigDecimal dados = new BigDecimal(0);
                
                List<Estado> lista = estadoService.getEstadosOrderByStatusName();
                
                
		for(Estado estado :lista){
                    
                        if(estado.getRole()==null)
                            continue;
                        
                        float media = estado.getMediaTempoPorPFInd();
			
                        dados = dados.add(new BigDecimal(media).divide(new BigDecimal(600.00),2,RoundingMode.CEILING));
		}
               
		
		return dados;
	}
        
        
        
        private  HashMap sortByValues(HashMap map) { 
            List list = new LinkedList(map.entrySet());
            // Defined Custom Comparator here
            Collections.sort(list, new Comparator() {
                 public int compare(Object o1, Object o2) {
                    return ((Comparable) ((Map.Entry) (o1)).getValue())
                       .compareTo(((Map.Entry) (o2)).getValue())*-1;
                 }
            });

            // Here I am copying the sorted list in HashMap
            // using LinkedHashMap to preserve the insertion order
            HashMap sortedHashMap = new LinkedHashMap();
            for (Iterator it = list.iterator(); it.hasNext();) {
                   Map.Entry entry = (Map.Entry) it.next();
                   sortedHashMap.put(entry.getKey(), entry.getValue());
            } 
            return sortedHashMap;
       }
        
        
        
        // a comparator that compares Strings
        class ValueComparator implements Comparator<String>{

                HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();

                public ValueComparator(HashMap<String, BigDecimal> map){
                        this.map.putAll(map);
                }

                @Override
                public int compare(String s1, String s2) {
                        return (map.get(s1).compareTo( map.get(s2))*-1);
                         	
                }
        }
        
}
