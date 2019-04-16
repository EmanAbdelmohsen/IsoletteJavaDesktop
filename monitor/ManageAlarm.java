package monitor;

import application.Constants;
import application.ManageControlInterface;
import application.Constants.StatusEnum;

/**
 * This class represents the logic for managing the alarm component of the Monitor temperature function.
 * @author calgiles3
 *
 */

public class ManageAlarm implements ManageControlInterface {
	private boolean alarmed;
	
	public ManageAlarm() {
		alarmed = false;
	}

	@Override
	public boolean executeRound(StatusEnum mode, double temperature, int[] desiredRange) {
		switch(mode) {
		case INIT:
			setAlarmed(false);
			break;
		case NORMAL:
			if (!alarmed && !(temperature < desiredRange[0])
					&& !(temperature > desiredRange[1])) {
				setAlarmed(false);
			}else if(alarmed && (!((temperature + Constants.HYSTERESIS_CONSTANT) > desiredRange[1]))
					&& !(temperature  - Constants.HYSTERESIS_CONSTANT < desiredRange[0])) {
				setAlarmed(false);
			}else {
				setAlarmed(true);
			}
			break;
		case FAILED:
			setAlarmed(true);
			break;
		default:
			//Do nothing
		}
		return isAlarmed();
	}
	
	public boolean isAlarmed() {
		return alarmed;
	}

	public void setAlarmed(boolean alarmed) {
		this.alarmed = alarmed;
	}

}
