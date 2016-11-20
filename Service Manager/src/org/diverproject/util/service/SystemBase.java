package org.diverproject.util.service;

import static org.diverproject.log.LogSystem.logError;
import static org.diverproject.log.LogSystem.logWarning;

import org.diverproject.util.BitWise;
import org.diverproject.util.ObjectDescription;
import org.diverproject.util.UtilException;
import org.diverproject.util.collection.Queue;
import org.diverproject.util.collection.abstraction.DynamicQueue;

/**
 * <h1>Base para Sistema</h1>
 *
 * <p>Classe que permite criar um novo e pequeno sistema dentro do projeto.
 * Deve possuir algumas funcionalidades b�sicas que todo sistema possui.
 * Dentre elas, algumas j� implementadas e outras que dever�o ser.</p>
 *
 * <p>Todo sistema deve ser atualizado, por tanto ser� de obriga��o deste,
 * implementar um procedimento que dever� fazer a atualiza��o do mesmo.
 * O mesmo vale para a funcionalidade que permite desligar o sistema.</p>
 *
 * <p>Em rela��o as funcionalidades j� implementadas de uma base para sistema,
 * se refere a utiliza��o de exception, dever� ser registrado internamente.
 * As vezes o sistema n�o deve relatar na hora, sendo necess�rio tal.</p>
 *
 * @see UtilException
 *
 * @author Andrew
 */

public abstract class SystemBase
{
	/**
	 * Propriedade que ir� dizer ao sistema para usar <b>Util Log</b>.
	 * Ser� usado apenas em algumas ocasi�es e da pr�pria base de sitema.
	 */
	public static final int PROPERTIE_LOG_EXCEPTIONS = 0x01;

	/**
	 * Propriedade que ir� dizer ao sistema para mostrar exce��es.
	 * Ser� mostrado a mensagem da exce��o sempre que uma for enfileirada.
	 * Essa propriedade ser� considerada mesmo com <code>PROPERTIE_EXCEPTION_SIGNAL</code>.
	 */
	public static final int PROPERTIE_EXCEPTION_SHOW = 0x02;

	/**
	 * Propriedade que ir� dizer ao sistema para sinalizar exce��es.
	 * Ser� mostrado apenas o nome da exce��o sempre que uma for enfileirada.
	 * Essa propriedade n�o ser� considerada com <code>PROPERTIE_EXCEPTION_SHOW</code>.
	 */
	public static final int PROPERTIE_EXCEPTION_SIGNAL = 0x04;

	/**
	 * Propriedade que ir� dizer ao sistema para utilizar o <b>Util Log</b>.
	 * Permite que a extens�o de uma classe como base para sistema possa usar
	 * essa propriedade para determinar se haver� o uso de logs nas opera��es.
	 */
	public static final int PROPERTIE_USE_LOG = 0x08;

	/**
	 * Vetor que dever� armazenar o nome das propriedades dispon�veis.
	 */
	public static final String PROPERTIE_NAMES[] = new String[]
	{
		"LOG_EXCEPTIONS",
		"EXCEPTION_SHOW",
		"EXCEPTION_SIGNAL",
		"USE_LOG",
	};

	/**
	 * Exce��o que foi gerada durante a atualiza��o do sistema.
	 */
	private Queue<UtilException> exceptions;

	/**
	 * Armazenamento e controle das propriedades da base do sistema.
	 */
	private BitWise properties;

	/**
	 * Constr�i uma nova base para a cria��o de sistemas no software.
	 * Para esse construtor deve ser inicializado a fila de exce��es.
	 */

	public SystemBase()
	{
		properties = new BitWise(PROPERTIE_NAMES);
		exceptions = new DynamicQueue<UtilException>();
	}

	/**
	 * Para melhorar a diversifica��o dos sistemas existem as propriedades.
	 * Podem ser usadas para adicionar/remover fun��es do sistema ou avisos.
	 * @param propertie c�digo da propriedade que deseja verificar.
	 * @return true se estiver habilitada ou false caso contr�rio.
	 */

	public boolean isPropertie(int propertie)
	{
		return properties.is(propertie);
	}

	/**
	 * Permite definir determinadas propriedades para serem usadas no sistema.
	 * Essas propriedades podem ser verificadas atrav�s de <code>isPropertie()</code>.
	 * @param propertie c�digo da propriedade do qual dever� ser definida.
	 * @param enable true para habilitar ou false para desabilitar.
	 */

	public void setPropertie(int propertie, boolean enable)
	{
		if (enable)
			properties.set(propertie);
		else
			properties.unset(propertie);
	}

	/**
	 * Nome do sistema pode ser usado externamente e internamente.
	 * Internamente alguns procedimentos usam o nome do sistema para que
	 * seja poss�vel identificar em mensagens de qual sistema veio.
	 * @return aquisi��o do nome que foi dado a esse sistema.
	 */

	public abstract String getSystemName();

	/**
	 * Deve ser chamado periodicamente, preferencialmente em intervalos pr�-definidos.
	 * Seu objeto � garantir que os atributos internos estejam atualizados de acordo.
	 * Podendo ainda chamar procedimentos para garantir funcionalidades no sistema.
	 * @param delay quantos milissegundos se passou desde a �ltima atualiza��o.
	 */

	public abstract void update(long delay);

	/**
	 * Para esse procedimento, ser� designado as funcionalidades para desligamento.
	 * O desligamento do sistema dever� liberar as informa��es da mem�ria (objeto null).
	 * Se houver ainda threads para tal, interromper estas e tira-las de circula��o.
	 * @throws UtilException apenas se houver alguma falha ao desligar o sistema.
	 */

	public abstract void shutdown() throws UtilException;

	/**
	 * Exce��es podem ocorrer durante a execu��o de um software, internamente no sistema.
	 * De modo a garantir que algumas exce��es n�o obriguem try catch este deve ser usado.
	 * @return aquisi��o da exce��es mais antiga que est� armazenada na fila.
	 */

	public final UtilException getException()
	{
		return exceptions.poll();
	}

	/**
	 * Permite definir uma nova exce��o causada internamente no sistema.
	 * Novas exce��es s�o colocadas no final da fila de exce��es.
	 * @param e exce��o que foi gerada internamente.
	 */

	public final void putException(UtilException e)
	{
		if (exceptions.offer(e))
		{
			if (properties.is(PROPERTIE_EXCEPTION_SHOW))
				logWarning("%s: %s", getSystemName(), e.getMessage());

			else if (properties.is(PROPERTIE_EXCEPTION_SIGNAL))
				logError("%s: %s", getSystemName(), e.getMessage());
		}
	}

	/**
	 * Procedimento chamado por toString a fim de preencher adequadamente o mesmo.
	 * O posicionamento do conte�do � sempre feito apenas ao final da descri��o.
	 * @param description refer�ncia da descri��o desse objeto que ser� usado.
	 */

	protected void toString(ObjectDescription description)
	{
		
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		toString(description);

		description.append("exceptions", exceptions.size());
		description.append(properties.toStringProperties());

		return description.toString();
	}
}
