/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agendatarea;

import java.io.*;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
/**
 *
 * @author JanetNaibi
 */
public class Forma extends Form implements CommandListener {
    Command g, s;
    Principal p; 
    TextField nom, mail, tel;
    ChoiceGroup Sexo, Hobbies;
    DateField Fecha;
    Image i;
    ImageItem Imagen;
    
    public Forma(Principal mid){
        super("Agenda");
        p = mid;
        String selsexo[] = {"Masculino","Femenino"};
        String selhobbie[] = {"Nadar","Correr","Bailar","Jugar"};
        
        nom = new TextField("Nombre", "", 30, TextField.ANY);
        mail = new TextField("Correo", "", 30, TextField.ANY);
        tel = new TextField("Teléfono", "", 30, TextField.ANY);
        s = new Command("Salir", Command.EXIT, 1);
        g = new Command("Guardar", Command.EXIT, 1);
        Sexo = new ChoiceGroup("Sexo",ChoiceGroup.EXCLUSIVE,selsexo,null);
        Hobbies = new ChoiceGroup("Hobbies",ChoiceGroup.EXCLUSIVE,selhobbie,null);
        Fecha = new DateField("Fecha",DateField.DATE);

        try {
        Imagen = new ImageItem ("", Image.createImage("/stitch.png"), ImageItem.LAYOUT_CENTER, "stitch");
        } 
        catch (IOException e) {
        }
        
        append(Imagen);
        append(nom);
        append(mail);
        append(tel);
        append(Sexo);
        append(Hobbies);
        append(Fecha);
        addCommand(s);
        addCommand(g);
        setCommandListener(this);
    } 
    
    public void commandAction(Command co, Displayable di) {
        if(co == s){
            p.destroyApp(true);
        }
        else{
            if(co == g){
                String nota = "";
                int selSexo = Sexo.getSelectedIndex();
                int selHobbie = Hobbies.getSelectedIndex();
                
                nota += "Nombre:" + nom.getString() + "\n";
                nota += "Correo:" + mail.getString() + "\n";
                nota += "Teléfono:" + tel.getString() + "\n";
                nota += "Sexo:" + Sexo.getString(selSexo) + "\n";
                nota += "Hobbies:" + Hobbies.getString(selHobbie) + "\n";
                nota += "Fecha:" + Fecha.getDate()+ "\n";
                
                p.d.setCurrent(new Alert("","Registro guardado: " + nota, null, AlertType.ERROR));
            }
        }
    }
}
