package org.diverproject.util.lang;

/**
 * <p><h1>Utilit�rio para Char</h1></p>
 *
 * Classe com m�todos utilit�rios para manusear valores do tipo char.
 * Todos os m�todos envolvem retornar atributos do tipo <b>char</b>.
 * Em alguns casos h� m�todos que retornam objetos do tipo <b>Character</b>.
 * Apesar de ambos serem do mesmo tipo, algumas opera��es necessitam
 * utilizar o objeto ao inv�s do atributo, e vice-versa.
 */

public class CharUtil
{
	/**
	 * C�digo para identifica��o do caracter: <b>Null or Time Fill Character</b>.
	 */
	public static final int CC_NUL = 0;

	/**
	 * C�digo para identifica��o do caracter: <b>Start of Heading</b>.
	 */
	public static final int CC_SOH = 1;

	/**
	 * C�digo para identifica��o do caracter: <b>Start of Text</b>.
	 */
	public static final int CC_STX = 2;

	/**
	 * C�digo para identifica��o do caracter: <b>End of Text (EOM)</b>.
	 */
	public static final int CC_ETX = 3;

	/**
	 * C�digo para identifica��o do caracter: <b>End of Transmission</b>.
	 */
	public static final int CC_EOT = 4;

	/**
	 * C�digo para identifica��o do caracter: <b>Enquiry (WRU)</b>.
	 */
	public static final int CC_ENQ = 5;

	/**
	 * C�digo para identifica��o do caracter: <b>Acknowledge (RU)</b>.
	 */
	public static final int CC_ACK = 6;

	/**
	 * C�digo para identifica��o do caracter: <b>Bell</b>.
	 */
	public static final int CC_BEL = 7;

	/**
	 * C�digo para identifica��o do caracter: <b>Back Space</b>.
	 */
	public static final int CC_BS  = 8;

	/**
	 * C�digo para identifica��o do caracter: <b>Horizontal Tab</b>.
	 */
	public static final int CC_HY  = 9;

	/**
	 * C�digo para identifica��o do caracter: <b>Line Feed</b>.
	 */
	public static final int CC_LF  = 10;

	/**
	 * C�digo para identifica��o do caracter: <b>Vertical Tab</b>.
	 */
	public static final int CC_VT  = 11;

	/**
	 * C�digo para identifica��o do caracter: <b>Form Feed</b>.
	 */
	public static final int CC_FF  = 12;

	/**
	 * C�digo para identifica��o do caracter: <b>Carriage Return</b>.
	 */
	public static final int CC_CR  = 13;

	/**
	 * C�digo para identifica��o do caracter: <b>Shift Out</b>.
	 */
	public static final int CC_SO  = 14;

	/**
	 * C�digo para identifica��o do caracter: <b>Shift In</b>.
	 */
	public static final int CC_SI  = 15;

	/**
	 * C�digo para identifica��o do caracter: <b>Data Link Escape</b>.
	 */
	public static final int CC_DLE = 16;

	/**
	 * C�digo para identifica��o do caracter: <b>Device Control 1 (XON)</b>.
	 */
	public static final int CC_DC1 = 17;

	/**
	 * C�digo para identifica��o do caracter: <b>Device Control 2 (TAPE)</b>.
	 */
	public static final int CC_DC2 = 18;

	/**
	 * C�digo para identifica��o do caracter: <b>Device Control 3 (XOFF)</b>.
	 */
	public static final int CC_DC3 = 19;

	/**
	 * C�digo para identifica��o do caracter: <b>Device Controle 4 (TAPE)</b>.
	 */
	public static final int CC_DC4 = 20;

	/**
	 * C�digo para identifica��o do caracter: <b>Negative Acknowledge</b>.
	 */
	public static final int CC_NAK = 21;

	/**
	 * C�digo para identifica��o do caracter: <b>Synchronous Idle</b>.
	 */
	public static final int CC_SYN = 22;

	/**
	 * C�digo para identifica��o do caracter: <b>End of Transmission Blocks</b>.
	 */
	public static final int CC_ETB = 23;

	/**
	 * C�digo para identifica��o do caracter: <b>Cancel</b>.
	 */
	public static final int CC_CAN = 24;

	/**
	 * C�digo para identifica��o do caracter: <b>End of Medium</b>.
	 */
	public static final int CC_EM  = 25;

	/**
	 * C�digo para identifica��o do caracter: <b>Special Sequence</b>.
	 */
	public static final int CC_SS = 26;

	/**
	 * C�digo para identifica��o do caracter: <b>Escape</b>.
	 */
	public static final int CC_ESC = 27;

	/**
	 * C�digo para identifica��o do caracter: <b>File Separator</b>.
	 */
	public static final int CC_FS  = 28;

	/**
	 * C�digo para identifica��o do caracter: <b>Group Separator</b>.
	 */
	public static final int CC_GS  = 29;

	/**
	 * C�digo para identifica��o do caracter: <b>Record Separator</b>.
	 */
	public static final int CC_RS  = 30;

	/**
	 * C�digo para identifica��o do caracter: <b>Unit Separator</b>.
	 */
	public static final int CC_US  = 31;

	/**
	 * C�digo para identifica��o do caracter: <b>Space</b>.
	 */
	public static final int CC_SPC = 32;


	/**
	 * Caracter que representa a funcionalidade de: <b>Null or Time Fill Character</b>.
	 */
	public static final char NUL = 0;

	/**
	 * Caracter que representa a funcionalidade de: <b>Start of Heading</b>.
	 */
	public static final char SOH = 1;

	/**
	 * Caracter que representa a funcionalidade de: <b>Start of Text</b>.
	 */
	public static final char STX = 2;

	/**
	 * Caracter que representa a funcionalidade de: <b>End of Text (EOM)</b>.
	 */
	public static final char ETX = 3;

	/**
	 * Caracter que representa a funcionalidade de: <b>End of Transmission</b>.
	 */
	public static final char EOT = 4;

	/**
	 * Caracter que representa a funcionalidade de: <b>Enquiry (WRU)</b>.
	 */
	public static final char ENQ = 5;

	/**
	 * Caracter que representa a funcionalidade de: <b>Acknowledge (RU)</b>.
	 */
	public static final char ACK = 6;

	/**
	 * Caracter que representa a funcionalidade de: <b>Bell</b>.
	 */
	public static final char BEL = 7;

	/**
	 * Caracter que representa a funcionalidade de: <b>Back Space</b>.
	 */
	public static final char BS  = 8;

	/**
	 * Caracter que representa a funcionalidade de: <b>Horizontal Tab</b>.
	 */
	public static final char HY  = 9;

	/**
	 * Caracter que representa a funcionalidade de: <b>Line Feed</b>.
	 */
	public static final char LF  = 10;

	/**
	 * Caracter que representa a funcionalidade de: <b>Vertical Tab</b>.
	 */
	public static final char VT  = 11;

	/**
	 * Caracter que representa a funcionalidade de: <b>Form Feed</b>.
	 */
	public static final char FF  = 12;

	/**
	 * Caracter que representa a funcionalidade de: <b>Carriage Return</b>.
	 */
	public static final char CR  = 13;

	/**
	 * Caracter que representa a funcionalidade de: <b>Shift Out</b>.
	 */
	public static final char SO  = 14;

	/**
	 * Caracter que representa a funcionalidade de: <b>Shift In</b>.
	 */
	public static final char SI  = 15;

	/**
	 * Caracter que representa a funcionalidade de: <b>Data Link Escape</b>.
	 */
	public static final char DLE = 16;

	/**
	 * Caracter que representa a funcionalidade de: <b>Device Control 1 (XON)</b>.
	 */
	public static final char DC1 = 17;

	/**
	 * Caracter que representa a funcionalidade de: <b>Device Control 2 (TAPE)</b>.
	 */
	public static final char DC2 = 18;

	/**
	 * Caracter que representa a funcionalidade de: <b>Device Control 3 (XOFF)</b>.
	 */
	public static final char DC3 = 19;

	/**
	 * Caracter que representa a funcionalidade de: <b>Device Controle 4 (TAPE)</b>.
	 */
	public static final char DC4 = 20;

	/**
	 * Caracter que representa a funcionalidade de: <b>Negative Acknowledge</b>.
	 */
	public static final char NAK = 21;

	/**
	 * Caracter que representa a funcionalidade de: <b>Synchronous Idle</b>.
	 */
	public static final char SYN = 22;

	/**
	 * Caracter que representa a funcionalidade de: <b>End of Transmission Blocks</b>.
	 */
	public static final char ETB = 23;

	/**
	 * Caracter que representa a funcionalidade de: <b>Cancel</b>.
	 */
	public static final char CAN = 24;

	/**
	 * Caracter que representa a funcionalidade de: <b>End of Medium</b>.
	 */
	public static final char EM  = 25;

	/**
	 * Caracter que representa a funcionalidade de: <b>Special Sequence</b>.
	 */
	public static final char SS = 26;

	/**
	 * Caracter que representa a funcionalidade de: <b>Escape</b>.
	 */
	public static final char ESC = 27;

	/**
	 * Caracter que representa a funcionalidade de: <b>File Separator</b>.
	 */
	public static final char FS  = 28;

	/**
	 * Caracter que representa a funcionalidade de: <b>Group Separator</b>.
	 */
	public static final char GS  = 29;

	/**
	 * Caracter que representa a funcionalidade de: <b>Record Separator</b>.
	 */
	public static final char RS  = 30;

	/**
	 * Caracter que representa a funcionalidade de: <b>Unit Separator</b>.
	 */
	public static final char US  = 31;

	/**
	 * Caracter que representa a funcionalidade de: <b>Space</b>.
	 */
	public static final char SPC = 32;

	/**
	 * Caracter que representa a funcionalidade de: <b>Delete</b>.
	 */
	public static final char DEL = 127;


	/**
	 * Vetor contendo o nome descritivo (sigla) de cada caracter respectivo ao c�digo do mesmo.
	 */
	public static final String CHARACTERS_NAME[] = new String[]
	{
		"NUL", "SOH", "STX", "ETX", "EOT", "ENQ", "ACK", "BEL", "BS", "HT", "LF", "VT", "FF", "CR", "SO", "SI",
		"DLE", "DC1", "DC2", "DC3", "DC4", "NAK", "SYN", "ETB", "CAN", "EM", "SUB", "ESC", "FS", "GS", "RS", "US", "SPC",
		"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/",
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?", "@",
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
		"[", "\\", "]", "^", "_", "`",
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
		"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
		"{", "|", "}", "~", "DEL",
		"?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?",
		"?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?",
		"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
		"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
		"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
		"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
		"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
		"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
	};

	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private CharUtil()
	{
		
	}

	/**
	 * Exibe um determinado vetor de caracteres no console separado por �ndice.
	 * Por padr�o esse m�todo ir� exibir apenas 20 elementos por linha.
	 * @param bytes vetor com os caracteres que ser�o exibidos no console.
	 */

	public static void print(char[] array)
	{
		print((short) 20, array);
	}

	/**
	 * Exibe um determinado vetor de caracteres no console em linha.
	 * @param perline quantos elementos ser�o exibidos por linha.
	 * @param array vetor com os caracteres que ser�o impressos.
	 */

	public static void print(short perline, char[] array)
	{
		print(perline, array, true);
	}

	/**
	 * Exibe um determinado vetor de caracteres no console em linha.
	 * @param perline quantos elementos ser�o exibidos por linha.
	 * @param array vetor com os caracteres que ser�o impressos.
	 * @param ascii exibir como c�digo ascii?
	 */

	public static void print(short perline, char[] array, boolean ascii)
	{
		if (array == null || array.length < 1)
			return;

		for (int i = 0; i < array.length; i++)
		{
			if (i % perline == 0)
				System.out.printf("\ni%4d: ", i + 1);

			if (ascii)
				System.out.printf("[%5d]", (int) array[i]);
			else
			{
				String str = getString(array[i]);

				if (str.length() == 3)
					System.out.printf("[%s]", str);
				else
					System.out.printf("[ %s ]", str);
			}
		}

		System.out.println();
	}

	/**
	 * Converte uma determinada quantidade especificada de bytes em caracter.
	 * Espera-se receber um ou dois bytes, mais que isso n�o ser� convertido.
	 * @param bytes bytes que ser� convertido para caracter.
	 * @return caracter criado a partir do byte passado.
	 */

	public static char parseByte(byte... bytes)
	{
		char value = 0;

		int count = IntUtil.minor(bytes.length, Short.BYTES);

		for (int i = 0, j = 0; i < count; i++, j += 8)
		{
			int parsed = (bytes[bytes.length - i - 1] & 255) << j;
			value |= parsed;
		}

		return value;
	}

	/**
	 * Copia uma parte de um determinado vetor de caracteres. O modo de c�pia
	 * � feito por um ponto inicial <b>offset</b> (�ndice). A partir deste
	 * ponto ele ir� obter os pr�ximos n �ndices e inserir no vetor.
	 * @param array vetor que ser� copiado uma parte.
	 * @param offset ponto inicial da c�pia (�ndice).
	 * @param length quantos elementos ser�o copiados.
	 * @return vetor apenas com os elementos copiados.
	 */

	public static char[] subarray(char[] array, int offset, int length)
	{
		if (offset < 0)
			offset = 0;

		if (length < 0)
			length = 0;

		if (offset > array.length - 1)
			return new char[]{};

		if (offset + length > array.length)
			length = array.length - offset;

		char select[] = new char[length];

		for (int i = 0; i < length; i++)
			select[i] = array[offset + i];

		return select;
	}

	/**
	 * Obt�m um valor de um caracter em string, onde os caracteres de comandos
	 * como SHO, STX, ETX s�o retornados strings nesse pr�prio formato.
	 * @param c caracter que ser� convertido para uma string.
	 * @return string contendo o valor leg�vel do caracter.
	 */

	public static String getString(char c)
	{
		if (c < CHARACTERS_NAME.length)
			return CHARACTERS_NAME[c];

		return Character.toString(c);
	}

}
