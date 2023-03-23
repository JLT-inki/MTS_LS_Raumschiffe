import java.util.ArrayList;
import java.util.Random;

public class Raumschiff
{
	private int photonentorpedoAnzahl;
	private int energieversorgungInProzent;
	private int schildeInProzent;
	private int huelleInProzent;
	private int lebenserhaltungssystemeInProzent;
	private int androidenAnzahl;
	private String schiffsname;
	private static ArrayList<String> broadcastKummunikator = new ArrayList<String>();
	private ArrayList<Ladung> ladungsverzeichnis = new ArrayList<Ladung>();
	
	/**
	 * Parameterloser Konstruktor der Klasse Raumschiff
	 */
	public Raumschiff() {}
	
	/**
	 * Vollparametrisierter Konstruktor der Klasse Raumschiff
	 * @param photonentorpedoAnzahl
	 * @param energieversorgungInProzent
	 * @param schildeInProzent
	 * @param huelleInProzent
	 * @param lebenserhaltungssystemeInProzent
	 * @param schiffsname
	 * @param androidenAnzahl
	 * @param ladungsverzeichnis
	 */
	public Raumschiff(int photonentorpedoAnzahl, int energieversorgungInProzent, int schildeInProzent, int huelleInProzent, int lebenserhaltungssystemeInProzent, String schiffsname, int androidenAnzahl, ArrayList<Ladung> ladungsverzeichnis)
	{
		setPhotonentorpedoAnzahl(photonentorpedoAnzahl);
		setEnergieversorgungInProzent(energieversorgungInProzent);
		setSchildeInProzent(schildeInProzent);
		setHuelleInProzent(huelleInProzent);
		setLebenserhaltungssystemeInProzent(lebenserhaltungssystemeInProzent);
		setAndroidenAnzahl(androidenAnzahl);
		setSchiffsname(schiffsname);
		setLadungsverzeichnis(ladungsverzeichnis);
	}
	
	/**
	 * Methode, die ein Objekt der Klasse Ladung zum Attribut ladungsverzeichnis
	 * eines Objekts der Klasse Raumschiff hinzufuegt
	 * @param neueLadung
	 */
	public void addLadung(Ladung neueLadung)
	{
		this.ladungsverzeichnis.add(neueLadung);
	}
	
	/**
	 * Methode, die den Zustand eines Raumschiffs auf der Konsole ausgibt
	 */
	public void zustandRaumschiff()
	{
		System.out.println("Name: " + getSchiffsname());
		System.out.println("\tAnzahl Photonentorpedos: " + getPhotonentorpedoAnzahl());
		System.out.println("\tEnergieversorgung (in %): " + getEnergieversorgungInProzent());
		System.out.println("\tSchilde (in %): " + getSchildeInProzent());
		System.out.println("\tHuelle (in %): " + getHuelleInProzent());
		System.out.println("\tLebenserhaltungssysteme (in %): " + getLebenserhaltungssystemeInProzent());
		System.out.println("\tAnzahl Androiden: " + getAndroidenAnzahl() + "\n");
	}
	
	/**
	 * Methode, die das Attribut ladungsverezeichnis auf der Konsole ausgibt
	 */
	public void ladungsverzeichnisAusgeben()
	{
		System.out.println("Ladung des Schiffs " + getSchiffsname() + ":");
		
		for(Ladung l: getLadungsverzeichnis())
		{
			System.out.println(l.toString());
		}
		
		System.out.println();
	}
	
	/**
	 * Methode, mit welcher ein Objekt der Klasse Raumschiff auf ein anderes Objekt
	 * der Klasse Raumschiff mit Photonentorpedos schiesst.
	 * @param ziel
	 */
	public void photonentorpedoSchiessen(Raumschiff ziel)
	{
		if(getPhotonentorpedoAnzahl() <= 0)
		{
			nachrichtAnAlle("-=*Click*=-");
		}
		else
		{
			setPhotonentorpedoAnzahl(getPhotonentorpedoAnzahl()-1);
			nachrichtAnAlle("Photonentorpedo abgeschossen");
			treffer(ziel);
		}
	}
	
	/**
	 * Methode, mit welcher ein Objekt der Klasse Raumschiff auf ein anderes Objekt
	 * der Klasse Raumschiff mit der Phaserkanone schiesst.
	 * @param ziel
	 */
	public void phaserkanoneSchiessen(Raumschiff ziel)
	{
		if(getEnergieversorgungInProzent() < 50)
		{
			nachrichtAnAlle("-=*Click*=-");
		}
		else
		{
			setEnergieversorgungInProzent(getEnergieversorgungInProzent() - 50);
			nachrichtAnAlle("Phaserkanone abgeschossen");
			treffer(ziel);
		}
	}
	
	/**
	 * Methode, mit welcher ein Objekt der Klasse Raumschiff einen Treffer durch die
	 * Phaserkanone oder die Photonentorpedos eines anderen Objekts der Klasse
	 * Raumschiff vermerkt
	 * @param ziel
	 */
	private void treffer(Raumschiff ziel)
	{
		nachrichtAnAlle(ziel.getSchiffsname() + " wurde getroffen!");
		ziel.setSchildeInProzent(ziel.getSchildeInProzent() - 50);
		
		//Negative Werte vermeiden
		if(ziel.getSchildeInProzent() < 0)
		{
			ziel.setSchildeInProzent(0);
		}
		
		if(ziel.getSchildeInProzent() == 0)
		{
			ziel.setHuelleInProzent(ziel.getHuelleInProzent() - 50);
			ziel.setEnergieversorgungInProzent(ziel.getEnergieversorgungInProzent() - 50);
			
			// Negative Werte vermeiden
			if(ziel.getHuelleInProzent() < 50)
			{
				ziel.setHuelleInProzent(0);
			}
			if(ziel.getEnergieversorgungInProzent() < 0)
			{
				ziel.setEnergieversorgungInProzent(0);
			}
			
			if(ziel.getHuelleInProzent() == 0)
			{
				nachrichtAnAlle("Die Lebenserhaltungssysteme von " + ziel.getSchiffsname() + " wurden vollstaendig zerstoert");
			}
		}
	}
	
	/**
	 * Methode, die einen String zum statischen Attribut broadcastKommunikator der
	 * Klasse Raumschiff hinzufuegt
	 * @param message
	 */
	public void nachrichtAnAlle(String message)
	{
		Raumschiff.broadcastKummunikator.add(message);
	}
	
	/**
	 * Methode, die ueberprueft, ob im Ladungsverzeichnis eines Objekts der Klasse Raumschiff
	 * eine Ladung mit dem Namen "Photonentorpedo" enthalten ist.
	 * Ist dies der Fall werden diese zu den Photonentorpedos dieses Objektes hinzugefuegt
	 * @param anzahlPhotonentorpedos
	 */
	public void photonentorpedosEinsetzen(int anzahlPhotonentorpedos)
	{
		boolean geladen = false;
		for(Ladung l: getLadungsverzeichnis())
		{
			if(l.getBezeichnung().equals("Photonentorpedo"))
			{
				geladen = true;
				
				int einzusetzendePhotonentorpedos = anzahlPhotonentorpedos;
				
				if(anzahlPhotonentorpedos > l.getMenge())
				{
					einzusetzendePhotonentorpedos = l.getMenge();
				}
				
				l.setMenge(l.getMenge()-einzusetzendePhotonentorpedos);
				setPhotonentorpedoAnzahl(getPhotonentorpedoAnzahl() + einzusetzendePhotonentorpedos);
				
				System.out.println(einzusetzendePhotonentorpedos + " Photonentorpedo(s) eingesetzt\n");
			}
		}
		
		if(geladen == false)
		{
			System.out.println("Keine Photonentorpedos gefunden!\n");
			nachrichtAnAlle("-=*Click*=-");
		}
	}
	
	/**
	 * Methode, die ueberprueft, ob im Attribut ladungsverzeichnis eines Objekts der Klasse
	 * Raumschiff ein Objekt der Klasse Ladung existiert, dessen Menge gleich 0 ist.
	 * Is dies der Fall wird dieses Objekt der Klasse Ladung entfernt
	 */
	public void ladungsverzeichnisAufraeumen()
	{
		for(Ladung l: getLadungsverzeichnis())
		{
			if(l.getMenge() == 0)
			{
				this.getLadungsverzeichnis().remove(l);
				break;
			}
		}
	}
	
	/**
	 * Methode, mit welcher die Schiffstrukturen eines Objekts der Kalsse Raumschiff repariert werden
	 * @param schutzschilde
	 * @param energieversorgung
	 * @param schiffshuelle
	 * @param anzahlDroiden
	 */
	public void reparaturDurchfuehren(boolean schutzschilde, boolean energieversorgung, boolean schiffshuelle, int anzahlDroiden)
	{
		Random random = new Random();
		int benutzteDroiden = anzahlDroiden;
		int anzahlReparaturen = 0;
		
		if(anzahlDroiden > getAndroidenAnzahl())
		{
			benutzteDroiden = getAndroidenAnzahl();
		}
		
		if(schutzschilde == true)
		{
			anzahlReparaturen++;
		}
		if(energieversorgung == true)
		{
			anzahlReparaturen++;
		}
		if(schiffshuelle = true)
		{
			anzahlReparaturen++;
		}
		
		if(anzahlReparaturen != 0)
		{
			int reparatur = (int) random.nextInt(100) * benutzteDroiden / anzahlReparaturen;
			
			if(schutzschilde == true)
			{
				setSchildeInProzent(getSchildeInProzent() + reparatur);
			}
			if(energieversorgung == true)
			{
				setEnergieversorgungInProzent(getEnergieversorgungInProzent() + reparatur);
			}
			if(schiffshuelle = true)
			{
				setHuelleInProzent(getHuelleInProzent() + reparatur);
			}
			
			//Verhindern, dass ein Wert ueber 100% geht
			if(getSchildeInProzent() > 100)
			{
				setSchildeInProzent(100);
			}
			if(getEnergieversorgungInProzent() > 100)
			{
				setEnergieversorgungInProzent(100);
			}
			if(getHuelleInProzent() > 100)
			{
				setHuelleInProzent(100);
			}
		}
	}
	
	/**
	 * Methode, die das statische Attribut broadcastKommunikator der Klasse Raumschiff
	 * zurueckgibt
	 * @return Den gesamnten Broadcastkommunikator der Klasse raumschiff
	 */
	public static ArrayList<String> eintraegeLogbuchZurueckgeben()
	{
		return broadcastKummunikator;
	}
	
	public void setPhotonentorpedoAnzahl(int photonentorpedoAnzahl)
	{
		this.photonentorpedoAnzahl = photonentorpedoAnzahl;
	}
	public int getPhotonentorpedoAnzahl()
	{
		return this.photonentorpedoAnzahl;
	}
	
	public void setEnergieversorgungInProzent(int energieversorgungInProzent)
	{
		this.energieversorgungInProzent = energieversorgungInProzent;
	}
	public int getEnergieversorgungInProzent()
	{
		return this.energieversorgungInProzent;
	}
	
	public void setSchildeInProzent(int schildeInProzent)
	{
		this.schildeInProzent = schildeInProzent;
	}
	public int getSchildeInProzent()
	{
		return this.schildeInProzent;
	}
	
	public void setHuelleInProzent(int huelleInProzent)
	{
		this.huelleInProzent = huelleInProzent;
	}
	public int getHuelleInProzent()
	{
		return this.huelleInProzent;
	}
	
	public void setLebenserhaltungssystemeInProzent(int lebenserhaltungssystemeInProzent)
	{
		this.lebenserhaltungssystemeInProzent = lebenserhaltungssystemeInProzent;
	}
	public int getLebenserhaltungssystemeInProzent()
	{
		return this.lebenserhaltungssystemeInProzent;
	}
	
	public void setAndroidenAnzahl(int androidenAnzahl)
	{
		this.androidenAnzahl = androidenAnzahl;
	}
	public int getAndroidenAnzahl()
	{
		return this.androidenAnzahl;
	}
	
	public void setSchiffsname(String schiffsname)
	{
		this.schiffsname = schiffsname;
	}
	public String getSchiffsname()
	{
		return this.schiffsname;
	}
	
	public void setLadungsverzeichnis(ArrayList<Ladung> ladungsverzeichnis)
	{
		this.ladungsverzeichnis = ladungsverzeichnis;
	}
	public ArrayList<Ladung> getLadungsverzeichnis()
	{
		return this.ladungsverzeichnis;
	}
}