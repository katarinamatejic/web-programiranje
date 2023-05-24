package com.example.domaci4_katarina_matejic_rn6820;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

@WebServlet(name = "list" , value = "/list")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String password = req.getParameter("password");
        Scanner scanner = new Scanner(new File("/Users/katarinamatejic/Desktop/domaci4_katarina_matejic_rn6820/src/test/resources/password.txt"));
        boolean flag= password.equals(scanner.nextLine());
        if(flag){
            out.println("<html><body><form method=\"POST\" action = \"list?password=sifra4\">");
            out.println("<h1>" + "Meals" + "</h1>");
            out.println("<html><body>");
            java.util.List<Food> meals = (java.util.List<Food>) getServletContext().getAttribute("meals");
            java.util.List<String> days = (java.util.List<String>) getServletContext().getAttribute("days");
            for(String day: days) {
                out.println("<h1>" + day + "</h1>");
                out.println("<h1></h1>");
                out.println("<table style=\"width:100 % \">");
                out.println("<tr> <th>#</th> <th>Meal</th> <th>Quantity</th> </tr>");
                int j = 0;
                for (Food meal : meals) {
                    if (meal.getDay().equals(day)) {
                        j++;
                        out.println("<tr> <th>" + j + "</th> <th>" + meal.getFood() + "</th> <th>" + meal.getOrder() + "</th> </tr>");
                    }
                }
                out.println("<style>\n" +
                        "table, th, td {\n" +
                        "  border: 1px solid black;\n" +
                        "}\n" +
                        "</style></table>\n");
                out.println("<h1></h1>");
        }


            out.println("<br><input type=\"submit\" name\"submit\" value\"Clear\"/></form>");
            out.println("</body></html>");
        }
        else {
            out.println("<h3>" +"password error" + "</h3>");
        }
        scanner.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        java.util.List<Food> meals = (java.util.List<Food>) getServletContext().getAttribute("meals");
        Map<String, java.util.List<Food>> map = (Map<String, java.util.List<Food>>) getServletContext().getAttribute("order");
        for (Map.Entry<String, java.util.List<Food>> m: map.entrySet()){
            m.setValue(null);
        }
        for (Food meal: meals) {
            meal.setOrder(0);
        }
        getServletContext().setAttribute("order", map);
        getServletContext().setAttribute("meals", meals);
    }

    }

