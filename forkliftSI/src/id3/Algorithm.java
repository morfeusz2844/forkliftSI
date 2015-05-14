package id3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Algorithm {
	private String[] allAttributes; 
	private int indexTargetAttribute = -1; 
	private Set<String> targetAttributeValues = new HashSet<String>(); 
	
	private long startTime; 
	private long endTime;   

	public Tree runAlgorithm(String input, String targetAttribute,
			String separator) throws IOException {
		startTime = System.currentTimeMillis();
		
		Tree tree = new Tree();

		BufferedReader reader = new BufferedReader(new FileReader(input));
		String line = reader.readLine();

		allAttributes = line.split(separator);
		
		// make an array to store the attributes except the target attribute
		int[] remainingAttributes = new int[allAttributes.length - 1];
		int pos = 0;
		
		for (int i = 0; i < allAttributes.length; i++) {
			if (allAttributes[i].equals(targetAttribute)) {
				indexTargetAttribute = i;
			} else {
				remainingAttributes[pos++] = i;
			}
		}

		List<String[]> instances = new ArrayList<String[]>();
		while (((line = reader.readLine()) != null)) { 
			if (line.isEmpty() == true ||
					line.charAt(0) == '#' || line.charAt(0) == '%'
							|| line.charAt(0) == '@') {
				continue;
			}
			
			String[] lineSplit = line.split(separator);
			instances.add(lineSplit);
			targetAttributeValues.add(lineSplit[indexTargetAttribute]);
		}
		reader.close();
		
		
		// start of recursion
		
		tree.root = id3(remainingAttributes, instances);
		tree.allAttributes = allAttributes;
		
		endTime = System.currentTimeMillis();  // record end time
		
		return tree; // return the tree
	}

	//creating of subtree according to input attributes
	private Node id3(int[] remainingAttributes, List<String[]> instances) {
		// if only one remaining attribute,
		// return a class node with the most common value in the instances
		if (remainingAttributes.length == 0) {
			Map<String, Integer> targetValuesFrequency = calculateFrequencyOfAttributeValues(
					instances, indexTargetAttribute);
			
			int highestCount = 0;
			String highestName = "";
			for (Entry<String, Integer> entry : targetValuesFrequency
					.entrySet()) {
				if (entry.getValue() > highestCount) {
					highestCount = entry.getValue();
					highestName = entry.getKey();
				}
			}

			ClassNode classNode = new ClassNode();
			classNode.className = highestName;
			return classNode;
		}

		Map<String, Integer> targetValuesFrequency = calculateFrequencyOfAttributeValues(
				instances, indexTargetAttribute);

		// if all instances are from the same class
		if (targetValuesFrequency.entrySet().size() == 1) {
			ClassNode classNode = new ClassNode();
			classNode.className = (String) targetValuesFrequency.keySet()
					.toArray()[0];
			return classNode;
		}

		double globalEntropy = 0d;
		for (String value : targetAttributeValues) {
			Integer frequencyInt = targetValuesFrequency.get(value);
			if(frequencyInt != null) {

				double frequencyDouble = frequencyInt / (double) instances.size();

				globalEntropy -= frequencyDouble * Math.log(frequencyDouble) / Math.log(2);
			}
		}
		System.out.println("Global entropy = " + globalEntropy);

		int attributeWithHighestGain = 0;
		double highestGain = -99999;
		for (int attribute : remainingAttributes) {
			double gain = calculateGain(attribute, instances, globalEntropy);
			System.out.println("Process " + allAttributes[attribute] +
			 " gain = " + gain);
			if (gain >= highestGain) {
				highestGain = gain;
				attributeWithHighestGain = attribute;
			}
		}
		
			if (highestGain == 0) {
			ClassNode classNode = new ClassNode();
			// take the most frequent classes
			int topFrequency = 0;
			String className = null;
			for(Entry<String, Integer> entry: targetValuesFrequency.entrySet()) {
				if(entry.getValue() > topFrequency) {
					topFrequency = entry.getValue();
					className = entry.getKey();
				}
			}
			classNode.className = className;
			return classNode;
		}

		// Create a decision node for the attribute
		 System.out.println("Attribute with highest gain = " +
		 allAttributes[attributeWithHighestGain] + " " + highestGain);
		DecisionNode decisionNode = new DecisionNode();
		decisionNode.attribute = attributeWithHighestGain;

		int[] newRemainingAttribute = new int[remainingAttributes.length - 1];
		int pos = 0;
		for (int i = 0; i < remainingAttributes.length; i++) {
			if (remainingAttributes[i] != attributeWithHighestGain) {
				newRemainingAttribute[pos++] = remainingAttributes[i];
			}
		}

		Map<String, List<String[]>> partitions = new HashMap<String, List<String[]>>();
		for (String[] instance : instances) {
			String value = instance[attributeWithHighestGain];
			List<String[]> listInstances = partitions.get(value);
			if (listInstances == null) {
				listInstances = new ArrayList<String[]>();
				partitions.put(value, listInstances);
			}
			listInstances.add(instance);
		}

		// Create the values for the subnodes
		decisionNode.nodes = new Node[partitions.size()];
		decisionNode.attributeValues = new String[partitions.size()];

		// For each partition, make a recursive call to create
		// the corresponding branches in the tree.
		int index = 0;
		for (Entry<String, List<String[]>> partition : partitions.entrySet()) {
			decisionNode.attributeValues[index] = partition.getKey();
			decisionNode.nodes[index] = id3(newRemainingAttribute,
					partition.getValue()); // recursive call
			index++;
		}
		
		// return the root node of the subtree created
		return decisionNode;
	}

	private double calculateGain(int attributePos, List<String[]> instances,
			double globalEntropy) {
		Map<String, Integer> valuesFrequency = calculateFrequencyOfAttributeValues(
				instances, attributePos);

		double sum = 0;
		for (Entry<String, Integer> entry : valuesFrequency.entrySet()) {
			sum += entry.getValue()
					/ ((double) instances.size())
					* calculateEntropyIfValue(instances, attributePos,
							entry.getKey());
		}
		return globalEntropy - sum;
	}

	private double calculateEntropyIfValue(List<String[]> instances,
			int attributeIF, String valueIF) {
		
		int instancesCount = 0;
		Map<String, Integer> valuesFrequency = new HashMap<String, Integer>();
		
		for (String[] instance : instances) {
			// if that instance has the value for the attribute
			if (instance[attributeIF].equals(valueIF)) {
				String targetValue = instance[indexTargetAttribute];
				if (valuesFrequency.get(targetValue) == null) {
					valuesFrequency.put(targetValue, 1);
				} else {
					valuesFrequency.put(targetValue,
							valuesFrequency.get(targetValue) + 1);
				}
				instancesCount++; 
			}
		}
		double entropy = 0;
		for (String value : targetAttributeValues) {
			// get the frequency
			Integer count = valuesFrequency.get(value);
			// if the frequency is not null
			if (count != null) {
				// update entropy according to the formula
				double frequency = count / (double) instancesCount;
				entropy -= frequency * Math.log(frequency) / Math.log(2);
			}
		}
		return entropy;
	}

	
	private Map<String, Integer> calculateFrequencyOfAttributeValues(
			List<String[]> instances, int indexAttribute) {
		// K: a string indicating a value
		// V: the frequency
		Map<String, Integer> targetValuesFrequency = new HashMap<String, Integer>();
		for (String[] instance : instances) {
			String targetValue = instance[indexAttribute];
			if (targetValuesFrequency.get(targetValue) == null) {
				targetValuesFrequency.put(targetValue, 1);
			} else {
				targetValuesFrequency.put(targetValue,
						targetValuesFrequency.get(targetValue) + 1);
			}
		}
		return targetValuesFrequency;
	}

	public void printStatistics() {
		System.out.println("Time to construct decision tree = "
				+ (endTime - startTime) + " ms");
		System.out.println("Target attribute = "
				+ allAttributes[indexTargetAttribute]);
		System.out.print("Other attributes = ");
		for (String attribute : allAttributes) {
			if (!attribute.equals(allAttributes[indexTargetAttribute])) {
				System.out.print(attribute + " ");
			}
		}
		System.out.println();
	}
}
