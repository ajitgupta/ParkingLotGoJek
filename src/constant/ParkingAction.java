package constant;

import Exception.InvalidAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AjitGupta on 17/09/17.
 */
public enum ParkingAction {
    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_NYMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
    SLOT_NUMBERS_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number"),
    DESTROY_PARKING_LOT("destroy_parking_lot"),
    EXIT_APP("exit_app");

    private String action;

    ParkingAction(String action) {
        this.action = action;
    }

    public static final Map<String,ParkingAction> parkingActionMap;
    static {
        Map<String,ParkingAction> actionMap=new HashMap<String, ParkingAction>();
        for (ParkingAction action:ParkingAction.values()){
            actionMap.put(action.getAction(),action);
        }
        parkingActionMap=actionMap;
    }

    public static ParkingAction getFromAction(String action) throws InvalidAction{
        if(parkingActionMap.containsKey(action)){
            return parkingActionMap.get(action);
        }
        throw new InvalidAction("Invalid Action To Perform");
    }
    public String getAction() {
        return action;
    }
}
