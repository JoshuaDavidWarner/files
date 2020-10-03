# -*- coding: utf-8 -*-
"""
Created on Fri Oct  2 14:56:16 2020

@author: joshua warner
Program 2 UDP Pinger
This program is the UDP Server
"""

import random
from socket import *

serverSocket = socket(AF_INET,SOCK_DGRAM)

server = ""
host = 6789

serverSocket.bind((server,host))
while True:
    #print("ready to serve...")
    rand = random.randint(0,10)

    message, address = serverSocket.recvfrom(1024)

    message = message.upper()

    if rand < 4:
        continue
    serverSocket.sendto(message,address)