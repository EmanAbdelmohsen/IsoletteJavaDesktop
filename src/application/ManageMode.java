package application;

import application.Constants.StatusEnum;

/**
 * This class is used to capture the logic to manage the mode for both the Temperature Regulator and the Temperature Monitor.
 * Although both functions are completely independent they have the same processing (algorithm) for determining the
 * mode states of the components.
 * @author calgiles3
 *
 */
public class ManageMode {
	
	private double elapsedRoundsForTimeout;
	private StatusEnum status;
	
	public ManageMode() {
		elapsedRoundsForTimeout = 0;
		setStatus(StatusEnum.INIT);
	}
	
	public void executeRound() {
		elapsedRoundsForTimeout++;
	}

	public StatusEnum executeRound(boolean interfaceFailure, boolean internalFailure, double temperature) {
		switch(getStatus()) {
			case INIT:
				elapsedRoundsForTimeout += Constants.ROUND;
				if (!interfaceFailure && !internalFailure && temperature != 0) {
					setStatus(StatusEnum.NORMAL);
					elapsedRoundsForTimeout = 0;
				}
				
				if (elapsedRoundsForTimeout > 1.0) {;
					setStatus(StatusEnum.FAILED);;
				}
				break;
			case NORMAL:
				if (interfaceFailure || internalFailure || temperature == 0 ) {
					setStatus(StatusEnum.FAILED);
				}
				break;
			default:
				//Do nothing
		}
		return getStatus();
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
