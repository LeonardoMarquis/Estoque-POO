package leonardomarquis.estoque;

import java.util.ArrayList;

public class Estoque{
    private ArrayList<Produto> produtos;

    public Estoque(){
        this.produtos = new ArrayList<>();
    }


    public void incluir(Produto p){
        for (Produto produto : produtos){       // é como o "for i in lista" do python!, so que dizendo o tipo da variavel que estara dentro do vetor a percorer
            if (produto.getCod() == p.getCod()){
                System.out.print("Ja existe um produto com este codigo!");
                return;
            }
        }
        produtos.add(p);
    }


    public void comprar(int cod, int quant, double preco){  // no caso esse é o comprar para o estoque
        for(Produto produto : produtos){
            if(produto.getCod() == cod{  // o restante das verificacoes estao no metodo compra do produto
                produto.compra(quant, preco);
                return;
            }
        }
    }

    public double vender(int cod, int quant){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                double valorTotalVenda = produto.vender(quant);
                if (valorTotalVenda >= 0){
                    return valorTotalVenda;
                }
            }
        }
        return -1;
    }
    public int quantidade(int cod){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                return produto.getQuantidade();
            }
        }
        return -1;
    }
    public String movimentacao(int cod, Date inicio, Date fim){

    }
    public ArrayList<Fornecedor> fornecedores(int cod){

    }
    public ArrayList<Produto> estoqueAbaixoDoMinimo(){

    }
    public void adicionarFornecedor(int cod, Fornecedor f){

    }
    public double precoDeVenda(int cod){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                return produto.getPrecoVenda();
            }
        }
        return -1;
    }
    public double precoDeCompra(int cod){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                return produto.getPrecoCompra();
            }
        }
        return -1;
    }

}

