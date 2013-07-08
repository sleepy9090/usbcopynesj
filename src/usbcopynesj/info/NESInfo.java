/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usbcopynesj.info;

import java.io.IOException;
import jd2xx.JD2XX;
import jd2xx.JD2XXInputStream;
import jd2xx.JD2XXOutputStream;
/**
 *
 * @author Shawn M. Crawford
 * @version 08/23/2012
 */
public class NESInfo {

    public String getNesInfo() throws IOException {
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
        for (int i=0; i<devicesBySerialNumber.length; ++i) {
            System.out.println("DEBUG: Serial Number: " + devicesBySerialNumber[i]);
        }

        Object[] devicesByDescription = jd.listDevicesByDescription();
        for (int i=0; i<devicesByDescription.length; ++i) {
            System.out.println("DEBUG: Description: " + devicesByDescription[i]);
        }

        Object[] devicesByLocation = jd.listDevicesByLocation();
        for (int i=0; i<devicesByLocation.length; ++i) {
            System.out.println("DEBUG: Location: " + devicesByLocation[i]);
        }

        if (devicesByDescription.length >= 1) {
            for (int i=0; i<devicesByDescription.length; ++i) {
                nesInfoString.append("\nFound " + devicesByDescription[i]);
                nesInfoString.append(" at location " + devicesByLocation[i]); 
                nesInfoString.append(" with serial number " + devicesBySerialNumber[i]);
                nesInfoString.append(".");
            }
        } else {
            nesInfoString.append("Failed");
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

        return nesInfoString.toString();
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