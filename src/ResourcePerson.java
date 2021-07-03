
public class ResourcePerson  {
 
	
	private String name;
	private String phoneNumber;
	private String Address;
	private String stateLocation;
	private String nameOfProffessionalBody;
	
	private boolean isProffessionalBody;
	private String visitationExercise;
	
	public ResourcePerson(String name, String phoneNumber, String address, String stateLocation,
			String nameOfProffessionalBody, boolean isProffesionalBody, String visitationExercise) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		Address = address;
		this.stateLocation = stateLocation;
		this.nameOfProffessionalBody = nameOfProffessionalBody;
		this.isProffessionalBody = isProffesionalBody;
		this.visitationExercise = visitationExercise;
	}
	
	public String getVisitationExercise() {
		return visitationExercise;
	}

	public void setVisitationExercise(String visitationExercise) {
		this.visitationExercise = visitationExercise;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNameOfProffessionalBody() {
		return nameOfProffessionalBody;
	}
	public void setNameOfProffessionalBody(String nameOfProffessionalBody) {
		this.nameOfProffessionalBody = nameOfProffessionalBody;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getStateLocation() {
		return stateLocation;
	}
	public void setStateLocation(String stateLocation) {
		this.stateLocation = stateLocation;
	}
	public boolean isProffessionalBody() {
		return isProffessionalBody;
	}
	public void setProffessionalBody(boolean isProffesionalBody) {
		this.isProffessionalBody = isProffesionalBody;
	}
	
	
	public boolean willTravelByFlight() {
	return	LocationUtils.distanceBetweenStates(ProgrammesCostEstimates.institutionLocation, this.stateLocation) > 600;
		}
	public double getTransport() {
		if(willTravelByFlight()) {
			return 130000;
		}
		
		return LocationUtils.distanceBetweenStates(ProgrammesCostEstimates.institutionLocation, this.stateLocation)* 40;
	}
	public int getHonorarium() {
		if(this.isProffessionalBody) {
			switch(this.visitationExercise) {
			case "Resource Inspection": return 35000;
			case "Accreditation": return 50000;
			}}
			else {
				switch(this.visitationExercise) {
				case "Resource Inspection": return 30000;
				case "Accreditation": return 40000;
			}
			
			}
	return 0;
	}
	
	public int getTotalEstimate(){
	return (int) (getTransport()+getHonorarium());
	}

	@Override
	public String toString() {
		return "ResourcePerson [name=" + name + ", phoneNumber=" + phoneNumber + ", stateLocation=" + stateLocation
				+ ", isProffesionalBody=" + isProffessionalBody + ", willTravelByFlight()=" + willTravelByFlight()
				+ ", getsTransport= N" + getTransport() + ", getsHonorarium= N" + getHonorarium()
				+ ", getsTotalEstimate= N" + getTotalEstimate() + "]";
	}

	
	
}
