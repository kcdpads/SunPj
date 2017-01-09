package finalWeb.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finalWeb.command.StyleCommand;

//占쏙옙타占쏙옙
@Component
public class StyleDao {

	@Autowired
	SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	public void insertStyle(StyleCommand command) {
		int x = session.insert("style.insertStyle", command);

		if (x != 0) {
			System.out.println("insert �꽦怨�");
		} else {
			System.out.println("insert �떎�뙣");
		}
	}

	public List<StyleCommand> getStyle() {
		List<StyleCommand> list = session.selectList("style.selectAll");
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = list.get(i).getStylePhoto();
			str = str.substring(0, str.indexOf(","));
			list.get(i).setStylePhoto(str);
		}

		return list;
	}

	public StyleCommand getStyleInfo(String styleNum) {
		StyleCommand styleCommand = session.selectOne("style.selectStyle", styleNum);
		session.update("style.updateViewCount", styleCommand);

		return styleCommand;
	}

	public StyleCommand getStyleInfo2(String styleNum) {
		StyleCommand styleCommand = session.selectOne("style.selectStyle", styleNum);

		return styleCommand;
	}

	public void updateStyle(StyleCommand command) {
		int x = session.update("style.updateStyle", command);

		if (x != 0) {
			System.out.println("update �꽦怨�");
		} else {
			System.out.println("update �떎�뙣");
		}
	}

	public void deleteStyle(String styleNum) {
		int x = session.delete("style.deleteStyle", styleNum);

		if (x != 0) {
			System.out.println("delete �꽦怨�");
		} else {
			System.out.println("delete �떎�뙣");
		}
	}

	public List<String> getDesigner() {
		List<String> list = session.selectList("style.selectDesigner");

		return list;
	}

	public int insertSelectSeq(StyleCommand styleCommand) throws SQLException {
		int i = session.insert("style.insertSelectSeq", styleCommand);
		System.out.println("번호:::"+styleCommand.getStyleNum());
		return i;
	}

}
// String onlyFileName = originalFilename.substring(0,
// originalFilename.indexOf(".")); //fileName
// String extension = originalFilename.substring(originalFilename.indexOf("."));
// // 확占쏙옙占쏙옙