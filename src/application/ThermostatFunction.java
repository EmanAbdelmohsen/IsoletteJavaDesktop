package application;

import application.Constants.StatusEnum;

public abstract class ThermostatFunction {
	private int minTemp;
	private int maxTemp;
	private StatusEnum status;//Only used for getting state; not for setting externally
	private boolean controller;//Only used for getting state; not for setting externally
	protected DetectFailure failure;
	protected ManageMode mode;

	
	public abstract StatusEnum executeRound(double temperature);//took out boolean tempValid
	
	public ThermostatFunction(int minTemp, int maxTemp) {
		mode = new ManageMode();//Instantiates a manage mode instance for either Thermostat functions
		status = mode.getStatus();
		controller = false;
		setMinTemp(minTemp);
		setMaxTemp(maxTemp);
	}

	public int getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	public StatusEnum getStatus() {
		return status;
	}

	protected void setStatus(StatusEnum status) {
		this.status = status;
	}

	public boolean getController() {
		return controller;
	}

	protected void setController(boolean controller) {
		this.controller = controller;
	}


}
