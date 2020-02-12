package com.koopahtmaniac.roadrusher.MenuHandler;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.Button;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.SDraw;
import com.koopahtmaniac.roadrusher.ToggleButton;

public class PauseSettingsMenu 
{
	Button Back, ChangeCar;
	ToggleButton Music,HDGraphics;
	public PauseSettingsMenu(TextureRegion MusicOn,TextureRegion MusicOff, TextureRegion Back, TextureRegion ChangeCar,TextureRegion HDON,TextureRegion HDOFF, int Width, int Height) 
	{
		this.Back = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2-100), Back);
		this.ChangeCar = new Button((int)(GameCore.Width/2), (int)(GameCore.Height/2), ChangeCar);
		this.Music = new ToggleButton((int)(GameCore.Width/2), (int)(GameCore.Height/2+100), MusicOn,MusicOff,GameCore.PlayMusic);
		this.HDGraphics = new ToggleButton((int)(GameCore.Width/2), (int)(GameCore.Height/2+200), HDON, HDOFF, GameCore.HD);
	}
	public void Update()
	{
		if (Gdx.input.isTouched())
		{
			if (Back.Update())
			{
				GameCore.GameState = 7;
			}
			else if (ChangeCar.Update())
			{
				GameCore.CorrentCar++;
				if (GameCore.CorrentCar == GameCore.PlayerCars.size())
				{
					GameCore.CorrentCar = 0;
				}
				GameCore.MainPlayer.texture = GameCore.PlayerCars.get(GameCore.CorrentCar);
			}
			else if (Music.Update())
			{
				GameCore.PlayMusic = !GameCore.PlayMusic;
				this.Music.On = GameCore.PlayMusic;
				GameCore.SaveSettings();
			}
			else if (HDGraphics.Update())
			{
				if (GameCore.UseLowResGraphics == 1)
				{
					GameCore.UseLowResGraphics = 0;
					this.HDGraphics.On = true;
					GameCore.HD = true;
					GameCore.MiniInit();
				}
				else
				{
					GameCore.UseLowResGraphics = 1;
					this.HDGraphics.On = false;
					GameCore.HD = false;
					GameCore.MiniInit();
				}
				GameCore.SaveSettings();
			}
		}
	}
	public void Draw(SpriteBatch sp)
	{
		Back.Draw(sp);
		ChangeCar.Draw(sp);
		Music.Draw(sp);
		HDGraphics.Draw(sp);
		SDraw.DrawRotated(sp, GameCore.MainPlayer.texture, GameCore.Width/2 - (GameCore.MainPlayer.texture.getRegionWidth()*2/2), 200,GameCore.MainPlayer.texture.getRegionWidth()*2,GameCore.MainPlayer.texture.getRegionHeight()*2, 0);
	}
}
