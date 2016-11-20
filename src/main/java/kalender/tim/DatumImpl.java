package kalender.tim;

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
	}
	public DatumImpl(Tag tag, Uhrzeit uhrzeit ) {
	}

	public DatumImpl(Datum d) {
	}

	private DatumImpl(Calendar intern) {
	}
	
	

	public int compareTo(Datum o) {
		// TODO Auto-generated method stub
		return 0;
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
