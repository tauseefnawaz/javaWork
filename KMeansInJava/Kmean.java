import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kmean {
	static ArrayList<ArrayList<Double>> X  = new ArrayList<ArrayList<Double> >(); 
	static ArrayList<String> y = new ArrayList<String>();
	
	public static void readfile(String path) {
		//read the data file and 
		//stored first four column in X and the last column for y(target variable) 
		
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] str = data.split(",");
				ArrayList<Double> row = new ArrayList<Double>();
				row.add(Double.parseDouble(str[0]));
				row.add(Double.parseDouble(str[1]));
				row.add(Double.parseDouble(str[2]));
				row.add(Double.parseDouble(str[3]));
				X.add(row);
				y.add(str[4]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	Kmean(){}
	
	public Double Euclidian_Distance(ArrayList<Double> Observe,ArrayList<Double> Actaul) {
		Double distance = 0.0;
		for(int i =0;i<Observe.size();i++) {
			distance += ((Observe.get(i)-Actaul.get(i))*(Observe.get(i)-Actaul.get(i)));
		}
		return Math.sqrt(distance);
	}
	public ArrayList<Integer> find_closest_centroids(ArrayList<ArrayList<Double>> centroids) {
		//Compute the closest Centroid by taking Euclidian_Distance 
		//for each data Point in the X
		//and store that centroid index which has less distance value.
		
		ArrayList<Integer> idx = new ArrayList<Integer>();
		Integer best_idx = 0;

		for(int i =0;i<X.size();i++) {
			ArrayList<Double> distances = new ArrayList<Double>();
			for(int j=0;j<centroids.size();j++) {
				distances.add(Euclidian_Distance(X.get(i),centroids.get(j)));
			}
			Double best_distance = 100.0;			
			for(int j=0;j<centroids.size();j++) {
				if(distances.get(j)<best_distance) {
					best_idx = j;
					best_distance = distances.get(j);
				}
			}
			idx.add(best_idx);
		}
		return idx;
	}
	public ArrayList<ArrayList<Double>> initialize_centriod(){
		
		ArrayList<ArrayList<Double>> initial_centriod = new ArrayList<ArrayList<Double>>();
		Random rand = new Random();
		int low = 0;
		int high = 49;
		int result = rand.nextInt(high-low) + low;
		initial_centriod.add(X.get(result));
		low = 50;
		high = 99;
		result = rand.nextInt(high-low) + low;
		initial_centriod.add(X.get(result));
		low = 100;
		high = 149;
		result = rand.nextInt(high-low) + low;
		initial_centriod.add(X.get(result));
		return initial_centriod;
	}
	public ArrayList<Double> computeMean(ArrayList<ArrayList<Double>> centroid) {
		//Calculate Row wise mean for each cluster, for updating cluster value
		Double Col1 = 0.0;
		Double Col2 = 0.0;
		Double Col3 = 0.0;
		Double Col4 = 0.0;
		
		for(int i =0;i<centroid.size();i++) {
			Col1 += centroid.get(i).get(0);
			Col2 += centroid.get(i).get(1);
			Col3 += centroid.get(i).get(2);
			Col4 += centroid.get(i).get(3);
		}
		
		ArrayList<Double> returnCentroid = new ArrayList<Double>();
		returnCentroid.add((Col1/centroid.size()));
		returnCentroid.add((Col2/centroid.size()));
		returnCentroid.add((Col3/centroid.size()));
		returnCentroid.add((Col4/centroid.size()));
		return returnCentroid;
	}
	public ArrayList<ArrayList<Double>>  compute_centroids(ArrayList<Integer> idx) {
		//seperate data points of X for each cluster
		//then calculate means of datapoints in every cluster to make new Cluster.
		
		ArrayList<ArrayList<Double>> centroid1  = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> centroid2  = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> centroid3  = new ArrayList<ArrayList<Double>>();
		for(int i =0;i<X.size();i++) {
			if(idx.get(i)==0) {
				centroid1.add(X.get(i));
			}else if(idx.get(i)==1) {
				centroid2.add(X.get(i));
			}else if(idx.get(i)==2) {
				centroid3.add(X.get(i));
			}
		}
		ArrayList<ArrayList<Double>> new_centriod = new ArrayList<ArrayList<Double>>();
		new_centriod.add(computeMean(centroid1));
		new_centriod.add(computeMean(centroid2));
		new_centriod.add(computeMean(centroid3));
		return new_centriod;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readfile("iris.txt");
		Kmean KmeanObj =new Kmean();
		ArrayList<ArrayList<Double>> initial_centriod = KmeanObj.initialize_centriod();
		
		ArrayList<Integer> idx = null;
		ArrayList<ArrayList<Double>> new_centriod=initial_centriod;
		
		int max_iteration = 100;
		for(int i=0;i<max_iteration;i++) {
			idx = KmeanObj.find_closest_centroids(new_centriod);
			new_centriod = KmeanObj.compute_centroids(idx);
		}
		System.out.print("Clustering Output After 100 iteration\n");
		System.out.print("0 for Cluster1\n1 for Cluster2\n2 for Cluster3\n");
		System.out.print(idx);
	}
}
