package application;

public class Main {

	public static void main(String[] args) {
		//Power up Round - the First round that occurs when OperatorInterface calls executeRound();
		int minDesired, maxDesired, minAlarm, maxAlarm;
		minDesired = 68;
		maxDesired = 90;
		minAlarm = 68;
		maxAlarm = 92;
		Thermostat testThermostat = new Thermostat(minDesired, maxDesired, minAlarm, maxAlarm);
		
		//Expect both Thermostat Functions to be in init mode
		System.out.println("Monitor status should be in init mode: " + testThermostat.getMonitorStatus());
		System.out.println("Regulator status should be in init mode: " +testThermostat.getRegulatorStatus());

		//Test Case #1

		double temperature = 67.5;
		
		for (int it = 0; it < 12; it++) {
			System.out.println("\n\t\t\tRound " + (it + 1 + ":\n"));
			testThermostat.executeRound(temperature);
			System.out.printf("%s%.1f%n", "The temp passed in was: ", temperature);
			System.out.println("Heat source turned on: " + testThermostat.getHeatControl());
			System.out.println("Status of the Temp Regulator: " + testThermostat.getRegulatorStatus());
			System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());
			
			System.out.println("\n\nAlarm is turned on: " + testThermostat.getAlarmControl());
			System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
			temperature += 0.1;
		}

		//Test Case #2
		testThermostat = new Thermostat(70, 90, 68, 92);
		
		//Current temp is bad (as indicated by a 0 sent by the Op interface) then both Thermostat functions should be in failed mode
		testThermostat.executeRound(68);
		testThermostat.executeRound(0);
		
		System.out.println("\n\nStatus of the Temp Regulator should be failed: " + testThermostat.getRegulatorStatus());
		System.out.println("And The Status of the Temp Monitor should be failed: " + testThermostat.getMonitorStatus());

		//Test Case #3
		testThermostat = new Thermostat(70, 90, 68, 92);
		testThermostat.executeRound(68);
		testThermostat.changeDesiredTempRange(0, 90);
		testThermostat.executeRound(69);
		
		System.out.println("\n\nStatus of the Temp Regulator should be failed: " + testThermostat.getRegulatorStatus());
		System.out.println("And The Status of the Temp Monitor should be normal: " + testThermostat.getMonitorStatus());

		//Test Case #4
		testThermostat = new Thermostat(70, 90, 68, 92);
		testThermostat.executeRound(68);
		testThermostat.changeAlarmTempRange(68, 0);
		testThermostat.executeRound(69);
		
		System.out.println("\n\nStatus of the Temp Regulator should be normal: " + testThermostat.getRegulatorStatus());
		System.out.println("And The Status of the Temp Monitor should be failed: " + testThermostat.getMonitorStatus());

	}

}
