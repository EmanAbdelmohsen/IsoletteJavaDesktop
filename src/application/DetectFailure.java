package application;

public abstract class DetectFailure {
	private boolean failure;

	public boolean selfCheck() {
		return failure;
	}

	public void setFailure(boolean failure) {
		this.failure = failure;
	}
	
}
