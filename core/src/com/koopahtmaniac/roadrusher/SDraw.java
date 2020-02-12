package com.koopahtmaniac.roadrusher;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SDraw
{
	//texture
	
	public static void Draw(SpriteBatch sp,Texture tex, float X, float Y)
	{
		sp.draw(tex,X,Cordinate.P(Y+tex.getHeight()),tex.getWidth()*(GameCore.UseLowResGraphics+1),tex.getHeight()*(GameCore.UseLowResGraphics+1));
	}
	public static void Draw(SpriteBatch sp,Texture tex, float X, float Y,float width, float height)
	{
		sp.draw(tex,X,Cordinate.P(Y+height),width,height);
	}
	public static void DrawRotated(SpriteBatch sp,Texture tex, float X, float Y,float width, float height, float Rotation)
	{
		sp.draw(tex, X, Cordinate.P(Y+height), (tex.getWidth()*(GameCore.UseLowResGraphics+1)/2), (tex.getHeight()*(GameCore.UseLowResGraphics+1)/2), width*(GameCore.UseLowResGraphics+1), height*(GameCore.UseLowResGraphics+1), 1, 1, Rotation, 0, 0, tex.getWidth()*(GameCore.UseLowResGraphics+1), tex.getHeight()*(GameCore.UseLowResGraphics+1), false, false);
	}
	
	public static void DrawRotatedFromTopLeft(SpriteBatch sp,Texture tex, float X, float Y,float width, float height, float Rotation)
	{
		sp.draw(tex, X, Cordinate.P(Y+height), 0, height, width, height, 1, 1, Rotation, 0, 0, tex.getWidth()*(GameCore.UseLowResGraphics+1), tex.getHeight()*(GameCore.UseLowResGraphics+1), false, false);
	}
	public static void DrawRotatedFromTopCenter(SpriteBatch sp,Texture tex, float X, float Y,float width, float height, float Rotation)
	{
		sp.draw(tex, X, Cordinate.P(Y+height), (tex.getWidth()*(GameCore.UseLowResGraphics+1)/2), height, width, height, 1f,1f,Rotation, 0, 0, tex.getWidth()*(GameCore.UseLowResGraphics+1), tex.getHeight()*(GameCore.UseLowResGraphics+1), false, false);
	}
	
	//texture
	
	
	//Texture Region
	
	public static void DrawTile(SpriteBatch sp,TextureRegion tex, float X, float Y)
	{
		sp.draw(tex, X, Cordinate.P(Y+tex.getRegionHeight()*(GameCore.UseLowResGraphics+1)),tex.getRegionWidth()*(GameCore.UseLowResGraphics+1),tex.getRegionHeight()*(GameCore.UseLowResGraphics+1));
	}
	public static void DrawRotated(SpriteBatch sp, TextureRegion tex, float X, float Y, int width, int height, float Rotation) 
	{
		sp.draw(tex, X, Cordinate.P(Y+height*(GameCore.UseLowResGraphics+1)), (tex.getRegionWidth()*(GameCore.UseLowResGraphics+1)/2), (tex.getRegionHeight()*(GameCore.UseLowResGraphics+1)/2),width*(GameCore.UseLowResGraphics+1),height*(GameCore.UseLowResGraphics+1),1,1,Rotation);
	}
	public static void DrawRotated(SpriteBatch sp, TextureRegion tex, float X, float Y, float Rotation) 
	{
		sp.draw(tex, X, Cordinate.P(Y+tex.getRegionHeight()*(GameCore.UseLowResGraphics+1)), (tex.getRegionWidth()*(GameCore.UseLowResGraphics+1)/2), (tex.getRegionWidth()*(GameCore.UseLowResGraphics+1)/2),tex.getRegionWidth()*(GameCore.UseLowResGraphics+1),tex.getRegionHeight()*(GameCore.UseLowResGraphics+1),1,1,Rotation);
	}
	public static void DrawRotatedFromTopLeft(SpriteBatch sp,TextureRegion tex, float X, float Y,float width, float height, float Rotation)
	{
		sp.draw(tex, X, Cordinate.P(Y+height*(GameCore.UseLowResGraphics+1)), 0, height*(GameCore.UseLowResGraphics+1), width*(GameCore.UseLowResGraphics+1), height*(GameCore.UseLowResGraphics+1), 1, 1, Rotation);
	}
	//Texture Region
	
	
	public static void DrawText(SpriteBatch sp,String StringData, int X, int Y) 
	{
		GameCore.Font1.draw(sp, StringData, X, Cordinate.P(Y+18));
	}
}
