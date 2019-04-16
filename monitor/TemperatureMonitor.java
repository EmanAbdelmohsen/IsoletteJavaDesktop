package monitor;

import application.Constants.StatusEnum;
import application.ManageControlInterface;
import application.ThermostatFunction;

public class TemperatureMonitor extends ThermostatFunction {
	private ManageControlInterface manageAlarm;
	private ManageInterface monitorInterface;

	public TemperatureMonitor(int minTemp, int maxTemp) {
		super(minTemp, maxTemp);
		failure = new MonitorFailure();
		int[] tempRange = {minTemp, maxTemp};
		monitorInterface = new ManageInterface(tempRange);
		manageAlarm = new ManageAlarm();
	}
	
	//Broken out execution of a round for timeout testing.
	public StatusEnum executeRound() {
		mode.executeRound();
		return monitorInterface.getStatus();
	}
	
	@Override
	public StatusEnum executeRound(double temperature) {
		boolean internalFailureReceived = failure.selfCheck();//DMF
		boolean interFaceFailureReceived = monitorInterface.getInterfaceFailure();//MMI-2
		StatusEnum modeResult = mode.executeRound(internalFailureReceived, interFaceFailureReceived, temperature);//MMM
		monitorInterface.setStatus(modeResult);
		monitorInterface.setTemperature(temperature);
		int[] desiredRange = monitorInterface.getDesiredTempRange();//MMI-3
		this.setController(manageAlarm.executeRound(modeResult, temperature, desiredRange));//MA
		this.setStatus(monitorInterface.getStatus());//MMI-1
		return monitorInterface.getStatus();
	}
	
}
