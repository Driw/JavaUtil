package org.diverproject.log;

/**
 * <h1>Listener para Registro</h1>
 *
 * <p>Esse listener � usado por LogSystem para que os registros detectados sejam repassados.
 * Assim qualquer projeto poder� usar os registros detectadas da forma que preferir.
 * Podendo adicionar ao console de desenvolvimento ou um console criado no projeto.
 * Sendo ainda de op��o apenas salvar as informa��es registradas em um arquivo.</p>
 *
 * @see Log
 *
 * @author Andrew
 */

public interface LogListener
{
	/**
	 * Se registrado no sistema de log ser� chamado sempre que um novo log for detectado.
	 * Log se refere a uma informa��o ou ocorrido do qual o sistema deseja registrar.
	 * @param log refer�ncia do objeto contendo todos os detalhes a serem registrados.
	 */

	void onMessage(Log log);
}
