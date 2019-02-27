/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senoidal;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.*;

/**
 * @author emili
 */
public class Espiral extends MIDlet implements CommandListener {

    private List menu;
    private Command salir; 
    private String[] titulos;
    private Display display;
    
    public Espiral() {
        titulos=new String[2];  
        titulos[0]="Canvas - Onda senoidal";
        titulos[1]="Canvass - Espiral";
        display=Display.getDisplay(this);
    }
    
    public void startApp() {
        menu = new List("Opciones",List.IMPLICIT,titulos,null);
        salir= new Command("salir",Command.EXIT,1);   
        menu.addCommand(salir);
        menu.setCommandListener(this);
        display.setCurrent(menu);
    }
    
    public void pauseApp() {
        display.setCurrent(menu);
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        
        if(d==menu){
            switch(menu.getSelectedIndex()){
                case 0:
                    System.out.println("Dibujar senoidal");
                    display.setCurrent(this.dibujarFuncionSenoidal());
                    break;
                case 1:
                    System.out.println("Dibujar espiral");
                    display.setCurrent(this.dibujarEspiral());
                    break;
                    
                default:
                    System.out.println("No se encontro el item seleccionado");
                    break;
            }
        }
    }
    
    private Canvas dibujarFuncionSenoidal(){
        return new Canvas() {
            private int width;
            private int height;
            
            protected void paint(Graphics g){       
                width=this.getWidth();
                height=this.getHeight();
                g.setColor(255, 255, 255);
                g.fillRect(0, 0, width, height);
                g.setColor(0, 0, 187);
                g.drawLine(width/2, 0, width/2, height);
                g.drawLine(0, height/2, width, height/2);
                //////////////Creacion de la onda senoidal/////////////////////
                ///////////////////////////////////////////////////////////////
                g.setColor(171, 0, 0);
                g.setStrokeStyle(Graphics.SOLID);
                for(double t=-8.0;t<8.0;t+=0.01){
                    double punto = 130+t*20;
                    double yImagen = 110-Math.toDegrees(Math.sin(t));
                    g.drawLine((int)punto,(int)yImagen,(int)punto+1, (int)yImagen+1);
                }
            }
        };
    }
    
    
    private Canvas dibujarEspiral(){
        
        return new Canvas() {
            private double origenx;
            private double origeny;
            
            private int width;
            private int heigth;
            
            
            protected void paint(Graphics g) {
                origenx=120.0;
                origeny=160.0;
                width=this.getWidth();
                heigth=this.getHeight();
                
                ///////////////FONDO DEL ESPIRAL////////////////////////////
                g.setColor(255, 255, 255);
                g.fillRect(0, 0, width, heigth);
                
                //////////////Dibujando Espiral/////////////////////////////
                ////////////////////////////////////////////////////////////
                g.setColor(0, 0, 185);
                g.setStrokeStyle(Graphics.SOLID);
                
                double widthArc=10.0;
                double heithghArc=10.0;
                int incrementWidth=10;
                
                g.drawArc((int)origenx, (int)(origeny+=5), (int)widthArc, (int)heithghArc,0,180);
                
                for(int ciclo=2;ciclo<23;ciclo++){
                    widthArc=widthArc+incrementWidth;
                    heithghArc=heithghArc+incrementWidth;
                    
                    if(ciclo==2){
                         g.drawArc((int)(origenx), (int)(origeny), (int)widthArc, (int)heithghArc,-180,180);
                         origenx+=(incrementWidth*ciclo);
                         continue;
                    }
                    if(ciclo%2==0){
                        g.drawArc((int)(origenx), (int)(origeny-=5), (int)widthArc, (int)heithghArc,-180,180);
                        origenx+=incrementWidth*ciclo;
                    }         
                    else{
                        origenx-=incrementWidth*ciclo;
                        g.drawArc((int)(origenx), (int)(origeny-=5), (int)widthArc, (int)heithghArc,0,180);
                    }
                }
            }
        };
    }
}
