package com.recipe;

import java.io.*;
import java.util.ArrayList;

public class Utilities {
    public <T> UtilityCodes appendToFile(ArrayList<T> list)
    {
        /*
            na osnovu tipa, automatski odredi file path.
            takodje, svaka klasa ce da ima svoj toString method, pomocu kojeg ces da
            parsiras tekst.
         */

        String filePath;

        if(list.get(0) instanceof Recipe)
            filePath = "com/recipe/recipes";
        else
            filePath = "com/recipe/groceries";

        try(FileWriter writer = new FileWriter(filePath, false))
        {
            StringBuilder sb = new StringBuilder();
            for(T item : list)
            {
                sb.append(item.toString());
            }

            String content = sb.toString();

            writer.write(content);

            return UtilityCodes.OK;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return UtilityCodes.APPEND_ERROR;
        }
    }

    public <T> UtilityCodes loadFromFile(ArrayList<T> list, UtilityCodes type)
    {
        String filePath;
        if(type == UtilityCodes.RECIPE)
            filePath = "com/recipe/recipes";
        else
            filePath = "com/recipe/groceries";

        try
        {
           FileReader fileReader = new FileReader(filePath);
           BufferedReader br = new BufferedReader(fileReader);

           String line;
           while((line = br.readLine()) != null)
           {
               String[] tokens = line.split(",");
               ArrayList<Pair> ingredients = new ArrayList<>();
               String name = tokens[0];
               int defaultNoPortions = Integer.parseInt(tokens[1]);

               for(int i = 2; i < tokens.length; i+=2)
               {
                   ingredients.add(new Pair(new Groceries(tokens[i]), Integer.parseInt(tokens[i+1])));
               }

               String manualPath = "com/recipe/manuals/" + name;
               String manual = Utilities.loadManual(manualPath);

               list.add(new Recipe());
           }

           return UtilityCodes.OK;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return UtilityCodes.READ_ERROR;
        }
    }

    private static String loadManual(String manualPath)
    {
        try
        {
            FileReader fr = new FileReader(manualPath);
            BufferedReader br = new BufferedReader(fr);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
