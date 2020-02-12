package com.koopahtmaniac.roadrusher.Variables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.koopahtmaniac.roadrusher.GameCore;
import com.koopahtmaniac.roadrusher.SDraw;


public class RotatedRectangle
{
	Polygon poly;
	public float width = 0, height = 0, X = 0, Y = 0;
	public Vector2 TopLeft = new Vector2(0, 0), TopRight = new Vector2(0, 0), BottomLeft = new Vector2(0, 0), BottomRight = new Vector2(0, 0);
	public float[] Data  = new float[]{0,0,width,0,width,height,0,height};
	public RotatedRectangle(float X, float Y, float width, float height, float Rotation) 
	{
		this.X = X;
		this.Y = Y;
		this.width = width;
		this.height = height;
		this.poly = new Polygon(new float[]{0,0,width,0,width,height,0,height});
		poly.setOrigin(width/2, height/2);
		poly.setPosition(X, Y);
		poly.setRotation(Rotation);
		Data = poly.getTransformedVertices();
		SetPoints();
	}
	public void SetPoints()
	{
		TopLeft.x = Data[0];
		TopLeft.y = Data[1];
		TopRight.x = Data[2];
		TopRight.y = Data[3];
		BottomRight.x = Data[4];
		BottomRight.y = Data[5];
		BottomLeft.x = Data[6];
		BottomLeft.y = Data[7];
	}
	public void Rotate(float Rotation)
	{
		poly.setRotation(-Rotation);
		Data = poly.getTransformedVertices();
		SetPoints();
	}
	public void Update(float X, float Y)
	{
		poly.setPosition(X, Y);
		Data = poly.getTransformedVertices();
		SetPoints();
	}
	public boolean Intersect(RotatedRectangle rect)
	{
		return Intersector.overlapConvexPolygons(poly, rect.poly);
	}
	public boolean IntersectLeft(RotatedRectangle rect)
	{
		if (Intersect(rect) && TopLeft.y < rect.BottomRight.y - GameCore.Speed && TopLeft.x < rect.BottomRight.x + (rect.width / 2) - 1)
        {
			return true;
        }
		return false;
	}
	public boolean IntersectRight(RotatedRectangle rect)
	{
		if (Intersect(rect) && TopLeft.y < rect.BottomRight.y - GameCore.Speed && TopLeft.x < rect.BottomRight.x - (rect.width / 2) + 1)
        {
			return true;
        }
		return false;
	}
	public void Draw(TextureRegion tex, SpriteBatch spritebatch)
    {
		
    	SDraw.DrawRotated(spritebatch, tex, Data[0]-2, Data[1]-2,0f);
    	SDraw.DrawRotated(spritebatch, tex, Data[2]-2, Data[3]-2,0f);
    	SDraw.DrawRotated(spritebatch, tex, Data[4]-2, Data[5]-2,0f);
    	SDraw.DrawRotated(spritebatch, tex, Data[6]-2, Data[7]-2,0f);
    }
}
