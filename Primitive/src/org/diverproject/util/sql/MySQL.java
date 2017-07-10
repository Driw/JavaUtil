package org.diverproject.util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.diverproject.util.ObjectDescription;

/**
 * <p>Utilizado para permitir conex�es com banco de dados do tipo MySQL.
 * Este por sua vez requer a utiliza��o de uma biblioteca que permita
 * a conex�o do mesmo. Normalmente se utiliza o MYSQL Connector.</p>
 *
 * <p>A finalidade e tornar a conex�o mais simples e transparente aos
 * desenvolvedores que desejam efetuar uma conex�o MYSQL. De modo
 * que ap�s a inst�ncia voc� possa definir os dados de conex�o que
 * s�o: host, database, usu�rio e senha. Ap�s isso � poss�vel iniciar
 * a conex�o por connect(). Se tudo estiver certo ir� criar a conex�o.</p>
 */

public class MySQL
{
	/**
	 * Endere�o do host do qual est� o MYSQL.
	 */
	private String host;

	/**
	 * Nome do banco de dados que ser� utilizado.
	 */
	private String database;

	/**
	 * Usu�rio que ir� permitir o acesso a esse banco.
	 */
	private String username;

	/**
	 * Senha respectiva do usu�rio que ser� utilizado.
	 */
	private String password;

	/**
	 * Refer�ncia da conex�o quando for criada.
	 */
	private Connection connection;

	/**
	 * Porta para conex�o com o banco de dados MySQL.
	 */
	private int port;

	/**
	 * TODO
	 */
	private boolean useLegacyDatetimeCode;

	/**
	 * Fuso hor�rio que a conex�o deve considerar.
	 */
	private String serverTimezone;

	/**
	 * Converter hor�rios que estejam com zeros em nulo.
	 */
	private boolean zeroDateTimeToNull;

	/**
	 * Faz a conex�o com o banco de dados MYSQL de acordo com os dados definidos.
	 * @throws ClassNotFoundException ocorre caso n�o haja a biblioteca do MYSQL.
	 * @throws SQLException algum problema ao tentar se conectar com o banco.
	 */

	public void connect() throws ClassNotFoundException, SQLException
	{
		if (host == null || host.length() == 0)
			throw new SQLException("host n�o configurado");

		if (database == null || database.length() == 0)
			throw new SQLException("banco de dados n�o configurado");

		if (username == null || username.length() == 0)
			throw new SQLException("usu�rio n�o configurado");

		if (password == null)
			password = "";

		if (port == 0)
			port = 3306;

		Class.forName("com.mysql.jdbc.Driver");

		String arguments = "&serverTimezone=America/Sao_Paulo";

		if (serverTimezone != null)
			arguments = "&serverTimezone=" +serverTimezone;

		if (useLegacyDatetimeCode)
			arguments += "&useLegacyDatetimeCode=true";
		else
			arguments += "&useLegacyDatetimeCode=false";

		if (zeroDateTimeToNull)
			arguments += "&zeroDateTimeBehavior=convertToNull";

		String url = String.format("jdbc:mysql://%s:%d/%s?%s", host, port, database, arguments);
		connection = DriverManager.getConnection(url, username, password);
	}

	/**
	 * A utiliza��o dessa classe e permitir a facilidade de conex�o com o MYSQL.
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

	/**
	 * O host � o endere�o do qual se encontra o banco de dados, normalmente um IP.
	 * @param host nome do host que ser� utilizado para fazer a conex�o.
	 */

	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * O banco de dados define qual conjunto de tabelas ser� usado quando queries forem feitas.
	 * @return nome do banco de dados que est� sendo utilizado nessa conex�o.
	 */

	public String getDatabase()
	{
		return database;
	}

	/**
	 * O banco de dados define qual conjunto de tabelas ser� usado quando queries forem feitas.
	 * @param database nome do banco de dados que ser� utilizado nessa conex�o.
	 */

	public void setDatabase(String database)
	{
		this.database = database;
	}

	/**
	 * O nome de usu�rio � necess�rio para fazer uma conex�o com o banco de dados MYSQL.
	 * @param username nome do usu�rio que far� o acesso ao banco de dados definido.
	 */

	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * Definir apenas caso haja uma, caso contr�rio n�o � necess�rio.
	 * @param password senha respectiva do usu�rio da conex�o.
	 */

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Definir apenas caso a porta n�o seja a padr�o (3306), caso contr�rio n�o � necess�rio.
	 * @param port nova porta em que o MySQL est� realizando as conex�es.
	 */

	public void setPort(int port)
	{
		if (port > 0 && port < 65536)
			this.port = port;
	}

	/**
	 * TODO
	 * @param useLegacyDatetimeCode
	 */

	public void setUseLegacyDatetimeCode(boolean useLegacyDatetimeCode)
	{
		this.useLegacyDatetimeCode = useLegacyDatetimeCode;
	}

	/**
	 * Permite definir o fuso hor�rio do qual a conex�o com o banco dever� considerar.
	 * @param serverTimezone nome que se refere ao fuso hor�rio desejado, padr�o: "America/Sao_paulo".
	 * @see https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
	 */

	public void setServerTimezone(String serverTimezone)
	{
		this.serverTimezone = serverTimezone;
	}

	/**
	 * Atrav�s desta op��o os resultados das consultas efetuadas no banco de dados que possuir
	 * um DateTime com valores todos igual a zero ser� considerado na convers�o como nulo.
	 * @param enable true para habilitar ou false para desabilitar essa opa��o.
	 */

	public void setZeroDateTimeToNull(boolean enable)
	{
		this.zeroDateTimeToNull = enable;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("host", host);
		description.append("port", port);
		description.append("database", database);
		description.append("username", username);
		description.append("password", password);

		if (useLegacyDatetimeCode)
			description.append("useLegacyDatetimeCode");
		
		if (zeroDateTimeToNull)
			description.append("zeroDateTimeToNUll");

		if (serverTimezone != null)
			description.append("serverTimeZone", serverTimezone);

		return description.toString();
	}
}
