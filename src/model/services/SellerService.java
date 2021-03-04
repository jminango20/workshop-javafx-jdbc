package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {
	
	private SellerDao dao = DaoFactory.createSellerDao();
	
	public List<Seller> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Seller obj) {
		if(obj.getId()==null) {//inserindo um novo departamento
			dao.insert(obj); //inseri no DB
		}
		else {
			dao.update(obj); //atualiza
		}
	}
	
	public void remove(Seller obj) {
		dao.deleteById(obj.getId());
	}

}
