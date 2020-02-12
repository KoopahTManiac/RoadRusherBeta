package com.koopahtmaniac.roadrusher.Particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.MainObjects.MainParticles;
import com.koopahtmaniac.roadrusher.SDraw;

import java.util.Random;


public class Grind extends MainParticles
{
	Random Direction = new Random();
	float VolX=0,VolY=0.9f;
	float TempRot =0;
	Random TextureRandome = new Random();
	TextureRegion Red,Yellow,White,Orange;
	float LifeTimeForGrind = 0.5f;

	public Grind(TextureRegion Red, TextureRegion Yellow, TextureRegion White, TextureRegion Orange) 
	{
		super(Red,25);
		this.Red = Red;
		this.Yellow = Yellow;
		this.White = White;
		this.Orange = Orange;
	}
	@Override
	public void Update(float GameTime) 
	{
		
		if (GameCore.MainPlayer.GrindLeft || GameCore.MainPlayer.GrindRight)
		{
			switch (TextureRandome.nextInt(3)) 
			{
				case 0:
				{
					texture = Red;
					break;
				}
				case 1:
				{
					texture = Yellow;
					break;
				}
				case 2:
				{
					texture = White;
					break;
				}
				case 3:
				{
					texture = Orange;
					break;
				}
			}
			TempRot = Direction.nextInt(90);
			if (GameCore.MainPlayer.GrindLeft)
			{
				VolX = (float)Math.cos(TempRot+70);
				VolY = (float)Math.sin(TempRot+70);
				this.Add(GameCore.MainPlayer.X, GameCore.MainPlayer.Y+10, VolX*1.5f, VolY*1.5f, TempRot+70,LifeTimeForGrind,texture);
				VolX = (float)Math.cos(TempRot+75);
				VolY = (float)Math.sin(TempRot+75);
				this.Add(GameCore.MainPlayer.X, GameCore.MainPlayer.Y+10, VolX*1.5f, VolY*1.5f, TempRot+75,LifeTimeForGrind,texture);
				VolX = (float)Math.cos(TempRot+65);
				VolY = (float)Math.sin(TempRot+65);
				this.Add(GameCore.MainPlayer.X, GameCore.MainPlayer.Y+10, VolX*1.5f, VolY*1.5f, TempRot+65,LifeTimeForGrind,texture);
			}
			if (GameCore.MainPlayer.GrindRight)
			{
				VolX = (float)Math.cos(TempRot+160);
				VolY = (float)Math.sin(TempRot+160);
				this.Add(GameCore.MainPlayer.X+GameCore.MainPlayer.Width, GameCore.MainPlayer.Y+10, VolX*1.5f, VolY*1.5f, TempRot+160,LifeTimeForGrind,texture);
				VolX = (float)Math.cos(TempRot+165);
				VolY = (float)Math.sin(TempRot+165);
				this.Add(GameCore.MainPlayer.X+GameCore.MainPlayer.Width, GameCore.MainPlayer.Y+10, VolX*1.5f, VolY*1.5f, TempRot+165,LifeTimeForGrind,texture);
				VolX = (float)Math.cos(TempRot+155);
				VolY = (float)Math.sin(TempRot+155);
				this.Add(GameCore.MainPlayer.X+GameCore.MainPlayer.Width, GameCore.MainPlayer.Y+10, VolX*1.5f, VolY*1.5f, TempRot+155,LifeTimeForGrind,texture);
			}
		}
		for (int i =Particles.length-1; i >=0 ;i--)
		{
			Corrent = Particles[i];
			if (Corrent.Visible)
			{
				Corrent.LifeTime -= GameTime;
				if (Corrent.LifeTime <=0)
				{
					Remove(i);
				}
				else
				{
					Corrent.X += Corrent.VolX;
					Corrent.Y += Corrent.VolY;
					Corrent.Y += ((GameCore.MainRoad.BaseSpeed * GameCore.MainRoad.SpeedModifier) / (4*GameCore.SlowMotion+1))/2 * (GameTime/GameCore.FrameLimiter);
				}
			}
		}
		
	}
	@Override
	public void Draw(SpriteBatch spritebatch) 
	{
		for (int i=Particles.length-1; i >=0; i--) 
		{
			Corrent = Particles[i];
			if(Corrent.Visible)
			{
				SDraw.DrawRotated(spritebatch, Corrent.tex, Corrent.X, Corrent.Y, Corrent.tex.getRegionWidth(), Corrent.tex.getRegionHeight(), Corrent.Rotation);
			}
		}
	}
}
