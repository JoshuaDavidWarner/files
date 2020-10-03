# -*- coding: utf-8 -*-
"""
Created on Fri Oct  2 09:36:01 2020

@author: joshua

Assignment 1
"""
#import libraries
from socket import *

import sys 
#create socket
serverSocket = socket(AF_INET, SOCK_STREAM)

serverPort = 8080 

serverSocket.bind(("", serverPort)) 

serverSocket.listen(1) # 



while True:

    print ('Ready to serve...')

    connectionSocket, addr = serverSocket.accept()

    try:

        message = connectionSocket.recv(1024)

        print (message)

        filename = message.split()[1]

        f = open(filename[1:])

        outputdata = f.read()

        connectionSocket.sendall(b"HTTP/1.1 200 OK\r\n\r\n")

        for i in range(0, len(outputdata)):

            connectionSocket.send(outputdata[i].encode())

        connectionSocket.send("\r\n".encode())

        connectionSocket.close()

    except IOError:

        connectionSocket.sendall(b"HTTP/1.1 404 Not Found\r\n\r\n")

        connectionSocket.sendall(b"<html><head></head><body><h2>404 Not Found</h2></body></html>\r\n")

        connectionSocket.close()

serverSocket.close()

sys.exit()
