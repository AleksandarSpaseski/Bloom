package bloom;

public class BadItem extends Item {
	private Type vid;

	public BadItem() {
		super();
		vid = Type.Item5;
	}

	public Type getVid() {
		return vid;
	}

	public int getPoints() {
		return vid.getPoints();
	}

}
