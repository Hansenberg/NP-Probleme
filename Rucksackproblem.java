package np;

public class Rucksackproblem {
	
	public static void main(String[] args){
		int b = 100;
		int[] w = {3, 2, 4};
		int[] v = {2, 3, 1};
		System.out.println("Gesamtgewicht: " + dynamisch(b, w, v));
	}
	
	public static int dynamisch(int b, int[] w, int[] v){
		
		//Demonstration für exponentielle Laufzeit
		int counter = 0;
		
		int n = w.length;
		//Array speichert Nr. der Gewichte, die im jeweiligen Platz in r liegen
		String[][] el = new String[n+1][b];
		//maximale Nutzwert mit allen Elementen bis i bei Höchstgewicht von j (r[i][j])
		int[][] r = new int[n+1][b];  
		
		//Iteration Elemente: Alle Elemente durchgehen, letztes bis erstes (Reihenfolge egal)
		for(int i = n-1; i >= 0; i--){ 
			
			//Iteration Höchstgewichte: bestimmt den größten Nutzen bei allen Gewichten bisher bei jeweiligem Gesamtgewicht
			for(int j = 0; j < b; j++){
				
				//Yay, Counter!
				counter++;
				
				//Liegt das Gewicht des aktuellen Elements innerhalb der aktuellen Beschränkungen?
				if(w[i] <= j){ 
					
					//Bestimmung größter Wert:
					//Wert des vorherigen Elements, oder
					//Wert d. Elements + Wert vom vorherigen Element bei Gesamtgewicht minus aktuelles Gewicht
					r[i][j] = Math.max(v[i] + r[i+1][j-w[i]], r[i+1][j]); 
					
					//Speichern der jeweiligen Gewichtnr.
					if(v[i] + r[i+1][j-w[i]] > r[i+1][j]){
						el[i][j] = el[i+1][j-w[i]] + ", " + i;
					} else{
						el[i][j] = el[i+1][j];
					}
					
				}
				else{ 
					
					//Speichern der jeweiligen Gewichtnr.
					el[i][j] = el[i+1][j];
					//Gewicht d. Elements überschreitet Beschränkung
					r[i][j] = r[i+1][j]; //Für alle Gewichte wird der Wert für das vorherige Element übernommen
				
				}
			}
		}
		//Yay, Counter!
		System.out.println("Counter: " + counter);
		
		//String.substring() wegen "null, " am Anfang
		System.out.println("Gewichte: " + el[0][b-1].substring(6));
		
		//pseudopolynomiell, da Laufzeit polynomiell von b abhängt
		//Größe von b ist exponentiell von Länge von b abhängig --> exponentielle Laufzeit
		//bei beendeter Iteration höchster Wert bei höchstem Gewicht bestimmt (Math.max)
		return r[0][b-1];
	}

}
