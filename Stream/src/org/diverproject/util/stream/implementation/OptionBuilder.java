package org.diverproject.util.stream.implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.diverproject.util.stream.implementation.input.InputMapped;
import org.diverproject.util.stream.implementation.option.OptionInput;
import org.diverproject.util.stream.implementation.option.OptionListInput;
import org.diverproject.util.stream.implementation.option.OptionListOutput;
import org.diverproject.util.stream.implementation.option.OptionOutput;
import org.diverproject.util.stream.implementation.output.OutputStream;

/**
 * <h1>Construtor de Entrada</h1>
 *
 * <p>Criado para funcionar como meio intermedi�rio entre a cria��o da comunica��o e a f�brica.
 * Possuindo apenas os m�todos que por nome a par�metros � poss�vel entender sua funcionalidade.
 * Assim, ser� poss�vel "esconder" a real origem das comunica��es que foram usadas por ele.</p>
 *
 * <p>Interface que possui todos os m�todos poss�veis para a cria��o de comunica��es para entrada de dados.
 * As poss�veis alternativas oferecidas � de um arquivo, InputOptionStream ou ent�o em um vetor de bytes.
 * A forma como � feito a leitura dos dados ser� respectiva de acordo com a f�brica que gerou este.</p>
 *
 * @see OptionInput
 *
 * @author Andrew Mello
 */

public class OptionBuilder
{
	/**
	 * Cria uma nova comunica��o com um arquivo para comunica��o de dados atrav�s do caminho especificado.
	 * @param path caminho parcial respectivo a aplica��o ou completo do arquivo que ser� lido.
	 * @return aquisi��o de uma nova comunica��o para entrada de dados a partir de um arquivo.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public OptionInput newInputOption(String path) throws IOException
	{
		return new OptionListInput(new InputMapped(path));
	}

	/**
	 * Cria uma nova comunica��o com um arquivo para comunica��o de dados atrav�s do caminho especificado.
	 * @param file refer�ncia do objeto que cont�m as informa��es do caminho do arquivo a ser lido.
	 * @return aquisi��o de uma nova comunica��o para entrada de dados a partir de um arquivo.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public OptionInput newInputOption(File file) throws IOException
	{
		return new OptionListInput(new InputMapped(file));
	}

	/**
	 * Cria uma nova comunica��o de sa�da com um arquivo de acordo com o caminho especificado abaixo.
	 * @param path caminho parcial em rela��o a aplica��o ou completo do arquivo a ser escrito.
	 * @return aquisi��o de uma nova comunica��o de sa�da com o arquivo especificado arquivo.
	 * @throws FileNotFoundException ocorre apenas se n�o for poss�vel encontrar o arquivo.
	 */

	public OptionOutput newOutputOption(String path) throws FileNotFoundException
	{
		return new OptionListOutput(new OutputStream(path));
	}

	/***
	 * Cria uma nova comunica��o de sa�da com um arquivo de acordo com a especifica��o abaixo do arquivo.
	 * @param file refer�ncia do objeto que cont�m as informa��es do caminho do arquivo a ser escrito.
	 * @return aquisi��o de uma nova comunica��o de sa�da com o arquivo especificado arquivo.
	 * @throws FileNotFoundException ocorre apenas se n�o for poss�vel encontrar o arquivo.
	 */

	public OptionOutput newOutputOption(File file) throws FileNotFoundException
	{
		return new OptionListOutput(new OutputStream(file));
	}
}
