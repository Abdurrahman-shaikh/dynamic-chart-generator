/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;

/**
 *
 * @author Abdur Rahman
 */
public class FileUploadModel implements Model{
    
    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        try{
            InputStream fileContent = null;
            final Part filePart = request.getPart("file");
            final String fileName = getFileName(filePart);
            OutputStream out = new FileOutputStream(new File("/home/suhaib"+File.separator+fileName));
            fileContent = filePart.getInputStream();
            int read;
            final byte[] bytes = new byte[1024];
            while( (read=fileContent.read(bytes)) != -1){
                out.write(bytes, 0, read);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private String getFileName(final Part part){
        for(String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf("=")+1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
}
