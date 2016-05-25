/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.IOException;

/**
 *
 * @author clodoaldo
 */
public class Controller {

    public Controller() {

    }

    void salva(String caminho, String conteudo) throws IOException {
        System.out.println(conteudo);
        System.out.println(caminho);
        String codeInJava = transformeToJava(conteudo);
        FileUtils fu = new FileUtils();
        System.out.println(fu.SalvaArquivo(caminho, conteudo));
    }

    private String transformeToJava(String conteudo) {
        System.out.println(conteudo);
        
        return conteudo;
    }

}
