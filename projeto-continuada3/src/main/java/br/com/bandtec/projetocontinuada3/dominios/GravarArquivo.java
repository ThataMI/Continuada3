package br.com.bandtec.projetocontinuada3.dominios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GravarArquivo {

    public static List<Produto> gravarArquivo(String nomeDoArquivo) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nome, tipoProduto;
        int idProduto;
        double preco;
        int contRegistro=0;

        List<Produto> listar = new ArrayList<Produto>();


        try {
            entrada = new BufferedReader(new FileReader(nomeDoArquivo));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {

            registro = entrada.readLine();

            while (registro != null) {

                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println( registro.substring(3, 28));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println(registro.substring(3,47));
                }
                else if (tipoRegistro.equals("02")) {
                    Produto produto = new Produto();
                    idProduto = Integer.parseInt(registro.substring(3,5));
                    nome = registro.substring(6,16);
                    tipoProduto = registro.substring(17,21);
                    preco = Double.parseDouble(registro.substring(22,27).replace(',','.'));

                    produto.setIdProduto(idProduto);
                    produto.setNome(nome);
                    produto.setTipoProduto(tipoProduto);
                    produto.setPreco(preco);

                    listar.add(produto);

                    System.out.printf("%-5s %-8s %-10s %5.2f \n", idProduto,nome,tipoProduto,preco);
                    contRegistro++;
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                registro = entrada.readLine();
            }

            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
        return listar;

    }

    public static void main(String[] args) {
        String nomeDoArquivo = "produto.txt";
        gravarArquivo(nomeDoArquivo);


    }

}
