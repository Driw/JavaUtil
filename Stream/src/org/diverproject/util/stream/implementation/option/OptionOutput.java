package org.diverproject.util.stream.implementation.option;

import org.diverproject.util.stream.StreamException;

/**
 * <p><h1>Stream para Sa�da de Op��es</h1></p>
 *
 * <p>Essa interface � implementada pelas streams que permite salvar dados de op��es formatadas.
 * Por padr�o o sistema de stream Util utiliza um byte para determinar o tipo do dado salvo,
 * outro byte para determinar o tamanho do nome da op��es e em seguida o nome dessa op��o.
 * E por �ltimo dever� salvar o dado da prefer�ncia de acordo com o tipo de dado que ser� salvo.</p>
 *
 * <p>Interface que ir� obrigar a classe a implementar todos os m�todos para se obter essas op��es.
 * Esses getters s�o para todos os tipos de op��es que ir�o retornar atributos primitivos do java.</p>
 *
 * @author Andrew
 */

public interface OptionOutput extends StreamOption
{
	/**
	 * Flush serve para liberar os dados da stream para a fonte do mesmo (arquivo ou conex�o).
	 * Utilizado quando o gerenciador tem que enviar os dados mas n�o pode fechar a stream ainda.
	 */

	void flush();

	/**
	 * Fecha por completo uma stream, fechando os objetos do java (input/output) e excluindo o buffer.
	 */

	void close();

	/**
	 * Escreve uma nova op��o na stream do tipo byte.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putByte(String name, byte value);

	/**
	 * Escreve uma nova op��o na stream do tipo char.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putChar(String name, char value);

	/**
	 * Escreve uma nova op��o na stream do tipo short.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putShort(String name, short value);

	/**
	 * Escreve uma nova op��o na stream do tipo int.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 * @throws StreamException stream fechada ou falha na escrita.
	 */

	void putInt(String name, int value);

	/**
	 * Escreve uma nova op��o na stream do tipo long.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putLong(String name, long value);

	/**
	 * Escreve uma nova op��o na stream do tipo float.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putFloat(String name, float value);

	/**
	 * Escreve uma nova op��o na stream do tipo double.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putDouble(String name, double value);

	/**
	 * Escreve uma nova op��o na stream do tipo string.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putString(String name, String value);

	/**
	 * Escreve uma nova op��o na stream do tipo boolean.
	 * @param name nome da op��o do qual est� sendo escrita.
	 * @param value valor respectiva ao nome da op��o passada.
	 */

	void putBoolean(String name, boolean value);
}
