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
				boolean status = elapsedRoundsForTimeout > 1.0 || (interfaceFailure && internalFailure);
				setStatus(status ? StatusEnum.FAILED : StatusEnum.NORMAL);
				break;
			case NORMAL:
				if (interfaceFailure && internalFailure) {
					setStatus(StatusEnum.FAILED);
				}
				break;
			case FAILED:
				//Do nothing
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
