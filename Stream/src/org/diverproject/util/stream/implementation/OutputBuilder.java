package org.diverproject.util.stream.implementation;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.diverproject.util.stream.Output;
import org.diverproject.util.stream.implementation.output.OutputByteArray;
import org.diverproject.util.stream.implementation.output.OutputMapped;

/**
 * <h1>Construtor de Sa�da</h1>
 *
 * <p>Criado para funcionar como meio intermedi�rio entre a cria��o da comunica��o e a f�brica.
 * Possuindo apenas os m�todos que por nome a par�metros � poss�vel entender sua funcionalidade.
 * Assim, ser� poss�vel "esconder" a real origem das comunica��es que foram usadas por ele.</p>
 *
 * <p>Interface que possui todos os m�todos poss�veis para a cria��o de comunica��es para sa�da de dados.
 * As poss�veis alternativas oferecidas � de um arquivo, OutputStream ou ent�o em um vetor de bytes.
 * A forma como � feito a escrita dos dados ser� respectiva de acordo com a f�brica que gerou este.</p>
 *
 * @see Output
 *
 * @author Andrew Mello
 */

public class OutputBuilder
{
	/**
	 * Cria uma nova comunica��o de sa�da com um arquivo de acordo com o caminho especificado abaixo.
	 * @param path caminho parcial em rela��o a aplica��o ou completo do arquivo a ser escrito.
	 * @return aquisi��o de uma nova comunica��o de sa�da com o arquivo especificado arquivo.
	 * @throws IOException apenas se houver algum problema para iniciar a stream do arquivo.
	 */

	public Output newOutput(String path) throws IOException
	{
		return newOutput(new File(path));
	}

	/***
	 * Cria uma nova comunica��o de sa�da com um arquivo de acordo com a especifica��o abaixo do arquivo.
	 * @param file refer�ncia do objeto que cont�m as informa��es do caminho do arquivo a ser escrito.
	 * @return aquisi��o de uma nova comunica��o de sa�da com o arquivo especificado arquivo.
	 * @throws IOException apenas se houver algum problema para iniciar a stream do arquivo.
	 */

	public Output newOutput(File file) throws IOException
	{
		return new OutputMapped(file);
	}

	/**
	 * Cria uma nova comunica��o de sa�da com uma comunica��o de sa�da padr�o do java existente.
	 * @param os refer�ncia da comunica��o de sa�da padr�o do java que ser� usada como sa�da.
	 * @return aquisi��o de uma nova comunica��o de sa�da avan�ada com base no padr�o do java.
	 * @throws IOException apenas se houver algum problema para iniciar a stream do arquivo.
	 */

	public Output newOutput(OutputStream os)
	{
		return new org.diverproject.util.stream.implementation.output.OutputStream(os);
	}

	/**
	 * Cria uma nova comunica��o de sa�da onde os dados que forem escritos ser�o repassados a um vetor.
	 * @param data vetor de bytes que ser� usada como sa�da interna dos dados que forem escritos.
	 * @return aquisi��o de uma nova comunica��o de sa�da para um vetor de bytes.
	 */

	public Output newOutput(byte[] data)
	{
		return new OutputByteArray(data);
	}
}
