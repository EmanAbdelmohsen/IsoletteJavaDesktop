package monitor;

import application.Constants.StatusEnum;
import application.ManageControlInterface;
import application.ThermostatFunction;

/**
 * This class implements the Temperature Monitoring function of the Thermostat and reports back the status of the function
 * upon conclusion of each round.
 * @author calgiles3
 *
 */

public class TemperatureMonitor extends ThermostatFunction {
	private ManageControlInterface manageAlarm;
	private ManageInterface monitorInterface;

	public TemperatureMonitor(int minTemp, int maxTemp) {
		super(minTemp, maxTemp);
		failure = new MonitorFailure();
		monitorInterface = new ManageInterface();
		manageAlarm = new ManageAlarm();
	}
	
	@Override
	public StatusEnum executeRound(double temperature) {
		boolean internalFailureReceived = failure.selfCheck();//DMF
		boolean interFaceFailureReceived = getMonitorInterfaceFailure();//MMI-2
		StatusEnum modeResult = mode.executeRound(internalFailureReceived, interFaceFailureReceived, temperature);//MMM
		monitorInterface.setStatus(modeResult);
		monitorInterface.setTemperature(temperature);
		int[] desiredRange = monitorInterface.getDesiredTempRange();//MMI-3
		this.setController(manageAlarm.executeRound(modeResult, temperature, desiredRange));//MA
		this.setStatus(monitorInterface.getStatus());//MMI-1
		return monitorInterface.getStatus();
	}
	
	private boolean getMonitorInterfaceFailure() {
		int[] tempRange = {this.getMinTemp(), this.getMaxTemp()};
		monitorInterface.setTempRange(tempRange);
		return monitorInterface.getInterfaceFailure();//MRI-3
	}
}
