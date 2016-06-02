/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author clodoaldo
 */
public class Controller {

    public Controller() {

    }

    void salva(String caminho, String conteudo) throws IOException {
//        System.out.println(conteudo);
//        System.out.println(caminho);
        String codeInJava = transformeToJava(conteudo);
        FileUtils fu = new FileUtils();
//        System.out.println(fu.SalvaArquivo(caminho, conteudo));
    }

    public String transformeToJava(String conteudo) throws IOException {
//        System.out.println(conteudo);
        HashLoader hl = new HashLoader();
        HashMap<String, String> hashLoaded = hl.loadHash();
//        System.out.println("tamanho da hash carregada " + hashLoaded.size());
        String linhaIdentificadora = conteudo.substring(0, conteudo.indexOf(";"));

        String[] identificacao = linhaIdentificadora.split(" ");
        identificacao[0] = identificacao[0].trim();
        String corpo = "";
        String cabecalho = hashLoaded.get(identificacao[0]) + " " + identificacao[1] + "{";
        corpo = cabecalho + "\n " + "public static void main(String[] args) " + "\n";
        int var = achaVariaveis(conteudo, linhaIdentificadora.length() + 1);
        corpo += "{";
        int aux = var + conteudo.indexOf(";");
        int pos = andaEspacoEmBranco(conteudo, aux);
        System.out.println(pos);

        String v = conteudo.substring(pos, pos + conteudo.indexOf("\n") - 1);
        System.out.println(v.trim());
        return conteudo;
    }

    String abreArquivo(String caminho) throws FileNotFoundException, IOException {
//        System.out.println(caminho);
        File arq = new File(caminho);
        String texto;
        String conteudo = "";
        BufferedReader br = new BufferedReader(new FileReader(arq));
        while ((texto = br.readLine()) != null) {
            conteudo += texto + "\n";
        }
        return conteudo;
    }

    private int achaVariaveis(String conteudo, int length) {
        int valor = length;
//        System.out.println(conteudo.charAt(valor));
        while (conteudo.charAt(valor) == '\n' || conteudo.charAt(valor) == ' ') {
            valor++;
        }
        valor = valor + 6;
        while (conteudo.charAt(valor) == '\n' || conteudo.charAt(valor) == ' ') {
            valor++;
        }
//        System.out.println(conteudo.charAt(valor));
//        System.out.println(valor);
        return valor - 1;
    }

    private int andaEspacoEmBranco(String conteudo, int aux) {
        while (conteudo.charAt(aux) == '\n' || conteudo.charAt(aux) == ' ') {
            aux++;
        }
        return aux;
    }
}
