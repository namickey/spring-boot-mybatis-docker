import urllib.request, json

url = "http://localhost:8080/full"
method = "POST"
headers = {"Content-Type" : "application/json"}

jsonData = json.dumps({"name" : "taro",         "nameNull" : None, "nameEmpty":"",
                       "age":20,                "ageNull":None, "ageEmpty":"",
                       "item":{"name":"hanako","price":100},"itemNull":None,"itemEmpty":{},
                       "codeList":["01","02"],  "codeListNull":None,"codeListEmptyList":[],
                       "itemList":[{"name":"taro","price":100},{"name":"hanako","price":200}],
                       "itemListNull":None,"itemListEmptyList":[]}).encode("utf-8")
request = urllib.request.Request(url, data=jsonData, method=method, headers=headers)
with urllib.request.urlopen(request) as response:
    response_body = response.read().decode("utf-8")
    print(json.dumps(json.loads(response_body), indent=2, separators=(',', ': ')))


url = "http://localhost:8080/rest"
request = urllib.request.Request(url, data=jsonData, method=method, headers=headers)
with urllib.request.urlopen(request) as response:
    response_body = response.read().decode("utf-8")
    print(json.dumps(json.loads(response_body), indent=2, separators=(',', ': ')))
