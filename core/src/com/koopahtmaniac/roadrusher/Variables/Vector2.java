package com.koopahtmaniac.roadrusher.Variables;

public class Vector2 
{
	public float X;
	public float Y;
	public Vector2(float X, float Y) 
	{
		this.X = X;
		this.Y = Y;
	}
	public void Rotate(float angle, Vector2 Orgin)
	{
		this.X = this.X-Orgin.X;
		this.Y = this.Y-Orgin.Y;
		
		double c = Math.sin((-angle+90)*(Math.PI/180));
		double s = Math.cos((-angle-90)*(Math.PI/180));
		
		this.X = (float)(this.X * c - this.Y * s);
		this.Y = (float)(this.X * s + this.Y * c);
		this.X += Orgin.X;
		this.Y += Orgin.Y;
	}
}
