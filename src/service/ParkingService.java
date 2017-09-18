package service;

import Exception.*;
import vo.InputLine;
import vo.Parking;

/**
 * Created by AjitGupta on 17/09/17.
 */
public interface ParkingService {
    public Parking createParkingLot(Parking parking, InputLine line) throws ParkingLotAlreadyExist, InvalidNumberOfVehicles, InvalidInputLine;
    public Parking park(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine, ParkingLotFull, CarAlreadyParked;
    public Parking leave(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine, InvalidSlotNo;
    public void parkingStatus(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine;
    public void getRegNoOfCarsByColor(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine;
    public void getSlotNoOfCarsByRegNo(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine;
    public void getSlotNosOfCarsByColor(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine;
}
