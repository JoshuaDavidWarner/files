import requests
r = requests.get('http://127.0.0.1:8080/HelloWorld.html')
print(r.text)