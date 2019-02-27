/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author JanetNaibi
 */
public class Animacion extends MIDlet {
    Display d;
    AnimaBola ab;
    AnimaImagen ai;

    public void startApp() {
        d = Display.getDisplay(this);
        //ab = new AnimaBola();
        //d.setCurrent(ab);
        ai = new AnimaImagen();
        d.setCurrent(ai);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}