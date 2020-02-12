package com.koopahtmaniac.roadrusher.Updaters;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.Objects.Coin;

public class CoinSpawner 
{
	float SpawnTimer = 1.0f;
	float ResetSpawnTimer =1.0f;
	Random RandomeSpawner = new Random();
	Random SelectedTrackSpawner = new Random();
	int LastSpawnLeft =99;
	int LastSpawnRight =99;
	int LastSpawnLeft2 =99;
	int LastSpawnRight2 =99;
	private TextureRegion texture;
	
	public CoinSpawner(TextureRegion texture) 
	{
		this.texture = texture;
	}
	public void Update(float GameTime)
	{
		RandomeSpawner.setSeed(RandomeSpawner.nextLong());
		SelectedTrackSpawner.setSeed(RandomeSpawner.nextLong());
		
		if ((SpawnTimer -= GameTime) <= 0)
		{
			SpawnTimer = ResetSpawnTimer;
			switch (RandomeSpawner.nextInt(2)) 
			{
				case 0:
				{
					if (true)
					{
						switch (SelectedTrackSpawner.nextInt(3)) 
						{
							case 0:
							{
								if (LastSpawnLeft != 0 &&  LastSpawnLeft2 !=0)
									GameCore.MainCoins.Add(new Coin(50, -40, texture));
								LastSpawnLeft2 = LastSpawnLeft;
								LastSpawnLeft = 0;
								
								break;
							}
							case 1:
							{
								if (LastSpawnLeft != 1 &&  LastSpawnLeft2 !=1)
									GameCore.MainCoins.Add(new Coin(130, -40, texture));
								LastSpawnLeft2 = LastSpawnLeft;
								LastSpawnLeft = 1;
								
								break;
							}
							case 2:
							{
								if (LastSpawnLeft != 2 &&  LastSpawnLeft2 !=2)
									GameCore.MainCoins.Add(new Coin(210, -40, texture));
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
					if (true)
					{
						switch (SelectedTrackSpawner.nextInt(5)) 
						{
							case 0:
							{
								if (LastSpawnRight != 0 &&  LastSpawnRight2 !=0)
									GameCore.MainCoins.Add(new Coin(300, -40, texture));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 0;
								break;
							}
							case 1:
							{
								if (LastSpawnRight != 1 &&  LastSpawnRight2 !=1)
									GameCore.MainCoins.Add(new Coin(380, -40, texture));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 1;
								break;
							}
							case 2:
							{
								if (LastSpawnRight != 2 &&  LastSpawnRight2 !=2)
									GameCore.MainCoins.Add(new Coin(460, -40, texture));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 2;
								break;
							}
							case 3:
							{
								if (LastSpawnRight != 3 &&  LastSpawnRight2 !=3)
									GameCore.MainCoins.Add(new Coin(540, -40, texture));
								LastSpawnRight2 = LastSpawnRight;
								LastSpawnRight = 3;
								break;
							}
							case 4:
							{
								if (LastSpawnRight != 4 &&  LastSpawnRight2 !=4)
									GameCore.MainCoins.Add(new Coin(620, -40, texture));
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
}
