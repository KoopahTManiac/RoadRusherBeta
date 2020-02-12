package com.koopahtmaniac.roadrusher.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.MainObjects.ObjectMain;
import com.koopahtmaniac.roadrusher.SDraw;

public class Road extends ObjectMain
{
	public float BaseSpeed = 8;
	public float SpeedModifier = 0.8f;
	public float SpeedModifierIncrease = 0.001f;
	public float MaxSpeed = 2f;
	public float Y = 0;
	public float Y2 = 0;
	public Road(TextureRegion texture) 
	{
		super(0, 0, texture);
		Y2 = Y-texture.getRegionHeight()*(GameCore.UseLowResGraphics+1);
	}
	@Override
	public void Update(float GameTime)
	{
		SpeedModifier += SpeedModifierIncrease / (4* GameCore.SlowMotion+1);
		Y += (BaseSpeed * SpeedModifier) / (4*GameCore.SlowMotion+1)  * (GameTime/GameCore.FrameLimiter);
		Y2 += (BaseSpeed * SpeedModifier) / (4*GameCore.SlowMotion+1)  * (GameTime/GameCore.FrameLimiter);
		if (Y > GameCore.Height)
		{
			Y = Y2 - texture.getRegionHeight()*(GameCore.UseLowResGraphics+1);
		}
		if (Y2 > GameCore.Height)
		{
			Y2 = Y - texture.getRegionHeight()*(GameCore.UseLowResGraphics+1);
		}
		if (SpeedModifier >MaxSpeed)
		{
			SpeedModifier=MaxSpeed;
		}
	}
	@Override
	public void Draw(SpriteBatch spritebatch) 
	{
		SDraw.DrawRotated(spritebatch, texture, 0, Y,texture.getRegionWidth(),texture.getRegionHeight(),0);
		SDraw.DrawRotated(spritebatch, texture, 0, Y2,texture.getRegionWidth(),texture.getRegionHeight(),0);
	}
	public void ResetSpeed()
	{
		SpeedModifier = 0.8f;
	}
}
