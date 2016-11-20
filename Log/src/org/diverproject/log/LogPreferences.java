package org.diverproject.log;

import java.io.File;

/**
 * <h1>Prefer�ncias para Registros</h1>
 *
 * <p>Classe que possui diversos procedimentos que permitem definir determinadas op��es para registros.
 * Em sua maioria determinar quais os tipos de registros que de fato devem ser registrados (arquivo/console).
 * Al�m disso, � aqui que se define o caminho do diret�rio no qual ser� salvo os registros em arquivo.</p>
 *
 * @author Andrew
 */

public class LogPreferences
{
	/**
	 * Determina se deve ser feito o uso de registros do tipo debug.
	 */
	private static boolean useDebug;

	/**
	 * Determina se deve ser feito o uso de registros do tipo informa��o.
	 */
	private static boolean useInfo;

	/**
	 * Determina se deve ser feito o uso de registros do tipo not�cia.
	 */
	private static boolean useNotice;

	/**
	 * Determina se deve ser feito o uso de registros do tipo alerta.
	 */
	private static boolean useWarning;

	/**
	 * Determina se deve ser feito o uso de registros do tipo erro.
	 */
	private static boolean useError;

	/**
	 * Determina se deve ser feito o uso de registros do tipo exception.
	 */
	private static boolean useException;

	/**
	 * Determina qual ser� o caminho (diret�rio) que ser� usado para salvar o arquivo de registros.
	 */
	private static String filePath;

	/**
	 * A utiliza��o de registros de debug permite que logDebug() seja funcional.
	 * @return true se estiver habilitado ou false caso contr�rio.
	 */

	public static boolean isUseDebug()
	{
		return useDebug;
	}

	/**
	 * Permite definir se os registros do tipo debug devem ou n�o ser usados.
	 * @param useDebug true para habilitar ou false caso contr�rio.
	 */

	public static void setUseDebug(boolean useDebug)
	{
		LogPreferences.useDebug = useDebug;
	}

	/**
	 * A utiliza��o de registros de debug permite que logInfo() seja funcional.
	 * @return true se estiver habilitado ou false caso contr�rio.
	 */

	public static boolean isUseInfo()
	{
		return useInfo;
	}

	/**
	 * Permite definir se os registros do tipo info devem ou n�o ser usados.
	 * @param useInfo true para habilitar ou false caso contr�rio.
	 */

	public static void setUseInfo(boolean useInfo)
	{
		LogPreferences.useInfo = useInfo;
	}

	/**
	 * A utiliza��o de registros de debug permite que logNotice() seja funcional.
	 * @return true se estiver habilitado ou false caso contr�rio.
	 */

	public static boolean isUseNotice()
	{
		return useNotice;
	}

	/**
	 * Permite definir se os registros do tipo notice devem ou n�o ser usados.
	 * @param useNotice true para habilitar ou false caso contr�rio.
	 */

	public static void setUseNotice(boolean useNotice)
	{
		LogPreferences.useNotice = useNotice;
	}

	/**
	 * A utiliza��o de registros de debug permite que logWarning() seja funcional.
	 * @return true se estiver habilitado ou false caso contr�rio.
	 */

	public static boolean isUseWarning()
	{
		return useWarning;
	}

	/**
	 * Permite definir se os registros do tipo warning devem ou n�o ser usados.
	 * @param useWarning true para habilitar ou false caso contr�rio.
	 */

	public static void setUseWarning(boolean useWarning)
	{
		LogPreferences.useWarning = useWarning;
	}

	/**
	 * A utiliza��o de registros de debug permite que logError() seja funcional.
	 * @return true se estiver habilitado ou false caso contr�rio.
	 */

	public static boolean isUseError()
	{
		return useError;
	}

	/**
	 * Permite definir se os registros do tipo error devem ou n�o ser usados.
	 * @param useError true para habilitar ou false caso contr�rio.
	 */

	public static void setUseError(boolean useError)
	{
		LogPreferences.useError = useError;
	}

	/**
	 * A utiliza��o de registros de debug permite que logException() seja funcional.
	 * @return true se estiver habilitado ou false caso contr�rio.
	 */

	public static boolean isUseException()
	{
		return useException;
	}

	/**
	 * Permite definir se os registros do tipo exception devem ou n�o ser usados.
	 * @param useException true para habilitar ou false caso contr�rio.
	 */

	public static void setUseException(boolean useException)
	{
		LogPreferences.useException = useException;
	}

	/**
	 * A utiliza��o de um arquivo permite que os registros sejam salvo no mesmo.
	 * @return caminho do arquivo que ser� usado para salvar registros.
	 */

	public static File getFile()
	{
		return new File(filePath);
	}

	/**
	 * Permite definir o caminho para salvar os arquivos criados se assim for poss�vel.
	 * Caso o caminho especificado seja inv�lido ou o sistema j� tenha sido iniciado,
	 * esse m�todo n�o ter� nenhuma utilidade retornando antes de definir o diret�rio.
	 * @param path caminho do diret�rio do qual os arquivos de registros ser�o criados.
	 */

	public static void setFile(String path)
	{
		filePath = path;
	}

	/**
	 * Quando chamado ir� definir que todos os tipos de mensagens devem ser registrados.
	 * Al�m das mensagens ir� utilizar todo e qualquer recurso como uso do console ou arquivo.
	 */

	public static void setUseAll()
	{
		setUseDebug(true);
		setUseError(true);
		setUseException(true);
		setUseInfo(true);
		setUseNotice(true);
		setUseWarning(true);
	}
}
