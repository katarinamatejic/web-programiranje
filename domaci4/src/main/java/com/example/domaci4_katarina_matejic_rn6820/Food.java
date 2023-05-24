package com.example.domaci4_katarina_matejic_rn6820;

public class Food {
    String day;
    String food;
    int order=0;

    public Food(String day, String food) {
        this.day = day;
        this.food = food;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
