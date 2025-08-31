package leonardomarquis.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public interface InterfaceEstoque {
    // aqui define os valores superiores a serem retornados nos respectivos metodos que
    // foram definidos em estoque

    // Return false se tiver numerico invalido ou texto vazio ou null ou ja tiver um produto
    public boolean incluir(Produto p);

    // return false se o produto nao existir, ou numericos invalidos ou se mandar data e o produto comprado nao for perecivel
    public boolean comprar(int cod, int quant, double preco, Date val);

    // return -1 se o produto nao existir, se for vencido ou se tiver numero invalido
    public double vender(int cod, int quant);

    // return null se o produto nao existir
    public Produto pesquisar (int cod);

    // return a lista ddos produtos com estoque abaixo do minimo, ou  lista vazia se nao tiver nenhum no casooo
    public ArrayList<Produto> estoqueAbaixoDoMinimo();

    // return umalista de produtos vencidos, ou entoa uma lista vazia caso nao tiver nenhum
    public ArrayList<Produto> estoqueVencido();

    // return 0 se nao houver nenhum produto vencido ou a quantidade de produtos vencidos do certo codigo
    public int quantidadeVencidos(int cod);

    public void adicionarFornecedor(int cod, Fornecedor f);

    public double precoDeVenda(int cod);

    public double precoDeCompra(int cod);

}
