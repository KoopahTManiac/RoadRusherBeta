package com.koopahtmaniac.roadrusher.MainObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.SDraw;

public class AnimationMain 
{
	public int Frame = 0;
	public int Frames;
	public float X;
	public float Y;
	public Texture texture;
	public TextureRegion[] tiledMap;
	public boolean Running = false;
	int tempX,tempY;
	public AnimationMain(float X, float Y,int Segments,int Frames, Texture texture) 
	{
		tiledMap = new TextureRegion[Segments*Segments];
		this.X = X;
		this.Y = Y;
		this.texture =  texture;
		this.Frames = Frames;
		for (int i = 0; i < Segments*Segments;i++)
		{
			
			tempX = texture.getWidth()/Segments * (i % Segments);
			tempY = texture.getHeight()/Segments * (i / Segments);
			this.tiledMap[i] = new TextureRegion(texture, texture.getWidth()/Segments*(i%Segments), texture.getHeight()/Segments * (i/Segments) , texture.getWidth()/Segments, texture.getHeight()/Segments);
		}
	}
	public void Play()
	{
		Running = true;
		if (Frame >= Frames)
		{
			Frame =0;
		}
		else
		{
			Frame +=1;
		}
	}
	public void Stop()
	{
		Frame = 0;
		Running = false;
	}
	public void Draw(SpriteBatch spritebatch)
	{
		if (Running)
		{
			SDraw.DrawTile(spritebatch, tiledMap[Frame], X, Y);
		}
	}
	public void Dispose()
	{
		this.texture.dispose();
	}
}
