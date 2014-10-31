/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usbcopynesj.system;

import java.util.logging.Level;
import java.util.logging.Logger;
import jd2xx.JD2XX;

/**
 *
 * @author Shawn M. Crawford
 */
public class Power {
    
    //static  BYTE    shadow = 0x24;
    private static byte SHADOW = 0x24;


    public Power() {
        
    }

//EVENT_RXCHAR = 1<<0,
//EVENT_MODEM_STATUS = 1<<1,

    public void resetNES() {

        JD2XX control = new JD2XX();
        JD2XX data = new JD2XX();
//        
        try {
////            DEBUG: Serial Number: RER9QXCJA
////            DEBUG: Serial Number: RER9QXCJB
////            DEBUG: Description: USB CopyNES A
////            DEBUG: Description: USB CopyNES B
////            DEBUG: Location: 0x3051 (12369)
////            DEBUG: Location: 0x3052 (12370)
////            JD2XX control = new JD2XX("USB CopyNES A", 0);
////            Object[] devicesByLocation = control.listDevicesByLocation();
////
////            System.out.println("Device location: " + devicesByLocation[0]);
////            System.out.println("Device location: " + devicesByLocation[1]);
////            
////            Integer location1 = (Integer)devicesByLocation[0];
////            Integer location2 = (Integer)devicesByLocation[1];
////            
////            System.out.println("Device location: " + location1);
////            System.out.println("Device location: " + location2);
////            
////            Integer.toHexString((Integer)devicesByLocation[0]);
//
////            control.open(0);
//            
//            //jd.resetDevice();
//            
////            System.out.println(control.listDevices(0));
////            System.out.println(control.toString());
//		Object[] devices = control.listDevices(0);
//		for (int i=0; i<devices.length; ++i) System.out.println("Device " + i + ":" + devices[i]);
//		Object[] devs = control.listDevicesBySerialNumber();
//		for (int i=0; i<devs.length; ++i) System.out.println("Device Serial Number " + i + ": " + devs[i]);
//		devs = control.listDevicesByDescription();
//		for (int i=0; i<devs.length; ++i) System.out.println("Device Description " + i + ": " +devs[i]);
//		devs = control.listDevicesByLocation();
//		for (int i=0; i<devs.length; ++i) System.out.println("Device Location " + i + ": Int: " + devs[i] + " Hex: " + Integer.toHexString((Integer)devs[i]));
//
//		int n = control.createDeviceInfoList();
//                System.out.println("Device Info List: " + n);
//		DeviceInfo diControl = control.getDeviceInfoDetail(0);
//		System.out.println("Control Device Info Detail: " + diControl.toString());
//                DeviceInfo diData = control.getDeviceInfoDetail(0);
//		System.out.println("Data Device Info Detail: " + diData.toString());
//
                // 0 is control port, 1 is data port
                control.open(0);
                control.setBaudRate(JD2XX.BAUD_921600);
                control.setDataCharacteristics(8, JD2XX.STOP_BITS_1, JD2XX.PARITY_NONE);
                control.setFlowControl(JD2XX.FLOW_NONE, 0, 0);
                control.setTimeouts(1000, 1000);

                data.open(1);
                control.setBaudRate(JD2XX.BAUD_921600);
                control.setDataCharacteristics(8, JD2XX.STOP_BITS_1, JD2XX.PARITY_NONE);
                control.setFlowControl(JD2XX.FLOW_NONE, 0, 0);
                control.setTimeouts(1000, 1000);
                
                
                
                String msg = "Hello Duke.";
msg += "The message is 'Fiat experimentum in corpore vili'";
int ret = data.write(msg.getBytes());
System.out.println(ret + " bytes sent.");
                
                data.close();
                control.close();
//                
////                System.out.println("trying to reset via control");
//                control.clrRts();
////                control.clrDtr();
//                control.setDtr();
////                Thread.sleep(1000);
//                System.out.println("Resetting NES...");
//                data.clrRts();
//                //data.clrDtr();
//                //Thread.sleep(1000);
//                data.setDtr();
//                Thread.sleep(1000);
//                
////                data.cyclePort();
////                control.cyclePort();
//                
////                data.write(0xA1);
////                byte[] bytes = new byte[] { (byte)0xA1};
//                //data.eeUAWrite(bytes);
////                control.resetDevice();
////                data.resetDevice();
//                //control.resetPort(); //Not supported
//                //data.resetPort(); // Not supported
//                //data.cyclePort(); //Not supported
////                System.out.println("Data Bitmode: " + data.getBitMode());
////                System.out.println("Control Bitmode: " + control.getBitMode());
//                
//
//                
////                System.out.println(control.getDeviceInfo());
////                System.out.println(data.getDeviceInfo());
////                System.out.println(control.getComPortNumber());
////                System.out.println(control.getDriverVersion());
////                System.out.println(data.getDriverVersion());
////                System.out.println(control.getEventStatus());
////                System.out.println(control.getLatencyTimer());
////                System.out.println(control.getLibraryVersion());
////                System.out.println("Control Modem Status: " + control.getModemStatus());
////                System.out.println("Data Modem Status: " + data.getModemStatus());
//                
////                control.purge(0);
////                data.purge(0);
//                
////                System.out.println(control.getQueueStatus());
////                int[] statuss = control.getStatus();
////                for (int i=0; i<devices.length; ++i) System.out.println(statuss[i]);
//
//                
//                
//                //ftStatus = FT_ClrRts(ftHandleB);
//
////                String msg = "Hello Duke.";
////                msg += "The message is 'Fiat experimentum in corpore vili'";
////                int ret = control.write(msg.getBytes());
////                System.out.println(ret + " bytes sent.");
//
////        if (rtype & RESET_ALTPORT)
////                shadow &= 0xF7;
////        else    shadow |= 0x08;
////        if (rtype & RESET_PLAYMODE)
////                shadow &= 0xFE;
////        else    shadow |= 0x01;
//
////                int ret = control.write(SHADOW &= 0xF7);
////                System.out.println(ret + " bytes sent.");
////                Thread.sleep(1000);
////                byte resetByte = SHADOW &= 0x08;
//
////                int ret = control.write(SHADOW |= 0x08);
////                System.out.println(ret + " bytes sent.");
////                Thread.sleep(1000);
//
////                resetByte = SHADOW &= 0xFE;
//
////                int ret = control.write(SHADOW &= 0xFE);
////                System.out.println(ret + " bytes sent.");
////                Thread.sleep(1000);
////                int ret = control.write(SHADOW &= 0x01);
////                System.out.println(ret + " bytes sent.");
////                Thread.sleep(1000);
////                // pull /RESET low
////                int ret = control.write(SHADOW &= 0xFB);
////                System.out.println(ret + " bytes sent.");
////                Thread.sleep(1000);
////                SHADOW |= 0x08;
////                SHADOW &= 0xFE;
////                SHADOW &= 0xFB;
////                int ret = control.write(SHADOW);
////                System.out.println(ret + " bytes sent.");
////                Thread.sleep(1000);
//////                resetByte = SHADOW |= 0x04;
////                SHADOW |= 0x04;
////                // pull /RESET high
////                ret = control.write(SHADOW);
////                System.out.println(ret + " bytes sent.");
////                Thread.sleep(1000);
//
////                System.out.println("Control eeRead: " + control.eeRead());
////                System.out.println("Data eeRead: " + data.eeRead());
////                
////                //System.out.println(data.eeReadConfig(1)); //not supported
////                System.out.println("Data eeUASize: " + data.eeUASize());
////                byte[] eeUARead = data.eeUARead(data.eeUASize());
//////		for (int i=0; i<eeUARead.length; ++i) System.out.println("Data eeUARead " + i + ": " + eeUARead[i]);
////                for (int i=0; i<eeUARead.length; ++i) System.out.print(eeUARead[i] + " ");
////                System.out.println("");
////
////                System.out.println("Control eeUASize: " + control.eeUASize());
////                eeUARead = control.eeUARead(control.eeUASize());
//////		for (int i=0; i<eeUARead.length; ++i) System.out.println("Data eeUARead " + i + ": " + eeUARead[i]);
////                for (int i=0; i<eeUARead.length; ++i) System.out.print(eeUARead[i] + " ");
////                System.out.println("");
////
////                ProgramData pd = data.eeRead();
////                System.out.println("Data version: " + pd.version);
////                //System.out.println(data.readEE(1)); //Exception
////                //pd = data.eeReadEx("RETR", "RE", "USB CopyNES", "RER9QXCJ"); //unsatisified link error
////                //System.out.println(pd);
////                DeviceInfo deviceInfo = control.getDeviceInfo();
////                System.out.println("Control device info: " + deviceInfo.description);
//
//                //data.write(0xA1);
//                
////                String msg = "Hello Duke.";
////                msg += "The message is 'Fiat experimentum in corpore vili'";
////                int ret = data.write(msg.getBytes());
////                System.out.println(ret + " bytes sent.");
////
////                byte[] rd = data.read(10);
////                System.out.println(new String(rd));
//
////JD2XXInputStream ins = new JD2XXInputStream(data);
////JD2XXOutputStream outs = new JD2XXOutputStream(data);
////
////byte[] received_data = new byte[10];
////int ret = ins.read(received_data);
////System.out.println(ret);
////
//////received_data = processData(received_data);
////outs.write(received_data);
////
//////ins.jd2xx.close();
////ins.close();
////outs.jd2xx.close();
////outs.close();
//                ProgramData pd = control.eeRead();
//                //System.out.println(pd.version);
//                System.out.println(pd.toString());
//                control.close();
//                data.close();
//
//
////                for (int x=0; x < 65535; x++) {
////                    try {
////                        control.open(x);
////                        System.out.println("****************************************************");
////                        System.out.println("SUCCESS!");
////                        System.out.println("****************************************************");
////                    } catch (Exception ex) {
////                        System.out.print(".");
////                    }
////                }
////                System.out.println(control.getComPortNumber());
////                control.close();
////                JD2XXOutputStream jos = new JD2XXOutputStream("RER9QXCJB", 0);
////                control.open(devices[1].toString(), 0);
////                control.close();
//                
////		String msg = "Hello dude. This is the message.";
////		int ret = control.write(msg.getBytes());
////		System.out.println(ret + " bytes sent.");
////                ProgramData pd = control.eeRead();
////		System.out.println(pd.toString());
////
////		di = control.getDeviceInfo();
////		System.out.println(di.toString());
////
//                
////		try {
////			control.addEventListener(
////				new JD2XXEventListener() {
////					public void jd2xxEvent(JD2XXEvent ev) {
////						JD2XX jo = (JD2XX)ev.getSource();
////						int et = ev.getEventType();
////						try {
////							if ((et & EVENT_RXCHAR) != 0) {
////								int r = jo.getQueueStatus();
////								System.out.println("RX event: " + new String(jo.read(r)));
////							}
////							else if ((et & EVENT_MODEM_STATUS) != 0) {
////								System.out.println("Modem status event");
////							}
////						}
////						catch (IOException e) { }
////					}
////				}
////			);
////		}
////		catch (TooManyListenersException e) { }
////		control.notifyOnEvent(EVENT_RXCHAR | EVENT_MODEM_STATUS, true);
//            
//            
//                
//                
////#define RESET_COPYMODE  0
////#define RESET_PLAYMODE  1
////#define RESET_ALTPORT   2
////#define RESET_NORESET   4
//
//            
//            //rtype was passed in as int in original
////        if (rtype & RESET_ALTPORT)
////                shadow &= 0xF7;
////        else    shadow |= 0x08;
////        if (rtype & RESET_PLAYMODE)
////                shadow &= 0xFE;
////        else    shadow |= 0x01;
////
////        if (!(rtype & RESET_NORESET))
////        {
////                shadow &= 0xFB;
////                pwControl(shadow);      // pull /RESET low
////                Sleep(SLEEP_SHORT);
////        }
////        shadow |= 0x04;         // pull /RESET high
////        pwControl(shadow);
////        Sleep(SLEEP_SHORT);
        } catch (Exception ex) {
            Logger.getLogger(Power.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
