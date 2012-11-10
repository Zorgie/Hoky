package team20;

import hockey.api.Util;

public class Defender extends BasePlayer {
	// Number of defender
	public int getNumber() {
		return 10;
	}

	private int id;

	public Defender(int a) {
		id = a;
	}

	// Name of defender
	public String getName() {
		if(id == 0)
			return "Skogsturken";
		return "Laserturken";
	}

	// Make left defender left handed, right defender right handed.
	public boolean isLeftHanded() {
		return getIndex() == 1;
	}

	// Initiate
	public void init() {
		setAimOnStick(false);
	}

	public void ZonePlay(int zone) {
		int movex = 0;
		int movey = 0;
		if (getIndex() == 1) {
			movey = 750;
		} else {
			movey = -750;
		}
		if (zone == 1) {
			movex = -2300;
		}
		if (zone == 2) {
			movex = -150;
		}
		if (zone == 3) {
			movex = 2300;
		}
		skate(movex, movey, MAX_SPEED);

	}

	// Defender intelligence
	public void step() {
		preStep();
		if (penalty) {
			endStep();
			return;
		}
		setMessage("Not penalty.");

		if (hasPuck()) {
			pass(getPlayer(5));
			endStep();
			return;
		}

		if (!teamHeldPuck()) {

			int zone = getPuckZone();

			if (getPuck().getHolder() != null)
				skate(getPuck().getHolder(), MAX_SPEED);
		}

		endStep();
	}
}
