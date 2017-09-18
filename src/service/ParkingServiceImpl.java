package service;

import Exception.*;
import vo.Car;
import vo.InputLine;
import vo.Parking;

import java.util.Collections;
import java.util.Iterator;

/**
 * Created by AjitGupta on 17/09/17.
 */
public class ParkingServiceImpl implements ParkingService {


    public Parking createParkingLot(Parking parking, InputLine line) throws ParkingLotAlreadyExist, InvalidNumberOfVehicles, InvalidInputLine {
        if (parking != null) {
            throw new ParkingLotAlreadyExist("parking Lot Already Created");
        }
        if (line.getData().size() == 1){
            Integer noOfSlot=Integer.parseInt(line.getData().get(0));
            parking = Parking.createParking(noOfSlot);
            System.out.println("Created a parking lot with "+noOfSlot+" slots");
            return parking;
        }
        throw new InvalidInputLine("Invalid input");
    }


    public Parking park(Parking parking,InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine, ParkingLotFull, CarAlreadyParked {
        if(parking==null){
            throw new ParkingLotDoesNotExist("Parking Lot Does not Exist");
        }
        if(parking.getParkingLot().keySet().size()>=parking.getMaxNumberOfVechicles()){
            throw new ParkingLotFull("Sorry, parking lot is full");
        }
        if(line.getData().size()!=2){
            throw new InvalidInputLine("Invalid input");
        }

        String registrationNumber=line.getData().get(0);
        String color=line.getData().get(1);
        Car car=new Car(registrationNumber,color);
        if(parking.getParkedCarRegistrationNumberList().contains(registrationNumber)){
            throw new CarAlreadyParked("Car Already Parked");
        }
        Integer parkingSlot=getCloseestParkingSlot(parking);
        parking.getParkingLot().put(parkingSlot,car);
        parking.getVacantSlots().remove(parking.getVacantSlots().indexOf(parkingSlot));
        parking.getParkedCarRegistrationNumberList().add(registrationNumber);
        System.out.println("Allocated slot number: "+parkingSlot);
        return parking;

    }


    public Parking leave(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine, InvalidSlotNo {
        if(parking==null){
            throw new ParkingLotDoesNotExist("Parking Lot Does not Exist");
        }
        if(line.getData().size()!=1){
            throw new InvalidInputLine("Invalid input");
        }
        Integer slotNoToLeave=Integer.parseInt(line.getData().get(0));
        if(!parking.getParkingLot().containsKey(slotNoToLeave)){
            throw new InvalidSlotNo("Not found");
        }
        String parkedCarRegNo=parking.getParkingLot().get(slotNoToLeave).getRegistrationNumber();
        parking.getParkingLot().remove(slotNoToLeave);
        parking.getParkedCarRegistrationNumberList().remove(parking.getParkedCarRegistrationNumberList().indexOf(parkedCarRegNo));
        parking.getVacantSlots().add(slotNoToLeave);
        Collections.sort(parking.getVacantSlots());
        System.out.println("Slot number "+slotNoToLeave+" is free");
        return parking;
    }


    public void parkingStatus(Parking parking,InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine {
        if(parking==null){
            throw new ParkingLotDoesNotExist("Parking Lot Does not Exist");
        }
        if(line.getData().size()!=0){
            throw new InvalidInputLine("Invalid input");
        }
        Iterator<Integer> iterator=parking.getParkingLot().keySet().iterator();
        System.out.println("Slot No.	Registration No.	Colour");
        while (iterator.hasNext()) {
            Integer parkingSlot = iterator.next();
            Car parkedCar = parking.getParkingLot().get(parkingSlot);
            System.out.println(parkingSlot.toString() + "		" + parkedCar.getRegistrationNumber() + "		" + parkedCar.getColor());
        }
        System.out.println("\n");
    }


    public void getRegNoOfCarsByColor(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine {
        if(parking==null){
            throw new ParkingLotDoesNotExist("Parking Lot Does not Exist");
        }
        if(line.getData().size()!=1){
            throw new InvalidInputLine("Invalid input");
        }
        String color=line.getData().get(0);
        Iterator<Integer> iterator = parking.getParkingLot().keySet().iterator();
        StringBuilder output=new StringBuilder();output.append(" ");
        while (iterator.hasNext()) {
            Integer parkingSlot = iterator.next();
            Car parkedCar = parking.getParkingLot().get(parkingSlot);
            if (parkedCar.getColor().equals(color)) {
                output.append(parkedCar.getRegistrationNumber()+",");
            }
        }
        printStringFromSB(output);

    }

    private void printStringFromSB(StringBuilder output) {
        output.setLength(output.length()-1);
        if(output.toString().length()!=0){
            System.out.println(output.toString());
        }else {
            System.out.println("Not found");
        }
    }


    public void getSlotNoOfCarsByRegNo(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine {
        if(parking==null){
            throw new ParkingLotDoesNotExist("Parking Lot Does not Exist");
        }
        if(line.getData().size()!=1){
            throw new InvalidInputLine("Invalid input");
        }
        String regNo=line.getData().get(0);
        Iterator<Integer> iterator = parking.getParkingLot().keySet().iterator();
        boolean found=false;
        while (iterator.hasNext()) {
            Integer parkingSlot = iterator.next();
            Car parkedCar = parking.getParkingLot().get(parkingSlot);
            if (parkedCar.getRegistrationNumber().equals(regNo)) {
                System.out.println(parkingSlot);
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("Not found");
        }
    }


    public void getSlotNosOfCarsByColor(Parking parking, InputLine line) throws ParkingLotDoesNotExist, InvalidInputLine {
        if(parking==null){
            throw new ParkingLotDoesNotExist("Parking Lot Does not Exist");
        }
        if(line.getData().size()!=1){
            throw new InvalidInputLine("Invalid input");
        }
        String color=line.getData().get(0);
        Iterator<Integer> iterator = parking.getParkingLot().keySet().iterator();
        StringBuilder output=new StringBuilder();output.append(" ");
        while (iterator.hasNext()) {
            Integer parkingSlot = iterator.next();
            Car parkedCar = parking.getParkingLot().get(parkingSlot);
            if (parkedCar.getColor().equals(color)) {
                output.append(parkingSlot + ",");
            }
        }
        printStringFromSB(output);
    }

    private Integer getCloseestParkingSlot(Parking parking){
        return parking.getVacantSlots().get(0);
    }
}
