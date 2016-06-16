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

/**
 *
 * @author a968692
 */
public class Compilador {

    Controller ct = new Controller();

    public void run(String arquivo) throws IOException, InterruptedException {
        // OLHAR ISSO URGENTE http://stackoverflow.com/questions/10125639/how-to-create-a-jar-file-using-the-terminal
        String nomeClasse = arquivo.substring(arquivo.lastIndexOf("/") + 1, arquivo.indexOf("."));
        criaManifest(nomeClasse);
        String comandoCriaJar = "jar cfm " + nomeClasse + ".jar Manifest.txt " + nomeClasse + ".class";
        Process processo = Runtime.getRuntime().exec(comandoCriaJar);
        BufferedReader readerJar
                = new BufferedReader(new InputStreamReader(processo.getInputStream()));
        String linha = "";
        while ((linha = readerJar.readLine()) != null) {
            System.out.println(linha + "\n");
        }
        processo.destroy();
        String comandoRodaJar = "java -jar " + nomeClasse + ".jar";
        Process processoRoda = Runtime.getRuntime().exec(comandoRodaJar);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(processoRoda.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        processoRoda.waitFor();
    }

    public void compilaAndRun() {
    }

    void compila(String arquivo) throws IOException, InterruptedException {
        File arq = new File(arquivo);
        System.out.println(arq.isFile());
        if (arq.isFile()) {
            String comando = "javac " + arq;
            Process processo = Runtime.getRuntime().exec(comando);
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(processo.getInputStream()));

            String line = "";
            System.out.println("compilando...");
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
            processo.waitFor();
            System.out.println("ok");
        } else {
            System.out.println("Selecione um arquivo.");
        }

    }

    private void criaManifest(String nomeClasse) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("Manifest.txt"), "utf-8"))) {
            writer.write("Main-class: " + nomeClasse + "\n");
        } catch (IOException ex) {
            System.out.println("Erro ao criar o Manifest");
        }
    }
}
