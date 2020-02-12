package com.koopahtmaniac.roadrusher.MenuHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.Button;
import com.koopahtmaniac.roadrusher.GameCore;

public class PauseMenu 
{
	private Button Resume, Exit, Settings, About;
	public PauseMenu(TextureRegion ResumeTex, TextureRegion ExitTex, TextureRegion SettingsTex, TextureRegion AboutTex, int Width, int Height) 
	{
		this.Resume = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2-100), ResumeTex);
		this.Settings = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2), SettingsTex);
		
		this.About = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2+100), AboutTex);
		this.Exit = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2+200), ExitTex);
	}
	public void Update()
	{
		if (Gdx.input.isTouched())
		{
			if (Resume.Update())
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
				GameCore.GameState = 9;
			}
			else if (About.Update())
			{
				GameCore.GameState = 8;
			}
		}
	}
	public void Draw(SpriteBatch sp)
	{
		Resume.Draw(sp);
		Exit.Draw(sp);
		Settings.Draw(sp);
		About.Draw(sp);
	}
}
