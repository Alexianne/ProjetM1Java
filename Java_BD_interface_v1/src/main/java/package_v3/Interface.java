/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_v3;

/**
 *
 * @author Alexandra
 */
public class Interface {
    
    /* Attributes */
    private String intName;
    private String intercoDevName;
    private String ipAddr;
    private String intState;
    
    /* Constructor */
    public Interface(String intName, String intercoDevName, String ipAddr, String intState){
        this.intName = intName;
        this.intercoDevName = intercoDevName;
        this.ipAddr = ipAddr;
        this.intState = intState;
    }
    
    /* Methods */
    public String getIntName() {
        return intName;
    }
    public void setIntName(String intName) {
        this.intName = intName;
    }
    public String getIntercoDevName() {
        return intercoDevName;
    }
    public void setIntercoDevName(String intercoDevName) {
        this.intercoDevName = intercoDevName;
    }
    public String getIpAddr() {
        return ipAddr;
    }
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    public String getIntState() {
        return intState;
    }
    public void setIntState(String intState) {
        this.intState = intState;
    }
    
}
