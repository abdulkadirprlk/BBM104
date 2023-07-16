public class MemberManager implements IMemberService{
	@Override
	public String addMember(String type) {
		if(type.equals("A")){
			Academic academic = new Academic();
			Main.members.add(academic);
			academic.setId(Main.members.indexOf(academic) + 1);
			return String.format("Created new member: Academic [id: %d]\n", academic.getId());
		}else if(type.equals("S")){
			Student student = new Student();
			Main.members.add(student);
			student.setId(Main.members.indexOf(student) + 1);
			return String.format("Created new member: Student [id: %d]\n", student.getId());
		}else{
			return "Wrong member type: Member type must be only (A)cademic or (S)tudent!\n";
		}
	}
}
