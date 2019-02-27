/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreNotOpenException;
/**
 *
 * @author emili
 */
public class FormRegistros extends Form implements CommandListener{
    
    private StringItem titleForm;
    private StringItem registros;
    private Command comandoSalir;
    private Principal principal;
    
    public FormRegistros(Principal principal,String title) {
        super(title);
        this.principal=principal;
        titleForm= new StringItem("","Registro de usuarios:\n ");
        comandoSalir=new Command("Salir",Command.EXIT,1);
        
        this.append(titleForm);
        this.setRegistrosPersistencia();
        this.addCommand(comandoSalir);
        this.setCommandListener(this);
    }
    
    private void setRegistrosPersistencia(){
        
        CRUDBinary persistencia =new CRUDBinary(principal);
        if(persistencia.abrirPersistencia("Contactos")){  
            String bufferRegistro;
            try {
                bufferRegistro = persistencia.mostrarRegistros();
                registros=new StringItem("",bufferRegistro);
                this.append(registros);
                
            } catch (RecordStoreNotOpenException ex) {
                ex.printStackTrace();
            } 
            persistencia.cerrarPersistencia();   
        }
    }
    
    public void commandAction(Command c, Displayable d) {
        if(c==this.comandoSalir){
            this.principal.setMenuPrincipal(this);
        }
    }
    
}

