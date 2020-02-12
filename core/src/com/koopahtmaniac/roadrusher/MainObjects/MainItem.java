package com.koopahtmaniac.roadrusher.MainObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.SDraw;
import com.koopahtmaniac.roadrusher.Variables.RotatedRectangle;

public class MainItem 
{
	public float X,Y;
	public TextureRegion texture;
	public RotatedRectangle CollisionBox;
	public MainItem(float X, float Y, TextureRegion tex) 
	{
		this.texture = tex;
		this.X=X;
		this.Y=Y;
		this.CollisionBox = new RotatedRectangle(X, Y, texture.getRegionWidth()*(GameCore.UseLowResGraphics+1), texture.getRegionHeight()*(GameCore.UseLowResGraphics+1), 0);
	}
	public MainItem(float X, float Y, TextureRegion tex, int Width,int Height) 
	{
		this.texture = tex;
		this.X=X;
		this.Y=Y;
		this.CollisionBox = new RotatedRectangle(X, Y, Width, Height, 0);
	}
	public void Move(float X, float Y)
	{
		
	}
	public void Update(float GameTime)
	{
		this.CollisionBox.Update(X, Y);
	}
	
	public void Draw(SpriteBatch spritebatch)
	{
		SDraw.DrawRotated(spritebatch, texture, this.X, this.Y, texture.getRegionWidth()*(GameCore.UseLowResGraphics+1), texture.getRegionHeight()*(GameCore.UseLowResGraphics+1), 0);
	}
	
}
