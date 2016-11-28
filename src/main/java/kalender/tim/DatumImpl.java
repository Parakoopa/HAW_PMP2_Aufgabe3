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
		return new TagImpl(this.getTagImJahr(), this.getWocheImJahr(), this.getMonatImJahr());
	}
	public Woche getWoche() {
		return new WocheImpl(getTagImJahr(), getMonatImJahr(), getTagImMonat());
	}
	public Monat getMonat() {
		return new MonatImpl(getMonatImJahr(), getTagImMonat());
	}
	public Uhrzeit getUhrzeit() {
		return new UhrzeitImpl(this.intern.get(Calendar.HOUR_OF_DAY), this.intern.get(Calendar.MINUTE));
	}

	public int getJahr() {return this.intern.get(Calendar.YEAR);}
	public int getTagImMonat() {return this.intern.get(Calendar.DAY_OF_MONTH);}
	public int getTagImJahr() {return this.intern.get(Calendar.DAY_OF_YEAR);}
	public int getWocheImMonat() {return this.intern.get(Calendar.WEEK_OF_MONTH);}
	public int getWocheImJahr() {return this.intern.get(Calendar.WEEK_OF_YEAR);}
	public int getMonatImJahr() {return this.intern.get(Calendar.MONTH);}


	public Datum add(Dauer dauer) {
		this.intern.add(Calendar.MINUTE,dauer.inMinuten());
		return  new DatumImpl(this);

	}
	public Datum sub(Dauer dauer) {return this.sub(dauer);}
	public Dauer abstand(Datum d) {return this.abstand(d);}


	public long differenzInTagen(Datum d) {
		return this.intern.get(Calendar.DAY_OF_YEAR) - d.getTagImJahr();
	}

	public int inMinuten() {
		return this.intern.get(Calendar.MINUTE);
	}//use Dauer.inMinuten instead?

	public Calendar inBasis() {
		Calendar copy = (Calendar) intern.clone();
		return copy;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DatumImpl datum = (DatumImpl) o;

		return intern != null ? intern.equals(datum.intern) : datum.intern == null;

	}

	@Override
	public int hashCode() {
		return intern != null ? intern.hashCode() : 0;
	}

}
