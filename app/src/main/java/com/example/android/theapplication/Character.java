package com.example.android.theapplication;

import android.media.Image;

/**
 * Created by android on 09/03/2016.
 */
public class Character {
    private String name;
    private int attackPower;
    private int healthPoint;
    private int image;
    private int avatar;
    private int imagePunch;
    private int imagePunch2;


    public Character (String namePar, int attackPowerPar, int healthPointPar, int imagePar, int avatarPar, int imagePunchPar,int imagePunch2Par){
        this.name = namePar;
        this.attackPower = attackPowerPar;
        this.healthPoint = healthPointPar;
        this.image = imagePar;
        this.avatar = avatarPar;
        this.imagePunch = imagePunchPar;
        this.imagePunch2 = imagePunch2Par;
    }
    public String getName()
    {
        return name;
    }
    public int getAttackPower()
    {
        return attackPower;
    }
    public int getHealthPoint()
    {
        return healthPoint;
    }
    public int getImage()
    {
        return image;
    }
    public int getAvatar()
    {
        return avatar;
    }
    public int getImagePunch(){return imagePunch;}
    public int getImagePunch2(){return imagePunch2;}
    public String toString()
    {
        return "Name : "+name+"\nAttack Power : "+attackPower+"\nHealth : "+healthPoint;
    }
}
