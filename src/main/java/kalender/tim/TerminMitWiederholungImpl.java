package kalender.tim;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import kalender.WiederholungType;
import kalender.interfaces.Datum;
import kalender.interfaces.DatumsGroesse;
import kalender.interfaces.Dauer;
import kalender.interfaces.IntervallIterator;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminMitWiederholung;
import kalender.interfaces.Wiederholung;
import kalender.interfaces.Woche;

public class TerminMitWiederholungImpl extends TerminImpl implements TerminMitWiederholung {

	private Wiederholung wdh;

	public TerminMitWiederholungImpl(String beschreibung, Datum start, Dauer dauer, WiederholungType type, int anzahl,
									 int zyklus) {
		super(beschreibung, start, dauer);
		this.wdh = new WiederholungImpl(type, anzahl, zyklus);
	}

	public TerminMitWiederholungImpl(String beschreibung, Datum start, Dauer dauer, Wiederholung wdh) {
		super(beschreibung, start, dauer);
		this.wdh = wdh;
	}

	
	
	public Wiederholung getWdh() {
		return this.wdh;
	}



	public Map<Datum, Termin> termineIn(Monat monat) {
		return  termineFuer(monat);

	}


	public Map<Datum, Termin> termineIn(Woche woche) {
		return  termineFuer(woche);
	}


	public Map<Datum, Termin> termineAn(Tag tag) {
		return  termineFuer(tag);
	}

	
	/**
	 * Beispiel für den naiven Iterator, der alle Wiederholungen explizit aufzaehlt
	 */

	public Iterator<Termin> iterator() {
		return new Iterator<Termin>() {
			private TerminMitWiederholung current = null;
			private int howManySeen = 0;


			public boolean hasNext() {
				return howManySeen <= wdh.anzahl();
			}


			public Termin next() {
				if (current == null) {
					current = TerminMitWiederholungImpl.this;
				} else {
					current = new TerminMitWiederholungImpl(getBeschreibung(), current.getWdh().naechstesDatum(),
							getDauer(), current.getWdh().sub(1));
				}
				howManySeen += 1;
				return current;
			}
		};
	}



	public IntervallIterator<Datum> intervallIterator(int von, int bis) {
			return new IntervallIterator<Datum>() {

				public boolean hasNext() {
					if(von <= bis) {
						return (von < TerminMitWiederholungImpl.this.getWdh().maxIntervallIndex());
					}
					return false;
				}

			public Datum next() {
				return TerminMitWiederholungImpl.this.getWdh().naechstesDatum(von);
			}

		};
	}



	public Map<Datum, Termin> termineFuer(DatumsGroesse groesse) {
		int start = this.getWdh().naechstesIntervall(groesse.getStart());
		int end = this.getWdh().naechstesIntervall(groesse.getEnde());

		if(end < getWdh().maxIntervallIndex()) {
			end = getWdh().maxIntervallIndex();
		}
		// wenn endIndex > maxIntervallIndex dann setze endIndex auf
		// maxIntervallIndex
		//
		// wenn endIndex < startIndex || endIndex < 0 || startIndex < 0 ||
		// endIndex > maxIntervallIndex
		// gib null zurück

		// 
		if(start > end || start < 0 || end < 0) {
			return null;
		}
		// Map erzeugen und die Wiederholungen einsammeln
		Map<Datum, Termin> tempMap = new HashMap<Datum, Termin>();
		Wiederholung wiederholung;
		TerminMitWiederholung terminMitWiederholung = this;
		IntervallIterator<Datum> intervallIterator = intervallIterator(start, end);

		while(intervallIterator.hasNext()) {
			Datum datum = intervallIterator.next();
			if(this == terminMitWiederholung) {
				wiederholung = this.getWdh().sub(start);//Sprung zum nächstfrühestmöglichen Termin
			}
			else {
				wiederholung = this.getWdh().sub(1); //Sprung zum nächstfrühesten Termin
			}
			terminMitWiederholung = new TerminMitWiederholungImpl(getBeschreibung(), datum, getDauer(),wiederholung);
			tempMap.put(datum, terminMitWiederholung);
		}
		return tempMap;
	}

	public class WiederholungImpl implements Wiederholung {

		private WiederholungType wdhType;
		private int anzahl;
		private int cycle;

		public WiederholungImpl(WiederholungType wdhType, int anzahl, int cyclus) {
			this.wdhType = wdhType;
			this.anzahl = anzahl;
			this.cycle = cyclus;
		}

		public WiederholungImpl(Wiederholung wdh) {
			this(wdh.getType(), wdh.anzahl(), wdh.getZyklus());
		}

		public WiederholungType getType() {
			return wdhType;
		}

		public int getZyklus() {
			return cycle;
		}

		public int anzahl() {
			return anzahl;
		}

		public int maxIntervallIndex() {
			return anzahl;
		}

		public int intervallLaenge() {
			return cycle * wdhType.inTagen();
		}
		/*
		 * @see kalender.interfaces.Wiederholung#naechstesIntervall(kalender.interfaces.Datum)
		 * 
		 * Methode liefert den Intervallindex für das einem Datum nachfolgendem
		 * Intervall. Es werden auch Intervalle berechnet, die außerhalb des
		 * gültigen Bereichs maxIntervallIndex liegen. Nutzer der Methode müssen
		 * sicher stellen, dass die Gültigkeit des Index geprüft wird.
		 */

		public int naechstesIntervall(Datum dat) {
			long diff = dat.differenzInTagen(getDatum());
			long div = diff / intervallLaenge();
			long mod = diff % intervallLaenge();

			/*
			 * div <= 0 und mod < 0: tag liegt vor dem ersten Termin der
			 * Wiederholung (Intervall 0) div > 0 && mod > 0: tag liegt vor dem
			 * Termin im Intervall div+1 div >= 0 && mod == 0: tag ist ein
			 * Termin der Wiederholung im Intervall div
			 */
			int intervallIndex = -1;
			if (div <= 0 && mod < 0)
				intervallIndex = 0;
			if (diff > 0 && mod > 0)
				intervallIndex = (int) div + 1;
			if (diff >= 0 && mod == 0)
				intervallIndex = (int) div;
			return intervallIndex;
		}

		/*
		 * @see kalender.interfaces.Wiederholung#vorherigesIntervall(kalender.
		 * interfaces.Datum)
		 * 
		 * Methode liefert den Intervallindex für das einem Datum vorausgehenden
		 * Intervall. Es werden auch Intervalle berechnet, die außerhalb des
		 * gültigen Bereichs maxIntervallIndex liegen. Nutzer der Methode müssen
		 * sicher stellen, dass die Gültigkeit des Index geprüft wird.
		 */

		public int vorherigesIntervall(Datum dat) {
			long diff = dat.differenzInTagen(getDatum());
			long div = diff / intervallLaenge();
			long mod = diff % intervallLaenge();

			/*
			 * diff < 0: dann liegt das Datum vor dem ersten Termin Fehler div
			 * >= 0 && mod = 0: dann interval = div sonst intervall =
			 * (naechstesIntervall(dat) -1)
			 */

			if (diff < 0)
				return -1;
			if (div >= 0 && mod == 0)
				return (int) div;
			return naechstesIntervall(dat) - 1;
		}
		@Override
		public int hashCode() {
			return this.anzahl() * this.cycle;
		}

		@Override
		public boolean equals( Object other) { //Object, da Implementierung der Superklasse Object
			if(this == other){ return true;}
			if(this.getClass() != null && this.getClass() != other.getClass()) {
				return false;
			}
			WiederholungImpl otherWiederholung = (WiederholungImpl) other; // impl overwrites
			if(this.anzahl() != otherWiederholung.anzahl() || this.cycle != otherWiederholung.getZyklus()) { //equals in Wiederholung angebrachter?
				return false;
			}
			return this.wdhType == otherWiederholung.wdhType;
		}

		@Override
		public String toString() {
			return"wdhType=" + wdhType + ", anzahl=" + anzahl + ", cycle=" + cycle;
		}

		/*
		 * @see kalender.interfaces.Wiederholung#naechstesDatum()
		 */

		public Datum naechstesDatum() {
			return naechstesDatum(1);
		}
		/*
		 * @see kalender.interfaces.Wiederholung#naechstesDatum(int)
		 */

		public Datum naechstesDatum(int faktor) {
			int anzahlTage = faktor * intervallLaenge();
			return new DatumImpl(getDatum()).add(new DauerImpl(anzahlTage, 0, 0));
		}
		/*
		 * @see kalender.interfaces.Wiederholung#sub(int)
		 */

		public Wiederholung sub(int wdhCount) {
			return new WiederholungImpl(wdhType, anzahl - wdhCount, cycle);
		}
		/*
		 * @see kalender.interfaces.Wiederholung#add(int)
		 */

		public Wiederholung add(int wdhCount) {
			return new WiederholungImpl(wdhType, anzahl + wdhCount, cycle);
		}


	}

}
