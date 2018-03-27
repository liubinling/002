/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservice_framework.newboncproject;

import java.awt.EventQueue;

/**
 *
 * @author ur
 */
public class NewBoncProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new SettingsDialog_1().setVisible(true);
            }
        });
    }
    
}
