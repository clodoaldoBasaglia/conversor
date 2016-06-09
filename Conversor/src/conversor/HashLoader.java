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
        linguagem.put("inteiro", "int;");
        linguagem.put("mostra", "System.out.println;");
        linguagem.put("decimal", "double;");
        linguagem.put("booleano", "boolean;");
        linguagem.put("se", "if;");
        linguagem.put("senao", "else;");
        linguagem.put("programa", "public class;");
        linguagem.put("inicio", "{;");
        linguagem.put("fim", "};");
        linguagem.put("fim.", "};");

        String info = "";
        for (Map.Entry<String, String> entry : linguagem.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String mapa = key + "~" + value;
            info += mapa;
        }
        info = info.trim();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("linguagem.txt"), "utf-8"))) {
            writer.write(info);
        } catch (IOException ex) {
            System.out.println("deu ruim");
        }
        return null;
    }

    public HashMap<String, String> loadHash() throws IOException {
        FileUtils fu = new FileUtils();
        String conteudoDaHash = fu.abrirArquivoHash("linguagem.txt");
        linguagem.clear();
//        if (linguagem.isEmpty()) {
//            System.out.println("sim");
//        } else {
//            System.out.println("não");
//        }
        String[] splitLinhas = conteudoDaHash.split(";");
        String[] traducao;
//        System.out.println(splitLinhas.length);
        for (int i = 0; i < splitLinhas.length - 1; i++) {
            traducao = splitLinhas[i].split("~");
//            System.out.println(traducao.length + " " + traducao[0]);
            linguagem.put(traducao[0], traducao[1]);
        }
        return linguagem;
    }

}
