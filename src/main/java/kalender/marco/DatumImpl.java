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
		this(new GregorianCalendar(
				tag.getJahr(),
				tag.getMonat(),
				tag.getTagImMonat(),
				0,
				0
		));
		// Kann auch durch
		//this(tag, new UhrzeitImpl());
		// ersetzt werden, aber würde dann Impl Klasse nutzen.
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
		// TODO Auto-generated method stub
		return null;
	}


	public Woche getWoche() {
		// TODO Auto-generated method stub
		return null;
	}


	public Monat getMonat() {
		// TODO Auto-generated method stub
		return null;
	}


	public Uhrzeit getUhrzeit() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getJahr() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getTagImMonat() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getTagImJahr() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getWocheImMonat() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getWocheImJahr() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getMonatImJahr() {
		// TODO Auto-generated method stub
		return 0;
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
