public class Ladung
{
	// Attribute
	private String bezeichnung;
	private int menge;
	
	//Konstruktoren
	public Ladung() {}
	
	public Ladung(String bezeichnung, int menge)
	{
		setBezeichnung(bezeichnung);
		setMenge(menge);
	}
	
	// Setter und Getter nach Attributen sortiert
	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}
	public String getBezeichnung()
	{
		return this.bezeichnung;
	}
	
	public void setMenge(int menge)
	{
		this.menge = menge;
	}
	public int getMenge()
	{
		return this.menge;
	}
	
	// Methode, die alle Attribute der Ladung als einen String zurueckgibt
	public String toString()
	{
		return("Bezeichnung: " + getBezeichnung() + "\nMenge: " + getMenge());
	}
}