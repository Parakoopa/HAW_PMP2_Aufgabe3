package kalender.marco;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Uhrzeit;
import kalender.interfaces.Woche;

public class DatumImpl implements Datum {


	private Calendar intern;
	
	public DatumImpl(Tag tag){
		this(tag, new UhrzeitImpl());
	}

	public DatumImpl(Tag tag, Uhrzeit uhrzeit ) {
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR, tag.getJahr());
		intern.set(Calendar.MONTH, tag.getMonat());
		intern.set(Calendar.DAY_OF_MONTH, tag.getTagImMonat());
		intern.set(Calendar.HOUR_OF_DAY, uhrzeit.getStunde());
		intern.set(Calendar.MINUTE, uhrzeit.getMinuten());
	}

	public DatumImpl(Datum d) {
		this(d.getTag(), d.getUhrzeit());
	}

	private DatumImpl(Calendar intern) {
		this.intern = intern;
	}
	
	

	public int compareTo(Datum o) {
		return this.abstand(o).inMinuten();
	}


	public Tag getTag() {
		return new TagImpl(getJahr(), getMonatImJahr(), getTagImMonat());
	}


	public Woche getWoche() {
		return new WocheImpl(getJahr(), getMonatImJahr(), getWocheImMonat());
	}


	public Monat getMonat() {
		return new MonatImpl(getJahr(), getMonatImJahr());
	}


	public Uhrzeit getUhrzeit() {
		return new UhrzeitImpl(intern.get(Calendar.HOUR_OF_DAY), intern.get(Calendar.MINUTE));
	}


	public int getJahr() {
		return intern.get(Calendar.YEAR);
	}


	public int getTagImMonat() {
		return intern.get(Calendar.DAY_OF_MONTH);
	}


	public int getTagImJahr() {
		return intern.get(Calendar.DAY_OF_YEAR);
	}


	public int getWocheImMonat() {
		return intern.get(Calendar.WEEK_OF_MONTH);
	}


	public int getWocheImJahr() {
		return intern.get(Calendar.WEEK_OF_YEAR);
	}


	public int getMonatImJahr() {
		return intern.get(Calendar.MONTH);
	}


	public Datum add(Dauer dauer) {
		int stundeVorher = getUhrzeit().getStunde();
		intern.add(Calendar.MINUTE, dauer.inMinuten());
		// Zeitumstellung muss beachtet werden, damit Wiederholungen korrekt arbeiten.
		// Es wird nur einfache deutsche Zeitumstellung ausgeglichen.
		if (getUhrzeit().getStunde() == stundeVorher + 1) {
			intern.add(Calendar.MINUTE, -60);
		} else if (getUhrzeit().getStunde() == stundeVorher - 1) {
			intern.add(Calendar.MINUTE, 60);
		}
		return new DatumImpl(this);
	}


	public Datum sub(Dauer dauer) {
		int stundeVorher = getUhrzeit().getStunde();
		intern.add(Calendar.MINUTE, -dauer.inMinuten());
		// Zeitumstellung muss beachtet werden, damit Wiederholungen korrekt arbeiten.
		// Es wird nur einfache deutsche Zeitumstellung ausgeglichen.
		if (getUhrzeit().getStunde() == stundeVorher + 1) {
			intern.add(Calendar.MINUTE, -60);
		} else if (getUhrzeit().getStunde() == stundeVorher - 1) {
			intern.add(Calendar.MINUTE, 60);
		}
		return new DatumImpl(this);
	}


	public Dauer abstand(Datum d) {
		return new DauerImpl(this.inMinuten()-d.inMinuten());
	}


	public long differenzInTagen(Datum d) {
		return getTag().differenzInTagen(d.getTag());
	}


	public int inMinuten() {
		return (int)(intern.getTimeInMillis() * .001 / 60);
	}


	public Calendar inBasis() {
		return (Calendar) intern.clone();
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

	@Override
	public String toString() {
		return "Datum{"+getTagImMonat()+"."+(getMonatImJahr()+1)+"."+getJahr()+" "+getUhrzeit().getStunde()+":"+getUhrzeit().getMinuten()+"}";
	}
}
