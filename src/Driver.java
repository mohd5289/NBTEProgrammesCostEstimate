
public class Driver {
	
	private String amount;
	private String fuel;
	public Driver(String amount, String fuel) {
		
		this.amount = amount;
		this.fuel = fuel;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public int getTotalEstimate(){
		return  Integer.parseInt(getFuel())+Integer.parseInt(getAmount());
		}
	@Override
	public String toString() {
		return "Driver [amount= N" + amount + ", fuel= N" + fuel + "]";
	}

}
