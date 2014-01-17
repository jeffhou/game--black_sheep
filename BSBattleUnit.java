package game_jh365;

import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;

public class BSBattleUnit {
	int relevantStat;
	BSUnit unit;
	int hp, max_hp;
	boolean player;
	BlackSheepEngine engine;
	public BSBattleUnit(int in_relevantStat, BSUnit in_unit, boolean isPlayer, BlackSheepEngine in_engine){
		relevantStat = in_relevantStat;
		unit = in_unit;
		max_hp = relevantStat * (relevantStat - 1) / 4;
		if(max_hp <= 0) max_hp = 1;
		hp = max_hp;
		engine = in_engine;
		player = isPlayer;
		new BSBattleUnitObject();
	}
	public class BSBattleUnitObject extends JGObject{
		BSBattleUnitObject(){
			super(
				"Battle Unit",// name by which the object is known
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
		public void paint(){
			int yOffsetScreen = 0;
			int yOffsetOther = 0;
			int xOffset = 0;
			if(player){
				yOffsetScreen = 120;
				yOffsetOther = 160;
				xOffset = 180;
			}
			engine.drawRect(0, yOffsetScreen, 320, 121, true, false, 0, new JGColor(111, 111, 111));  // background
			if(player){
				engine.drawOval(100, 220, 150, 150, true, true, 1, unit.getColor()); // player
				engine.drawOval(100, 220, 150, 150, false, true, 10, JGColor.white);  // player
				
			}else{
				engine.drawRect(220, 20, 150, 150, true, true, 1, unit.getColor());  //enemy
				engine.drawRect(220, 20, 150, 150, false, true, 10, JGColor.white);  //enemy
			}
			engine.drawRect(10 + xOffset, 40 + yOffsetOther, 120, 30, true, false, 1, JGColor.white);  //player HP block
			engine.drawRect(50 + xOffset, 40 + yOffsetOther, 70, 26, true, false, 1, new JGColor(111, 111, 111));  //player HP block
			engine.drawRect(50 + xOffset, 46 + yOffsetOther, 70 * hp / max_hp, 14, true, false, 1, JGColor.red);  //player HP block
			engine.drawString("HP:", 15 + xOffset, 49 + yOffsetOther, -1, new JGFont("Arial", 1, 18), JGColor.black);
			engine.drawString(hp + " / " + max_hp, 85 + xOffset, 33 + yOffsetOther, 0, new JGFont("Arial", 1, 12), JGColor.white);
			
		}
	}
	
}
