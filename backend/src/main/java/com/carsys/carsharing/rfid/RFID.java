package com.carsys.carsharing.rfid;

public class RFID {

    private int dauer;
    private String rfid;
    private boolean isActive;

    public RFID(int dauer, String rfid, boolean isActive) {
        this.dauer = dauer;
        this.rfid = rfid;
        this.isActive = isActive;
    }
}
