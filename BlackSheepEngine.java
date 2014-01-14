package game_jh365;
import java.awt.Color;

import jgame.*;
import jgame.platform.*;
/** TODO
 */
public class BlackSheepEngine extends JGEngine{
	public Integer[][] grid;
	BSPlayer player;
	BSPlayerObject playerObject;
	public BlackSheepEngine(JGPoint size){
		initEngine(size.x, size.y);
	}
	
	@Override
	public void initCanvas() {
		setCanvasSettings(
			BlackSheepGame.WINDOW_TILE_LENGTH,
			BlackSheepGame.WINDOW_TILE_WIDTH,
			BlackSheepGame.TILE_LENGTH,
			BlackSheepGame.TILE_WIDTH,
			null, null, null);
	}

	@Override
	public void initGame() {
		setFrameRate(35, 2);
		grid = new Integer[BlackSheepGame.WINDOW_TILE_LENGTH][BlackSheepGame.WINDOW_TILE_WIDTH];
		setGameState("InGame");
	}
	public void startInGame(){
		if(player == null)
			player = new BSPlayer();
		playerObject = new BSPlayerObject();
		playerObject.loadPlayer(player);
	}
	/** A simple timer. */
	int gametimer=0;
	/** Mouse position of previous frame. */
	//int prevmousex=0,prevmousey=0;
	public void doFrameInGame(){
		moveObjects(null, 0);
		gametimer++;
		//processKeys();
		if(getKey(KeyLeft)){
			clearKey(KeyLeft);
			player.position_x -= 1;
		}else if(getKey(KeyRight)){
			clearKey(KeyRight);
			player.position_x += 1;
		}else if(getKey(KeyUp)){
			clearKey(KeyUp);
			player.position_y -= 1;
		}else if(getKey(KeyDown)){
			clearKey(KeyDown);
			player.position_y += 1;
		}
		if(getKey('R')){
			clearKey('R');
			player.increaseRage(5);
		}else if(getKey('G')){
			clearKey('G');
			player.increaseGrit(5);
		}else if(getKey('B')){
			clearKey('B');
			player.increaseBrawn(5);
		}
		System.out.println(player.getRage());
		this.setBGColor(new JGColor(255 - player.getRage(), 255 - player.getGrit(), 255 - player.getBrawn()));
		
		if(getKey(KeyEsc)){
			clearKey(KeyEsc);
			setGameState("PlayerStats");
		}
	}
	public void startPlayerStats(){
		removeObjects(null, 0);
	}
	public void paintFramePlayerStats(){
		drawString("Player Stats", pfWidth()/2, 40, 0, new JGFont("Arial", 0, 30), new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
		drawString("RAGE", 80, 110, 1, new JGFont("Arial", 0, 18), new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
		drawString("GRIT", 80, 160, 1, new JGFont("Arial", 0, 18), new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
		drawString("BRAWN", 80, 210, 1, new JGFont("Arial", 0, 18), new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
		drawRect(100, 108, player.getRage() / 2, 18, true, false, 1, JGColor.red);
		drawRect(100, 158, player.getGrit() / 2, 18, true, false, 1, JGColor.green);
		drawRect(100, 208, player.getBrawn() / 2, 18, true, false, 1, JGColor.blue);
		drawString(player.getRage() + "", 240, 110, -1, new JGFont("Arial", 0, 18), new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
		drawString(player.getGrit() + "", 240, 160, -1, new JGFont("Arial", 0, 18), new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
		drawString(player.getBrawn() + "", 240, 210, -1, new JGFont("Arial", 0, 18), new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
		//drawRect(100, 5, player.getRage(), 18, true, false);
	}
	public void doFramePlayerStats(){
		if(getKey(KeyEsc)){
			clearKey(KeyEsc);
			setGameState("InGame");
		}
	}
	class BSPlayer{
		int position_x;
		int position_y;
		
		int statRage;
		int statGrit;
		int statBrawn;
		BSPlayer(){
			position_x = 8;
			position_y = 1;
			
			statRage = 5;
			statGrit = 5;
			statBrawn = 5;
		}
		public void increaseRage(int increaseAmt){
			statRage += increaseAmt;
		}
		public void increaseGrit(int increaseAmt){
			statGrit += increaseAmt;
		}
		public void increaseBrawn(int increaseAmt){
			statBrawn += increaseAmt;
		}
		public int getRage(){
			return statRage;
		}
		public int getGrit(){
			return statGrit;
		}
		public int getBrawn(){
			return statBrawn;
		}
		public int getPositionX(){
			return position_x;
		}
		public int getPositionY(){
			return position_y;
		}
	}
	class BSPlayerObject extends JGObject{
		BSPlayer player;
		BSPlayerObject(){
			super(
				"Player",// name by which the object is known
				true,
				0,  // X position
				0, // Y position
				1, // the object's collision ID (used to determine which classes
				   // of objects should collide with each other)
				null // name of sprite or animation to use (null is none)
			);
			xspeed = 0;
			yspeed = 0;
		}
		public void loadPlayer(BSPlayer loadedPlayer){
			player = loadedPlayer;
		}
		public void move(){
			x = player.position_x * BlackSheepGame.TILE_LENGTH;
			y = player.position_y * BlackSheepGame.TILE_WIDTH;
		}
		public void paint(){
			setColor(new JGColor(player.getRage(), player.getGrit(), player.getBrawn()));
			drawOval(x,y,16,16,true,false);
		}
	}
}
