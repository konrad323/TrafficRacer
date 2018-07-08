package com.game.gamestate;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager 
{
	private final ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE = 1;
	public static final int RECORDSSTATE = 2;
	public static final int ACHIEVEMENTSSTATE = 3;
	public static final int CARCHOOSESTATE = 4;
	public static final int GAMEPLAYCHOOSESTATE = 5;
	public static final int DIFFICULTYCHOOSESTATE = 6;
	
	public GameStateManager()
	{
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		
		gameStates.add(new MenuState(this));
		gameStates.add(new LevelState(this));
		gameStates.add(new RecordsState(this));
		gameStates.add(new AchievementsState(this));
		gameStates.add(new CarChooseState(this));
		gameStates.add(new GameplayChooseState(this));
		gameStates.add(new DifficultyChooseState(this));
	}
	
	public void setState(int state)
	{
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update()
	{
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g)
	{
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) 
	{
		gameStates.get(currentState).keyPressed(k);
	}

	public void keyReleased(int k) 
	{
		gameStates.get(currentState).keyReleased(k);
	}
	
	public int getCurrentState()
	{
		return currentState;
	}
}
