/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.services.navigation;
import java.awt.EventQueue;


/**
 *
 * @author Alexander M S
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(() -> navigation.showLogin(null));
    }
    
}
