package com.koopahtmaniac.roadrusher.Items;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.Objects.Coin;

import java.util.ArrayList;


public class Coins
{
	public ArrayList<Coin> Coins = new ArrayList<Coin>();
	public Coins() 
	{
		// TODO Auto-generated constructor stub
	}
	public void Update(float GameTime)
	{
		for (int i =Coins.size()-1; i > 0;i--)
		{
			if(Coins.get(i).Y > GameCore.Height || Coins.get(i).Consumed)
			{
				Coins.remove(i);
			}
			else
			{
				Coins.get(i).Y += ((GameCore.MainRoad.BaseSpeed * GameCore.MainRoad.SpeedModifier) / (4*GameCore.SlowMotion+1))  * (GameTime/GameCore.FrameLimiter);
				Coins.get(i).Update(GameTime);
			}
			
		}
		
	}
	public void Add(Coin coin)
	{
		Coins.add(coin);
	}
	public void Draw(SpriteBatch spritebatch)
	{
		for (Coin coin : this.Coins) 
		{
			coin.Draw(spritebatch);
		}
	}
}
