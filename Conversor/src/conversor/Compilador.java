/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author a968692
 */
public class Compilador {

    Controller ct = new Controller();

    public void run(String arquivo) throws IOException, InterruptedException {
        // OLHAR ISSO URGENTE http://stackoverflow.com/questions/10125639/how-to-create-a-jar-file-using-the-terminal
        System.out.println(arquivo);
        String caminho = arquivo.substring(0, arquivo.indexOf("."));
        System.out.println(caminho);
        File arq = new File(caminho);
        System.out.println(arq.isFile() ? "sim " : "não");/*
        String comando = "java " + caminho;
        Process processo = Runtime.getRuntime().exec(comando);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(processo.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        processo.waitFor();
         */
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
//        System.out.println(arq.isFile());

    }
}
