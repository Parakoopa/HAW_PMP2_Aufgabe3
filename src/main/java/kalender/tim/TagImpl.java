package kalender.tim;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Tag;

public class TagImpl implements Tag {

	private Calendar intern; 
	
	public TagImpl(int jahr, int tagImJahr) {
	}
	public TagImpl(int jahr, int monat, int tagImMonat) {
	}
	
	public TagImpl(Tag tag) {
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


	public int compareTo(Datum o) {return this.compareTo(o);}


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
		Calendar otherCalendar  = Calendar.getInstance();
		otherCalendar.clear();
		intern.set(Calendar.DAY_OF_YEAR, other.getTagImJahr()); //too complicated?
		return this.intern.getTimeInMillis() - otherCalendar.getTimeInMillis();
	}


	public Calendar inBasis() {
		Calendar copy = (Calendar) intern.clone();
		return copy;
	}

}
