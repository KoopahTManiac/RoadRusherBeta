package com.koopahtmaniac.roadrusher.MainObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.SDraw;
import com.koopahtmaniac.roadrusher.Variables.RotatedRectangle;

public class ObjectMain 
{
	public float X;
	public float Y;
	public TextureRegion texture;
	public RotatedRectangle CollisionBox;
	public float Height,_Height;
	public float Width,_Width;
	public float Rotation;
	public ObjectMain(float X, float Y, TextureRegion texture)
	{
		this.X = X;
		this.Y = Y;
		this.texture = texture;
		this.CollisionBox = new RotatedRectangle((int)X, (int)Y, texture.getRegionWidth()*(GameCore.UseLowResGraphics+1), texture.getRegionHeight()*(GameCore.UseLowResGraphics+1), 0);
		this.Width = texture.getRegionWidth()*(GameCore.UseLowResGraphics+1);
		this.Height = texture.getRegionHeight()*(GameCore.UseLowResGraphics+1);
		this._Width = texture.getRegionWidth();
		this._Height = texture.getRegionHeight();
	}
	public ObjectMain(float X, float Y, TextureRegion texture , int Width, int Height)
	{
		this.X = X;
		this.Y = Y;
		this.texture = texture;
		this.CollisionBox = new RotatedRectangle((int)X, (int)Y, Width, Height, 0);
		this.Width = texture.getRegionWidth()*(GameCore.UseLowResGraphics+1);
		this.Height = texture.getRegionHeight()*(GameCore.UseLowResGraphics+1);
		this._Width = texture.getRegionWidth();
		this._Height = texture.getRegionHeight();
	}
	public void Update(float GameTime)
	{
		CollisionBox.Update(X, Y);
	}
	
	public void Move(float X, float Y)
	{
		this.X += X;
		this.Y += Y;
	}
	
	public void MoveTo(float X, float Y)
	{
		this.X = X;
		this.Y = Y;
	}
	
	public void Draw(SpriteBatch spritebatch)
	{
		SDraw.DrawRotated(spritebatch, texture, X, Y,(int)_Width,(int)_Height,Rotation);
	}
	
	public void ResetCollisionBounds()
	{
		this.CollisionBox = new RotatedRectangle(X, Y, Width, Height, Rotation);
	}
}
