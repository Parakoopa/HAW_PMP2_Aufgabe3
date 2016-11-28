package kalender;

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

	public DauerImpl(int minuten) { //22300
		this.minuten = minuten;
	}

	public DauerImpl(int stunden, int minuten) {//
		this(stunden * 60 + minuten);
	}

	public DauerImpl(int tage, int stunden, int minuten) {
		this(tage * 24 + stunden, minuten);
	}

	@Override
	public int compareTo(Dauer o) {
		return this.inMinuten() - o.inMinuten();
	}

	@Override
	public int inMinuten() {
		return minuten;
	}

	@Override
	public int inStunden() {
		return this.minuten/60;
	}

	@Override
	public int inTagen() {
		return this.inStunden()/24;
	}

	@Override
	public int inWochen() {
		return this.inTagen()/7;
	}

	@Override
	public int anteilMinuten() {
		return this.minuten % 60;
	}

	@Override
	public int anteilStunden() {
		return new DauerImpl(this.inStunden() % 24, 0).inMinuten();
	}

	@Override
	public int anteilTage() {
		return new DauerImpl(this.inTagen() % 7, 0, 0).inMinuten();
	}

	@Override
	public int anteilWochen() {
		return this.inWochen() % 30 * 7 * 24 * 60; // somehow get real month lenghts
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
