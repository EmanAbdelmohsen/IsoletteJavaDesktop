package application;

public class Constants {

	public static final double ROUND = 0.1;
	public static final int INITIALIZATION_TIMEOUT = 1;
	public static final double HYSTERESIS_CONSTANT = 0.5;
	
	public static enum StatusEnum {
		INIT("init"),
		NORMAL("On"),
		FAILED("Failed");
		
		private String value;
		
		private StatusEnum(String value) {
			this.setValue(value);
		}

		public String getValue() {
			return value;
		}

		private void setValue(String value) {
			this.value = value;
		}
	}
}
