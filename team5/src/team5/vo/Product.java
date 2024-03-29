package team5.vo;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
	/*
	 * @Author 우성준
	 * 
	 * @Since 23/02/06 Product VO 담당 writher, status 추가 category, proName 생성자 지역변수
	 * 순서 바뀜 생성자 및 변수 순서 바뀜 status, reviewlist 삭제 rnoCnt(글번호카운트) 추가
	 */
	private int proId;
	private String proName;
	private String category;
	private int proCnt;
	private int proPrice;
	private Date regDate;
	private String writer;
	private Long rnoCnt;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int proId, String category, String proName, int proCnt, int proPrice, String writer) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.category = category;
		this.proCnt = proCnt;
		this.proPrice = proPrice;
		this.writer = writer;
	}

	public Product(int proId, String proName, String category, int proCnt, int proPrice, Date regDate, String writer,
			Long rnoCnt) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.category = category;
		this.proCnt = proCnt;
		this.proPrice = proPrice;
		this.regDate = regDate;
		this.writer = writer;
		this.rnoCnt = rnoCnt;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getProCnt() {
		return proCnt;
	}

	public void setProCnt(int proCnt) {
		this.proCnt = proCnt;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Long getRnoCnt() {
		return rnoCnt;
	}

	public void setRnoCnt(Long rnoCnt) {
		this.rnoCnt = rnoCnt;
	}

	@Override
	public String toString() {
		return proId + "       " + proName + "      " + category + "      " + proCnt + "    " + proPrice + "   "
				+ regDate + "   " + writer;
	}

}
