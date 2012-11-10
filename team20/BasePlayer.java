package team20;

import hockey.api.IObject;
import hockey.api.IPlayer;
import hockey.api.Player;
import hockey.api.Position;
import hockey.api.Util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public abstract class BasePlayer extends Player {
	// The middle of the opponents goal, on the goal line
	protected static final Position GOAL_POSITION = new Position(2600, 0);
	protected Random rnd;
	protected boolean penalty;
	private ArrayList<IPlayer> teamMembers = new ArrayList<IPlayer>();
	private IPlayer self;
	private IObject puckOrigin;

	// Left handed?
	public boolean isLeftHanded() {
		return false;
	}

	public boolean isBlocked(IPlayer friend) {

		double friendDist = Util.dist(getX() - friend.getX(), getY() - friend.getY());

		for (int i = 0; i < 12; i++) {
			IPlayer cur = getPlayer(i);

			if (cur.isOpponent()) {
				double oppDist = Util.dist(getX() - cur.getX(), getY() - cur.getY()) + Util.dist(friend.getX() - cur.getX(), friend.getY() - cur.getY());
				if(oppDist / friendDist < 1.05){
					return true;
				}
			}
		}
		
		return false;
	}

	public void setPlayer(IPlayer player) {
		self = player;
	}

	public boolean puckAtOrigin() {
		return Util.dist(getPuck().getX() - puckOrigin.getX(), getPuck().getY() - puckOrigin.getY()) < 2;
	}
	
	public boolean enemyAhead(){
		for(int i=0; i<12; i++){
			IPlayer cur = getPlayer(i);
			
			if(cur.isOpponent() && cur != getGoalKeeper(6)){
				if(cur.getX() > getX())
					return true;
			}
		}
		return false;
	}

	public double distanceToClosestOpponent() {
		IPlayer closest = null;
		for (int i = 0; i < 12; ++i) { // G� through all players
			IPlayer cur = getPlayer(i);

			if (cur.isOpponent() && // If player is opponent...
					(closest == null || Util.dist(getX() - cur.getX(), // ...and
																		// closest
																		// so
																		// far...
							getY() - cur.getY()) < Util.dist(getX() - closest.getX(), getY() - closest.getY())))
				closest = cur; // ... then remember him
		}
		return Util.dist(getX() - closest.getX(), getY() - closest.getY());

	}

	// Initiate
	public void init() {
		rnd = new Random();
		teamMembers.add(getPlayer(1));
		teamMembers.add(getPlayer(2));
		teamMembers.add(getPlayer(3));
		teamMembers.add(getPlayer(4));
		teamMembers.add(getPlayer(5));
	}

	// Face off
	public void faceOff() {
		puckOrigin = new Position(getPuck().getX(), getPuck().getY());
	}

	public void endStep() {
		penalty = false;
	}

	public boolean teamHeldPuck() {
		for (IPlayer player : teamMembers) {
			if (player.hasPuck()) {
				return true;
			}
		}
		return false;

	}

	public void moveToZone(int zone) {

	}

	public int getPuckZone() {
		if (getPuck().getX() < -766) {
			return 1;
		} else if (getPuck().getX() > 2133) {
			return 3;
		} else {
			return 2;
		}
	}

	// Penalty shot
	public void penaltyShot() {
		penalty = true;
		setAimOnStick(true);

		setMessage("Penalty.");

		if (!hasPuck()) {
			skate(getPuck(), MAX_SPEED);
			return;
		}

		boolean shoot = false;
		Position targetPos = new Position(2600, 500);

		if (getX() > 1500)
			shoot = true;
		if (shoot) {
			shoot(GOAL_POSITION, MAX_SHOT_SPEED);
		} else {
			skate(targetPos, MAX_SPEED);
		}
	}

	public void preStep() {
		if (Math.abs(getGoalKeeper(0).getY()) > 1500) {
			penaltyShot();
		}
	}
	

	public void pass(IPlayer target){
		double dist = Util.dist(getX() - target.getX(), getY() - target.getY());
	}
	
	public void kamikaze(){
		setAimOnStick(true);

		setMessage("RAAAMBOOOO.");
		
		if(!hasPuck())
			return;

		Position targetPos = new Position(2300, 500);

		if (getX() > 1500 && getX() < 2500)
			shoot(GOAL_POSITION.getX() -  25, GOAL_POSITION.getY(), MAX_SHOT_SPEED);
		else
			skate(targetPos, MAX_SPEED);
	}

	// Player intelligence goes here
	public void step() {
	}
}
