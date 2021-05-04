package com.example.UrlopyProjekt.servlets;

import com.example.UrlopyProjekt.DBUtil.DBUtilPracownik;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/PracownikServlet")
public class PracownikServlet extends HttpServlet {

    private DataSource dataSource;
    private DBUtilPracownik dbUtil;

    public PracownikServlet() {
        // Obtain our environment naming context
        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/urlopy_web_app");


        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        try {

            String command = request.getParameter("command");

            if(command==null)
                command="login";

            switch(command) {
                case "register":
                    register(request, response);
                    break;

                default:


                    response.setContentType("text/html");

                    String name = request.getParameter("loginInput");
                    System.out.println("przeczytalem ze login to: " + name);
                    String password = request.getParameter("passwordInput");

                    System.out.println("przeczytalem ze haslo to: " + password);

                    try {
                        boolean flag = verify(name, password);
                        System.out.println("czy weryfikacja przeszla pomyślnie? :" + flag);
                        if (flag) {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("main-pracownik.jsp");
                            String nazwisko = dbUtil.getNazwisko();
                            System.out.println("pana nazwisko:" + nazwisko);
                            // dodanie listy do obiektu zadania
                            request.setAttribute("nazwisko", nazwisko);

                            dispatcher.forward(request, response);
                            System.out.println("wyslano response do main-pracownik.jsp");
                        } else {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/logowanie-pracownik.html");
                            dispatcher.include(request, response);

                        }
                    } catch (Exception e) {

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
                        dispatcher.include(request, response);
                    }
            }
        }catch (Exception e){
            throw new ServletException(e);
        }

    }

    private void register(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("rejestracja");

        response.setContentType("text/html");

        String name = request.getParameter("loginInput");
        System.out.println("przeczytalem ze login to: " + name);
        String password = request.getParameter("passwordInput");
        String password2 = request.getParameter("passwordInput2");
        int rok = Integer.parseInt(request.getParameter("yearInput"));



    }

    private boolean verify(String name, String password) {

        return dbUtil.verify(name, password);
    }

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            dbUtil = new DBUtilPracownik(dataSource);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
