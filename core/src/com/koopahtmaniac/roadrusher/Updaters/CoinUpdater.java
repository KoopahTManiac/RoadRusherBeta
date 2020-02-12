package com.koopahtmaniac.roadrusher.Updaters;

import com.koopahtmaniac.roadrusher.GameCore;


public class CoinUpdater 
{
	public CoinUpdater() 
	{
		// TODO Auto-generated constructor stub
	}
	public void Update(float GameTime)
	{
		GameCore.MainCoins.Update(GameTime);
	}
}
