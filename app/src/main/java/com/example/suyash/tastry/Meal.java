package com.example.suyash.tastry;

/**
 * Created by Suyash on 27-12-2017.
 */

public class Meal {
    public String meal;
    public boolean isSelected;

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Meal (){

    }

    public Meal(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Meal(String meal) {
        this.meal = meal;

    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
}
