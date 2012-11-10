package team20;

import hockey.api.Util;

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
	public void ZonePlay(int zone){
		int movex=0;
		int movey=0;
		if(getIndex()==1){
			movey=750;
		}else{
			movey=-750;
		}
		if(zone==1){
			movex=-2300;
		}
		if(zone==2){
			movex=-150;
		}
		if(zone==3){
			movex=2300;
		}
		skate(movex,movey,1000);
		
	}

	// Defender intelligence
	public void step() {
		preStep();
		if (penalty) {
			endStep();
			return;
		}
		setMessage("Not penalty.");
		
		if (!teamHeldPuck()){
			int zone=getPuckZone();
			
			ZonePlay(zone);
			setMessage("Defending Zone");
			if(Util.dist(this, getPuck())<900){
				setMessage("Chasing Puck");
			skate(getPuck().getHolder(), MAX_SPEED);
			}
		}
		if (teamHeldPuck()){
			int zone=getPuckZone();
			setMessage("Zone Playing");
			ZonePlay(zone);
		}
		
		if(this.hasPuck()){
			setMessage("");
			shoot(getPlayer(5),4444);
		}

		endStep();
	}
}
