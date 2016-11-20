package org.diverproject.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.diverproject.util.collection.List;
import org.diverproject.util.collection.abstraction.DynamicList;

/**
 * <h1>Sistema dos Registros</h1>
 *
 * <p>Classe principal para o controle de registros de mensagens da aplica��o que est� sendo executada.
 * Esse log permite que um arquivo seja criado em um local especificado e salve determinadas mensagens.
 * Mensagens que podem ser enviadas atrav�s dos m�todos dispon�veis em LogDisplay conforme necess�rio.</p>
 *
 * <p>Uma das possibilidades que essa biblioteca permite � que os registros sejam feitos no meio do c�digo.
 * Al�m disso h� algumas op��es que podem ser definidas atrav�s da classe LogPreferences com setters.</p>
 *
 * <p>Outro objeto dessa classe � conter m�todos que permitam que o sistema possa ser usado adequadamente.
 * Possuindo diversos tipos de impress�o de mensagens como registro no sistema de acordo com as necessidades.
 * H� a possibilidade de especificar se � registro de debug, info, notice, warning, error ou exception.</p>
 *
 * @see LogPreferences
 * @see LogConsole
 * @see LogFile
 * @see Log
 *
 * @author Andrew
 */

public class LogSystem
{
	/**
	 * Tipo de mensagens geradas por log.
	 */
	private static final String LOG_TYPE = "Log";

	/**
	 * Tipo de mensagens geradas por logDebug.
	 */
	private static final String DEBUG_TYPE = "Debug";

	/**
	 * Tipo de mensagens geradas por logInfo.
	 */
	private static final String INFO_TYPE = "Info";

	/**
	 * Tipo de mensagens geradas por logNotice.
	 */
	private static final String NOTICE_TYPE = "Notice";

	/**
	 * Tipo de mensagens geradas por logWarning.
	 */
	private static final String WARNING_TYPE = "Warning";

	/**
	 * Tipo de mensagens geradas por logError.
	 */
	private static final String ERROR_TYPE = "Error";

	/**
	 * Tipo de mensagens geradas por logException.
	 */
	private static final String EXCEPTION_TYPE = "Exception";

	/**
	 * Refer�ncia do writer que ser� usado para salvar as mensagens de registro.
	 */
	private static BufferedWriter bufferedWriter;

	/**
	 * Lista com os objetos que ir�o receber as mensagens do log.
	 */
	private static final List<LogListener> listeners;

	/**
	 * Altera a fonte de quem ser� mostrado no registro;
	 */
	private static int upSource;

	/**
	 * Objeto para sincroniza��o afim de evitar erros com upSource;
	 */
	private static Object lock;

	static
	{
		listeners = new DynamicList<>();
		listeners.add(new LogListener()
		{
			@Override
			public void onMessage(Log log)
			{
				LogFile.print(log);
			}
		});

		lock = new Object();
	}

	/**
	 * Procedimento que permite obter a refer�ncia do writer que ir� guardar os registros.
	 * Caso n�o tenha sido criado um writer, ser� exibido uma mensagem em uma janela.
	 * @return refer�ncia do writer que ir� guardar os registros.
	 */

	static BufferedWriter getBufferedWrite()
	{
		if (bufferedWriter == null)
			JOptionPane.showMessageDialog(null, "Log n�o foi inicializado.", "Falha de Log", JOptionPane.ERROR_MESSAGE);

		return bufferedWriter;
	}

	/**
	 * Verifica se o writer j� foi inicializado no sistema de registros.
	 * @return true se tiver sido iniciado ou false caso contr�rio.
	 */

	public static boolean hasInitialize()
	{
		return bufferedWriter != null;
	}

	/**
	 * Deve fazer a inicializa��o do writer para gravar todos os registros em um determinado arquivo.
	 * @return true se conseguir inicializar ou false caso j� tenha sido inicializado.
	 * @throws LogException ocorre apenas se houver falha durante o uso do arquivo para log.
	 */

	public static boolean initialize() throws LogException
	{
		if (hasInitialize())
			return false;

		try {

			File file = LogPreferences.getFile();

			if (file == null)
				throw new LogException("arquivo para log n�o definido");

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);

			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			LogSystem.log("Log: Inicializa��o efetuado com �xito (hor�rio: %s, arquivo: %s).\n", format.format(cal.getTime()), LogPreferences.getFile());

			return true;

		} catch (IOException e) {
			throw new LogException("Falha ao iniciar arquivo para log (%s).", e.getMessage());
		}
	}

	/**
	 * Deve fazer o t�rmino do writer que grava todos os registros em um determinado arquivo.
	 * @return true se conseguir finalizar com �xito ou false caso n�o tenha sido iniciado.
	 * @throws LogException ocorre apenas se houver falha durante o uso do arquivo para log.
	 */

	public static boolean terminate() throws LogException
	{
		if (!hasInitialize())
			return false;

		try {

			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			LogSystem.log("Log: Finaliza��o dos registros (hor�rio: %s, arquivo: %s).\n", format.format(cal.getTime()), LogPreferences.getFile());

			bufferedWriter.close();
			bufferedWriter.flush();

			return true;

		} catch (IOException e) {
			throw new LogException("Falha ao encerrar arquivo para log (%s).", e.getMessage());
		}
	}

	/**
	 * Sempre que uma mensagem for registrada no sistema ser� repassado aos listeners.
	 * Esse m�todo ir� adicionar um novo objeto a lista para receber esses registros.
	 * @param listener refer�ncia do objeto listener para receber as mensagens.
	 */

	public static void addListener(LogListener listener)
	{
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	/**
	 * Sempre que uma mensagem for registrada no sistema ser� repassado aos listeners.
	 * Esse m�todo ir� remover um novo objeto a lista para receber esses registros.
	 * @param listener refer�ncia do objeto listener para receber as mensagens.
	 */

	public static void removeListener(LogListener listener)
	{
		listeners.remove(listener);
	}

	/**
	 * Permite alterar quem ser� mostrado no registro como fonte da chamada.
	 * POde ser usado por m�todos gerais que tendem apenas a facilitar codifica��es.
	 * @param upSource quantos traces dever�o ser voltados a partir da chamada.
	 */

	public static void setUpSource(int upSource)
	{
		if (upSource > 0)
			LogSystem.upSource = upSource;
	}

	/**
	 * Procedimento interno que ir� criar o objeto para definir informa��es do registro.
	 * Este inclui a inicializa��o de Throwable para descobrir a origem da chamada log.
	 * Assim � poss�vel saber o nome do arquivo, classe, m�todo e linha que foi chamado.
	 * @param type nome do tipo de mensagem que est� sendo registrado no servi�o.
	 * @param message mensagem contendo as informa��es ou ocorridos a serem registrados.
	 */

	private static void internalLog(String type, String message)
	{
		Throwable throwable = new Throwable();

		internalLog(type, message, throwable);
	}

	/**
	 * Procedimento interno que ir� criar o objeto para definir informa��es do registro.
	 * Este inclui a inicializa��o de Throwable para descobrir a origem da chamada log.
	 * Assim � poss�vel saber o nome do arquivo, classe, m�todo e linha que foi chamado.
	 * @param type nome do tipo de mensagem que est� sendo registrado no servi�o.
	 * @param message mensagem contendo as informa��es ou ocorridos a serem registrados.
	 * @param throwable fonte da onde a mensagem foi originada.
	 */

	private static void internalLog(String type, String message, Throwable throwable)
	{
		if (!hasInitialize())
			return;

		synchronized (lock)
		{
			Log log = new Log();
			log.setType(type);
			log.setMessage(message);
			log.setStackTrace(throwable.getStackTrace()[2 + upSource]);

			upSource = 0;

			for (LogListener listener : listeners)
				listener.onMessage(log);	
		}
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * @param str conte�do do qual ser� usado como registro.
	 */

	public static void log(String str)
	{
		internalLog(LOG_TYPE, str);
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * @param format formado da mensagem que ser� exibida pelo registro.
	 * @param args refer�ncia dos objetos de acordo com a formata��o.
	 */

	public static void log(String format, Object... args)
	{
		internalLog(LOG_TYPE, String.format(format, args));
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de debug.
	 * @param str conte�do do qual ser� usado como registro.
	 */

	public static void logDebug(String str)
	{
		internalLog(DEBUG_TYPE, str);
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de debug.
	 * @param format formado da mensagem que ser� exibida pelo registro.
	 * @param args refer�ncia dos objetos de acordo com a formata��o.
	 */

	public static void logDebug(String format, Object... args)
	{
		if (!LogPreferences.isUseDebug())
			return;

		internalLog(DEBUG_TYPE, String.format(format, args));
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de info.
	 * @param str conte�do do qual ser� usado como registro.
	 */

	public static void logInfo(String str)
	{
		internalLog(INFO_TYPE, str);
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de info.
	 * @param format formado da mensagem que ser� exibida pelo registro.
	 * @param args refer�ncia dos objetos de acordo com a formata��o.
	 */

	public static void logInfo(String format, Object... args)
	{
		if (!LogPreferences.isUseDebug())
			return;

		internalLog(INFO_TYPE, String.format(format, args));
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de notice.
	 * @param str conte�do do qual ser� usado como registro.
	 */

	public static void logNotice(String str)
	{
		internalLog(NOTICE_TYPE, str);
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de notice.
	 * @param format formado da mensagem que ser� exibida pelo registro.
	 * @param args refer�ncia dos objetos de acordo com a formata��o.
	 */

	public static void logNotice(String format, Object... args)
	{
		if (!LogPreferences.isUseDebug())
			return;

		internalLog(NOTICE_TYPE, String.format(format, args));
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de warning.
	 * @param str conte�do do qual ser� usado como registro.
	 */

	public static void logWarning(String str)
	{
		internalLog(WARNING_TYPE, str);
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de warning.
	 * @param format formado da mensagem que ser� exibida pelo registro.
	 * @param args refer�ncia dos objetos de acordo com a formata��o.
	 */

	public static void logWarning(String format, Object... args)
	{
		if (!LogPreferences.isUseWarning())
			return;

		internalLog(WARNING_TYPE, String.format(format, args));
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de error.
	 * @param str conte�do do qual ser� usado como registro.
	 */

	public static void logError(String str)
	{
		internalLog(ERROR_TYPE, str);
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de error.
	 * @param format formado da mensagem que ser� exibida pelo registro.
	 * @param args refer�ncia dos objetos de acordo com a formata��o.
	 */

	public static void logError(String format, Object... args)
	{
		if (!LogPreferences.isUseError())
			return;

		internalLog(ERROR_TYPE, String.format(format, args));
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de exception.
	 * @param e exce��o gerada do qual ser� exibida junto com seu nome.
	 */

	public static void logExeception(Exception e)
	{
		String message = String.format("%s [%s]\n", e.getMessage(), e.getClass().getSimpleName());

		internalLog(EXCEPTION_TYPE, message);
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de exception.
	 * @param e exce��o gerada do qual ser� usada a mensagem como registro.
	 */

	public static void logExeceptionMessage(Exception e)
	{
		internalLog(EXCEPTION_TYPE, e.getMessage());
	}

	/**
	 * Imprimi uma determina mensagem formatada no sistema de registros.
	 * Verifica se o uso de console como tamb�m o de arquivo est�o habilitados.
	 * Nesse caso apenas se estiver habilitado o registro de exception.
	 * @param e exce��o gerada do qual ser� exibida junto com seu nome.
	 */

	public static void logExeceptionSource(Exception e)
	{
		String message = String.format("%s [%s]\n", e.getMessage(), e.getClass().getSimpleName());

		upSource = -2;

		internalLog(EXCEPTION_TYPE, message, e);
	}
}
