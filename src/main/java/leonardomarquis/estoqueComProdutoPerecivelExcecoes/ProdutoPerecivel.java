package leonardomarquis.estoqueComProdutoPerecivelExcecoes;

import java.util.*;

public class ProdutoPerecivel extends Produto { // produtoperezivel herda de produto


    private ArrayList<Lote> lotes;

    public ProdutoPerecivel(int cod, String desc, int min, double lucro) {
        super(cod, desc, min, lucro);   // pegar o construrtor do de quem ele herda
        this.lotes = new ArrayList<>();
    }

    public void compra(int quant, double val, Date validade) {  //compra para o estoque!
        Date hoje = new Date();
        if(validade.after(hoje)){
            super.compra(quant, val, validade);
            Lote lote = new Lote(quant, validade);
            lotes.add(lote);
        }

    }



    @Override


    public double vender(int quant) {
        if (quant <= 0){
            return -1;
        }

        // para pegar so os lotes nao vencdos
        ArrayList<Lote> lotesValidos = new ArrayList<>();
        Date hoje = new Date();

        for (Lote lote : lotes) {
            if (lote.getValidade() == null || lote.getValidade().after(hoje)) {
                lotesValidos.add(lote);
            }
        }
        // se o lote estiver na validade ele vai para os lotes vendiveis

        // saner se tem n de lotes validos suficeinte
        int disponivel = 0;
        for (Lote lote : lotesValidos) {
            disponivel += lote.getQuant();
        }

        if (disponivel < quant) {

            System.out.println("Nao ha quantidade suficiente de lotes validos!");
            return -1;      // da esse return, para a funcao de vender do estoque,
                            // no caso ocorre isso aqui quando nao tem lotes validos para vender
                            // ou seja, os lotes que tem estao vencidos
                            // entao posso dizer que quando voltar la, é porque os LOTES ESTAO VENCIDOS
                            // e ai DA THROW NA EXCECAO DE LOTES VENCIDOS!!
        }

        // tem n suficiente entao da para vender
        int totalVendidos = 0;
        double totalValor = 0.0;

        // aqui e para ordenar os lotes validos pela data de validade (do mais proximo para o mais distante)
        Collections.sort(lotesValidos, Comparator.comparing(Lote::getValidade));



        // verifica os lotes válidos para realizar a venda
        Iterator<Lote> iterator = lotesValidos.iterator();
        while (iterator.hasNext()) {
            Lote lote = iterator.next();
            int qtdLote = lote.getQuant();
            int aVender = Math.min(quant - totalVendidos, qtdLote); // Vende o minimo entre o que falta e a quant disponiv no lote
            lote.setQuant(qtdLote - aVender); // atualiza a quantidade do  lote
            totalVendidos += aVender; // atualiza a quant vendida
            totalValor += aVender * getPrecoVenda(); // atualiza o valor total da venda

            // se tiver lotevazido remove
            if (lote.getQuant() == 0) {
                lotes.remove(lote); // Remove o lote da lista
                System.out.println("O lote foi removido por falta de unidades");
            }

            if (totalVendidos == quant) break; // Se já vendeu a quantidade necessária, encerra o loop
        }

        // mudar quantidade no produto(perecivel no caso)
        int novaQuanti = 0;
        for (Lote lote : lotes){
            novaQuanti += lote.getQuant();
        }
        setQuantidade(novaQuanti);

        //adcicionar a movkementacao
        addMovimentacoes("venda", totalVendidos, getPrecoVenda());
        return totalValor;
    }



    public int quantidadeVencidos() {
        int totalVencidos = 0;
        for (Lote lote : lotes) {       // de acordo com o requisitado: se a validade e hoje, esta vencido >:(   logo se a data e menor ou igaul a de hoje, esta vencido
            if (lote.getValidade() != null && (lote.getValidade().getTime() <= (new Date()).getTime() )) {
                totalVencidos += lote.getQuant();
            }
        }
        return totalVencidos;
    }
}
