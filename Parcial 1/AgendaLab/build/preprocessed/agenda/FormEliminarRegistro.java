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
public class FormEliminarRegistro extends Form implements CommandListener{
    
    private Command commandSalir,commandAceptar;
    private Principal principal;
    private TextField registroEliminar;
    private StringItem itemText;
    private Alert alert;
    
    public FormEliminarRegistro(String title,Principal principal) {
        super(title);
        this.principal=principal;
        onLoadComponentesForm();
    }
        
    private void  onLoadComponentesForm(){
        itemText=new StringItem("","Eliminacion de registros");
        registroEliminar=new TextField("Coloque el registro a eliminar: ","",30,TextField.ANY);
        commandSalir=new Command("Salir",Command.EXIT,1);
        commandAceptar=new Command("Aceptar",Command.OK,1);
        this.append(itemText);
        this.append(registroEliminar);
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
      if(c==commandSalir)
            this.principal.setMenuPrincipal(this);
    
      if(c==commandAceptar){
         CRUDBinary persistencia = new CRUDBinary();
         String indexRegistro=registroEliminar.getString();
         
         if(!indexRegistro.equals("")){
             
            persistencia.abrirPersistencia("Contactos");
            int indexEliminacion=Integer.parseInt(indexRegistro);
             
            if(persistencia.eliminarRegistro(indexEliminacion)){
              alert=new Alert("Nota", "Contacto Eliminado",null,AlertType.CONFIRMATION);
              alert.setTimeout(5000);
              principal.getDisplay().setCurrent(this.alert,this);
            }else
                System.out.println("Problema al eliminar el contacto \n");
              
            persistencia.cerrarPersistencia();
         }else{
                alert=new Alert("Nota", "El campo no puede estar vacio",null,AlertType.CONFIRMATION);
                alert.setTimeout(4000);
                principal.getDisplay().setCurrent(this.alert,this);
         }   
      }
    }
    
}

