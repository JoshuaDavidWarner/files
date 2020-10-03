from socket import *
import time
import sys
import warnings
warnings.filterwarnings("ignore")


server = '127.0.0.1'
port = 6789

clientSocket = socket(AF_INET,SOCK_DGRAM)
count = 1

while(count < 11):
    try:
        message = str(count)
        
        clientSocket.settimeout(1)
        clientSocket.sendto(message.encode(),(server,port))
        t0 = time.clock()
        modified,addr = clientSocket.recvfrom(2048)
        t1 = time.clock() - t0
        print("The count = " + modified.decode() + ' and the time elapsed is ' + str(t1) + " seconds")
        print()
        count = count + 1
    except timeout:
        print('Request Timed Out')
        print()
        count = count + 1
        continue
    
    
clientSocket.close()