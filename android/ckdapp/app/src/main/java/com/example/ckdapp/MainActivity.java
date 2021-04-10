package com.example.ckdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    RadioGroup RedBloodCells,PusCells,PusCellClumps,Bacteria,DiabetusMilletus,CoronaryArteryDisease,Appetite,PedalEdema,Anemia;
    EditText Age,BloodPressure,SpecificGravity,Albumin,Sugar,BloodGlucoseRandom,BloodUrea,SerumCreatinine,Sodium,Pottassium,Hemoglobin,PackedCellVolume,WBCcount,RBCcount,Hypertension;
    String age,bp,sg,al,su,bgr,bu,sc,sod,pot,hemo,pcv,wbc,rbc,htn,rc,pc,pcc,ba,dm,cad,appet,pe,ane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RedBloodCells=findViewById(R.id.radiorc);
        PusCells=findViewById(R.id.radiopc);
        PusCellClumps=findViewById(R.id.radiopcc);
        Bacteria=findViewById(R.id.radioba);
        DiabetusMilletus=findViewById(R.id.radiodm);
        CoronaryArteryDisease=findViewById(R.id.radiocad);
        Appetite=findViewById(R.id.radioappet);
        PedalEdema=findViewById(R.id.radiope);
        Anemia=findViewById(R.id.radioane);

        Age=findViewById(R.id.age);
        BloodPressure=findViewById(R.id.bp);
        SpecificGravity=findViewById(R.id.sg);
        Albumin=findViewById(R.id.al);
        Sugar=findViewById(R.id.su);
        BloodGlucoseRandom=findViewById(R.id.bgr);
        BloodUrea=findViewById(R.id.bu);
        SerumCreatinine=findViewById(R.id.sc);
        Sodium=findViewById(R.id.sod);
        Pottassium=findViewById(R.id.pot);
        Hemoglobin=findViewById(R.id.hemo);
        PackedCellVolume=findViewById(R.id.pcv);
        WBCcount=findViewById(R.id.wbc);
        RBCcount=findViewById(R.id.rbc);
        Hypertension=findViewById(R.id.htn);

        Age.setFilters(new InputFilter[]{new MinMaxFilter(this,11,90)});
        BloodPressure.setFilters(new InputFilter[]{new MinMaxFilter(this,60,90)});
        Sugar.setFilters(new InputFilter[]{new MinMaxFilter(this,1.01,1.025)});
        BloodGlucoseRandom.setFilters(new InputFilter[]{new MinMaxFilter(this,70.0,219)});
        BloodUrea.setFilters(new InputFilter[]{new MinMaxFilter(this,10,98)});
        SerumCreatinine.setFilters(new InputFilter[]{new MinMaxFilter(this,0.40,3.6)});
        Sodium.setFilters(new InputFilter[]{new MinMaxFilter(this,131.00,147.000)});
        Pottassium.setFilters(new InputFilter[]{new MinMaxFilter(this,2.90,5.500)});
        Hemoglobin.setFilters(new InputFilter[]{new MinMaxFilter(this,9.60,17.8)});
        PackedCellVolume.setFilters(new InputFilter[]{new MinMaxFilter(this,29.00,54.00)});
        WBCcount.setFilters(new InputFilter[]{new MinMaxFilter(this,4200.00,11900.000)});
        RBCcount.setFilters(new InputFilter[]{new MinMaxFilter(this,3.60,6.500)});
        Albumin.setFilters(new InputFilter[]{new MinMaxFilter(this,0.00,4.00)});
        Hypertension.setFilters(new InputFilter[]{new MinMaxFilter(this,0.00,1.00)});


    }
    public void submitandvalidate(View view)
    {
        age=Age.getText().toString();
        bp=BloodPressure.getText().toString();
        sg=SpecificGravity.getText().toString();
        al=Albumin.getText().toString();
        su=Sugar.getText().toString();
        bgr=BloodGlucoseRandom.getText().toString();
        bu=BloodUrea.getText().toString();
        sc=SerumCreatinine.getText().toString();
        sod=Sodium.getText().toString();
        pot=Pottassium.getText().toString();
        hemo=Hemoglobin.getText().toString();
        pcv=PackedCellVolume.getText().toString();
        wbc=WBCcount.getText().toString();
        rbc=RBCcount.getText().toString();
        htn=Hypertension.getText().toString();

        int RedBloodCellsID=RedBloodCells.getCheckedRadioButtonId();
        RadioButton redbloodcellsbtn=findViewById(RedBloodCellsID);

        int PusCellsID=PusCells.getCheckedRadioButtonId();
        RadioButton puscellsbtn=findViewById(PusCellsID);

        int PusCellClumpsID=PusCellClumps.getCheckedRadioButtonId();
        RadioButton puscellclumpsbtn=findViewById(PusCellClumpsID);

        int BacteriaID=Bacteria.getCheckedRadioButtonId();
        RadioButton bacteriabtn=findViewById(BacteriaID);

        int DiabetusMilletusID=DiabetusMilletus.getCheckedRadioButtonId();
        RadioButton diabetusmilletusbtn=findViewById(DiabetusMilletusID);

        int CoronaryArteryDiseaseID=CoronaryArteryDisease.getCheckedRadioButtonId();
        RadioButton coronaryarterydiseasebtn=findViewById(CoronaryArteryDiseaseID);

        int AppetiteID=Appetite.getCheckedRadioButtonId();
        RadioButton appetitebtn=findViewById(AppetiteID);

        int PedalAdemaID=PedalEdema.getCheckedRadioButtonId();
        RadioButton pedalademabtn=findViewById(PedalAdemaID);

        int AnemiaID=Anemia.getCheckedRadioButtonId();
        RadioButton anemiabtn=findViewById(AnemiaID);

        if(RedBloodCellsID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            rc=redbloodcellsbtn.getText().toString();
        }

        if(PusCellsID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            pc=puscellsbtn.getText().toString();
        }

        if(PusCellClumpsID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            pcc=puscellclumpsbtn.getText().toString();
        }

        if(BacteriaID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            ba=bacteriabtn.getText().toString();
        }

        if(DiabetusMilletusID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            dm=diabetusmilletusbtn.getText().toString();
        }

        if(CoronaryArteryDiseaseID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            cad=coronaryarterydiseasebtn.getText().toString();
        }

        if(AppetiteID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            appet=appetitebtn.getText().toString();
        }

        if(PedalAdemaID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            pe=pedalademabtn.getText().toString();
        }

        if(AnemiaID==-1){
            Toast.makeText(this, "Nothing selected,please choose any of the above in Gender", Toast.LENGTH_SHORT).show();
        }
        else{
            ane=anemiabtn.getText().toString();
        }

        if(!TextUtils.isEmpty(age)&&(!TextUtils.isEmpty(bp)&&(!TextUtils.isEmpty(sg)&&(!TextUtils.isEmpty(al)&&(!TextUtils.isEmpty(su)&&
                (!TextUtils.isEmpty(bgr))&&(!TextUtils.isEmpty(bu)&&(!TextUtils.isEmpty(sc)&&(!TextUtils.isEmpty(sod)&&(!TextUtils.isEmpty(pot)
                &&(!TextUtils.isEmpty(hemo)&&(!TextUtils.isEmpty(pcv)&&(!TextUtils.isEmpty(wbc)&&(!TextUtils.isEmpty(rbc)&&(!TextUtils.isEmpty(htn)))))))))))))))
        {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final String url = "";
            JSONObject postParams = new JSONObject();
            try
            {
                postParams.put("Age", age);
                postParams.put("BloodPressure", bp);
                postParams.put("Specific_Gravity",sg);
                postParams.put("Albumin", al);
                postParams.put("Sugar", su);
                postParams.put("Red_Blood_Cells", rc);
                postParams.put("Pus_Cells",pc );
                postParams.put("Pus_Cell_Clumps" ,pcc );
                postParams.put("Bacteria",ba );
                postParams.put("Blood_Glucose_Random",bgr );
                postParams.put("Blood_Urea",bu );
                postParams.put("Serum_Creatinie",sc );
                postParams.put("Sodium",sod );
                postParams.put("Potassium",pot );
                postParams.put("Hemoglobin",hemo );
                postParams.put("Packed_Cell_Voume",pcv);
                postParams.put("White_Blood_Cells_Count",wbc );
                postParams.put("Red_Blood_Cells_Count",rbc);
                postParams.put("Hypertension",htn);
                postParams.put("Diabetes_Milletus",dm );
                postParams.put("Coronary_Artery_Disease",cad );
                postParams.put("Appetite",appet );
                postParams.put("Pedal_Adema",pe );
                postParams.put("Anemia",ane );
            } catch (JSONException e)
            {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("On Response", "onResponse: " + response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("On Error",error.toString());
                    Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);

        }
        else
        {
            Toast.makeText(this, "check if all the details are given correct", Toast.LENGTH_SHORT).show();
        }


    }
}