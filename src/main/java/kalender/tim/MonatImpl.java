package kalender.tim;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Uhrzeit;
import kalender.interfaces.Woche;

public class MonatImpl implements Monat {

	private Calendar intern;

	public MonatImpl(int jahr, int monat) {
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR, jahr);
		intern.set(Calendar.MONTH, monat);
	}


	public Datum getStart() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.DAY_OF_MONTH, 1);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)));

	}

	public Datum getEnde() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.DAY_OF_MONTH, intern.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)),
				new UhrzeitImpl(23, 59));
	}

	public int getMonat() {
		return this.intern.get(Calendar.MONTH);
	}


	public int getJahr() {
		return this.intern.get(Calendar.YEAR);
	}

}
