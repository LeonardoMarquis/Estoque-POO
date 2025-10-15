package leonardomarquis.estoqueComProdutoPerecivelExcecao;

import java.util.ArrayList;
import java.util.Date;

public interface InterfaceEstoqueComExcecoes {
    // aqui define os valores superiores a serem retornados nos respectivos metodos que
    // foram definidos em estoque

    // Return false se tiver numerico invalido ou texto vazio ou null ou ja tiver um produto
    public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos;

    //Se for enviado validade e o produto não for perecível, exceção ProdutoNaoPerecivel
    public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel;

    // return -1 se o produto nao existir, se for vencido ou se tiver numero invalido
    public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido, DadosInvalidos;


    // return null se o produto nao existir
    public Produto pesquisar (int cod) throws ProdutoInexistente;

    // return a lista ddos produtos com estoque abaixo do minimo, ou  lista vazia se nao tiver nenhum no casooo
    public ArrayList<Produto> estoqueAbaixoDoMinimo();

    // return umalista de produtos vencidos, ou entoa uma lista vazia caso nao tiver nenhum
    public ArrayList<Produto> estoqueVencido();

    // return 0 se nao houver nenhum produto vencido ou a quantidade de produtos vencidos do certo codigo
    public int quantidadeVencidos(int cod) throws ProdutoInexistente;

    public int quantidade(int cod) throws ProdutoInexistente;


}
