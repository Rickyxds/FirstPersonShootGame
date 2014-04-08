package com.example.firstpersonshootergame;

import android.app.Application;

public class Globals extends Application{
	
	private String resultadoFinal = "Player   Award     Assassinatos   Mortes\n";
	
	
	

	public String getResultadoFinal() {
		return resultadoFinal;
	}

	public void setResultadoFinal(String resultadoFinal) {
		this.resultadoFinal = resultadoFinal;
	}

}
