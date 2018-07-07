package com.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.images.Background;

public class MenuState extends GameState
{
	private Background bg;
	
	private int currentChoice;
	
	private String[] options = {"Start", "Records", "Quit"};
	
	private Color titleColor;
	private Font titleFont; 
	
	private Font font;
	
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		try
		{
			bg = new Background("/Backgrounds/Menu.jpg", 1);
			bg.setVector(0, -0.15);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Sans Serif", Font.BOLD, 28);
			font = new Font("Arial", Font.BOLD, 14);
			
		}catch (Exception e)
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
		//draw bg
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Traffic Racer 2D", 65, 70);
		
		g.setFont(font);
		
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice)
				g.setColor(Color.RED);
	
			else
				g.setColor(Color.BLACK);
			
			if(i == 0) g.drawString(options[0], 160, 140);
			else if(i == 1) g.drawString(options[1], 148, 155);
			else g.drawString(options[2], 161, 170);
		}
	}
	
	private void select()
	{
		if(currentChoice == 0)
			gsm.setState(GameStateManager.CARCHOOSESTATE);

		else if(currentChoice == 1)
			gsm.setState(GameStateManager.RECORDSSTATE);
		
		else if(currentChoice == 2)
			System.exit(0);
	}
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ENTER)
			select();
		
		if(key == KeyEvent.VK_UP)
		{
			currentChoice--;
			
			if(currentChoice == -1)
				currentChoice = options.length - 1;
			
		}
		if(key == KeyEvent.VK_DOWN)
		{
			currentChoice++;
			
			if(currentChoice == options.length)
				currentChoice = 0;
			
		}
		
	}
	
	public void keyReleased(int key)
	{
		
	}
}