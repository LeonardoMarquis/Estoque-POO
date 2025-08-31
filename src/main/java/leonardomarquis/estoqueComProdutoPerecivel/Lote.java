package leonardomarquis.estoqueComProdutoPerecivel;


import java.util.Date;

public class Lote {
    private int quant;
    private Date validade;

    public Lote(int quant, Date validade) {
        this.quant = quant;
        this.validade = validade;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    public int getQuant() {
        return quant;
    }



    public void setValidade(Date validade) {
        this.validade = validade;
    }
    public Date getValidade() {
        return validade;
    }


}