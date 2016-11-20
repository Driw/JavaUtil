package org.diverproject.util.stream;

/**
 * <h1>Stream</h1>
 *
 * <p>Usado para determinar algumas funcionalidades padr�es para todos os meios de comunica��es.
 * Suas funcionalidades devem ser de permitir obter algumas informa��es em rela��o a essa comunica��o.
 * Ponteiro indicando onde est� a comunica��o, comprimento m�ximo, tamanho j� percorrido e espa�o sobrando.</p>
 *
 * <p>Al�m disso algumas outras fun��es tamb�m s�o de responsabilidade de uma stream saber como lidar.
 * Verificar se a stream est� vazia (prov�vel falha na comunica��o) ou ainda ent�o se ela foi fechada.
 * Uma vez que tenha sido fechada, as streams n�o devem permitir qualquer comunica��o com estas.</p>
 *
 * <p>Por �ltimo, em alguns casos, os dados primitivos s�o armazenados com seus bytes invertidos.
 * Para isso, � de responsabilidade da stream permitir essa alterna��o entre comunicar-se invertido.
 * Ela dever� ainda permitir verificar se a atual comunica��o est� ou n�o sendo feito invertido.</p>
 *
 * @author Andrew Mello
 */

public interface Stream
{
	/**
	 * Offset � a margem do inicio da comunica��o at� onde ela est� sendo feita no momento.
	 * Para facilitar, ser� denominado como ponteiro durante a utiliza��o da comunica��o.
	 * Esse ponteiro aponta para onde ser� feito a leitura dos pr�ximos dados solicitados.
	 * @return aquisi��o do �ndice do ponteiro de leitura, quantidade de bytes lidos.
	 */

	int offset();

	/**
	 * Comprimento � usado para garantir que mais dados do que o existente sejam lidos.
	 * Indicando assim, o espa�o exato de quantos bytes ser� poss�vel fazer a comunica��o.
	 * Podendo ainda tamb�m em caso de sa�da de dados, indicar o limite de dados para este.
	 * @return aquisi��o do limite de dados que poder�o <i>trafegar</i> durante a comunica��o.
	 */

	int length();

	/**
	 * Espa�o � uma fun��o muito importante, � usado para verificar se h� dados para serem lidos.
	 * Ou no caso de escrita verificar se h� espa�o para fazer o armazenamento dos dados escritos.
	 * Isso tamb�m pode ajudar como um indicador de que n�o h� mais nada a fazer na comunica��o.
	 * @return aquisi��o da quantidade de bytes que ainda podem ser lidos ou escritos na stream.
	 */

	int space();

	/**
	 * Essa fun��o tem como objetivo indireto indicar que h� um problema na comunica��o.
	 * Quando uma comunica��o est� vazia significa que est� n�o possui dados para ler ou escrever.
	 * Por tanto � bem prov�vel que houve uma falha na comunica��o, a n�o ser que esta esteja em branco.
	 * @return true se a comunica��o estiver vazia ou false caso contr�rio.
	 */

	boolean isEmpty();

	/**
	 * Uma comunica��o fechada n�o ir� permitir fazer a leitura ou escrita de dados na comunica��o.
	 * No caso da leitura, qualquer dado seguinte lido ser� sempre respectivo a null ou em c�digo ASCII: 0.
	 * @return true se a comunica��o estiver fechada ou false caso contr�rio.
	 */

	boolean isClosed();

	/**
	 * Deve ser chamado apenas se houve alguma falha durante a comunica��o ou ent�o quando ela acabou.
	 * Isso ir� garantir que a comunica��o seja fechada, assim sendo, n�o poder� mais ler ou escrever.
	 */

	void close();

	/**
	 * Comunica��es que estejam invertidas, sempre ir�o ler ou escrever os dados primitivos ao contr�rio.
	 * Esse <i>ao contr�rio</i> � referente a ordem em que seus bytes ser�o lidos ou ent�o escritos.
	 * @return true se a comunica��o estiver sendo invertida ou false caso contr�rio.
	 */

	boolean isInverted();

	/**
	 * Permite definir a comunica��o como invertida, assim os bytes dos dados primitivos ser�o invertidos.
	 * Alguns arquivos precisam de uma leitura de dados padr�es por�m em alguns casos eles s�o invertidos.
	 * @param enable true para habilitar a comunica��o de dados invertida ou false para desabilitar.
	 */

	void setInvert(boolean enable);

	/**
	 * Permite pular uma determinada quantidade de bytes do fluxo pr�-estabelecido.
	 * @param bytes quantidade de bytes que ser�o pulados no fluxo.
	 */

	void skipe(int bytes);

	/**
	 * Restabelece a stream como se nenhum dado tivesse lido, movendo ponteiros para o inicio.
	 */

	void reset();
}
