import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

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