/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;

/**
 *
 * @author emili
 */
public class FormAddRegistro extends Form implements CommandListener{

    private Principal principal;
    private TextField nombre,correo,telefono;
    private DateField fechaNacimiento;
    private Command comandoGuardar,comandoSalir;
    private CRUDBinary register;
    private Alert alert;
    
    public FormAddRegistro(Principal principal,String tituloForm) {
        super(tituloForm);
        this.principal=principal;
        this.nombre=new TextField("Nombre: ","",30,TextField.ANY);
        this.correo=new TextField("Correo: ","",30,TextField.ANY);
        this.telefono=new TextField("Telefono: ","",30,TextField.ANY);
        this.fechaNacimiento=new DateField("Fecha nacimiento: ",DateField.DATE);
        
        this.comandoGuardar= new Command("Guardar",Command.EXIT,1);
        this.comandoSalir = new Command("Salir",Command.EXIT,1);
        
        this.append(nombre);
        this.append(correo);
        this.append(telefono);
        this.append(fechaNacimiento);
        
        this.addCommand(comandoSalir);
        this.addCommand(comandoGuardar);
        this.setCommandListener(this);
    }

    public void commandAction(Command comando, Displayable d) {
        
        if(comando==this.comandoSalir){
            this.principal.setMenuPrincipal(this);
        }
        
        if(comando==this.comandoGuardar){
            CRUDBinary persistencia= new CRUDBinary();
            if(persistencia.abrirPersistencia("Contactos")){
                
                ByteArrayOutputStream ByteWriteStream= new ByteArrayOutputStream();
                DataOutputStream dataWriteStream = new DataOutputStream(ByteWriteStream);
                
                try {
                    dataWriteStream.writeUTF(this.nombre.getString()+"/");
                    dataWriteStream.writeUTF(this.correo.getString()+"/");
                    dataWriteStream.writeUTF(this.telefono.getString()+"/");
                    dataWriteStream.writeUTF(this.fechaNacimiento.getDate().toString());
                   
                    int idRegistro=persistencia.agregarRegistro(ByteWriteStream);
                    if(idRegistro!=-1){
                        alert=new Alert("Nota", "Contacto Guardado",null,AlertType.CONFIRMATION);
                        alert.setTimeout(5000);
                        principal.getDisplay().setCurrent(this.alert,this);
                        persistencia.cerrarPersistencia();
                    }else
                        System.out.println("Error al guardar el registro en la persistencia");
                    
                } catch (Exception e) {
                    System.out.println("Error al crear el registro-> verifique: "+e.getMessage());
                }
            }else
                System.out.println("No se  logro crear la persistecia del dispositivo");
        }
    }
    
    
    
}
