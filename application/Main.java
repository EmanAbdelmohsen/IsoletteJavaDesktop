package application;

import monitor.TemperatureMonitor;
import regulate.TemperatureRegulator;

public class Main {

	public static void main(String[] args) {
		TemperatureRegulator temperatureRegulatorFunction = new TemperatureRegulator(70, 90);
		TemperatureMonitor temperatureMonitorFunction = new TemperatureMonitor(68, 92);
		double temperature = 67;
		
		for (int it = 0; it < 2; it++) {
			System.out.println("\n\t\t\tRound " + (it + 1 + ":\n"));
			temperatureRegulatorFunction.executeRound(temperature);
			temperatureMonitorFunction.executeRound(temperature);
			
			System.out.println("Heat source turned on: " + temperatureRegulatorFunction.getController());
			System.out.println("Status of the Temp Regulator: " + temperatureRegulatorFunction.getStatus());
			System.out.println("Current display temp from the Temp Regulator: " + temperatureRegulatorFunction.getDisplayTemperature());
			
			System.out.println("\n\nAlarm is turned on: " + temperatureMonitorFunction.getController());
			System.out.println("Status of the Temp Monitor: " + temperatureMonitorFunction.getStatus());
		}

	}

}
