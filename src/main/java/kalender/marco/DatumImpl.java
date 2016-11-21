package kalender.marco;

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
		this(tag, new UhrzeitImpl());
	}

	public DatumImpl(Tag tag, Uhrzeit uhrzeit ) {
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR, tag.getJahr());
		intern.set(Calendar.MONTH, tag.getMonat());
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
		// TODO Auto-generated method stub
		return null;
	}


	public Datum sub(Dauer dauer) {
		// TODO Auto-generated method stub
		return null;
	}


	public Dauer abstand(Datum d) {
		// TODO Auto-generated method stub
		return null;
	}


	public long differenzInTagen(Datum d) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int inMinuten() {
		// TODO Auto-generated method stub
		return 0;
	}


	public Calendar inBasis() {
		// TODO Auto-generated method stub
		return null;
	}

}
