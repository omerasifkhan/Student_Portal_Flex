package Flex;

abstract public class User {

	public String userName;
	public String Gender;
	public String userEmail;
	public String userAddress;
	public String userContactNo;
	public String userBloodGrp;
	public String CNIC;
	public String userNatonality;
	public String userCampus;
	public String userPassword;
	
	public User(String name, String gender, String email,
				String address, String CN, String BG, String cnic,
				String nationality, String campus, String pass) {
		this.userName=name;
		this.Gender=gender;
		this.userEmail=email;
		this.userAddress=address;
		this.userContactNo=CN;
		this.userBloodGrp=BG;
		this.CNIC=cnic;
		this.userNatonality=nationality;
		this.userCampus=campus;
		this.userPassword=pass;
		
	}
}
