package game_jh365;

import jgame.JGColor;
import jgame.JGObject;

class BSUnitObject extends JGObject{
	BSUnit unit;
	BlackSheepEngine engine;
	BSUnitObject(){
		super(
			"Unit",// name by which the object is known
			true,
			0,  // X position
			0, // Y position
			0, // the object's collision ID (used to determine which classes
			   // of objects should collide with each other)
			null // name of sprite or animation to use (null is none)
		);
		xspeed = 0;
		yspeed = 0;
	}
	BSUnitObject(BlackSheepEngine in_engine){
		this();
		engine = in_engine;
	}
	public void loadUnit(BSUnit loadedUnit){
		unit = loadedUnit;
	}
	public void move(){
		x = unit.position_x * BlackSheepGame.TILE_LENGTH;
		y = unit.position_y * BlackSheepGame.TILE_WIDTH;
	}
	public void paint(){
		engine.setColor(unit.getColor());
		engine.drawRect(x, y, 16, 16, true, false);
		engine.drawRect(x, y, 16, 16, false, false, 0, JGColor.white);
		
	}
}