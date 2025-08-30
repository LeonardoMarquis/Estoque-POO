package leonardomarquis.estoque;

import java.util.Date;
import java.util.ArrayList;

public class Produto{
    private int cod;
    private String desc;
    private double min;
    private double lucro;

    private double precoCompra;
    private double precoVenda;
    private int quantidade;

    private ArrayList<String> movimentacoes;
    private ArrayList<Fornecedor> fornecedores;



    public Produto(int cod, String desc, int min, double lucro){

        this.cod = cod;
        this.desc = desc;
        this.min = min;     // é o estoque minimo, na hora de incluir o produto no estoque, definindo o valor do estoque minimo do produto para o estoque, e so um conceito para dizer, que esta com o estoque no minimo
        this.lucro = lucro; // é em porcentagem mas representa em decimal

        this.precoCompra = 0;
        this.precoVenda = 0;
        this.quantidade = 0;

        this.movimentacoes = new ArrayList<>();
        this.fornecedores = new ArrayList<>();
    }

    public void compra(int quant, double val){
        if(quant <= 0 || val < 0){
            System.out.println("Quantidade ou Valor inválido!");
            return;
        }

        this.precoCompra = (this.quantidade * precoCompra + quant * val)/(quantidade + quant);
        this.quantidade += quant;
        this.precoVenda = this.precoCompra + this.precoCompra * lucro;      // atualizar logo o preco de venda, ja que sempre que atualiza do de compra tem que atualizar o de venda tambem!
        movimentacao("compra", quant, val);
    }

    public double vender(int quant){
        if(quant <= 0 || quant > this.quantidade){
            System.out.println("Quantidade ou Valor invalido!");
            return -1;
        }

        this.quantidade -= quant;;
        movimentacao("venda", quant, precoVenda);
        return quant * precoVenda;      // OBS NESSE THIS ******** | o metodo retorna o valor total da venda
    }

    public void movimentacao(String tipo, int quant, double val){
        Date data = new Date();
        long time = data.getTime();
        String registro = time + "|" + tipo + "|R$ " + quant + "|Unid:" + val;
        movimentacoes.add(registro);
    }




    public boolean estoqueAbaixoDoMinimo(){
        if (this.quantidade < min){
            return true;
        }
        return false;
    }

    public void addFornecedores(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
    }
    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public ArrayList<String> getMovimentacoes() {
        return movimentacoes;
    }


    public int getCod(){
        return cod;
    }
    public String getDesc(){
        return desc;
    }
    public double getMin(){
        return min;
    }
    public double getLucro(){
        return lucro;
    }
    public double getPrecoCompra(){
        return precoCompra;
    }
    public double getPrecoVenda(){
        return precoVenda;
    }
    public int getQuantidade(){
        return quantidade;
    }
}
