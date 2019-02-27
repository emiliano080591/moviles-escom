/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;

/**
 * @author emili
 */
public class Cuadro extends Canvas implements CommandListener {
    private int w;
    private int h; 
    private int n;
    
    public Cuadro(int num) {
        n=num-1;
        try {
        // Set up this canvas to listen to command events
        setCommandListener(this);
        // Add the Exit command
        addCommand(new Command("Exit", Command.EXIT, 1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    } 

    public void paint(Graphics g) {
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
                
                for(int i=0; i<n;i++){
                    y=seno(grado,longitud);
                    x=coseno(grado,longitud);
                    g.setColor(0, 0, 187);
                    g.fillRect((w/2)-x, (w/2)-y, x*2, y*2);
                    g.setColor(255, 0, 0);
                    g.fillArc((w/2)-x, (w/2)-y, x*2, y*2, 0, 360);
                    longitud=x;   
                }//fin de for
                
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
