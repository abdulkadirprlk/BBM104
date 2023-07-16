//Abdulkadir Parlak 2210765025

import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<String> linesAsArrayList = new ArrayList<>();
	public static HashSet<String> linesAsHashSet = new HashSet<>();
	public static TreeSet<String> linesAsTreeSet = new TreeSet<>();
	public static HashMap<Integer, String> linesAsHashMap = new HashMap<>();

	public static void main(String[] args) {
		String inputFile = args[0];
		readAndStoreInput(inputFile);
		arrayListWrite(linesAsArrayList);
		arrayListSortAndWrite(linesAsArrayList);
		hashSetWrite(linesAsHashSet);
		treeSetWrite(linesAsTreeSet);
		treeSetSortAndWrite(linesAsTreeSet);
		hashMapWrite(linesAsHashMap);
	}

	public static void readAndStoreInput(String inputFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] lineArray = line.split("\t");
				linesAsArrayList.add(line);
				linesAsHashSet.add(line);
				linesAsTreeSet.add(line);
				linesAsHashMap.put(Integer.parseInt(lineArray[0]), lineArray[1]);
			}
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void arrayListWrite(ArrayList<String> arrayList) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("poemArrayList.txt"));
			for (String line : arrayList) {
				writer.write(line + "\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void arrayListSortAndWrite(ArrayList<String> arrayList) {
		Collections.sort(linesAsArrayList, new idComparator());
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("poemArrayListOrderByID.txt"));
			for (String line : arrayList) {
				writer.write(line + "\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void hashSetWrite(HashSet<String> hashSet) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("poemHashSet.txt"));
			for (String line : hashSet) {
				writer.write(line + "\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void treeSetWrite(TreeSet<String> treeSet) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("poemTreeSet.txt"));
			for (String line : treeSet) {
				writer.write(line + "\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void treeSetSortAndWrite(TreeSet<String> treeSet) {
		TreeSet<String> sortedTreeSet = new TreeSet<>(new idComparator());
		sortedTreeSet.addAll(treeSet);//adds all elements and sort by IDs automatically.
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("poemTreeSetOrderByID.txt"));
			for (String line : sortedTreeSet) {
				writer.write(line + "\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void hashMapWrite(HashMap<Integer, String> hashMap){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(" poemHashMap.txt "));
			for(int id: hashMap.keySet()){
				writer.write(id + "\t" + hashMap.get(id) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
