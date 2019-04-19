/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isolette_monitor;

import application.Constants;

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

    public OperatorInterface()
    {
        MinDesiredTemp = 97;
        MaxDesiredTemp = 100;
        
        MinAlarmTemp = 96;
        MaxAlarmTemp = 101;
        
        RegulatorState = Constants.StatusEnum.INIT;
        MonitorState = Constants.StatusEnum.INIT;
        AlarmState = Constants.AlarmStateEnum.OFF;
    }
}
