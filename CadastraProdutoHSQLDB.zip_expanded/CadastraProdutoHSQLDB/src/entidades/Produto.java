package entidades;

/**
 * Classe que representa a estrutura do objeto produto.
 * 
 * Aplica SRP pois trata exclusivamente da estrutura do objeto produto.
 */

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    
	public Produto(int codigo, String nome, String descricao) {
		super();
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    
}