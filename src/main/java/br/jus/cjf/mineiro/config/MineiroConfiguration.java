package br.jus.cjf.mineiro.config;

import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import br.jus.cjf.spring.util.SpringUtils;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
//@PropertySource("classpath:mineiro.properties")
 @PropertySources({
 
    @PropertySource("classpath:mineiro.properties"),
    @PropertySource(value = "file:${mineiro.properties.dir:defaultValue}/mineiro.properties", ignoreResourceNotFound=true)
    
 })
public class MineiroConfiguration {

	
	@Autowired
	private Environment environment;	
	
	


	
	@Bean
	public ConfiguracaoMineiro getConfiguracaoMineiro(){
		
		ConfiguracaoMineiro configuracaoMineiro = new ConfiguracaoMineiro();
		configuracaoMineiro.setAmbienteMineiro(environment.getRequiredProperty("mineiro.ambiente"));
		configuracaoMineiro.setRemetenteEmail(environment.getRequiredProperty("email.remetente"));
		configuracaoMineiro.setDestinatarioEmail(environment.getRequiredProperty("email.destinatario"));
		configuracaoMineiro.setDestinatarioEmailTeste(environment.getRequiredProperty("email.destinatario.teste"));
		configuracaoMineiro.setTituloEmail(environment.getRequiredProperty("email.titulo"));
		configuracaoMineiro.setTituloEmailTeste(environment.getRequiredProperty("email.titulo.teste"));
		configuracaoMineiro.setUrlRedmine(environment.getRequiredProperty("redmine.url"));
                configuracaoMineiro.setVersao(environment.getRequiredProperty("mineiro.versao"));
       
		SessionFactoryImpl mineiroSessionFactory = (SessionFactoryImpl) SpringUtils.getApplicationContext().getBean("mineiroSessionFactory");
		SessionFactoryImpl redmineSessionFactory = (SessionFactoryImpl) SpringUtils.getApplicationContext().getBean("redmineSessionFactory");
		//SessionFactoryImpl simusSessionFactory = (SessionFactoryImpl) SpringUtils.getApplicationContext().getBean("simusSessionFactory");

		configuracaoMineiro.setUrlBancoMineiro(mineiroSessionFactory.getProperties().getProperty("connection.url"));
		configuracaoMineiro.setUrlBancoRedmine(redmineSessionFactory.getProperties().getProperty("connection.url"));
		//configuracaoMineiro.setUrlBancoSimus(simusSessionFactory.getProperties().getProperty("connection.url"));

		
		return configuracaoMineiro;
	}
	
	


	
	
}
