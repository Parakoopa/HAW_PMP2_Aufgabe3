package kalender.tim;

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
	} //but why?


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


	public int compareTo(Tag o) {return (int)this.differenzInTagen(o);}


	public int getJahr() {
		return this.intern.get(Calendar.YEAR);
	}


	public int getMonat() {
		return this.intern.get(Calendar.MONTH);
	}


	public int getTagImJahr() {
		return this.intern.get(Calendar.DAY_OF_YEAR);
	}


	public int getTagImMonat() {
		return this.intern.get(Calendar.DAY_OF_MONTH);
	}


	public long differenzInTagen(Tag other) {
		Calendar otherCalendar = other.inBasis();
		return (long) ((this.intern.getTimeInMillis() - otherCalendar.getTimeInMillis()) * 0.001 / 60 / 60 / 24);
	}


	public Calendar inBasis() {
		Calendar copy = (Calendar) intern.clone();
		return copy;
	}

}
