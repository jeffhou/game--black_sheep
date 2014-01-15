package game_jh365;
import jgame.*;
import jgame.platform.*;

public class BlackSheepGame{
	public static final int TILE_LENGTH = 16;
	public static final int TILE_WIDTH = 16;
	public static final int WINDOW_TILE_LENGTH = 20;
	public static final int WINDOW_TILE_WIDTH = 20;
	public static void main(String[] args){
		new BlackSheepEngine(
			new JGPoint(
				WINDOW_TILE_LENGTH * TILE_LENGTH, 
				WINDOW_TILE_WIDTH * TILE_WIDTH));  //creates window
	}
}