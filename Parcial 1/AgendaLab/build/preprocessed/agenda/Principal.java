/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import javax.microedition.midlet.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.*;

/**
 * @author emili
 */
public class Principal extends MIDlet implements CommandListener {
    
    private Display display;
    private List menu;
    private String[] titulos;
    private StringItem  itemString;
    private String contactosAlmacenados;

    public Principal() {
        contactosAlmacenados="";
        display = Display.getDisplay(this);
    }
    
    public void startApp() {
        
        titulos=new String[4];
        titulos[0]="Nuevo Contacto";
        titulos[1]="Modificar Contacto";
        titulos[2]="Mostrar Contactos";
        titulos[3]="Eliminar Contacto";
        
        menu = new List("Opciones",List.IMPLICIT,titulos,null);
        menu.setCommandListener(this);
        display.setCurrent(menu);
    }
    
    public void pauseApp() {
        this.display.setCurrent(menu);
    }
    
    public void destroyApp(boolean unconditional) {
    
    }

    public void commandAction(Command c, Displayable d) {
       if(d==menu){
           switch(menu.getSelectedIndex()){
               case 0:
                   this.display.setCurrent(new FormAddRegistro(this,"Registro de contacto"));
                   break;
               case 1:
                   this.display.setCurrent(new FormModificarRegistro(this,"Modificar contacto"));
                   break;
               case 2:
                   this.display.setCurrent(new FormRegistros(this,"Mostrar Contactos"));
                   break;
               case 3:
                   this.display.setCurrent(new FormEliminarRegistro("Eliminar Registro",this));
                   break;
           }
       }
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
    
    public void setMenuPrincipal(Form formActual){
        this.display.setCurrent(menu);
        formActual=null;
    }
    
    public String getContactosAlmacenados() {
        return contactosAlmacenados;
    }

    public void setContactosAlmacenados(String contactosAlmacenados) {
        this.contactosAlmacenados += contactosAlmacenados;
    }
}