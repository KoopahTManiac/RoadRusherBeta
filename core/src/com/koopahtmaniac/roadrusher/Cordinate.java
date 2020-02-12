package com.koopahtmaniac.roadrusher;

public class Cordinate
{
	public Cordinate() 
	{
		// TODO Auto-generated constructor stub
	}
	public static float P(float p)
	{
		return GameCore.Height-p;
	}
	public static int P(int p)
	{
		return (int)(GameCore.Height-p);
	}
}
