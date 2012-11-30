import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import ants.Action;
import ants.Ant;
import ants.Direction;
import ants.Surroundings;
import ants.Tile;

public class MyAnt implements Ant {

	// ant remembers if it is carrying food
	boolean hasFood;	
	
	// ant remembers the path it has traveled from the mound, we reset the path once we go back to mound	
	LinkedList<Direction> pathTraveled = new LinkedList<Direction>();
	
	// ant remembers the locations in the world and communicate them to other ants
	Memory memory = new Memory();
	
	// action taken after thinking, we default to HALT
	Action action = Action.HALT;
	
	// current surroundings during thinking
	Surroundings surroundings;
	
	// current location of the ant, we specify mound as (0,0)
	Location location = new Location(0, 0);

	public Action getAction(Surroundings surroundings) {
		this.surroundings = surroundings;

		System.out.println("Ant: " + this.hashCode() % 100);
		System.out.println("Location: " + location);

		watchSurroundings();
		
		// main ant logic
		// if we have food, if we're home, drop it, otherwise go home
		// if we dont have food, go find food
		if (hasFood) {
			if (isHome()) {
				dropFood();
				pathTraveled = new LinkedList<Direction>();
			} else
				goHome();
		} else {
			findFood();
		}

		System.out.println("action: " + toString(action));
		System.out.println("Location': " + location);
		System.out.println();
		return action;
	}

	/**
	 * watch surroundings, remember it in memory and if has food, mark current location as visited
	 */
	private void watchSurroundings() {
		// remember this tile
		Tile tile = surroundings.getCurrentTile();
		memory.remember(location, pathTraveled.size());
		int minimumDistance = memory.world.get(location).minimumDistance;
		memory.setFoodCount(location, tile.getAmountOfFood());
		memory.markVisited(location);
		// remember the surroundings
		for (Direction d : Direction.values()) {
			Tile surroundingTile = surroundings.getTile(d);
			if (surroundingTile.isTravelable()) {
				Location to = location.to(d);
				memory.remember(to, minimumDistance + 1);
				memory.setFoodCount(to, surroundingTile.getAmountOfFood());
			}
		}
	}


	/**
	 * set action to DROP_OFF and set hasFood to false appropriately
	 */
	private void dropFood() {
		action = Action.DROP_OFF;
		hasFood = false;
	}

	private void findFood() {
		// check if there's food on currentTile
		Tile currentTile = surroundings.getCurrentTile();
		if (!isHome() && currentTile.getAmountOfFood() > 0) {
			action = Action.GATHER;
			hasFood = true;
			memory.setFoodCount(location, currentTile.getAmountOfFood() - 1);
			return;
		}

		// we dont have food on currentTile then
		Location destination = null;

		// move to food if we've seen it before in memory
		Location foodDestination = memory.findFood();
		if (null != foodDestination) {
			destination = foodDestination;
			System.out.println("going to food at " + destination);
		} else {
			// explore world, since we don't remember seeing any food, go to new locations 
			Location unvisitedDestination = memory.findUnvisited(location);
			if (foodDestination == null && null != unvisitedDestination) {
				destination = unvisitedDestination;
				System.out.println("going to unvisited at " + destination);
			}
		}

		// do bfs to find out how to reach destination
		if (null != destination) {
			LinkedList<Location> path = memory.searchPath(location, destination);
			Direction d = getDirection(location, path.get(1));
			pathTraveled.add(d);
			move(d);
		} else {
			// fark i have no purpose in life
		}

	}

	private boolean isHome() {
		return (location.x == 0) && (location.y == 0);
	}

	/**
	 * search path to go home from current location and move ant in that direction
	 */
	private void goHome() {
		LinkedList<Location> path = memory.searchPath(location, new Location(0,0));
		Direction d = getDirection(location, path.get(1));		
		move(d);
	}

	public byte[] send() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			ObjectOutputStream out = new ObjectOutputStream(os);
			out.writeObject(memory.world);
			out.close();
			os.close();
			return os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void receive(byte[] data) {
		ByteArrayInputStream is = new ByteArrayInputStream(data);
		try {
			ObjectInputStream in= new ObjectInputStream(is);
			Object o = in.readObject();
			if (o instanceof HashMap) {
				@SuppressWarnings("unchecked")
				HashMap<Location,Data> map = (HashMap<Location,Data>)o;
				memory.merge(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void move(Direction d) {
		if (d.equals(Direction.EAST))
			location.x++;
		if (d.equals(Direction.WEST))
			location.x--;
		if (d.equals(Direction.NORTH))
			location.y++;
		if (d.equals(Direction.SOUTH))
			location.y--;
		action = Action.move(d);
	}

	/**
	 * utility function, source and target must be neighbor
	 */
	private static Direction getDirection(Location source, Location target) {
		if (source.x < target.x)
			return Direction.EAST;
		if (source.x > target.x)
			return Direction.WEST;
		if (source.y < target.y)
			return Direction.NORTH;
		if (source.y > target.y)
			return Direction.SOUTH;
		return null;
	}

	/**
	 * utility function, for debugging
	 */
	static String toString(Action action) {
		if (Action.DROP_OFF.equals(action))
			return "DROP_OFF";
		if (Action.GATHER.equals(action))
			return "GATHER";
		if (Action.HALT.equals(action))
			return "HALT";
		return action.getDirection().toString();
	}
	
}