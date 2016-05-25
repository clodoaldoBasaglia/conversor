/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author clodoaldo
 */
public class FileUtils {

    public boolean SalvaArquivo(String caminho, String info) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(caminho + ".dod"), "utf-8"))) {
            writer.write(info);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    

}
