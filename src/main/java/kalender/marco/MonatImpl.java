package kalender.marco;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Monat;

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
		copy.set(Calendar.DAY_OF_MONTH, copy.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)));
	}


	public int getMonat() {
		return intern.get(Calendar.MONTH);
	}


	public int getJahr() {
		return intern.get(Calendar.YEAR);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MonatImpl monat = (MonatImpl) o;

		return intern != null ? intern.equals(monat.intern) : monat.intern == null;

	}

	@Override
	public int hashCode() {
		return intern != null ? intern.hashCode() : 0;
	}
}
