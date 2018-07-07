package com.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.images.Background;
import com.game.window.Menu;

public class GameplayChooseState extends GameState
{
	private Background bg;
	private BufferedImage normal;
	private BufferedImage time;
	
	private static int currentChoice;
	
	private String[] options = {"Normal", "With time"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public GameplayChooseState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		try
		{
			bg = new Background("/Backgrounds/GameplayChoose.png", 1);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Sans Serif", Font.BOLD, 28);
			font = new Font("Arial", Font.BOLD, 14);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void init() 
	{
		try 
		{
			normal = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/NormalGameplay.png"));
			time = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/TimeGameplay.png"));
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		currentChoice = 0;
	}

	@Override
	public void update() 
	{
		
	}

	@Override
	public void draw(Graphics2D g) 
	{
		bg.draw(g);	
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Choose a gameplay", 50, 30);
				
		g.setFont(font);
				
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice)
				g.setColor(Color.RED);
			
			else
				g.setColor(Color.BLACK);
			
			
			if(i == 0) g.drawString(options[0], 155, 140);
			else g.drawString(options[1], 147, 160);
			
			if(currentChoice == 0) 
			{
				g.setColor(Color.BLACK);
				g.drawString("Just score as many points as possible", 50, 220);
				g.drawImage(normal, 127, 40, null);
			}
			
			else 
			{
				g.setColor(Color.BLACK);
				g.drawString("Score as many points as possible", 65, 220);
				g.drawString("in one minute time", 114, 240);
				g.drawImage(time, 127, 40, null);
			}
		}
	}

	@Override
	public void keyPressed(int key) 
	{
		if(key == KeyEvent.VK_ENTER)
		{	
			gsm.setState(GameStateManager.LEVELSTATE);
			Menu.stopMusic();
		}
		
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
	public void keyReleased(int key) 
	{
		
	}
	
	public static int getCurrentChoice()
	{
		return currentChoice;
	}
}
