package project.inventoryapp.controllers;

/**This is the InHouse class. This class extends the part class. */
public class InHouse extends Part {
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        setMachineId(machineId);
    }

    /** This method gets the machineId value.
     @return machineId. */
    public int getMachineId() {
        return machineId;
    }

    /** This method sets the machineId value.
     @param machineId the machineId to set. */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
