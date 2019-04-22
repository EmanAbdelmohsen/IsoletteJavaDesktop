/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *class implements the Operator Interface requirements EA-OI
 */
public class OperatorInterface {
    
    int MinDesiredTemp;
    int MaxDesiredTemp;
    
    int MinAlarmTemp;
    int MaxAlarmTemp;
    
    String RegulatorState;    
    String MonitorState;    
    String AlarmState; 
    
    String HeatControl;
    
    int DisplayedTemperature;
    double currentTemp;

    Thermostat Thermostat;
    public OperatorInterface()
    {
        MinDesiredTemp = 97;
        MaxDesiredTemp = 100;
        
        MinAlarmTemp = 96;
        MaxAlarmTemp = 101;
        
        DisplayedTemperature = 77;    
        currentTemp = 77;   //assuming room temperature (25 degree Celsius)
        
        //thermostat initialization
        Thermostat = new Thermostat(MinDesiredTemp, MaxDesiredTemp, MinAlarmTemp, MaxAlarmTemp);        
        
        AlarmState = SetAlarmState(Thermostat.getAlarmControl());
        MonitorState = Thermostat.getMonitorStatus();
        RegulatorState = Thermostat.getRegulatorStatus();
        
        HeatControl = SetHeatControl(Thermostat.getHeatControl());
    }
    
    public void OperateThermostat()
    {
        Thermostat.executeRound(currentTemp);
        //if(currentTemp > (double)MaxDesiredTemp)DecrementCurrentTemp();
        /*if(currentTemp <= (double)MinDesiredTemp)*/ IncrementCurrentTemp();
        
        AlarmState = SetAlarmState(Thermostat.getAlarmControl());
        MonitorState = Thermostat.getMonitorStatus();
        RegulatorState = Thermostat.getRegulatorStatus();      
        HeatControl = SetHeatControl(Thermostat.getHeatControl());
        
        System.out.println("current temperature reading from sensor:" + currentTemp);
        //System.out.println("current temperature reading from sensor:" + Thermostat.getDisplayTemperature());

    }
    
    public String execute_round(int minTemp, int maxTemp, int minAlarmTemp, int maxAlarmTemp)
    {
        String errorMsg = "";
        
        //based on the environmental requirements (EA-OI-3 to EA-OI-)
        Boolean EaOi3 = minAlarmTemp >= 93;
        Boolean EaOi4 = minAlarmTemp <= minTemp - 1;
        Boolean EaOi5 = minTemp >= 97;
        Boolean EaOi6 = minTemp <= maxTemp - 1;
        Boolean EaOi7 = maxTemp <= 100;
        Boolean EaOi8 = maxAlarmTemp >= maxTemp + 1;
        Boolean EaOi9 = maxAlarmTemp <= 103;
        
        if(EaOi3 && EaOi4 && EaOi5 && EaOi6 && EaOi7 && EaOi8 && EaOi9)
        {
            MinDesiredTemp = minTemp;
            MaxDesiredTemp = maxTemp;
            MinAlarmTemp = minAlarmTemp;
            MaxAlarmTemp = maxAlarmTemp;
            
            OperateThermostat();
        }
        else if(!EaOi3 || !EaOi9) errorMsg = "Alarm temperature values are out of valid range [93-103].";
        else if(!EaOi5 || !EaOi7) errorMsg = "Desired temperature values are out of valid range [97-100].";
        else if(!EaOi4 || !EaOi8) errorMsg = "Alarm temperature values do not fit the desired temperature range.";   
        else if(!EaOi6) errorMsg = "Min desired temperature is beyond the max desired temperature value";
        
        return errorMsg;
    }
    
    //Getters
    public int GetMinDesiredTemp()
    {
        return MinDesiredTemp;
    }
    
    public int GetMaxDesiredTemp()
    {
        return MaxDesiredTemp;
    }
    
    public int GetMinAlarmTemp()
    {
        return MinAlarmTemp;
    }
    
    public int GetMaxAlarmTemp()
    {
        return MaxAlarmTemp;
    }
    
    public String GetMonitorState()
    {
        return MonitorState;
    }
    
    public String GetRegulatorState()
    {
        return  RegulatorState;
    }
    
    public String GetAlarmState()
    {
        return  AlarmState;
    }
    
    public String GetHeatControl()
    {
        return  HeatControl;
    }
    
    public String GetDisplayedTemp()
    {
        int temp = (int)Thermostat.getDisplayTemperature();
        return temp == 0? "Unspecified" : String.valueOf(temp);
    }
    
    public double GetCurrentTemp()
    {
        return currentTemp;
    }
    //end of Getters
    
    //Setters
    private String SetAlarmState(Boolean alarmStatus)
    {
        return alarmStatus? "On" : "Off";
    }
    
    private String SetHeatControl(Boolean heatCtrl)
    {
        return heatCtrl? "On" : "Off";
    }
    
    private void IncrementCurrentTemp()
    {
        currentTemp += (((double) 1)/(double)600);   //temperature increases at a rate of 1F/min - EA-IS-1
    }
    
    private void DecrementCurrentTemp()
    {
        currentTemp -= (((double) 1)/(double)600);
    }
    //end of Setters
}
