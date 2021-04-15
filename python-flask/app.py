import pandas as pd
from sklearn.preprocessing import StandardScaler  
import pickle
from flask_cors import CORS
from flask import Flask,request,render_template
app=Flask(__name__)
CORS(app)
model = pickle.load(open('model.pkl', 'rb'))
@app.route('/')
def home():
    return render_template('index.html')

@app.route('/predict',methods=['POST'])
def predict():
    age=request.json['Age']
    bp=request.json['Blood_Pressure']
    sg=request.json['Specific_Gravity']
    al=request.json['Albumin']
    su=request.json['Sugar']
    rc=request.json['Red_Blood_Cells']
    pc=request.json['Pus_Cells']
    pcc=request.json['Pus_Cell_Clumps']
    ba=request.json['Bacteria']
    bgr=request.json['Blood_Glucose_Random']
    bu=request.json['Blood_Urea']
    sc=request.json['Serum_Creatinine']
    sod=request.json['Sodium']
    pot=request.json['Potassium']
    hemo=request.json['Hemoglobin']
    pcv=request.json['Packed_Cell_Volume']
    wbc=request.json['White_Blood_Cell_Count']
    rbc=request.json['Red_Blood_Cell_Count']
    htn=request.json['Hypertension']
    dm=request.json['Diabetes_Milletus']
    cad=request.json['Coronory_Artery_Disease']
    appet=request.json['Appetite']
    pe=request.json['Pedal_Edema']
    ane=request.json['ane']
    datavalues=[[age,bp,sg,al,su,rc,pc,pcc,ba,bgr,bu,sc,sod,pot,hemo,pcv,wbc,rbc,htn,dm,cad,appet,pe,ane]]
    data=pd.DataFrame(datavalues,columns=['Age','Blood_Pressure','Specific_Gravity','Alubmin','Sugar','Red_Blood_Cells',
                                          'Pus_Cells','Pus_Cell_Clumps','Bacteria','Blood_Glucose_Random','Blood_Urea',
                                          'Serum_Creatinine','Sodium','Potassium','Hemoglobin','Packed_Cell_Volume',
                                          'White_Blood_Cell_Count','Red_Blood_Cell_Count','Hypertension','Diabetes_Milletus',
                                          'Coronory_Artery_Disease','Appetite','Pedal_Edema','ane'])
    categorical=['Red_Blood_Cells','Pus_Cells','Pus_Cell_Clumps','Bacteria','Hypertension','Diabetes_Milletus','Coronory_Artery_Disease','Appetite','Pedal_Edema','ane']
    from sklearn.preprocessing import LabelEncoder
    le=LabelEncoder()
    for i in categorical:
        data[i] = le.fit_transform(data[i])
    data[['Age','Blood_Pressure','Specific_Gravity','Alubmin','Sugar','Blood_Glucose_Random','Blood_Urea','Serum_Creatinine','Sodium','Potassium',
          'Hemoglobin','Packed_Cell_Volume','White_Blood_Cell_Count','Red_Blood_Cell_Count','Hypertension']] = StandardScaler().fit_transform(data[['Age','Blood_Pressure','Specific_Gravity','Alubmin','Sugar','Blood_Glucose_Random','Blood_Urea','Serum_Creatinine','Sodium','Potassium',
          'Hemoglobin','Packed_Cell_Volume','White_Blood_Cell_Count','Red_Blood_Cell_Count','Hypertension']])
    res=model.predict(data)
    output=res[0]
    
    if output==0:
        res_str="Chronic Kidney Disease is positive" 
    else:
        res_str="Chronic Kidney Disease is negative"
    return res_str

if __name__=="__main__":
    app.run(debug=True)
