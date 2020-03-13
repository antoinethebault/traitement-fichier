package application;

import java.util.Comparator;
import java.util.Map.Entry;

public class AllergenesAdditifsComparator implements Comparator<Entry<String,Integer>> {

	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		int v1=o1.getValue();
		int v2=o2.getValue();
		if (v1<v2)
			return 1;
		else if (v1>v2)
			return -1;
		return 0;
	}

}
