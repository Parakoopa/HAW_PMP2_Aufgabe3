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
		this(new GregorianCalendar(
				tag.getJahr(),
				tag.getMonat(),
				tag.getTagImMonat(),
				0,
				0
		));
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


	public Tag getTag() {return this.getTag();}
	public Woche getWoche() {return this.getWoche();}
	public Monat getMonat() {return this.getMonat()}
	public Uhrzeit getUhrzeit() {return this.getUhrzeit();}


	public int getJahr() {return this.getJahr();}
	public int getTagImMonat() {return this.getTagImMonat();}
	public int getTagImJahr() {return this.getTagImJahr();}
	public int getWocheImMonat() {return this.getWocheImMonat();}
	public int getWocheImJahr() {return this.getWocheImJahr();}
	public int getMonatImJahr() {return this.getMonatImJahr();}


	public Datum add(Dauer dauer) {return this.add(dauer);}
	public Datum sub(Dauer dauer) {return this.sub(dauer);}
	public Dauer abstand(Datum d) {return this.abstand(d);}


	public long differenzInTagen(Datum d) {return this.differenzInTagen(d);}

	public int inMinuten() {return this.inMinuten();}

	public Calendar inBasis() {return this.inBasis();}

}
