/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author JanetNaibi
 */
public class Principal extends MIDlet implements CommandListener {
    Display d;
    Forma f;
    public Principal() {
        d = Display.getDisplay(this);
        f = new Forma(this);
    }
    
    public void startApp() {
        d.setCurrent(f);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean x) {
    }
    
    public void commandAction(Command co, Displayable di){
    }
}







