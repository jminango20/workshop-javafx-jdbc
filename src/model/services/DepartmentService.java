package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Department obj) {
		if(obj.getId()==null) {//inserindo um novo departamento
			dao.insert(obj); //inseri no DB
		}
		else {
			dao.update(obj); //atualiza
		}
	}
	
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}

}
