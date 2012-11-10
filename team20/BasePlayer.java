package team20;

import hockey.api.Player;
import hockey.api.Position;

import java.awt.Color;
import java.util.Random;

public abstract class BasePlayer extends Player {
	// The middle of the opponents goal, on the goal line
	protected static final Position GOAL_POSITION = new Position(2600, 0);
	private Random rnd;
	protected boolean penalty;

	// Left handed?
	public boolean isLeftHanded() {
		return false;
	}

	// Initiate
	public void init() {
		rnd = new Random();
	}

	// Face off
	public void faceOff() {
	}
	
	public void endStep(){
		penalty = false;
	}

	// Penalty shot
	public void penaltyShot() {
		penalty = true;
		setAimOnStick(true);
		
		setMessage("Penalty.");
		
		if(!hasPuck()){
			skate(getPuck(), MAX_SPEED);
			return;
		}
		
		boolean shoot = false;

		if (getX() > 1500)
			shoot = true;
		if (shoot) {
			shoot(GOAL_POSITION, MAX_SHOT_SPEED);
		} else {
			skate(GOAL_POSITION.getX(), GOAL_POSITION.getY() + 500, MAX_SPEED);
		}
	}
	
	public void preStep(){
		if(Math.abs(getGoalKeeper(0).getY()) > 1500){
			penaltyShot();
		}
	}

	// Player intelligence goes here
	public void step() {
	}
}
