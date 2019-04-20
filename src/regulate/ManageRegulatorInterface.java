package regulate;

import application.Constants.StatusEnum;
import monitor.ManageInterface;
/**
 * This class defines the interface logic for the Thermostat manage Regulator interface function. It extends the ManageInterface
 * concrete class which abstracts all of the common functionality in both of the independent Thermostat main interface functions.
 * @author calgiles3
 *
 */

public class ManageRegulatorInterface extends ManageInterface{
	
	/**
	 * Implements requirement MRI-2
	 * @return
	 */
	public int getDisplayTemp() {
		return this.getStatus().equals(StatusEnum.NORMAL) ? roundToInt() : 0;//0 temperature indicates an UNSPECIFIED.
	}
	
	private int roundToInt() {
		return Math.round((float)this.getTemperature());
	}

}
