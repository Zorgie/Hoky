package team20;

import java.awt.Color;

import hockey.Util;
import hockey.api.IPlayer;

public class Center extends BasePlayer {
	// Number of center player
	public int getNumber() {
		return 19;
	}

	// Name of center player
	public String getName() {
		return "Center";
	}

	// Center player's intelligence
	public void step() {
		preStep();
		if (penalty) {
			endStep();
			return;
		}
		setMessage("Not penalty.");
		if (hasPuck()) {
			double closest = distanceToClosestOpponent();
			if (closest < 300) {
				setMessage("Climbin' your windows, shatching yo' people up!");
				int targetPlayer = 3 + rnd.nextInt()%2;
				shoot(getPlayer(targetPlayer), MAX_SHOT_SPEED);
				
			} else {
				penaltyShot();
			}
			// // Distance to enemy goal keeper is less than 1k.
			// IPlayer self = getPlayer(5);
			// IPlayer goalie = getGoalKeeper(6);
			// double goalDist = Math
			// .sqrt(Math.pow(self.getX() - goalie.getX(), 2)
			// + Math.pow(self.getY() - goalie.getY(), 2));
			// if (goalDist < 1500) {
			// penaltyShot();
			// } else {
			// skate(GOAL_POSITION, MAX_SPEED);
			// }
		} else {
			if (puckAtOrigin()) {
				setMessage("Faceoff, motherfucker!");
				setAimOnStick(true);
				skate(getX() + 1000, getY(), 100);
			} else {
				setMessage("Dolla dolla bill, y'all!");
				setAimOnStick(true);
				skate(getPuck(), MAX_SPEED);
			}
		}

		endStep();
	}
}
