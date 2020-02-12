package com.koopahtmaniac.roadrusher.Updaters;

import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.Objects.Enemy;


public class UpdateEnemys 
{
	private static Enemy Corrent;
	public static void Update(float GameTime)
	{
		for(int i = GameCore.EnemysLeft.size()-1; i >=0; i--) //move
		{
			Corrent = GameCore.EnemysLeft.get(i);
			Corrent.Update(GameTime);
			if (GameCore.MainPlayer.Intersect(Corrent.CollisionBox))
			{
				if (GameCore.MainPlayer.Y > Corrent.Y+Corrent.Height-5 && !GameCore.MainPlayer.Grind)
				{
					GameCore.MainPlayer.Explosion = true;
					GameCore.MainPlayer.ExplosionLeft = true;
					GameCore.MainPlayer.ExplosionRight = true;
				}
				else if (GameCore.MainPlayer.IntersectRight(Corrent.CollisionBox))
				{
					if (GameCore.MainPlayer.TiltX >GameCore.MaxTilt || GameCore.MainPlayer.TiltX <-GameCore.MaxTilt)
					{
						GameCore.MainPlayer.Explosion = true;
						GameCore.MainPlayer.ExplosionLeft = true;
					}
					else
					{
						GameCore.MainPlayer.GrindLeft = true;
						GameCore.MainPlayer.Grind = true;
						GameCore.MainPlayer.GrindTimeOut = GameCore.MainPlayer.GrindTimeOutReset;
						GameCore.MainPlayer.X = Corrent.X + Corrent.texture.getRegionWidth()*(1+GameCore.UseLowResGraphics);
					}
				}
				else if (GameCore.MainPlayer.IntersectLeft(Corrent.CollisionBox))
				{
					
					if (GameCore.MainPlayer.TiltX >GameCore.MaxTilt || GameCore.MainPlayer.TiltX <-GameCore.MaxTilt)
					{
						GameCore.MainPlayer.Explosion = true;
						GameCore.MainPlayer.ExplosionRight = true;
					}
					else
					{
						GameCore.MainPlayer.GrindRight = true;
						GameCore.MainPlayer.Grind = true;
						GameCore.MainPlayer.GrindTimeOut = GameCore.MainPlayer.GrindTimeOutReset;
						GameCore.MainPlayer.X = Corrent.X - GameCore.MainPlayer.texture.getRegionWidth()*(1+GameCore.UseLowResGraphics);
					}
				}
				else
				{
					GameCore.MainPlayer.Explosion = true;
					GameCore.MainPlayer.ExplosionLeft = true;
					GameCore.MainPlayer.ExplosionRight = true;
				}
			}
			if (Corrent.Y > GameCore.Height)
			{
				GameCore.EnemysLeft.remove(i);
			}
		}
		for(int i=GameCore.EnemysRight.size()-1; i >=0; i--) //move
		{
			Corrent = GameCore.EnemysRight.get(i);
			Corrent.Update(GameTime);
			if (GameCore.MainPlayer.Intersect(Corrent.CollisionBox))
			{
				if (GameCore.MainPlayer.Y > Corrent.Y+Corrent.Height-5 && !GameCore.MainPlayer.Grind)
				{
					GameCore.MainPlayer.Explosion = true;
					GameCore.MainPlayer.ExplosionLeft = true;
					GameCore.MainPlayer.ExplosionRight = true;
				}
				else if (GameCore.MainPlayer.IntersectRight(Corrent.CollisionBox))
				{
					if (GameCore.MainPlayer.TiltX > GameCore.MaxTilt || GameCore.MainPlayer.TiltX < -GameCore.MaxTilt)
					{
						GameCore.MainPlayer.Explosion = true;
						GameCore.MainPlayer.ExplosionRight = true;
					}
					else
					{
						GameCore.MainPlayer.GrindRight = true;
						GameCore.MainPlayer.Grind = true;
						GameCore.MainPlayer.GrindTimeOut = GameCore.MainPlayer.GrindTimeOutReset;
						GameCore.MainPlayer.X = Corrent.X - GameCore.MainPlayer.texture.getRegionWidth()*(1+GameCore.UseLowResGraphics);
					}
				}
				else if (GameCore.MainPlayer.IntersectLeft(Corrent.CollisionBox))
				{
					if (GameCore.MainPlayer.TiltX >GameCore.MaxTilt || GameCore.MainPlayer.TiltX <-GameCore.MaxTilt)
					{
						GameCore.MainPlayer.Explosion = true;
						GameCore.MainPlayer.ExplosionLeft = true;
					}
					else
					{
						GameCore.MainPlayer.GrindLeft = true;
						GameCore.MainPlayer.Grind = true;
						GameCore.MainPlayer.GrindTimeOut = GameCore.MainPlayer.GrindTimeOutReset;
						GameCore.MainPlayer.X = Corrent.X + Corrent.texture.getRegionWidth()*(1+GameCore.UseLowResGraphics);
					}
				}
				else
				{
					GameCore.MainPlayer.Explosion = true;
					GameCore.MainPlayer.ExplosionLeft = true;
					GameCore.MainPlayer.ExplosionRight = true;
				}
			}
			if (Corrent.Y > GameCore.Height)
			{
				GameCore.EnemysRight.remove(i);
			}
		}
	}
}
