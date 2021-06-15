package Flex;

public class VisitingFaculty extends Faculty{
	
	public long salaryPerLecture;

	public VisitingFaculty(String name, String gender, String email,
			String address, String CN, String BG, String cnic,
			String nationality, String campus, String pass,
			int id, long sPL) {
		
		super(name,gender,email,address,CN,BG,cnic,nationality,
				campus,pass,id);
		
		salaryPerLecture=sPL;
		
	}
}
