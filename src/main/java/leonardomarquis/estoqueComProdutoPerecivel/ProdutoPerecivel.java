package leonardomarquis.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ProdutoPerecivel extends Produto {
    private ArrayList<Lote> lotes;

    public ProdutoPerecivel(int cod, String desc, int min, double lucro) {
        super(cod, desc, min, lucro);
        this.lotes = new ArrayList<>();
    }

    public void compra(int quant, double val, Date validade) {
        super.compra(quant, val, validade);
        Lote lote = new Lote(quant, validade);
        lotes.add(lote);
    }

    @Override
    public double vender(int quant) {
        if (quant <= 0) return -1;

        // Filtra apenas os lotes válidos (não vencidos)
        ArrayList<Lote> lotesValidos = new ArrayList<>();
        Date hoje = new Date();

        for (Lote lote : lotes) {
            if (lote.getValidade() == null || lote.getValidade().after(hoje)) {
                lotesValidos.add(lote);
            }
        }

        // Verifica se há quantidade suficiente em lotes válidos
        int disponivel = 0;
        for (Lote lote : lotesValidos) {
            disponivel += lote.getQuant();
        }

        if (disponivel < quant) {
            // Se não houver lotes válidos suficientes
            System.out.println("Não há quantidade suficiente de lotes válidos.");
            return -1;
        }

        // Agora podemos vender, pois temos quantidade suficiente nos lotes válidos
        int totalVendidos = 0;
        double totalValor = 0.0;

        // Ordenar os lotes válidos pela data de validade (do mais próximo para o mais distante)
        Collections.sort(lotesValidos, Comparator.comparing(Lote::getValidade));

        // Itera sobre os lotes válidos para realizar a venda
        Iterator<Lote> iterator = lotesValidos.iterator();
        while (iterator.hasNext()) {
            Lote lote = iterator.next();
            int qtdLote = lote.getQuant();
            int aVender = Math.min(quant - totalVendidos, qtdLote); // Vende o mínimo entre o que falta e a quantidade disponível no lote
            lote.setQuant(qtdLote - aVender); // Atualiza a quantidade do lote
            totalVendidos += aVender; // Atualiza a quantidade vendida
            totalValor += aVender * getPrecoVenda(); // Atualiza o valor total da venda

            // Se o lote estiver vazio, deve ser removido
            if (lote.getQuant() == 0) {
                iterator.remove(); // Remove o lote da lista
                System.out.println("Lote removido, pois ficou sem unidades.");
            }

            if (totalVendidos == quant) break; // Se já vendeu a quantidade necessária, encerra o loop
        }

        // Adiciona movimentações de venda
        adicionarMovimentacoes("Venda", totalVendidos, getPrecoVenda());
        return totalValor;
    }

    public int quantidadeVencidos() {
        int totalVencidos = 0;
        for (Lote lote : lotes) {
            if (lote.getValidade() != null && lote.getValidade().before(new Date())) {
                totalVencidos += lote.getQuant();
            }
        }
        return totalVencidos;
    }
}
