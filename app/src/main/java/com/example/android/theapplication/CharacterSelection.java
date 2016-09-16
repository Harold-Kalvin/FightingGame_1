package com.example.android.theapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterSelection extends AppCompatActivity {

    private static int indexArray1 = 0;
    private static int indexArray2 = 0;
    private CharacterList charactersList = new CharacterList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //displaying the descriptions of the characters and the images
        changeDisplay1();
        changeDisplay2();

        //getting intent from the main menu
        Intent intent = getIntent();

        //creating an intent to pass to the Battle Activity
        Button but = (Button) findViewById(R.id.startButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CharacterSelection.this, Battle.class);
                Button but = (Button) findViewById(R.id.startButton);
                String message = but.getText().toString();

                //passing datas from characters into the Battle activity
                intent.putExtra("nom1", charactersList.getCharactersList().get(indexArray1).getName());
                intent.putExtra("nom2", charactersList.getCharactersList().get(indexArray2).getName());
                intent.putExtra("attackPower1",charactersList.getCharactersList().get(indexArray1).getAttackPower());
                intent.putExtra("attackPower2", charactersList.getCharactersList().get(indexArray2).getAttackPower());
                intent.putExtra("health1",charactersList.getCharactersList().get(indexArray1).getHealthPoint());
                intent.putExtra("health2",charactersList.getCharactersList().get(indexArray2).getHealthPoint());
                intent.putExtra("image1",charactersList.getCharactersList().get(indexArray1).getImage());
                intent.putExtra("image2",charactersList.getCharactersList().get(indexArray2).getImage());

                startActivity(intent);
            }
        });
    }
    public void changeDisplay1()
    {
        TextView text = (TextView)findViewById(R.id.textView1);
        String descriptionCharacter = charactersList.getCharactersList().get(indexArray1).toString();
        text.setText(descriptionCharacter);
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        image1.setImageResource(charactersList.getCharactersList().get(indexArray1).getImage());
    }
    public void changeDisplay2()
    {
        TextView text = (TextView)findViewById(R.id.textView2);
        String descriptionCharacter = charactersList.getCharactersList().get(indexArray2).toString();
        text.setText(descriptionCharacter);
        ImageView image1 = (ImageView)findViewById(R.id.imageView2);
        image1.setImageResource(charactersList.getCharactersList().get(indexArray2).getImage());
    }
    public void next(View v)
    {
        if (indexArray1 == charactersList.getCharactersList().size()-1)
            indexArray1 = 0;
        else
            indexArray1++;
        changeDisplay1();
    }
    public void previous(View v)
    {
        if (indexArray1 == 0)
            indexArray1 = charactersList.getCharactersList().size()-1;
        else
            indexArray1--;
        changeDisplay1();
    }
    public void next2(View v)
    {
        if (indexArray2 == charactersList.getCharactersList().size()-1)
            indexArray2 = 0;
        else
            indexArray2++;
        changeDisplay2();
    }
    public void previous2(View v)
    {
        if (indexArray2 == 0)
            indexArray2 = charactersList.getCharactersList().size()-1;
        else
            indexArray2--;
        changeDisplay2();
    }
}
