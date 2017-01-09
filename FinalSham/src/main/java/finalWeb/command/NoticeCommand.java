package finalWeb.command;

import java.sql.Timestamp;

public class NoticeCommand {
		private int noticeNo;
	    private String writer;
	    private String noticeTitle;
	    private String noticeContent;
	    private String passwd;
	    private Timestamp noticeDate;
	    private int noticeHits;
	    private int ref;
	    private int re_step;
	    private int re_level;   
	    
	    
		public int getNoticeNo() {
			return noticeNo;
		}
		public void setNoticeNo(int noticeNo) {
			this.noticeNo = noticeNo;
		}
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			this.writer = writer;
		}
		public String getNoticeTitle() {
			return noticeTitle;
		}
		public void setNoticeTitle(String noticeTitle) {
			this.noticeTitle = noticeTitle;
		}
		public String getNoticeContent() {
			return noticeContent;
		}
		public void setNoticeContent(String noticeContent) {
			this.noticeContent = noticeContent;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public Timestamp getNoticeDate() {
			return noticeDate;
		}
		public void setNoticeDate(Timestamp noticeDate) {
			this.noticeDate = noticeDate;
		}
		public int getNoticeHits() {
			return noticeHits;
		}
		public void setNoticeHits(int noticeHits) {
			this.noticeHits = noticeHits;
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
	