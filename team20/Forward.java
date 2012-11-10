package team20;

public class Forward extends BasePlayer {
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
			skate(getPuck(), MAX_SPEED); // get the puck
		}

		endStep();
	}
}
