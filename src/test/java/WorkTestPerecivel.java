
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


import leonardomarquis.estoqueComProdutoPerecivel.*;

class WorkTestPerecivel {

    private ProdutoPerecivel produto;

    @BeforeEach
    void setUp() {
        produto = new ProdutoPerecivel(1, "Leite", 10, 0.5); // código, descrição, mínimo, lucro
    }

    @Test
    void testCompraEQuantidadeVencidos() {
        // Compra com validade futura
        produto.compra(5, 2.0, new Date(System.currentTimeMillis() + 86400000)); // +1 dia
        assertEquals(0, produto.quantidadeVencidos());

        // Compra com validade passada
        produto.compra(3, 2.0, new Date(System.currentTimeMillis() - 86400000)); // -1 dia
        assertEquals(3, produto.quantidadeVencidos());
    }

    @Test
    void testVendaComSucesso() {
        produto.compra(10, 2.0, new Date(System.currentTimeMillis() + 86400000)); // validade futura

        double esperado = 10 * produto.getPrecoVenda();
        double resultado = produto.vender(10);

        assertEquals(esperado, resultado, 0.001, "Venda deve retornar valor correto");
    }

    @Test
    void testVendaComProdutoVencido() {
        produto.compra(5, 2.0, new Date(System.currentTimeMillis() - 86400000)); // validade passada

        double resultado = produto.vender(5);
        assertEquals(-1, resultado, "Não deve vender produtos vencidos");
    }

    @Test
    void testVendaParcialDisponivel() {
        produto.compra(5, 2.0, new Date(System.currentTimeMillis() + 86400000)); // validade futura

        double esperado = 3 * produto.getPrecoVenda();
        double resultado = produto.vender(3);

        assertEquals(esperado, resultado, 0.001, "Venda parcial deve funcionar corretamente");
    }

    @Test
    void testVendaQuantidadeInsuficiente() {
        produto.compra(2, 2.0, new Date(System.currentTimeMillis() + 86400000)); // validade futura

        double resultado = produto.vender(5);
        assertEquals(-1, resultado, "Não deve vender se quantidade for insuficiente");
    }

    @Test
    void testVendaComQuantidadeZero() {
        double resultado = produto.vender(0);
        assertEquals(-1, resultado, "Não deve vender se quantidade for zero");
    }
}
