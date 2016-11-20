package org.diverproject.util.service;

import static org.diverproject.log.LogSystem.logError;
import static org.diverproject.log.LogSystem.logInfo;

import java.io.File;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.UtilException;

/**
 * <h1>Sistema para Bibliotecas</h1>
 *
 * <p>Esse sistema tem como finalidade o carregamento de bibliotecas.
 * para que funcione deve ser definido (preferencialmente) um diret�rio.
 * Nesse diret�rio dever� conter uma pasta para cada tipo de sistema.</p>
 *
 * <p>No caso s�o os tr�s seguintes SO: Mac OS (mac), Windows (win),
 * Sunos (solaris), para cada um deles deve haver um diret�rio que ir�
 * conter os arquivos respectivos para as bibliotecas carreg�veis.</p>
 *
 * <p>O sistema ir� solicitar um pr�-fixo que ser� dado como nome da library.
 * Para ent�o tentar carregar o mesmo no sistema se assim for poss�vel.
 * Caso n�o haja defini��es para tal ir� verificar a exist�ncia do mesmo.</p>
 *
 * <p>Uma vez que n�o tenha gerado qualquer exce��o essa biblioteca estar�
 * dispon�vel para ser utilizada no sistema sem problemas de erro por
 * falta de informa��es carregadas da biblioteca em quest�o.</p>
 *
 * @see SystemBase
 *
 * @author Andrew
 *
 */

public class LibrarySystem extends SystemBase
{
	/**
	 * Refer�ncia do objeto LibrarySystem para adaptar ao padr�o de projetos Singleton.
	 */
	private static final LibrarySystem instance = new LibrarySystem();

	/**
	 * Diret�rio onde deve estar localizado as bibliotecas.
	 */
	private String directory;

	/**
	 * Constr�i um novo sistema para gerenciamento de bibliotecas.
	 * Dever� iniciar definido um diret�rio padr�o para o mesmo.
	 */

	private LibrarySystem()
	{
		directory = "3rdparty";
	}

	@Override
	public String getSystemName()
	{
		return "SYS.Library";
	}

	@Override
	public void update(long delay)
	{
		
	}

	@Override
	public void shutdown() throws UtilException
	{
		this.directory = null;
	}

	/**
	 * Diret�rio de bibliotecas � usado para que as bibliotecas possam ser carregadas.
	 * Por padr�o o diret�rio definido � <b>/3rdparty/</b>, podendo ser modificada.
	 * Nesse diret�rio dever� conter outras pastas, uma para cada tipo de SO.
	 * Dentro dessas pastas ir�o conter os arquivos das bibliotecas de acordo.
	 * @param directory caminho completo ou parcial do diret�rio para bibliotecas.
	 */

	public void setDirectory(String directory)
	{
		if (directory != null)
			this.directory = directory;
	}

	/**
	 * O procedimento de vincula��o dever� determinar um diret�rio para location.
	 * Esse diret�rio ser� de acordo com as especifica��es do sistema operacional.
	 * Assim o software poder� aceitar diversos tipos de SO se houver as bibliotecas.
	 * @param name nome da biblioteca como nome de arquivo para ser vinculado.
	 * @param location defini��o do nome para localiza��o da configura��o.
	 * @return true se conseguir vincular ou false caso contr�rio.
	 */

	public boolean bind(String name, String location)
	{
		if (directory == null)
		{
			ServiceException exception = new ServiceException("diret�rio n�o definido");
			putException(exception);

			return false;
		}

		String libname = System.mapLibraryName(name);
		String osname = System.getProperty("os.name").toLowerCase();
		String osarch = System.getProperty("os.arch");

		if (osname.startsWith("windows"))
		{
			osname = "win";
			osarch = (osarch.endsWith("86")) ? "32" : "64";
		}

		try {

			String path = String.format("%s/%s%s/%s", directory, osname, osarch, libname);
			File file = new File(path);

			if (!file.exists())
			{
				if (isPropertie(PROPERTIE_USE_LOG))
					logError("%s: biblioteca n�o encontrada (%s)\n", getSystemName(), libname);

				return false;
			}

			System.setProperty(location, file.getParentFile().getAbsolutePath());

			if (isPropertie(PROPERTIE_USE_LOG))
				logInfo("%s: biblioteca vinculada com �xito (%s)\n", getSystemName(), libname);

			return true;

		} catch (Exception e) {

			ServiceException exception = new ServiceException(e.getMessage());
			putException(exception);

		}

		return false;
	}

	/**
	 * Carrega uma determinada DLL no software atrav�s de <code>load()</code>.
	 * Deve ser necess�rio haver uma configura��o de diret�rio nas propriedades do
	 * sistema da Java Virtual Machine (JVM), que pode ser feito por <code>bind()</code>.
	 * @param libname nome do arquivo sem extens�o .dll de biblioteca, apenas o nome.
	 * @param location propriedade que guarda a localiza��o da biblioteca.
	 */

	public void load(String libname, String location)
	{
		String propertie = System.getProperty(location);
		String filename = String.format("%s\\%s.dll", propertie, libname);

		System.load(filename);
	}

	/**
	 * LibrarySistem utiliza Singleton que permite a cria��o de apenas um objeto do tipo.
	 * Usado para que n�o seja poss�vel criar mais do que dois clientes ao mesmo tempo.
	 * @return aquisi��o da refer�ncia do objeto cliente criado.
	 */

	public static LibrarySystem getInstance()
	{
		return instance;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("directory", directory);

		return description.toString();
	}
}
