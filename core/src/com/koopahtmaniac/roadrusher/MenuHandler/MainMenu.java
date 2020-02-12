package com.koopahtmaniac.roadrusher.MenuHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.Button;
import com.koopahtmaniac.roadrusher.GameCore;

public class MainMenu 
{
	Button Start, Exit, Settings, About;
	public MainMenu(TextureRegion StartTex, TextureRegion ExitTex, TextureRegion SettingsTex, TextureRegion AboutTex, int Width, int Height) 
	{
		this.Start = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2-100), StartTex);
		this.Settings = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2), SettingsTex);
		this.About = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2+100), AboutTex);
		this.Exit = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2+200), ExitTex);
	}
	public void Update()
	{
		if (Gdx.input.isTouched())
		{
			if (Start.Update())
			{
				GameCore.GameState = 2;
			}
			else if (Exit.Update())
			{
				GameCore.GameTextures.dispose();
				System.exit(0);
			}
			else if (Settings.Update())
			{
				GameCore.GameState = 4;
			}
			else if (About.Update())
			{
				GameCore.GameState = 6;
			}
		}
	}
	public void Draw(SpriteBatch sp)
	{
		Start.Draw(sp);
		Exit.Draw(sp);
		Settings.Draw(sp);
		About.Draw(sp);
	}
}
