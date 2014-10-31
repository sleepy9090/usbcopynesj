/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usbcopynesj.info;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import jd2xx.JD2XX;
//import purejavacomm.CommPortIdentifier;
//import purejavacomm.SerialPort;
/**
 *
 * @author Shawn M. Crawford
 * @version 08/23/2012
 */
public class NESInfo {

//    private static SerialPort serialPortControl;
//    private static SerialPort serialPortData;
    
    private static OutputStream outputStreamControl;
    private static InputStream inputStreamControl;
    private static OutputStream outputStreamData;
    private static InputStream inputStreamData;
    // 1. The USB CopyNES uses a USB to serial adapter (FT2232C).
    // 2. Linux recognizes the adapter and maps it to a serial port (/dev/ttyUSB0 and /dev/ttyUSB1)
    // 3. Each time the USB CopyNES is plugged in, the serial ports must be chowned to the user running this program (consider making a udev rule)
    private static final String COPYNES_CONTROL_PORTNAME = "/dev/ttyUSB0";
    private static final String COPYNES_DATA_PORTNAME = "/dev/ttyUSB1";
    
    private static final String CONTROL_NAME = "ttyUSB0";
    private static final String DATA_NAME = "ttyUSB1";
    
    static Enumeration portList;
//    static CommPortIdentifier portIdControl;
//    static CommPortIdentifier portIdData;
    static String messageString = "Hello, world!\n";
    
    // generic constructor
    public NESInfo() {
        
    }

    public String getNesInfo() throws Exception {
//        try {
//            portList = CommPortIdentifier.getPortIdentifiers();
//            while (portList.hasMoreElements()) {
//                portId = (CommPortIdentifier) portList.nextElement();
//
//                System.out.println("DEBUG: portId.getCurrentOwner(): " + portId.getCurrentOwner());
//                System.out.println("DEBUG: portId.getName(): " + portId.getName());
//                System.out.println("DEBUG: portId.getPortType(): " + portId.getPortType());
//                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//                    System.out.println("DEBUG: Found a serial port.");
//                } else if (portId.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
//                    System.out.println("DEBUG: Found a parallel port.");
//                } else {
//                    System.out.println("DEBUG: Should not happen.");
//                }
//            }
            
            
//            portIdControl = CommPortIdentifier.getPortIdentifier(COPYNES_CONTROL_PORTNAME);
//            System.out.println("DEBUG: portId.getCurrentOwner(): " + portIdControl.getCurrentOwner());
//            System.out.println("DEBUG: portId.getName(): " + portIdControl.getName());
//            System.out.println("DEBUG: portId.getPortType(): " + portIdControl.getPortType());
//
//            
//            portIdData = CommPortIdentifier.getPortIdentifier(COPYNES_DATA_PORTNAME);
//            System.out.println("DEBUG: portId.getCurrentOwner(): " + portIdData.getCurrentOwner());
//            System.out.println("DEBUG: portId.getName(): " + portIdData.getName());
//            System.out.println("DEBUG: portId.getPortType(): " + portIdData.getPortType());
//
//            
//            serialPortControl = (SerialPort) portIdControl.open(CONTROL_NAME, 1000);
//            outputStreamControl = serialPortControl.getOutputStream();
//            inputStreamControl = serialPortControl.getInputStream();
//            
//            serialPortData = (SerialPort) portIdData.open(DATA_NAME, 1000);
//            outputStreamData = serialPortData.getOutputStream();
//            inputStreamData = serialPortData.getInputStream();
//            
//            serialPortControl.setSerialPortParams(9600,
//                            SerialPort.DATABITS_8,
//                            SerialPort.STOPBITS_1,
//                            SerialPort.PARITY_NONE);
//            
//            outputStreamControl.write(messageString.getBytes());
//            
//            System.out.println("Data data available: " + inputStreamData.available());
//            System.out.println("Control data available: " + inputStreamData.available());
//            //int data = inputStreamData.read();
//            //while(data != -1) {
//              //do something with data...
//              //doSomethingWithData(data);
//                //System.out.println("DATA: " + data);
//
//                //data = inputStreamData.read();
//            //}
//            outputStreamControl.close();
//            inputStreamControl.close();
//            outputStreamData.close();
//            inputStreamData.close();
//            
//        } catch (NoSuchPortException ex) {
//            Logger.getLogger(NESInfo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (PortInUseException ex) {
//            Logger.getLogger(NESInfo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedCommOperationException ex) {
//            Logger.getLogger(NESInfo.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        
        
        
        
        
        
        
        StringBuilder nesInfoString = new StringBuilder();
        JD2XX jd = new JD2XX();

//        // flags control how devices are listed (description, location, serial number)
//        // 0,1 for serial number
//        // 2,3 for description
//        // 4,5 for location
//        Object[] devices = jd.listDevices(8);
//        for (int i=0; i<devices.length; ++i) {
//            System.out.println("DEBUG: " + devices[i]);
//        }
        
        Object[] devicesBySerialNumber = jd.listDevicesBySerialNumber();
        int totalDevices = devicesBySerialNumber.length;
        System.out.println("Devices found: " + totalDevices);

        if (totalDevices == 2) {
            String[] deviceLocations = new String[2];

            for (int i=0; i<2; ++i) {
                System.out.println("DEBUG: Serial Number: " + devicesBySerialNumber[i]);
            }

            Object[] devicesByDescription = jd.listDevicesByDescription();
            for (int i=0; i<2; ++i) {
                System.out.println("DEBUG: Description: " + devicesByDescription[i]);
            }

            Object[] devicesByLocation = jd.listDevicesByLocation();
            for (int i=0; i<2; ++i) {
                deviceLocations[i] = Integer.toHexString((Integer)devicesByLocation[i]);
                System.out.println("DEBUG: Location: 0x" + deviceLocations[i]);
            }


            for (int i=0; i<devicesByDescription.length; ++i) {
                nesInfoString.append("\nFound ").append(devicesByDescription[i]);
                nesInfoString.append(" at location 0x").append(deviceLocations[i]); 
                nesInfoString.append(" with serial number ").append(devicesBySerialNumber[i]);
                nesInfoString.append(".");
            }

//        // Debug playing around
//        System.out.println("DEBUG: jd.getLibraryVersion(): " + jd.getLibraryVersion());
//        jd.openBySerialNumber(devicesBySerialNumber[1].toString());
//
//        System.out.println("DEBUG: jd.eeRead(): " + jd.eeRead());
//        //System.out.println("DEBUG: jd.eeReadConfig(1): " + jd.eeReadConfig((byte)0xA1));
//        System.out.println("DEBUG: jd.getBitMode(): " + jd.getBitMode());
//        //System.out.println("DEBUG: jd.getComPortNumber(): " + jd.getComPortNumber());
//        System.out.println("DEBUG: jd.getDeviceInfo(): " + jd.getDeviceInfo());
//        System.out.println("DEBUG: jd.getDriverVersion(): " + jd.getDriverVersion());
//        System.out.println("DEBUG: jd.getEventStatus(): " + jd.getEventStatus());
//        System.out.println("DEBUG: jd.getLatencyTimer(): " + jd.getLatencyTimer());
//        
//        System.out.println("DEBUG: jd.getModemStatus(): " + jd.getModemStatus());
//        System.out.println("DEBUG: jd.getQueueStatus(): " + jd.getQueueStatus());
//        int[] status = jd.getStatus();
//        for (int i=0; i<status.length; ++i) {
//            System.out.println("DEBUG: jd.getStatus(): " + status[i]);
//        }
//        //System.out.println("DEBUG: jd.resetDevice(): " + jd.resetDevice());
//        jd.close();
//        // End Debug
        } else {
            nesInfoString.append("Failed");
        }

        jd.close();
        return nesInfoString.toString();
//        return "";
    }

    
//OLD STUFF

//    private static final String CMD_NAME = "NES Info";
//
//    public boolean nesInfo(int hardwareVersion) {
//        char[] version = new char[256];
//        int i;
//        byte versionByte = (byte)0xA1;
//        
//        if (hardwareVersion == 1) {
//            //MessageBox: ("USB CopyNES did not return a version reply");
//            //StatusOK();
//            return false;
//        }
//        
//        //MessageBox: ("Retrieving internal version string...");
//        //BOOL	WriteByteEx (BYTE data, BOOL warn)
//        if (!writeByteEx(versionByte, false)) {
//            //MessageBox: ("Failed to request version string!");
//            //StatusOK();
//            return false;
//        }
//
//        for (i=0; i<256; i++) {
//            if (!writeByteEx(versionByte, false)) {
//                //MessageBox: ("Error reading version string!");
//                //StatusOK();
//                return false;
//            }
//            
//            //if (!version[i]) {
//            //    break;
//            //}
//        }
//
//        //MessageBox: (version)
//        //StatusOK();
//        return true;
//
//    }
//    
//    public boolean writeByteEx(byte data, boolean warn) {
//        // Stubbed
//        return true;
//    }
//
//    public boolean readByteEx(byte data, boolean warn) {
//        // Stubbed
//        return true;
//    }
    

}

/*
#include "StdAfx.h"
#define	CMD_NAME	"NES Info"

BOOL	CMD_NESINFO (void)
{
	char Version[256];
	int i;
	OpenStatus(topHWnd);
	if (HWVer == 1)
	{
		StatusText("USB CopyNES did not return a version reply");
		StatusOK();
		return FALSE;
	}
	StatusText("Retrieving internal version string...");
	if (!WriteByteEx(0xA1,FALSE))
	{
		StatusText("Failed to request version string!");
		StatusOK();
		return FALSE;
	}

	for (i = 0; i < 256; i++)
	{
		if (!ReadByteEx(&Version[i],FALSE))
		{
			StatusText("Error reading version string!");
			StatusOK();
			return FALSE;
		}
		if (!Version[i])
			break;
	}
	StatusText(Version);
	StatusOK();
	return TRUE;
}
 */

/* miscdialogs.c

void	StatusOK (void)
{
	StatusButton();
	CloseStatus();
}

void	StatusButton (void)
{
	if (DlgStatus == NULL)
		return;
	StatButton = FALSE;
	EnableWindow(GetDlgItem(DlgStatus,IDC_STATUS_BUTTON),TRUE);
	SetFocus(GetDlgItem(DlgStatus,IDC_STATUS_BUTTON)); 

	while (!StatButton)
	{
		MSG msg;
		while (PeekMessage(&msg, NULL, 0, 0, PM_REMOVE)) 
		{
			if (!IsDialogMessage(DlgStatus,&msg) && !IsDialogMessage(topHWnd,&msg))
			{
				TranslateMessage(&msg);
				DispatchMessage(&msg);
			}
		}
		Sleep(10);
	}
	EnableWindow(GetDlgItem(DlgStatus,IDC_STATUS_BUTTON),FALSE);
}

void	CloseStatus (void)
{
	if (DlgStatus == NULL)
		return;
	DestroyWindow(DlgStatus);
	DlgStatus = NULL;
}

void	__cdecl	StatusText (char *text, ...)
{
	static char txt[1024];
	va_list marker;

	if (DlgStatus == NULL)
		return;
	va_start(marker,text);
	_vsnprintf(txt,1024,text,marker);
	va_end(marker);

	SendDlgItemMessage(DlgStatus,IDC_STATUS_TEXT,LB_ADDSTRING,0,(LPARAM)(LPCTSTR)txt);
	SendDlgItemMessage(DlgStatus,IDC_STATUS_TEXT,LB_SETTOPINDEX,SendDlgItemMessage(DlgStatus,IDC_STATUS_TEXT,LB_GETCOUNT,0,0) - 1,0);
	UpdateWindow(DlgStatus);
}

********io.c
BOOL	WriteByteEx (BYTE data, BOOL warn)  //write byte to copynes, return good/bad flag
{
  DWORD BytesWritten = 0;

  FT_SetTimeouts(ftHandleA,10000,0);
  
  TxBuffer[0] = data;
  
  ftStatus = FT_Write(ftHandleA,TxBuffer,1,&BytesWritten);
  if (ftStatus == FT_OK)
  { 
    if (BytesWritten == 1) 
    { 
      //MessageBox(topHWnd, "Write Success!", "WriteByte", MB_OK | MB_ICONERROR);
      // FT_Read OK
      return TRUE;
    } 
    else 
    { 
      // FT_Write Timeout 
      if (warn)
        MessageBox(topHWnd, "USB Error: Write Timeout", "WriteByteEx", MB_OK | MB_ICONERROR);
      return FALSE;  
    } 
  } 
  else 
  { 
    // FT_Write Failed 
    //if (warn)
      	StatusText("FT STATUS = %i", ftStatus);
             MessageBox(topHWnd, "USB Error: Write Failed", "WriteByteEx", MB_OK | MB_ICONERROR);

    return FALSE;  
  } 
}

BOOL	ReadByteEx (BYTE *data, BOOL warn)   //read byte from copynes, return good/bad flag
{  
  DWORD BytesReceived = 0;
  
  FT_SetTimeouts(ftHandleA,10000,0);
  ftStatus = FT_Read(ftHandleA,RxBuffer,1,&BytesReceived);
  if (ftStatus == FT_OK) 
  { 
    if (BytesReceived == 1)
    { 
      // FT_Read OK 
      //MessageBox(topHWnd, "Read Success", "ReadByteEx", MB_OK | MB_ICONERROR);
      *data = RxBuffer[0];
      return TRUE;
    } 
    else 
    { 
      // FT_Read Timeout 
      if (warn)
        MessageBox(topHWnd, "USB Error: Read Timeout", "ReadByteEx", MB_OK | MB_ICONERROR);
      return FALSE;  
    } 
  } 
  else 
  { 
    // FT_Read Failed 
    //if (warn)
      MessageBox(topHWnd, "USB Error: Read Failed", "ReadByteEx", MB_OK | MB_ICONERROR);
    return FALSE;  
  } 
}
 */