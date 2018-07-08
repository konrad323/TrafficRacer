package com.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.images.Background;

public class DifficultyChooseState extends GameState
{
	private Background bg;
	
	private static int currentChoice;
	private final String[] options = {"Easy", "Medium", "Hard"};
	
	private Color titleColor;
	private Font titleFont, font;
	
	public DifficultyChooseState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		try
		{
			bg = new Background("resources/backgrounds/DifficultyChoose.png", 1);
			
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
	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g) 
	{
		bg.draw(g);	
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Choose a difficulty level", 20, 30);		
		g.setFont(font);
				
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice) g.setColor(Color.RED);
			else g.setColor(Color.BLACK);
					
			if(i == 0) g.drawString(options[0], 161, 140);
			else if(i == 1) g.drawString(options[1], 150, 160);
			else if(i == 2) g.drawString(options[2], 161, 180);
		}
	}

	@Override
	public void keyPressed(int key) 
	{
		if(key == KeyEvent.VK_ENTER)
			gsm.setState(GameStateManager.GAMEPLAYCHOOSESTATE);
		else if(key == KeyEvent.VK_UP)
		{
			currentChoice--;
			if(currentChoice == -1)
				currentChoice = options.length - 1;
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			currentChoice++;
			if(currentChoice == options.length)
				currentChoice = 0;
		}
	}

	@Override
	public void keyReleased(int key) {}
	
	public static int getCurrentChoice()
	{
		return currentChoice;
	}
}
