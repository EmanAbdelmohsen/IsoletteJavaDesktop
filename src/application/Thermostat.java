package application;

import monitor.TemperatureMonitor;
import regulate.TemperatureRegulator;

public class Thermostat {
	private TemperatureRegulator regulatorFunction;
	private ThermostatFunction monitorFunction;
	
	public Thermostat(int minDesired, int maxDesired, int minAlarm, int maxAlarm) {
		regulatorFunction = new TemperatureRegulator(minDesired, maxDesired);
		monitorFunction = new TemperatureMonitor(minAlarm, maxAlarm);
	}
	
	public void changeDesiredTempRange(int minDesired, int maxDesired) {
		regulatorFunction.setMinTemp(minDesired);
		regulatorFunction.setMaxTemp(maxDesired);
	}
	
	public void changeAlarmTempRange(int minDesired, int maxDesired) {
		monitorFunction.setMinTemp(minDesired);
		monitorFunction.setMaxTemp(maxDesired);
	}
	
	public void executeRound(double currentTempFromSensor) {
		regulatorFunction.executeRound(currentTempFromSensor);
		monitorFunction.executeRound(currentTempFromSensor);
	}
	
	public boolean getHeatControl() {
		return regulatorFunction.getController();
	}
	
	public boolean getAlarmControl() {
		return monitorFunction.getController();
	}
	
	public double getDisplayTemperature() {
		return regulatorFunction.getDisplayTemperature();
	}
	
	public String getRegulatorStatus() {
		return regulatorFunction.getStatus().getValue();
	}
	
	public String getMonitorStatus() {
		return monitorFunction.getStatus().getValue();
	}

}
