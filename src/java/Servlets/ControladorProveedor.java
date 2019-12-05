/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Gestion.GestionProveedor;
import Negocio.Producto;
import Negocio.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class ControladorProveedor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        Proveedor prove = new Proveedor();
        GestionProveedor gpre = new GestionProveedor();
        
        if (menu.equals("Proveedor")) 
        {
            switch (accion) 
            {
                case "Listar":
                    ArrayList<Proveedor> lista = gpre.getTodos();
                    System.out.println(lista);
                    
                    request.setAttribute("proveedores", lista);
                    break;
                    
                case "Agregar":
                    String id = request.getParameter("txtDni");
                    String Nombre = request.getParameter("txtNombres");
                    
                    prove = new Proveedor(id, Nombre);
                    
                    gpre.guardaProveedor(prove);
                   
                    request.getRequestDispatcher("ControladorProveedor?menu=Proveedor&accion=Listar").forward(request, response);
                    break;   
                default:                    
                    throw new AssertionError();
            }
            
            request.getRequestDispatcher("Proveedor.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}