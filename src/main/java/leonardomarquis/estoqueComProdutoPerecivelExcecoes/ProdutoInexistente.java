package leonardomarquis.estoqueComProdutoPerecivelExcecoes;

public class ProdutoInexistente extends Exception{
    public ProdutoInexistente(){
        super("Produto inexistente!");
    }

    public ProdutoInexistente(String msg){
        super(msg);
    }
}


// funciona assim:
// as funcoes que tiverem caminhos que nao dao certo, eu uso um try: camiho certo e catch: chamo a excecao
//
//  e como tem esse 2° construtor, ele serve para colocar mensagem especifica na hora de dar throw new excecao(mensagem especifica daquele caso);
//