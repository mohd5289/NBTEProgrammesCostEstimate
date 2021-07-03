
public class NBTEStaff  {
  
	
	
	private String name;
	private boolean isSeniorStaff;
	private String visitationExercise;
	
	

	

	public NBTEStaff(String name, boolean isSeniorStaff, String visitationExercise) {
		super();
		this.name = name;
		this.isSeniorStaff = isSeniorStaff;
		this.visitationExercise = visitationExercise;
	}

	public String getName() {
		return name;
	}
	public String getVisitationExercise() {
		return visitationExercise;
	}

	public void setVisitationExercise(String visitationExercise) {
		this.visitationExercise = visitationExercise;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isSeniorStaff() {
		return isSeniorStaff;
	}

	public void setSeniorStaff(boolean isSeniorStaff) {
		this.isSeniorStaff = isSeniorStaff;
	}
	
	public boolean willTravelByFlight() {
		return	LocationUtils.distanceBetweenStates(ProgrammesCostEstimates.institutionLocation, "Kaduna") > 600;
			}
	
	public int getTransport() {
		return 0;
	}
	public int getHonorarium() {
		if(this.isSeniorStaff) {
			switch(this.visitationExercise) {
			case "Resource Inspection": return 24000;
			case "Accreditation": return 32000;
			case "Verification": return 48000;
			}}
			else {
				switch(this.visitationExercise) {
				case "Resource Inspection": return 18000;
				case "Accreditation": return 24000;
				case "Verification": return 36000;
				}
			
			}
	return 0;
	}
	
	public int getTotalEstimate(){
	return getTransport()+getHonorarium();
	}

	@Override
	public String toString() {
		return "NBTEStaff [name=" + name + ", willTravelByFlight()=" + willTravelByFlight() + ", getsTransport= N"
				+ getTransport() + ", getsHonorarium= N" + getHonorarium() + ", getsTotalEstimate= N" + getTotalEstimate()
				+ "]";
	}

	
	
	

	
	
	
	

}
