package leonardomarquis.estoqueComProdutoPerecivelExcecao;

import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoqueComExcecoes {
    private ArrayList<Produto> produtos;

    public Estoque(){
        this.produtos = new ArrayList<>();
    }

    // no logar de um if else, posso colocar um try catch
    // e no lugar de um return false pode ser um throw new excecao tal() e como no construtor dela tem como colocar mensagem especifica, posso colocar
    public void incluir(Produto produt) throws DadosInvalidos, ProdutoJaCadastrado {
        if (produt == null){   // verificar se algum parametro e null e bom ser a primeira verificacao
            throw new DadosInvalidos("Produto nulo inserido");
        }

        if (produt.getCodigo() <= 0 || produt.getDescricao() == null || produt.getDescricao().isEmpty() || produt.getMin() <= 0 || produt.getLucro() < 0) {
            throw new DadosInvalidos("Dados do produto inválidos");
        }

        for (Produto produto : produtos) {
            if (produto.getCodigo() == produt.getCodigo()) {
                throw new ProdutoJaCadastrado("Ja existe produto com o codigo " + produt.getCodigo());
            }
        }
        produtos.add(produt);

    }

    public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel{ // val e a data de validade do produto
        if(quant <= 0 || preco <= 0){   // VERIFICACAO extra para evitar verificar so no produto(elemento ddo array de produtos).compra
            throw new DadosInvalidos("Preco ou quantidade inválidas");
        }

        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {

                if (produto instanceof ProdutoPerecivel) {   // para ver produto como objeto da classe Produto perecivel, se for normal ai para o else if
                    Date date_today = new Date();
                    if (val == null || val.getTime() < date_today.getTime()){

                        throw new ProdutoNaoPerecivel("Produto perecivel deve ter validade e que seja de hoje em diante!");
                    }
                    produto.compra(quant, preco, val);
                    return;     // para quebrar o loop
                }
                else if (val == null){  // se nao for produto perecivel e receber val( data de validade) = null
                    produto.compra(quant, preco, null);
                    return;
                    // nao é para retornar excecao quando ocorre um caso que nao é erro, nao precisa
                }
                else {
                    throw new DadosInvalidos("Para Produto nao perecivel deve colocar validade = null!");
                }
                // seguindo o requisitado: no caso de ser um produto normal, deve dar para comprar certinho so se der a data null para ele

            }
            else{
                throw new ProdutoInexistente();
            }

        }


    }

    public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido, DadosInvalidos{
        if (quant <= 0) {
            throw new DadosInvalidos("Quantidade inválida");
        }
        for (Produto produto : produtos) {  // a verificacao de lote so ocorre dentro do vender do produto perecivel
            if (produto.getCodigo() == cod ) {
                if (produto instanceof ProdutoPerecivel){
                    return ((ProdutoPerecivel) produto).vender(quant);
                }
                else { // Adicionar após o if para ProdutoPerecivel, aqui é se nao for perecivel
                    if (produto.getQuantidade() >= quant) {
                        double valor = quant * produto.getPrecoVenda();
                        produto.setQuantidade(produto.getQuantidade() - quant);
                        return valor;
                    }
                    throw new DadosInvalidos("Quantidade de Produtos insuficiente");
                }
            }
            else{
                throw new ProdutoInexistente();
            }
        }
        throw new ProdutoVencido("Produto com lotes vencidos!");
        // A LINHA 55 DE PRODUTO PERECIVEL NOS LEVA PARA essa throw acima
    }

    public Produto pesquisar(int cod) throws ProdutoInexistente{
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto;
            }
        }
        throw new ProdutoInexistente();
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

    public int quantidadeVencidos(int cod) throws ProdutoInexistente{
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod && produto instanceof ProdutoPerecivel) {
                return ((ProdutoPerecivel) produto).quantidadeVencidos();
            }
        }
        throw new ProdutoInexistente("Produto com lotes vencidos!");
    }

    public void adicionarFornecedor(int cod, Fornecedor f) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                produto.addFornecedor(f);
                return;
            }
        }
    }

    public double precoDeVenda(int cod) throws ProdutoInexistente{
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto.getPrecoVenda();
            }
        }
        throw new ProdutoInexistente();
    }

    public double precoDeCompra(int cod) throws ProdutoInexistente{
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto.getPrecoCompra();
            }
        }
        throw new ProdutoInexistente();
    }



    public int quantidade(int cod) throws ProdutoInexistente{
        for (Produto produto : produtos){
            if (produto.getCodigo() == cod){
                return produto.getQuantidade();
            }
        }
        throw new ProdutoInexistente("Produto com lotes vencidos!");
    }




}

