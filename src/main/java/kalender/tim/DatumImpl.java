package kalender.tim;

import java.util.Calendar;
import java.util.GregorianCalendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Uhrzeit;
import kalender.interfaces.Woche;

public class DatumImpl implements Datum {


	private Calendar intern;
	
	public DatumImpl(Tag tag){
		this.intern = new GregorianCalendar(
				tag.getJahr(),
				tag.getMonat(),
				tag.getTagImMonat(),
				0,
				0
		);
	}
	public DatumImpl(Tag tag, Uhrzeit uhrzeit ) {
		this(new GregorianCalendar(
				tag.getJahr(),
				tag.getMonat(),
				tag.getTagImMonat(),
				uhrzeit.getStunde(),
				uhrzeit.getMinuten()
		));
	}

//why?
	public DatumImpl(Datum d) {this(d.getTag(),d.getUhrzeit());}

	private DatumImpl(Calendar intern) {this.intern = intern;}

	public int compareTo(Datum o) {return this.abstand(o).inMinuten();}


	public Tag getTag() {
		Tag tag = new Tag(this.intern.get(Calendar.DAY_OF_YEAR));
		return tag;
	}
	public Woche getWoche() {
		Woche woche = new Woche(this.intern.get(Calendar.WEEK_OF_YEAR));
	}
	public Monat getMonat() {
		Monat monat = new Monat(this.intern.get(Calendar.MONTH));
	}
	public Uhrzeit getUhrzeit() {
		Uhrzeit uhrzeit = new Uhrzeit(this.intern.get(Calendar.AM_PM));
	}

	public int getJahr() {return this.intern.get(Calendar.YEAR);}
	public int getTagImMonat() {return this.intern.get(Calendar.DAY_OF_MONTH);}
	public int getTagImJahr() {return this.intern.get(Calendar.DAY_OF_YEAR);}
	public int getWocheImMonat() {return this.intern.get(Calendar.WEEK_OF_MONTH);}
	public int getWocheImJahr() {return this.intern.get(Calendar.WEEK_OF_YEAR);}
	public int getMonatImJahr() {return this.intern.get(Calendar.MONTH);}


	public Datum add(Dauer dauer) {
		return new Dauer(this.inBasis() + dauer.inMinuten());

	}
	public Datum sub(Dauer dauer) {return this.sub(dauer);}
	public Dauer abstand(Datum d) {return this.abstand(d);}


	public long differenzInTagen(Datum d) {
		return this.intern.getTimeInMillis() - d.getTimeInMillis();
	}

	public int inMinuten() {
		return this.intern.;
	}

	public Calendar inBasis() {
		return Calendar copy = (Calendar) intern.clone();

	}

}
