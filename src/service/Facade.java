package service;



import java.util.List;

import dao.CVDao;
import model.Cv;
import model.Member;
import modelContent.CvContent;

public class Facade {

	private static Facade instance;
	
	private CVDao cvService;
	
	private Facade(){
		cvService = new CVDao();
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
}