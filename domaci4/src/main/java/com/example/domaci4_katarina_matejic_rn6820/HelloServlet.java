package com.example.domaci4_katarina_matejic_rn6820;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private List<String> days=new ArrayList<>();
    private List<Food> meals=new ArrayList<>();
    private Map<String, List<Food>> order=new HashMap<>();



    public void init() {
     days.add("Ponedeljak");
     days.add("Utorak");
     days.add("Sreda");
     days.add("Cetvrtak");
     days.add("Petak");
     getServletContext().setAttribute("days", days);
     for (String day :days){
         initMenu(day);
     }
    }

    private void initMenu(String day){
        File daysMenu = new File("/Users/katarinamatejic/Desktop/domaci4_katarina_matejic_rn6820/src/test/resources/"+day+".txt");
        try{
            Scanner sc = new Scanner(daysMenu);
            while (sc.hasNextLine()){
                String food = sc.nextLine();
                Food meal = new Food(day,food);
                meals.add(meal);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("service method");
        super.service(req, resp);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        order = (Map<String, List<Food>>) getServletContext().getAttribute("order");
        // da li postoji order, da li je trenutni korisnik ostavio  order i  da li mu je zapamcena porudzbina
        if (order != null && order.get(req.getSession().getId()) != null && order.get(req.getSession().getId()).size() != 0) {
            out.println("<h3>" + "Naruceno" + "</h3>");
            List<Food> it = order.get(req.getSession().getId());
            //ako je stavio order i izlistace se svi dani i porudzbina za svaki dan
            for (Food meal : it) {
                out.println("<br>");
                out.println(meal.getDay());
                out.println("<br>");
                out.println("<br>");
                out.println(meal.getFood());
                out.println("<br>");
                out.println("<br>");

            }
            return;
        }
        //ako nije jos odredio porudzbinu
        getServletContext().setAttribute("meals", meals);
        out.println("<html><body><form method=\"POST\" action = \"hello-servlet\">");    //kad se pritisne dugme u toj formi, postuje se na hello serlet podaci iz forme
        out.println("<h1>" + "Choose your lunch" + "</h1>");
        for (String day: days) {
            out.println("<h3>" + day + "</h3>");
            out.println("<select name = \""+ day + "\" id=\"" + day + "\">");
            System.out.println("<select name = \" "+ day + "\" id=\"" + day+ "\">" );
            for (Food meal: meals) {
                if (meal.getDay().equals(day)) {
                    out.println("<option value = \"" + meal.getFood() + "\" selected>" + meal.getFood() + "</option>");
                }
            }
            out.println("</select><br>");

        }
        out.println("<br><button type=\"submit\" name\"submit\"> Confirm </button></form>");
        out.println("</body></html>");



    }
//kad confirm on odatle uzme i za svaki dan iscupa koji obrok sam uzela za taj dan
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Food>it=new ArrayList<>();
        for (String day: days) {
            String food = req.getParameter(day);
            System.out.println(food);
            Food m = returnMeal(day, food);
            it.add(m);
        }
        order= (Map<String, List<Food>>) getServletContext().getAttribute("order");
        if(order==null)order=new HashMap<>();
        order.put(req.getSession().getId(),it);
        getServletContext().setAttribute("order",order);
        resp.sendRedirect("/response.html");
    }
    private Food returnMeal(String day, String meal){
        for (Food food : meals) {
            if (food.getDay().equals(day) && food.getFood().equals(meal)) {
                food.setOrder(food.order + 1);
                getServletContext().setAttribute("meals",meals);
                return food;
            }
        }
        return null;
    }

    public void destroy() {
    }
}