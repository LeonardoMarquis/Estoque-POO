package leonardomarquis.estoqueComProdutoPerecivelExcecao;

public class ProdutoVencido extends Exception{
    public ProdutoVencido(){
        super("Produto vencido!");
    }

    public ProdutoVencido(String msg){
        super(msg);
    }
}


// funciona assim:
// as funcoes que tiverem caminhos que nao dao certo, eu uso um try: camiho certo e catch: chamo a excecao
//
//
//