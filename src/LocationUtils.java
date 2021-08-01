import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LocationUtils {
	
	//public static final HashMap<Integer,String> statesWithLatLng =
 	//ArrayList<Double> o = (5.532,7.486);

	//double[] myList = {1.9, 2.9, 3.4, 3.5};
	public static final Map<String, ArrayList<Double>> statesWithLatLng;
	static {
		statesWithLatLng = new HashMap<>();
		statesWithLatLng.put("Abia", new ArrayList<Double>( Arrays.asList(5.532,7.486)));
		statesWithLatLng.put("Adamawa", new ArrayList<Double> (Arrays.asList(10.270, 13.270)));
		statesWithLatLng.put("Akwa Ibom", new ArrayList<Double> (Arrays.asList(5.007, 7.850)));
		statesWithLatLng.put("Anambra", new ArrayList<Double> ( Arrays.asList(6.210, 7.070)));
		statesWithLatLng.put("Bayelsa", new ArrayList<Double> ( Arrays.asList(6.26, 4.92)));
		statesWithLatLng.put("Bauchi",new ArrayList<Double> ( Arrays.asList(11.680, 10.190)));
		statesWithLatLng.put("Benue", new ArrayList<Double> (Arrays.asList(7.190, 8.130)));
		statesWithLatLng.put("Borno", new ArrayList<Double> ( Arrays.asList(10.620, 12.190)));
		statesWithLatLng.put("Cross River", new ArrayList<Double> ( Arrays.asList(4.960, 8.330)));
		statesWithLatLng.put("Delta", new ArrayList<Double> ( Arrays.asList(5.890, 5.680)));
		statesWithLatLng.put("Edo", new ArrayList<Double> ( Arrays.asList(6.340, 5.620)));
		statesWithLatLng.put("Ekiti", new ArrayList<Double> ( Arrays.asList(7.630, 5.219)));
		statesWithLatLng.put("Enugu", new ArrayList<Double> ( Arrays.asList(6.867, 7.383)));
		statesWithLatLng.put("FCT Abuja",new ArrayList<Double> ( Arrays.asList(9.083, 7.533)));
		statesWithLatLng.put("Gombe",new ArrayList<Double> ( Arrays.asList(10.290, 11.170)));
		statesWithLatLng.put("Imo",new ArrayList<Double> ( Arrays.asList(5.493, 7.026)));
		statesWithLatLng.put("Jigawa", new ArrayList<Double> ( Arrays.asList(11.800, 9.350)));
		statesWithLatLng.put("Kaduna",new ArrayList<Double> ( Arrays.asList(11.080, 7.710)));
		statesWithLatLng.put("Kano", new ArrayList<Double> ( Arrays.asList(12.000, 8.520)));
		statesWithLatLng.put("Katsina", new ArrayList<Double> ( Arrays.asList(11.520, 7.320)));
		statesWithLatLng.put("Kebbi", new ArrayList<Double> ( Arrays.asList(12.450, 4.200)));
		statesWithLatLng.put("Kogi", new ArrayList<Double> ( Arrays.asList(7.800, 6.740)));
		statesWithLatLng.put("Kwara", new ArrayList<Double> ( Arrays.asList(8.490, 4.550)));
		statesWithLatLng.put("Lagos", new ArrayList<Double> ( Arrays.asList(6.433, 3.391)));
		statesWithLatLng.put("Nassarawa", new ArrayList<Double> ( Arrays.asList(8.490, 8.520)));
		statesWithLatLng.put("Niger", new ArrayList<Double> ( Arrays.asList(10.400, 5.469)));
		statesWithLatLng.put("Ogun", new ArrayList<Double> ( Arrays.asList(7.160, 3.350)));
		statesWithLatLng.put("Ondo", new ArrayList<Double> ( Arrays.asList(7.250, 5.200)));
		statesWithLatLng.put("Osun", new ArrayList<Double> ( Arrays.asList(7.630, 4.180)));
		statesWithLatLng.put("Oyo", new ArrayList<Double> ( Arrays.asList(7.970, 3.590)));
		statesWithLatLng.put("Plateau", new ArrayList<Double> ( Arrays.asList(9.930, 8.890)));
		statesWithLatLng.put("Rivers", new ArrayList<Double> ( Arrays.asList(4.810, 7.010)));
		statesWithLatLng.put("Sokoto", new ArrayList<Double> ( Arrays.asList(13.060, 5.240)));
		statesWithLatLng.put("Taraba", new ArrayList<Double> ( Arrays.asList(7.870, 9.780)));
		statesWithLatLng.put("Yobe", new ArrayList<Double> ( Arrays.asList(11.749, 11.966)));
		
		statesWithLatLng.put("Zamfara", new ArrayList<Double> ( Arrays.asList(12.170, 6.659)));
	
	
	}
	
	public static double distanceBetweenStates(String origin, String destination) {
		
	ArrayList<Double> originLatLng=	statesWithLatLng.get(origin);
	ArrayList<Double> destinationLatLng=	statesWithLatLng.get(destination);
	return distance(originLatLng.get(0),originLatLng.get(1),destinationLatLng.get(0),destinationLatLng.get(1) , 'K');	
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515;
		  if (unit == 'K') {
		    dist = dist * 1.609344;
		  } else if (unit == 'N') {
		  dist = dist * 0.8684;
		    }
		  return (dist);
		}

		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::  This function converts decimal degrees to radians             :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private static double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);
		}

		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::  This function converts radians to decimal degrees             :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private static double rad2deg(double rad) {
		  return (rad * 180.0 / Math.PI);
		}
		

		
// driver code

}
