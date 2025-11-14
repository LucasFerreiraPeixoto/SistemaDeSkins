package Model;

import java.util.Date;

public class Venda {
    private int id_venda;
    private int id_usuario;
    private int id_produto;
    private int quantidade;
    private Date data_venda;
    private double valor_total;
    private String nome_usuario;
    private String nome_produto;
    
    public Venda() {}
    
    public Venda(int id_venda, int id_usuario, int id_produto, int quantidade, 
                 Date data_venda, double valor_total) {
        this.id_venda = id_venda;
        this.id_usuario = id_usuario;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.data_venda = data_venda;
        this.valor_total = valor_total;
    }
    
    public Venda(int id_venda, String nome_usuario, String nome_produto, int quantidade, 
                 Date data_venda, double valor_total) {
        this.id_venda = id_venda;
        this.nome_usuario = nome_usuario;
        this.nome_produto = nome_produto;
        this.quantidade = quantidade;
        this.data_venda = data_venda;
        this.valor_total = valor_total;
    }
    
    public int getId_venda() {
        return id_venda;
    }
    
    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }
    
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public int getId_produto() {
        return id_produto;
    }
    
    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public Date getData_venda() {
        return data_venda;
    }
    
    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }
    
    public double getValor_total() {
        return valor_total;
    }
    
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }
    
    public String getNome_usuario() {
        return nome_usuario;
    }
    
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }
    
    public String getNome_produto() {
        return nome_produto;
    }
    
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }
}

