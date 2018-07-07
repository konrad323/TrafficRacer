package com.game.images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.game.window.Menu;

public class Background 
{
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms)
	{
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y)
	{
		this.x = (x * moveScale) % Menu.WIDTH;
		this.y = (y * moveScale) % Menu.HEIGHT;
	}
	
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update()
	{
		x = (x * moveScale) % Menu.WIDTH + dx;
		y = (y * moveScale) % Menu.HEIGHT + dy;
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(image, (int) x, (int) y, null);
		
		if(y < 0)
		{
			g.drawImage(image, (int) x, (int) y + Menu.HEIGHT, null);
		}
		if(y > 0)
		{
			g.drawImage(image, (int) x, (int) y - Menu.HEIGHT, null);
		}
	}
}
