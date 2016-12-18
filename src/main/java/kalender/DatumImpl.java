package kalender;

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
	
	
	@Override
	public int compareTo(Datum o) {
		return this.abstand(o).inMinuten();
	}

	@Override
	public Tag getTag() {
		return new TagImpl(getJahr(), getMonatImJahr(), getTagImMonat());
	}

	@Override
	public Woche getWoche() {
		return new WocheImpl(getJahr(), getMonatImJahr(), getWocheImMonat());
	}

	@Override
	public Monat getMonat() {
		return new MonatImpl(getJahr(), getMonatImJahr());
	}

	@Override
	public Uhrzeit getUhrzeit() {
		return new UhrzeitImpl(this.intern.get(Calendar.HOUR_OF_DAY), this.intern.get(Calendar.MINUTE));
	}

	@Override
	public int getJahr() {
		return intern.get(Calendar.YEAR);
	}

	@Override
	public int getTagImMonat() {
		return intern.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public int getTagImJahr() {
		return intern.get(Calendar.DAY_OF_YEAR);
	}

	@Override
	public int getWocheImMonat() {
		return intern.get(Calendar.WEEK_OF_MONTH);
	}

	@Override
	public int getWocheImJahr() {
		return intern.get(Calendar.WEEK_OF_YEAR);
	}

	@Override
	public int getMonatImJahr() {
		return intern.get(Calendar.MONTH);
	}

	@Override
	public Datum add(Dauer dauer) {
		this.intern.add(Calendar.MINUTE,dauer.inMinuten());
		return new DatumImpl(this);
	}

	@Override
	public Datum sub(Dauer dauer) {
		this.intern.add(Calendar.MINUTE,-dauer.inMinuten());
		return new DatumImpl(this);
	}

	@Override
	public Dauer abstand(Datum d) {
		return new DauerImpl(this.inMinuten()-d.inMinuten());
	}

	@Override
	public long differenzInTagen(Datum d) {
		return getTag().differenzInTagen(d.getTag());
	}

	@Override
	public int inMinuten() {
		return (int)(intern.getTimeInMillis() * .001 / 60);
	}

	@Override
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
		return ""+getTagImMonat()+"."+(getMonatImJahr()+1)+"."+getJahr()+" "+getUhrzeit().getStunde()+":"+getUhrzeit().getMinuten()+" Uhr";
	}
}
