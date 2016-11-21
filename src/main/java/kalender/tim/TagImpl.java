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
		return new DatumImpl(0,0);
	}


	public Datum getEnde() {
		// TODO Auto-generated method stub
		return null;
	}


	public int compareTo(Tag o) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getJahr() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getMonat() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getTagImJahr() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getTagImMonat() {
		// TODO Auto-generated method stub
		return 0;
	}


	public long differenzInTagen(Tag other) {
		// TODO Auto-generated method stub
		return 0;
	}


	public Calendar inBasis() {
		// TODO Auto-generated method stub
		return null;
	}

}
