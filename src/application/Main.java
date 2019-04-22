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
		testThermostat = new Thermostat(minDesired, maxDesired, minAlarm, maxAlarm);
		
		//Current temp is bad (as indicated by a 0 sent by the Op interface) then both Thermostat functions should be in failed mode
		testThermostat.executeRound(68);
		testThermostat.executeRound(0);
		
		System.out.println("\n\nStatus of the Temp Regulator should be failed: " + testThermostat.getRegulatorStatus());
		System.out.println("And The Status of the Temp Monitor should be failed: " + testThermostat.getMonitorStatus());

		//Test Case #3
		testThermostat = new Thermostat(minDesired, maxDesired, minAlarm, maxAlarm);
		testThermostat.executeRound(68);
		testThermostat.changeDesiredTempRange(0, 90);
		testThermostat.executeRound(69);
		
		System.out.println("\n\nStatus of the Temp Regulator should be failed: " + testThermostat.getRegulatorStatus());
		System.out.println("And The Status of the Temp Monitor should be normal: " + testThermostat.getMonitorStatus());

		//Test Case #4
		testThermostat = new Thermostat(minDesired, maxDesired, minAlarm, maxAlarm);
		testThermostat.executeRound(68);
		testThermostat.changeAlarmTempRange(68, 0);
		testThermostat.executeRound(69);
		
		System.out.println("\n\nStatus of the Temp Regulator should be normal: " + testThermostat.getRegulatorStatus());
		System.out.println("And The Status of the Temp Monitor should be failed: " + testThermostat.getMonitorStatus());

		System.out.println("\n\n\t\tTIMEOUT TC'S\n");
		System.out.println("\n\n\t\tRound 1:\n");

		//Test Case #5 - Simulate an internal timeout failure after 1 second (10 rounds) has completed for the Regulator
		//i.e. InternalInterface Timeout
		testThermostat = new Thermostat(minDesired, 0, minAlarm, maxAlarm);
		for (int it = 0; it < 10; it++) {
			testThermostat.executeRound(temperature);
			System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
			System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
			System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());
			temperature += 0.1;
		}
		
		//Timeout round
		testThermostat.executeRound(temperature);
		System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
		System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
		System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());

		//Must power cycle to reset failed state
		testThermostat.changeDesiredTempRange(minDesired, maxDesired);
		testThermostat.executeRound(temperature);
		System.out.println("\nStatus of the Temp Regulator should still be failed: " + testThermostat.getRegulatorStatus());
		System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
		System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());

		//Test Case #6 - Simulate an internal timeout failure after 1 second (10 rounds) has completed for the Monitor
		//i.e. InternalInterface Timeout
		System.out.println("\n\n\t\tRound 2:\n");

		testThermostat = new Thermostat(minDesired, maxDesired, 0, maxAlarm);
		for (int it = 0; it < 10; it++) {
			testThermostat.executeRound(temperature);
			System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
			System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
			System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());
			temperature += 0.1;
		}
		
		//Avoid Timeout
		testThermostat.changeAlarmTempRange(minDesired, maxDesired);
		testThermostat.executeRound(temperature);
		System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
		System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
		System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());
		
		testThermostat.executeRound(temperature);
		System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
		System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
		System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());

		//Move from On to failed state
		testThermostat.changeAlarmTempRange(minDesired, 0);
		testThermostat.executeRound(temperature);
		System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
		System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
		System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());

		
		System.out.println("\n\n\t\tRound 3:\n");

		//Test Case #7 - Simulate a temperature sensor failure
		testThermostat = new Thermostat(minDesired, maxDesired, minAlarm, maxAlarm);
		temperature = 0;
		for (int it = 0; it < 10; it++) {
			testThermostat.executeRound(temperature);
			System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
			System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
			System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());
		}
		
		testThermostat.executeRound(temperature);
		System.out.println("\nStatus of the Temp Regulator: " + testThermostat.getRegulatorStatus());
		System.out.println("Status of the Temp Monitor: " + testThermostat.getMonitorStatus());
		System.out.println("Current display temp from the Temp Regulator: " + testThermostat.getDisplayTemperature());

	}
	
	
	public void isoletteIsOn() {//Isolette powered On checkbox
		int minimumDesiredTemp = 97;
		Thermostat thermostat = null;
		executeRoundOne(minimumDesiredTemp, thermostat);
		
		try {
			executeRemainingRounds(minimumDesiredTemp, thermostat);
		} catch (InterruptedException e) {
			System.err.println("Exception thrown");
			e.printStackTrace();
		}
	}
	
	
	public void executeRoundOne(int minimumDesiredTemp, Thermostat thermostat) {
		thermostat = new Thermostat(minimumDesiredTemp, 99, 96, 100);//Round 1; use variables here
		displayThermostatResults(thermostat);
		
	}
	
	public void executeRemainingRounds(int minimumDesiredTemp, Thermostat thermostat) throws InterruptedException {//input from UI checkbox "Powered On"
		double currentTempFromSensor = 95;
		for (int it = 0; it < 1201; it++) {
			currentTempFromSensor = generateTemperature(currentTempFromSensor);
			thermostat.executeRound(currentTempFromSensor);
		
			if (it == 600) {//temp increases by 1
				Thread.sleep(10000);
			}
			

			if (it == 900) {//alarm should be false
				Thread.sleep(10000);
			}
			
			//At round 1200 currentTempFromSensor == minimumDesiredTemp; getHeatControl() == false;
			
			
			displayThermostatResults(thermostat);
		
			if (currentTempFromSensor == minimumDesiredTemp) {
				break;
			}
		}
	}

	private void displayThermostatResults(Thermostat thermostat) {//Sends back results to the UI display.
		thermostat.getAlarmControl();
		thermostat.getDisplayTemperature();
		thermostat.getHeatControl();
		thermostat.getMonitorStatus();
		thermostat.getRegulatorStatus();
	}

	private double generateTemperature(double currentTempFromSensor) {
		currentTempFromSensor += 1/600;
		return currentTempFromSensor;
	}

}
