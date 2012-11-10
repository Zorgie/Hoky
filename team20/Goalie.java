package team20;

import hockey.api.GoalKeeper;
import hockey.api.Position;

import java.awt.Color;

public class Goalie extends GoalKeeper {
	// Middle of our own goalcage, on the goal line
	protected static final Position GOAL_POSITION = new Position(-2600, 0);

	// Number of the goalie.
	public int getNumber() {
		return 1;
	}

	// Name of the goalie.
	public String getName() {
		return "The Goalie";
	}

	// Left handed goalie
	public boolean isLeftHanded() {
		return true;
	}

	// Initiate
	public void init() {
	}

	// Face off
	public void faceOff() {
	}

	// Called when the goalie is about to receive a penalty shot
	public void penaltyShot() {
	}

	// Intelligence of goalie.
	public void step() {
		if(hasPuck()){
			shoot(getPlayer(5), 1000);
			return;
		}
		skate(getGoaliePos(90), 120);
		turn(getPuck(), MAX_TURN_SPEED);
	}
	
	public Position getGoaliePos(int cm){
		if(getPuck().getX()<-2600){
			if(getPuck().getY()>=0){
				return new Position(GOAL_POSITION.getX(),GOAL_POSITION.getY()+cm);
			}
			if(getPuck().getY()<0){
				return new Position(GOAL_POSITION.getX(),GOAL_POSITION.getY()-cm);
			}
		}
		double dx= GOAL_POSITION.getX()-getPuck().getX();
		double dy=-getPuck().getY();
		double angle = Math.atan(dy/dx);
		int cmx=(int) (cm*Math.cos(angle));
		int cmy=(int) (cm*Math.sin(angle));
		Position p =new Position(GOAL_POSITION.getX()+cmx,GOAL_POSITION.getY()+cmy);
		setDebugPoint(p.getX(), p.getY(), Color.BLUE);
		showDebugPoint(true);
		return p;
	}
}
