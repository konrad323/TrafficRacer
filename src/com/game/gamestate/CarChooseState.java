package com.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.images.Background;
import com.game.images.Item;

public class CarChooseState extends GameState
{
	private Background bg;
	
	private final Item[] car = new Item[4];
	private final String PATH = "resources/sprites/player/";
	private static int currentChoice;
	private final String[] options = {"Lamborghini Murcielago", "Dodge Challenger", "Aston Martin DB9", "Military Jeep"};
	
	private Color titleColor;
	private Font titleFont, font;
	
	public CarChooseState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		try
		{
			bg = new Background("resources/backgrounds/CarChoose.png", 1);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Sans Serif", Font.BOLD, 28);
			font = new Font("Arial", Font.BOLD, 14);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void init() 
	{
		currentChoice = 0;
		
		car[0] = new Item(PATH + "car.png");
		car[0].setPosition(70, 50);
		
		car[1] = new Item(PATH + "dodge.png");
		car[1].setPosition(270, 50);
		
		car[2] = new Item(PATH + "aston.png");
		car[2].setPosition(70, 170);
		
		car[3] = new Item(PATH + "jeep.png");
		car[3].setPosition(270, 170);
	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g) 
	{
		bg.draw(g);	
		
		for(int i = 0; i < 4; i++) car[i].draw(g);
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Choose a car", 95, 30);
		g.setFont(font);
				
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice) g.setColor(Color.RED);
			else g.setColor(Color.BLACK);
					
			if(i == 0) g.drawString(options[0], 2, 130);
			else if(i == 1) g.drawString(options[1], 220, 130);
			else if(i == 2) g.drawString(options[2], 25 , 250);
			else g.drawString(options[3], 240, 250);
		}
	}

	@Override
	public void keyPressed(int key) 
	{
		if(key == KeyEvent.VK_ENTER)
			gsm.setState(GameStateManager.DIFFICULTYCHOOSESTATE);
		else if(key == KeyEvent.VK_UP)
		{
			if(currentChoice == 0 || currentChoice == 1) return;
			currentChoice -= 2;
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			if(currentChoice == 2 || currentChoice == 3) return;
			currentChoice += 2;
		}
		else if(key == KeyEvent.VK_RIGHT)
		{
			if(currentChoice == 1 || currentChoice == 3) return;	
			currentChoice++;
		}
		else if(key == KeyEvent.VK_LEFT)
		{
			if(currentChoice == 0 || currentChoice == 2) return;	
			currentChoice--;
		}
	}

	@Override
	public void keyReleased(int key) {}
	
	public static int getCurrentChoice()
	{
		return currentChoice;
	}
}
