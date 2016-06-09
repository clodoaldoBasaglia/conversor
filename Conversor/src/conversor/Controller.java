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
import java.util.ArrayList;
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
        ArrayList<String> arrayLinhas = new ArrayList<String>();
        ArrayList<String> arrayVariaveis = new ArrayList<String>();
        ArrayList<String> splitVariaveis = new ArrayList<String>();
        HashLoader hl = new HashLoader();
        HashMap<String, String> hashLoaded = hl.loadHash();
        conteudo = conteudo.trim();
        String[] vetor = conteudo.split("\n");
        String corpo = "";
        String tab = "    ";
        String psvm = "public static void main(String[] args){ \n";
        for (int i = 0; i < vetor.length; i++) {
            arrayLinhas.add(vetor[i].toString());
        }
        int pos = arrayLinhas.indexOf("variaveis:");
        int fim = arrayLinhas.indexOf("fimvar;");
        System.out.println("Inicio: " + pos + "Fim: " + fim);
        pos++;
        for (int i = pos; i < fim; i++) {
            arrayVariaveis.add(arrayLinhas.get(i));
        }
        String identificacao = arrayLinhas.get(0);
        String[] splitIdentificacao = identificacao.split(" ");
        System.out.println(splitIdentificacao[0]);
        corpo += hashLoaded.get(splitIdentificacao[0].trim());
        corpo += " " + splitIdentificacao[1].replace(";", "{ \n");
        corpo += tab + psvm;

        for (String var : arrayVariaveis) {
            String[] split = var.split(" ");
            if (split.length == 2) {
                System.out.println(split[0].trim());
                corpo += "\t" + hashLoaded.get(split[0].trim()) + " " + split[1].trim() + "\n";
            }
        }
        for (int i = fim + 2; i < arrayLinhas.size(); i++) {
            arrayLinhas.set(i, arrayLinhas.get(i).replaceAll("inicio", "{"));
            arrayLinhas.set(i, arrayLinhas.get(i).replaceAll("fim.", "}"));
            arrayLinhas.set(i, arrayLinhas.get(i).replaceAll("fim", "}"));
        }
        for (int i = fim + 1; i < arrayLinhas.size(); i++) {
            arrayLinhas.set(i, arrayLinhas.get(i).replace("inicio", ""));
        }

        for (int i = fim + 1; i < arrayLinhas.size(); i++) {
            corpo += arrayLinhas.get(i) + "\n";
        }
        //substitui mostra
        corpo += corpo.replaceAll("mostra", hashLoaded.get("mostra"));
        return corpo;
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

    private String concatenaVariaveis(String conteudo, int var) {
//        System.out.println(conteudo.charAt(var));
        int cont = 0;
        int auxiliar = conteudo.indexOf(":");
        int contagem = auxiliar + conteudo.indexOf(";");
        String intervalo = conteudo.substring(auxiliar, contagem);
        intervalo = intervalo.trim();
//        System.out.println(intervalo);
        while (!intervalo.trim().equalsIgnoreCase("fim;")) {

            intervalo = conteudo.substring(auxiliar, contagem);
            intervalo = intervalo.trim();
            auxiliar = contagem;
            contagem = auxiliar + conteudo.indexOf(";");
            System.out.println(intervalo + " " + cont);
            cont++;
        }

        return "";
    }
}
