package com.game.images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.window.Menu;

public class Background 
{
	private BufferedImage image;
	
	private double x, y;
	private double dx, dy;
	private double moveScale;
	
	public Background(String s, double ms)
	{
		try
		{
			image = ImageIO.read(new File(s));
			moveScale = ms;	
		}
		catch(Exception e)
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
			g.drawImage(image, (int) x, (int) y + Menu.HEIGHT, null);
		if(y > 0)
			g.drawImage(image, (int) x, (int) y - Menu.HEIGHT, null);
	}
}
