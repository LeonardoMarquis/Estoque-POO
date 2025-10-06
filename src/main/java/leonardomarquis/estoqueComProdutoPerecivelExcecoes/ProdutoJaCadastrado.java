package leonardomarquis.estoqueComProdutoPerecivelExcecoes;

public class ProdutoJaCadastrado extends Exception{
    public ProdutoJaCadastrado(){
        super("Produto ja cadastrado!");
    }

    public ProdutoJaCadastrado(String msg){
        super(msg);
    }
}


// funciona assim:
// as funcoes que tiverem caminhos que nao dao certo, eu uso um try: camiho certo e catch: chamo a excecao
//
//
//