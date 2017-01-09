package finalWeb.dao;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finalWeb.command.*;

// �����
@Component("userDao")
public class UserDao {

	@Autowired
	SqlSession session;
	
	public UserCommand info(UserCommand userCommand){
		return session.selectOne("myUser.info",userCommand);
	}
	
	public UserCommand email(UserCommand userCommand){
		return session.selectOne("myUser.isEmail", userCommand);
	}
	
	public void join(UserCommand userCommand){
		System.out.println("����2");
		int check = session.insert("myUser.insertInfo",userCommand);
		
		if( check != 0){
			System.out.println("���� ����");
		}else{
			System.out.println("���� ����");
		}
	}
	
	public void updatePwd(UserCommand userCommand){
		int check = session.insert("myUser.updatePwd",userCommand);
		
		if( check != 0){
			System.out.println("���� ����");
		}else{
			System.out.println("���� ����");
		}
	}
	
	public void updateInfo(UserCommand userCommand){
		int check = session.insert("myUser.updateInfo",userCommand);
		
		if( check != 0){
			System.out.println("���� ����");
		}else{
			System.out.println("���� ����");
		}
	}
	
	public void deleteInfo(UserCommand userCommand){
		int check = session.delete("myUser.deleteInfo",userCommand);
		
		if( check != 0){
			System.out.println("���� ����");
		}else{
			System.out.println("���� ����");
		}
	}

	
	
	
}
