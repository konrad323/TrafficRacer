package com.game.images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Item 
{
	private  BufferedImage image;
	
	private double x, y;
	private double dx, dy;
	
	public Item(String s)
	{
		try
		{
			image = ImageIO.read(new File(s));
			
			this.x = 50;
			this.y = -100;
			this.dx = this.dy = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y)
	{	
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update()
	{
		x = x + dx;
		y = y + dy;
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(image, (int) x, (int) y, null);
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getHeight()
	{
		return image.getHeight();
	}
	
	public double getWidth()
	{
		return image.getWidth();
	}
	
	public int getPixelRGB(int x, int y)
	{
		return image.getRGB(x, y);
	}
}
