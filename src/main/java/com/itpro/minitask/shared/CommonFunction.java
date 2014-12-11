package com.itpro.minitask.shared;

public class CommonFunction {
	public boolean isBlank(String s) {
		return (s == null) || (s.trim().length() == 0);
	}
}
