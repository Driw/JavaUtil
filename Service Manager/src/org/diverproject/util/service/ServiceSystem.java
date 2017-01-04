package org.diverproject.util.service;

import static org.diverproject.log.LogSystem.logError;
import static org.diverproject.log.LogSystem.logException;
import static org.diverproject.log.LogSystem.logNotice;
import static org.diverproject.log.LogSystem.logWarning;

import org.diverproject.util.collection.Map;
import org.diverproject.util.collection.abstraction.StringMap;

/***
 * <h1>Sistema de Servi�os</h1>
 *
 * <p>Tem como finalidade fazer o gerenciamento dos servi�os existentes no engine.
 * Al�m disso � ele quem ir� garantir que todos os servi�os sejam atualizados.
 * Nele � poss�vel iniciar, interromper ou terminar um servi�o pelo identificador.</p>
 *
 * @author Andrew
 */

public final class ServiceSystem extends SystemBase
{
	/**
	 * Refer�ncia do objeto ServiceSystem para adaptar ao padr�o de projetos Singleton.
	 */
	private static final ServiceSystem INSTANCE = new ServiceSystem();


	/**
	 * Lista que ir� armazenar os servi�os.
	 */
	private Map<String, Service> services;

	/**
	 * Construtor privado para respeitar o padr�o de projetos Singleton.
	 * Deve fazer a inicializa��o da lista para armazenamento dos servi�os.
	 */

	public ServiceSystem()
	{
		services = new StringMap<Service>();

		logNotice("%s: sistema para servi�os instanciado.\n", getSystemName());
	}

	@Override
	public String getSystemName()
	{
		return "SYS.Services";
	}

	@Override
	public void update(long delay)
	{
		for (Service service : services)
			if (service.getState() == Service.SERVICE_RUNNING)
				service.update(delay);
	}

	@Override
	public void shutdown()
	{
		for (Service service : services)
		{
			try {
				service.terminate();
			} catch (ServiceException e) {
				logException(e);
			}
		}
	}

	/**
	 * Deve adicionar um novo servi�o a lista de servi�os, uma vez que tenha sido
	 * adicionado ser� poss�vel controlar o mesmo atrav�s do sistema de servi�os.
	 * @param service refer�ncia do servi�o a ser adicionado.
	 */

	public void add(Service service)
	{
		if (service == null)
			logWarning("Service System: servi�o nulo.\n");

		else if (service.getState() != Service.SERVICE_UNDEFINID)
			logWarning("Service System: servi�o inv�lido (%s).\n", service.getIdentificator());

		else if (services.add(service.getIdentificator(), service))
			logNotice("Service System: registro feito com �xito (%s).\n", service.getIdentificator());

		else
			logError("Service System: falha no regitros(%s).\n", service.getIdentificator());
	}

	/**
	 * Deve excluir um servi�o existente da lista de servi�os, uma vez que tenha sido
	 * exclu�do n�o ser� poss�vel fazer a utiliza��o do mesmo pelo sistema de servi�o.
	 * @param service refer�ncia do servi�o a ser exclu�do.
	 */

	public void delete(Service service)
	{
		if (service == null);

		switch (service.getState())
		{
			case Service.SERVICE_STARTED:
			case Service.SERVICE_RUNNING:
				logNotice("Service System: servi�o rodando (%s).\n", service.getIdentificator());
				return;

			case Service.SERVICE_STOPED:
				logNotice("Service System: servi�o parado (%s).\n", service.getIdentificator());
				return;

			case Service.SERVICE_TERMINATED:
				logNotice("Service System: servi�o em t�rmino (%s).\n", service.getIdentificator());
				return;
		}

		if (services.removeKey(service.getIdentificator()))
			logNotice("Service System: servi�o exclu�do (%s).\n", service.getIdentificator());
		else
			logWarning("Service System: sevi�o n�o encontrado (%s).\n", service.getIdentificator());
	}

	/**
	 * Permite iniciar um determinado servi�o especificado pelo identificador.
	 * Se houver falha na interrup��o � poss�vel obter o erro por getException().
	 * @param identification identificador que foi definido ao servi�o.
	 * @return true se conseguiu interromper ou false caso contr�rio.
	 */

	public boolean start(String identification)
	{
		Service service = services.get(identification);

		if (service == null)
			return false;

		try {
			service.start();
			return true;
		} catch (ServiceException e) {
			putException(e);
			return false;
		}
	}

	/**
	 * Permite terminar um determinado servi�o especificado pelo identificador.
	 * Se houver falha na interrup��o � poss�vel obter o erro por getException().
	 * @param identification identificador que foi definido ao servi�o.
	 * @return true se conseguiu interromper ou false caso contr�rio.
	 */

	public boolean terminate(String identification)
	{
		Service service = services.get(identification);

		if (service == null)
			return false;

		try {
			service.terminate();
			return true;
		} catch (ServiceException e) {
			putException(e);
			return false;
		}
	}

	/**
	 * Permite interromper um determinado servi�o especificado pelo identificador.
	 * Se houver falha na interrup��o � poss�vel obter o erro por getException().
	 * @param identification identificador que foi definido ao servi�o.
	 * @return true se conseguiu interromper ou false caso contr�rio.
	 */

	public boolean interrupted(String identification)
	{
		Service service = services.get(identification);

		if (service == null)
			return false;

		try {
			service.interrupted();
			return true;
		} catch (ServiceException e) {
			putException(e);
			return false;
		}
	}

	/**
	 * ServiceSystem utiliza o padr�o de projetos Singleton tendo apenas uma inst�ncia.
	 * Utilizado para que n�o seja poss�vel criar um outro sistema para entrada.
	 * @return aquisi��o do gerenciador de entradas do engine.
	 */

	public static ServiceSystem getInstance()
	{
		return INSTANCE;
	}
}
