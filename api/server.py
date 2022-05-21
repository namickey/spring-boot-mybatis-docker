# coding: utf-8

import socket
import threading
import json

bind_ip = "0.0.0.0"
bind_port = 5000

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((bind_ip, bind_port))
server.listen(5)
print('[*] Listening on %s:%d' % (bind_ip, bind_port))

def handle_client(client_socket):
    request = client_socket.recv(80000)
    #print("[*] Received: %s" % request)
    print(request[str(request).find('{"na')-2:].decode(encoding='utf-8'))
    data = '{"name":"taro", "' + request[str(request).find('{"na')-2:].decode(encoding='utf-8')

    print(json.dumps(json.loads(data), indent=2, separators=(',', ': ')))
    client_socket.send("HTTP/1.1 200".encode())
    client_socket.close()

while True:
    client, addr = server.accept()
    print("[*] Accepted connection from: %s:%d" % (addr[0], addr[1]))
    client_handler = threading.Thread(target=handle_client, args=(client, ))
    client_handler.start()
