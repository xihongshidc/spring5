package com.dc.springTest.injection;

import com.dc.springTest.User;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/9 15:19
 */
public class UserHolder {
	private User user;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserHolder() {
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}

	public UserHolder(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
