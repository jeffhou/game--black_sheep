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
			if(player){
				engine.drawRect(0, 120, 320, 121, true, false, 0, new JGColor(111, 111, 111));  // background
				engine.drawRect(0, 120, 320, 320, true, false, 1, new JGColor(111, 111, 111));  // background
				engine.drawOval(100, 220, 150, 150, true, true, 1, unit.getColor()); // player
				engine.drawOval(100, 220, 150, 150, false, true, 10, JGColor.white);  // player
				engine.drawRect(190, 200, 120, 30, true, false, 1, JGColor.white);  //player HP block
				engine.drawRect(230, 200, 70, 26, true, false, 1, new JGColor(111, 111, 111));  //player HP block
				engine.drawRect(230, 206, 70 * hp / max_hp, 14, true, false, 1, JGColor.red);  //player HP block
				engine.drawString("HP:", 195, 209, -1, new JGFont("Arial", 1, 18), JGColor.black);
				engine.drawString(hp + " / " + max_hp, 265, 193, 0, new JGFont("Arial", 1, 12), JGColor.white);
			}else{
				engine.drawRect(0, 0, 320, 121, true, false, 0, new JGColor(111, 111, 111));  // background
				engine.drawRect(220, 20, 150, 150, true, true, 1, unit.getColor());  //enemy
				engine.drawRect(220, 20, 150, 150, false, true, 10, JGColor.white);  //enemy
				engine.drawRect(10, 40, 120, 30, true, false, 1, JGColor.white);  //enemy HP block
				engine.drawRect(50, 40, 70, 26, true, false, 1, new JGColor(111, 111, 111));  //enemy HP block
				engine.drawRect(50, 46, 70 * hp / max_hp, 14, true, false, 1, JGColor.red);  //enemy HP block
				engine.drawString("HP:", 15, 49, -1, new JGFont("Arial", 1, 18), JGColor.black);
				engine.drawString(hp + " / " + max_hp, 85, 33, 0, new JGFont("Arial", 1, 12), JGColor.white);
			}
			
		}
	}
	
}
