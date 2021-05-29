/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jsf.controladores.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ASUS
 */
@Named("FileUpdloadView")
@ManagedBean

public class FileUploadView {
    
    private UploadedFile file = null;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void seleccionarArchivo(FileUploadEvent event) throws SQLException {
        if(event != null ){
            file = event.getFile();
            
            cargarArchivo();
        }
    }
    

    private void cargarArchivo() throws SQLException {
        
        try{
            if (file != null )
            {
                StringBuilder direccionServer = new StringBuilder();
                direccionServer.append("resources").append(File.separator).
                    append("upload").append(File.separator);
                    
                String FicheroAGuardar = FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath(direccionServer.toString())
                    + File.separator;
                
            
                if (!file.getFileName().equals("")) 
                {
                    File ficheroValidar = new File(FicheroAGuardar);
                    if (!ficheroValidar.exists()) 
                    {
                        ficheroValidar.mkdirs();
                    }
                
                    if (file != null )
                    {
                   
                        String rutaArchivo = getFile().getFileName();
                        String Result = java.util.UUID.randomUUID().toString();
                        
                        String codigoID = Result.substring(0, 6);
                        
                        try(OutputStream out = new FileOutputStream(
                        new File (FicheroAGuardar + codigoID + "-" + rutaArchivo)))
                        {
                            
                            int read= 0; 
                            
                            byte [] bytes = new byte [(int) getFile().getSize()];
                            InputStream in = getFile().getInputstream();
                            while ((read = in.read(bytes)) != -1){
                                out.write(bytes, 0, read);
                            }
                            getFile().getInputstream().close();
                            out.flush();
                            out.close();
                            file = null;
                        }catch(Exception e)
                        {
                            file = null;
                        }
                    }
                }
            }
            
        }
        catch (Exception e){
            file = null;
            System.out.println("ERROR"+e.getMessage());
        }
    }
    
   
    
}
