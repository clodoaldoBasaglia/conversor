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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author a968692
 */
public class HashLoader {

    HashMap<String, String> linguagem = new HashMap<>();

    public HashLoader() {

    }

    public HashMap<String, String> hashCreator() {
        linguagem.put("mostra", "System.out.println(" + ");");
        linguagem.put("inteiro", "int");
        linguagem.put("decimal", "double");
        linguagem.put("booleano", "boolean");
        linguagem.put("se", "if");
        linguagem.put("senão", "else");
        linguagem.put("programa", "public class ");
        linguagem.put("inicio", "{");
        linguagem.put("fim.", "}");
        File palavras = new File("linguagem.txt");
        String info = "";
        for (Map.Entry<String, String> entry : linguagem.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String mapa = key + ":" + value + "$$";
            info += mapa;
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("linguagem.txt"), "utf-8"))) {
            writer.write(info);
        } catch (IOException ex) {
            System.out.println("deu ruim");
        }
        return null;
    }

}
