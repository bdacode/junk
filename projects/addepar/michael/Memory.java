import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * In memory, we remember every location of our world and the distance (# of
 * movement) to it from (0,0) *
 * 
 * @author mpermana
 */
public class Memory {

	HashMap<Location, Data> world = new HashMap<Location, Data>();

	/**
	 * remember this location in memory and update the distance to this
	 * location, least distance is remembered
	 */
	public void remember(Location location, int distance) {
		Data data = world.get(location);
		if (null == data) {
			data = new Data(distance);
			world.put(location, data);
		}
		data.updateDistance(distance);
	}

	/**
	 * mark that this location has been visited
	 */
	public void markVisited(Location location) {
		world.get(location).visited = true;
	}

	/**
	 * set food count on this location
	 */
	public void setFoodCount(Location location, int foodCount) {
		Data data = world.get(location);
		if (foodCount > 0)
			System.out
					.println("setFoundCount " + location + " -> " + foodCount);

		data.setFoodCount(foodCount);

	}

	/**
	 * @return location of nearest food from mound
	 */
	public Location findFood() {
		Iterator<Location> it = world.keySet().iterator();
		Location result = null;
		int distance = 0;
		while (it.hasNext()) {
			Location key = (Location) it.next();
			Data data = world.get(key);
			boolean isHome = (key.x == 0) && (key.y == 0);
			if (!isHome && data.foodCount > 0) {
				if (result == null) {
					result = key;
					distance = data.minimumDistance;
				} else {
					int d = data.minimumDistance;
					if (d < distance) {
						// there's nearer food, get that first
						distance = d;
						result = key;
					}
				}

			}
		}
		return result;
	}

	/** breadth first search to find path from source to target
	 * @param source
	 * @param target
	 * @return
	 */
	public LinkedList<Location> searchPath(Location source, Location target) {

		LinkedList<Location> queue = new LinkedList<Location>();
		HashMap<Location, Location> visitedFrom = new HashMap<Location, Location>();
		queue.add(source);
		Location previous = null;
		visitedFrom.put(source, previous);
		LinkedList<Location> path = new LinkedList<Location>();

		while (true) {
			Location now = queue.removeFirst();
			if (now.equals(target)) {

				Location backtrace = now;
				while (!backtrace.equals(source)) {
					path.addFirst(backtrace);
					backtrace = visitedFrom.get(backtrace);
				}
				path.addFirst(backtrace);
				break;
			}

			Location[] neighbours = getNeighbours(now);
			for (Location n : neighbours) {
				if (n != null && null == visitedFrom.get(n)) {
					visitedFrom.put(n, now);
					queue.add(n);
				}
			}
		}

		for (Location xy : path) {
			System.out.print(xy + " ");
		}
		System.out.println();

		return path;
	}

	public Location[] getNeighbours(Location xy) {
		Location west = new Location(xy.x - 1, xy.y);
		Location east = new Location(xy.x + 1, xy.y);
		Location north = new Location(xy.x, xy.y + 1);
		Location south = new Location(xy.x, xy.y - 1);

		// ArrayList<XY> result = new ArrayList<XY>();
		Location result[] = new Location[4];
		if (world.containsKey(west))
			result[0] = west;
		if (world.containsKey(east))
			result[1] = east;
		if (world.containsKey(north))
			result[2] = north;
		if (world.containsKey(south))
			result[3] = south;
		return result;
	}

	public static void main(String[] args) {
		Memory testBFS = new Memory();
		testBFS.remember(new Location(0, 0), 0);
		testBFS.remember(new Location(1, 0), 0);
		testBFS.remember(new Location(1, 1), 0);
		testBFS.remember(new Location(1, 2), 0);
		testBFS.remember(new Location(2, 0), 0);
		testBFS.remember(new Location(2, 2), 0);
		testBFS.searchPath(new Location(0, 0), new Location(2, 2));
	}

	// find nearest unvisited from location, bfs too	
	public Location findUnvisited(Location location) {
		for (Map.Entry<Location,Data> e : world.entrySet()) {
			Location l = e.getKey();
			Data d = e.getValue();
			//System.out.println(l+" -> "+d.visited);
			if (!d.visited)
				return l;
			if (world.get(l).visited != d.visited) {
				System.out.println("wtf");
			}
		}
		
		
		boolean useHeuristic = true;
		if (useHeuristic) {
			Location result = null;
			int minDistance = 0;
	
			Iterator<Location> it = world.keySet().iterator();
			while (it.hasNext()) {
				Location key = (Location) it.next();
				Data data = world.get(key);
				if (world.get(key).visited)
					continue;
				if (result == null) {
					result = key;
					minDistance = (key.x-location.x)*(key.x-location.x)+(key.y-location.y)*(key.y-location.y); 
				} else {
					int distance = (key.x-location.x)*(key.x-location.x)+(key.y-location.y)*(key.y-location.y); 
					if (distance < minDistance) {
						minDistance = distance;
						result = key;
					}
				}
			}
			return result;
		}
		LinkedList<Location> queue = new LinkedList<Location>();		
		queue.add(location);
		while (true) {
			Location node = queue.removeFirst();
			Data data = world.get(node);
			if (!data.visited)
				return node;
			Location[] neighbours = getNeighbours(node);
			for (Location l : neighbours) {
				if (l != null)
					queue.add(l);
			}
		}

	}

	public void merge(HashMap<Location, Data> otherWorld) {
		Iterator<Location> it = otherWorld.keySet().iterator();
		while (it.hasNext()) {
			Location key = (Location) it.next();
			Data data = otherWorld.get(key);

			if (world.get(key) == null) {
				System.out.println("I Learned about new place in this world "
						+ key);
			}
			remember(key, data.minimumDistance);
			Data myData = world.get(key);
			int myCount = myData.foodCount;
			int hisCount = data.foodCount;
			if (hisCount < myCount) {
				// at (0,0) this is not reliable
				setFoodCount(key, data.foodCount);
			}
			if (data.visited)
				myData.visited = true;
		}

	}

}
