/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

/**
 * @author emili
 */
public class Inicio extends MIDlet implements CommandListener{
   private Display display;
   private Form form = new Form("Examen");
   private Command submit = new Command("Submit", Command.SCREEN, 1);
   private Command exit = new Command("Exit", Command.EXIT, 1);
   private TextField textfield = new TextField("Ingrese un numero:", "", 30, TextField.ANY);
   Cuadro cuadro;
   
   public Inicio(){
        
        display = Display.getDisplay(this);
        form.addCommand(exit);
        form.addCommand(submit);
        form.append(textfield);
        form.setCommandListener(this);
    }
    public void startApp() {
        display.setCurrent(form);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command c, Displayable d) {
        if (c == submit) {
            int num=Integer.parseInt(textfield.getString());
            cuadro=new Cuadro(num);
            Display.getDisplay(this).setCurrent(cuadro);
            form.removeCommand(submit);
          } else if (c == exit) {
            destroyApp(false);
            notifyDestroyed();
        }       
    }
}
