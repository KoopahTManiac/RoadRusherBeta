package com.koopahtmaniac.roadrusher;

import java.awt.Font;
import java.util.ArrayList;

import sun.util.logging.resources.logging;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;
import com.koopahtmaniac.roadrusher.Animation.Explosion;
import com.koopahtmaniac.roadrusher.Items.Coins;
import com.koopahtmaniac.roadrusher.MainObjects.Gui;
import com.koopahtmaniac.roadrusher.MenuHandler.AboutMenu;
import com.koopahtmaniac.roadrusher.MenuHandler.MainMenu;
import com.koopahtmaniac.roadrusher.MenuHandler.PauseAboutMenu;
import com.koopahtmaniac.roadrusher.MenuHandler.PauseMenu;
import com.koopahtmaniac.roadrusher.MenuHandler.PauseSettingsMenu;
import com.koopahtmaniac.roadrusher.MenuHandler.SettingsMenu;
import com.koopahtmaniac.roadrusher.Objects.Enemy;
import com.koopahtmaniac.roadrusher.Objects.Player;
import com.koopahtmaniac.roadrusher.Objects.Road;
import com.koopahtmaniac.roadrusher.Particles.Grind;
import com.koopahtmaniac.roadrusher.Particles.TireTracks;
import com.koopahtmaniac.roadrusher.Updaters.CoinSpawner;
import com.koopahtmaniac.roadrusher.Updaters.CoinUpdater;
import com.koopahtmaniac.roadrusher.Updaters.EnemySpawner;
import com.koopahtmaniac.roadrusher.Updaters.PlayerUpdater;
import com.koopahtmaniac.roadrusher.Updaters.UpdateEnemys;

public class GameCore implements ApplicationListener 
{
	
	
	//////////////////////////////////////////////////////////
	////                  Game Settings                  ////

	boolean MusicStarted = false;
	public static TextureAtlas GameTextures;
	public static TextureAtlas LowRes,HighRes;
	public static boolean HD = false;
	public static boolean PlayMusic = true;
	public static float Height = 1366;
	public static float Width = 768;
	public static OrthographicCamera Cam;
	public static int CorrentCar =0;
	public static int GameState = 0; 
	public static int SlowMotion = 0;
	public static int UseLowResGraphics = 1;
	
	/////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////
	//// 				Menu Handler					////
	public static boolean CanClick = true;
	
	
	
	////////////////////////////////////////////////////////
	
	

	
	
	
	//private TextureRegion RedDot;
	
	private CoinUpdater MainCoinUpdater = new CoinUpdater();
	private EnemySpawner MainEnemySpawner = new EnemySpawner();
	private SpriteBatch batch;
	private boolean ResetExplosion = false;
	private int G =0;
	private static CoinSpawner MainCoinSpawner;
	private static Grind MainGrind;
	private static Gui MainGuiGameRunning;
	private static Gui MainGuiGameRunningPoints;
	private static TextureRegion CoinTexture;
	private static TextureRegion GameOver;
	private static float BaseX = 742f;
	private static float BaseY = 83f;
	
	
	public Music BackgroudMusic;
	public float GameTime =0;
	public float RespawnTime = 0.03666666666f;
	public float RespawnTimeReset = 0.03666666666f;
	private float WSS;
	private float HSS;
	public static ArrayList<Enemy> EnemysLeft = new ArrayList<Enemy>();
	public static ArrayList<Enemy> EnemysRight = new ArrayList<Enemy>();
	public static ArrayList<TextureRegion> EnemyCar = new ArrayList<TextureRegion>();
	public static ArrayList<TextureRegion> PlayerCars = new ArrayList<TextureRegion>();
	public static BitmapFont Font1;
	public static Coins MainCoins = new Coins();
	public static Explosion MainExplosion;
	public static IReqHandler ExternalHandler;
	public static MainMenu MainMenuHandler;
	public static Player MainPlayer;
	public static Preferences prefs;
	public static Rectangle ClickPos =new Rectangle(0, 0, 1, 1);
	public static Road MainRoad;
	public static SettingsMenu MainSettingsMenu;
	public static PauseMenu MainPauseMenu;
	public static PauseAboutMenu MainPauseAboutMenu;
	public static PauseSettingsMenu MainPauseSettingsMenu;
	public static AboutMenu MainAboutMenu;
	public static Texture LoadingScreen;
	public static TextureRegion Start,Back,Exit,Settings,MusicOn,MusicOff,ChangeCar,HDON,HDOFF,Resume,AboutGame,AboutGameBackground;;
	
	public static TireTracks MainTireTracks;
	public static boolean FinishedLodingGame = false;
	public static float FrameLimiter = 0.011f;
	public static float SpawnTimer = FrameLimiter*1.1f;
	public static float SpawnTimerReset = FrameLimiter*1.1f;
	public static float HS;
	public static float MaxY,MinY,MaxTilt;
	public static float SlowMotionPoints = 0f;
	public static float SlowMotionPointsMax = 400f;
	public static float Speed;
	public static float WS;
	public static int GameScore = 0;
	public static int MaxScore =0;
	
	
	public static GlyphLayout glyphLayout = new GlyphLayout();
	
	
	
	
	public static void MiniInit()
	{
		Gdx.graphics.setVSync(false);
		Font1 = new BitmapFont();
		Font1.getData().setScale(1.3f);
		if (UseLowResGraphics == 1)
		{
			GameTextures = LowRes;
		}
		else
		{
			GameTextures = HighRes;
		}
		
		Back = GameTextures.findRegion("Back");
		ChangeCar = GameTextures.findRegion("ChangeCar");
		Exit = GameTextures.findRegion("Exit");
		HDOFF = GameTextures.findRegion("HDOFF");
		HDON = GameTextures.findRegion("HDON");
		MusicOff = GameTextures.findRegion("MOFF");
		MusicOn = GameTextures.findRegion("MON");
		Settings = GameTextures.findRegion("Settings");
		Start = GameTextures.findRegion("Start");
		AboutGame = GameTextures.findRegion("About");
		Resume = GameTextures.findRegion("Resume");
		AboutGameBackground = GameTextures.findRegion("AboutGame");
		PlayerCars = new ArrayList<TextureRegion>();
		PlayerCars.add(GameTextures.findRegion("DodgeRam"));
		PlayerCars.add(GameTextures.findRegion("Car1"));
		PlayerCars.add(GameTextures.findRegion("Car2"));
		
		EnemyCar = new ArrayList<TextureRegion>();
		EnemyCar.add(GameTextures.findRegion("DodgeRam"));
		EnemyCar.add(GameTextures.findRegion("Car1"));
		EnemyCar.add(GameTextures.findRegion("Car2"));
		EnemyCar.add(GameTextures.findRegion("DodgeRam"));
		EnemyCar.add(GameTextures.findRegion("Car2"));
		EnemyCar.add(GameTextures.findRegion("Car1"));
		
		
		CoinTexture = GameTextures.findRegion("coingold");
		GameOver = GameTextures.findRegion("GameOverScreen");
		MainCoinSpawner = new CoinSpawner(CoinTexture);
		MainGrind = new Grind(GameTextures.findRegion("GrindRed"), GameTextures.findRegion("GrindYellow"), GameTextures.findRegion("GrindWhite"), GameTextures.findRegion("GrindRed"));
		MainGuiGameRunning = new Gui(0, 0, GameTextures.findRegion("meters"));
		MainGuiGameRunningPoints = new Gui(BaseX, BaseY, GameTextures.findRegion("slowmotionmeterpoints"));
		
		MainPlayer = new Player(MainPlayer.X, MainPlayer.Y, 0, 0, PlayerCars.get(CorrentCar));
		MainRoad = new Road(GameTextures.findRegion("vag"));
		MainTireTracks = new TireTracks(GameTextures.findRegion("TireTrack"));
		
		//////////////////////////////////////////////////////////////
		//// 						Menu's  				 	  ////
		
		MainMenuHandler = new MainMenu(Start, Exit, Settings, AboutGame, (int)Width, (int)Height);
		MainSettingsMenu =  new SettingsMenu(MusicOn, MusicOff, Back, ChangeCar,HDON,HDOFF, (int)Width, (int)Height);
		MainAboutMenu = new AboutMenu(Back, AboutGameBackground, (int)Width, (int)Height);
		MainPauseAboutMenu = new PauseAboutMenu(Back, AboutGameBackground, (int)Width, (int)Height);
		MainPauseMenu = new PauseMenu(Resume, Exit, Settings, AboutGame, (int)Width, (int)Height);
		MainPauseSettingsMenu = new PauseSettingsMenu(MusicOn, MusicOff, Back, ChangeCar, HDON, HDOFF, (int)Width, (int)Height);
		
		//////////////////////////////////////////////////////////////
		
		
		
	}
	public void Init()
	{
		Cam = new OrthographicCamera(Width, Height);
		batch = new SpriteBatch();
		Cam.translate(Width/2, Height/2);
		Cam.update();
		Gdx.graphics.setVSync(false);
		
		batch.maxSpritesInBatch = 100;
		
		MusicStarted = false;
		EnemyCar = new ArrayList<TextureRegion>();
		EnemysLeft = new ArrayList<Enemy>();
		EnemysRight = new ArrayList<Enemy>();
		MainEnemySpawner = new EnemySpawner();
		MainCoinUpdater = new CoinUpdater();
		Font1 = new BitmapFont();
		
		HighRes = new TextureAtlas(Gdx.files.internal("HighRes/pack"));
		LowRes = new TextureAtlas(Gdx.files.internal("LowRes/pack"));
		MainExplosion = new Explosion(0, 0, new Texture(Gdx.files.internal("GameTextures/Effects/explosion.png")));
		
		if (UseLowResGraphics == 1)
		{
			GameTextures = LowRes;
		}
		else
		{
			GameTextures = HighRes;
		}
		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		WS = 1;//w/768;
		HS = 1;//h/1366;
		WSS = w/768;
		HSS = h/1366;
		
		
		MainPlayer = new Player(Gdx.graphics.getWidth()/2, 1366-116,0,0, GameTextures.findRegion("DodgeRam"));
		
		
		
		MaxTilt = 3.5f;
		
		//RedDot = GameTextures.findRegion("RedSquere");
		
		
		if (BackgroudMusic != null)
		{
			BackgroudMusic.stop();
			BackgroudMusic.dispose();
		}
		BackgroudMusic = Gdx.audio.newMusic(Gdx.files.internal("Music.ogg"));
		BackgroudMusic.setLooping(true);
//		BackgroudMusic.play();
		MiniInit();
		
	}


	public GameCore() 
	{
		ExternalHandler = new IReqHandler() 
		{
			
			@Override
			public void showAd(boolean show) 
			{
				
			}
			
			@Override
			public void showFullAd(boolean show) 
			{
				
			}
		};
	}
	public GameCore(IReqHandler irh) 
	{
		ExternalHandler = irh;
	}
	@Override
	public void create() 
	{
		Cam = new OrthographicCamera(Width, Height);
		Cam.translate(Width/2, Height/2);
		Cam.update();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		WSS = w/768;
		HSS = h/1366;
		HS = 1;
		WS = 1;
		Gdx.input.setCatchBackKey(true);
		Gdx.graphics.setVSync(false);
		prefs = Gdx.app.getPreferences("GameSettings");
		GameState = 0;
		if (prefs.contains("Score"))
		{
			MaxScore = prefs.getInteger("Score");
		}
		if (prefs.contains("Music"))
		{
			PlayMusic = prefs.getBoolean("Music");
		}
		if (prefs.contains("HD"))
		{
			UseLowResGraphics = prefs.getInteger("HD");
			if (UseLowResGraphics == 1)
			{
				HD = false;
			}
			else
			{
				HD = true;
			}
		}
		FinishedLodingGame = false;
		LoadingScreen = new Texture(Gdx.files.internal("MainUi/LoadingScreen.png"));
		batch = new SpriteBatch();
		WS = 1;
		HS = 1;

		//Init();
	}
	@Override
	public void dispose() 
	{
		batch.dispose();
		GameTextures.dispose();
		MainExplosion.Dispose();
		HighRes.dispose();
		LowRes.dispose();
		LoadingScreen.dispose();
	}
	

	@Override
	public void pause() 
	{
		prefs.flush();
		if (prefs.contains("Score"))
		{
			prefs.remove("Score");
			prefs.putInteger("Score", MaxScore);
			prefs.flush();
		}
		else
		{
			prefs.putInteger("Score", MaxScore);
			prefs.flush();
		}
	}

	@Override
	public void render() 
	{
		//Gdx.graphics.setVSync(false);
		//loger.log();
		if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			if (GameState == 2 || GameState == 8 || GameState == 9)
			{
				if (prefs.contains("Score"))
				{
					prefs.remove("Score");
					prefs.putInteger("Score", MaxScore);
					prefs.flush();
				}
				else
				{
					prefs.putInteger("Score", MaxScore);
					prefs.flush();
				}
				GameState = 7;
			}
			else if (GameState == 4 || GameState == 6)
			{
				GameState = 1;
			}
			if (GameState == 1)
			{
				if (prefs.contains("Score"))
				{
					prefs.remove("Score");
					prefs.putInteger("Score", MaxScore);
					prefs.flush();
				}
				else
				{
					prefs.putInteger("Score", MaxScore);
					prefs.flush();
				}
				System.exit(0);
			}
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glSampleCoverage(1, false);
		if(Gdx.graphics.getBufferFormat().coverageSampling)
		{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		}
		Update(Gdx.graphics.getRawDeltaTime());
		
		//Start Draw
		batch.setProjectionMatrix(Cam.combined);
		batch.begin();
		//Draw Player
		switch (GameState) 
		{
			case 0:
			{
				if (!FinishedLodingGame)
				{
					SDraw.Draw(batch, LoadingScreen, 0, 0,768,1366);
					FinishedLodingGame = true;
				}
				else
				{
					SDraw.Draw(batch, LoadingScreen, 0, 0,768,1366);
					batch.end();
					Init();
					batch.begin();
					GameState = 1;
				}
				break;
			}
			case 1:
			{
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl20.glSampleCoverage(1, false);
				if (Gdx.input.isTouched() == false)
				{
					CanClick = true;
				}
				MainMenuHandler.Draw(batch);
				break;
			}
			case 2:
			{
				MainRoad.Draw(batch);
				MainTireTracks.Draw(batch);
				MainCoins.Draw(batch);
				MainPlayer.Draw(batch);
				MainGrind.Draw(batch);
				
				for (G = EnemysLeft.size()-1; G >= 0; G--)
				{
					EnemysLeft.get(G).Draw(batch);
					//Enemy.CollisionBox.Draw(RedDot, batch);
				}
				for (G = EnemysRight.size()-1; G >= 0; G--)
				{
					EnemysRight.get(G).Draw(batch);
					//Enemy.CollisionBox.Draw(RedDot, batch);
				}
//				MainPlayer.CollisionBox.Draw(RedDot, batch);
				MainExplosion.Draw(batch);
				
				
				MainGuiGameRunningPoints.Draw(batch);
				MainGuiGameRunning.Draw(batch);

				glyphLayout.setText(Font1,String.valueOf((int)GameScore));

				SDraw.DrawText(batch,String.valueOf((int)GameScore),180-(int)(glyphLayout.width/2),13);
				glyphLayout.setText(Font1,String.valueOf((int)MaxScore));
				SDraw.DrawText(batch,String.valueOf((int)MaxScore),(int)(635-(glyphLayout.width/2)),13);
				//SDraw.DrawText(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 10, 10);
				break;
			}
			case 4:
			{
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl20.glSampleCoverage(1, false);
				if (Gdx.input.isTouched() == false)
				{
					CanClick = true;
				}
				MainSettingsMenu.Draw(batch);
				break;
			}
			case 5:
			{
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl20.glSampleCoverage(1, false);
				SDraw.DrawRotated(batch, GameOver, 0, 0, 0f);
				break;
			}
			case 6:
			{
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl20.glSampleCoverage(1, false);
				if (Gdx.input.isTouched() == false)
				{
					CanClick = true;
				}
				MainAboutMenu.Draw(batch);
				break;
			}
			case 7:
			{
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl20.glSampleCoverage(1, false);
				if (Gdx.input.isTouched() == false)
				{
					CanClick = true;
				}
				MainPauseMenu.Draw(batch);
				break;
			}
			case 8:
			{
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl20.glSampleCoverage(1, false);
				if (Gdx.input.isTouched() == false)
				{
					CanClick = true;
				}
				MainPauseAboutMenu.Draw(batch);
				break;
			}
			case 9:
			{
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Gdx.gl20.glSampleCoverage(1, false);
				if (Gdx.input.isTouched() == false)
				{
					CanClick = true;
				}
				MainPauseSettingsMenu.Draw(batch);
				break;
			}
		
		}
		//End Draw
		batch.end();
	}

	@Override
	public void resize(int width, int height) 
	{
		//float w = width;
		//float h = height;
		//WS = w/768;
		//HS = h/1366;
	}
	@Override
	public void resume() 
	{
		//Init();
		//GameState = 2;
		//FinishedLodingGame = false;
		
	}
	public void Update(float GameTime)
	{
		if (BackgroudMusic != null)
		{
			if (PlayMusic)
			{
				if (!MusicStarted)
				{
					BackgroudMusic.play();
					MusicStarted = true;
				}
			}
			else if (MusicStarted)
			{
				BackgroudMusic.stop();
				MusicStarted = false;
			}
		}
		ClickPos.x = Gdx.input.getX()/WSS;
		ClickPos.y = Gdx.input.getY()/HSS;
		switch (GameState) 
		{
			case 0:
			{
				break;
			}
			case 1:
			{
				MainMenuHandler.Update();
				break;
			}
			case 2:
			{
				Speed = ((MainRoad.BaseSpeed * MainRoad.SpeedModifier) / (4*SlowMotion+1));
				MainGuiGameRunningPoints.Height = (SlowMotionPoints/SlowMotionPointsMax)*933f/(UseLowResGraphics+1);
				MainGuiGameRunningPoints.Y = BaseY+933f-(SlowMotionPoints/SlowMotionPointsMax)*933f;
				PlayerUpdater.Update(GameTime);
				if (!MainPlayer.Explosion)
				{
					MainGrind.Update(GameTime);
					MainEnemySpawner.Update(GameTime);
					UpdateEnemys.Update(GameTime);
					MainRoad.Update(GameTime);
					MainPlayer.Move(Gdx.input,GameTime);
					MainPlayer.Update(GameTime);
					
					MainCoinUpdater.Update(GameTime);
					MainCoinSpawner.Update(GameTime);
					MainTireTracks.Update(GameTime);
					if (!ResetExplosion)
					{
						MainExplosion.Stop();
					}
					GameScore += GameTime*5;
					UpdateMaxScore();
				}
				SpawnTimer -= GameTime;
				if (MainPlayer.Drift)
				{
					
					if (SpawnTimer <=0)
					{
						SpawnTimer = SpawnTimerReset;
						MainTireTracks.Add(MainPlayer.X, MainPlayer.Y, 0, 0, MainPlayer.Rotation);
					}
				}
				
				break;
			}
			case 3:
			{
				MainRoad.ResetSpeed();
				MainEnemySpawner.Reset();
				System.gc();
				GameState = 5;
				break;
			}
			
			case 4:
			{
				MainSettingsMenu.Update();
				break;
			}
			case 5:
			{
				GameScore = 0;
				Gdx.input.setCatchMenuKey(true);
				ExternalHandler.showFullAd(true);
				if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.MENU))
				{
					MainPlayer.Lives = MainPlayer.ResetLives;
					ExternalHandler.showFullAd(false);
					GameState = 2;
				}
				break;
			}
			case 6:
			{
				MainAboutMenu.Update();
				break;
			}
			case 7:
			{
				MainPauseMenu.Update();
				break;
			}
			case 8:
			{
				MainPauseAboutMenu.Update();
				break;
			}
			case 9:
			{
				MainPauseSettingsMenu.Update();
				break;
			}
		}
		
		
	}
	public void UpdateMaxScore()
	{
		if (MaxScore < GameScore)
		{
			MaxScore = GameScore;
		}
	}
	public static void SaveHighScore()
	{
		if (prefs.contains("Score"))
		{
			prefs.remove("Score");
			prefs.putInteger("Score", MaxScore);
			prefs.flush();
		}
		else
		{
			prefs.putInteger("Score", MaxScore);
			prefs.flush();
		}
	}
	public static void SaveSettings()
	{
		if (prefs.contains("Music"))
		{
			prefs.remove("Music");
			prefs.putBoolean("Music", PlayMusic);
			prefs.flush();
		}
		else
		{
			prefs.putBoolean("Music", PlayMusic);
			prefs.flush();
		}
		
		if (prefs.contains("HD"))
		{
			prefs.remove("HD");
			prefs.putInteger("HD", UseLowResGraphics);
			prefs.flush();
		}
		else
		{
			prefs.putInteger("HD", UseLowResGraphics);
			prefs.flush();
		}
	}
}
