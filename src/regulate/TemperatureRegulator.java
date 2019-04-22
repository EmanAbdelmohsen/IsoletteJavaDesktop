package regulate;

import application.Constants.StatusEnum;
import application.ManageControlInterface;
import application.ThermostatFunction;

/**
 * This class implements the Temperature Regulator function of the Thermostat and reports back the status of the function
 * upon conclusion of each round.
 * @author calgiles3
 *
 */

public class TemperatureRegulator extends ThermostatFunction{
	private ManageControlInterface manageHeatSource;
	private ManageRegulatorInterface regulatorInterface;


	public TemperatureRegulator(int minTemp, int maxTemp) {
		super(minTemp, maxTemp);
		failure = new RegulatorFailure();
		regulatorInterface = new ManageRegulatorInterface();
		manageHeatSource = new ManageHeatSource();
	}
	
	@Override
	public StatusEnum executeRound(double temperature) {
		boolean internalFailureReceived = failure.selfCheck();//DRF
		boolean interFaceFailureReceived = getRegulatorInterfaceFailure();
		StatusEnum modeResult = mode.executeRound(internalFailureReceived, interFaceFailureReceived, temperature);//MRM
		regulatorInterface.setStatus(modeResult);
		regulatorInterface.setTemperature(temperature);
		int[] desiredRange = regulatorInterface.getDesiredTempRange();//MRI-4
		this.setController(manageHeatSource.executeRound(modeResult, temperature, desiredRange));//MHS
		this.setStatus(regulatorInterface.getStatus());//MRI-1
		return regulatorInterface.getStatus();
	}

	private boolean getRegulatorInterfaceFailure() {
		int[] tempRange = {this.getMinTemp(), this.getMaxTemp()};
		regulatorInterface.setTempRange(tempRange);
		return regulatorInterface.getInterfaceFailure();//MRI-3
	}
	
	/**
	 * This method is used to set the display temperature of the Operator Interface. Should get the temp directly
	 * from the Temperature Sensor component
	 * @param temperature
	 */
	public int getDisplayTemperature() {
		return regulatorInterface.getDisplayTemp();//MRI-2
	}
	
}
