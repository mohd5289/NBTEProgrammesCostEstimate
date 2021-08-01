
public class ProffessionalBodyResourcePerson {


	 private String proffessionalBodyName;
	  
	  private String address;
	  
	  private String phoneNumber;
	  
	  private String visit;
	  
	  public ProffessionalBodyResourcePerson(String proffessionalBodyName, String address, String phoneNumber, String visit) {
	    this.proffessionalBodyName = proffessionalBodyName;
	    this.address = address;
	    this.phoneNumber = phoneNumber;
	    this.visit = visit;
	  }
	  
	  public void setPhoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
	  }
	  
	  public String getPhoneNumber() {
	    return this.phoneNumber;
	  }
	  
	  public String getProffessionalBodyName() {
	    return this.proffessionalBodyName;
	  }
	  
	  public void setProffessionalBodyName(String proffessionalBodyName) {
	    this.proffessionalBodyName = proffessionalBodyName;
	  }
	  
	  public String getAddress() {
	    return this.address;
	  }
	  
	  public void setAddress(String address) {
	    this.address = address;
	  }
	  
	  public String getVisit() {
	    return this.visit;
	  }
	  
	  public void setVisit(String visit) {
	    this.visit = visit;
	  }
	  
	  public boolean willTravelByFlight() {
	    return true;
	  }
	  
	  public int getTransport() {
	    return 130000;
	  }
	  
	  public int getHonorarium() {
	    String str;
	    switch ((str = this.visit).hashCode()) {
	      case -531640122:
	        if (!str.equals("Resource Inspection"))
	          break; 
	        return 35000;
	      case 1996496410:
	        if (!str.equals("Accreditation"))
	          break; 
	        return 50000;
	    } 
	    return 0;
	  }
	  
	  public int getTotalEstimate() {
	    return getTransport() + getHonorarium();
	  }
	  
	  public String toString() {
	    return "ProffessionalBodyResourcePerson [proffessionalBodyName=" + this.proffessionalBodyName + ", address=" + 
	      this.address + ", phoneNumber=" + this.phoneNumber + ", willTravelByFlight()=" + willTravelByFlight() + 
	      ", getsTransport()=" + getTransport() + ", getsHonorarium()=" + getHonorarium() + 
	      ", getsTotalEstimate()=" + getTotalEstimate() + "]";
	  }













}
