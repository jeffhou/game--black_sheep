package game_jh365;

import jgame.JGColor;

class BSUnit{
	int position_x;
	int position_y;
	
	int statRed;
	int statGreen;
	int statBlue;
	
	String name;
	BSUnit(){		
		statRed = 5;
		statGreen = 5;
		statBlue = 5;
	}
	public int getRed(){
		return statRed;
	}
	public int getGreen(){
		return statGreen;
	}
	public int getBlue(){
		return statBlue;
	}
	public JGColor getColor(){
		return new JGColor(getRed(), getGreen(), getBlue());
	}
	public int getStat(int index){
		if(index == 0){
			return getRed();
		}else if(index == 1){
			return getGreen();
		}else if(index == 2){
			return getBlue();
		}else{
			return 1;
		}
	}
	public int getPositionX(){
		return position_x;
	}
	public int getPositionY(){
		return position_y;
	}
	public void setPosition(int x, int y){
		position_x = x;
		position_y = y;
	}
	public void increaseRed(int increaseAmt){
		statRed += increaseAmt;
		if(statRed > 255) statRed = 255;
	}
	public void increaseGreen(int increaseAmt){
		statGreen += increaseAmt;
		if(statGreen > 255) statGreen = 255;
	}
	public void increaseBlue(int increaseAmt){
		statBlue += increaseAmt;
		if(statBlue > 255) statBlue = 255;
	}
	public void increaseExp(BSUnit deadEnemy, int numEnemies){
		increaseRed(((deadEnemy.statRed - 5) / 250) * numEnemies);
		increaseBlue(((deadEnemy.statBlue - 5) / 250) * numEnemies);
		increaseGreen(((deadEnemy.statGreen - 5) / 250) * numEnemies);
	}
	public String getName(){
		return name;
	}
}