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

    public void compila() {

    }

    public void run(String arquivo) throws IOException, InterruptedException {
        File arq = new File(arquivo);
        System.out.println(arq.isFile());
        String comando = "python "+arq;
        Process processo = Runtime.getRuntime().exec(comando);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(processo.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        processo.waitFor();
    }

    public void compilaAndRun() {
    }
}
