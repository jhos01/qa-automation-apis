package com.qaxpert.restAssured;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class MiFicha {
    public static void main(String[] args) {
        //Save variables
        String name = "Jhoselin";
        int age = 29;
        boolean StudyingAutomationOfApis = true;
        ArrayList<String> hobbies = new ArrayList<String>();
        hobbies.add("PlayVolleyball");
        hobbies.add("ReadArticles");
        hobbies.add("Running");
        //Show information in the screen
        System.out.println(name);
        System.out.println(age);
        System.out.println(StudyingAutomationOfApis);
        System.out.println(hobbies);
        //Print each type of variable
        System.out.println( name.getClass().getSimpleName());
        System.out.println(hobbies.getClass().getSimpleName());
        //Ask the user her favorite hobbie and included it to the list
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cual es tu hobbie favorito? ");
        String newHobbie = scanner.nextLine();
        hobbies.add(newHobbie);
        //Display # of hobbies
        System.out.println("Number of hobbies listed: "+ hobbies.size());
        //Change age + 1
        age = age + 1;
        System.out.println("New age: "+ age);









        





    }
}
