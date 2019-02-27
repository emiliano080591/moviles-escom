/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author emili
 */
public class examen extends MIDlet implements CommandListener{
    private Display d;
    private Command cs;
    private Canvas ca;
    public examen(){
        d = Display.getDisplay(this);
        ca = new Canvas() {
            private int w;
            private int h;              

            protected void paint(Graphics g) {
                w = getWidth();
                h = getHeight();
                int radio=w/2;
		int x=0;
		int y=0;
		int grado=45;
		int longitud=w/2;
                        
                g.setColor(255, 255, 255);
                g.fillRect(0, 0, w, h);
                g.setColor(0, 0, 187);
                g.fillRect(0, 0, w, w);
                g.setColor(255, 0, 0);
                g.fillArc(0, 0, w, w, 0, 360);
                
                for(int i=0; i<5;i++){
                    y=seno(grado,longitud);
                    x=coseno(grado,longitud);
                    try{
                        Thread.sleep(1000);
                        g.setColor(0, 0, 187);
                        g.fillRect((w/2)-x, (w/2)-y, x*2, y*2);
                        g.setColor(255, 0, 0);
                        g.fillArc((w/2)-x, (w/2)-y, x*2, y*2, 0, 360);
                        longitud=x;
                    }catch(InterruptedException ex){}
                    
                }//fin de for
                
                
                
                                             
            }//fin de paint
        };//fin de canvas
        cs = new Command("Salir", Command.EXIT, 3);
        ca.addCommand(cs);
        ca.setCommandListener(this);
        
    }//fin del constructor
    public void startApp() {
        d.setCurrent(ca);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int seno(int grado,int longitud){
        int x;
	x=(int)(longitud*Math.sin(grado* Math.PI / 180));
	return x;
    }

    public int coseno(int grado,int longitud){
	int y;
	y=(int)(longitud*Math.cos(grado* Math.PI / 180));
	return y;
    }
}
