package org.diverproject.util;

/**
 * Bracket
 *
 * Classe que permite trabalhar na contagem para de par�ntese, colchete e chave.
 * Possuindo m�todos que ir�o fazer a contagem desses quando forem abertos ou fechados.
 * Essa contagem � salva internamente para que outros m�todos possam process�-los.
 *
 * Esses m�todos permitem verificar se h� ou n�o algum bracket que foi aberto e n�o fechou.
 * Al�m disso h� outros que verificam se foi verificado mais fechamento do que abertura.
 * Como tamb�m faz o oposto deste que � verificar se h� algum em aberto sem fechar.
 *
 * @author Andrew
 */

public class Bracket
{
	/**
	 * Contagem de chaves.
	 */
	int braces;

	/**
	 * Contagem de colchetes.
	 */
	int brackets;

	/**
	 * Contagem de par�nteses.
	 */
	int parenthesis;

	/**
	 * Verifica se um caracter � um tipo de abertura seja l� qual for esse.
	 * @param c caracter do qual ser� verificado se � uma abertura.
	 */

	public void parseOpen(char c)
	{
		if (c == '(')
			parenthesis++;

		else if (c == '[')
			brackets++;

		else if (c == '{')
			braces++;
	}

	/**
	 * Verifica se um caracter � um tipo de fechamento seja l� qual for esse.
	 * @param c caracter do qual ser� verificado se � um fechamento.
	 */

	public void parseClose(char c)
	{
		if (c == '}')
			braces--;

		else if (c == ']')
			brackets--;

		else if (c == ')')
			parenthesis--;
	}

	/**
	 * Faz a verifica��o para garantir se h� algum que foi aberto e n�o foi fechado.
	 * @return true se houver um seja l� qual for esse ou false caso n�o haja nenhum.
	 */

	public boolean has()
	{
		return braces != 0 || brackets != 0 || parenthesis != 0;
	}

	/**
	 * Verifica se h� mais fechamentos do que aberturas, isso nunca pode ocorrer.
	 * @throws UtilException mensagem respectiva a qual tipo de fechamento que tem muito.
	 */

	public void isMuch() throws UtilException
	{
		if (parenthesis < 0)
			throw new UtilException("muitos parenteses");

		if (brackets < 0)
			throw new UtilException("muitos conchetes");

		if (braces < 0)
			throw new UtilException("muitas chaves");
	}

	/**
	 * Verifica se h� mais aberturas do que fechamentos, isso n�o pode acontecer no final.
	 * @throws UtilException mensagem respectiva a qual tipo de abertura que tem muito.
	 */

	public void isLittle() throws UtilException
	{
		if (parenthesis > 0)
			throw new UtilException("falta parenteses");

		if (brackets > 0)
			throw new UtilException("falta conchetes");

		if (braces > 0)
			throw new UtilException("falta chaves");			
	}

	@Override
	public String toString()
	{
		return String.format("Bracket[braces: %d, bracket: %d, parenthesis: %d]", braces, brackets, parenthesis);
	}
}
