package com.game.gamestate;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.Timer;

import com.game.gameplay.Functions;
import com.game.gameplay.RecordsManagement;
import com.game.images.Background;
import com.game.images.Item;
import com.game.window.Menu;

public class LevelState extends GameState implements ActionListener
{	
	private Background bg;
	private Item car;
	
	private Item[] c = new Item[6];
	private Item[] o = new Item[2];
	
	Timer timer1; // timer do rozgrywki time gameplay
	Timer timer2; // timer do wyswietlania zdobytych punktow
	Clip clip;
	
	private static int currentScore;
	private static int previousScore;
	private static int time;
	
	private boolean zaczeto;
	private boolean lose;
	private boolean optionsShowed;
	private boolean one_arrived;
	private boolean left, right;
	private boolean changeTime;
	private boolean pointsReceived;
	private boolean firstReceivedPoints;
	private boolean wasCollision;
	private boolean ifSoundDelayed; // aby sie pozbyc opoznienia spowodowanego dzialaniem clip.isActive()
	
	private int currentChoice;
	
	private String[] options = {"Play again", "Exit"};
	
	
	public LevelState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	public void draw(Graphics2D g)
	{
		bg.draw(g);
	
		for(int i = 0; i < 6; i++)
			c[i].draw(g);
		
		for(int i = 0; i < 2; i++)
			o[i].draw(g);
		
		car.draw(g);
		
		if(left && car.getX() > 0) car.setPosition(car.getX() - 1, car.getY());
		if(right && car.getX() + car.getWidth() < Menu.WIDTH) car.setPosition(car.getX() + 1, car.getY());
		
		if(pointsReceived && currentScore > previousScore)
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 17));
			g.drawString("+" + (currentScore - previousScore), (int) car.getX(), (int) (car.getY() + car.getHeight() / 2));
		}
		
		if(GameplayChooseState.getCurrentChoice() == 1 && changeTime && !lose)
		{
			time--;
			changeTime = false;
		}
		
		if(GameplayChooseState.getCurrentChoice() == 1) 
		{
			g.setColor(Color.RED.darker());
			g.setFont(new Font("Verdana", Font.BOLD, 17));
			g.drawString("Time left " + String.valueOf(time), 2, 20);
		}
		
		if(time == 0)
		{
			lose = true;
			clip.stop();
		}
		
		for(int i = 0; i < 6; i++)
		{
			if(Functions.collision(car, c[i]))
			{
				lose = true;
				left = false;
				right = false;
				
				if(!wasCollision)
				{
					clip.stop();
					playSound("resources/music/collision.wav");
					wasCollision = true;
					ifSoundDelayed = true;
				}
			}
		}
		
		for(int i = 0; i < 2; i++)
		{
			if(Functions.collision(car, o[i]))
			{
				lose = true;
				left = false;
				right = false;
				
				if(!wasCollision)
				{
					clip.stop();
					playSound("resources/music/collision.wav");
					wasCollision = true;
				}
			}
		}
	
		g.setColor(Color.BLACK);
		g.setFont(new Font("Verdana", Font.BOLD, 18));
		g.drawString("Points " + currentScore, 150, 20);
		
		if(!zaczeto)
		{
			g.drawString("Click up to start", 113, 100);
		}
		
		if(one_arrived && (c[0].getY() > Menu.HEIGHT - 140 || c[1].getY() > Menu.HEIGHT - 140 || c[2].getY() > Menu.HEIGHT - 140))
		{
			Functions.generateCars(c, 3, 5);
			Functions.generateCarsPosition(c, 3, 5);
			Functions.setCarLevelVectors(c, 3, 5, DifficultyChooseState.getCurrentChoice());
			
			one_arrived = false;
		}
		
		if(!one_arrived && (c[3].getY() > Menu.HEIGHT - 120 || c[4].getY() > Menu.HEIGHT - 120 || c[5].getY() > Menu.HEIGHT - 120))
		{
			Functions.generateCars(c, 0, 2);
			Functions.generateCarsPosition(c, 0, 2);
			Functions.setCarLevelVectors(c, 0, 2, DifficultyChooseState.getCurrentChoice());
			
			one_arrived = true;
		}
		
		if(o[0].getY() > Menu.HEIGHT)
		{
			Functions.generateObstacles(o, 0, 0);
			Functions.setObstacleLevelVectors(o, 0, 0, DifficultyChooseState.getCurrentChoice());
			Functions.generateObstaclesPosition(o, 0, 0, 2);
		}
		
		if(o[1].getY() > Menu.HEIGHT)
		{
			Functions.generateObstacles(o, 1, 1);
			Functions.setObstacleLevelVectors(o, 1, 1, DifficultyChooseState.getCurrentChoice());
			Functions.generateObstaclesPosition(o, 1, 1, 327);
		}
		
		if(lose)
		{
			for(int i = 0; i < 6; i++)
				c[i].setVector(0, 0);
			
			for(int i = 0; i < 2; i++)
				o[i].setVector(0, 0);
			
			g.drawString("GAME OVER", 130, 80);
			
			try 
			{
				RecordsManagement.saveRecord(DifficultyChooseState.getCurrentChoice(), 
												GameplayChooseState.getCurrentChoice(), currentScore);
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				RecordsManagement.saveAchievements(DifficultyChooseState.getCurrentChoice(), 
														GameplayChooseState.getCurrentChoice(), 
																currentScore, Menu.getPlaysNumber(), time);
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			timer1.stop();
			timer2.stop();
		}
		
		if(zaczeto && !lose && !clip.isActive())
			playSound("resources/music/car driving.wav");
		
		if(lose && !ifSoundDelayed && !clip.isActive())
		{
			if(!optionsShowed) currentChoice = 0;
			optionsShowed = true;
			
			g.setFont(new Font("Verdana", Font.BOLD, 14));
			
			for(int i = 0; i < options.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.RED);
				
				else
					g.setColor(Color.BLACK);
				
				if(i == 0) g.drawString(options[0], 145, 120);
				else g.drawString(options[1], 170, 135);
			}
		}
		
		if(!lose) 
		{
			for(int i = 0; i < 6; i++)
			{
				int tempScore = currentScore;
				currentScore = Functions.scoreCounter(car, c[i], currentScore);
				
				if(currentScore > tempScore && !firstReceivedPoints)
				{
					pointsReceived = true;
					timer2.restart();
				}
			}
		
		}
		
		if(firstReceivedPoints && currentScore > previousScore)
		{
			timer2.start();
			pointsReceived = true;
			firstReceivedPoints = false;
		}
		
		ifSoundDelayed = false;
			
	}
	
	private void select()
	{
		if(currentChoice == 0)
			init();
	
		else 
			gsm.setState(GameStateManager.MENUSTATE);
	}
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_UP && !zaczeto)
		{
			for(int i = 0; i < 6; i++)
				Functions.setCarLevelVectors(c, 0, c.length - 1, DifficultyChooseState.getCurrentChoice());
			
			for(int i = 0; i < 2; i++)
				Functions.setObstacleLevelVectors(o, 0, o.length - 1, DifficultyChooseState.getCurrentChoice());
			
			zaczeto = true;
			
			if(GameplayChooseState.getCurrentChoice() == 1) 
				timer1.start();
			
			playSound("resources/music/car driving.wav");
		}
		
		else if(key == KeyEvent.VK_ENTER && optionsShowed)
			select();
		
		else if(key == KeyEvent.VK_LEFT && car.getX() >= 0 && zaczeto && !lose)
			left = true;
		
		else if(key == KeyEvent.VK_RIGHT && car.getX() + car.getWidth() <= Menu.WIDTH && zaczeto && !lose)
			right = true;
		
		else if(key == KeyEvent.VK_UP && optionsShowed)
		{
			currentChoice--;
			
			if(currentChoice == -1)
				currentChoice = options.length - 1;
		}
		
		else if(key == KeyEvent.VK_DOWN && optionsShowed)
		{
			currentChoice++;
			if(currentChoice == options.length)
				currentChoice = 0;
		}
	}
	
	public void keyReleased(int k)
	{
		if(k == KeyEvent.VK_LEFT)
			left = false;
		
		else if(k == KeyEvent.VK_RIGHT)
			right = false;
	}
	
	@Override
	public void init() 
	{	
		bg = new Background("resources/backgrounds/game.png", 0.1);
		
		timer1 = new Timer(1000, this);
		timer2 = new Timer(1000, this);
		
		Functions.generateCars(c, 0, c.length - 1);
		Functions.generateObstacles(o, 0, o.length - 1);
		
		initGame();
	}
	
	public void initGame()
	{
		
		if(CarChooseState.getCurrentChoice() == 0) car = new Item("resources/sprites/player/car.png");
		else if(CarChooseState.getCurrentChoice() == 1) car = new Item("resources/sprites/player/dodge.png");
		else if(CarChooseState.getCurrentChoice() == 2) car = new Item("resources/sprites/player/aston.png");
		else if(CarChooseState.getCurrentChoice() == 3) car = new Item("resources/sprites/player/jeep.png");
		
		o[0].setPosition(2, -100);
		o[1].setPosition(327, -150);
		
		Functions.generateCarsPosition(c, 0, 2);
		
		c[3].setPosition(80, -1100);
		c[4].setPosition(150, -1100);
		c[5].setPosition(220, -1100);
		
		zaczeto = false;
		lose = false;
		optionsShowed = false;
		one_arrived = true;
		left = false;
		right = false;
		changeTime = false;
		pointsReceived = false;
		firstReceivedPoints = true;
		wasCollision = false;
		ifSoundDelayed = false;
		
		car.setPosition(Menu.WIDTH / 2 - car.getWidth() / 2, Menu.HEIGHT - car.getHeight() - 50);		
		
		currentScore = 0;
		previousScore = 0;
		time = 60;
		
		try 
		{
			RecordsManagement.savePlays();
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() 
	{
		car.update();
		
		for(int i = 0; i < 6; i++)
			c[i].update();
		
		for(int i = 0; i < 2; i++)
			o[i].update();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		
		if(source == timer1)
		{
			changeTime = true;
		}
		
		else
		{
			previousScore = currentScore;
			pointsReceived = !pointsReceived;
		}
		
	}
	
	public void playSound(String filename)
	{
		try 
		{
		    File file = new File(filename);
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;

		    stream = AudioSystem.getAudioInputStream(file);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		    
		}catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}

}
