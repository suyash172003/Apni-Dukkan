package com.example.Dukkan.Model;

public class Otp {
	
	private String Gotp;
	private String Eotp;
	
    public String getGotp() {
		return Gotp;
	}

	public void setGotp(String gotp) {
		Gotp = gotp;
	}

	public String getEotp() {
		return Eotp;
	}

	public void setEotp(String eotp) {
		Eotp = eotp;
	}

	public Otp(String gotp, String eotp) {
		super();
		Gotp = gotp;
		Eotp = eotp;
	}

	public Otp(){}

}
