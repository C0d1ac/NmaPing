# NmaPing
<p align="center">
<img src="/assets/logo.png" width="200" height="200">
</p>

__NmaPing__ is an Android app that provides an easy-to-use and user-friendly interface for cybersecurity professionals, network professionals, or even casual users. It assists them in pinging publicly available hosts or scanning their ports.

# SETUP


The login and register is handled by __firebase authentication__ , You need to setup the firebase with android studio, on your project goto __Tools > Firebase__ , that would get you to the firebase menu on the right, click on __"Realtime Database"__ then __"Get started with realtime databse"__ , by this time you should already have a firebase account to link the app with a running project or a new one ,  after that follow the steps provided by the android studio guide, this will generate the `google_services.json` file that holds all the firebase configurations, You'll need to modify the source, in the `HistoryActivity.java` and in `nmapActicvity.java` look for "TO BE REPLACED WITH THE FIREBASE DATABASE LINK" and change it to your firebase database link( to get this link head over to your firebase console then click on "Realtime Database" and copy the database link), also don't forget to change "TO BE REPLACED WITH THE API PUBLIC IP" to your server IP in t efollowing files : `nmapActicvity.java`, `pingActivity.java`.
<br>
# Usage 

## login and signup 

Launching the app users will be greeted with the Login view, clicking the "Register" Button would take them to the register page on the right 
<p align="center">
<img src="/assets/1.jpg" width="300" height="600">
<img src="/assets/2.jpg" width="300" height="600">
</p>
<br>

## Main View

Logging into the App, users would get this view : 

<p align="center">
<img src="/assets/3.jpg" width="300" height="600">
</p>

## Port Scan

<br>
From the above menu a user can choose "Nmap", which is a button that would take him to this view :
<br>

<p align="center">
<img src="/assets/4.jpg" width="300" height="600">
</p>

<br>
The above view has a field for the user to enter an IP Address and run a port scan 
<br>

## Ping

Here the user can ping any public IP address

<p align="center">
<img src="/assets/5.jpg" width="300" height="600">
</p>

## Demo 

Check This Demo :




