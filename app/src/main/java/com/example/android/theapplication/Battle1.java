package com.example.android.theapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Battle1 extends AppCompatActivity {
    private static int attackPower1;
    private static int attackPower2;
    private static int healthPoint1;
    private static int healthPoint2;
    private int img1;
    private int img2;
    private int imagePunch1;
    private int imagePunch2;
    private String difficulty;
    //random int for CPU
    private CharacterList charactersList = new CharacterList();
    private Random r = new Random();
    private int indexCPU = r.nextInt(charactersList.getCharactersList().size() - 0) + 0;

    private boolean isDone = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializing CPU's character
        String nom2 = charactersList.getCharactersList().get(indexCPU).getName();
        int attackPwr2 = charactersList.getCharactersList().get(indexCPU).getAttackPower();
        int healthPt2 = charactersList.getCharactersList().get(indexCPU).getHealthPoint();
        int image2 = charactersList.getCharactersList().get(indexCPU).getImage();
        int imgPunch2 = charactersList.getCharactersList().get(indexCPU).getImagePunch2();

        //getting every datas from the CharacterSelection activity (for the player 1)
        Intent intent = getIntent();
        String nom1 = intent.getStringExtra("nom1");
        int attackPwr1 = intent.getIntExtra("attackPower1", 10);
        int healthPt1 = intent.getIntExtra("health1", 100);
        int image1 = intent.getIntExtra("image1", 0);
        int imgPunch1=intent.getIntExtra("imagePunch1", 10);

        difficulty = intent.getStringExtra("difficulty");
        img1 = image1;
        img2 = image2;
        imagePunch1=imgPunch1;
        imagePunch2=imgPunch2;

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
        progressbar1.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        progressbar2.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        //max health value
        progressbar1.setMax(healthPoint1);
        progressbar2.setMax(healthPoint2);
        progressbar1.setProgress(healthPoint1);
        progressbar2.setProgress(healthPoint2);
        //text health
        TextView textHealth1 = (TextView)findViewById(R.id.health1);
        TextView textHealth2 = (TextView)findViewById(R.id.health2);
        textHealth1.setText(healthPoint1 + "/" + healthPoint1);
        textHealth2.setText(healthPoint2 + "/" + healthPoint2);
        //IMAGE
        ImageView img1 = (ImageView)findViewById(R.id.battleImage1);
        img1.setImageResource(image1);
        ImageView img2 = (ImageView)findViewById(R.id.battleImage2);
        img2.setImageResource(image2);

        if (!isDone)
            ActionStartsHere();
    }
    public void moveLeft(View v)
    {
        //get the player X position
        ImageView imagePlayer = (ImageView)findViewById(R.id.battleImage1);
        float x = imagePlayer.getX();
        //if X superior or equals 0, player can move back (can't move back otherwise)
        if (x>=0)
            imagePlayer.setX(x-20);
    }
    public void moveRight(View v)
    {
        //get the player X position
        ImageView imagePlayer = (ImageView)findViewById(R.id.battleImage1);
        float x = imagePlayer.getX();
        ImageView imageCPU = (ImageView)findViewById(R.id.battleImage2);
        float x2 = imageCPU.getX();
        //if X inferior or equals CPU's XPosition-70 (70 = cpu's width)
        if (x<=(x2-70)) {
            imagePlayer.setX(x + 20);
        }
    }
    public void punch(View v)
    {
        //changes real image with the "punch" version
        ImageView imagePunch = (ImageView)findViewById(R.id.battleImage1);
        imagePunch.setImageResource(imagePunch1);
        //after 100 milliseconds revert into the real image
        new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {
                    ImageView imagePunch = (ImageView) findViewById(R.id.battleImage1);
                    imagePunch.setImageResource(img1);
                }
            },
        100);
        //decreases progress
        ProgressBar progressbar1 = (ProgressBar)findViewById(R.id.barPlayer2);
        int progress = progressbar1.getProgress();

        if (isNextTo()) {
            progressbar1.setProgress(progress - attackPower1);
            //updates health text
            TextView textHealth2 = (TextView)findViewById(R.id.health2);
            textHealth2.setText(progress - attackPower1 + "/" + healthPoint2);
        }
        victoryVerification(progressbar1, "You");
    }
    public void victoryVerification(ProgressBar bar,String who)
    {
        if (bar.getProgress() <= 0)
        {
            isDone = true;
            TextView text = (TextView)findViewById(R.id.victoryText);
            text.setText(who+ " won !");
            Button bouton = (Button)findViewById(R.id.attack);
            bouton.setClickable(false);
            Button bouton2 = (Button)findViewById(R.id.right);
            bouton2.setClickable(false);
            Button bouton3 = (Button)findViewById(R.id.left);
            bouton3.setClickable(false);
        }
    }
    public void restartBattle(View v)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void moveBackCPU()
    {
        ImageView imagePlayer = (ImageView)findViewById(R.id.battleImage2);
        float x = imagePlayer.getX();
        View parent = (View)imagePlayer.getParent();
        int width = parent.getWidth();
        //if X inferior or equals parent's width, player can move back (can't move back otherwise)
        if (x<=(width-100))
            imagePlayer.setX(x+10);
    }
    public void moveForwardCPU()
    {
        //get the player X position
        ImageView imagePlayer = (ImageView)findViewById(R.id.battleImage2);
        float x = imagePlayer.getX();
        ImageView imageCPU = (ImageView)findViewById(R.id.battleImage1);
        float x2 = imageCPU.getX();
        //if X inferior or equals CPU's XPosition-70 (70 = cpu's width)
        if (x>=(x2+70)) {
            imagePlayer.setX(x - 20);
        }
    }
    public void punchCPU()
    {
        //changes real image with the "punch" version
        ImageView imagePunch = (ImageView)findViewById(R.id.battleImage2);
        imagePunch.setImageResource(imagePunch2);
        //after 100 milliseconds revert into the real image
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        ImageView imagePunch = (ImageView) findViewById(R.id.battleImage2);
                        imagePunch.setImageResource(img2);
                    }
                },
                100);
        //decreases progress
        ProgressBar progressbar1 = (ProgressBar)findViewById(R.id.barPlayer1);
        int progress = progressbar1.getProgress();

        if (isNextTo()) {
            progressbar1.setProgress(progress - attackPower2);
            //updates health text
            TextView textHealth2 = (TextView)findViewById(R.id.health1);
            textHealth2.setText(progress - attackPower2 + "/" + healthPoint1);
        }
        victoryVerification(progressbar1,"CPU");
    }
    //checks if CPU is next to player 1
    public boolean isNextTo()
    {
        ImageView imageCPU = (ImageView)findViewById(R.id.battleImage2);
        float xCPU = imageCPU.getX();
        ImageView imagePlayer = (ImageView)findViewById(R.id.battleImage1);
        float xPlayer = imagePlayer.getX();
        //if X inferior or equals CPU's XPosition-70 (70 = cpu's width)
        return xPlayer>=(xCPU-70);
    }
    //the CPU acts randomly
    public void aiEasy()
    {
        Random r = new Random();
        int cpu = r.nextInt(3 - 0) + 0;
        //move back
        if (cpu == 0)
        {
            moveBackCPU();
        }
        else if (cpu == 1)
        {
            moveForwardCPU();
        }
        else if (cpu == 2)
        {
            punchCPU();
        }
    }
    //the CPU is more agressive and steps back rarely, he moves forward and attacks most of the time
    public void aiMedHard()
    {
        Random r = new Random();
        int cpu = r.nextInt(10 - 0) + 0;
        if (cpu <= 2)
        {
            moveBackCPU();
        }
        else {
            if (!isNextTo())
                moveForwardCPU();
            else
                punchCPU();
        }
    }
    //the CPU starts
    public void ActionStartsHere() {
            againStartGPSAndSendFile();
    }
    public void againStartGPSAndSendFile() {
        //depending on the difficulty, the CPU acts faster
        int timer = 0;
        switch (difficulty){
            case "EASY":
                timer = 500;
                break;
            case "MEDIUM":
                timer = 300;
                break;
            case "HARD":
                timer=100;
                break;
        }
        new CountDownTimer(timer, timer) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (difficulty.equals("EASY"))
                    aiEasy();
                else
                    aiMedHard();
            }

            @Override
            public void onFinish() {
                //test if the match is done
                if (!isDone)
                    ActionStartsHere();
            }

        }.start();
    }
}
