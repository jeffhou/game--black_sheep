package game_jh365;
import java.util.ArrayList;

import jgame.platform.JGEngine;
import jgame.*;

public class BlackSheepEngine extends JGEngine{
	BSUnit[][] grid;
	BSPlayer player;
	BSPlayerObject playerObject;
	ArrayList<BSUnit> npcUnits;
	ArrayList<BSUnitObject> npcUnitObjects;
	BSBattleUnit battlePlayer;
	BSBattleUnit battleEnemy;
	BSBattleController battleController;
	
	final int MAX_NPC_UNITS = 100;
	
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
		grid = new BSUnit[BlackSheepGame.WINDOW_TILE_LENGTH][BlackSheepGame.WINDOW_TILE_WIDTH];
		setGameState("Instructions");
	}
	public void startInstructions(){
		removeObjects(null, 0);
	}
	public void paintFrameInstructions(){
		drawString("BLACK SHEEP", pfWidth()/2, 50, 0, new JGFont("Arial", 0, 30), JGColor.white);
		drawString("To win, get 255 in every stat. You gain", pfWidth()/2, 100, 0, new JGFont("Arial", 0, 18), JGColor.white);
		drawString("stats by winning fights. Your skills", pfWidth()/2, 120, 0, new JGFont("Arial", 0, 18), JGColor.white);
		drawString("are based on your individual stats. Use", pfWidth()/2, 140, 0, new JGFont("Arial", 0, 18), JGColor.white);
		drawString("arrow keys and space bar to navigate", pfWidth()/2, 160, 0, new JGFont("Arial", 0, 18), JGColor.white);
		drawString("texts. Esc to reach stats page.", pfWidth()/2, 180, 0, new JGFont("Arial", 0, 18), JGColor.white);
		
		drawString("Press space to begin.", pfWidth()/2, 220, 0, new JGFont("Arial", 0, 18), JGColor.white);
	}
	public void doFrameInstructions(){
		if(getKey(' ')){
			clearKey(' ');
			setGameState("InGame");
		}
	}
	public void startWin(){
		removeObjects(null, 0);
	}
	public void paintFrameWin(){
		drawString("You win!", pfWidth()/2, pfHeight()/2 - 50, 0, new JGFont("Arial", 0, 40), JGColor.white);
	}
	public void startLose(){
		removeObjects(null, 0);
	}
	public void paintFrameLose(){
		drawString("You lose!", pfWidth()/2, pfHeight()/2 - 20, 0, new JGFont("Arial", 0, 40), JGColor.white);
	}
	public void startInGame(){
		removeObjects(null, 0);
		if(npcUnits == null)
			npcUnits = new ArrayList<BSUnit>();
		npcUnitObjects = new ArrayList<BSUnitObject>();
		for(BSUnit i : npcUnits){
			BSUnitObject tempObject = new BSUnitObject(this);
			tempObject.loadUnit(i);
			npcUnitObjects.add(tempObject);
		}
		if(player == null)
			player = new BSPlayer();
		playerObject = new BSPlayerObject(this);
		playerObject.loadUnit(player);
	}
	
	/** A simple timer. */
	int gametimer=0;
	public void doFrameInGame(){
		moveObjects(null, 0);
		gametimer++;
		boolean moving = true;
		//processKeys();
		if(getKey(KeyLeft)){
			clearKey(KeyLeft);
			if(player.position_x > 0) player.position_x -= 1;
		}else if(getKey(KeyRight)){
			clearKey(KeyRight);
			if(player.position_x < 19) player.position_x += 1;
		}else if(getKey(KeyUp)){
			clearKey(KeyUp);
			if(player.position_y > 0) player.position_y -= 1;
		}else if(getKey(KeyDown)){
			clearKey(KeyDown);
			if(player.position_y < 19) player.position_y += 1;
		}else{
			moving = false;
		}

		if(getKey('R')){
			clearKey('R');
			player.increaseRed(5);
		}else if(getKey('G')){
			clearKey('G');
			player.increaseGreen(5);
		}else if(getKey('B')){
			clearKey('B');
			player.increaseBlue(5);
		}
		
		if(getKey(KeyEsc)){
			clearKey(KeyEsc);
			setGameState("PlayerStats");
		}
		if(grid[player.position_x][player.position_y] != null){
			//player.increaseRed(tempUnit.getRed()/5 - 1);
			//player.increaseBlue(tempUnit.getBlue()/5 - 1);
			//player.increaseGreen(tempUnit.getGreen()/5 - 1);
			grid[player.position_x][player.position_y].setPosition(20, 20);
			setGameState("Battle");
		}
		
		if(npcUnits.size() == 0 || moving && (int) random(0, 6, 1) == 1){
			generateNPC();
		}
		//random(-2, 2, 1);
	}
	public void generateNPC(){
		
		int x = random(0, BlackSheepGame.WINDOW_TILE_LENGTH - 1, 1);
		int y = random(0, BlackSheepGame.WINDOW_TILE_WIDTH - 1, 1);
		System.out.println(x + " " + y);
		if(player.position_x == x && player.position_y == y) return;
		for(BSUnit i : npcUnits){
			if(i.position_x == x && i.position_y == y){
				return;
			}
		}
		BSUnit newUnit = new BSUnit();
		newUnit.setPosition(x, y);
		grid[x][y] = newUnit; 
		BSUnitObject newUnitObject = new BSUnitObject(this);
		npcUnits.add(newUnit);
		newUnitObject.loadUnit(newUnit);
		npcUnitObjects.add(newUnitObject);
		
		int color = random(0, 2, 1);
		System.out.println("Color: " + color);
		if(color==0){
			newUnit.increaseRed(250);
			newUnit.name="red";
		}
		else if(color==1){
			newUnit.increaseGreen(250);
			newUnit.name="green";
		}
		else if(color==2){
			newUnit.increaseBlue(250);
			newUnit.name="blue";
		}
	}
	public void startPlayerStats(){
		removeObjects(null, 0);
	}
	public void paintFramePlayerStats(){
		drawString("Player Stats", pfWidth()/2, 40, 0, new JGFont("Arial", 0, 30), new JGColor(255 - player.getRed() / 2, 255 - player.getGreen() / 2, 255 - player.getBlue() / 2));
		drawString("Red", 80, 110, 1, new JGFont("Arial", 0, 18), new JGColor(255 - player.getRed() / 2, 255 - player.getGreen() / 2, 255 - player.getBlue() / 2));
		drawString("Green", 80, 160, 1, new JGFont("Arial", 0, 18), new JGColor(255 - player.getRed() / 2, 255 - player.getGreen() / 2, 255 - player.getBlue() / 2));
		drawString("Blue", 80, 210, 1, new JGFont("Arial", 0, 18), new JGColor(255 - player.getRed() / 2, 255 - player.getGreen() / 2, 255 - player.getBlue() / 2));
		drawRect(100, 108, player.getRed() / 2, 18, true, false, 1, JGColor.red);
		drawRect(100, 158, player.getGreen() / 2, 18, true, false, 1, JGColor.green);
		drawRect(100, 208, player.getBlue() / 2, 18, true, false, 1, JGColor.blue);
		drawString(player.getRed() + "", 240, 110, -1, new JGFont("Arial", 0, 18), new JGColor(255 - player.getRed() / 2, 255 - player.getGreen() / 2, 255 - player.getBlue() / 2));
		drawString(player.getGreen() + "", 240, 160, -1, new JGFont("Arial", 0, 18), new JGColor(255 - player.getRed() / 2, 255 - player.getGreen() / 2, 255 - player.getBlue() / 2));
		drawString(player.getBlue() + "", 240, 210, -1, new JGFont("Arial", 0, 18), new JGColor(255 - player.getRed() / 2, 255 - player.getGreen() / 2, 255 - player.getBlue() / 2));
		//drawRect(100, 5, player.getRed(), 18, true, false);
	}
	public void doFramePlayerStats(){
		if(getKey(KeyEsc)){
			clearKey(KeyEsc);
			setGameState("InGame");
		}
		System.out.println(npcUnits.size());
	}
	public void startBattle(){
		removeObjects(null, 0);
		
		battlePlayer = new BSBattleUnit(player.getRed(), player, true, this);
		battleEnemy = new BSBattleUnit(npcUnits.size() * (npcUnits.size() + 1) / 4 + 1, grid[player.position_x][player.position_y], false, this);
		battleController = new BSBattleController(player, grid[player.position_x][player.position_y], this);
		
	}
	public void paintFrameBattle(){
	}
	public void doFrameBattle(){
		if(player.getRed() >= 255 && player.getBlue() >= 255 && player.getGreen() >= 255) setGameState("Win");
		if(getKey(' ')){
			clearKey(' ');
			if(battlePlayer.hp == 0) setGameState("Lose");
			if(battleController.phase == 3){
				player.increaseExp(grid[player.position_x][player.position_y], battleController.relevant_stat);
				npcUnits.remove(grid[player.position_x][player.position_y]);
				grid[player.position_x][player.position_y] = null;
				setGameState("InGame");
			}
			battleController.phase = (battleController.phase + 1) % 3;
			battleController.postCalc = false;
			
		}
		if(battleEnemy.hp <= 0 || battleController.phase == 1 && !battleController.postCalc){
			battleEnemy.hp -= player.getStat(battleController.selector_x * 2 + battleController.selector_y) / 2 + 1;
			if(battleEnemy.hp <= 0){
				battleEnemy.hp = 0;
				battleController.phase = 3;
			}
			battleController.postCalc = true;
		}else if(battleController.phase == 2 && !battleController.postCalc){
			battlePlayer.hp -= battleController.relevant_stat / 2 + 1;
			if(battlePlayer.hp <= 0){
				battlePlayer.hp = 0;
			}
			battleController.postCalc = true;
		}
		
		if(getKey('K')){
			clearKey('K');
			battleEnemy.hp -= 1000000;
			
		}else if(getKey(KeyLeft)){
			clearKey(KeyLeft);
			battleController.moveLeft();
		}else if(getKey(KeyRight)){
			clearKey(KeyRight);
			battleController.moveRight();
		}else if(getKey(KeyUp)){
			clearKey(KeyUp);
			battleController.moveUp();
		}else if(getKey(KeyDown)){
			clearKey(KeyDown);
			battleController.moveDown();
		}
		
	}
	
}
