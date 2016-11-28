package kalender.tim;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Uhrzeit;
import kalender.interfaces.Woche;

public class DauerImpl implements Dauer {

	private int minuten;
	
	public DauerImpl(Datum d1, Datum d2) {
	}

	public DauerImpl(int minuten) {
	}
	
	public DauerImpl(int stunden, int minuten) {
	}

	public DauerImpl(int tage, int stunden, int minuten) {
	}


	public int compareTo(Dauer o) {
		return this.minuten - o.inMinuten();
	}


	public int inMinuten() {
		return this.minuten;
	}

	public int inStunden() {
		return this.minuten/60;
	}


	public int inTagen() {
		return this.inStunden()/24;
	}


	public int inWochen() {
		return this.inTagen()/7;
	}


	public int anteilMinuten() {
		return this.minuten%60;
	}


	public int anteilStunden() {
		return this.inStunden()%24;
	}


	public int anteilTage() {
		return this.inTagen()%7;
	}


	public int anteilWochen() {
		return this.inTagen()%54; //somehow get real month lenghts
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || this.getClass() != other.getClass()) {
			return false;
		}
		DauerImpl otherDauer = (DauerImpl) other;
		return minuten == otherDauer.minuten;
	}
}
