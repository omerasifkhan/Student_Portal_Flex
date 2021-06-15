package Flex;

public class Undergraduate extends Student{
	
	public String FYP;

	public Undergraduate(String name, String gender, String email,
			String address, String CN, String BG, String cnic,
			String nationality, String campus, String pass,
			String RN, float cgpa, String fyp,FlexApplication subject) {
		
		super(name,gender,email,address,CN,BG,cnic,nationality,
				campus,pass, RN, cgpa,subject);
		FYP=fyp;
		
	}

}
