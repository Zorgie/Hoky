package team20;

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
		if(penalty){
			endStep();
			return;
		}
		setMessage("Not penalty.");
		if (hasPuck()) {
			// Distance to enemy goal keeper is less than 1k.
			IPlayer self = getPlayer(5);
			IPlayer goalie = getGoalKeeper(6);
			double goalDist = Math
					.sqrt(Math.pow(self.getX() - goalie.getX(), 2)
							+ Math.pow(self.getY() - goalie.getY(), 2));
			if (goalDist < 1000) {
				penaltyShot();
			} else {
				skate(GOAL_POSITION, MAX_SPEED);
			}
		} else
			skate(0, getPuck().getY(), 1000);

		endStep();
	}
}
