package com.koopahtmaniac.roadrusher.Objects;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.MainObjects.ObjectMain;
import com.koopahtmaniac.roadrusher.SDraw;
import com.koopahtmaniac.roadrusher.Variables.RotatedRectangle;

public class Player extends ObjectMain
{
	public float SpeedX = 1.5f;
	public float SpeedY = 2.5f;
	public float Rotation = 0;
	public float TiltSensitivity = 2;
	public boolean GrindLeft = false;
	public boolean GrindRight = false;
	public boolean Grind = false;
	public boolean Explosion = false;
	public boolean ExplosionLeft = false;
	public boolean ExplosionRight = false;
	public float MaxTilt = 8;
	public int Lives = 3;
	public int MaxLives = 5;
	public int ResetLives = 3;
	public boolean Drift = false;
	public float DriftTimeOut = 1.5f;
	public float ResetDriftTimeOut = 1.5f;
	public float TiltXOld = 0;
	public float TiltX =0;
	public float TiltY =0;
	public float CalibrateX;
	public float CalibrateY;
	public float DriftX =0;
	public float GrindTimeOut = 0.5f;
	public float GrindTimeOutReset = 0.5f;
	public float SmoothMovementBase = 0.0111f;
	public Player(float X, float Y,float CalibrateX,float CalibrateY, TextureRegion texture) 
	{
		super(X, Y, texture);
		this.X = X;
		this.Y = Y;
		this.CalibrateX = CalibrateX;
		this.CalibrateY = CalibrateY;
		this.texture = texture;
		CollisionBox = new RotatedRectangle(X, Y, texture.getRegionWidth()*(GameCore.UseLowResGraphics+1), texture.getRegionHeight()*(GameCore.UseLowResGraphics+1), 0);
	}
	public void SetTexture(TextureRegion texture)
	{
		this.texture = texture;
		CollisionBox = new RotatedRectangle(X, Y, texture.getRegionWidth()*(GameCore.UseLowResGraphics+1), texture.getRegionHeight()*(GameCore.UseLowResGraphics+1), 0);
	}
	public boolean Intersect(RotatedRectangle rect)
	{
		return CollisionBox.Intersect(rect);
	}
	public boolean IntersectLeft(RotatedRectangle rect)
	{
		return CollisionBox.IntersectLeft(rect);
	}
	public boolean IntersectRight(RotatedRectangle rect)
	{
		return CollisionBox.IntersectRight(rect);
	}
	@Override
	public void Draw(SpriteBatch spritebatch) 
	{
		SDraw.DrawRotated(spritebatch, texture, X, Y, texture.getRegionWidth(), texture.getRegionHeight(), Rotation);
	}
	
	public void Update(float GameTime) 
	{
		GrindTimeOut -= GameTime;
		if (GrindTimeOut < 0)
		{
			Grind = false;
			GrindLeft = false;
			GrindRight = false;
		}
		CollisionBox.Rotate(Rotation);
		CollisionBox.Update(X, Y);
		
		if (Drift)
		{
			DriftTimeOut -= GameTime;
			if (DriftTimeOut < 0)
			{
				Drift = false;
				DriftTimeOut = ResetDriftTimeOut;
			}
		}
		if (GameCore.SlowMotion ==1)
		{ 
			if (GameCore.SlowMotionPoints >0)
			{
				GameCore.SlowMotionPoints -=1;
			}
			else
			{
				GameCore.SlowMotion = 0;
			}
		}
		if (this.X <  270)
		{
			GameCore.SlowMotionPoints += 0.5;
		}
		if (GameCore.SlowMotionPoints > GameCore.SlowMotionPointsMax)
		{
			GameCore.SlowMotionPoints = GameCore.SlowMotionPointsMax;
		}
	}
	public void Move(Input input, float GameTime) 
	{
		if (input.isTouched())
		{
			if (GameCore.SlowMotionPoints > 0)
			{
				GameCore.SlowMotion = 1;
			}
			else
			{
				GameCore.SlowMotion = 0;
			}
		}
		else
		{
			GameCore.SlowMotion = 0;
		}
		
		if(Gdx.app.getType() == ApplicationType.Android) 
		{
			if (DriftX < -input.getAccelerometerX())
			{
				DriftX += 0.22f;
			}
			else if (DriftX > -input.getAccelerometerX())
			{
				DriftX -= 0.22f;
			}
			else
			{
				DriftX = -input.getAccelerometerX();
			}
			TiltX = -input.getAccelerometerX();
			TiltY = input.getAccelerometerY();
		}
		else
		{
			
			if (input.isKeyPressed(Input.Keys.LEFT))
			{
				TiltX  -=0.3f;
			}
			else if (input.isKeyPressed(Input.Keys.RIGHT))
			{
				TiltX  +=0.3f;
			}
			else
			{
				if (TiltX ==0)
				{
					
				}
				else if (TiltX >0)
				{
					TiltX -= 0.05f;
				}
				else
				{
					TiltX += 0.05f;
				}
			}
			
			if (input.isKeyPressed(Input.Keys.UP))
			{
				TiltY  -=0.15f;
			}
			else if (input.isKeyPressed(Input.Keys.DOWN))
			{
				TiltY  +=0.15f;
			}
			else
			{
				if (TiltY == 0)
				{
					
				}
				else if (TiltY >0)
				{
					TiltY -= 0.05f;
				}
				else
				{
					TiltY += 0.05f;
				}
			}
		}
		
		
		if (TiltX >MaxTilt)
			TiltX =MaxTilt;
		if (TiltY >MaxTilt)
			TiltY =MaxTilt;
		if (TiltX <-MaxTilt)
			TiltX =-MaxTilt;
		if (TiltY <-MaxTilt)
			TiltY =-MaxTilt;
		
		if (Drift)
		{
			Rotation = -DriftX*TiltSensitivity*3;
			X += ((DriftX)*SpeedX / ((1*GameCore.SlowMotion) + 1))*2 * (GameTime/GameCore.FrameLimiter);
			
		}
		else if (TiltX > 0.5f || TiltX < -0.5f)
		{
			if (!Explosion)
			{
				if (TiltX-TiltXOld > 2.5f || TiltX-TiltXOld < -2.5f)
				{
					TiltXOld = TiltX;
					DriftTimeOut = ResetDriftTimeOut;
					Drift = true;
					
				}
				TiltXOld = TiltX;
				Rotation = -TiltX*TiltSensitivity;
				X += TiltX*SpeedX / ((1*GameCore.SlowMotion) + 1) * (GameTime/GameCore.FrameLimiter);
			}
		}
		else
		{
			Rotation =0;
		}
		if (Drift)
		{
			Y += -(2*((1*GameCore.SlowMotion) + 1)) + TiltY*SpeedY* ((1*GameCore.SlowMotion) + 1)  * (GameTime/GameCore.FrameLimiter);
		}
		else
		{
			if (TiltY > 0.5f || TiltY < -0.5f)
			{
				if (!Explosion)
				{
					Y += TiltY*SpeedY / ((1*GameCore.SlowMotion) + 1)  * (GameTime/GameCore.FrameLimiter);
				}
			}
		}
		if (X < 20)
		{
			if (TiltX < -2.5f && !GrindLeft)
			{
				X = 19f;
				this.Explosion = true;
				this.ExplosionLeft = true;
			}
			else
			{
				X = 19f;
				GameCore.MainPlayer.GrindTimeOut = GameCore.MainPlayer.GrindTimeOutReset;
				GrindLeft = true;
			}
		}
		else if (X > GameCore.Width-47d-66d)
		{
			if (TiltX > 2.5f && !GrindRight)
			{
				this.Explosion = true;
				this.ExplosionRight = true;
				X= GameCore.Width-46f-66f;
			}
			else
			{
				X= GameCore.Width-46f-66f;
				GameCore.MainPlayer.GrindTimeOut = GameCore.MainPlayer.GrindTimeOutReset;
				GrindRight = true;
			}
		}
		if (Y < 89)
		{
			Y = 89f;
		}
		else if (Y > GameCore.Height-116d)
		{
			Y= GameCore.Height-116f;
		}
		if (GrindLeft || GrindRight)
		{
			if (!Explosion)
			{
				if (Rotation > 5)
				{
					Rotation -= 5;
				}
				else if (Rotation < -5)
				{
					Rotation += 5;
				}
				else
				{
					Rotation = 0;
				}
			}
		}
		CollisionBox.Rotate(Rotation);
		
	}
}
