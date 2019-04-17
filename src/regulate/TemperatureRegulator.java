package regulate;

import application.Constants.StatusEnum;
import application.ManageControlInterface;
import application.ThermostatFunction;

public class TemperatureRegulator extends ThermostatFunction{
	private ManageControlInterface manageHeatSource;
	private ManageRegulatorInterface regulatorInterface;


	public TemperatureRegulator(int minTemp, int maxTemp) {
		super(minTemp, maxTemp);
		failure = new RegulatorFailure();
		int[] tempRange = {minTemp, maxTemp};
		regulatorInterface = new ManageRegulatorInterface(tempRange);
		manageHeatSource = new ManageHeatSource();
	}
	
	//Broken out execution of a round for timeout testing.
	public StatusEnum executeRound() {
		mode.executeRound();
		return regulatorInterface.getStatus();
	}

	
	@Override
	public StatusEnum executeRound(double temperature) {
		boolean internalFailureReceived = failure.selfCheck();//DRF
		boolean interFaceFailureReceived = regulatorInterface.getInterfaceFailure();//MRI-3
		StatusEnum modeResult = mode.executeRound(internalFailureReceived, interFaceFailureReceived, temperature);//MRM
		int[] desiredRange = regulatorInterface.getDesiredTempRange();//MRI-4
		this.setController(manageHeatSource.executeRound(modeResult, temperature, desiredRange));//MHS
		this.setStatus(regulatorInterface.getStatus());//MRI-1
		return regulatorInterface.getStatus();
	}
	
	/**
	 * This method is used to set the display temperature of the Operator Interface. Should get the temp directly
	 * from the Temperature Sensor component
	 * @param temperature
	 */
	public int getDisplayTemperature(double temperature) {
		return regulatorInterface.getDisplayTemp();//MRI-2
	}
	
	public boolean getHeatControl() {
		return getController();
	}
	
}
