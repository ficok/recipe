package com.recipe;

import com.recipe.Groceries;

public class Pair {
    private Groceries ingredient;
    private Integer amountNeeded;

    public Pair(Groceries ingredient, Integer amountNeeded)
    {
        this.ingredient = ingredient;
        this.amountNeeded = amountNeeded;
    }

    @Override
    public String toString()
    {
        return ingredient.getName() + "," +
               amountNeeded;
    }

    public Groceries getIngredient() {
        return ingredient;
    }

    public Integer getAmountNeeded() {
        return amountNeeded;
    }
}
