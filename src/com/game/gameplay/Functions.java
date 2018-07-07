package com.game.gameplay;


import java.awt.Rectangle;
import java.util.Random;

import com.game.images.Item;

public abstract class Functions 
{
	private static Random generator = new Random();
	
	public static boolean collision(Item car, Item c1) // transparency color number = 16777215
	{
		Rectangle r1 = new Rectangle((int) car.getX(), (int) car.getY(), (int) car.getWidth(), (int) car.getHeight());
		Rectangle r2 = new Rectangle((int) c1.getX(), (int) c1.getY(), (int) c1.getWidth(), (int) c1.getHeight());
		if(!r1.intersects(r2)) return false;
		
		for(int i = 0; i < car.getWidth(); i++)
		{
			for(int j = 0; j < car.getHeight(); j++)
			{
				if(car.getPixelRGB(i, j) != 16777215)
				{
					if(r2.contains((int) (car.getX() + i), (int)(car.getY() + j)))
					{
						if (c1.getPixelRGB((int) (car.getX() + i - c1.getX()), 
								(int)(car.getY() + j - c1.getY())) != 16777215) return true;
					}
					
				}
	
			}
		}
		
		return false;
	}
	
	public static int scoreCounter(Item car, Item c, int currentScore)
	{
		if(car.getY() == (int) c.getY() && ((car.getX() < c.getX() && c.getX() - car.getX() - car.getWidth() < 4)
				|| (car.getX() > c.getX() && car.getX() - c.getX() - c.getWidth() < 4)))
					currentScore += 100;
		
		else if(car.getY() == (int) c.getY() && ((car.getX() < c.getX() && c.getX() - car.getX() - car.getWidth() < 7)
				|| (car.getX() > c.getX() && car.getX() - c.getX() - c.getWidth() < 7)))
					currentScore += 50;
		
		else if(car.getY() == (int) c.getY() && ((car.getX() < c.getX() && c.getX() - car.getX() - car.getWidth() < 10)
				|| (car.getX() > c.getX() && car.getX() - c.getX() - c.getWidth() < 10)))
					currentScore += 25;

		return currentScore;
	}
	
	public static void generateCars(Item[] c, int from, int to)
	{
		for(int i = from; i <= to; i++)
		{
			int pol = generator.nextInt(15);
			
			if(pol == 0) c[i] = new Item("resources/sprites/enemies/car1.png");
			else if(pol == 1) c[i] = new Item("resources/sprites/enemies/car2.png");
			else if(pol == 2) c[i] = new Item("resources/sprites/enemies/car3.png");
			else if(pol == 3) c[i] = new Item("resources/sprites/enemies/car4.png");
			else if(pol == 4) c[i] = new Item("resources/sprites/enemies/car5.png");
			else if(pol == 5) c[i] = new Item("resources/sprites/enemies/car6.png");
			else if(pol == 6) c[i] = new Item("resources/sprites/enemies/car7.png");
			else if(pol == 7) c[i] = new Item("resources/sprites/enemies/car8.png");
			else if(pol == 8) c[i] = new Item("resources/sprites/enemies/car9.png");
			else if(pol == 9) c[i] = new Item("resources/sprites/enemies/car10.png");
			else if(pol == 10) c[i] = new Item("resources/sprites/enemies/car11.png");
			else if(pol == 11) c[i] = new Item("resources/sprites/enemies/car12.png");
			else if(pol == 12) c[i] = new Item("resources/sprites/enemies/truck1.png");
			else if(pol == 13) c[i] = new Item("resources/sprites/enemies/truck2.png");
			else c[i] = new Item("resources/sprites/enemies/bus.png");
		}	
	}
	
	public static void generateCarsPosition(Item[] c, int from, int to)
	{
		int pol1 = generator.nextInt(50) + 60;
		int pol2 = generator.nextInt(80) + 72;
		
		c[from].setPosition(pol1, -pol2);
		
		int suma = pol1;
		
		for(int i = from + 1; i <= to; i++)
		{
			int pol3 = generator.nextInt(40) + 60;
			pol2 = generator.nextInt(80) + 72;
			suma = suma + pol3;
			c[i].setPosition(suma, -pol2);
		}	
	}
	
	public static void generateObstacles(Item[] o, int from, int to)
	{
		for(int i = from; i <= to; i++)
		{
			int pol = generator.nextInt(4);
			
			if(pol == 0) o[i] = new Item("resources/sprites/enemies/rock1.png");
			else if(pol == 1) o[i] = new Item("resources/sprites/enemies/rock2.png");
			else if(pol == 2) o[i] = new Item("resources/sprites/enemies/tree1.png");
			else o[i] = new Item("resources/sprites/enemies/tree2.png");
		}
	}
	
	public static void generateObstaclesPosition(Item[] o, int from, int to, int x)
	{
		for(int i = from; i <= to; i++)
		{
			int pol = generator.nextInt(300);
			o[i].setPosition(x, -70 - pol);
		}
	}
	
	public static void setCarLevelVectors(Item c[], int from, int to, int level)
	{
		for(int i = from; i <= to; i++)
		{
			if(level == 0)
				c[i].setVector(0, 0.8);
			
			else if(level == 1)
				c[i].setVector(0, 0.95);
			
			else
				c[i].setVector(0, 1.1);
		}
	}
	
	public static void setObstacleLevelVectors(Item o[], int from, int to, int level)
	{
		for(int i = from; i <= to; i++)
		{
			if(level == 0)
				o[i].setVector(0, 1.6);
			
			else if(level == 1)
				o[i].setVector(0, 1.9);
			
			else
				o[i].setVector(0, 2.2);
		}
	}
}
