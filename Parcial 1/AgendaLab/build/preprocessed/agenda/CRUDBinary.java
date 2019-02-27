/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.ByteArrayOutputStream;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;


/**
 *
 * @author emili
 */
public class CRUDBinary {
    
    private RecordStore persistencia;
    private Principal principal;
    
    public CRUDBinary(RecordStore persistencia) {
        this.persistencia = persistencia;
    }
    
    public CRUDBinary(Principal principal) {
        this.principal=principal;
    }

    public CRUDBinary() {
        this.persistencia=null;
        this.principal=null;
    }
    
    public boolean abrirPersistencia(String nombreContacto){
        try {
            persistencia=RecordStore.openRecordStore(nombreContacto, true);
            return true;
        } catch (RecordStoreException e) {
            e.toString();
            return false;
        }
    }
    
    public int agregarRegistro(ByteArrayOutputStream baos){
        byte[] mensaje;
        mensaje = baos.toByteArray();
        try {
            return persistencia.addRecord(mensaje, 0, mensaje.length);
        } catch (RecordStoreException e) {
            e.toString();
            return -1;
        }
    }
    
    public boolean eliminarRegistro(int index){
        try {
            persistencia.deleteRecord(index);
            return true;
        } catch (InvalidRecordIDException ex) {
            ex.printStackTrace();
            return false;
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public String  mostrarRegistros() throws RecordStoreNotOpenException{
        String buffer="";
        byte[] registro = new byte[100];
        int registroPrincipal=0;
        
        RecordEnumeration enumerator=persistencia.enumerateRecords(null, null, true);
        try {
            registroPrincipal=enumerator.previousRecordId();
            enumerator.reset();
        } catch (InvalidRecordIDException ex) {
            ex.printStackTrace();
        }
        
        while(enumerator.hasNextElement()){
            try {
                byte [] dato = enumerator.nextRecord();
                buffer +="\nid: "+(registroPrincipal++)+" ->"+new String(dato,0,dato.length);
            } catch (RecordStoreException ex) {
                ex.printStackTrace();
            }
        }
        return buffer;    
    }
    
    public boolean cerrarPersistencia(){
        try {
            persistencia.closeRecordStore();
            return true;
        } catch (Exception e) {
            e.toString();
            return false;
        }
    }
    
    public boolean modificarRegistro(int idRecord,ByteArrayOutputStream baos){
      byte[] mensaje;
        mensaje = baos.toByteArray();
        try {
            persistencia.setRecord(idRecord,mensaje, 0, mensaje.length);
            return true;
        } catch (RecordStoreException e) {
            e.toString();
            return false;
        }
    }
    
    
}

