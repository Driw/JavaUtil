package org.diverproject.util.collection;

import org.diverproject.util.lang.CharUtil;
import org.diverproject.util.lang.IntUtil;

/**
 * <p><h1>String Estendida</h1></p>
 *
 * <p>Estrutura de dados que permite trabalhar com uma �nica string ou conte�do de uma.
 * Permite iniciar o conte�do dessa string e inserir dados no final dessa mesma.
 * Al�m disso permite percorrer a string com m�todos next() e back() dentre outros.</p>
 *
 * @author Andrew
 */

public class StringExtend
{
	/**
	 * �ndice do caracter do n� atual.
	 */
	private int offset;

	/**
	 * Conte�do respectivo do n�.
	 */
	private String string;

	/**
	 * Constr�i um novo n� de caracteres com conte�do em branco.
	 */

	public StringExtend()
	{
		this("");
	}

	/**
	 * Constr�i um novo n� de caracteres atrav�s de uma string.
	 * @param str string que ser� usada como conte�do do n�.
	 */

	public StringExtend(String str)
	{
		string = str;
		offset = 0;
	}

	/**
	 * Todo n� de caracter deve possuir um caracter armazenado no mesmo.
	 * @return aquisi��o do caracter do qual esse n� armazena.
	 */

	public char get()
	{
		return get(offset);
	}

	/**
	 * Permite obter um determinado caracter de um determinado �ndice.
	 * @param index �ndice do posicionamento do caracter.
	 * @return caracter respectivo ou null se n�o encontrar.
	 */

	public char get(int index)
	{
		if (index < 0 || index >= string.length())
			return 0;

		return string.charAt(index);
	}

	/**
	 * Verifica se o n� possui um pr�ximo caracter, se houver substitui os dados pelo pr�ximo.
	 * @return true se houver um pr�ximo caracter ou false caso contr�rio.
	 */

	public boolean next()
	{
		if (finish())
			return false;

		offset++;

		return true;
	}

	/**
	 * Verifica se o n� possui um caracter anterior, se houver substitui os dados pelo anterior.
	 * @return true se houver um caracter anterior ou false caso contr�rio.
	 */

	public boolean back()
	{
		if (start())
			return false;

		offset--;

		return true;
	}

	/**
	 * Faz com que o ponteiro avance na string se assim for poss�vel
	 * @return caracter atual do qual o ponteiro est� se referindo.
	 */

	public char nextChar()
	{
		char c = get();
		next();

		return c;
	}

	/**
	 * Faz com que o ponteiro retroceda na string se assim for poss�vel
	 * @return caracter atual do qual o ponteiro est� se referindo.
	 */

	public char backChar()
	{
		char c = get();
		back();

		return c;
	}

	/**
	 * Verifica se esse n� est� atualmente no primeiro elemento do mesmo.
	 * Nesse caso significa que n�o h� mais caracteres anteriores.
	 * @return true se chegou ao fim ou false caso contr�rio.
	 */

	public boolean start()
	{
		return offset < 0;
	}

	/**
	 * Verifica se esse n� est� atualmente no �ltimo elemento do mesmo.
	 * Nesse caso significa que n�o h� mais caracteres seguintes.
	 * @return true se chegou ao fim ou false caso contr�rio.
	 */

	public boolean finish()
	{
		return offset >= string.length();
	}

	/**
	 * Faz com que o ponteiro na string retorne ao inicio do mesmo.
	 */

	public void restart()
	{
		offset = -1;
	}

	/**
	 * Faz com que o ponteiro da string avance para o fim do mesmo.
	 */

	public void terminate()
	{
		offset = string.length();
	}

	/**
	 * Permite inserir um determinado caracter no �ltimo n�.
	 * @param c character do qual deve ser inserido no �ltimo n�.
	 */

	public void insert(char c)
	{
		string += c;
	}

	/**
	 * Verifica se um determinado caracter � igual ao que est� armazenado no n�.
	 * @param character qual o caracter que ser� verificado a igualdade.
	 * @return true se forem iguais ou false caso contr�rio.
	 */

	public boolean is(char character)
	{
		return get() == character;
	}

	/**
	 * �ndice determina o ponteiro de navega��o dentro do n� de caracteres.
	 * @return aquisi��o do �ndice desse n� de caracteres.
	 */

	public int offset()
	{
		return offset;
	}

	/**
	 * Comprimento � obtido atrav�s da string conte�do usada.
	 * @return aquisi��o do comprimento da string estendida.
	 */

	public int length()
	{
		return string.length();
	}

	/**
	 * Faz com que o ponteiro se mova para uma determinada posi��o na string.
	 * Quando definido n�o permite passar do inicio ou do fim da string.
	 * Quando -1 significa que n�o foi iterado e quando no limita determina
	 * que a itera��o j� chegou ao fim e n�o h� mais nada para iterar.
	 * @param offset nova posi��o (�ndice) do ponteiro na string.
	 */

	public void to(int offset)
	{
		this.offset = IntUtil.limit(offset, -1, string.length());
	}

	/**
	 * Constr�i uma nova string com um determinado conte�do a partir desse n� de caracteres.
	 * @param offset �ndice do primeiro caracter que deve ser usado nessa string.
	 * @param length quantos caracteres essa nova string dever� possuir.
	 * @return string contendo o conte�do de acordo com os par�metros passados,
	 * caso o �ndice seja inv�lido retorna string em branco, no caso de o tamanho
	 * for al�m do que � poss�vel n�o ir� retornar caracteres amais ou exception.
	 */

	public String cut(int offset, int length)
	{
		return new String(CharUtil.subarray(string.toCharArray(), offset, length));
	}

	/**
	 * Constr�i uma nova string com o conte�do pr�ximo ao offset.
	 * @param length quantos caracteres a esquerda e a direita.
	 * @return string contendo os valores pr�ximos ao offset.
	 */

	public Object fear(int length)
	{
		return new String(CharUtil.subarray(string.toCharArray(), offset - length, length * 2));
	}

	/**
	 * Acrescenta um determinado conte�do especificado em string (caracteres) ao final.
	 * @param string refer�ncia do objeto contendo o conjunto de caracteres (string).
	 */

	public void append(String string)
	{
		this.string += string;
	}

	/**
	 * Em alguns casos pode ser necess�rio se obter o conte�do direto da string.
	 * Isso ir� ocorrer principalmente quando o m�todo deleteAt for utilizado.
	 * @return aquisi��o direta do conte�do que est� sendo usado internamente.
	 */

	public String raw()
	{
		return this.string;
	}

	/**
	 * Excluir todo o conte�do inicial da string interna at� um ponto delimitado.
	 * Essa exclus�o ir� incluir o caracter especificado �ndice por par�metro.
	 * @param c at� qual caracter que ser� exclu�do, ser� usado como �ndice.
	 */

	public void deleteAt(char c)
	{
		deleteAt(c, 1);
	}

	/**
	 * Excluir todo o conte�do inicial da string interna at� um ponto delimitado.
	 * Essa exclus�o ir� incluir o caracter especificado �ndice por par�metro.
	 * Nesse caso ir� procurar uma caracter espec�fico repetido em x vezes.
	 * @param c caracter do qual dever� ser encontrado para ser o �ndice.
	 * @param time n�mero de repeti��es que dever� ocorrer desse caracter.
	 */

	public void deleteAt(char c, int time)
	{
		for (int i = 0; i < string.length(); i++)
		{
			if (string.charAt(i) == c)
				time--;

			if (time == 0)
				deleteAt(i);
		}
	}

	/**
	 * Excluir todo o conte�do inicial da string interna at� um ponto delimitado.
	 * Essa exclus�o ir� incluir o caracter especificado �ndice por par�metro.
	 * @param index �ndice do caracter que ser� recortado do inicio at� ele.
	 */

	public void deleteAt(int index)
	{
		if (offset < index)
			offset = 0;

		string = string.substring(index);
	}

	/**
	 * Procedimento interno que ir� formatar o conte�do adicionando chaves ao caracter ponteiro.
	 * Utilizado somente e diretamente por toString afim de facilitar a utiliza��o do mesmo.
	 * @return aquisi��o de uma string formatada conforma as especifica��es do m�todo.
	 */

	private String getCotent()
	{
		String before = string.substring(0, IntUtil.min(offset, 0));
		String after = string.substring(IntUtil.max(offset + 1, string.length()), string.length());

		if (start())
			return String.format("[]%s", after);

		else if (finish())
			return String.format("%s[]", before);

		return String.format("%s[%s]%s", before, get(), after);
	}

	@Override
	public String toString()
	{
		return String.format("CharNode[prev: %s, char: %s, next: %s, content: %s]", get(offset - 1), get(), get(offset + 1), getCotent());
	}
}
