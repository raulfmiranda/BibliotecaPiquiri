package br.com.blogspot.javandroidfortaleza.bibliotecapiquiri;

public class Item {
	private Integer id, ano, quantidade;
	private String titulo, descricao, autor;
	private Grupo grupo;
	private byte[] imagem;
	
	public Item() {
		
	}
	
	public Item(Integer quantidade, String titulo, Grupo grupo, byte[] imagem) {		
		this.quantidade = quantidade;
		this.titulo = titulo;
		this.grupo = grupo;
		this.imagem = imagem;
	}
	
	public Item(Integer id, Integer quantidade, String titulo, Grupo grupo, byte[] imagem) {		
		this.id = id;
		this.quantidade = quantidade;
		this.titulo = titulo;
		this.grupo = grupo;
		this.imagem = imagem;
	}
	
	public Item(Integer id, Integer ano, Integer quantidade, String titulo, String descricao, String autor, Grupo grupo,
			byte[] imagem) {
		this.id = id;
		this.ano = ano;
		this.quantidade = quantidade;
		this.titulo = titulo;
		this.descricao = descricao;
		this.autor = autor;
		this.grupo = grupo;
		this.imagem = imagem;
	}

	public enum Grupo {
		LIVRO(1), REVISTA(2), JORNAL(3), ARTIGO(4);
		
		private final int tipo;
		Grupo(int tipoGrupo) {
			tipo = tipoGrupo;
		}
		public int getTipo() {
			return tipo;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
}
