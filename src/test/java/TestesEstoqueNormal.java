//package leonardomarquis.estoque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import leonardomarquis.estoque.*;
//remover imports e colocar o path e colocar os coisas do professor

public class TestesEstoqueNormal {

    @Test
    public void produtosAbaixoDoEstoqueMinimo() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Fornecedor forn2 = new Fornecedor(19, "Ambev");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        Produto prod2 = new Produto(15, "Cerveja", 5, 1);
        Produto prod3 = new Produto(18, "Cerveja Pilsen", 5, 1);

        estoque.incluir(prod1);
        estoque.incluir(prod2);
        estoque.incluir(prod3);
        estoque.comprar(12, 3, 5);
        estoque.comprar(15, 1, 10);
        estoque.comprar(18, 5, 8);
        ArrayList<Produto> produtosAbaixoDoMinimo = estoque.estoqueAbaixoDoMinimo();
        assertEquals(2, produtosAbaixoDoMinimo.size() );
    }

    @Test
    public void compraItens() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 0.5);
        estoque.incluir(prod1);
        estoque.comprar(12, 10, 4);
        estoque.comprar(12, 20, 7);
        assertEquals(6, estoque.precoDeCompra(12), 0.0001);
        assertEquals(9, estoque.precoDeVenda(12), 0.0001);
        // Verifica se a quantidade de itens foi atualizada corretamente
        assertEquals(30, estoque.quantidade(12));
    }

    @Test
    public void compraItensProdutoNaoIncluido() {
        Estoque estoque = new Estoque();
        estoque.comprar(12, 10, 4);
        assertEquals(-1, estoque.quantidade(12));
    }

    @Test
    public void compraQuantidadeNegativaDeItens() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.comprar(12, -10, 4);
        assertEquals(0, estoque.quantidade(12));
    }


    @Test
    public void vendeQuantidadeNegativaDeItens() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.comprar(12, 10, 4);
        estoque.vender(12, -5);
        assertEquals(10, estoque.quantidade(12));
    }


    @Test
    public void quantidadeProdutoNaoIncluido() {
        Estoque estoque = new Estoque();
        int retorno = estoque.quantidade(0);
        assertEquals(-1, retorno);
    }

    @Test
    public void verificaFornecedorProduto() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Fornecedor forn2 = new Fornecedor(49, "Kibon");
        Fornecedor forn3 = new Fornecedor(50, "Quick");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.adicionarFornecedor(12, forn1);
        estoque.adicionarFornecedor(12, forn2);
        estoque.adicionarFornecedor(12, forn3);
        ArrayList<Fornecedor> forns = estoque.fornecedores(12);
        assertEquals(3, forns.size());

    }



    //=========================================================================================
    // testes extras

    Produto p1 = new Produto(1, "lapis ", 10, 0.5);
    Produto p2 = new Produto(2, "copic48 ", 5, 0.5);
    Produto p3 = new Produto(3, "sacocarvao ", 10, 0.5);
    Fornecedor f1 = new Fornecedor(100, "Bee");
    Fornecedor f2 = new Fornecedor(101, "Tanjiro");
    Estoque e1 = new Estoque();

    @Test
    public void testeCompraProdutos(){
        e1.incluir(p1);
        e1.incluir(p2);
        System.out.println(e1.quantidade(1));
        System.out.println(e1.quantidade(2));

        e1.comprar(1,100,1);
        e1.comprar(2, 50, 20);

        System.out.println(e1.quantidade(1));
        System.out.println(e1.quantidade(2));

        assertEquals(100, e1.quantidade(1), 0.001);
        assertEquals(50, e1.quantidade(2), 0.001);
    }

    @Test
    public void testeVenderProdutos(){
        e1.incluir(p1);
        e1.incluir(p2);

        e1.comprar(1,100,1);
        e1.comprar(2, 50, 20);

        System.out.println(e1.quantidade(1));
        System.out.println(e1.quantidade(2));

        e1.vender(1,80);
        e1.vender(2, 46);

        System.out.println(e1.quantidade(1));
        System.out.println(e1.quantidade(2));


        assertEquals(20, e1.quantidade(1), 0.001);
        assertEquals(4, e1.quantidade(2), 0.001);

        assertNotNull(e1.estoqueAbaixoDoMinimo());
    }

    @Test
    public void testeAddFornecedor(){
        e1.incluir(p1);
        e1.incluir(p3);
        e1.adicionarFornecedor(1, f1);  // o cod e o codigo do produto queo fornecedor fornece
        e1.adicionarFornecedor(3, f2);

        System.out.println(e1.fornecedores(1));
        System.out.println(e1.fornecedores(3));

        assertNotNull(e1.fornecedores(1));
        assertNotNull(e1.fornecedores(3));

    }
    @Test
    public void testeFornecedoresNull(){            // quer saber se tem fornecedor
        e1.incluir(p3);                             // tem o produto mas nao tem o fornecedor

        System.out.println(e1.fornecedores(3));

        assertNull(e1.fornecedores(3));         //como esta sem fornecedor deve retornar null
    }

    @Test
    public void testeFornecedoresNullSemProdutoIncluso(){   // quer saber se tem fornecedor
        // mas nem tem o produto e nem o fornecedor
        System.out.println(e1.fornecedores(3));

        assertNull(e1.fornecedores(3));         //como esta sem fornecedor e sem produto deve retornar null
    }


}
