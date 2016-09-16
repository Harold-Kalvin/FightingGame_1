package com.example.android.theapplication;

import java.util.ArrayList;

/**
 * Created by Harold BASA on 13/03/2016.
 */
public class CharacterList {
    private ArrayList<Character> charactersList = new ArrayList<>();

    public CharacterList(){
        //instantiation  of the characters
        Character georges = new Character("Georges" , 10,120, R.drawable.georges,R.drawable.georgesavatar,R.drawable.georgespunch1,R.drawable.georgespunch2);
        Character robert = new Character("Robert" , 5,200, R.drawable.robert,R.drawable.robertavatar,R.drawable.robertpunch1,R.drawable.robertpunch2);
        Character gustave = new Character("Gustave" , 8,150, R.drawable.gustave,R.drawable.gustaveavatar,R.drawable.gustavepunch1,R.drawable.gustavepunch2);
        Character ronda = new Character("Ronda" , 20,80, R.drawable.ronda,R.drawable.rondaavatar,R.drawable.rondapunch1,R.drawable.rondapunch2);
        //adding into the ArrayList, the different characters
        charactersList.add(georges);
        charactersList.add(robert);
        charactersList.add(gustave);
        charactersList.add(ronda);
    }
    public ArrayList<Character> getCharactersList ()
    {
        return charactersList;
    }
}
