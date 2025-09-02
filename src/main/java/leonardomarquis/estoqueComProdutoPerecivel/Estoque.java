package leonardomarquis.estoqueComProdutoPerecivel;

import java.util.Date;
import java.util.ArrayList;

public class Estoque implements InterfaceEstoque{
    private ArrayList<Produto> produtos;

    public Estoque(){
        this.produtos = new ArrayList<>();
    }


    public boolean incluir(Produto produt) {
        if (produt == null){   // verificar se algum parametro e null e bom ser a primeira verificacao
            return false;
        }

        if (produt.getCodigo() <= 0 || produt.getDescricao() == null || produt.getDescricao().isEmpty() || produt.getMin() <= 0 || produt.getLucro() < 0) {
            return false;
        }

        for (Produto produto : produtos) {
            if (produto.getCodigo() == produt.getCodigo()) {
                return false;
            }
        }
        produtos.add(produt);
        return true;
    }

    public boolean comprar(int cod, int quant, double preco, Date val) { // val e a data de validade do produto
        if(quant <= 0 || preco <= 0){   // VERIFICACAO extra para evitar verificar so no produto(elemento ddo array de produtos).compra
            return false;
        }

        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {

                if (produto instanceof ProdutoPerecivel ) {   // para ver produto como objeto da classe Produto perecivel, se for normal ai para o else if
                    Date date_today = new Date();
                    if (val == null || val.getTime() < date_today.getTime()){
                        System.out.println("Produto perecivel deve ter validade e que seja de hoje em diante!");
                        return false;
                    }
                    produto.compra(quant, preco, val);
                    return true;
                }
                else if (val == null){  // se nao for produto perecivel e receber val( data de validade) = null
                    produto.compra(quant, preco, null);
                    return true;
                }
                else {
                    System.out.println("Para Produto nao perecivel deve colocar validade = null!");
                    return false;
                }
                // seguindo o requisitado: no caso de ser um produto normal, deve dar para comprar certinho so se der a data null para ele

            }
        }
        return false;
    }

    public double vender(int cod, int quant) {
        if (quant <= 0) {
            return -1;
        }
        for (Produto produto : produtos) {  // a verificacao de lote so ocorre dentro do vender do produto perecivel
            if (produto.getCodigo() == cod ) {
                if (produto instanceof ProdutoPerecivel){
                    return ((ProdutoPerecivel) produto).vender(quant);
                }
                else { // Adicionar após o if para ProdutoPerecivel
                    if (produto.getQuantidade() >= quant) {
                        double valor = quant * produto.getPrecoVenda();
                        produto.setQuantidade(produto.getQuantidade() - quant);
                        return valor;
                    }
                    return -1;
                }
            }
        }
        return -1;
    }

    public Produto pesquisar(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto;
            }
        }
        return null;
    }

    public ArrayList<Produto> estoqueAbaixoDoMinimo() {
        ArrayList<Produto> abaixoMinimo = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.estoqueAbaixoDoMinimo()) {
                abaixoMinimo.add(produto);
            }
        }
        return abaixoMinimo;
    }

    public ArrayList<Produto> estoqueVencido() {
        ArrayList<Produto> vencidos = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto instanceof ProdutoPerecivel && ((ProdutoPerecivel) produto).quantidadeVencidos() > 0) {
                vencidos.add(produto);
            }
        }
        return vencidos;
    }

    public int quantidadeVencidos(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod && produto instanceof ProdutoPerecivel) {
                return ((ProdutoPerecivel) produto).quantidadeVencidos();
            }
        }
        return 0;
    }

    public void adicionarFornecedor(int cod, Fornecedor f) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                produto.addFornecedor(f);
                return;
            }
        }
    }

    public double precoDeVenda(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto.getPrecoVenda();
            }
        }
        return -1;
    }

    public double precoDeCompra(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto.getPrecoCompra();
            }
        }
        return -1;
    }



    public int quantidade(int cod) {
        for (Produto produto : produtos){
            if (produto.getCodigo() == cod){
                return produto.getQuantidade();
            }
        }
        return -1;
    }




}

