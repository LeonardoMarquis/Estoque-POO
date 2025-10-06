package leonardomarquis.estoqueComProdutoPerecivelExcecoes;

public class DadosInvalidos extends Exception{
    public DadosInvalidos(){
        super("Dados invalidos!");
    }

    public DadosInvalidos(String msg){
        super(msg);
    }
}


// funciona assim:
// as funcoes que tiverem caminhos que nao dao certo, eu uso um try: camiho certo e catch: chamo a excecao
//
//
//