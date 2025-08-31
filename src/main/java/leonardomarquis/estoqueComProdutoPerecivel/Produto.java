package leonardomarquis.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public class Produto {
    private ArrayList<Fornecedor> fornecedores;
    private ArrayList<String> movimentacoes;
    private double precoCompra;
    private double precoVenda;
    private int quantidade;
    private int cod;
    private String desc;
    private int min;
    private double lucro;

    public Produto(int cod, String desc, int min, double lucro) {
        this.fornecedores = new ArrayList<>();
        this.movimentacoes = new ArrayList<>();
        this.precoCompra = 0;
        this.precoVenda = 0;
        this.quantidade = 0;
        this.cod = cod;
        this.desc = desc;
        this.min = min;
        this.lucro = lucro;
    }

    public void adicionarMovimentacoes(String tipo, int quant, double preco) {
        Date data = new Date();
        long time = data.getTime();
        String movimentacao = time + " " + tipo + "Quantidade: " + quant + " Preco: " + preco + ".";
        movimentacoes.add(movimentacao);
    }

    public void compra(int quant, double val, Date validade) {
        if (quant <= 0 || val < 0) {
            System.out.println("Operação inválida");
            return;
        }
        this.precoCompra = (this.quantidade * this.precoCompra + quant * val) / (this.quantidade + quant);
        this.quantidade += quant;
        this.precoVenda = this.precoCompra * (1 + this.lucro);
        adicionarMovimentacoes("Compra", quant, val);
    }

    public double vender(int quant) {
        if (quant <= 0 || quant > this.quantidade) {
            return -1;
        }
        this.quantidade -= quant;
        adicionarMovimentacoes("Venda", quant, precoVenda);
        return quant * this.precoVenda;
    }


    public boolean estoqueAbaixoDoMinimo() {
        return this.quantidade < this.min;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void adicionarFornecedor(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
    }

    public ArrayList<String> getMovimentacoes() {
        return movimentacoes;
    }

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public double getLucro() {
        return lucro;
    }

    public int getQuantidade() {
        return this.quantidade;
    }
    public int setQuantidade( int novaQuantidade ) {
        if (novaQuantidade >= 0) {
            this.quantidade = novaQuantidade;
        }
        return quantidade;
    }
    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public int getMin() {
        return min;
    }
}



