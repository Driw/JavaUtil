package org.diverproject.util.stream;

/**
 * <h1>Sa�da</h1>
 *
 * <p>Usado para determinar uma stream como sa�da de dados na aplica��o, tamb�m � um escritor na comunica��o.
 * Possui alguns procedimentos b�sicos que ir� permitir trabalhar com dados primitivos atrav�s dos bytes.
 * Classes que implementem essa interface dever�o especificar como ser� feito a convers�o dos dados.</p>
 *
 * @see Output
 *
 * @author Andrew Mello
 */

public interface Output extends Writer
{
	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Para esse caso ser� colocado um byte direto, funcionando como uma simples escrita.
	 * @param value valor de um �nico byte do qual ser� escrito na comunica��o estabelecida.
	 */

	void putByte(byte value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Para esse caso ser� colocado um ou mais bytes direto, funcionando como uma simples escrita.
	 * @param values bytes ou um vetor de bytes que dever�o ser escritos na stream.
	 */

	void putBytes(byte... values);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso o caracter que for passado ser� convertido em um byte e escrito em seguida.
	 * @param value valor de um byte representando um caracter a ser escrito.
	 */

	void putChar(char value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso os caracteres que forem passados ser�o convertidos em bytes e escrito em seguida.
	 * @param values caracter ou um vetor de caracteres que dever�o ser escritos na stream.
	 */

	void putChars(char... values);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso o n�mero que for passado ser� convertido em um byte e escrito em seguida.
	 * @param value valor de dois bytes representando um n�mero short a ser escrito.
	 */

	void putShort(short value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso os n�meros que forem passados ser�o convertidos em bytes e escritos em seguida.
	 * @param values n�meros short ou um vetor de short que dever�o ser escritos na stream.
	 */

	void putShorts(short... value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso o n�mero que for passado ser� convertido em um byte e escrito em seguida.
	 * @param value valor de quatro bytes representando um n�mero int a ser escrito.
	 */

	void putInt(int value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso os n�meros que forem passados ser�o convertidos em bytes e escritos em seguida.
	 * @param values n�meros inteiros ou um vetor de int que dever�o ser escritos na stream.
	 */

	void putInts(int... value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso o n�mero que for passado ser� convertido em um byte e escrito em seguida.
	 * @param value valor de oito bytes representando um n�mero long a ser escrito.
	 */

	void putLong(long value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso os n�meros que forem passados ser�o convertidos em bytes e escritos em seguida.
	 * @param values n�meros long ou um vetor de long que dever�o ser escritos na stream.
	 */

	void putLongs(long... value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso o n�mero que for passado ser� convertido em um byte e escrito em seguida.
	 * @param value valor de quatro bytes representando um n�mero float a ser escrito.
	 */

	void putFloat(float value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso os n�meros que forem passados ser�o convertidos em bytes e escritos em seguida.
	 * @param values n�meros flutuantes ou um vetor de float que dever�o ser escritos na stream.
	 */

	void putFloats(float... value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso o n�mero que for passado ser� convertido em um byte e escrito em seguida.
	 * @param value valor de quatro bytes representando um n�mero double a ser escrito.
	 */

	void putDouble(double value);

	/**
	 * Deve escrever um determinado valor dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Nesse caso os n�meros que forem passados ser�o convertidos em bytes e escritos em seguida.
	 * @param values n�meros double ou um vetor de double que dever�o ser escritos na stream.
	 */

	void putDoubles(double... value);

	/**
	 * Deve escrever uma determinada string dentro da comunica��o de acordo com o ponteiro de escrita.
	 * O primeiro byte escrito de cada string ser� para definir a quantidade de caracteres.
	 * @param str string contendo os bytes representados em caracteres que ser�o escritos.
	 */

	void putString(String str);

	/**
	 * Deve escrever determinadas strings dentro da comunica��o de acordo com o ponteiro de escrita.
	 * O primeiro byte escrito de cada string ser� para definir a quantidade de caracteres.
	 * @param values strings ou um vetor com strings que dever�o ser escritos.
	 */

	void putStrings(String... values);

	/**
	 * Deve escrever uma determinada string dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Para esse caso ser� escrito todo o conte�do poss�vel da string e preencher o resto com nulos.
	 * @param str string contendo os bytes representados em caracteres que ser�o escritos.
	 * @param length quantos caracteres dever�o fazer parte do contexto da string.
	 */

	void putString(String str, int length);

	/**
	 * Deve escrever determinadas strings dentro da comunica��o de acordo com o ponteiro de escrita.
	 * Para esse caso ser� escrito todo o conte�do poss�vel da string e preencher o resto com nulos.
	 * @param length quantos caracteres dever�o fazer parte do contexto de cada string.
	 * @param values strings ou um vetor com strings que dever�o ser escritos.
	 */

	void putStrings(int length, String... values);

	/**
	 * Flush serve para liberar os dados da stream para a fonte do mesmo (arquivo ou conex�o).
	 * Utilizado quando o gerenciador tem que enviar os dados mas n�o pode fechar a stream ainda.
	 */

	void flush();
}
