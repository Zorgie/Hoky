package team20;

import hockey.api.Player;
import hockey.api.Position;

import java.awt.Color;
import java.util.Random;

public abstract class BasePlayer extends Player {
	// The middle of the opponents goal, on the goal line
	protected static final Position GOAL_POSITION = new Position(2600, 0);
	private Random rnd;

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

	// Penalty shot
	public void penaltyShot() {
		int targetX = 3000 - 400;
		int targetY = rnd.nextInt() % 180 - 90;

		setDebugPoint(GOAL_POSITION.getX(), GOAL_POSITION.getY(), Color.BLUE);
		
		boolean shoot = false;

		if (getX() > 1000)
			shoot = true;
		if (shoot) {
			shoot(GOAL_POSITION, MAX_SHOT_SPEED);
		} else {
			skate(GOAL_POSITION, MAX_SPEED);
		}
	}

	// Player intelligence goes here
	public void step() {
	}
}
