package com.recipe;

public class Groceries implements Comparable<Groceries> {
    private String name;
    private int amount;

    public Groceries(String name, int amount)
    {
        this.name = name;
        this.amount = amount;
    }

    public Groceries(String name)
    {
        this(name, 0);
    }

    @Override
    public int compareTo(Groceries other)
    {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString()
    {
        return name + "," +
               amount + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
