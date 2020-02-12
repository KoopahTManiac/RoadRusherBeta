package com.koopahtmaniac.roadrusher.MenuHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.Button;
import com.koopahtmaniac.roadrusher.Cordinate;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.SDraw;

public class PauseAboutMenu 
{
	Button Back;
	TextureRegion AboutMe,BackTex;
	public PauseAboutMenu(TextureRegion BackTex,TextureRegion AboutMe, int Width, int Height) 
	{
		this.AboutMe = AboutMe;
		this.BackTex = BackTex;
		this.Back = new Button((int)(GameCore.Width/2), (int)(100), BackTex);
	}
	public void Update()
	{
		if (Gdx.input.isTouched())
		{
			if (Back.Update())
			{
				GameCore.GameState = 7;
			}
		}
	}
	public void Draw(SpriteBatch sp)
	{
		SDraw.DrawRotated(sp,AboutMe,0f, Cordinate.P( 0 + AboutMe.getRegionHeight() * (GameCore.UseLowResGraphics+1)),0);
		Back.Draw(sp);		
	}
}
