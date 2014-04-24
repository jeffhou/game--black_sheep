package game_jh365;

import jgame.JGColor;

class BSPlayerObject extends BSUnitObject{
	BSPlayerObject(){
		super();
	}
	BSPlayerObject(BlackSheepEngine in_engine){
		super();
		engine = in_engine;
	}
	public void paint(){
		engine.setColor(unit.getColor());
		engine.drawOval(x, y, 15, 15, true, false);
		engine.drawOval(x, y, 15, 15, false, false, 0, JGColor.white);
	}
}