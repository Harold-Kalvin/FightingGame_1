package com.example.android.theapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Battle extends AppCompatActivity {
    private static int attackPower1;
    private static int attackPower2;
    private static int healthPoint1;
    private static int healthPoint2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getting every datas from the CharacterSelection activity
        Intent intent = getIntent();
        String nom1 = intent.getStringExtra("nom1");
        String nom2 = intent.getStringExtra("nom2");
        int attackPwr1 = intent.getIntExtra("attackPower1", 10);
        int attackPwr2 = intent.getIntExtra("attackPower2", 10);
        int healthPt1 = intent.getIntExtra("health1",100);
        int healthPt2 = intent.getIntExtra("health2",100);
        int image1 = intent.getIntExtra("image1",0);
        int image2 = intent.getIntExtra("image2", 0);

        //adding changes
        //NAME
        TextView charName1 = (TextView)findViewById(R.id.characName1);
        TextView charName2 = (TextView)findViewById(R.id.characName2);
        charName1.setText(nom1);
        charName2.setText(nom2);
        //ATTACK POWER
        attackPower1 = attackPwr1;
        attackPower2 = attackPwr2;
        //HEALTH
        healthPoint1 = healthPt1;
        healthPoint2 = healthPt2;
        ProgressBar progressbar1 = (ProgressBar)findViewById(R.id.barPlayer1);
        ProgressBar progressbar2 = (ProgressBar)findViewById(R.id.barPlayer2);
        progressbar1.setRotation(180);
        //Adding colors on progress bar
        progressbar1.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        progressbar2.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        //max health value
        progressbar1.setMax(healthPoint1);
        progressbar2.setMax(healthPoint2);
        progressbar1.setProgress(healthPoint1);
        progressbar2.setProgress(healthPoint2);
        //text health
        TextView textHealth1 = (TextView)findViewById(R.id.health1);
        TextView textHealth2 = (TextView)findViewById(R.id.health2);
        textHealth1.setText(healthPoint1+"/"+healthPoint1);
        textHealth2.setText(healthPoint2 + "/" + healthPoint2);
        //IMAGE
        ImageView img1 = (ImageView)findViewById(R.id.battleImage1);
        img1.setImageResource(image1);
        ImageView img2 = (ImageView)findViewById(R.id.battleImage2);
        img2.setImageResource(image2);
    }
    public void player1Attack(View v)
    {
        //decreases progress
        ProgressBar progressbar1 = (ProgressBar)findViewById(R.id.barPlayer2);
        int progress = progressbar1.getProgress();
        progressbar1.setProgress(progress - attackPower1);
        //updates health text
        TextView textHealth2 = (TextView)findViewById(R.id.health2);
        textHealth2.setText(progress-attackPower1 + "/" + healthPoint2);
        victoryVerification(progressbar1, "Player 1");
    }
    public void player2Attack(View v)
    {
        ProgressBar progressbar2 = (ProgressBar)findViewById(R.id.barPlayer1);
        int progress2 = progressbar2.getProgress();
        progressbar2.setProgress(progress2-attackPower2);
        TextView textHealth = (TextView)findViewById(R.id.health1);
        textHealth.setText(progress2-attackPower2 + "/" + healthPoint1);
        victoryVerification(progressbar2, "Player 2");
    }
    public void restartBattle(View v)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void victoryVerification(ProgressBar bar, String nomJoueur)
    {
        if (bar.getProgress() == 0)
        {
            //display in long period of time
            //Toast.makeText(getApplicationContext(), nomJoueur + " has won !", Toast.LENGTH_LONG).show();
            TextView text = (TextView)findViewById(R.id.victoryText);
            text.setText(nomJoueur+ " has won !");
            Button bouton = (Button)findViewById(R.id.player1);
            bouton.setClickable(false);
            Button bouton2 = (Button)findViewById(R.id.player2);
            bouton2.setClickable(false);
        }
    }
}
