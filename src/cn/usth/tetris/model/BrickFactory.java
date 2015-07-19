package cn.usth.tetris.model;

public class BrickFactory {
	public static Brick getBrick(){
		Brick brick;
		int BrickStyle=(int)(Math.random()*1000)%5;
		switch(BrickStyle){
		case 0:
			brick= new ZBrick();
			return brick;
		case 1:
			brick= new LBrick();
			return brick;
		case 2:
			brick= new TBrick();
			return brick;
		case 3:
			brick= new SquareBrick();
			return brick;
		case 4:
			brick= new IBrick();
			return brick;
		}
		return null;
	}
}
