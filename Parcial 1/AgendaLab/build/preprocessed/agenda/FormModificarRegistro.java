/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreNotOpenException;


/**
 *
 * @author luisGerardo
 */
public class FormModificarRegistro extends Form implements CommandListener{

    private Command commandSalir,commandAceptar;
    private Principal principal;
    private TextField nombre,correo,telefono,registroModificar;
    private DateField fechaNacimiento;
    private StringItem itemText;
    private Alert alert;
    
    public FormModificarRegistro(Principal principal,String title) {
        super(title);
        this.principal=principal;
        onLoadComponentesForm();
    }
    
    private void  onLoadComponentesForm(){
        itemText=new StringItem("","Modificacion de registros");
        this.nombre=new TextField("Nombre: ","",30,TextField.ANY);
        this.correo=new TextField("Correo: ","",30,TextField.ANY);
        this.telefono=new TextField("Telefono: ","",30,TextField.ANY);
        this.fechaNacimiento=new DateField("Fecha nacimiento: ",DateField.DATE);
        this.registroModificar=new TextField("Registro a modificar: ","",30,TextField.ANY);
        
        commandSalir=new Command("Salir",Command.EXIT,1);
        commandAceptar=new Command("Aceptar",Command.OK,1);
        
        this.append(itemText);
        this.append(nombre);
        this.append(correo);
        this.append(telefono);
        this.append(fechaNacimiento);
        this.append(registroModificar);
        
        this.addCommand(commandSalir);
        this.addCommand(commandAceptar);
        itemText= new StringItem("",getRegistrosPersistencia());
        this.append(itemText);
        
        this.setCommandListener(this);
    }
    
    private String getRegistrosPersistencia(){ 
        CRUDBinary persistencia =new CRUDBinary();
        if(persistencia.abrirPersistencia("Contactos")){  
            String bufferRegistro;
            try {
                bufferRegistro = persistencia.mostrarRegistros();
                return bufferRegistro;
              
            } catch (RecordStoreNotOpenException ex) {
                ex.printStackTrace();
            } 
            persistencia.cerrarPersistencia();   
        }
        return null;
    }
    
    public void commandAction(Command c, Displayable d) {
        if(c==this.commandSalir)
            this.principal.setMenuPrincipal(this);
        
        if(c==this.commandAceptar){
            CRUDBinary persistencia= new CRUDBinary();
            if(persistencia.abrirPersistencia("Contactos")){
                
                ByteArrayOutputStream ByteWriteStream= new ByteArrayOutputStream();
                DataOutputStream dataWriteStream = new DataOutputStream(ByteWriteStream);
                
                try {
                    dataWriteStream.writeUTF(this.nombre.getString()+"/");
                    dataWriteStream.writeUTF(this.correo.getString()+"/");
                    dataWriteStream.writeUTF(this.telefono.getString()+"/");
                    dataWriteStream.writeUTF(this.fechaNacimiento.getDate().toString());
                    int index=Integer.parseInt(this.registroModificar.getString());
                            
                    if(persistencia.modificarRegistro(index,ByteWriteStream)){
                        alert=new Alert("Archivo", "El Contacto fue Modificado",null,AlertType.CONFIRMATION);
                        alert.setTimeout(5000);
                        principal.getDisplay().setCurrent(this.alert,this);
                        persistencia.cerrarPersistencia();
                    }else
                        System.out.println("Error al guardar el registro en la persistencia");   
                } catch (Exception e) {
                    System.out.println("Error al crear el registro-> verifique: "+e.getMessage());
                }
            }
        }
    }
    
    
}

