#!/usr/bin/env python3
import RPi.GPIO as GPIO
from mfrc522 import SimpleMFRC522
import mfrc522
import socket
import os
import time
import json
import sys
import select
import datetime
import _thread
from types import SimpleNamespace

led_accept_card = 12 # 0 = on
led_denied_card = 16 # 0 = on
led_got_card_id = 20 # 0 = on
led_no_card_id = 21  # 0 = on


GPIO.setmode(GPIO.BCM)           
GPIO.setup(led_accept_card, GPIO.OUT)
GPIO.setup(led_denied_card, GPIO.OUT)
GPIO.setup(led_got_card_id, GPIO.OUT)
GPIO.setup(led_no_card_id, GPIO.OUT)

GPIO.output(led_accept_card, 1)       
GPIO.output(led_denied_card, 1)       
GPIO.output(led_got_card_id, 1) 
GPIO.output(led_no_card_id, 0) 

#Init Cardreader
reader = SimpleMFRC522()


#Connection
SERVER_HOST = "192.168.2.112"
SERVER_PORT = 5001

s = socket.socket()
s.bind((SERVER_HOST, SERVER_PORT))

standard_id = "9999945345453"
received_id = standard_id
accept = "accept\n"
not_accept = "notAccept\n"

number_of_card_contacts = 0 # 2 = Rueckgabe
duration_left_in_minutes = 0


lock = _thread.allocate_lock()

def countMinutes():
    global duration_left_in_minutes
    
    while(True):
        time.sleep(60)
        lock.acquire()
        duration_left_in_minutes = duration_left_in_minutes - 1;
        lock.release()
        
        
def checkCardValidity():
    global duration_left_in_minutes
    global received_id
    global number_of_card_contacts
    global standard_id
    
    while(True):# Pruefe, ob Karte noch gueltig
        time.sleep(5)
        lock.acquire()
        if(received_id != standard_id):         
            if((number_of_card_contacts > 2) | (duration_left_in_minutes <= 0)):
                received_id = standard_id
                number_of_card_contacts = 0
                duration_left_in_minutes = 0
                GPIO.output(led_got_card_id, 1) 
                GPIO.output(led_no_card_id, 0) 
        lock.release()
        
    
def getNewRFIDtokensOverNetwork():
    global received_id
    global number_of_card_contacts
    global duration_left_in_minutes
    
    while True: 
             
        try:
            #Start listening
            s.listen()
            print(datetime.datetime.now()) 
            print(f"[*] Listening as {SERVER_HOST}:{SERVER_PORT}")

            client_socket, address = s.accept()
            
            with client_socket:
                print(f"[+] {address} is connected.")
                
                received = client_socket.recv(1024)
                
                if not received:
                    client_socket.send(not_accept.encode())
                    break
                
                received_data = json.loads(received, object_hook=lambda d: SimpleNamespace(**d))
                print(datetime.datetime.now()) 
                print(received_data.dauer, received_data.rfid, received_data.isActive)
                
                lock.acquire()
                received_id = received_data.rfid
                number_of_card_contacts = 0
                duration_left_in_minutes = received_data.dauer
                lock.release()
                
                client_socket.send(accept.encode())      
                GPIO.output(led_got_card_id, 0) 
                GPIO.output(led_no_card_id, 1) 
                print("\n")
            
        
        except Exception as e: 
            
            print(f"Fehler {e}")	
            time.sleep(5)


_thread.start_new_thread(getNewRFIDtokensOverNetwork,())
_thread.start_new_thread(checkCardValidity,())
_thread.start_new_thread(countMinutes,())

while True:
    try:        
        while True:
            GPIO.output(led_accept_card, 1)       
            GPIO.output(led_denied_card, 1)
            
            id, text = reader.read()
            print("\n") 
            print(datetime.datetime.now())
            print(id)
            
            lock.acquire()
            if(received_id == str(id)):
        
                GPIO.output(led_accept_card, 0)       
                GPIO.output(led_denied_card, 1)
                number_of_card_contacts = number_of_card_contacts + 1
                print("permission granted\n")
                lock.release() 
                time.sleep(3)

            else:
                lock.release() 
                GPIO.output(led_accept_card, 1)       
                GPIO.output(led_denied_card, 0)
                
                print("permission denied\n")
                time.sleep(3)
            
 
                
    except KeyboardInterrupt:
        print ("Das Programm wurde beendet.\n")
        GPIO.cleanup()
        s.close()
        break


