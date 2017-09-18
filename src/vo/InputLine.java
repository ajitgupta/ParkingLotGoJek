package vo;

import Exception.InvalidAction;
import Exception.InvalidInputLine;
import constant.ParkingAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AjitGupta on 17/09/17.
 */
public class InputLine {
    private ParkingAction parkingAction;
    private List<String> data;

    public InputLine(String input) throws InvalidInputLine,InvalidAction{
        String[] inputs=input.split("\\s+");
        if(inputs.length<1){
            throw new InvalidInputLine("invalid input");
        }
        this.parkingAction=ParkingAction.getFromAction(inputs[0]);
        data=new ArrayList<String>();
        for(Integer i=1;i<=inputs.length-1;i++){
            data.add(inputs[i]);
        }
    }

    public ParkingAction getParkingAction() {
        return parkingAction;
    }

    public void setParkingAction(ParkingAction parkingAction) {
        this.parkingAction = parkingAction;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
