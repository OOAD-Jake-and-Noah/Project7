import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Here the gui is created as a singleton and the gui functions entirely off of the main menu, so that is all the singleton needs to create
class GUIDriverSingleton {
	private static GUIDriverSingleton single_instance = null;
	private GUIDriverSingleton(){
		MainMenu menu = new MainMenu();
	}
	public static GUIDriverSingleton getInstance(){
		if(single_instance == null)
			single_instance = new GUIDriverSingleton();
		return single_instance;
	}
}

public class GUIDriver{
	public static void main(String args[]){
		GUIDriverSingleton gui = GUIDriverSingleton.getInstance();
	}
}