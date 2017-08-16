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
	
	public boolean addCv(CvContent c,Member m){
		return cvService.addCv(c, m);
	}
	
	public List<Cv> listCvbyMember(Member m){
		return cvService.listCvbyMember(m);
	}
	public List<Cv> listCvbyManager(){
		return cvService.listCvByManager();
	}
	public boolean loginCheck(String loginUserName, String loginPassword)
	{
		if(memberService.loginCheck(loginUserName, loginPassword) == 0)
			return true;
		else
			return false;
	}
}