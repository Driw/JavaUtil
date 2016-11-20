package org.diverproject.util.service;

/**
 * <h1>Servi�o</h1>
 *
 * <p>Interface utilizada para a cria��o de diferentes tipos de servi�os no engine.
 * Esses servi�os ser�o armazenados no gerenciador de servi�os e podem ser usados.
 * Todo servi�o dever� possuir algumas funcionalidades b�sicas para tal, segue:</p>
 *
 * <p>Primeiramente, os servi�os tem que ser inicializados para que este entre em vigor.
 * Assim sendo, todo servi�o tem que possuir um c�digo de estado de como est�.
 * Todos os servi�os devem possuir um nome que ir� permitir a identifica��o deste.</p>
 *
 * <p>Servi�os ser�o necess�rios sempre que houver uma necessidade de cria��o de um
 * sistema que seja muito grande, como por exemplo o gerenciamento dos arquivos usados
 * pelo cliente, recursos gr�ficos e sonoros como tamb�m a entrada de mouse e teclado.</p>
 *
 * @author Andrew
 */

public interface Service
{
	/**
	 * C�digo para determinar que o servi�o se encontra em estado n�o definido.
	 */
	public static final int SERVICE_UNDEFINID = 0;

	/**
	 * C�digo para determinar que o servi�o se encontra j� iniciado mas n�o rodando.
	 */
	public static final int SERVICE_STARTED = 1;

	/**
	 * C�digo para determinar que o servi�o se encontrava iniciado e est� rodando.
	 */
	public static final int SERVICE_RUNNING = 2;

	/**
	 * C�digo para determinar que o servi�o se encontrava iniciado e agora est� parado.
	 */
	public static final int SERVICE_STOPED = 3;

	/**
	 * C�digo para determinar que o servi�o se encontra em fase de finaliza��o.
	 */
	public static final int SERVICE_TERMINATED = 4;


	/**
	 * Chamado a cada frame executado no cliente para garantir que o sistema atualize.
	 * @param delay quantos milissegundos se passaram desde a �ltima atualiza��o.
	 */

	void update(long delay);

	/**
	 * Procedimento chamado sempre que o servi�o entrar em vigor no sistema, iniciar.
	 * Deve garantir que todos os recursos utilizados no sistema estejam dispon�veis.
	 * Caso o servi�o esteja interrompido, apenas deve retornar a rodar normalmente.
	 * @throws ServiceException falha na inicializa��o do servi�o, provavelmente h� um
	 * recurso do qual n�o pode ser inicializado que estar� especificado na mensagem.
	 */

	void start() throws ServiceException;

	/**
	 * Terminar o servi�o significa que ele ser� interrompido e em seguida apagado.
	 * Uma vez que ele tenha sido apagado todas as informa��es no mesmo ir�o junto.
	 * Al�m disso um servi�o terminado n�o pode retornar a operar, por�m n�o diz
	 * que um novo servi�o possa ser criado, indo de servi�o para servi�o.
	 * @throws ServiceException falha na finaliza��o do servi�o, provavelmente h� um
	 * recurso do qual n�o pode ser finalizado que estar� especificado na mensagem.
	 */

	void terminate() throws ServiceException;

	/**
	 * Interromper o servi�o significa que ele ir� parar de funcionar temporariamente.
	 * Assim sendo o servi�o poder� retornar a opera��o quando for iniciado novamente.
	 * Inici�-lo novamente e n�o significa restabelec�-lo completamente, apenas que ele
	 * dever� entrar em vigor novamente assim que solicitado.
	 * @throws ServiceException falha na interrup��o do servi�o, provavelmente h� um
	 * recurso do qual n�o pode ser interrompido que estar� especificado na mensagem.
	 */

	void interrupted() throws ServiceException;

	/**
	 * Estado ir� determinar o que o servi�o est� atualmente fazendo para se mantar.
	 * Cada procedimento chamado no servi�o ou funcionalidades poder� acarretar na
	 * mudan�a do estado do sistema de acordo com as especifica��es internas do mesmo.
	 * @return aquisi��o do c�digo do estado atual do servi�o no sistema.
	 */

	int getState();

	/**
	 * Identificador � um nome que � dado a todos os servi�os com prefer�ncia a padr�es.
	 * Sendo utilizado para que o sistema possa identificar exatamente qual servi�o �.
	 * @return aquisi��o do nome de identifica��o do servi�o em quest�o.
	 */

	String getIdentificator();
}
