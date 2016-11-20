package org.diverproject.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p><h1>Utilit�rio para Data</h1></p>
 *
 * Utilit�rios para gerenciamento (an�lise e convers�o) de dados do tipo data (Date).
 * Convers�es b�sicas de Date para String e String para Date e obten��o de partes dos dados.
 */

public class DateUtil
{
	/**
	 * Qual o formato padr�o que as datas devem possuir.
	 */
	public static final String DEFAULT_PATTERN = "dd/MM/yyyy";

	/**
	 * Qual ser� a data exibida quando for inv�lida.
	 */
	public static final String DEFAULT_DATE_ERROR = "00/00/0000";

	/**
	 * Vetor contendo os dias de cada m�s do ano.
	 */
	private static final int[] DAYS_OF_MONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private DateUtil()
	{
		
	}

	/**
	 * Converte uma determinada data para que possa ser leg�vel por pessoas em string.
	 * O formato em que a data convertida ir� possuir nesse caso ser� <code>DEFAULT_PATTERN</code>.
	 * @param date refer�ncia da data do qual deseja converter em string de modo formatado.
	 * @return aquisi��o da data passada no formato especificado por <code>DEFAULT_PATTERN</code>.
	 */

	public static String toString(Date date)
	{
		return toString(date, DEFAULT_PATTERN, DEFAULT_DATE_ERROR);
	}

	/**
	 * Converte uma determinada data para que possa ser leg�vel por pessoas em string.
	 * Para esse caso o formato em que a data ir� possuir dever� ser especificado.
	 * @param date refer�ncia da data do qual deseja converter em string de modo formatado.
	 * @param pattern qual dever� ser o padr�o usado em que a data ser� formatada.
	 * @return aquisi��o da data passada no formato especificado por <b>pattern</b>.
	 */

	public static String toString(Date date, String pattern)
	{
		return toString(date, pattern, DEFAULT_DATE_ERROR);
	}

	/**
	 * Converte uma determinada data para que possa ser leg�vel por pessoas em string.
	 * Para esse caso o formato em que a data ir� possuir dever� ser especificado.
	 * @param date refer�ncia da data do qual deseja converter em string de modo formatado.
	 * @param pattern qual dever� ser o padr�o usado em que a data ser� formatada.
	 * @param invalid resultado que ser� retornado caso a data seja inv�lida.
	 * @return aquisi��o da data passada no formato especificado por <b>pattern</b>.
	 */

	public static String toString(Date date, String pattern, String invalid)
	{
		if (pattern == null)
			pattern = DEFAULT_PATTERN;

		if (date == null)
			return invalid;

		DateFormat format = new SimpleDateFormat(pattern);
		String dateFormatted  = format.format(date);

		return dateFormatted;
	}

	/**
	 * Converte uma determinada data para um valor do tipo string.
	 * O formato neste m�todo deve corresponder a h:m:s:ms.
	 * @param date data que ser� convertida.
	 * @param prefix pr�-fixo que ser� usado.
	 * @return hor�rio da data no formato string.
	 */

	public static String toStringTime(Date date, String prefix)
	{
		if (date == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return String.format("%d%s%d%s%d%s%d", c.get(Calendar.HOUR_OF_DAY), prefix, c.get(Calendar.MINUTE)+1, prefix, c.get(Calendar.SECOND), prefix, c.get(Calendar.MILLISECOND));
	}

	/**
	 * Analisa uma determinada string e tenta convert�-la para um objeto do tipo Date.
	 * @param string qual a data (em formato string) que ser� convertida.
	 * @return objeto do tipo Date convertido.
	 * @throws ParseException exce��o que pode ocorrer durante a convers�o.
	 */

	public static Date toDate(String string) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(string);
	}

	/**
	 * Permite obter apenas um peda�o de um objeto do tipo date.
	 * @param date qual a data desejada para se obter o dia.
	 * @return dia da data que foi inserida em <b>date</b>.
	 */

	public static int getDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Permite obter apenas um peda�o de um objeto do tipo date.
	 * @param date qual a data desejada para se obter o m�s.
	 * @return m�s da data que foi inserida em <b>date</b>.
	 */

	public static int getMonth(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.MONTH)+1;
	}

	/**
	 * Permite obter apenas um peda�o de um objeto do tipo date.
	 * @param date qual a data desejada para se obter o ano.
	 * @return ano da data que foi inserida em <b>date</b>.
	 */

	public static int getYear(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.YEAR);
	}

	/**
	 * Retorna a quantidade de dias que um determinado m�s pode ter.
	 * Analisa o m�s e o ano (no caso do m�s de fevereiro).
	 * @param year ano que ser� analisado.
	 * @param month m�s do qual deseja o n�mero de dias.
	 * @return n�mero de dias no m�s e ano definido.
	 */

	public static int mountDays(int year, int month)
	{
		if (month >= 1 && month <= 12)
			return DAYS_OF_MONTH[month - 1] + (month == 2 && year % 4 == 0 ? 1 : 0);

		return 0;
	}
}
