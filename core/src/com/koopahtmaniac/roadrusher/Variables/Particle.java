package com.koopahtmaniac.roadrusher.Variables;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Particle 
{
	public float X,Y,VolX,VolY,Rotation,LifeTime;
	public TextureRegion tex;
	public boolean Visible = false;
	public Particle(float X, float Y, float VolX, float VolY, float Rotation,float LifeTime, TextureRegion tex) 
	{
		this.X = X;
		this.Y = Y;
		this.VolX = VolX;
		this.VolY = VolY;
		this.Rotation = Rotation;
		this.LifeTime = LifeTime;
		this.tex = tex;
	}
	public Particle(float X, float Y, float VolX, float VolY, float Rotation) 
	{
		this.X = X;
		this.Y = Y;
		this.VolX = VolX;
		this.VolY = VolY;
		this.Rotation = Rotation;
		this.LifeTime = 5f;
	}
	
}
