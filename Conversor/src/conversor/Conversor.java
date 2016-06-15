/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

/**
 *
 * @author clodoaldo
 */
public class Conversor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashLoader hl = new HashLoader();
        hl.hashCreator();
//        new SplashScreen().setVisible(true);
        new jFrameTela().setVisible(true);
    }

}
