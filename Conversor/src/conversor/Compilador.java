/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author a968692
 */
public class Compilador {

    Controller ct = new Controller();
    ArrayList<String> saida = new ArrayList<String>();

    public ArrayList<String> getSaida() {
        return saida;
    }

    public void run(String arquivo) throws IOException, InterruptedException {
        String nomeClasse = arquivo.substring(arquivo.lastIndexOf("/") + 1, arquivo.lastIndexOf("."));
        String caminhoArquivo = arquivo.substring(0, arquivo.lastIndexOf("/") + 1);
        criaManifest(nomeClasse, caminhoArquivo);
        String comandoCriaJar = "jar cfm " + caminhoArquivo+nomeClasse + ".jar " + 
                caminhoArquivo+"Manifest.txt " + caminhoArquivo+ "*.class";
        System.out.println(comandoCriaJar);
        Process processo = Runtime.getRuntime().exec(comandoCriaJar);
        BufferedReader readerJar
                = new BufferedReader(new InputStreamReader(processo.getInputStream()));
        String linha = "";
        while ((linha = readerJar.readLine()) != null) {
            System.out.println();
            saida.add(linha + "\n");
        }
        processo.destroy();
        String comandoRodaJar = "java -jar " + caminhoArquivo + nomeClasse + ".jar";
        System.out.println(comandoRodaJar);
        Process ls = Runtime.getRuntime().exec("ls " + caminhoArquivo);
        BufferedReader r = new BufferedReader(new InputStreamReader(ls.getInputStream()));
        String out = "";
        while ((out = r.readLine()) != null) {
            saida.add(out + "\n");
        }
        Process processoRoda = Runtime.getRuntime().exec(comandoRodaJar);
        System.out.println("oi");
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(processoRoda.getErrorStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line + "\n");
            saida.add(line + "\n");
        }

        processoRoda.waitFor();
    }

    public void compilaAndRun() {
    }

    void compila(String arquivo) throws IOException, InterruptedException {
        File arq = new File(arquivo);
        if (arq.isFile()) {
            String comando = "javac " + arq;
            Process processo = Runtime.getRuntime().exec(comando);
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(processo.getInputStream()));

            String line = "";
            saida.add("compilando...");
            System.out.println("compilando...");
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
                saida.add(line + "\n");
            }
            processo.waitFor();
            saida.add("ok");
            System.out.println("ok");
        } else {
            System.out.println("Selecione um arquivo.");
        }

    }

    private void criaManifest(String nomeClasse, String caminhoArquivo) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(caminhoArquivo + "Manifest.txt"), "utf-8"))) {
            writer.write("Main-class: " + nomeClasse + "\n");
        } catch (IOException ex) {
            System.out.println("Erro ao criar o Manifest");
        }
    }
}

