package com.koopahtmaniac.roadrusher.MainObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.SDraw;

public class Gui 
{
	public float X;
	public float Y;
	public TextureRegion texture;
	public float Width;
	public float Height;
	public Gui(float X, float Y, TextureRegion texture) 
	{
		this.X = X;
		this.Y = Y;
		this.texture = texture;
		this.Width = this.texture.getRegionWidth();
		this.Height = this.texture.getRegionHeight();
	}
	public Gui(float X, float Y,float Width, float Height, TextureRegion texture) 
	{
		this.X = X;
		this.Y = Y;
		this.texture = texture;
		this.Width = Width;
		this.Height = Height;
	}
	public void Draw(SpriteBatch spritebatch)
	{
		SDraw.DrawRotated(spritebatch,texture, X, Y,(int)Width,(int)Height,0);
	}
	public void DrawTo(SpriteBatch spritebatch, float X, float Y)
	{
		SDraw.DrawRotated(spritebatch,texture, X, Y,(int)Width,(int)Height,0);
	}
}
