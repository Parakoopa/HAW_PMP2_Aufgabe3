package kalender.marco;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Tag;

public class TagImpl implements Tag {

	private Calendar intern; 
	
	public TagImpl(int jahr, int tagImJahr) {
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR, jahr);
		intern.set(Calendar.DAY_OF_YEAR, tagImJahr);
	}
	public TagImpl(int jahr, int monat, int tagImMonat) {
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR, jahr);
		intern.set(Calendar.MONTH, monat);
		intern.set(Calendar.DAY_OF_MONTH, tagImMonat);
	}
	
	public TagImpl(Tag tag) {
		this(tag.getJahr(), tag.getTagImJahr());
	}


	public Datum getStart() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.HOUR_OF_DAY, 0);
		copy.set(Calendar.MINUTE, 0);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)),
				new UhrzeitImpl(copy.get(Calendar.HOUR_OF_DAY),copy.get(Calendar.MINUTE)));
	}


	public Datum getEnde() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.HOUR_OF_DAY, copy.getActualMaximum(Calendar.HOUR_OF_DAY));
		copy.set(Calendar.MINUTE, copy.getActualMaximum(Calendar.MINUTE));
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)),
				new UhrzeitImpl(copy.get(Calendar.HOUR_OF_DAY),copy.get(Calendar.MINUTE)));
	}


	public int compareTo(Tag o) {
		return (int)this.differenzInTagen(o);
	}


	public int getJahr() {
		return intern.get(Calendar.YEAR);
	}


	public int getMonat() {
		return intern.get(Calendar.MONTH);
	}


	public int getTagImJahr() {
		return intern.get(Calendar.DAY_OF_YEAR);
	}


	public int getTagImMonat() {
		return intern.get(Calendar.DAY_OF_MONTH);
	}


	public long differenzInTagen(Tag other) {
		return (long) ((intern.getTimeInMillis() - other.inBasis().getTimeInMillis()) * 0.001 / 60 / 60 / 24);
	}


	public Calendar inBasis() {
		return (Calendar) intern.clone();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TagImpl tag = (TagImpl) o;

		return intern != null ? intern.equals(tag.intern) : tag.intern == null;

	}

	@Override
	public int hashCode() {
		return intern != null ? intern.hashCode() : 0;
	}
}
