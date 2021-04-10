import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler  
from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import LogisticRegression
import pickle
df=pd.read_csv(".\kidney_disease.csv")
df.drop('id',axis=1,inplace=True)
categorical=['rbc','pc','pcc','ba','htn','dm','cad','appet','pe','ane','classification']
numerical=['age','bp','sg','al','su','bgr','bu','sc','sod','pot','hemo','pcv','wc','rc']

#filling missing values with mode
cols=['age','bp','sg','al','su','rbc','pc','pcc','ba','bgr','bu','sc','sod','pot','hemo','pcv','wc','rc','htn','dm','cad','appet','pe','ane','classification']
for i in cols:
    df[i]=df[i].fillna(df[i].dropna().mode()[0])
df.info()

df['dm']=df['dm'].str.lstrip(' ')

from sklearn.preprocessing import LabelEncoder
le=LabelEncoder()
for i in categorical:
    df[i] = le.fit_transform(df[i])
    
train=df.iloc[:,0:7]
test=df.iloc[:,-1]

#rfe
from sklearn.feature_selection import RFE
y=df.classification
x=df.drop('classification',axis=1)

model=LogisticRegression(solver='lbfgs')
rfe=RFE(model,10)
fit=rfe.fit(x,y)
print('Num Features:%d'% fit.n_features_)
print('Selected Features:%s'% fit.support_)
print('Feature Ranking:%s'% fit.ranking_)


#splitting dataset into training and test data

x_train,x_test,y_train,y_test=train_test_split(x,y,test_size=0.2)
st_x= StandardScaler()  
x_train= st_x.fit_transform(x_train)  
x_test= st_x.transform(x_test)
print(x_train)
RF=RandomForestClassifier()

#Train the model
RF.fit(x_train,y_train)
# Saving model to disk
pickle.dump(RF, open('model.pkl','wb'))
# Loading model to compare the results
model = pickle.load(open('model.pkl','rb'))
#print(model.predict([[2, 9, 6]]))