package application;

import monitor.TemperatureMonitor;
import regulate.TemperatureRegulator;


/**
 * This class implements the Temperature Regulator and Monitoring functions in one single Thermostat component.
 * This is a singleton class and the object instantiated from this class receives temperature input from the
 * temperature sensor in order to execute each round. The initial instantiation of this class is designed to simulate
 * a power cycle (on/off).
 * @author calgiles3
 *
 */
public class Thermostat {
	private TemperatureRegulator regulatorFunction;
	private ThermostatFunction monitorFunction;
	
	public Thermostat(int minDesired, int maxDesired, int minAlarm, int maxAlarm) {
		regulatorFunction = new TemperatureRegulator(minDesired, maxDesired);
		monitorFunction = new TemperatureMonitor(minAlarm, maxAlarm);
	}
	
	public void executeRound(double currentTempFromSensor) {
		regulatorFunction.executeRound(currentTempFromSensor);
		monitorFunction.executeRound(currentTempFromSensor);
	}
	
	public void changeDesiredTempRange(int minDesired, int maxDesired) {
		regulatorFunction.setMinTemp(minDesired);
		regulatorFunction.setMaxTemp(maxDesired);
	}
	
	public void changeAlarmTempRange(int minDesired, int maxDesired) {
		monitorFunction.setMinTemp(minDesired);
		monitorFunction.setMaxTemp(maxDesired);
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
