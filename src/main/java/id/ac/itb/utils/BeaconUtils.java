package id.ac.itb.utils;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

import id.ac.itb.model.Beacon;
import id.ac.itb.model.Position;

public class BeaconUtils {
	
	/**
	 * 
	 * @param beacon
	 * @return
	 * 
	 * Posisi Ibeacon didapatkan dari nilai minor Ibeacon tersebut.
	 * Maksimum minor yang didefinisikan adalah 64999.
	 * Posisi X iBeacon didapat dari 3 digit pertama angka minor.
	 * Posisi Y iBeacon didapat dari 2 digit terakhir angka minor.
	 * 
	 */
	private static double[][] getPositions(Beacon[] beacons){
		double[][] positions = new double[beacons.length][2];
		
		for (int i = 0; i<beacons.length; i++){
			int minor = beacons[i].getMinor();
			positions[i][0] = minor / 100; //Set nilai X
			positions[i][1] = minor % 100; //Set nilai Y
		}
		
		return positions;
	}
	
	private static double[] getDistances(Beacon[] beacons){
		double[] distances = new double[beacons.length];
		for (int i = 0; i<beacons.length; i++){
			distances[i] = beacons[i].getDistance();
		}
		return distances;
	}
	
	public static Position trilateration(Beacon[] beacons){
		
		Position position = new Position();
		
		try{
			double[][] positions = getPositions(beacons);
			double[] distances = getDistances(beacons);
			NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
			Optimum optimum = solver.solve();
			double[] centroid = optimum.getPoint().toArray();
			
			if (centroid.length == 2){
				position.setX(centroid[0]);
				position.setY(centroid[1]);
			}
		}
		catch(Exception e){
			position.setX(-1);
			position.setY(-1);
		}
		return position;
	}
	
}
