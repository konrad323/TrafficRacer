package com.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.images.Background;
import com.game.window.Menu;

public class RecordsState extends GameState
{
	private Background bg;
	
	private final String[] options = {"Achievements", "Back"};
	private int currentChoice;
	private Font font;
	
	public RecordsState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		try
		{
			bg = new Background("resources/backgrounds/records.jpg", 1);
			bg.setVector(0, -0.15);
			
			font = new Font("Arial", Font.BOLD, 14);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		currentChoice = 0;
	}
	
	public void update()
	{
		bg.update();
	}
	
	public void draw(Graphics2D g)
	{
		bg.draw(g);
		
		g.setFont(font);
		
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice)
			{
				g.setColor(Color.RED);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
	
			if(i == 0) g.drawString(options[0], 130, 140);
			else g.drawString(options[1], 160, 155);
		}
		
		g.setColor(Color.BLUE.darker());
		g.setFont(new Font("Arial", Font.BOLD, 16));
		
		g.drawString("Normal Gameplay ", 2, 15);
		g.drawString("Time Gameplay", 250, 15);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 13));
		
		g.drawString("Easy " + Menu.getCurrentNormalEasyRecord(), 2, 35);
		g.drawString("Medium " + Menu.getCurrentNormalMediumRecord(), 2, 55);
		g.drawString("Hard " + Menu.getCurrentNormalHardRecord(), 2, 75);
		
		g.drawString("Easy " + Menu.getCurrentTimeEasyRecord(), 250, 35);
		g.drawString("Medium " + Menu.getCurrentTimeMediumRecord(), 250, 55);
		g.drawString("Hard " + Menu.getCurrentTimeHardRecord(), 250, 75);
	}
	
	public void select()
	{
		if(currentChoice == 0)
			gsm.setState(GameStateManager.ACHIEVEMENTSSTATE);
		
		else if(currentChoice == 1)
			gsm.setState(GameStateManager.MENUSTATE);
	}
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ENTER)
			select();	
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
}
