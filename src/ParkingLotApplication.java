import service.ParkingService;
import service.ParkingServiceImpl;
import vo.InputLine;
import vo.Parking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by AjitGupta on 17/09/17.
 */
public class ParkingLotApplication {
    private static final ParkingService parkingService=new ParkingServiceImpl();

    public static void main(String[] args) {
        Parking parking=null;
        String inputString;
        BufferedReader bufferedReader = null;
        boolean exit_app = false;
        try {
            if (args.length > 0) {
                bufferedReader = new BufferedReader(new FileReader(args[0]));
            }
            while (!exit_app){
                if (args.length == 0) {
                    System.out.println("Enter Command ");
                    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                }
                inputString = bufferedReader.readLine().trim();
                InputLine line= null;
                try {
                    line = new InputLine(inputString);
                } catch (Exception e){
                    printException(e);
                    continue;
                }
                switch (line.getParkingAction()){
                    case CREATE_PARKING_LOT:
                        try {
                            parking=parkingService.createParkingLot(parking,line);
                        } catch (Exception e) {
                            printException(e);
                        }
                        break;
                    case PARK:
                        try {
                            parking=parkingService.park(parking,line);
                        } catch (Exception e){
                            printException(e);
                        }
                        break;
                    case LEAVE:
                        try {
                            parking=parkingService.leave(parking,line);
                        } catch (Exception e){
                            printException(e);
                        }
                        break;
                    case STATUS:
                        try {
                            parkingService.parkingStatus(parking,line);
                        } catch (Exception e) {
                           printException(e);
                        }
                        break;
                    case REGISTRATION_NYMBERS_FOR_CARS_WITH_COLOUR:
                        try {
                            parkingService.getRegNoOfCarsByColor(parking,line);
                        } catch (Exception e) {
                            printException(e);
                        }
                        break;
                    case SLOT_NUMBERS_FOR_REGISTRATION_NUMBER:
                        try {
                            parkingService.getSlotNoOfCarsByRegNo(parking,line);
                        } catch (Exception e) {
                            printException(e);
                        }
                        break;
                    case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                        try {
                            parkingService.getSlotNosOfCarsByColor(parking,line);
                        } catch (Exception e) {
                            printException(e);
                        }
                        break;
                    case EXIT_APP:
                        exit_app=true;
                        break;
                }
            }
        }catch (Exception e){
            printException(e);
        }
    }

    private static void printException(Exception e) {
        System.out.println(e.getMessage());
    }
}
