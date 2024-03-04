/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;

/**
 *
 * @author Abdur Rahman
 */
public interface Model {
    void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt);
}
