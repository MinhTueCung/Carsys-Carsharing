package com.carsys.carsharing.rfid;

import com.carsys.carsharing.buisnessLayer.BookingsApiImpl;
import com.google.gson.Gson;
import java.util.Date;
import java.util.UUID;


public class RFIDClient extends Thread
{
    private UUID bookingId;
    private Date startDate;
    private Date endDate;
    private String chipNr;
    private BookingsApiImpl bookingsApi;

    public RFIDClient(BookingsApiImpl bookingsApi, UUID bookingId, Date startDate, Date endDate, String chipNr)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.chipNr = chipNr;
        this.bookingId = bookingId;
        this.bookingsApi = bookingsApi;
    }

    public void run()
    {
        Date now = new Date();

        long timeToWait = (startDate.getTime()-now.getTime());
        long rentTimeInMinutes = (endDate.getTime()-startDate.getTime())/(1000*60);

        if(timeToWait <= 0) {
            timeToWait = 0;
        }

        System.out.println("timeToWait: " + timeToWait/1000 + " s");
        System.out.println("rentTimeInMinutes: " + rentTimeInMinutes+ " m");

        try { Thread.sleep(timeToWait); }
        catch (InterruptedException e) { }

        //AUSLEIHSTATUS AUF RENTED SETZEN
        bookingsApi.setBookingStatusDueToRFID(bookingId);

        java.io.PrintWriter     out = null;
        java.io.BufferedReader  in = null;
        java.net.Socket socket = null;

        try
        {
            socket = new java.net.Socket("192.168.2.112",5001);
        }
        catch (Exception e) {
        }
        try {
            in = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            out = new java.io.PrintWriter(socket.getOutputStream(),true);

            RFID myRfid = new RFID((int)rentTimeInMinutes, chipNr, true);
            Gson gson = new Gson();
            String jsonOutString = gson.toJson(myRfid);
            out.println(jsonOutString);
            String feedback = "";
            while (true)
            {
                feedback = in.readLine();

                //Rueckgabe durch RFID
                if(feedback.equals("accept"))
                {
                    bookingsApi.earlyReturnOfCarDueToRFID(bookingId);
                    out.println(feedback);

                    break;
                }
                else
                {
                    out.println("FEHLER");
                }
                sleep(20000);
                out.println(jsonOutString);
            }
            out.close();
            in.close();
            socket.close();
        }
        catch (Exception e)
        {
            ;
        }
    }
}

