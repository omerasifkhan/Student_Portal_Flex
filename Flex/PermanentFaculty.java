package Flex;

public class PermanentFaculty extends Faculty{

	public long salaryPerSemester;
	
	
	public PermanentFaculty(String name, String gender, String email,
			String address, String CN, String BG, String cnic,
			String nationality, String campus, String pass,
			int id,long sPS) {
		
		super(name,gender,email,address,CN,BG,cnic,nationality,
				campus,pass,id);
		
		salaryPerSemester=sPS;
		
		
	}

}
