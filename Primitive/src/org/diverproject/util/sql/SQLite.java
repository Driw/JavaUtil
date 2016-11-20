package org.diverproject.util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>Utilizado para permitir conex�es com banco de dados do tipo SQLite.
 * Este por sua vez requer a utiliza��o de uma biblioteca que permita
 * a conex�o do mesmo. Normalmente se utiliza o SQLite Connector.</p>
 *
 * <p>A finalidade e tornar a conex�o mais simples e transparente aos
 * desenvolvedores que desejam efetuar uma conex�o SQLite. De modo que
 * seja necess�rio apenas passar a refer�ncia do arquivo que ser� lido.</p>
 */

public class SQLite
{
	/**
	 * Refer�ncia da conex�o quando for criada.
	 */
	private Connection connection;

	/**
	 * Constr�i uma nova conex�o com um determinado arquivo do tipo banco de dados.
	 * @param path caminho do qual se encontra o arquivo que ser� usado na conex�o.
	 * @throws ClassNotFoundException ocorre apenas se n�o houver o SQLite Connector.
	 * @throws SQLException problema que pode ocorrer durante a conex�o SQL.
	 */

	public SQLite(String path) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + path);
	}

	/**
	 * A utiliza��o dessa classe e permitir a facilidade de conex�o com o SQLite.
	 * @return conex�o com o banco de dados efetuada.
	 */

	public Connection getConnection()
	{
		return connection;
	}

	/**
	 * Permite fechar a conex�o com o banco de dados de modo seguro.
	 * @throws SQLException exce��o que pode ocorrer.
	 */

	public void closeConnection() throws SQLException
	{
		connection.close();
	}
}
