package br.jus.cjf.hibernate.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GeraSchema {

	public static void main(String[] args) {
		SchemaExport schemaExport = new SchemaExport(new Configuration().configure("hibernate-mineiro.cfg.xml"));
		schemaExport.setDelimiter(";");
		schemaExport.create(true, true);
	}
	
}
