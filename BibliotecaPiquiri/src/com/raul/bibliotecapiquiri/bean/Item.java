package com.raul.bibliotecapiquiri.bean;

public class Item {
	private Integer id, ano, quantidade;
	private String titulo, descricao, autor;
	private Grupo grupo;
	private byte[] imagem;
	
	public Item() {
		
	}
	
	//Construtor com o mínimo de parâmetros possíveis
	public Item(Integer quantidade, String titulo, Grupo grupo, byte[] imagem) {		
		this.quantidade = quantidade;
		this.titulo = titulo;
		this.grupo = grupo;
		this.imagem = imagem;
	}
	
	//Construtor completo sem a imagem
	public Item(Integer ano, Integer quantidade, String titulo, String descricao, String autor, Grupo grupo) {
		this.ano = ano;
		this.quantidade = quantidade;
		this.titulo = titulo;
		this.descricao = descricao;
		this.autor = autor;
		this.grupo = grupo;
	}
	
	//Construtor completo
	public Item(Integer ano, Integer quantidade, String titulo, String descricao, String autor, Grupo grupo,
			byte[] imagem) {
		this.ano = ano;
		this.quantidade = quantidade;
		this.titulo = titulo;
		this.descricao = descricao;
		this.autor = autor;
		this.grupo = grupo;
		this.imagem = imagem;
	}

	public enum Grupo {
		LIVRO, REVISTA, JORNAL, ARTIGO, OUTROS;
	}
	
	public boolean anoVazio() {
		if(ano == null || ano.equals(0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean quantidadeVazia() {
		if(quantidade == null || quantidade.equals(0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean tituloVazio() {
		if(titulo == null || titulo.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean descricaoVazia() {
		if(descricao == null || descricao.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean autorVazio() {
		if(autor == null || autor.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Início de Setters e Getters
	public Integer getId() {
		return id;
	}

	public Item setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getAno() {
		return ano;
	}

	public Item setAno(Integer ano) {
		this.ano = ano;
		return this;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Item setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}

	public String getTitulo() {
		return titulo;
	}

	public Item setTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public String getDescricao() {
		return descricao;
	}

	public Item setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public String getAutor() {
		return autor;
	}

	public Item setAutor(String autor) {
		this.autor = autor;
		return this;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public Item setGrupo(Grupo grupo) {
		this.grupo = grupo;
		return this;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
