package br.com.bandtec.projetocontinuada3.repositorios;

import br.com.bandtec.projetocontinuada3.dominios.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
