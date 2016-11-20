package org.diverproject.util.collection.abstraction;

import java.util.Iterator;

import org.diverproject.util.FileUtil;
import org.diverproject.util.ObjectDescription;
import org.diverproject.util.collection.Folder;
import org.diverproject.util.collection.FolderElement;
import org.diverproject.util.collection.List;
import org.diverproject.util.collection.Map;

/**
 * <p><h1>Pasta Virtual</h1></p>
 *
 * <p>Essa pasta virtual vai funcionar da seguinte forma, � composta por um nome, arquivos e outras pastas virtuais.
 * Como uma pasta f�sica em disco, ela pode possuir tanto arquivos como pastas dentro dela, e o seu nome.
 * Utilizar isso, apesar de uma adi��o e remo��o mais demorada que outras estruturas a busca � altamente boa.</p>
 *
 * <p>Utiliza uma tabela espalhada para armazenamento dos arquivos de modo que permita uma f�cil identifica��o.
 * Quando maior o n�mero de arquivos com o mesmo inicio no nome maior o tempo de procura usando essa estrutura.
 * No caso das pastas � utilizado uma lista din�mica, quanto a performance depende do n�mero de pastas.
 * Caso o nome da pasta seja de ordem alfab�tica menor ser� mais r�pido caso contr�rio mais demorado.</p>
 *
 * <p>Quanto algumas caracter�sticas exclusivas da estrutura, possui sub-m�todos com pr�-fixo <code>sub</code>.
 * S�o chamados pelos m�todos principais quando uma a��o � executada com �xito, afim de permitir um <i>listener</i>.
 * Como por exemplo, ao adicionar um arquivo a pasta uma a��o deve ser executada, ou quando ele � removido.</p>
 *
 * <p>Como toda pasta, esta tamb�m possui uma pasta ra�z, a pasta no qual ela est� alocada.
 * Se n�o for definido nenhuma pasta ra�z para ela, ser� considerada que ela � a pasta ra�z principal.
 * Ou seja, todo arquivo ou pasta estar� alocado dentro dela, em quanto ela, n�o est� alocado em ningu�m.
 * Internamente � usado apenas para garantir a limpeza de pastas vazias ou lista de pastas vazias.</p>
 *
 * @see Folder
 * @see FolderElement
 * @see AbstractCollection
 *
 * @author Andrew Mello
 *
 * @param <E> tipo de elemento do qual ser� armazenado.
 */

public class VirtualFolder<E> extends AbstractCollection<E> implements Folder<E>
{
	/**
	 * Nome da pasta virtual.
	 */
	private String name;

	/**
	 * Pasta ra�z, onde ela est� alocada.
	 */
	private VirtualFolder<E> parent;

	/**
	 * Mapeamento dos arquivos alocados.
	 */
	protected Map<String, E> files;

	/**
	 * Lista de pastas armazenadas.
	 */
	protected List<VirtualFolder<E>> folders;

	/**
	 * Constr�i uma nova pasta virtual, para essa pasta virtual ser� considerada como ra�z.
	 * Para esse caso, ela n�o estar� alocado a nenhuma outra pasta.
	 * @param name nome do qual ser� dado a pasta, para validar diret�rios.
	 */

	public VirtualFolder(String name)
	{
		this(name, null);
	}

	/**
	 * Constr�i uma nova pasta virtual, considera que ela est� sendo criada a partir de outra.
	 * @param name nome do qual ser� dado a pasta, para validar diret�rios.
	 * @param parent qual � a pasta do qual originou essa.
	 */

	public VirtualFolder(String name, VirtualFolder<E> parent)
	{
		this.name = name;
		this.parent = parent;
	}

	@Override
	public void clear()
	{
		if (folders != null)
		{
			for (VirtualFolder<E> folder : folders)
				folder.clear();

			folders = null;
		}

		if (files != null)
		{
			for (E file : files)
				subClear(file);

			files = null;
		}
	}

	@Override
	public int size()
	{
		int size = 0;

		if (files != null)
			size += files.size();

		if (folders != null)
			for (VirtualFolder<E> folder : folders)
				size += folder.size();

		return size;
	}

	@Override
	public int length()
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean contains(E file)
	{
		if (file instanceof FolderElement)
		{
			FolderElement element = (FolderElement) file;
			String filepath = element.getFilePath();
			Path path = new Path(filepath);

			return contains(path);
		}

		return false;
	}

	@Override
	public boolean contains(String path)
	{
		return contains(new Path(path));
	}

	/**
	 * Chamado internamente para verificar se essa pasta cont�m um determinado caminho.
	 * @param path caminho do arquivo do qual deseja verificar se existe na pasta.
	 * @return true se encontrar o caminho ou false caso contr�rio.
	 */

	private boolean contains(Path path)
	{
		if (!name.equals(path.enter()))
			return false;

		if (path.isFile())
		{
			if (files == null)
				return false;

			return files.containsKey(path.name());
		}

		if (folders != null)
			for (VirtualFolder<E> folder : folders)
				if (folder.name.equals(path.name()))
					return folder.contains(path);

		return false;
	}

	@Override
	public boolean add(E element)
	{
		if (element instanceof FolderElement)
		{
			FolderElement file = (FolderElement) element;
			String filepath = file.getFilePath();
			Path path = new Path(filepath);

			return add(file, path);
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean add(FolderElement file, Path path)
	{
		if (!name.equals(path.enter()))
			return false;

		if (path.isDirectory())
		{
			if (folders == null)
			{
				VirtualFolder<E> folder = new VirtualFolder<E>(path.name(), this);

				folders = new DynamicList<VirtualFolder<E>>();
				folders.add(folder);

				return folder.add(file, path);
			}

			for (VirtualFolder<E> folder : folders)
				if (folder.name.equals(path.name()))
					return folder.add(file, path);

			VirtualFolder<E> folder = new VirtualFolder<E>(path.name(), this);
			folders.add(folder);

			return folder.add(file, path);
		}

		if (files == null)
			files = new StringMap<E>();

		if (files.containsKey(path.name()))
			return false;

		files.add(path.name(), (E) file);
		subAdd((E) file);

		return true;
	}

	@Override
	public boolean remove(String path)
	{
		return remove(new Path(path));
	}

	/**
	 * Chamado internamente para remover um arquivo a partir de um caminho especificado.
	 * @param path caminho do arquivo do qual deseja remover dessa pasta.
	 * @return true se encontrar e remover ou false caso contr�rio.
	 */

	private boolean remove(Path path)
	{
		if (!name.equals(path.enter()))
			return false;

		if (path.isFile())
		{
			if (files == null)
				return false;

			E file = files.get(path.name());
			files.removeKey(path.name());

			subRemove(file);
			subRemoveClear(this);

			return true;
		}

		if (folders != null)
			for (VirtualFolder<E> folder : folders)
				if (folder.name.equals(path.name()))
					return folder.remove(path);

		return false;
	}

	@Override
	public E get(String path)
	{
		return get(new Path(path));
	}

	/**
	 * Chamado internamente para obter um arquivo a partir de um caminho especificado.
	 * @param path caminho do arquivo do qual deseja obter dessa pasta.
	 * @return refer�ncia do arquivo se encontrar ou null caso contr�rio.
	 */

	private E get(Path path)
	{
		if (!name.equals(path.enter()))
			return null;

		if (path.isFile())
		{
			if (files == null)
				return null;

			return files.get(path.name());
		}

		if (folders != null)
			for (VirtualFolder<E> folder : folders)
				if (folder.name.equals(path.name()))
				{
					E e = folder.get(path.name());

					if (e != null)
						return e;
				}

		return null;
	}

	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * Quando uma pasta for limpa, ao remover cada arquivo ser� chamado esse m�todo.
	 * @param file refer�ncia do arquivo do qual est� sendo removido.
	 */

	protected void subClear(E file)
	{
		
	}

	/**
	 * Quando um novo arquivo for adicionado a essa pasta ser� chamado esse m�todo.
	 * @param file refer�ncia do arquivo do qual est� sendo adicionado.
	 */

	protected void subAdd(E file)
	{
		
	}

	/**
	 * Quando um arquivo � removido deve chamar esse m�todo para limpar a pasta do mesmo.
	 * A limpeza se refere a excluir a lista de arquivos se n�o hover mais nenhum.
	 * Em seguida passa para a pasta ra�z verificar se esta pasta possui arquivos.
	 * Caso n�o possua ela ser� removida e assim por diante at� chegar a ra�z m�xima.
	 * @param folder refer�ncia da pasta onde se encontra o arquivo removido.
	 */

	protected void subRemoveClear(VirtualFolder<E> folder)
	{
		if (folder == null)
			return;

		if (folder.files != null && folder.files.size() == 0)
			folder.files = null;

		if (folder.folders != null)
		{
			for (int i = 0; i < folder.folders.size(); i++)
				if (folder.folders.get(i).size() == 0)
					folder.folders.remove(i);

			if (folder.folders.size() == 0)
				folder.folders = null;
		}

		subRemoveClear(folder.parent);
	}

	/**
	 * Quando um arquivo for removido dessa pasta ser� chamado esse m�todo.
	 * @param file refer�ncia do arquivo do qual foi removido da pasta.
	 */

	protected void subRemove(E file)
	{
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator()
	{
		E array[] = (E[]) new Object[size()];
		subIterator(array, this, 0);

		return new Iterator<E>()
		{
			private int i = 0;

			@Override
			public boolean hasNext()
			{
				return i < array.length;
			}

			@Override
			public E next()
			{
				return array[i++];
			}
		};
	}

	/**
	 * Chamado sempre que uma nova itera��o for criada, ir� preencher a lista de arquivos.
	 * @param array vetor do qual ir� armazenar os arquivos para serem iterados.
	 * @param root pasta virtual do qual ter� os arquivos listados.
	 * @param offset �ndice atual para posicionar os novos arquivos.
	 */

	private int subIterator(E[] array, VirtualFolder<E> root, int offset)
	{
		int alocated = 0;

		if (root.files != null)
			for (E file : root.files)
				if (file != null)
					array[offset + alocated++] = file;

		if (root.folders != null)
			for (VirtualFolder<E> folder : root.folders)
				if (folder != null)
				{
					int count = subIterator(array, folder, offset + alocated);
					alocated += count;
				}

		return alocated;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("name", name);
		description.append("size", size());
		description.append("files", files == null ? 0 : files.size());
		description.append("folders", folders == null ? 0 : folders.size());

		return description.toString();
	}

	protected class Path
	{
		private int offset;
		private String splitPath[];

		public Path(String path)
		{
			path = FileUtil.adaptPath(path);

			offset = 0;
			splitPath = path.split("/");
		}

		public String name()
		{
			return splitPath[offset];
		}

		public String enter()
		{
			if (offset < splitPath.length - 1)
				return splitPath[offset++];

			return null;
		}

		public boolean isFile()
		{
			return offset == splitPath.length - 1;
		}

		public boolean isDirectory()
		{
			return offset != splitPath.length - 1;
		}

		@Override
		public String toString()
		{
			StringBuilder str = new StringBuilder();

			for (int i = 0; i < splitPath.length; i++)
			{
				if (i == offset)
					str.append("[" +splitPath[i]+ "]");
				else
					str.append(splitPath[i]);

				if (i != splitPath.length - 1)
					str.append("/");
			}

			return str.toString();
		}
	}
}
