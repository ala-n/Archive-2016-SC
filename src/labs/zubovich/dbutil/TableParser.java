package labs.zubovich.dbutil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TableParser {

	/**
	 * Take attention TreeMap so the keyset is sorted
	 * */
	public static TreeMap<Integer, List<Integer>> readTable(URL url) {
		TreeMap<Integer, List<Integer>> result = new TreeMap<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] nums = line.split("[\\s ]+");
				List<Integer> intList = new ArrayList<>();
				intList.add(Integer.parseInt(nums[1]));
				intList.add(Integer.parseInt(nums[2]));
				intList.add(Integer.parseInt(nums[3]));
				result.put(Integer.parseInt(nums[0]), intList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
