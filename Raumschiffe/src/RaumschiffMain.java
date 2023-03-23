import java.util.ArrayList;

public class RaumschiffMain
{
	public static void main(String[] args)
	{
		// Ladungen initialisieren
		Ladung ladung1 = new Ladung("Ferengi Schneckensaft", 200);
		Ladung ladung2 = new Ladung("Borg-Schrott", 5);
		Ladung ladung3 = new Ladung("Rote Materie", 2);
		Ladung ladung4 = new Ladung("Forschungssonde", 35);
		Ladung ladung5 = new Ladung("Bat'leth Klingonen Schwert", 200);
		Ladung ladung6 = new Ladung("Plasma-Waffe", 50);
		Ladung ladung7 = new Ladung("Photonentorpedo", 3);
		
		// Ladungsverzeichnisse initialisieren
		ArrayList<Ladung> ladungsverzeichnis_klingonen = new ArrayList<Ladung>();
		ArrayList<Ladung> ladungsverzeichnis_romularer = new ArrayList<Ladung>();
		ArrayList<Ladung> ladungsverzeichnis_vulkanier = new ArrayList<Ladung>();
		
		// Raumschiffe initialisieren
		Raumschiff klingonen = new Raumschiff(1, 100, 100, 100, 100, "IKS Hegh'ta", 2, ladungsverzeichnis_klingonen);
		Raumschiff romulaner = new Raumschiff(2, 100, 100, 100, 100, "IRW Khazara", 2, ladungsverzeichnis_romularer);
		Raumschiff vulkanier = new Raumschiff(0, 80, 80, 50, 100, "Ni'var", 5, ladungsverzeichnis_vulkanier);
		
		// Ladungen auf Raumschiffe packen, nach Raumschiffen sortiert
		klingonen.addLadung(ladung1);
		klingonen.addLadung(ladung5);
		
		romulaner.addLadung(ladung2);
		romulaner.addLadung(ladung3);
		romulaner.addLadung(ladung6);
		
		vulkanier.addLadung(ladung4);
		vulkanier.addLadung(ladung7);
		
		// Quellcode verwenden
		klingonen.photonentorpedoSchiessen(romulaner);
		romulaner.phaserkanoneSchiessen(klingonen);
		vulkanier.nachrichtAnAlle("Gewalt ist nicht logisch");
		klingonen.zustandRaumschiff();
		klingonen.ladungsverzeichnisAusgeben();
		vulkanier.reparaturDurchfuehren(true, true, true, vulkanier.getAndroidenAnzahl());
		vulkanier.photonentorpedosEinsetzen(ladung7.getMenge());
		vulkanier.ladungsverzeichnisAufraeumen();
		klingonen.photonentorpedoSchiessen(romulaner);
		klingonen.photonentorpedoSchiessen(romulaner);
		
		klingonen.zustandRaumschiff();
		romulaner.zustandRaumschiff();
		vulkanier.zustandRaumschiff();
		klingonen.ladungsverzeichnisAusgeben();
		romulaner.ladungsverzeichnisAusgeben();
		vulkanier.ladungsverzeichnisAusgeben();
		
		System.out.println(Raumschiff.eintraegeLogbuchZurueckgeben());
	}
}