import java.io.Serializable;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int foodCount;
	boolean visited;

	public Data(int distance) {
		this.minimumDistance = distance;
	}

	/**
	 * minimum steps to reach this surroundings from the start
	 */
	int minimumDistance;

	public int getMinimumDistance() {
		return minimumDistance;
	}

	public void updateDistance(int distanceHere) {
		if (distanceHere < minimumDistance) {
			System.out.println(" setting distance to "+distanceHere);
			minimumDistance = distanceHere;
		}
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}

}
