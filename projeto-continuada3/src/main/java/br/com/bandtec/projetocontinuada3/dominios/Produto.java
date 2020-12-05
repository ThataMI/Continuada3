package br.com.bandtec.projetocontinuada3.dominios;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    public Integer idProduto;

    public String nome;

    public String tipoProduto;

    public Double preco;

    public UUID uu;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public UUID getUu() {
        return uu;
    }

    public void setUu(UUID uu) {
        this.uu = uu;
    }
}
