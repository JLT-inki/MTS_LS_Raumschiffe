public class Ladung
{
	private String bezeichnung;
	private int menge;
	
	/**
	 * Parameterloser Kosntruktor der Klasse Ladung
	 */
	public Ladung() {}
	
	/**
	 * Vollparamterisierter Konstruktor der Klasse Ladung
	 * @param bezeichnung
	 * @param menge
	 */
	public Ladung(String bezeichnung, int menge)
	{
		setBezeichnung(bezeichnung);
		setMenge(menge);
	}
	
	/**
	 * Methode, die die eindeutige Kennung eines Objekt der Klasse Ladung als String zurueckgibt
	 * @return String, der alle Attribute einer Ladung enthaelt
	 */
	public String toString()
	{
		return("\tBezeichnung: " + getBezeichnung() + " (Menge: " + getMenge() + ")");
	}
	
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
}