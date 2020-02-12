package com.koopahtmaniac.roadrusher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ToggleButton extends Button 
{
	public boolean On = true;
	TextureRegion TextureOn,TextureOff;
	public ToggleButton(int X, int Y, TextureRegion textureOn, TextureRegion textureOff, boolean On) 
	{
		super(X, Y, textureOn);
		this.On = On;
		this.TextureOn = textureOn;
		this.TextureOff = textureOff;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void Draw(SpriteBatch spritebatch) 
	{
		if  (On)
		{
			tex = TextureOn;
		}
		else
		{
			tex = TextureOff;
		}
		super.Draw(spritebatch);
	}
}
