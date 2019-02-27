/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package triangulo;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.util.Random;

/**
 * @author emili
 */
public class Triangulo extends MIDlet implements CommandListener{
    private Display d;
    private Command cs;
    private Canvas ca;
    private int puntoP[] = new int[2];
    private int prueba = 0;

    public Triangulo() {
        d = Display.getDisplay(this);
        ca = new Canvas() {
            private int w;
            private int h;
            private int x1,y1;
            private int x2,y2;
            private int x3,y3;
            private int contador = 0;
            private int aleatorio;                

            protected void paint(Graphics g) {
                w = getWidth();
                h = getHeight();
                g.setColor(255, 255, 255);
                g.fillRect(0, 0, w, h);
                
                //Coordenadas de los vertices
                x1 = w/4; y1 = 3*h/4;
                x2 = 3*w/4; y2 = 3*h/4;
                x3 = w/2; y3 = (3*h/4)-104;
                
                g.setColor(0, 0, 187);
                g.drawRect(w/4, 3*h/4, 1, 1);       //Vertice 1
                g.drawRect(3*w/4, 3*h/4, 1, 1);     //Vertice 2
                g.drawRect(w/2, (3*h/4)-104, 1, 1); //Vertice 3
                
                //Pintando el primer pixel fuera del triangulo
                puntoP[0] = 10;
                puntoP[1] = 10;
                g.setColor(255, 0, 0);
                g.drawRect(puntoP[0], puntoP[1], 1, 1);
                
                Random r = new Random();                
                //Bucle de pintar pixeles
                while(contador < 100000){
                //for(contador = 0; contador < 100000; contador++){                    
                    aleatorio = r.nextInt(3)+1;                    
                    switch (aleatorio){ 
                        case 1:
                            //Calcula el punto medio entre un vertice y un punto, lo guarda en puntoP[]
                            punto_medio(x1, y1);
                            g.drawRect(puntoP[0], puntoP[1], 1, 1);
                            break;
                            
                        case 2:
                            punto_medio(x2, y2);
                            g.drawRect(puntoP[0], puntoP[1], 1, 1);
                            break;
                            
                        case 3:
                            punto_medio(x3, y3);
                            g.drawRect(puntoP[0], puntoP[1], 1, 1);
                            break;
                        
                        default:
                            System.out.println("Numero aleatorio fuera de rango");
                            break;
                    }
                    contador++;                    
                }                                
            }
        };
        cs = new Command("Salir", Command.EXIT, 3);
        ca.addCommand(cs);
        ca.setCommandListener(this);
    }        
    
    public void punto_medio(int x, int y){
        int xm, ym;
        xm = (x + puntoP[0]) / 2;
        ym = (y + puntoP[1]) / 2;
        puntoP[0] = xm;
        puntoP[1] = ym;
        prueba++;
    }
    public void startApp() {
        d.setCurrent(ca);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command co, Displayable di) {
        if (co == cs) {
            destroyApp(true);
            notifyDestroyed();
        }else
            d.setCurrent(new Alert("", "Otro comando digitado...", null, AlertType.ERROR));
    }
}
