package Model;

public class Produto {
    private int id_produto;
    private String nome_produto;
    private String descricao;
    private double preco;
    private int estoque;
    private String jogo;
    private String raridade;
    private String tipo;
    
    public Produto() {}
    
    public Produto(int id_produto, String nome_produto, String descricao, double preco, 
                   int estoque, String jogo, String raridade, String tipo) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.jogo = jogo;
        this.raridade = raridade;
        this.tipo = tipo;
    }
    
    public int getId_produto() {
        return id_produto;
    }
    
    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }
    
    public String getNome_produto() {
        return nome_produto;
    }
    
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public int getEstoque() {
        return estoque;
    }
    
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    public String getJogo() {
        return jogo;
    }
    
    public void setJogo(String jogo) {
        this.jogo = jogo;
    }
    
    public String getRaridade() {
        return raridade;
    }
    
    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

