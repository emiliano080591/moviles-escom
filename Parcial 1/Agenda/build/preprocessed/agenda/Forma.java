/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.*;
import javax.microedition.lcdui.*;


/**
 *
 * @author JanetNaibi
 */

public class Forma extends Form implements CommandListener {
    Command g, s;
    TextField nom, ape, tel, cel, dom;
    Principal p; 
    Alert a;
    
    public Forma(Principal mid){
        super("Agenda");
        p = mid;
        nom = new TextField("Nombre", "", 30, TextField.ANY);
        ape = new TextField("Apellido", "", 30, TextField.ANY);
        tel = new TextField("Teléfono", "", 30, TextField.ANY);
        cel = new TextField("Celular", "", 30, TextField.ANY);
        dom = new TextField("Domicilio", "", 30, TextField.ANY);
        s = new Command("Salir", Command.EXIT, 1);
        g = new Command("Guardar", Command.EXIT, 1);
        append(nom);
        append(ape);
        append(tel);
        append(cel);
        append(dom);
        addCommand(s);
        addCommand(g);
        setCommandListener(this);
    }
    
    public void commandAction(Command co, Displayable di){
        if(co == s){
            p.destroyApp(true);
        }
        else{
            if(co == g){
                RMSOps rmso = new RMSOps();
                rmso.abrir("ZonaJavaZone");
                try{
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(baos);
                    dos.writeUTF(nom.getString());
                    dos.writeUTF(ape.getString());
                    dos.writeUTF(tel.getString());
                    dos.writeUTF(cel.getString());
                    dos.writeUTF(dom.getString());
                    dos.close();
                    rmso.adicionarRegistro(baos);
                    rmso.cerrar();
                }
                catch(IOException ioe){
                    ioe.printStackTrace();
                }
                a = new Alert("NOTA", "Guardado", null, AlertType.CONFIRMATION);
                a.setTimeout(5000);
                p.d.setCurrent(a, this);
                nom.setString("");
                ape.setString("");
                tel.setString("");
                cel.setString("");
                dom.setString("");
            }
        }
    }
}