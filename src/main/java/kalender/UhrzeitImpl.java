package kalender;

import kalender.interfaces.Uhrzeit;

import java.util.Calendar;

public class UhrzeitImpl implements Uhrzeit {
	private Calendar intern;

	public UhrzeitImpl(){
		this(0,0);
	}
	
	public UhrzeitImpl(int stunde, int minute) {
		if (stunde < 0 || stunde > 24 || (stunde == 24 && minute != 0) || minute < 0 || minute > 59) {
			throw new IllegalArgumentException("Intervall fuer Stunde [0,24), fuer Minute [0,59] oder 24:00");
		}
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.HOUR_OF_DAY, stunde);
		intern.set(Calendar.MINUTE, minute);
	}

	public UhrzeitImpl(Uhrzeit o) {
		this(o.getStunde(), o.getMinuten());
	}


	public int compareTo(Uhrzeit o) {
		return (getStunde()*60 + getMinuten()) - (o.getStunde()*60 + o.getMinuten());
	}


	public int getStunde() {
		return this.intern.get(Calendar.HOUR_OF_DAY);
	}


	public int getMinuten() {
		return this.intern.get(Calendar.MINUTE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UhrzeitImpl uhrzeit = (UhrzeitImpl) o;

		return intern != null ? intern.equals(uhrzeit.intern) : uhrzeit.intern == null;

	}

	@Override
	public int hashCode() {
		return intern != null ? intern.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Uhrzeit{"+getStunde()+":"+getMinuten()+"}";
	}
}
