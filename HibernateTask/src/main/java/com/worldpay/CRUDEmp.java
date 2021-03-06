package com.worldpay;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CRUDEmp {

	public static void main(String args[]) {
		SessionFactory sf=Util.getSF();
		Session session=sf.openSession();
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("1. View Employee");
			System.out.println("2. Add Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			int choice = scanner.nextInt();
			
			switch(choice) {
			
			case 1:
				
				Criteria cr = session.createCriteria(Emp.class);
				List<Emp> list = cr.list();
				for(Emp employee : list)
				{
					System.out.println(employee.getEname());
					System.out.println("Employee No : "+employee.getEno());
					System.out.println("Employee Name : "+employee.getEname());
					System.out.println("Employee Salary : "+employee.getSal());
					System.out.println("Employee Laptop Code : "+employee.getLaptop().getCode());
					System.out.println("Employee Laptop Brand : "+employee.getLaptop().getBrand());
					System.out.println("Employee Laptop Price : "+employee.getLaptop().getPrice());
					
					List<Project> projects=employee.getProjects();
					for(Project project:projects){
						System.out.println(project.getPcode()+","+project.getPtitle()+","+project.getCost());
					
					}
					
					List<Vehicle> vehicles=employee.getVehicles();
					for(Vehicle vehicle:vehicles)
						System.out.println(vehicle.getRegno()+","+vehicle.getBrand()+","+vehicle.getModel()+","+vehicle.getPrice());

				}
				break;
				
			case 2:
				System.out.println("enter eno");
				int eno1 = scanner.nextInt();
				System.out.println("enter ename");
				String ename1 = scanner.next();
				System.out.println("enter esal");
				int esal1 = scanner.nextInt();
				System.out.println("enter laptop_code");
				String lcode1 = scanner.next();
				Transaction tr = session.beginTransaction();
				Emp employee1 = new Emp(eno1,ename1,esal1,new Laptop(lcode1));
				session.save(employee1);
				tr.commit();
				break;
				
			case 3:
				System.out.println("enter eno");
				int eno2 = scanner.nextInt();
				System.out.println("enter ename");
				String ename2 = scanner.next();
				System.out.println("enter esal");
				int esal2 = scanner.nextInt();
				System.out.println("enter laptop_code");
				String lcode2 = scanner.next();
				
				Emp e=session.get(Emp.class,eno2);
				e.setEname(ename2);
				e.setSal(esal2);
				e.setLaptop(new Laptop(lcode2));
				
				Transaction tr1 = session.beginTransaction();
				session.update(e);
				tr1.commit();
				System.out.println("\nRecord Updated");
				break;
				
			case 4:
				System.out.println("enter eno");
				int eno3 = scanner.nextInt();
				Emp e1=session.get(Emp.class,eno3);
				Transaction tr2 = session.beginTransaction();
				session.delete(e1);
				tr2.commit();
				System.out.println("\nRecord Deleted");
				break;
			
			case 5: System.exit(0);
				
			default:
				System.out.println("Please!!!! Enter correct option ");
			}
		}
	}
}
