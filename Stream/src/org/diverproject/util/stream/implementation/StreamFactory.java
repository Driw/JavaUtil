package org.diverproject.util.stream.implementation;

/**
 * <h1>F�brica de Stream</h1>
 *
 * <p>Stream � um meio de comunica��o que no caso ser� de dados em bytes com a aplica��o desenvolvida.
 * Essa comunica��o ir� permitir interpretar dados <b>crus</b> em puro bytes para dados primitivos.
 * Tais como valores num�ricos inteiros (short, int e long) como flutuantes (float e double).</p>
 *
 * <p>A f�brica vai funcionar como uma esp�cie de fronteira entre o desenvolvimento e esses servi�os.
 * Atrav�s dele ser� poss�vel obter as montadoras que ir�o de fato criar as streams de acordo com o desejado.
 * No caso pode ser um arquivo pelo seu caminho especificado ou ainda ent�o um objeto do tipo File.
 * Como ainda permite trabalhar com InputStream e OutputStream e tamb�m usando um vetor de bytes como dados.</p>
 *
 * <p>A utiliza��o do Factory Method foi uma das formas de aumentar o encapsulamento das classes envolvidas.
 * De modo que, n�o seja poss�vel acessar estas diretamente (instanciar) como se quer visualizar estas.
 * Por tanto, a visualiza��o ficar� como responsabilidade das interfaces que ir�o redirecionar aos m�todos.</p>
 *
 * @see BufferBuilder
 * @see InputBuilder
 * @see OutputBuilder
 *
 * @author Andrew Mello
 */

public class StreamFactory
{
	/**
	 * Cria uma nova inst�ncia para o montador de streams para trabalhar diretamente com buffers.
	 * Buffers aqui ser� considerado como um vetor de bytes com conte�do ou totalmente em branco.
	 * Os buffers tamb�m n�o ir�o copiar o vetor, ir�o utiliz�-lo diretamente com fonte de dados.
	 * @return refer�ncia do montador de buffers para interpreta��o de dados em bytes.
	 */

	public BufferBuilder newBufferBuilder()
	{
		return new BufferBuilder();
	}

	/**
	 * Cria uma nova inst�ncia para o montador de streams para trabalhar com streams do Java.
	 * Essas streams s�o referentes apenas ao lado de entrada de dados, ou seja, a leitura destes.
	 * Streams desse tipo podem ser originadas ou por um InputStream ou ent�o por um arquivo.
	 * @return refer�ncia do montador de entradas para interpreta��o de dados em bytes.
	 */

	public InputBuilder newInputBuilder()
	{
		return new InputBuilder();
	}

	/**
	 * Cria uma nova inst�ncia para o montador de streams para trabalhar com streams do Java.
	 * Essas streams s�o referentes apenas ao lado de sa�da de dados, ou seja, escrev�-los.
	 * Streams desse tipo podem ser originadas ou por um OutputStream ou para escrever arquivos.
	 * @return refer�ncia do montador de sa�das da interpreta��o de dados em bytes.
	 */

	public OutputBuilder newOutputBuilder()
	{
		return new OutputBuilder();
	}

	/**
	 * Cria uma nova inst�ncia para o montador de streams para trabalhar com op��es.
	 * Essas streams s�o referentes tanto ao lado de leitura de op��es como grava��o delas.
	 * @return refer�ncia do montador de stream para interpreta��o de op��es como bytes.
	 */

	public OptionBuilder newOptionBuilder()
	{
		return new OptionBuilder();
	}
}
