package application;
/**
 * This is the Parent class for both internal failures for the heat source and the alarm. This class could be implemented
 * as a bean class in a more thorough implementation. This class also currently does not contain any abstract
 * methods but has been designed for extensibility.
 * @author calgiles3
 *
 */
public abstract class DetectFailure {
	private boolean failure;

	public boolean selfCheck() {
		return failure;
	}

	public void setFailure(boolean failure) {
		this.failure = failure;
	}
	
}
