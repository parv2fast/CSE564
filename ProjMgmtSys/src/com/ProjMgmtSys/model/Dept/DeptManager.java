package com.ProjMgmtSys.model.Dept;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public class DeptManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createDept(String depName){
		Dept dept = new Dept();
		dept.setDepName(depName);
		
		int repeat = checkRepeatDept(depName);
		if(repeat == -1)
			return "";
		
		createSession();
		session.save(dept);
		session.getTransaction().commit();
		session.close();
		return "" + dept.getDepId();
//		return queryDeptId(depName);
	}
	
	public static int checkRepeatDept(String depName){
		createSession();
		String hql = "from Dept as dept where dept.depName=:depName";
		Query query = session.createQuery(hql);
		query.setString("depName", depName);
		List <Dept>list = query.list();
		int result = 0;
		if(list.size() > 0)
			result = -1;
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public static String queryDeptId(String depName){
		createSession();
		String hql = "from Dept as dept where dept.depName=:depName";
		Query query = session.createQuery(hql);
		query.setString("depName", depName);
		List <Dept>list = query.list();
		Dept dept = null;
		java.util.Iterator<Dept> iter = list.iterator();
		while (iter.hasNext()) {
			dept = iter.next();
		}
		session.getTransaction().commit();
		session.close();
		return "" + dept.getDepId();		
	}
	
	@SuppressWarnings("unchecked")
	public static Dept queryDeptById(int depId){
		createSession();
		String hql = "from Dept as dept where dept.depId=:depId";
		Query query = session.createQuery(hql);
		query.setInteger("depId", depId);
		List <Dept>list = query.list();
		Dept dept = null;
		java.util.Iterator<Dept> iter = list.iterator();
		while (iter.hasNext()) {
			dept = iter.next();
		}
		session.getTransaction().commit();
		session.close();
		return dept;		
	}
	
	public static Dept queryDeptByName(String depName){
		createSession();
		String hql = "from Dept as dept where dept.depName=:depName";
		Query query = session.createQuery(hql);
		query.setString("depName", depName);
		List <Dept>list = query.list();
		Dept dept = null;
		java.util.Iterator<Dept> iter = list.iterator();
		while (iter.hasNext()) {
			dept = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return dept;		
	}
	
	public static String updateDepName(String oldName, String newName){
		int repeat = checkRepeatDept(newName);
		if(repeat == -1)
			return "0";
		
		Dept dept1 = queryDeptByName(oldName);
		if(dept1 == null)
			return "0";
		
		createSession();
		String hql = "update Dept as dept set dept.depName=:newName where dept.depName=:oldName";
		Query query = session.createQuery(hql);
		query.setString("oldName", oldName);
		query.setString("newName", newName);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return "1";
	}
	
	public static List <Dept> geyAllDepName(){
		createSession();
		String hql = "from Dept as dept";
		Query query = session.createQuery(hql);
		List <Dept> list = query.list();
		Dept dept = null;
		session.getTransaction().commit();
		session.close();
		java.util.Iterator<Dept> iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().getDepId());
		}			
		return list;	
	}
}