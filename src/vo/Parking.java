package vo;

import Exception.InvalidNumberOfVehicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AjitGupta on 17/09/17.
 */
public class Parking {
    private Map<Integer,Car> parkingLot;
    private Integer maxNumberOfVechicles;
    private List<Integer> vacantSlots;
    private List<String> parkedCarRegistrationNumberList;

    private static Parking parking=null;

    public static Parking createParking(Integer maxNumberOfVechicles) throws InvalidNumberOfVehicles{
        if(parking==null){
            parking=new Parking(maxNumberOfVechicles);
        }
        return parking;
    }

    private Parking(Integer maxNumberOfVechicles) throws InvalidNumberOfVehicles{
        validateMaxNumberOfVehicles(maxNumberOfVechicles);

        this.maxNumberOfVechicles=maxNumberOfVechicles;
        parkingLot=new HashMap<Integer,Car>();
        vacantSlots=new ArrayList<Integer>();
        parkedCarRegistrationNumberList=new ArrayList<String>();
        for (int i = 0; i < maxNumberOfVechicles; i++) {
            vacantSlots.add(i + 1);
        }
    }

    private void validateMaxNumberOfVehicles(Integer maxNumberOfVechicles) throws InvalidNumberOfVehicles {
        if(maxNumberOfVechicles<0){
            throw new InvalidNumberOfVehicles("Invalid");
        }
    }

    public Map<Integer, Car> getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(Map<Integer, Car> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Integer getMaxNumberOfVechicles() {
        return maxNumberOfVechicles;
    }

    public void setMaxNumberOfVechicles(Integer maxNumberOfVechicles) {
        this.maxNumberOfVechicles = maxNumberOfVechicles;
    }

    public List<Integer> getVacantSlots() {
        return vacantSlots;
    }

    public void setVacantSlots(List<Integer> vacantSlots) {
        this.vacantSlots = vacantSlots;
    }

    public List<String> getParkedCarRegistrationNumberList() {
        return parkedCarRegistrationNumberList;
    }

    public void setParkedCarRegistrationNumberList(List<String> parkedCarRegistrationNumberList) {
        this.parkedCarRegistrationNumberList = parkedCarRegistrationNumberList;
    }
}
