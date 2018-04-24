package lyft.design.lyftDriverDisplay;

/**
 * Created by yingtan on 4/22/18.
 */
import java.util.*;
public class DispatchService {
    Map<String, Set<Driver>> geohashToDrivers;
    public DispatchService() {
        geohashToDrivers = new HashMap<>();
    }
    public Set<Object[]> getSetOfNearbyCars(double longtitude, double latitude) {
        String userGeoHash = computeGeohash(longtitude, latitude);
        Set<Object[]> res = new HashSet<>();
        if (geohashToDrivers.containsKey(userGeoHash)) {
            // query redis driver table and get diff drivers
            Set<Driver> drivers = geohashToDrivers.get(userGeoHash);
            for (Driver d : drivers) {
                double[] dLoc = d.getLocation();
                double dist = (dLoc[0] - longtitude ) * (dLoc[0] - longtitude ) +
                        (dLoc[1] - latitude) * (dLoc[1] - latitude);
                double eta = System.currentTimeMillis() + Math.sqrt(dist) / (d.getCar().getCarType().milePerSecond);
                double price = Math.sqrt(dist) * (d.getCar().getCarType().pricePerMile);
                String type = d.getCar().getCarType().typeName;
                Object[] ob = new Object[]{eta, type, price};
                res.add(ob);
            }
        }
        return res;

    }
    private String computeGeohash(double lontig, double lati) {
        return "";
    }
}
