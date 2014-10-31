usbcopynesj
===========

A Java port / w.i.p. of the USB CopyNES software.

Required libs:
jd2xx.jar - A JD2XX fork that supports OS X (x86_64) and Linux (x86, x86_64, arm7hf, arm7sf) (https://github.com/0x6a77/JD2XX) and forked again (https://github.com/metratec/JD2XX). Using https://github.com/metratec/JD2XX as of 09/16/2014)

Setup:
Ubuntu 14.04:
In the scripts directory, setup-ubuntu.14.04.sh must have the USERNAME variable set to the current user:
vi ./scripts/setup-ubuntu.14.04.sh

# Currently logged in user:
USERNAME=myusername

In the scripts directory, setup-ubuntu.14.04.sh (sudo ./scripts/setup-ubuntu.14.04.sh) script must be run as su to set permissions for the ports and bus/device, used by USB CopyNES. This must be run after the USB CopyNES is plugged in. Ensure all other ftdi devices are NOT plugged in. Ensure permissions are set for the script itself (sudo chmod 775 ./scripts/setup-ubuntu.14.04.sh, sudo chown myusername:myusername ./scripts/setup-ubuntu.14.04.sh).

Windows 7:


Initial putback:
- Basic GUI
- Connects with USB CopyNES
- No real functionality yet.
