package team20;

import hockey.Util;
import hockey.api.Position;

public class Forward extends BasePlayer {
	private Position forwardPoint;
	private Position forwardReturn;
	private boolean forward;
	private boolean initiated;
	private boolean left;
	private int id;

	// Number of forward

	public Forward(int which) {
		id = which;
		if (which == 0) {
			forwardPoint = new Position(866, -500);
			forwardReturn = new Position(-867, -500);
		} else {
			forwardPoint = new Position(866, 500);
			forwardReturn = new Position(-867, 500);
			left = true;

		}
	}

	public int getNumber() {
		return 15;
	}

	public boolean isLeftHanded() {
		return left;
	}

	// Name of forward
	public String getName() {
		if(id == 0)
			return "Ridiculously Photogenetic Forward";
		return "Bad Luck Forward";
	}

	// Intelligence of forward
	public void step() {
		preStep();
		if (penalty) {
			endStep();
			return;
		}
		setMessage("Not penalty.");
		if (hasPuck()) {
			kamikaze();
		} else {
			if (!puckAtOrigin() && Util.dist(getPuck().getX() - getX(), getPuck().getY() - getY()) < 400) {
				setAimOnStick(true);
				skate(getPuck(), MAX_SPEED * 3 / 4);
			} else {
				if (!initiated) {
					skate(forwardPoint, MAX_SPEED / 2);
					forward = true;
					initiated = true;
				}
				if (forward && getX() > 650) {
					skate(forwardReturn, MAX_SPEED / 2);
					forward = false;
				}
				if (!forward && getX() < 0) {
					skate(forwardPoint, MAX_SPEED / 2);
					forward = true;
				}
			}
		}

		endStep();
	}
}
