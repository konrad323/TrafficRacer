package com.game.window;

import javax.swing.JFrame;

public class Game 
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Traffic Racer 2D");
		window.setContentPane(new Menu());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}
