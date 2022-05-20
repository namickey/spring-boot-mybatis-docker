import urllib.request, json

url = "http://localhost:8080/hello"
method = "POST"
headers = {"Content-Type" : "application/json"}

request = urllib.request.Request(url, data=json.dumps({"id" : "aaa", "name" : "taro"}).encode("utf-8"), method=method, headers=headers)
with urllib.request.urlopen(request) as response:
    response_body = response.read().decode("utf-8")
    print(response_body)
