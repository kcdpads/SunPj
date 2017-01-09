package finalWeb.command;

import java.sql.Timestamp;

public class ProcedureQACommand {
		private int pdqNo;
	    private String writer;
	    private String pdqTitle;
	    private String pdqContent;
	    private String passwd;
	    private Timestamp pdqDate;
	    private int pdqHits;
	    private String styleName;
	    
	    
	    public String getStyleName() {
			return styleName;
		}
		public void setStyleName(String styleName) {
			this.styleName = styleName;
		}
		public int getPdqNo() {
			return pdqNo;
		}
		public void setPdqNo(int pdqNo) {
			this.pdqNo = pdqNo;
		}
		public String getPdqTitle() {
			return pdqTitle;
		}
		public void setPdqTitle(String pdqTitle) {
			this.pdqTitle = pdqTitle;
		}
		public String getPdqContent() {
			return pdqContent;
		}
		public void setPdqContent(String pdqContent) {
			this.pdqContent = pdqContent;
		}
		public Timestamp getPdqDate() {
			return pdqDate;
		}
		public void setPdqDate(Timestamp pdqDate) {
			this.pdqDate = pdqDate;
		}
		public int getPdqHits() {
			return pdqHits;
		}
		public void setPdqHits(int pdqHits) {
			this.pdqHits = pdqHits;
		}
		private int ref;
	    private int re_step;
	    private int re_level;   
	    
	    
	
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			this.writer = writer;
		}
		
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		
		public int getRef() {
			return ref;
		}
		public void setRef(int ref) {
			this.ref = ref;
		}
		public int getRe_step() {
			return re_step;
		}
		public void setRe_step(int re_step) {
			this.re_step = re_step;
		}
		public int getRe_level() {
			return re_level;
		}
		public void setRe_level(int re_level) {
			this.re_level = re_level;
		}
}
	