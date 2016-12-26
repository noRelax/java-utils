package player.module;

public class VolumeBarEvent {
	private Object source;
	private int comparison;
	private int value;

	protected void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	protected void setComparison(int comparison) {
		this.comparison = comparison;
	}

	public int getComparison() {
		return comparison;
	}

	protected void setSource(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}
}
