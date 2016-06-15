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
        fu.SalvaArquivo(caminho, conteudo);
        fu.SalvaArquivoEmJava(caminho, codeInJava);
    }

    public String transformeToJava(String conteudo) throws IOException {
        ArrayList<String> arrayLinhas = new ArrayList<String>();
        ArrayList<String> correcao = new ArrayList<String>();
        ArrayList<String> arrayVariaveis = new ArrayList<String>();
        ArrayList<String> splitVariaveis = new ArrayList<String>();
        HashLoader hl = new HashLoader();
        HashMap<String, String> hashLoaded = hl.loadHash();
        conteudo = conteudo.trim();
        String[] vetor = conteudo.split("\n");
        String corpo = "";
        String tab = "    ";
        String psvm = "public static void main(String[] args){ \n";

        for (String vetor1 : vetor) {
            arrayLinhas.add(vetor1);
        }
        int pos = arrayLinhas.indexOf("variaveis:");
        int fim = arrayLinhas.indexOf("fimvar;");
//        System.out.println("Inicio: " + pos + "Fim: " + fim);
        pos++;
        for (int i = pos; i < fim; i++) {
            System.out.println(arrayLinhas.get(i));
//            arrayVariaveis.add(arrayLinhas.get(i));
        }
        String identificacao = arrayLinhas.get(0);
        String[] splitIdentificacao = identificacao.split(" ");
//        System.out.println(splitIdentificacao[0]);
        corpo += hashLoaded.get(splitIdentificacao[0].trim());
        corpo += " " + splitIdentificacao[1].replace(";", "{ \n");
        corpo += tab + psvm;
        /* String whatTheHellHappened = "";
        for (String var : arrayVariaveis) {
            String[] split = var.split(" ");
            if (split.length == 2) {
//                System.out.println(split[0].trim());
                whatTheHellHappened += "\t" + hashLoaded.get(split[0].trim()) + " " + split[1].trim() + "\n";
            }
        }
        corpo += whatTheHellHappened;
        for (int i = fim + 2; i < arrayLinhas.size(); i++) {
            arrayLinhas.set(i, arrayLinhas.get(i).replaceAll("inicio", "{"));
            arrayLinhas.set(i, arrayLinhas.get(i).replaceAll("fim.", "}"));
            arrayLinhas.set(i, arrayLinhas.get(i).replaceAll("fim", "}"));
        }
        for(int i = 0; i < arrayLinhas.size();i++){
            System.out.println(arrayLinhas.get(i));
        }
        /*
        for (int i = fim + 1; i < arrayLinhas.size(); i++) {
            arrayLinhas.set(i, arrayLinhas.get(i).replace("inicio", ""));
        }
         
        String somethingIsWrongAndIdontKnowWhat = "";
        for (int i = fim + 1; i < arrayLinhas.size(); i++) {
            somethingIsWrongAndIdontKnowWhat += arrayLinhas.get(i) + "\n";
        }
        corpo += somethingIsWrongAndIdontKnowWhat; */
        //substitui mostra
        corpo += corpo.replaceAll("mostra", hashLoaded.get("mostra"));
        System.out.println(corpo);
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

}
