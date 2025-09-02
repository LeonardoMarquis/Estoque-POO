package leonardomarquis.estoque;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




public class Estoque{
    private ArrayList<Produto> produtos;

    public Estoque(){
        this.produtos = new ArrayList<>();
    }


    public void incluir(Produto p){
        for (Produto produto : produtos){       // é como o "for i in lista" do python!, so que dizendo o tipo da variavel que estara dentro do vetor a percorer
            if (produto.getCod() == p.getCod()){
                System.out.print("Ja existe um produto com este codigo!");
                return;
            }
        }
        produtos.add(p);
    }


    public void comprar(int cod, int quant, double preco){  // no caso esse é o comprar para o estoque
        for(Produto produto : produtos){
            if(produto.getCod() == cod){  // o restante das verificacoes estao no metodo compra do produto
                produto.compra(quant, preco);
                return;
            }
        }
    }

    public double vender(int cod, int quant){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                double valorTotalVenda = produto.vender(quant);
                if (valorTotalVenda >= 0){
                    return valorTotalVenda;
                }
            }
        }
        return -1;
    }
    public int quantidade(int cod){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                return produto.getQuantidade();
            }
        }
        return -1;
    }
    public String movimentacao(int cod, Date inicio, Date fim){
        StringBuilder movimentacoes = new StringBuilder();      // para nao ter que ficar concatenando as movimentacoes com +
        long inicTime = inicio.getTime();   // vai retornar em milisegundos desde a epoch time que e 01 01 1970
        long fimTime = fim.getTime();
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                for(String movimen : produto.getMovimentacoes()){
                    String[]  each = movimen.split("; ", 2);
                    long movTime = Long.parseLong(each[0]);         // o each 0 tem o tempo, o each 1 tem o desc da operacao

                    if (movTime >= inicTime && movTime <= fimTime ){    // para pegar no tempo pedido, entre tal tempo e tal
                        //aqui e para formatar  data em forma correta para mostrar la
                        Date dt1 = new Date(movTime);
                        SimpleDateFormat simpdtformat = new SimpleDateFormat("dd/MM/yyyy");
                        String dt1NewForm = simpdtformat.format(dt1);
                        movimentacoes.append(dt1NewForm).append("- ").append(each[1]).append("\n");
                    }
                }

            }
        }
        return movimentacoes.toString();
    }

    public ArrayList<Fornecedor> fornecedores(int cod){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                return produto.getFornecedores().isEmpty() ? null : produto.getFornecedores();
            }
        }
        return null;
    }


    public ArrayList<Produto> estoqueAbaixoDoMinimo(){
        ArrayList<Produto> comEstoqueAbaixo = new ArrayList<>();
        for(Produto produto : produtos){
            if(produto.estoqueAbaixoDoMinimo()){
                comEstoqueAbaixo.add(produto);
            }
        }
        return comEstoqueAbaixo;
    }
    public void adicionarFornecedor(int cod, Fornecedor f){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                produto.addFornecedores(f);
                return;
            }
        }
    }
    public double precoDeVenda(int cod){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                return produto.getPrecoVenda();
            }
        }
        return -1;
    }
    public double precoDeCompra(int cod){
        for(Produto produto : produtos){
            if(produto.getCod() == cod){
                return produto.getPrecoCompra();
            }
        }
        return -1;
    }

}

