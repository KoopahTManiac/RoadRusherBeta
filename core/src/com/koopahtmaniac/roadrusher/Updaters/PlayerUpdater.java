package com.koopahtmaniac.roadrusher.Updaters;


import com.badlogic.gdx.math.Rectangle;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.Variables.RotatedRectangle;

public class PlayerUpdater 
{
	public static Rectangle ResetRectM = new Rectangle(0, 0, 0, 0);
	public static void Update(float GameTime)
	{
		if (GameCore.MainPlayer.Explosion)
		{
			GameCore.MainPlayer.Update(GameTime);
			GameCore.MainPlayer.TiltX = 0;
			GameCore.MainPlayer.TiltY = 0;
			GameCore.MainPlayer.TiltXOld =0;
			GameCore.MainPlayer.CollisionBox = new RotatedRectangle(GameCore.MainPlayer.X, GameCore.MainPlayer.Y, GameCore.MainPlayer.Width, GameCore.MainPlayer.Height,GameCore.MainPlayer.Rotation);
			if (GameCore.MainExplosion.LifeTime < 0)
			{
				if (GameCore.MainPlayer.Lives >0)
				{
					GameCore.EnemysLeft.clear();
					GameCore.EnemysRight.clear();
					GameCore.MainPlayer.Lives -= 1;
					GameCore.MainPlayer.X = GameCore.Width/2-GameCore.MainPlayer.texture.getRegionWidth()/2;
					GameCore.MainPlayer.Y = GameCore.Height-GameCore.MainPlayer.texture.getRegionHeight();
					GameCore.MainPlayer.Rotation = 0;
					GameCore.MainPlayer.Explosion = false;
					GameCore.MainPlayer.ExplosionLeft = false;
					GameCore.MainPlayer.ExplosionRight = false;
					GameCore.MainTireTracks.Reset();
				}
				else
				{
					GameCore.EnemysLeft.clear();
					GameCore.EnemysRight.clear();
					GameCore.GameState = 3;
				}
			}
			if (GameCore.MainPlayer.ExplosionLeft)
			{
				GameCore.MainExplosion.X = GameCore.MainPlayer.X-32*GameCore.WS;
				GameCore.MainExplosion.Y = GameCore.MainPlayer.Y-32*GameCore.WS;
				GameCore.MainExplosion.Play(GameTime);
			}
			else if (GameCore.MainPlayer.ExplosionRight)
			{
				GameCore.MainExplosion.X = GameCore.MainPlayer.X+GameCore.MainPlayer.texture.getRegionWidth()*GameCore.WS-32*GameCore.WS;
				GameCore.MainExplosion.Y = GameCore.MainPlayer.Y-32*GameCore.WS;
				GameCore.MainExplosion.Play(GameTime);
			}
			else
			{
				GameCore.MainExplosion.Stop();
			}
			
		}
	}
}
