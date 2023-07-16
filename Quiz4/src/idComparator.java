import java.util.Comparator;

public class idComparator implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		String[] o1List = o1.split("\t");
		String[] o2List = o2.split("\t");
		return Integer.parseInt(o1List[0]) - Integer.parseInt(o2List[0]);
	}
}
