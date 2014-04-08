package com.example.firstpersonshootergame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Sampler.Value;
import android.app.Activity;
import android.content.Intent;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	
	ArrayList<String> players = new ArrayList<String>();
	ArrayList<Integer> assasinatos = new ArrayList<Integer>(); 
	ArrayList<Integer> mortes = new ArrayList<Integer>();
	ArrayList<String> armas = new ArrayList<String>();
	ArrayList<String> award = new ArrayList<String>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    public void clickCarregar(View v){
    	
    	
    	
    	EditText nomeArquivo = (EditText) findViewById(R.id.editText1);
       	
    	String nomeArq = nomeArquivo.getText().toString();
    	
    	File arq; 
        String lstrlinha;
         
         try{
                     	 
            arq = new File(Environment.getExternalStorageDirectory(), nomeArq);
            BufferedReader br = new BufferedReader(new FileReader(arq));
            
            String txtLer = ("");
            //monta um arquivo conhecido com todo o log
   
            while ((lstrlinha = br.readLine()) != null) {
            	
            	if (!txtLer.toString().equals("")){
                      txtLer = txtLer+"\n";
                }
                  txtLer = txtLer + lstrlinha;
                  
            	String [] infos = lstrlinha.split("\\s+");
            			
                //Obter o nome dos Players  
            	if(lstrlinha.contains("New match") || lstrlinha.contains("has ended")){
               	  
          	  	}else{  
                 
          	  		if(players.contains(infos[3]) || infos[3].equals("<WORLD>")){
          	  			
          	  		}else{
          	  			players.add(infos[3]);
          	  		}
          	  		
          	  		if(players.contains(infos[5]) || infos[3].equals("<WORLD>")){
          	  			
          	  		}else{
          	  			players.add(infos[5]);
          	  		}
          	  	}
            	
            }
                  
            //contar o numero de assassinatos e mortes que cada player teve
      	  	int numeroP = players.size();
      	  	int i = 0;
      	  	String nomePA;
      	  	String nomePM;
      	  
      	  	while(i < numeroP){
      	  		
      	  		nomePA = " - "+players.get(i);
      	  		nomePM = "killed "+players.get(i); 
      	  		
      	  		assasinatos.add(txtLer.split(nomePA, -1).length-1);
      	  		mortes.add(txtLer.split(nomePM, -1).length-1);
      	  		
      	  		if(txtLer.split(nomePM, -1).length-1 == 0){
      	  			award.add("_____*");
      	  		}else{
      	  			award.add("______");
      	  		}
      	  		i++;
      	  	}
      	    Globals g = (Globals)getApplication();
      	  	int space = 17;
      	  	
      	  	for(int j = 0; j < numeroP; j++){
      	  		String aux = players.get(j);
      	  		aux = aux + award.get(j);
      	  		for(int s = 0; s < space - players.get(j).length(); s++){
      	  			aux += "_"; 
      	  		}
      	  		
      	  		g.setResultadoFinal(g.getResultadoFinal()+aux+assasinatos.get(j)+"__________"+mortes.get(j)+("\n"));
      	  	}
      	  	
      	  	Intent myIntent = new Intent(this, ResultActivity.class);
      	   	this.startActivity(myIntent);
      	  	
          } 
          catch (Exception e){
        
          }        
    }
}
