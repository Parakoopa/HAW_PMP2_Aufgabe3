package kalender.marco;

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
		this(d1.inMinuten() - d2.inMinuten());
	}

	public DauerImpl(int minuten) {
		this.minuten = minuten;
	}
	
	public DauerImpl(int stunden, int minuten) {
		this(stunden * 60 + minuten);
	}

	public DauerImpl(int tage, int stunden, int minuten) {
		this(tage * 24 + stunden, minuten);
	}


	public int compareTo(Dauer o) {
		return this.inMinuten() - o.inMinuten();
	}


	public int inMinuten() {
		return minuten;
	}


	public int inStunden() {
		return minuten / 60;
	}


	public int inTagen() {
		return inStunden() / 24;
	}


	public int inWochen() {
		return inTagen() / 7;
	}


	public int anteilMinuten() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int anteilStunden() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int anteilTage() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int anteilWochen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DauerImpl dauer = (DauerImpl) o;

		return minuten == dauer.minuten;

	}

	@Override
	public int hashCode() {
		return minuten;
	}

	@Override
	public String toString() {
		return "DauerImpl{" +
				"minuten=" + minuten +
				'}';
	}
}
