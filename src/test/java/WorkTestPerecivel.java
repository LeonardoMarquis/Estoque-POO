
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


import leonardomarquis.estoqueComProdutoPerecivel.*;

public class WorkTestPerecivel {
    Estoque e1 =  new Estoque();
    Produto p1 = new ProdutoPerecivel(1, "Melancia", 10, 0.5);
    Produto p2 = new ProdutoPerecivel(2, "Caju", 50, 0.6);


    @Test
    void testComprarVencidosParaEstoque() {      //comprando vencido para o estoque
        // Compra com validade para frente
        p1.compra(5, 2.0, new Date(System.currentTimeMillis() + 86400000)); // +1 dia
        assertEquals(0, e1.quantidadeVencidos(1));

        // compra de vencido
        p1.compra(3, 2.0, new Date(System.currentTimeMillis() - 86400000)); // -1 dia
        assertEquals(0, e1.quantidadeVencidos(1));  // o sistema nao deixa comprar vencido!
    }

    // de vendas
    @Test
    void testVenda() {
        p1.compra(10, 2.0, new Date(System.currentTimeMillis() + 86400000)); // validade futura em um dia

        double esperado = 10 * p1.getPrecoVenda();
        double resultado = p1.vender(10);

        assertEquals(esperado, resultado, 0.001, "Venda deve retornar valor correto");
    }

    @Test
    void testVenderVencidos() {
        p2.compra(5, 2.0, new Date(System.currentTimeMillis() - 86400000)); // vencido

        double resultado = p2.vender(5);
        assertEquals(-1, resultado, "Não deve vender produtos vencidos");   // pode vender vencidos
    }



    // de quantidades
    @Test
    void testVendaQuantidadeInsuficiente() {
        p2.compra(2, 2.0, new Date(System.currentTimeMillis() + 86400000)); // validade para frente

        double resultado = p2.vender(5);
        assertEquals(-1, resultado, "Não deve vender se quantidade for insuficiente");
    }

    @Test
    void testVendaComQuantidadeZero() {
        double resultado = p2.vender(0);
        assertEquals(-1, resultado, "Não deve vender se quantidade for zero");
    }

}
