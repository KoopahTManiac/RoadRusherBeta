package com.koopahtmaniac.roadrusher.Particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.MainObjects.MainParticles;
import com.koopahtmaniac.roadrusher.SDraw;

public class TireTracks extends MainParticles
{
	float VolX_ =0;
	float VolY_ =0;
	float X_ = 0;
	float Y_ = 0;
	float Height=0;
	float RotationF = 0;
	public int PosMax = 200;
	public TireTracks(TextureRegion texture) 
	{
		super(texture,150);
	}
	@Override
	public void Add(float X, float Y, float VolX, float VolY, float Rotation) 
	{
		
		VolX_ = (float)Math.sin(((double)-Rotation+90d)*0.0174532925d);
		VolY_ = (float)Math.cos(((double)-Rotation+90d)*0.0174532925d);
		X_ = X+VolX_*2;
		Y_ = Y-VolY_*2;
		VolX_ = (float)Math.sin(((double)-Rotation+180d)*0.0174532925d);
		VolY_ = (float)Math.cos(((double)-Rotation+180d)*0.0174532925d);
		Y_ += 56.5f;
		
		X_ += VolX_*30;
		Y_ -= VolY_*30;
		
		if (Pos <= 1)
		{
			RotationF = (float)Math.atan2(Particles[Particles.length-2].Y-Y_, Particles[Particles.length-2].X-X_) / (float)(Math.PI / 180f)+270;
			super.Add(X_, Y_, 0, 0, RotationF);
		}
		else
		{
			RotationF = (float)Math.atan2(Particles[Pos-2].Y-Y_, Particles[Pos-2].X-X_) / (float)(Math.PI / 180f)+270;
			super.Add(X_, Y_, 0, 0, RotationF);
		}
		
		VolX_ = (float)Math.sin(((double)-Rotation+90d)*0.0174532925d);
		VolY_ = (float)Math.cos(((double)-Rotation+90d)*0.0174532925d);
		X_ = X+VolX_*38;
		Y_ = Y-VolY_*38;
		VolX_ = (float)Math.sin(((double)-Rotation+180d)*0.0174532925d);
		VolY_ = (float)Math.cos(((double)-Rotation+180d)*0.0174532925d);
		Y_ += 56.5f;
		X_ += VolX_*30;
		Y_ -= VolY_*30;
		if (Pos <= 1)
		{
			RotationF = (float)Math.atan2(Particles[Particles.length-1].Y-Y_, Particles[Particles.length-1].X-X_) / (float)(Math.PI / 180f)+270;
			super.Add(X_, Y_, 0, 0, RotationF);
		}
		else
		{
			RotationF = (float)Math.atan2(Particles[Pos-2].Y-Y_, Particles[Pos-2].X-X_) / (float)(Math.PI / 180f)+270;
			super.Add(X_, Y_, 0, 0, RotationF);
		}
	}
	@Override
	public void Update(float GameTime) 
	{
		
		for (int i = Particles.length-1; i >=0 ;i--)
		{
			Corrent = Particles[i];
			if (Corrent.Visible)
			{
				Corrent.Y+=(GameCore.MainRoad.BaseSpeed * GameCore.MainRoad.SpeedModifier) / (4*GameCore.SlowMotion+1) * (GameTime/GameCore.FrameLimiter);
				Corrent.LifeTime -= GameTime;
				
				if (Corrent.Y > GameCore.Height+50)
				{
					Remove(i);
				}
				else if (Corrent.LifeTime <=0)
				{
					Remove(i);
				}
			}
		} 
		
	}
	@Override
	public void Draw(SpriteBatch spritebatch) 
	{
		Height = ((GameCore.MainRoad.BaseSpeed * GameCore.MainRoad.SpeedModifier)/8)*30;
		for (int i=Particles.length-1; i >=0; i--) 
		{
			this.Corrent = Particles[i];
			if(Corrent.Visible)
			SDraw.DrawRotatedFromTopLeft(spritebatch, this.texture, Corrent.X, Corrent.Y, texture.getRegionWidth(), Height, -Corrent.Rotation);
		}
	}

}
