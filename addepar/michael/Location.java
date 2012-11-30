import java.io.Serializable;

import ants.Direction;


public class Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int x;
	int y;
	
	public Location(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Location to(Direction d) {
		int newx = x;
		int newy = y;
		if (Direction.WEST.equals(d)) {
			newx--;
		}
		if (Direction.EAST.equals(d)) {
			newx++;
		}
		if (Direction.NORTH.equals(d)) {
			newy++;
		}
		if (Direction.SOUTH.equals(d)) {
			newy--;
		}
		return new Location(newx,newy);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location o = (Location)obj;
			return (o.x == x) && (o.y == y);
		}
		return false;
	}
	
	@Override
	public int hashCode() {	
		return x * 100 + y;
	}

	@Override
	public String toString() {	
		return "(" + x + "," + y + ")";
	}
}
