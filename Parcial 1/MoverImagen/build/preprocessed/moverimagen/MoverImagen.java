/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moverimagen;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author JanetNaibi
 */
public class MoverImagen extends MIDlet {
    private Imagen img;
    private Display d;
    public MoverImagen() {
        d = Display.getDisplay(this);
        img = new Imagen(this);
    }
    public void startApp() {
        d.setCurrent(img);
    }
    public void pauseApp() {    }
    public void destroyApp(boolean b) {    }
    public void salir() {
        destroyApp(false);
        notifyDestroyed();
    }
}
