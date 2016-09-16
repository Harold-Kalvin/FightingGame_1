package com.example.android.theapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterSelection1 extends AppCompatActivity {

    private static int indexArray = 0;
    private CharacterList charactersList = new CharacterList();
    //int of player
    private static int playerInt = 0;
    private static String difficulty = "EASY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init indexArray (otherwise a bug occures)
        indexArray = 0;
        playerInt = 0;
        //initialising the page
        changeDisplay();

        //creation of the character's selection buttons
        GridLayout layout = (GridLayout) findViewById(R.id.theLayout);
        for (int i = 0; i < charactersList.getCharactersList().size(); i++,indexArray++)
        {
            ImageButton myButton = new ImageButton(this);
            myButton.setImageResource(charactersList.getCharactersList().get(indexArray).getAvatar());
            layout.addView(myButton);
            //on click on the buttons => display the character's image and description
            myButton.setOnClickListener(new View.OnClickListener() {
                TextView text = (TextView)findViewById(R.id.descrip1Player);
                String descriptionCharacter = charactersList.getCharactersList().get(indexArray).toString();
                ImageView image1 = (ImageView)findViewById(R.id.image1Player);
                //create a variable int in which we pass the index of the Character from the ArrayList
                int playerIndex = indexArray;
                int theImage =charactersList.getCharactersList().get(indexArray).getImage();
                //on click on the button => changes text, image and current Index Character
                public void onClick(View view) {
                    text.setText(descriptionCharacter);
                    image1.setImageResource(theImage);
                    playerInt = playerIndex;
                }
            });
        }

        //getting intent from the main menu
        Intent intent = getIntent();

        Button but = (Button) findViewById(R.id.startButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CharacterSelection1.this, Battle1.class);

                //passing datas from characters into the Battle activity
                intent.putExtra("nom1", charactersList.getCharactersList().get(playerInt).getName());
                intent.putExtra("attackPower1", charactersList.getCharactersList().get(playerInt).getAttackPower());
                intent.putExtra("health1", charactersList.getCharactersList().get(playerInt).getHealthPoint());
                intent.putExtra("image1", charactersList.getCharactersList().get(playerInt).getImage());
                intent.putExtra("imagePunch1", charactersList.getCharactersList().get(playerInt).getImagePunch());
                intent.putExtra("difficulty", difficulty);
                startActivity(intent);
            }
        });
    }
    public void changeDisplay()
    {
        TextView text = (TextView)findViewById(R.id.descrip1Player);
        String descriptionCharacter = charactersList.getCharactersList().get(indexArray).toString();
        text.setText(descriptionCharacter);
        ImageView image1 = (ImageView)findViewById(R.id.image1Player);
        image1.setImageResource(charactersList.getCharactersList().get(indexArray).getImage());
        changeTextDifficulty();
    }
    public void nextDif(View v)
    {
        switch (difficulty) {
            case "EASY":
                difficulty = "MEDIUM";
                break;
            case "MEDIUM":
                difficulty = "HARD";
                break;
            case "HARD":
                difficulty = "EASY";
                break;
        }
        changeTextDifficulty();
    }
    public void previousDif(View v)
    {
        switch (difficulty) {
            case "EASY":
                difficulty = "HARD";
                break;
            case "MEDIUM":
                difficulty = "EASY";
                break;
            case "HARD":
                difficulty = "MEDIUM";
                break;
        }
        changeTextDifficulty();
    }
    public void changeTextDifficulty()
    {
        TextView textDif = (TextView)findViewById(R.id.textDifficulty);
        textDif.setText(difficulty);
    }
}
