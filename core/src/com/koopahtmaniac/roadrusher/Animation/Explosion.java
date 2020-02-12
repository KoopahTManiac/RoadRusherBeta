package com.koopahtmaniac.roadrusher.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.koopahtmaniac.roadrusher.MainObjects.AnimationMain;


public class Explosion extends AnimationMain
{
	public static int segments = 4;
	public float LifeTime = 1;
	public float OrginalLifeTime =1;
	public Explosion(float X, float Y, Texture texture) 
	{
		super(X, Y, segments, 12, texture);
		// TODO Auto-generated constructor stub
	}
	public void Play(float GameTime) 
	{
		this.LifeTime -= GameTime;
		if (LifeTime > 0)
		{
			Running = true;
			if (Frame >= Frames)
			{
				Frame =0;
			}
			else
			{
				Frame++;
			}
		}
		else
		{
			this.Running = false;
		}
	}
	@Override
	public void Stop() 
	{
		Frame = 0;
		Running = false;
		LifeTime = OrginalLifeTime;
	}
}
