/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pae_proyectofinal;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author william
 */
public class Caminante2 implements Runnable{

    PanelSnake2 panelSnake;
    boolean estado = true;

    public Caminante2(PanelSnake2 panel){
        this.panelSnake=panel;
    }

    @Override
    public void run() {
        while(true){
            panelSnake.avanzar();
            panelSnake.repaint();
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                Logger.getLogger(Caminante.class.getName()).log(Level.SEVERE, null, e);

            }
        }
    }
    
    public void parar(){
        this.estado=false;
    }
    
}
