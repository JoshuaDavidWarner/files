# -*- coding: utf-8 -*-
"""
Created on Thu Oct  1 19:36:07 2020

@author: joshua warner

This assignment creates a TCP web server. This is a multi-threaded version.
"""

#Import Libraries

from socket import *
import sys 
from _thread import *

#Set up socket on local host
serverSocket = socket(AF_INET, SOCK_STREAM)

server = ""
port = 8080 
threadcount = 0

serverSocket.bind((server, port))

serverSocket.listen(5) 

#create a new function for multithread
def new_thread_connection(message):
    
        try:

        
            data = message.recv(1024)

            print (message)

            filename = data.split()[1]

            f = open(filename[1:])

            outputdata = f.read()

            connectionSocket.sendall(b"HTTP/1.1 200 OK\r\n\r\n")

            for i in range(0, len(outputdata)):

                connectionSocket.send(outputdata[i].encode())

            connectionSocket.send("\r\n".encode())

            connectionSocket.close()

#if file isn't present, 404 error will show

        except IOError:

            connectionSocket.sendall(b"HTTP/1.1 404 Not found\r\n\r\n")

            connectionSocket.sendall(b"<html><head></head><body><h2>404 Not Found</h2></body></html>\r\n")

            connectionSocket.close()

#Ready the socket to accept a TCP Connection
while(True):

    print ('Ready to serve...')
    connectionSocket, addr = serverSocket.accept()
    start_new_thread(new_thread_connection,(connectionSocket,))

    

    

    
    




#close out the socket again just in case, as well as terminate the program.
serverSocket.close()

sys.exit()
