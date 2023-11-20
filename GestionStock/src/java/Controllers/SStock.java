/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Article;
import Models.EntreeStock;
import Models.SortieStock;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Ny Aina Ratolo
 */
public class SStock extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SStock</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SStock at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        Date dateSortie = Date.valueOf(request.getParameter("dateSortie"));
        int idArticle = Integer.parseInt(request.getParameter("article"));
        double qte = Double.parseDouble(request.getParameter("quantite"));
        String idmagasin = request.getParameter("magasin");
        
        try{
        
            Article art = new Article();
            int modeSortie = art.estFIFOouLIFO(idArticle);
            
            if(modeSortie == 1){
                try{
                    EntreeStock es = new EntreeStock();
                    ArrayList<EntreeStock> lses = new ArrayList<EntreeStock>();
                    lses = es.getListeEntreeStockParArticleFIFODispo(idArticle);
                    SortieStock ss = new SortieStock();
                    ss.etapeSortie(lses, qte, dateSortie, idArticle, idmagasin);
                }
                catch(Exception e){e.printStackTrace();}
                response.sendRedirect("SortieStock.jsp");
            }
            else if(modeSortie == -1){
                try{
                    EntreeStock es = new EntreeStock();
                    ArrayList<EntreeStock> lses = new ArrayList<EntreeStock>();
                    lses = es.getListeEntreeStockParArticleLIFODispo(idArticle);
                    SortieStock ss = new SortieStock();
                    ss.etapeSortie(lses, qte, dateSortie, idArticle, idmagasin);
                }
                catch(Exception e){e.printStackTrace();}
                response.sendRedirect("SortieStock.jsp");
            }
            
        }
        catch(Exception e){ e.printStackTrace();}
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
