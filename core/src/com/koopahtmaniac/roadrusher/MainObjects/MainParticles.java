package com.koopahtmaniac.roadrusher.MainObjects;

import java.util.ArrayList;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.koopahtmaniac.roadrusher.SDraw;
import com.koopahtmaniac.roadrusher.Variables.Particle;

public class MainParticles 
{
	public Particle[] Particles;
	public TextureRegion texture;
	int i=0;
	public int Pos = 0;
	public int PosMax = 50;
	public int AParticles =0;
	public Particle Corrent;
	public MainParticles(TextureRegion texture) 
	{
		this.Particles = new Particle[PosMax];
		this.texture = texture;
		for (int t=0; t < PosMax;t++)
		{
			this.Particles[t] = new Particle(0, 0, 0, 0, 0);
		}
		AParticles = Particles.length;
	}
	public MainParticles(TextureRegion texture, int j) 
	{
		this.PosMax = j;
		this.Particles = new Particle[j];
		this.texture = texture;
		for (int t=0; t < PosMax;t++)
		{
			this.Particles[t] = new Particle(0, 0, 0, 0, 0);
		}
		AParticles = Particles.length;
	}
	public void Update(float GameTime)
	{
		
	}
	public void Draw(SpriteBatch spritebatch)
	{
		
		for(int i=Particles.length-1; i >= 0; i --)
		{
			Corrent = this.Particles[i];
			if(Corrent.Visible)
			SDraw.DrawRotated(spritebatch, texture, Corrent.X, Corrent.Y, texture.getRegionWidth(), texture.getRegionHeight(), Corrent.Rotation);
		}
	}
	public void Add(float X,float Y, float VolX, float VolY, float Rotation)
	{
		Corrent = this.Particles[Pos];
		Corrent.X = X;
		Corrent.Y = Y;
		Corrent.VolX = VolX;
		Corrent.VolY = VolY;
		Corrent.Rotation = Rotation;
		Corrent.Visible = true;
		Corrent.LifeTime = 5;
		Pos++;
		if (Pos >= PosMax)
			Pos=0;
	}
	public void Add(float X,float Y, float VolX, float VolY, float Rotation, float LifeTime, TextureRegion tex)
	{
		Corrent = this.Particles[Pos];
		Corrent.X = X;
		Corrent.Y = Y;
		Corrent.VolX = VolX;
		Corrent.VolY = VolY;
		Corrent.Rotation = Rotation;
		Corrent.Visible = true;
		Corrent.tex = tex;
		Corrent.LifeTime = LifeTime;
		Pos++;
		if (Pos >= PosMax)
			Pos=0;
	}
	public void Reset()
	{
		for (int i=0; i < AParticles; i++)
		{
			this.Particles[i].Visible = false;
		}
	}
	public void Remove(int Remove)
	{
		this.Particles[Remove].Visible = false;
	}
}
