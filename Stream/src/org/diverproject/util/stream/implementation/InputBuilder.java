package org.diverproject.util.stream.implementation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.diverproject.util.stream.Input;
import org.diverproject.util.stream.implementation.input.InputByteArray;
import org.diverproject.util.stream.implementation.input.InputMapped;

/**
 * <h1>Construtor de Entrada</h1>
 *
 * <p>Criado para funcionar como meio intermedi�rio entre a cria��o da comunica��o e a f�brica.
 * Possuindo apenas os m�todos que por nome a par�metros � poss�vel entender sua funcionalidade.
 * Assim, ser� poss�vel "esconder" a real origem das comunica��es que foram usadas por ele.</p>
 *
 * <p>Interface que possui todos os m�todos poss�veis para a cria��o de comunica��es para entrada de dados.
 * As poss�veis alternativas oferecidas � de um arquivo, InputStream ou ent�o em um vetor de bytes.
 * A forma como � feito a leitura dos dados ser� respectiva de acordo com a f�brica que gerou este.</p>
 *
 * @see Input
 *
 * @author Andrew Mello
 */

public class InputBuilder
{
	/**
	 * Cria uma nova comunica��o com um arquivo para comunica��o de dados atrav�s do caminho especificado.
	 * @param path caminho parcial respectivo a aplica��o ou completo do arquivo que ser� lido.
	 * @return aquisi��o de uma nova comunica��o para entrada de dados a partir de um arquivo.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public Input newInput(String path) throws IOException
	{
		return new InputMapped(path);
	}

	/**
	 * Cria uma nova comunica��o com um arquivo para comunica��o de dados atrav�s do caminho especificado.
	 * @param file refer�ncia do objeto que cont�m as informa��es do caminho do arquivo a ser lido.
	 * @return aquisi��o de uma nova comunica��o para entrada de dados a partir de um arquivo.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public Input newInput(File file) throws IOException
	{
		return new InputMapped(file);
	}

	/**
	 * Cria uma nova comunica��o com uma comunica��o para entrada de dados padr�o do java especificado.
	 * @param is refer�ncia da comunica��o padr�o do java pra entrada de dados que ser� usada.
	 * @return aquisi��o de uma nova comunica��o avan�ada a partir de uma padr�o do java.
	 */

	public Input newInput(InputStream is)
	{
		return new org.diverproject.util.stream.implementation.input.InputStream(is);
	}

	/**
	 * Constr�i uma nova comunica��o usando um vetor de bytes para fazer a entrada de dados.
	 * @param data refer�ncia do vetor que ser� usado para fazer a leitura dos dados.
	 * @return aquisi��o de uma nova comunica��o para entrada de dados com vetor.
	 */

	public Input newInput(byte[] data)
	{
		return new InputByteArray(data);
	}
}
