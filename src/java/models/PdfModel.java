/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;

/**
 *
 * @author Abdur Rahman
 */
public class PdfModel implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        String fn = request.getParameter("fname");
        String ln = request.getParameter("lname");
        System.out.println(fn);
        System.out.println(ln);
        try {
            Document doc = new Document();
            doc.setPageSize(PageSize.A4);
            doc.setMargins(36, 72, 108, 180);
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(new File("/home/suhaib/itextDemo.pdf")));
            doc.open();
            doc.add(new Paragraph(fn));
            doc.add(new Paragraph(ln));
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
