package usbcopynesj.cart;

/**
 * Play the NES Cart.
 * 
 * @author Shawn M. Crawford
 * @version 08/23/2012
 */
public class PlayCart {
    private static final int RESET_COPYMODE = 0;
    private static final int RESET_PLAYMODE = 1;
    private static final int RESET_ALTPORT = 2;
    private static final int RESET_NORESET = 4;
            
    public boolean playCart() {
        
        //call resetNES() to be located in IO class with RESET_PLAYMODE
        //MessageBox messageBox = new MessageBox();
        
        //call resetNES() to be located in IO class with RESET_COPYMODE
        return true;
    }
}

/*
#include "StdAfx.h"
#define	CMD_NAME	"Play Cartridge"

BOOL	CMD_PLAYCART (void)
{
	ResetNES(RESET_PLAYMODE);
	MessageBox(topHWnd,"Playing game - press OK to terminate",MSGBOX_TITLE,MB_OK);
	ResetNES(RESET_COPYMODE);
	return TRUE;
}
 */

//from CopyNesW.h
/* I/O routines */
/*
#define	RESET_COPYMODE	0
#define	RESET_PLAYMODE	1
#define	RESET_ALTPORT	2
#define	RESET_NORESET	4
*/