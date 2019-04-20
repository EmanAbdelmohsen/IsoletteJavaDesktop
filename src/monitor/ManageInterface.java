package monitor;

import java.util.Arrays;

import application.Constants;
import application.Constants.StatusEnum;

/**
 * This class defines the interface logic for the Thermostat manage Monitor interface function.
 * @author calgiles3
 *
 */

public class ManageInterface {
	private StatusEnum status;
	private double temperature;
	private int[] tempRange;//tempRange.length == 2 and tempRange[0] reps the lower value, tempRange[1] reps the upper value.
	private boolean tempRangeChanged;//To indicate a temperature range change after the initial set

	public ManageInterface() {
		this.setStatus(StatusEnum.INIT);
	}
	/**
	 * Implements requirements MRI-1 & MMI-1
	 * @return
	 */
	public StatusEnum getStatus() {
		return status;
	}
	
	public String getInterfaceStatus() {
		return this.getStatus().getValue();
	}
	
	/**
	 * Implements requirements MRI-3 & MMI-2
	 * @return
	 */
	public boolean getInterfaceFailure() {
		if (this.getTempRange() != null) {
			return Arrays.stream(tempRange).anyMatch(v -> v == 0);
		}
		
		return true;
	}
	
	/**
	 * Implements requirements MRI-4 & MMI-3
	 * @return
	 */
	public int[] getDesiredTempRange() {
		int[] unspecified = {0,0};
		return getInterfaceFailure() ? unspecified : getTempRange();
	}
	
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	protected double getTemperature() {//gets the display temperature for the Op Interface (needed?)
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * This method implements the REQ-MRI-9, REQ-MRI-9, REQ-MMI-6 & REQ-MMI-7 functional requirements implicitly
	 * as the values of the tempRange variable determine if the range is meant to be set or is further UNSPECIFIED
	 * (indicated by a 0 value presence in the range tuple)
	 * @return
	 */
	public int[] getTempRange() {
		return tempRange;
	}

	public void setTempRange(int[] tempRange) {
		hasTempRangeChanged(tempRange);
		this.tempRange = tempRange;
	}

	/**
	 * This method is designed to kick off internal changes in state on account of a temperature rate change.
	 * @param tempRange
	 */
	private void hasTempRangeChanged(int[] tempRange) {
		if (this.getTempRange() != null && !this.getTempRange().equals(tempRange)) {
			this.setTempRangeChanged(true);
		}else {
			this.setTempRangeChanged(false);
		}
	}

	/**
	 * Can be used to detect changes in the temperature range state of the component.
	 * @return
	 */
	public boolean isTempRangeChanged() {
		return tempRangeChanged;
	}

	private void setTempRangeChanged(boolean tempRangeChanged) {
		this.tempRangeChanged = tempRangeChanged;
	}
}
