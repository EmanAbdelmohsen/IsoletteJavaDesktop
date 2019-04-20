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
    
    //int DisplayTemp;
    
    int MinAlarmTemp;
    int MaxAlarmTemp;
    
    Constants.StatusEnum RegulatorState;    
    Constants.StatusEnum MonitorState;    
    Constants.AlarmStateEnum AlarmState; 
    
    int CurrentTemperature;

    Thermostat Thermostat;
    public OperatorInterface()
    {
        MinDesiredTemp = 97;
        MaxDesiredTemp = 100;
        
        MinAlarmTemp = 96;
        MaxAlarmTemp = 101;
        
        CurrentTemperature = 77;    //assuming room temperature (25 degree Celsius)
        
        //thermostat initialization
        Thermostat = new Thermostat(MinDesiredTemp, MaxDesiredTemp, MinAlarmTemp, MaxAlarmTemp);        
        AlarmState = Constants.AlarmStateEnum.OFF;
    }
    
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
        return  Thermostat.getMonitorStatus();
    }
    
    public String GetRegulatorState()
    {
        return  Thermostat.getRegulatorStatus();
    }
    
    public String GetAlarmState()
    {
        return  AlarmState.toString();
    }
    
    public int GetCurrentTemp()
    {
        return  CurrentTemperature;
    }
}
