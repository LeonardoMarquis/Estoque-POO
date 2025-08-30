// package leonardomarquis.estoque;

import leonardomarquis.estoque.Estoque;
import leonardomarquis.estoque.Produto;
import leonardomarquis.estoque.Produto;
import leonardomarquis.estoque.Fornecedor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// LEMBRAR DE REMOVER OS IMPORTS E DEIXAR NA MESMA PACKGE, e de colocar o testes na mesma pasta dos outros

public class WorkTest {
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
