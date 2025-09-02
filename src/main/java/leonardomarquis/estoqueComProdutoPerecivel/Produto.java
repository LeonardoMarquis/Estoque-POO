package leonardomarquis.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public class Produto {
    private int cod;
    private String desc;
    private int min;
    private double lucro;

    private double precoCompra;
    private double precoVenda;
    private int quantidade;

    private ArrayList<String> movimentacoes;
    private ArrayList<Fornecedor> fornecedores;



    public Produto(int cod, String desc, int min, double lucro) {

        this.cod = cod;
        this.desc = desc;
        this.min = min;
        this.lucro = lucro; // e em porcentagem mas representa em deciamal

        this.precoCompra = 0;
        this.precoVenda = 0;
        this.quantidade = 0;

        this.movimentacoes = new ArrayList<>();
        this.fornecedores = new ArrayList<>();


    }



    public void compra(int quant, double val, Date validade) {  //colocado esse parametro
        if (quant <= 0 || val < 0) {
            System.out.println("Operação inválida");
            return;
        }
        this.precoCompra = (this.quantidade * this.precoCompra + quant * val) / (this.quantidade + quant);
        this.quantidade += quant;
        this.precoVenda = this.precoCompra * (1 + this.lucro);
        addMovimentacoes("Compra", quant, val);
    }

    public double vender(int quant) {
        if (quant <= 0 || quant > this.quantidade) {
            return -1;
        }
        this.quantidade -= quant;
        addMovimentacoes("Venda", quant, precoVenda);
        return quant * this.precoVenda;
    }

    public void addMovimentacoes(String tipo, int quant, double preco) {
        Date data = new Date();
        long time = data.getTime();
        String registro = time + " " + tipo + "qtd: " + quant + " valor: " + preco + ".";
        movimentacoes.add(registro);
    }

    public boolean estoqueAbaixoDoMinimo() {
        return this.quantidade < this.min;
    }




    public void addFornecedor(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public ArrayList<String> getMovimentacoes() {
        return movimentacoes;
    }



    public int getCodigo() {
        return cod;
    }

    public String getDescricao() {
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


    public int setQuantidade( int novaQuantidade ) {
        if (novaQuantidade >= 0) {
            this.quantidade = novaQuantidade;
        }
        return quantidade;
    }
    public int getQuantidade() {
        return this.quantidade;
    }



    public int getMin() {
        return min;
    }
}



