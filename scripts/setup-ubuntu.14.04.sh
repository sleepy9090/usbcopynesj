#!/bin/bash
#
# Script to setup permissions for the ports used by usbcopynes under Ubuntu 14.04.
#
# TODO: convert to udev rule
#
# Notes:
# Specific to Ubuntu 14.04, not tested under other versions.
# Ensure all other ftdi devices are NOT plugged in.
# Script must be run as root.
#

# Currently logged in user:
USERNAME=sleepy

# Get the bus and device. 
# e.g. Bus 003 Device 007: ID 0403:6010 Future Technology Devices International, Ltd FT2232C Dual USB-UART/FIFO IC
echo "Getting USB CopyNES bus and device info..."
BUS=$(/usr/bin/lsusb | /bin/grep FT2232C | /usr/bin/awk '{print $2}')
if [ $? -ne 0 ]; then
    echo "Failed to get the USB CopyNES bus. Exiting."
    exit
else
    echo "Found USB CopyNES on bus: ${BUS}"
fi

DEVICE=$(/usr/bin/lsusb | /bin/grep FT2232C | /usr/bin/awk '{print $4}' | sed 's/:$//')
if [ $? -ne 0 ]; then
    echo "Failed to get the USB CopyNES device. Exiting."
    exit
else
    echo "Found USB CopyNES device: ${DEVICE}"
fi

# Setup the paths for the usb copynes devices
USB_COPYNES_CONTROL_PORT=/dev/ttyUSB0
USB_COPYNES_DATA_PORT=/dev/ttyUSB1
USB_DEVICE=/dev/bus/usb/${BUS}/${DEVICE}

# Set permissions.
echo "Setting permissions..."
/bin/chmod 755 -R ${USB_COPYNES_CONTROL_PORT} ${USB_COPYNES_DATA_PORT} ${USB_DEVICE}
if [ $? -ne 0 ]; then
    echo "Failed to set permissions. Are you running as root? Exiting."
    exit
else
    echo "OK"
fi

# Change ownership.
echo "Changing ownership..."
/bin/chown ${USERNAME}:${USERNAME} -R ${USB_COPYNES_CONTROL_PORT} ${USB_COPYNES_DATA_PORT} ${USB_DEVICE}
if [ $? -ne 0 ]; then
    echo "Failed to change ownership. Did you set the username variable? Exiting."
    exit
else
    echo "OK"
fi 

# Unload VCP driver
echo "Unloading VCP drivers..."
sudo rmmod ftdi_sio
if [ $? -ne 0 ]; then
    echo "Failed to unload ftdi_sio. Exiting."
    exit
else
    echo "OK"
fi 
sudo rmmod usbserial
if [ $? -ne 0 ]; then
    echo "Failed to unload usbserial. Exiting."
    exit
else
    echo "OK"
fi 

echo "Done!"


# drivers need installed? see manual
# sudo chmod 755 libftd2xx.so.1.1.12