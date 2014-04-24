package game_jh365;

import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;

public class BSBattleController {
	BSUnit player, enemy;
	BlackSheepEngine engine;
	boolean postCalc;
	int relevant_stat;
	int phase = 0; 
	final String[] COMMANDS = {"Red Rover", "Green Giant", "Blue Bell", "Prismatic Prism"};
	final String[] ATK_MSGS = {"You play Red Rover with him and   run through him and deal %d damage.",
								"You summon the Green Giant who    freezes your enemy for %d damage.", 
								"You give him the tub of Blue Bell and he eats %d calories of pain.", 
								"You throw the Prismatic Prism at   him, doing %d damage."};
	final int[] COMMAND_X_COORS = {29, 174};
	final int[] COMMAND_Y_COORS = {258, 289};
	int selector_x = 0;
	int selector_y = 0;
	public BSBattleController(BSUnit unit, BSUnit unit2, BlackSheepEngine in_engine){
		enemy = unit2;
		player = unit;
		engine = in_engine;
		relevant_stat = engine.npcUnits.size();
		new BSBattleControllerObject();
	}
	public class BSBattleControllerObject extends JGObject{
		BSBattleControllerObject(){
			super(
				"BattleController",// name by which the object is known
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
			engine.drawRect(0, 240, 320, 80, true, false, 1, JGColor.white);
			engine.drawRect(7, 247, 306, 66, true, false, 1, JGColor.black);
			if(phase == 0){
				for(int i = 0; i < 2; i++){
					for(int j = 0; j < 2; j++){
						engine.drawString(COMMANDS[i * 2 + j], COMMAND_X_COORS[i], COMMAND_Y_COORS[j], -1, new JGFont("Arial", 0, 16), JGColor.white);
					}
				}
				engine.drawRect(COMMAND_X_COORS[selector_x] - 15, COMMAND_Y_COORS[selector_y] + 3, 5, 5, true, false, 1, JGColor.white);
			}else if(phase == 1){
				engine.drawString(ATK_MSGS[selector_x * 2 + selector_y].substring(0, 34), 160, 262, 0, new JGFont("Arial", 0, 16), JGColor.white);
				engine.drawString(String.format(ATK_MSGS[selector_x * 2 + selector_y].substring(34), player.getStat(selector_x * 2 + selector_y) / 2 + 1), 160, 287, 0, new JGFont("Arial", 0, 16), JGColor.white);
			}else if(phase == 2){
				engine.drawString("Sometimes, it's not so bad being a square.", 160, 262, 0, new JGFont("Arial", 0, 16), JGColor.white);
				engine.drawString(String.format("He pokes you with a corner for %d damage.", relevant_stat / 2 + 1), 160, 287, 0, new JGFont("Arial", 0, 16), JGColor.white);
			}else if(phase == 3){
				engine.drawString("You win the fight", 160, 262, 0, new JGFont("Arial", 0, 16), JGColor.white);
				engine.drawString(String.format("and gain %d exp in %s.", relevant_stat / 2 + 1, enemy.name), 160, 287, 0, new JGFont("Arial", 0, 16), JGColor.white);
			}
			
		}
	}
	public void moveLeft(){
		selector_x = 0;
	}
	public void moveRight(){
		selector_x = 1;
	}
	public void moveUp(){
		selector_y = 0;
	}
	public void moveDown(){
		selector_y = 1;
	}
}
