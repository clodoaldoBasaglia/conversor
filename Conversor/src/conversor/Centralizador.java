/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author clodoaldo
 */
public class Centralizador {

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
//            System.out.println(arrayLinhas.get(i));
            arrayVariaveis.add(arrayLinhas.get(i));
        }
        String identificacao = arrayLinhas.get(0);
        String[] splitIdentificacao = identificacao.split(" ");
//        System.out.println(splitIdentificacao[0]);
        corpo += hashLoaded.get(splitIdentificacao[0].trim());
        corpo += " " + splitIdentificacao[1].replace(";", "{ \n");
        corpo += tab + psvm;
        corpo = corpo.replaceAll("mostra", hashLoaded.get("mostra"));
        for (String variaveis : arrayVariaveis) {
//            System.out.println(variaveis);
            String[] split = variaveis.trim().split(" ");
//            System.out.println(hashLoaded.get(split[0].trim()) + " " + split[1].trim());
            corpo = corpo.concat("\t" + hashLoaded.get(split[0].trim()) + " " + split[1].trim());
        }
        System.out.println(corpo);
       
        //substitui mostra

        return corpo;
    }

}
