package game_jh365;
import jgame.*;
import jgame.platform.*;

public class BlackSheepEngine extends JGEngine{
	public Integer[][] grid;
	BSPlayer player;
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
		player = new BSPlayer();
		// TODO Auto-generated method stub
	}
	/** A simple timer. */
	int gametimer=0;
	/** Mouse position of previous frame. */
	int prevmousex=0,prevmousey=0;
	public void doFrame(){
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
	}
	class BSPlayer extends JGObject{
		int position_x;
		int position_y;
		BSPlayer(){
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
			position_x = 8;
			position_y = 1;
		}
		public void move(){
			x = position_x * BlackSheepGame.TILE_LENGTH;
			y = position_y * BlackSheepGame.TILE_WIDTH;
		}
		public void paint(){
			setColor(new JGColor(220, 220, 220));
			drawOval(x,y,16,16,true,false);
		}
	}
}
