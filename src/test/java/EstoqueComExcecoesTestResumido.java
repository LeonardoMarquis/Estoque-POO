//package pedroSantosNeto.estoqueComProdutoPerecivelExcecao;
//package leonardomarquis.estoqueComProdutoPerecivelExcecao;

import leonardomarquis.estoqueComProdutoPerecivelExcecao.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;

public class EstoqueComExcecoesTestResumido {

    @Test
    public void testIncluirProdutoAindaNaoIncluido() throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente {
        // Criação de objetos necessários para o teste
        Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
        Produto produto = new Produto(1, "Produto A", 10, 1.5);

        // Criação da instância do Estoque
        Estoque estoque = new Estoque();

        // Teste de inclusão de um produto
        estoque.incluir(produto);

        // Verificação do resultado esperado
        Produto produtoEncontrado = null;
        produtoEncontrado = estoque.pesquisar(1);
        assertEquals(produto.getCodigo(), produtoEncontrado.getCodigo());
    }
    
    @Test
    public void incluirProdutoComMinNegativo() throws ProdutoJaCadastrado, ProdutoInexistente {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Unilever");
        Fornecedor forn2 = new Fornecedor(33, "Nestle");
        Produto prod1 = new Produto(12, "Condicionador", -1, 2);
        Produto prod2 = new ProdutoPerecivel(14, "Shampoo", -1, 2);

        try {
            estoque.incluir(prod1);
            fail("Deveria ter dado erro de dados invalidos");
        } catch (DadosInvalidos e) {
            // TODO: handle exception ! o que fazer se essa excecao acontecer
        }
        try {
            estoque.incluir(prod2);
            fail("Deveria ter dado erro de dados invalidos");
        } catch (DadosInvalidos e) {
            // TODO: handle exception
        }
    }
    

    @Test
    public void testIncluirProdutoExistente() throws DadosInvalidos, ProdutoInexistente, ProdutoJaCadastrado {
        // Criação de objetos necessários para o teste
        Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
        Produto produto = new Produto(1, "Produto A", 10, 1.5);
        Produto produto2 = new Produto(1, "Produto B", 10, 1.5);

        // Criação da instância do Estoque
        Estoque estoque = new Estoque();
        estoque.incluir(produto);

        // Teste de inclusão do mesmo produto novamente
        try {
            estoque.incluir(produto2);
            fail("Deveria ter dado erro de prod já cadastrado");	
        } catch (ProdutoJaCadastrado e) {
        }
    }
  
    
    @Test
    public void testIncluirProdutosComNomeNulo () throws DadosInvalidos, ProdutoInexistente, ProdutoJaCadastrado {
        // Criação de objetos necessários para o teste
        Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
        Produto produto = new Produto(1, null, 10, 1.5);

        // Criação da instância do Estoque
        Estoque estoque = new Estoque();

        // Teste de inclusão do mesmo produto novamente
        try {
            estoque.incluir(produto);
            fail("Deveria ter dado erro de dados invalidos");	
        } catch (DadosInvalidos e) {
        }
    }
    
    @Test
    public void testIncluirProdutosComNomeVazio () throws DadosInvalidos, ProdutoInexistente, ProdutoJaCadastrado {
        // Criação de objetos necessários para o teste
        Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
        Produto produto = new Produto(1, "", 10, 1.5);
        Produto produto2 = new Produto(2, "   ", 10, 1.5);

        // Criação da instância do Estoque
        Estoque estoque = new Estoque();

        // Teste de inclusão do mesmo produto novamente
        try {
            estoque.incluir(produto);
            fail("Deveria ter dado erro de dados invalidos");	
        } catch (DadosInvalidos e) {
        }
        // Teste de inclusão do mesmo produto novamente
        try {
            estoque.incluir(produto2);
            fail("Deveria ter dado erro de dados invalidos");	
        } catch (DadosInvalidos e) {
        }
    }
        
    @Test
    public void compraProdutoNaoPerecivel()
            throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Unilever");
        Fornecedor forn2 = new Fornecedor(19, "Gilette");
        Produto prod1 = new Produto(12, "Shampoo", 5, 2);
        Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1);

        estoque.incluir(prod1);
        estoque.incluir(prod2);
        estoque.comprar(prod1.getCodigo(), 10, 5, null);
        estoque.comprar(prod2.getCodigo(), 5, 2.5, null);
        
        int quant1 = estoque.quantidade(12);
        int quant2 = estoque.quantidade(13);
        assertEquals(10, quant1);
        assertEquals(5, quant2);

    }
    
    @Test
    public void testComprarProdutoNaoPerecivelComData() throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoVencido {
        // Criação de objetos necessários para o teste
        Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
        Produto produto = new Produto(2, "Produto B", 5, 2.0);

        // Criação da instância do Estoque
        Estoque estoque = new Estoque();

        estoque.incluir(produto);

        try {
            estoque.comprar(2, 5, 2.0, new Date());
            fail("Deveria ter dado erro de compra de prod nao perecivel com data de validade");	
        } catch (ProdutoNaoPerecivel e) {
        }
    }
    
    @Test
    public void compraProdutoPerecivel()
            throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(33, "Nestle");
        Fornecedor forn2 = new Fornecedor(24, "Ambev");
        Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2);
        Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1);
        Date data = new Date();
        data.setTime(data.getTime() + 120000);

        estoque.incluir(prod1);
        estoque.incluir(prod2);
        estoque.comprar(prod1.getCodigo(), 24, 8, data);
        estoque.comprar(prod1.getCodigo(), 10, 5, data);
        estoque.comprar(prod2.getCodigo(), 11, 4.23, data);
        estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
        
        int quant1 = estoque.quantidade(14);
        int quant2 = estoque.quantidade(15);
        assertEquals(34, quant1);
        assertEquals(16, quant2);
    }

    @Test
    public void vendaProdutoPerecivelVariosLotes()
            throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido {
        Estoque estoque = new Estoque();
        Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2);
        Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1);
        Date data1 = new Date();
        data1.setTime(data1.getTime() + 120000);
        Date data2 = new Date();
        data2.setTime(data2.getTime() + 120000);

        estoque.incluir(prod1);
        estoque.incluir(prod2);
        estoque.comprar(prod1.getCodigo(), 10, 8, data1);
        estoque.comprar(prod1.getCodigo(), 10, 5, data2);
        estoque.comprar(prod2.getCodigo(), 5, 4.23, data1);
        estoque.comprar(prod2.getCodigo(), 5, 2.5, data2);
        
        int quant1 = estoque.quantidade(14);
        int quant2 = estoque.quantidade(15);
        assertEquals(20, quant1);
        assertEquals(10, quant2);
        
        data2.setTime(data2.getTime() - 140000);
        
        try {
            estoque.vender(14, 11);
            fail("Nao deveria ter vendido quant maior que a valida");
        } catch (ProdutoVencido e) {
            assertEquals(20, estoque.quantidade(14));
        }
    }

    @Test
    public void vendaProdutoPerecivelComValidadeMenorQueDataAtual()
            throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido, InterruptedException {
        Estoque estoque = new Estoque();
        Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2);
        Date dataHj = new Date();
        dataHj.setTime(dataHj.getTime() + 120000);

        estoque.incluir(prod1);
        estoque.comprar(prod1.getCodigo(), 24, 8, dataHj);

        Thread.sleep(100);
        dataHj.setTime(dataHj.getTime() - 140000);
        
        try {
            estoque.vender(14, 20);
            fail("Deveria ter dado excecao de prod vencido");
        } catch (ProdutoVencido e) {
        }
    }
    
    
    @Test
    public void vendeItensQuantidadeNegativa() throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel, ProdutoVencido {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.comprar(12, 20, 5, null);
        // Verifica se o valor total da venda esta correto
        try {
            estoque.vender(12, -1);
            fail("Não deveria ter vendido itens quant negativa");
        } catch (DadosInvalidos e) {
            // TODO: handle exceptio
        }
    }
    
    @Test
    public void vendeItens() throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        Produto prod2 = new Produto(13, "Sorvete", 5, 0.1);
        estoque.incluir(prod1);
        estoque.comprar(12, 20, 5, null);
        estoque.incluir(prod2);
        estoque.comprar(13, 20, 5, null);
        // Verifica se o valor total da venda esta correto
        assertEquals(5.5, estoque.vender(13, 1), 0.001);
    }
    
    @Test
    public void vendeItensQuantidadeMaiorQueEstoque() throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.comprar(12, 20, 5, null);
        // Verifica se o valor total da venda esta correto
        try {
            estoque.vender(12, 30);
            fail("Nao deveria ter vendido quant maior que estoque");
        } catch (DadosInvalidos e) {
            // TODO: handle exception, o que fazer se essa excecao acontecer
        }
        assertEquals(20, estoque.quantidade(12));
    }
    
        
    @Test
    public void incluirProdutoNull() throws ProdutoJaCadastrado, ProdutoInexistente {
        Estoque estoque = new Estoque();
        
        try {
            estoque.incluir(null);
            fail("Não deveria comprar item null");
        } catch (DadosInvalidos e) {
        	// TODO: handle exception
        }
    }






    @Test
    public void vendaQuantidadeMaiorQueEstoque() {
        Estoque estoque = new Estoque();
        Produto prod1 = new Produto(12, "Shampoo", 5, 2);
        Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1);
        Date data = new Date();
        data.setTime(data.getTime() + 120000);

        try {
            estoque.incluir(prod1);
            estoque.incluir(prod2);
            estoque.comprar(12, 10, 5, null);
            estoque.comprar(15, 5, 2.5, data);
            estoque.vender(12, 5);
            assertEquals(5, estoque.quantidade(12));
            estoque.vender(15, 2);
            assertEquals(3, estoque.quantidade(15));
        } catch (Exception e) {
            // possíveis exceções dependendo da implementação
        }

        try {
            estoque.vender(12, 12);
            fail("Vender quantidade maior que estoque deve lançar alguma exceção ou retornar -1 dependendo da implementação");
        } catch (Exception e) {
            // aceitável que lance exceção
        }
    }


    @Test
    public void produtosAbaixoDoEstoqueMinimo() {
        Estoque estoque = new Estoque();
        Produto prod1 = new Produto(12, "Shampoo", 5, 2);
        Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1);
        Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2);
        Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1);
        Date data = new Date();
        data.setTime(data.getTime() + 120000);

        try {
            estoque.incluir(prod1);
            estoque.incluir(prod2);
            estoque.incluir(prod3);
            estoque.incluir(prod4);
            estoque.comprar(prod1.getCodigo(), 10, 5, null);
            estoque.comprar(prod2.getCodigo(), 10, 2.5, null);
            estoque.comprar(prod3.getCodigo(), 10, 5, data);
            estoque.comprar(prod4.getCodigo(), 20, 2.5, data);
            estoque.vender(12, 9);
            estoque.vender(13, 1);
            estoque.vender(14, 2);
            estoque.vender(15, 18);
        } catch (Exception e) {
            // dependendo da implementação, algumas operações podem lançar exceções
        }
        ArrayList<Produto> abaixoMinActual = estoque.estoqueAbaixoDoMinimo();
        assertEquals(2, abaixoMinActual.size());
        for (Produto produto : abaixoMinActual) {
            assertTrue(produto == prod1 || produto == prod4);
        }
    }

    @Test
    public void testVendaComQuantidadeZero() {

        Estoque e1 =  new Estoque();
        Produto p2 = new ProdutoPerecivel(2, "Caju", 50, 0.6);

        try {
            e1.incluir(p2);
        } catch (Exception ex) {
            // ok
        }

        try {
            e1.vender(2, 0);
            fail("Não deve vender se quantidade for zero");
        } catch (DadosInvalidos ex) {
            // esperado se implementação valida entrada
        } catch (Exception ex) {
            // outro comportamento possível
        }
    }

    @Test
    public void compraProdutoPerecivelComValidadeMenorQueDataAtual() {
        Estoque estoque = new Estoque();
        Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2);
        Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1);
        Date data = new Date();
        data.setTime(data.getTime() - 120000);

        try {
            estoque.incluir(prod1);
            estoque.incluir(prod2);
        } catch (Exception e) {
            fail("Inclusão não deve lançar exceção");
        }

        try {
            estoque.comprar(14, 24, 8, data);
            fail("Compra de perecível com validade passada deve lançar ProdutoNaoPerecivel");
        } catch (ProdutoNaoPerecivel e) {
            // esperado
        } catch (Exception e) {
            fail("Esperado ProdutoNaoPerecivel ao comprar com validade passada");
        }

        try {
            estoque.quantidade(14);
            // se não lançou exceção na compra, quantidade pode ser diferente
        } catch (ProdutoInexistente e) {
            // esperado se compra não ocorreu
        }
    }

    @Test
    public void incluirProdutoComLucroNegativo() {
        Estoque estoque = new Estoque();
        Produto prod1 = new Produto(12, "Shampoo", 5, -2);
        Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, -2);

        try {
            estoque.incluir(prod1);
            fail("Inclusão com lucro negativo deve lançar DadosInvalidos");
        } catch (DadosInvalidos e) {
            // esperado
        } catch (ProdutoJaCadastrado e) {
            throw new RuntimeException(e);
        }

        try {
            estoque.incluir(prod2);
            fail("Inclusão perecivel com lucro negativo deve lançar DadosInvalidos");
        } catch (DadosInvalidos e) {
            // esperado
        } catch (ProdutoJaCadastrado e) {
            throw new RuntimeException(e);
        }

        try {
            estoque.pesquisar(12);
            fail("Pesquisar produto não incluido deve lançar ProdutoInexistente");
        } catch (ProdutoInexistente e) {
            // esperado
        }

        try {
            estoque.pesquisar(14);
            fail("Pesquisar produto não incluido deve lançar ProdutoInexistente");
        } catch (ProdutoInexistente e) {
            // esperado
        }
    }



    @Test
    public void compraProdutoNaoExistente() {
        Estoque estoque = new Estoque();
        Produto prod1 = new Produto(12, "Shampoo", 5, 2);
        Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1);
        Date data = new Date();

        // produtos nao foram incluídos, portanto quantidade(...) deve lançar ProdutoInexistente
        try {
            estoque.quantidade(prod1.getCodigo());
            fail("Quantidade para produto não incluido deve lançar ProdutoInexistente");
        } catch (ProdutoInexistente e) {
            // esperado
        }
        try {
            estoque.quantidade(prod2.getCodigo());
            fail("Quantidade para produto não incluido deve lançar ProdutoInexistente");
        } catch (ProdutoInexistente e) {
            // esperado
        }
    }
}
