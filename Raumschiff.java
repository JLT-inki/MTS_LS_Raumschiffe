import java.util.ArrayList;
import java.util.Random;

public class Raumschiff
{
	// Attribute
	private int photonentorpedoAnzahl;
	private int energieversorgungInProzent;
	private int schildeInProzent;
	private int huelleInProzent;
	private int lebenserhaltungssystemeInProzent;
	private int androidenAnzahl;
	private String schiffsname;
	private static ArrayList<String> broadcastKummunikator = new ArrayList<String>();
	private ArrayList<Ladung> ladungsverzeichnis = new ArrayList<Ladung>();
	
	// Konstruktoren
	public Raumschiff() {}
	
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
	
	// Methode, welche eine einzelne Ladung hinzufügt
	public void addLadung(Ladung neueLadung)
	{
		this.ladungsverzeichnis.add(neueLadung);
	}
	
	// Methode, die alle Zustaende des Raumschiffs auf der Konsole ausgibt
	public void zustandRaumschiff()
	{
		System.out.println("Anzahl Photonentorpedos: " + getPhotonentorpedoAnzahl());
		System.out.println("Energieversorgung (in %): " + getEnergieversorgungInProzent());
		System.out.println("Schilde (in %): " + getSchildeInProzent());
		System.out.println("Huelle (in %): " + getHuelleInProzent());
		System.out.println("Lebenserhaltungssysteme (in %): " + getLebenserhaltungssystemeInProzent());
		System.out.println("Anzahl Androiden: " + getAndroidenAnzahl());
	}
	
	//Methode, die alle Ladungen eines Raumschiffs auf der Konsole ausgibt
	public void ladungsverzeichnisAusgeben()
	{
		for(Ladung l: getLadungsverzeichnis())
		{
			System.out.println(l.toString());
		}
	}
	
	// Methode, die einen Photonentorpedo (falls vorhanden) abschiesst
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
	
	//Methde, mit welcher die Phaserkanone abgeschossen wird
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
	
	// Methode, die Treffer vermerkt
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
	
	// Methode, die Nachrichten im Broadcastkommunikator speichert
	public void nachrichtAnAlle(String message)
	{
		Raumschiff.broadcastKummunikator.add(message);
	}
	
	// Methode, die Photonentorpedos einsetzt
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
				
				System.out.println(einzusetzendePhotonentorpedos + " Photonentorpedo(s) eingesetzt");
			}
		}
		
		if(geladen == false)
		{
			System.out.println("Keine Photonentorpedos gefunden!");
			nachrichtAnAlle("-=*Click*=-");
		}
	}
	
	// Methode, die ueberprueft ob von einer Ladung 0 vorhanden sind und diese dann entfernt
	public void ladungsverzeichnisAufraeumen()
	{
		for(Ladung l: getLadungsverzeichnis())
		{
			if(l.getMenge() == 0)
			{
				this.getLadungsverzeichnis().remove(l);
			}
		}
	}
	
	// Methode, die Reparaturen am Schiff durchfuerht
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
			
			// Reparatur anwenden
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
	
	// Methode, die alle Logbucheintraege zurueckgibt
	public static ArrayList<String> eintraegeLogbuchZurueckgeben()
	{
		return broadcastKummunikator;
	}
	
	// Setter und Getter nach Attributen gruppiert
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