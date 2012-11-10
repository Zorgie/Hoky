package team20;

public class Defender extends BasePlayer {
	// Number of defender
	public int getNumber() {
		return 10;
	}

	// Name of defender
	public String getName() {
		return "Defender";
	}

	// Make left defender left handed, right defender right handed.
	public boolean isLeftHanded() {
		return getIndex() == 1;
	}

	// Initiate
	public void init() {
		setAimOnStick(false);
	}

	// Defender intelligence
	public void step() {
		preStep();
		if (penalty) {
			endStep();
			return;
		}
		setMessage("Not penalty.");
		
		if (getPuck().isHeld())
			skate(getPuck().getHolder(), MAX_SPEED);
		else if (getIndex() == 1)
			skate(this.GOAL_POSITION.getX()+600,this.GOAL_POSITION.getY()-700,1000);
		else
			skate(this.GOAL_POSITION.getX()+600,this.GOAL_POSITION.getY()+700,1000);

		endStep();
	}
}
