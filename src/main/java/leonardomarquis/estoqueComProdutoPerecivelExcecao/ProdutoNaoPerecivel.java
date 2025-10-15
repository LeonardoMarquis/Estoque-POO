package leonardomarquis.estoqueComProdutoPerecivelExcecao;

public class ProdutoNaoPerecivel extends Exception{
    public ProdutoNaoPerecivel(){
        super("Produto não perecivel!");
    }

    public ProdutoNaoPerecivel(String msg){
        super(msg);
    }
}


// funciona assim:
// as funcoes que tiverem caminhos que nao dao certo, eu uso um try: camiho certo e catch: chamo a excecao
//
// uma excecao pode nem sempre ser para um caso de erro, por ser para um caso que tambem é aceito mas é diferente de outro nao é?
//