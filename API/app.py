from flask import Flask, jsonify, request
import re
import subprocess
import time

app = Flask(__name__)

@app.route('/api', methods=['GET'])
def api():
    return jsonify(message = "Hello World!!!")
@app.route('/',methods=['GET'])
def index():
    return "Hi, This Is Me, Index \n"

@app.route('/ping',methods=['POST'])
def ping():
    ip = request.json
    print(ip['ip'])
    dict={}
    if(is_valid_ip(ip['ip'])):
        var = subprocess.check_output(["ping", ip['ip'],"-c","4"])
        dict[0] = var.decode()
        return jsonify(dict)
    else:
        dict[0] = "Invalid IP Address!"
        return jsonify(dict)

@app.route('/scan',methods=['POST'])
def scan():
    ip = request.json
    dict={}
    if(is_valid_ip(ip['ip'])):
        if(ip['ip'] in ['127.0.0.1','']):
            dict[0] = "Cannot Scan That IP!!!"
            return jsonify(dict)
        print(ip['ip'])
        out = subprocess.check_output(["nmap",ip['ip']])
        s = out.decode().split('\n')
        out = ""
        for i in range(len(s)):
            if('Nmap done' not in s[i]):
                dict[i] = s[i]
            else:
                break
        dict.pop(len(dict)-1)
        time.sleep(1)
        print("[SENT])")
        return jsonify(dict)
    else:
        dict[0] = "Invalid IP Address!"
        return jsonify(dict)

def is_valid_ip(ip):
    pattern = r'^(\d{1,3}\.){3}\d{1,3}$'
    return re.match(pattern, ip) is not None

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=1337)