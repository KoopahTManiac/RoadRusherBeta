package com.koopahtmaniac.roadrusher.Updaters;

import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.Objects.Enemy;

import java.util.Random;


public class EnemySpawner 
{
	float TimeElapsedSinceStart =0;
	float SpawnTimer = 0.4f;
	float ResetSpawnTimer =0.4f;
	float MinResetSpawnTimer =0.1f;
	Random RandomeSpawner = new Random();
	Random SelectedTrackSpawner = new Random();
	float MaxCarsOnRoadLeft = 99;
	float MaxCarsOnRoadLeftBase = 99;
	float MaxCarsOnRoadRight = 99;
	float MaxCarsOnRoadRightBase = 99;
	int LastSpawnLeft =99;
	int LastSpawnRight =99;
	int LastSpawnLeft2 =99;
	int LastSpawnRight2 =99;
	Random SpeedLimiter = new Random();
	int Base =80;
	public EnemySpawner() 
	{
		// TODO Auto-generated constructor stub
	}
	public void Update(float GameTime)
	{
		RandomeSpawner.setSeed(RandomeSpawner.nextLong());
		SelectedTrackSpawner.setSeed(RandomeSpawner.nextLong());
		TimeElapsedSinceStart +=GameTime;
		ResetSpawnTimer -= 0.00001f;
		if (!(ResetSpawnTimer < MinResetSpawnTimer))
		{
			ResetSpawnTimer -= 0.00001f;
		}
		int CorrentCar = RandomeSpawner.nextInt(GameCore.EnemyCar.size());
		if ((SpawnTimer -= GameTime) <= 0)
		{
			SpawnTimer = ResetSpawnTimer;
			switch (RandomeSpawner.nextInt(2)) 
			{
				case 0:
				{
					if (GameCore.EnemysLeft.size() < MaxCarsOnRoadLeft)
					{
						switch (SelectedTrackSpawner.nextInt(3)) 
						{
							case 0:
							{
								if (LastSpawnLeft != 0 &&  LastSpawnLeft2 !=0)
								GameCore.EnemysLeft.add(
										new Enemy(
										67-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()+0.3f,
										180));
								LastSpawnLeft2 = LastSpawnLeft;
								LastSpawnLeft = 0;
								
								break;
							}
							case 1:
							{
								if (LastSpawnLeft != 1 &&  LastSpawnLeft2 !=1)
								GameCore.EnemysLeft.add(
										new Enemy(
										148-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()+0.2f,
										180));
								LastSpawnLeft2 = LastSpawnLeft;
								LastSpawnLeft = 1;
								
								break;
							}
							case 2:
							{
								if (LastSpawnLeft != 2 &&  LastSpawnLeft2 !=2)
								GameCore.EnemysLeft.add(
										new Enemy(
										228-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, 
										GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()+0.3f,
										180));
								LastSpawnLeft2 = LastSpawnLeft;
								LastSpawnLeft = 2;
								break;
							}
						}
					}
					break;
				}
				
				case 1:
				{
					if (GameCore.EnemysRight.size() < MaxCarsOnRoadRight)
					{
						switch (SelectedTrackSpawner.nextInt(5)) 
						{
							case 0:
							{
								if (LastSpawnRight != 0 &&  LastSpawnRight2 !=0)
								GameCore.EnemysRight.add(
										new Enemy(
										310-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, 
										GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()*1f+1.5f,
										0));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 0;
								break;
							}
							case 1:
							{
								if (LastSpawnRight != 1 &&  LastSpawnRight2 !=1)
								GameCore.EnemysRight.add(
										new Enemy(
										395-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, 
										GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()*1f+1.5f,
										0));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 1;
								break;
							}
							case 2:
							{
								if (LastSpawnRight != 2 &&  LastSpawnRight2 !=2)
								GameCore.EnemysRight.add(
										new Enemy(
										475-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, 
										GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()*1f+1.5f,
										0));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 2;
								break;
							}
							case 3:
							{
								if (LastSpawnRight != 3 &&  LastSpawnRight2 !=3)
								GameCore.EnemysRight.add(
										new Enemy(
										555-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, 
										GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()*1f+1.5f,
										0));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 3;
								break;
							}
							case 4:
							{
								if (LastSpawnRight != 4 &&  LastSpawnRight2 !=4)
								GameCore.EnemysRight.add(
										new Enemy(
										638-(GameCore.EnemyCar.get(CorrentCar).getRegionWidth()/2*(GameCore.UseLowResGraphics+1)*GameCore.WS), 
										-120, 
										GameCore.EnemyCar.get(CorrentCar),
										SpeedLimiter.nextFloat()*1f+1.5f,
										0));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 4;
								break;
							}
						}
					}
					break;
				}
			}
			SpawnTimer = ResetSpawnTimer;
		}
	}
	public void Reset() 
	{
		ResetSpawnTimer = 0.4f;
	}
}
