/**
 * 
 */
package com.roxx.learning.servicemix.db.util;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.openjpa.jdbc.meta.MappingTool;

/**
 * Utilitaire pour la génération du schéma de départ
 * 
 * @author julien
 *
 */
public class OpenJPADDLGenerator {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, SQLException {
		// -schemaAction build -sql create.sql
		String[] arguments = { "-schemaAction", "build", "-sql", "target/create.sql" };
		MappingTool.main(arguments);
	}

}
