import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Spreadsheet {

	static String input = "C:/Users/mpermana/workspace/quantcast/input.txt";
	static boolean ascsv = false;
	static String rowseparator = "";
	static String colseparator = "\n";

	static int n; // column (width) 3 : 1..n
	static int m; // rows (height) 26 : A..Z

	static Cell[][] cells;

	// static List<Cell> topologicalSortL = new ArrayList<Cell>();
	static LinkedList<Cell> topologicalSortS = new LinkedList<Cell>();

	static HashMap<String, LinkedList<Cell>> dependancyMap = new HashMap<String, LinkedList<Cell>>(
			10000);

	static void testCode() throws CircularDependancyException {
		Cell testCell = new Cell(0,0);
		testCell.setInput("-100 1 *");
		System.out.println(testCell.evaluate());
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {
			if (args.length > 0)
				input = args[0];
			sc = new Scanner(new File(input));
			ascsv = true;
			rowseparator = "\n";
			colseparator = ",";
		} catch (Exception e) {

		}

		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();

		try {

			cells = new Cell[m][];
			for (int row = 0; row < m; row++) {
				cells[row] = new Cell[n];
				for (int col = 0; col < n; col++) {
					Cell cell = cells[row][col] = new Cell(row,col);
					String input = sc.nextLine().toUpperCase();
					cell.setInput(input);

					// just for fun setting the name;
					cell.edgeCount = cell.references.size();
					if (cell.edgeCount == 0) {
						topologicalSortS.add(cell);
					} else {
						ArrayList<String> references = cell.references;
						for (String string : references) {
							addDependancyMap(string, cell);
						}
					}
				}
			}

			// do topological sort
			int countEvaluated = 0;
			while (topologicalSortS.size() > 0) {
				Cell cell = topologicalSortS.removeFirst();
				cell.evaluate();
				countEvaluated++;
				// topologicalSortL.add(cell);

				// get every cell x that refer cell y
				LinkedList<Cell> list = dependancyMap.get(cell.name);
				if (null == list)
					continue;
				for (Cell cellx : list) {
					cellx.edgeCount--;
					if (cellx.edgeCount == 0) {
						topologicalSortS.add(cellx);
					}
				}
			}

			if (countEvaluated < n * m) {
				for (int row = 0; row < m; row++) {
					for (int col = 0; col < n; col++) {
						Cell cell = cells[row][col];
						if (!cell.evaluated) {
							String name = cell.toString();
							System.out.println(cell+" not evaluated");
						}
					}
				}
				throw new CircularDependancyException(
						"circular dependency detected: " + countEvaluated
								+ " cells evaluated");
			}

			System.out.println(n + " " + m);
			
			for (int row = 0; row < m; row++) {
				for (int col = 0; col < n; col++) {
					Cell cell = cells[row][col];
					double d = cell.evaluate();
					String s = String.format("%.5f", d);
					System.out.print(s);
					System.out.print(colseparator);
				}
				System.out.print(rowseparator);
			}

		} catch (CircularDependancyException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	private static void addDependancyMap(String string, Cell cell) {
		LinkedList<Cell> list = dependancyMap.get(string);
		if (null == list) {
			list = new LinkedList<Cell>();
			dependancyMap.put(string, list);
		}
		list.add(cell);
	}

}
