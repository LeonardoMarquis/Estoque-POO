package leonardomarquis.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;


public class Estoque implements InterfaceEstoque{
    private ArrayList<Produto> produtos;

    public Estoque(){
        this.produtos = new ArrayList<>();
    }


    public boolean incluir(Produto p) {
        if (p.getCod() <= 0 || p.getDesc() == null || p.getDesc().isEmpty() || p.getMin() <= 0) {
            return false;
        }
        for (Produto produto : produtos) {
            if (produto.getCod() == p.getCod()) {
                return false;
            }
        }
        produtos.add(p);
        return true;
    }

    public boolean comprar(int cod, int quant, double preco, Date val) {
        for (Produto produto : produtos) {
            if (produto.getCod() == cod) {
                if (produto instanceof ProdutoPerecivel && val == null) {
                    return false;
                }
                produto.compra(quant, preco, val);
                return true;
            }
        }
        return false;
    }

    public double vender(int cod, int quant) {
        if (quant <= 0) {
            return -1;
        }
        for (Produto produto : produtos) {
            if (produto.getCod() == cod && produto instanceof ProdutoPerecivel) {
                return ((ProdutoPerecivel) produto).vender(quant);
            }
            else { // Adicionar após o if para ProdutoPerecivel
                if (produto.getQuantidade() >= quant) {
                    double valor = quant * produto.getPrecoVenda();
                    produto.setQuantidade(produto.getQuantidade() - quant);
                    return valor;
                }
                return -1;
            }
        }
        return -1;
    }

    public Produto pesquisar(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCod() == cod) {
                return produto;
            }
        }
        return null;
    }

    public ArrayList<Produto> estoqueAbaixoDoMinimo() {
        ArrayList<Produto> abaixoMinimo = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.estoqueAbaixoDoMinimo()) {
                abaixoMinimo.add(produto);
            }
        }
        return abaixoMinimo;
    }

    public ArrayList<Produto> estoqueVencido() {
        ArrayList<Produto> vencidos = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto instanceof ProdutoPerecivel && ((ProdutoPerecivel) produto).quantidadeVencidos() > 0) {
                vencidos.add(produto);
            }
        }
        return vencidos;
    }

    public int quantidadeVencidos(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCod() == cod && produto instanceof ProdutoPerecivel) {
                return ((ProdutoPerecivel) produto).quantidadeVencidos();
            }
        }
        return 0;
    }

    public void adicionarFornecedor(int cod, Fornecedor f) {
        for (Produto produto : produtos) {
            if (produto.getCod() == cod) {
                produto.adicionarFornecedor(f);
                return;
            }
        }
    }

    public double precoDeVenda(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCod() == cod) {
                return produto.getPrecoVenda();
            }
        }
        return -1;
    }

    public double precoDeCompra(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCod() == cod) {
                return produto.getPrecoCompra();
            }
        }
        return -1;
    }


}

