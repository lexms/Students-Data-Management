/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.services;

import io.frames.*;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class navigation {
    public static void showLogin(JFrame frame){
        if(frame != null){
            frame.dispose();
        }
        new frameLogin().setVisible(true);
        
    }
    public static void showMainMenu(JFrame frame){
        frame.dispose();
        new frameMainMenu().setVisible(true);
    }
    
    public static void showRegister(JFrame frame){
        frame.dispose();
        new frameRegister().setVisible(true);
    }
    
    public static void showSimulation(JFrame frame){
        frame.dispose();
        new frameSimulation().setVisible(true);
    }
    
    
}
