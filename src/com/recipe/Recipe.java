package com.recipe;

import java.util.ArrayList;
import java.util.Collections;

public class Recipe implements Comparable<Recipe> {
    private String name;
    private ArrayList<Pair> ingredientList;
    private ArrayList<Pair> missingIngredientList;
    private int defaultNoPortions;
    private String manual;

    public Recipe(String name, ArrayList<Pair> ingredients, int defaultNoPortions, String manual)
    {
        this.name = name;
        this.ingredientList = ingredients;
        this.defaultNoPortions = defaultNoPortions;
        this.missingIngredientList = new ArrayList<>();

        /*
        * 1. pokusaj da otvoris manual file (ima isto ime kao sto je ime recepta)
        *   - ako ne moze da otvori fajl, zaustavi trenutnu radnju
        * 2. ucitaj tekst iz njega u string manual
        * 3. zatvori fajl
        */
    }
    public UtilityCodes isMakeable(ArrayList<Groceries> availableGroceries)
    {
        int portions = this.numMakeablePortions(availableGroceries);
        return portions != 0 ? UtilityCodes.MAKEABLE :
                               UtilityCodes.NOT_MAKEABLE;
    }
    public int numMakeablePortions(ArrayList<Groceries> availableGroceries)
    {
        int makeablePortions = 0;
        for(Pair ingredient : ingredientList)
        {
            int currentGroceryIndex = availableGroceries.indexOf(ingredient.getIngredient());
            if(currentGroceryIndex == -1)
            {
                System.out.println("missing " + ingredient.getAmountNeeded() + " amounts of " + ingredient.getIngredient().getName());
                missingIngredientList.add(ingredient);
                makeablePortions = 0;
                continue;
            }

            Groceries currentGrocery = availableGroceries.get(currentGroceryIndex);

            if(currentGrocery.getAmount() < ingredient.getAmountNeeded())
            {
                System.out.println("missing " + (ingredient.getAmountNeeded() - currentGrocery.getAmount())+ " amounts of " + ingredient.getIngredient().getName() + ".");
                missingIngredientList.add(ingredient);
                makeablePortions = 0;
            }
            else
            {
                makeablePortions = Integer.min(makeablePortions, (currentGrocery.getAmount() / ingredient.getAmountNeeded()));
            }
        }
        return makeablePortions;
    }

    public UtilityCodes addNewRecipe(ArrayList<Recipe> recipes)
    {
        Collections.sort(recipes);

        return UtilityCodes.OK;
    }

    @Override
    public int compareTo(Recipe other)
    {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString()
    {
        return this.name + "," +
               this.defaultNoPortions + "," +
               this.getIngredientList().toString() + "\n";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Pair> getIngredientList()
    {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Pair> ingredientList)
    {
        this.ingredientList = ingredientList;
    }

    public int getDefaultNoPortions()
    {
        return defaultNoPortions;
    }

    public void setDefaultNoPortions(int defaultNoPortions)
    {
        this.defaultNoPortions = defaultNoPortions;
    }
}