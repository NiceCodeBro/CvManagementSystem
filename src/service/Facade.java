package service;



import java.util.List;

import dao.CVDao;
import model.Cv;
import model.Member;

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
	
	public List<Cv> listCvbyMember(Member m){
		return cvService.listCvbyMember(m);
	}
}
