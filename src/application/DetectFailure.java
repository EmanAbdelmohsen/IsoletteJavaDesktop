package application;

public abstract class DetectFailure {
	private boolean failure;
	
	public DetectFailure() {
		failure = false;
	}

	public boolean selfCheck() {
		return getFailure();
	}
	
	private boolean getFailure() {
		return failure;
	}

	public void setFailure(boolean failure) {
		this.failure = failure;
	}
	
}
