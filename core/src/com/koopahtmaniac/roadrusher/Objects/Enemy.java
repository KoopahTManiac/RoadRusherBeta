package com.koopahtmaniac.roadrusher.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.MainObjects.ObjectMain;

public class Enemy extends ObjectMain
{
	public float SpeedLimiter = 5;
	public Enemy(float X, float Y, TextureRegion texture,float SpeedLimiter,float Rotation) 
	{
		super(X, Y, texture);
		this.Rotation = Rotation;
		this.SpeedLimiter = SpeedLimiter;
		CollisionBox.Rotate(Rotation);	
	}
	@Override
	public void Move(float X, float Y) 
	{
		super.Move(X, Y);
	}
	@Override
	public void Update(float GameTime) 
	{
		this.Y += ((GameCore.MainRoad.BaseSpeed * GameCore.MainRoad.SpeedModifier) / (4*GameCore.SlowMotion+1))/SpeedLimiter * (GameTime/GameCore.FrameLimiter);
		super.Update(GameTime);
	}
	@Override
	public void Draw(SpriteBatch spritebatch) 
	{
		super.Draw(spritebatch);
	}
}
