package autotype;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.exit;
import static java.awt.event.KeyEvent.*;


public class AutoTyper {
	private Robot robot;
	private Keyboard keyboard;
	private char[] content;
	
	public static void sleep(double time) {
		try {
			Thread.sleep((long) time*1000);
		} catch (InterruptedException e) {}
	}
	
	public void Type() throws IllegalArgumentException {
		for (char character : content) {
			try {keyboard.type(character);} catch (Exception ex) {}
		}
		robot.keyPress(VK_ENTER);
		robot.keyRelease(VK_ENTER);
		keyboard.type("--Autotyped by Mudkip's Autotyper");
		robot.keyPress(VK_CONTROL);
		robot.keyRelease(VK_CONTROL);
		robot.keyPress(VK_ENTER);
		robot.keyRelease(VK_ENTER);
		robot.keyPress(VK_CONTROL);
		robot.keyRelease(VK_CONTROL);
		robot.keyPress(VK_RIGHT);
		robot.keyRelease(VK_RIGHT);
		robot.keyPress(VK_ENTER);
		robot.keyRelease(VK_ENTER);
	}
	

	public static void error(String msg) {
		System.out.println(msg);
		exit(1);
	}
	
	public AutoTyper(String filename) {
		File file=new File(filename);
		FileReader reader=null;
		if (!(file.exists())) {
			error(filename+" is not a valid file!");
		}
		try {
			reader=new FileReader(file);
		} catch (FileNotFoundException e1) {
			error(filename+" not accessible!");
		}
		try {
			content=new char[(int) file.length()];
			reader.read(content,0,(int) file.length());
		} catch (IOException e1) {
			error("Couldn't read from file!");
		} finally {
			try {reader.close();} catch (Exception ex){}
		}
		try {
			robot = new Robot();
			robot.setAutoDelay(35);
			robot.setAutoWaitForIdle(true);
		} catch (AWTException e) {
			System.out.println("Could not create robot!");
			exit(1);
		}
		keyboard=new Keyboard(robot);
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length<1) {
			System.out.println("Must supply a file name!");
			exit(1);
		}
		AutoTyper typer=new AutoTyper(args[0]);
		System.out.println("Starting in 10...");
		sleep(10);
		typer.Type();
	}
}
