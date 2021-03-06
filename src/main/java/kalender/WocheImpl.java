package kalender;

import kalender.interfaces.Datum;
import kalender.interfaces.Woche;

import java.util.Calendar;

public class WocheImpl implements Woche {

	private Calendar intern;

	public WocheImpl(int jahr, int monat, int wocheImMonat) {
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR, jahr);
		intern.set(Calendar.MONTH, monat);
		intern.set(Calendar.WEEK_OF_MONTH, wocheImMonat);
	}


	public int getJahr() {
		return intern.get(Calendar.YEAR);
	}


	public int getMonat() {
		return intern.get(Calendar.MONTH);
	}


	public int getWocheImMonat() {
		return intern.get(Calendar.WEEK_OF_MONTH);
	}


	public int getWocheImJahr() {
		return intern.get(Calendar.WEEK_OF_YEAR);
	}


	public Datum getStart() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)));
	}


	public Datum getEnde() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)),
				new UhrzeitImpl(23, 59));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		WocheImpl woche = (WocheImpl) o;

		return intern != null ? intern.equals(woche.intern) : woche.intern == null;

	}

	@Override
	public int hashCode() {
		return intern != null ? intern.hashCode() : 0;
	}

	public String toString() {
		return String.format("Woche %d,%d.%d [" + getStart() + "," + getEnde() + "]", getWocheImMonat(), getMonat() + 1,
				getJahr());
	}

}
