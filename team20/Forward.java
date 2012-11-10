package team20;

import hockey.api.Position;

public class Forward extends BasePlayer {
	private Position forwardPoint = new Position(2600, 500);
	// Number of forward
	public int getNumber() {
		return 15;
	}

	// Name of forward
	public String getName() {
		return "Forward";
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
			shoot(getPlayer(5), 4444); // pass center player
		} else {
			skate(forwardPoint, MAX_SPEED); // get the puck
		}

		endStep();
	}
}
