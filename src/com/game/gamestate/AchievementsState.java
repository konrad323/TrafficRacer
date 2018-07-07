package com.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.game.images.Background;
import com.game.images.Item;
import com.game.window.Menu;

public class AchievementsState extends GameState
{
	private Background bg;
	
	private String option = "Back";
	
	Item[] medal = new Item[8];
	
	private static int currentChoice;
	
	private Font font;
	
	public AchievementsState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		try
		{
			bg = new Background("/Backgrounds/Achievements.jpg", 1);
			
			font = new Font("Arial", Font.BOLD, 14);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void init()
	{	
		currentChoice = 0;
		
		for(int i = 0; i < medal.length; i++)
		{
			medal[i] = new Item("/Sprites/Achievements/medal.jpg");
		}
	
		setMedalsPosition();
	
	}
	
	public void update()
	{
		bg.update();
	}
	
	public void draw(Graphics2D g)
	{
		//draw bg
		bg.draw(g);
		
		g.setFont(font);
		
		for(int i = 0; i < medal.length; i++)
			medal[i].draw(g);
		
		for(int i = 0; i < 9; i++)
		{
			if(i == currentChoice)
				g.setColor(Color.RED);
			
			else
				g.setColor(Color.BLACK);
				
			if(i == 0) g.drawString(option, 160, 265);
			else if(i == 1) g.drawString("1", 41, 85);
			else if(i == 2) g.drawString("2", 131, 85);
			else if(i == 3) g.drawString("3", 221, 85);
			else if(i == 4) g.drawString("4", 311, 85);
			else if(i == 5) g.drawString("5", 41, 175);
			else if(i == 6) g.drawString("6", 131, 175);
			else if(i == 7) g.drawString("7", 221, 175);
			else g.drawString("8", 311, 175);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 13));
			
			if(currentChoice == 1) g.drawString("Score 5000 points in a normal easy level gameplay", 20, 220);
			else if(currentChoice == 2) g.drawString("Score 5000 points in a normal medium level gameplay", 10, 220);
			else if(currentChoice == 3) g.drawString("Score 5000 points in a normal hard level gameplay", 20, 220);
			else if(currentChoice == 4) g.drawString("Drive for one minute in a time easy level gameplay", 20, 220);
			else if(currentChoice == 5) g.drawString("Drive for one minute in a time medium level gameplay", 10, 220);
			else if(currentChoice == 6) g.drawString("Drive for one minute in a time hard level gameplay", 20, 220);
			else if(currentChoice == 7) g.drawString("Play the game 2000 times", 100, 220);
			else if(currentChoice == 8) g.drawString("Play the game 5000 times", 100, 220);
			
			if(Menu.getAchievement1()) g.drawString("Done", 27, 33);
			if(Menu.getAchievement2()) g.drawString("Done", 117, 33);
			if(Menu.getAchievement3()) g.drawString("Done", 207, 33);
			if(Menu.getAchievement4()) g.drawString("Done", 297, 33);
			if(Menu.getAchievement5()) g.drawString("Done", 27, 123);
			if(Menu.getAchievement6()) g.drawString("Done", 117, 123);
			if(Menu.getAchievement7()) g.drawString("Done", 207, 123);
			if(Menu.getAchievement8()) g.drawString("Done", 297, 123);
		}
		
	}
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ENTER && currentChoice == 0)
			gsm.setState(GameStateManager.RECORDSSTATE);
		
		else if(key == KeyEvent.VK_UP)
		{
			if(currentChoice == 0)
				currentChoice = 6;
			
			else if(currentChoice == 5 || currentChoice == 6 || currentChoice == 7 || currentChoice == 8)
				currentChoice -= 4;
			
			else return;
		}
		
		else if(key == KeyEvent.VK_DOWN)
		{	
			if(currentChoice == 1 || currentChoice == 2 || currentChoice == 3 || currentChoice == 4)
				currentChoice += 4;
			
			else
				currentChoice = 0;
			
		}
		
		else if(key == KeyEvent.VK_RIGHT)
		{
			if(currentChoice == 0) return;
			
			else if(currentChoice == 4 || currentChoice == 8) return;
			
			else
				currentChoice++;
			
		}
		
		else if(key == KeyEvent.VK_LEFT)
		{
			if(currentChoice == 0) return;
			
			else if(currentChoice == 1 || currentChoice == 5) return;
			
			else
				currentChoice--;
			
		}
		
	}
	
	public void keyReleased(int key)
	{
		
	}
	
	public void setMedalsPosition()
	{
		medal[0].setPosition(25, 10);
		medal[1].setPosition(115, 10);
		medal[2].setPosition(205, 10);
		medal[3].setPosition(295, 10);
		medal[4].setPosition(25, 100);
		medal[5].setPosition(115, 100);
		medal[6].setPosition(205, 100);
		medal[7].setPosition(295, 100);
	}
}