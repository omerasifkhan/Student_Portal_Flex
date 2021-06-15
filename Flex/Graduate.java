package Flex;

public class Graduate extends Student{
	
	public String researchThesis;

	public Graduate(String name, String gender, String email,
			String address, String CN, String BG, String cnic,
			String nationality, String campus, String pass,
			String RN, float cgpa, String thesis,FlexApplication subject) {
		
		super(name,gender,email,address,CN,BG,cnic,nationality,
				campus,pass, RN, cgpa,subject);
		researchThesis=thesis;
	}

}
