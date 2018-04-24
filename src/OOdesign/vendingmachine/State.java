package OOdesign.vendingmachine;

/**
 * Created by yingtan on 4/15/18.
 */
public interface State {

    public void insertCoin()throws MachineWarning;
    public void pressButton()throws MachineWarning;
    public void dispense()throws MachineWarning;
}
