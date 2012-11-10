package team20;

import hockey.api.Position;

public class Forward extends BasePlayer {
	private Position forwardPoint;
	private Position forwardReturn;
	private boolean forward;
	private boolean initiated;
	// Number of forward
	
	public Forward(int which){
		if(which==0){
			forwardPoint = new Position(2600, -500);
			forwardReturn = new Position(0, -500);
		}else{
			forwardPoint = new Position(2600, 500);
			forwardReturn = new Position(2600, 500);
		}
	}
	
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
			if(!initiated){
				skate(forwardPoint, MAX_SPEED);
				forward = true;
			}
			if (forward && getX() > 1750){
					skate(forwardReturn, MAX_SPEED/2);
					forward = false;
			}
			if(!forward && getX() <1250){
					skate(forwardPoint, MAX_SPEED/2);
					forward = true;
			}
		}
			
		endStep();
	}
}
