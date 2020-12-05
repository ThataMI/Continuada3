package br.com.bandtec.projetocontinuada3.controladores;


import br.com.bandtec.projetocontinuada3.dominios.GravarArquivo;
import br.com.bandtec.projetocontinuada3.dominios.Produto;
import br.com.bandtec.projetocontinuada3.obj.FilaObj;
import br.com.bandtec.projetocontinuada3.obj.PilhaObj;
import br.com.bandtec.projetocontinuada3.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    PilhaObj<Produto> pilha = new PilhaObj<>(100);
    List<Produto> lista = new ArrayList<Produto>();
    FilaObj<Produto>  fila = new FilaObj<>(100);

    @GetMapping("/verifica")
    public ResponseEntity verificar() {
        List<Produto> produto = repository.findAll();

        return produto.isEmpty() ? noContent().build() : ok(produto);
    }

    @PostMapping("/add")
    public ResponseEntity enfileirarReq(@RequestBody Produto addProduto){
        UUID identificador = UUID.randomUUID();
        addProduto.setUu(identificador);
        fila.insert(addProduto);
        pilha.push(addProduto);
        return ResponseEntity.ok().body(identificador);
    }

    @Scheduled(fixedRate = 10000)
    public void conferir() {
        if (!fila.isEmpty()) {
            repository.save(fila.poll());
            System.out.println("item adicionado");
            lista.add(fila.poll());
        }
    }

    @DeleteMapping("desfazerReq")
    public ResponseEntity desfazerReq(){
        if(!pilha.isEmpty()) {
            repository.delete(pilha.pop());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/consulta/{identificador}")
    public ResponseEntity consulta(@PathVariable UUID identificador){
        for (Produto p : lista) {
            if (p.getUu().equals(identificador)) {
                lista.remove(0);
                return ResponseEntity.ok().body("Produto adicionado");
            }
        }
        return ResponseEntity.badRequest().body("Aguarde");


    }


    @PostMapping("/arquivo")
    public ResponseEntity enviar(@RequestParam("arquivo") MultipartFile arquivo) throws IOException {


        if (arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo não enviado!");
        }

        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());

        byte[] conteudo = arquivo.getBytes();


        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        String nomeDoArquivo = "produto.txt";

        List<Produto>produtos =  GravarArquivo.gravarArquivo(nomeDoArquivo);

        for (Produto p : produtos){

            repository.save(p);

        }

        return ResponseEntity.created(null).build();
    }


}
