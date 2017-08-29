package service;



import java.util.List;

import dao.CVDao;
import dao.MemberDao;
import model.Cv;
import model.Member;
import modelContent.CvContent;

public class Facade {

	private static Facade instance;
	
	private CVDao cvService;
	private MemberDao memberService;
	
	private Facade(){
		cvService = new CVDao();
		memberService = new MemberDao();
	}
	
	public static Facade getInstance(){
		if(instance==null){
			instance = new Facade();
		}
		return instance;
	}
	
	public CvContent getCvforUpdate(int cvId,Member m){
		return cvService.getCvforUpdate(cvId, m);
	}
	
	public boolean addCv(CvContent c,Member m){
		return cvService.addCv(c, m);
	}
	
	public List<Cv> listCvbyMember(Member member){
		return cvService.listCvbyMember(member);
	}
	public List<Cv> listCvbyManager(){
		return cvService.listCvByManager();
	}
	public Member getMemberInf(String loginUserName, String loginPassword)
	{
		return memberService.getMemberInf(loginUserName, loginPassword) ;
	}

	public void deleteCvByRole(int cvId, Member member)
	{
		cvService.deleteCvByRole(cvId, member);
	}
	public boolean deleteCvforUpdate(int cvId){
		return cvService.deleteCvforUpdate(cvId);
	}
	
	public CvView.CvContent getCvContetn(int cvId)
	{
		return cvService.getCvContent(cvId);
	}
}