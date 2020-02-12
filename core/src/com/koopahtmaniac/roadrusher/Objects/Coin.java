package com.koopahtmaniac.roadrusher.Objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.MainObjects.MainItem;
import com.koopahtmaniac.roadrusher.SDraw;

public class Coin extends MainItem
{
	public int Frame = 0;
	public int Frames = 8;
	public boolean Consumed = false;
	public TextureRegion[] tiledMap;
	public Coin(float X, float Y, TextureRegion texture) 
	{
		super(X, Y, texture, 64, 64);
		
		tiledMap = new TextureRegion[Frames];
		for (int i=0; i < Frames;i++)
		{
			tiledMap[i] = new TextureRegion(texture, (32/(GameCore.UseLowResGraphics+1)) * i, 0, 32/(GameCore.UseLowResGraphics+1), 32/(GameCore.UseLowResGraphics+1));
		}
		
	}
	@Override
	public void Update(float GameTime) 
	{
		super.Update(GameTime);
		this.CollisionBox.Update(X, Y);
		if (GameCore.MainPlayer.Intersect(CollisionBox))
		{
			this.Consumed = true;
			GameCore.GameScore += 150;
		}
	}
	public void Draw(SpriteBatch spritebatch)
	{
		if (Frame >=tiledMap.length)
		{
			Frame= 0;
		}
		SDraw.DrawRotated(spritebatch, tiledMap[Frame],X, Y,64/(GameCore.UseLowResGraphics+1),64/(GameCore.UseLowResGraphics+1), 0);
		
		Frame++;
	}
}