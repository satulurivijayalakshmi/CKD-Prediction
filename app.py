import pandas as pd
from sklearn.preprocessing import StandardScaler
import pickle
from flask_cors import CORS
from flask import Flask,request,render_template
app=Flask(__name__)
CORS(app)
model=pickle.load(open('model.pkl','rb'))
@app.route('/predict',methods=['POST'])
def predict():
    
    age=request.json['age']
    bloodpressure = request.json['bp']
    specificgravity = request.json['sg']
    albumin = request.json['al']
    sugar = reuest.json['su']
    redbloodcells = request.json['rbc']
    puscells = request.json['pc']
    puscellclumps = request.json['pcc']
    bacteria = request.json['ba']
    bloodglucoserandom = request.json['bgr']
    bloodurea = request.json['bu']
    serumcreatinine = request.json['sc']
    sodium = request.json['sod']
    potassium = request.json['pot']
    hemoglobin = request.json['hemo']
    packedcellvolume = request.json['pcv']
    whitebloodcellcount = request.json['wc']
    redbloodcellcount = request.json['rc']
    hypertension = request.json['htn']
    diabetesmilletus = request.json['dm']
    coronoryarterydisease =request.json['cad']
    appetite = request.json['appet']
    pedaledema = request.json['pe']
    anemia = request.json['ane']
    data=pd.DataFrame(datavalues,columns=['age','bp','sg','al','su','rc','pc','pcc','ba','bgr','bu','sc','sod','pot','hemo','pcv','wbc','rbc','htn','dm','cad','appet','pe','ane'])
    res=model.predict(data)
    output= res[0]

    if output == 0:
        res_str = "chronic kidney disease is positive"
    else:
        res_str = "chronic kidney disease is negative"
    return res_str
if __name__=="__main__":
    app.run(debug=True)
    
