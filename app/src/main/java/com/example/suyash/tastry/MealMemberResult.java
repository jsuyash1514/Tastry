package com.example.suyash.tastry;

/**
 * Created by Suyash on 27-12-2017.
 */

public class MealMemberResult {
    public String foodOption, number;

    public MealMemberResult(){

    }

    public MealMemberResult(String foodOption, String number) {
        this.foodOption = foodOption;
        this.number = number;
    }

    public String getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(String foodOption) {
        this.foodOption = foodOption;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
