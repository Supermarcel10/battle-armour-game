package game;

import org.jbox2d.common.Vec2;

import java.util.HashMap;


public class Config {
	public static final boolean DEBUG = true;
	public static boolean fullscreen = false;
	public static Vec2 resolution = new Vec2(1920, 1080);
	public static boolean fpsLock = false;
	public static int fps = 60;
	public static String title = "City Game";
	public static HashMap<Character, String> key_binding = new HashMap<>(){{
		put('w', "player1-UP");
		put('a', "player1-LEFT");
		put('s', "player1-DOWN");
		put('d', "player1-RIGHT");
	}};
}
