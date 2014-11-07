package com.includesys.sm.dto.member;

public class ZipcodeDoro {
	

	//검색조건
	private String searchDoro;
	private String tblName;
	
	//결과
	private String post_num;
	private String si_do;
	private String si_gun_gu;
	private String eup_myn;
	private String street_name;
	private String beopjeong;
	private String ri;
	 
	private String jebun;
	private String jebun1;
	private String jebun2;
	 
	private String building;
	private String building1;
	private String building2;
	
	
	public String getSearchDoro() {
		return searchDoro;
	}
	public void setSearchDoro(String searchDoro) {
		this.searchDoro = searchDoro;
	}
	public String getTblName() {
		return tblName;
	}
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}
	public String getPost_num() {
		return post_num;
	}
	public void setPost_num(String post_num) {
		this.post_num = post_num;
	}
	public String getSi_do() {
		return si_do;
	}
	public void setSi_do(String si_do) {
		this.si_do = si_do;
	}
	public String getSi_gun_gu() {
		return si_gun_gu;
	}
	public void setSi_gun_gu(String si_gun_gu) {
		this.si_gun_gu = si_gun_gu;
	}
	public String getEup_myn() {
		return eup_myn;
	}
	public void setEup_myn(String eup_myn) {
		this.eup_myn = eup_myn;
	}
	public String getBeopjeong() {
		return beopjeong;
	}
	public void setBeopjeong(String beopjeong) {
		this.beopjeong = beopjeong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getJebun() {
		return jebun1 +" "+jebun2;
	}
	public void setJebun(String jebun) {
		this.jebun = jebun;
	}
	public String getJebun1() {
		return jebun1;
	}
	public void setJebun1(String jebun1) {
		this.jebun1 = jebun1;
	}
	public String getJebun2() {
		return jebun2;
	}
	public void setJebun2(String jebun2) {
		this.jebun2 = jebun2;
	}
	public String getBuilding() {
		return building1+" "+building2;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getBuilding1() {
		return building1;
	}
	public void setBuilding1(String building1) {
		this.building1 = building1;
	}
	public String getBuilding2() {
		return building2;
	}
	public void setBuilding2(String building2) {
		this.building2 = building2;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

}
