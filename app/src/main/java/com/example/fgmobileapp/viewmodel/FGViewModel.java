package com.example.fgmobileapp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.fgmobileapp.model.FGModel;

public class FGViewModel extends BaseObservable {
    private FGModel model;
    //private FGPlayer player;

    public FGViewModel(){
        model = new FGModel();
        //player = new FGPlayer();
    }

    private String description;

    @Bindable
    public int getThrottle() {
        //transform 0 -> 1 to 0 -> 100
        float val = model.getThrottle();
        int progress = (int)(val * 100);
        return progress;
    }

    public void setThrottle(int progress) {
        //transform 0 -> 100 to 0 -> 1
        float throttle = progress / 100.0f;
        model.setThrottle(throttle);
        notifyPropertyChanged(com.example.fgmobileapp.BR.throttle);
        refreshDescription();
        model.FGCommand("set /controls/engines/current-engine/throttle ",throttle);
    }
    @Bindable
    public int getRudder() {
        //transform -1 -> 1 to 0 -> 100
        float val = model.getRudder();
        // (int)((val - (-1)) / (1 - (-1)));
        int progress = (int)((val + 1) / 2f * 100f);
        return progress;
    }

    public void setRudder(int progress) {
        float rudder = progress / 100.0f * 2f - 1f;
        model.setRudder(rudder);
        notifyPropertyChanged(com.example.fgmobileapp.BR.rudder);
        refreshDescription();
        model.FGCommand("set /controls/flight/rudder ",rudder);
    }
    @Bindable
    public float getAileron() {
        return model.getAileron();
    }

    public void setAileron(float aileron) {
       model.setAileron(aileron);
        notifyPropertyChanged(com.example.fgmobileapp.BR.aileron);
        refreshDescription();
        model.FGCommand("set /controls/flight/aileron ",aileron);
    }
    @Bindable
    public float getElevator() {
        return model.getElevator();
    }

    public void setElevator(float elevator) {
        model.setElevator(elevator);
        notifyPropertyChanged(com.example.fgmobileapp.BR.elevator);
        refreshDescription();
        model.FGCommand("set /controls/flight/elevator ",elevator);
    }

//    @Bindable
//    public String getIp() {
//        return model.getIp();
//    }
//
//    public void setIp(String ip) {
//       model.setIp(ip);
//        notifyPropertyChanged(com.example.fgmobileapp.BR.ip);
//    }
//
//    @Bindable
//    public String getPort() {
//        return model.getPort();
//    }
//
//    public void setPort(String port) {
//        model.setPort(port);
//        notifyPropertyChanged(com.example.fgmobileapp.BR.port);
//    }

//    public void onClickConnect()  {
//        // you have to implement the body of connect
//        try {
//            model.Connect(getIp(), getPort());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void onPositionChanged(float aileron, float elevator)
    {
        setAileron(aileron);
        setElevator(-1 * elevator);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(com.example.fgmobileapp.BR.description);
    }

    public void Connect(String ip, int port) throws Exception {
        model.Connect(ip, port);
    }

    public void refreshDescription()
    {
        setDescription("slider_X: " + String.format("%.2f", model.getThrottle())
                + " slider_Y: " + String.format("%.2f", model.getRudder())
                + "   dAileron: " + String.format("%.2f", model.getAileron())
                + "    dElevator:" + String.format("%.2f", model.getElevator())  + "");
    }
}
