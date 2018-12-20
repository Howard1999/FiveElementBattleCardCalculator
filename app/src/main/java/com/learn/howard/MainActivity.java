package com.learn.howard;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;


public class MainActivity extends AppCompatActivity
        implements OnClickListener , OnLongClickListener , AdapterView.OnItemSelectedListener {

    int MaxLife=250;
    int MaxElfPoint=6;

    int Alife,Blife;
    int Temp;
    TextView TeamALF,TeamBLF;
    TextView txv_elfpA1, txv_elfpA2, txv_elfpB1, txv_elfpB2;
    Boolean A,B,Plus,Minus,AEp1,AEp2,BEp1,BEp2;
    String output;
    int elfpA1 =0, elfpA2 =0, elfpB1 =0, elfpB2 =0;

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_reset250) {
            Alife=MaxLife;
            Blife=MaxLife;
            Temp=0;
            A=false;
            B=false;
            Plus=false;
            Minus=false;
            AEp1=false;
            AEp2=false;
            BEp1=false;
            BEp2=false;
            elfpA1 =0;
            elfpA2 =0;
            elfpB1 =0;
            elfpB2 =0;
            output="";
            ResetTextColor();
            TeamALF.setBackgroundColor(Color.rgb(245,255,250));
            TeamBLF.setBackgroundColor(Color.rgb(245,255,250));
            ResetAllAdapter();
        }
        else if(v.getId()==R.id.button_change){
            Temp=Alife;
            Alife=Blife;
            Blife=Temp;
            Plus=false;
            Minus=false;
            Temp=0;

            if(A){
                output=String.valueOf(Alife);
            }
            else if(B){
                output=String.valueOf(Blife);
            }
        }
        else if(v.getId()==R.id.TeamALife) {
            Temp=0;
            A=true;
            B=false;
            Plus=false;
            Minus=false;
            AEp1=false;
            AEp2=false;
            BEp1=false;
            BEp2=false;
            output=String.valueOf(Alife);
            ResetTextColor();
            TeamALF.setTextColor(Color.rgb(255,153,18));
        }
        else if(v.getId()==R.id.TeamBLife) {
            Temp=0;
            B=true;
            A=false;
            Plus=false;
            Minus=false;
            AEp1=false;
            AEp2=false;
            BEp1=false;
            BEp2=false;
            output=String.valueOf(Blife);
            ResetTextColor();
            TeamBLF.setTextColor(Color.rgb(255,153,18));
        }
        else if(v.getId()==R.id.buttonplus&&(A||B)) {
            if(Plus||Minus){
                if(output.substring(output.length()-1).contains("-")){
                    output=output.substring(0,output.length()-1);
                    output=output.concat("+");
                    Plus=true;
                    Minus=false;
                }
                else if(output.substring(output.length()-1).contains("+")){
                }
                else{
                    if(A&&Plus){
                        Alife=Alife+Temp;
                    }
                    if(B&&Plus){
                        Blife=Blife+Temp;
                    }
                    if(A&&Minus){
                        Alife=Alife-Temp;
                    }
                    if(B&&Minus){
                        Blife=Blife-Temp;
                    }
                    Temp=0;
                    Plus=true;
                    Minus=false;
                    output=output.concat("+");
                }
            }
            else{
                Plus=true;
                Minus=false;
                output=output.concat("+");
            }
        }
        else if(v.getId()==R.id.buttonminus&&(A||B)) {
            if(Plus||Minus) {
                if (output.substring(output.length() - 1).contains("+")) {
                    output = output.substring(0, output.length() - 1);
                    output = output.concat("-");
                    Plus=false;
                    Minus=true;
                }
                else if (output.substring(output.length() - 1).contains("-")) {
                }
                else{
                    if(A&&Plus){
                        Alife=Alife+Temp;
                    }
                    if(B&&Plus){
                        Blife=Blife+Temp;
                    }
                    if(A&&Minus){
                        Alife=Alife-Temp;
                    }
                    if(B&&Minus){
                        Blife=Blife-Temp;
                    }
                    Temp=0;
                    Plus=false;
                    Minus=true;
                    output=output.concat("-");
                }
            }
            else{
                Minus=true;
                Plus=false;
                output=output.concat("-");
            }
        }
        else if(v.getId()==R.id.buttonplus&&(AEp1||AEp2||BEp1||BEp2)) {
            Spinner elfA1=(Spinner) findViewById(R.id.spinnerTeamA_Elf_1);
            Spinner elfA2=(Spinner) findViewById(R.id.spinnerTeamA_Elf_2);
            Spinner elfB1=(Spinner) findViewById(R.id.spinnerTeamB_Elf_1);
            Spinner elfB2=(Spinner) findViewById(R.id.spinnerTeamB_Elf_2);

            if(AEp1&& elfpA1 <MaxElfPoint&&elfA1.getSelectedItemPosition()!=0){
                elfpA1 +=1;
            }
            else if(AEp2&& elfpA2 <MaxElfPoint&&elfA2.getSelectedItemPosition()!=0){
                elfpA2 +=1;
            }
            else if(BEp1&& elfpB1 <MaxElfPoint&&elfB1.getSelectedItemPosition()!=0){
                elfpB1 +=1;
            }
            else if(BEp2&& elfpB2 <MaxElfPoint&&elfB2.getSelectedItemPosition()!=0){
                elfpB2 +=1;
            }
        }
        else if(v.getId()==R.id.buttonminus&&(AEp1||AEp2||BEp1||BEp2)) {
            Spinner elfA1=(Spinner) findViewById(R.id.spinnerTeamA_Elf_1);
            Spinner elfA2=(Spinner) findViewById(R.id.spinnerTeamA_Elf_2);
            Spinner elfB1=(Spinner) findViewById(R.id.spinnerTeamB_Elf_1);
            Spinner elfB2=(Spinner) findViewById(R.id.spinnerTeamB_Elf_2);

            if(AEp1&& elfpA1 >0&&elfA1.getSelectedItemPosition()!=0){
                elfpA1 -=1;
                if(elfpA1 ==0){
                    ResetAdapter(elfA1);
                }
            }
            else if(AEp2&& elfpA2 >0&&elfA2.getSelectedItemPosition()!=0){
                elfpA2 -=1;
                if(elfpA2 ==0){
                    ResetAdapter(elfA2);
                }
            }
            else if(BEp1&& elfpB1 >0&&elfB1.getSelectedItemPosition()!=0){
                elfpB1 -=1;
                if(elfpB1 ==0){
                    ResetAdapter(elfB1);
                }
            }
            else if(BEp2&& elfpB2 >0&&elfB2.getSelectedItemPosition()!=0){
                elfpB2 -=1;
                if(elfpB2 ==0){
                    ResetAdapter(elfB2);
                }
            }
        }
        else if(v.getId()==R.id.buttonequal) {
            if(A&&Plus){
                Alife=Alife+Temp;
            }
            if(B&&Plus){
                Blife=Blife+Temp;
            }
            if(A&&Minus){
                Alife=Alife-Temp;
            }
            if(B&&Minus){
                Blife=Blife-Temp;
            }

            Temp=0;
            Plus=false;
            Minus=false;

            if(Alife>MaxLife){
                Alife=MaxLife;
            }
            else if(Alife<0){
                Alife=0;
            }
            if(Blife>MaxLife){
                Blife=MaxLife;
            }
            else if(Blife<0){
                Blife=0;
            }

            if(A){
                output=String.valueOf(Alife);
            }
            else if(B){
                output=String.valueOf(Blife);
            }
        }
        else if(v.getId()==R.id.textViewTeamA_Elf_1_point){
            Temp=0;
            A=false;
            B=false;
            Plus=false;
            Minus=false;
            AEp1=true;
            AEp2=false;
            BEp1=false;
            BEp2=false;
            output=String.valueOf(elfpA1);
            ResetTextColor();
            txv_elfpA1.setTextColor(Color.rgb(255,153,18));
        }
        else if(v.getId()==R.id.textViewTeamA_Elf_2_point){
            Temp=0;
            A=false;
            B=false;
            Plus=false;
            Minus=false;
            AEp1=false;
            AEp2=true;
            BEp1=false;
            BEp2=false;
            output=String.valueOf(elfpA2);
            ResetTextColor();
            txv_elfpA2.setTextColor(Color.rgb(255,153,18));
        }
        else if(v.getId()==R.id.textViewTeamB_Elf_1_point){
            Temp=0;
            A=false;
            B=false;
            Plus=false;
            Minus=false;
            AEp1=false;
            AEp2=false;
            BEp1=true;
            BEp2=false;
            output=String.valueOf(elfpB1);
            ResetTextColor();
            txv_elfpB1.setTextColor(Color.rgb(255,153,18));
        }
        else if(v.getId()==R.id.textViewTeamB_Elf_2_point){
            Temp=0;
            A=false;
            B=false;
            Plus=false;
            Minus=false;
            AEp1=false;
            AEp2=false;
            BEp1=false;
            BEp2=true;
            output=String.valueOf(elfpB2);
            ResetTextColor();
            txv_elfpB2.setTextColor(Color.rgb(255,153,18));
        }
        else if((Plus||Minus)&&(A||B)){
            if(v.getId()==R.id.button0) {
                Temp=Temp*10;
                if(Temp!=0){
                    output=output.concat("0");
                }
            }
            else if(v.getId()==R.id.button1) {
                Temp=Temp*10+1;
                output=output.concat("1");
            }
            else if(v.getId()==R.id.button2) {
                Temp=Temp*10+2;
                output=output.concat("2");
            }
            else if(v.getId()==R.id.button3) {
                Temp=Temp*10+3;
                output=output.concat("3");
            }
            else if(v.getId()==R.id.button4) {
                Temp=Temp*10+4;
                output=output.concat("4");
            }
            else if(v.getId()==R.id.button5) {
                Temp=Temp*10+5;
                output=output.concat("5");
            }
            else if(v.getId()==R.id.button6) {
                Temp=Temp*10+6;
                output=output.concat("6");
            }
            else if(v.getId()==R.id.button7) {
                Temp=Temp*10+7;
                output=output.concat("7");
            }
            else if(v.getId()==R.id.button8) {
                Temp=Temp*10+8;
                output=output.concat("8");
            }
            else if(v.getId()==R.id.button9) {
                Temp=Temp*10+9;
                output=output.concat("9");
            }
            else if(v.getId()==R.id.buttonBACK){
                Temp=Temp/10;
                if(output.contains("-")||output.contains("+")){
                    if(!(output.substring(output.length()-1).contains("-")||output.substring(output.length()-1).contains("+"))){
                        output=output.substring(0,output.length()-1);
                    }
                }
            }
        }

        if(Alife>MaxLife){
            Alife=MaxLife;
        }
        else if(Alife<0){
            Alife=0;
        }
        if(Blife>MaxLife){
            Blife=MaxLife;
        }
        else if(Blife<0){
            Blife=0;
        }

        if(Alife>=125){
            TeamALF.setBackgroundColor(Color.rgb(245,255,250));
        }
        else if(Alife>=60){
            TeamALF.setBackgroundColor(Color.rgb(255,250,205));
        }
        else{
            TeamALF.setBackgroundColor(Color.rgb(255,218,185));
        }
        if(Blife>=125){
            TeamBLF.setBackgroundColor(Color.rgb(245,255,250));
        }
        else if(Blife>=60){
            TeamBLF.setBackgroundColor(Color.rgb(255,250,205));
        }
        else{
            TeamBLF.setBackgroundColor(Color.rgb(255,218,185));
        }
        TeamALF.setText(String.valueOf(Alife));
        TeamBLF.setText(String.valueOf(Blife));
        txv_elfpA1.setText(String.valueOf(elfpA1));
        txv_elfpA2.setText(String.valueOf(elfpA2));
        txv_elfpB1.setText(String.valueOf(elfpB1));
        txv_elfpB2.setText(String.valueOf(elfpB2));
        if(A){
            if(output.length()>6){
                TeamALF.setText(output.substring(output.length()-6));
            }else{
                TeamALF.setText(output);
            }
        }
        else if(B){
            if(output.length()>6){
                TeamBLF.setText(output.substring(output.length()-6));
            }
            else{
                TeamBLF.setText(output);
            }
        }
    }

    @Override
    public boolean onLongClick(View v){
        if(v.getId()==R.id.textViewSurround){
            Spinner srd=(Spinner)findViewById(R.id.spinnerSurround);
            if(srd.getSelectedItemPosition()!=0){
                ResetAdapter(srd);
                Alife-=20;
                Blife-=20;
            }
        }
        else if(v.getId()==R.id.textViewStar){
            Spinner stA=(Spinner) findViewById(R.id.spinnerStarA);
            Spinner stB=(Spinner) findViewById(R.id.spinnerStarB);
            if(stA.getSelectedItemPosition()!=0){
                ResetAdapter(stA);
                Alife-=20;
            }
            if(stB.getSelectedItemPosition()!=0){
                ResetAdapter(stB);
                Blife-=20;
            }
        }
        else if(v.getId()==R.id.textViewElf){
            Spinner elfA1=(Spinner) findViewById(R.id.spinnerTeamA_Elf_1);
            Spinner elfA2=(Spinner) findViewById(R.id.spinnerTeamA_Elf_2);
            Spinner elfB1=(Spinner) findViewById(R.id.spinnerTeamB_Elf_1);
            Spinner elfB2=(Spinner) findViewById(R.id.spinnerTeamB_Elf_2);

            if(elfA1.getSelectedItemPosition()!=0){
                Alife-=20;
                if(elfpA1 >2){
                    elfpA1 -=2;
                }
                else{
                    elfpA1 =0;
                    ResetAdapter(elfA1);
                }
            }
            if(elfA2.getSelectedItemPosition()!=0){
                Alife-=20;
                if(elfpA2 >2){
                    elfpA2 -=2;
                }
                else{
                    elfpA2 =0;
                    ResetAdapter(elfA2);
                }
            }
            if(elfB1.getSelectedItemPosition()!=0){
                Blife-=20;
                if(elfpB1 >2){
                    elfpB1 -=2;
                }
                else{
                    elfpB1 =0;
                    ResetAdapter(elfB1);
                }
            }
            if(elfB2.getSelectedItemPosition()!=0){
                Blife-=20;
                if(elfpB2 >2){
                    elfpB2 -=2;
                }
                else{
                    elfpB2 =0;
                    ResetAdapter(elfB2);
                }
            }
        }
        else if(v.getId()==R.id.TeamALife){
            Alife=Alife/2;
        }
        else if(v.getId()==R.id.TeamBLife){
            Blife=Blife/2;
        }

        if(Alife>=125){
            TeamALF.setBackgroundColor(Color.rgb(245,255,250));
        }
        else if(Alife>=60){
            TeamALF.setBackgroundColor(Color.rgb(255,250,205));
        }
        else{
            TeamALF.setBackgroundColor(Color.rgb(255,218,185));
        }
        if(Blife>=125){
            TeamBLF.setBackgroundColor(Color.rgb(245,255,250));
        }
        else if(Blife>=60){
            TeamBLF.setBackgroundColor(Color.rgb(255,250,205));
        }
        else{
            TeamBLF.setBackgroundColor(Color.rgb(255,218,185));
        }
        TeamALF.setText(String.valueOf(Alife));
        TeamBLF.setText(String.valueOf(Blife));
        txv_elfpA1.setText(String.valueOf(elfpA1));
        txv_elfpA2.setText(String.valueOf(elfpA2));
        txv_elfpB1.setText(String.valueOf(elfpB1));
        txv_elfpB2.setText(String.valueOf(elfpB2));
        return true;
    }

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        Alife=MaxLife;
        Blife=MaxLife;
        Temp=0;
        A=false;
        B=false;
        Plus=false;
        Minus=false;
        output="";

        TeamALF=(TextView)findViewById(R.id.TeamALife);
        TeamBLF=(TextView)findViewById(R.id.TeamBLife);

        txv_elfpA1 =(TextView) findViewById(R.id.textViewTeamA_Elf_1_point);
        txv_elfpA2 =(TextView) findViewById(R.id.textViewTeamA_Elf_2_point);
        txv_elfpB1 =(TextView) findViewById(R.id.textViewTeamB_Elf_1_point);
        txv_elfpB2 =(TextView) findViewById(R.id.textViewTeamB_Elf_2_point);

        TeamALF.setText(String.valueOf(Alife));
        TeamBLF.setText(String.valueOf(Blife));

        SetAdapter();

        Button btnP;
        btnP=(Button) findViewById(R.id.buttonplus);
        btnP.setOnClickListener(this);

        Button btnM;
        btnM=(Button) findViewById(R.id.buttonminus);
        btnM.setOnClickListener(this);

        Button btnBACK;
        btnBACK=(Button) findViewById(R.id.buttonBACK);
        btnBACK.setOnClickListener(this);

        Button btnE;
        btnE=(Button) findViewById(R.id.buttonequal);
        btnE.setOnClickListener(this);

        Button btnRe;
        btnRe=(Button) findViewById(R.id.button_reset250);
        btnRe.setOnClickListener(this);

        Button btnzero;
        btnzero=(Button) findViewById(R.id.button0);
        btnzero.setOnClickListener(this);

        Button btnone;
        btnone=(Button) findViewById(R.id.button1);
        btnone.setOnClickListener(this);

        Button btntwo;
        btntwo=(Button) findViewById(R.id.button2);
        btntwo.setOnClickListener(this);

        Button btnthree;
        btnthree=(Button) findViewById(R.id.button3);
        btnthree.setOnClickListener(this);

        Button btnfour;
        btnfour=(Button) findViewById(R.id.button4);
        btnfour.setOnClickListener(this);

        Button btnfive;
        btnfive=(Button) findViewById(R.id.button5);
        btnfive.setOnClickListener(this);

        Button btnsix;
        btnsix=(Button) findViewById(R.id.button6);
        btnsix.setOnClickListener(this);

        Button btnseven;
        btnseven=(Button) findViewById(R.id.button7);
        btnseven.setOnClickListener(this);

        Button btneight;
        btneight=(Button) findViewById(R.id.button8);
        btneight.setOnClickListener(this);

        Button btnnine;
        btnnine=(Button) findViewById(R.id.button9);
        btnnine.setOnClickListener(this);

        Button btnch;
        btnch=(Button) findViewById(R.id.button_change);
        btnch.setOnClickListener(this);

        txv_elfpA1.setOnClickListener(this);
        txv_elfpA2.setOnClickListener(this);
        txv_elfpB1.setOnClickListener(this);
        txv_elfpB2.setOnClickListener(this);

        TeamALF.setOnClickListener(this);
        TeamALF.setOnLongClickListener(this);
        TeamBLF.setOnClickListener(this);
        TeamBLF.setOnLongClickListener(this);

        TextView srd=(TextView)findViewById(R.id.textViewSurround);
        srd.setOnLongClickListener(this);
        TextView star=(TextView)findViewById(R.id.textViewStar);
        star.setOnLongClickListener(this);
        TextView elf=(TextView)findViewById(R.id.textViewElf);
        elf.setOnLongClickListener(this);

        Spinner TeamA_Elf_1=(Spinner)findViewById(R.id.spinnerTeamA_Elf_1);
        TeamA_Elf_1.setOnItemSelectedListener(this);
        Spinner TeamA_Elf_2=(Spinner)findViewById(R.id.spinnerTeamA_Elf_2);
        TeamA_Elf_2.setOnItemSelectedListener(this);
        Spinner TeamB_Elf_1=(Spinner)findViewById(R.id.spinnerTeamB_Elf_1);
        TeamB_Elf_1.setOnItemSelectedListener(this);
        Spinner TeamB_Elf_2=(Spinner)findViewById(R.id.spinnerTeamB_Elf_2);
        TeamB_Elf_2.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> arg0,View arg1,int position,long arg3){
        if(position!=0){
            if(arg0.getId()==R.id.spinnerTeamA_Elf_1){
                elfpA1=2;
            }
            else if(arg0.getId()==R.id.spinnerTeamA_Elf_2){
                elfpA2=2;
            }
            else if(arg0.getId()==R.id.spinnerTeamB_Elf_1){
                elfpB1=2;
            }
            else if(arg0.getId()==R.id.spinnerTeamB_Elf_2){
                elfpB2=2;
            }
        }
        txv_elfpA1.setText(String.valueOf(elfpA1));
        txv_elfpA2.setText(String.valueOf(elfpA2));
        txv_elfpB1.setText(String.valueOf(elfpB1));
        txv_elfpB2.setText(String.valueOf(elfpB2));
    }
    public void onNothingSelected(AdapterView<?> arg0){
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public void SetAdapter(){
        String elements[]={"無","金","水","木","火","土"};
        ArrayAdapter<String>element=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elements);
        element.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner srd=(Spinner) findViewById(R.id.spinnerSurround);
        Spinner stA=(Spinner) findViewById(R.id.spinnerStarA);
        Spinner stB=(Spinner) findViewById(R.id.spinnerStarB);
        Spinner elfA1=(Spinner) findViewById(R.id.spinnerTeamA_Elf_1);
        Spinner elfA2=(Spinner) findViewById(R.id.spinnerTeamA_Elf_2);
        Spinner elfB1=(Spinner) findViewById(R.id.spinnerTeamB_Elf_1);
        Spinner elfB2=(Spinner) findViewById(R.id.spinnerTeamB_Elf_2);

        srd.setAdapter(element);
        stA.setAdapter(element);
        stB.setAdapter(element);
        elfA1.setAdapter(element);
        elfA2.setAdapter(element);
        elfB1.setAdapter(element);
        elfB2.setAdapter(element);
    }
    public void ResetAdapter(Spinner spin){
        String elements[]={"無","金","水","木","火","土"};
        ArrayAdapter<String>element=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elements);
        element.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(element);
    }
    public void ResetAllAdapter(){
        Spinner srd=(Spinner) findViewById(R.id.spinnerSurround);
        Spinner stA=(Spinner) findViewById(R.id.spinnerStarA);
        Spinner stB=(Spinner) findViewById(R.id.spinnerStarB);
        Spinner elfA1=(Spinner) findViewById(R.id.spinnerTeamA_Elf_1);
        Spinner elfA2=(Spinner) findViewById(R.id.spinnerTeamA_Elf_2);
        Spinner elfB1=(Spinner) findViewById(R.id.spinnerTeamB_Elf_1);
        Spinner elfB2=(Spinner) findViewById(R.id.spinnerTeamB_Elf_2);

        ResetAdapter(srd);
        ResetAdapter(stA);
        ResetAdapter(stB);
        ResetAdapter(elfA1);
        ResetAdapter(elfA2);
        ResetAdapter(elfB1);
        ResetAdapter(elfB2);
    }
    public void ResetTextColor(){
        TeamALF.setTextColor(Color.rgb(0,0,0));
        TeamBLF.setTextColor(Color.rgb(0,0,0));
        txv_elfpA1.setTextColor(Color.rgb(0,0,0));
        txv_elfpA2.setTextColor(Color.rgb(0,0,0));
        txv_elfpB1.setTextColor(Color.rgb(0,0,0));
        txv_elfpB2.setTextColor(Color.rgb(0,0,0));
    }
    public native String stringFromJNI();
}
