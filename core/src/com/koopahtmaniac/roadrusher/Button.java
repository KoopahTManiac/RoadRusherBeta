package com.koopahtmaniac.roadrusher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button 
{
	TextureRegion tex;
	Rectangle Rect = new Rectangle(0, 0, 0, 0);
	
	public Button(int X,int Y,TextureRegion texture) 
	{
		Rect = new Rectangle(X-(texture.getRegionWidth()/2)*(GameCore.UseLowResGraphics+1), Y - (texture.getRegionHeight()/2)*(GameCore.UseLowResGraphics+1), texture.getRegionWidth()*(GameCore.UseLowResGraphics+1), texture.getRegionHeight()*(GameCore.UseLowResGraphics+1));
		this.tex = texture;
	}
	public boolean Update()
	{
		if (Rect.overlaps(GameCore.ClickPos) && GameCore.CanClick)
		{
			GameCore.CanClick = false;
			return true;
		}
		return false;
	}
	public void Draw(SpriteBatch spritebatch)
	{
		SDraw.DrawTile(spritebatch, tex, Rect.x, Rect.y);
	}
}
