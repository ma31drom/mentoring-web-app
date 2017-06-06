package com.epam.mentoring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PERSISTENT_LOGINS")
public class Credentials {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String series;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "TOKEN", unique = true, nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;

    public String getSeries() {
	return series;
    }

    public void setSeries(String series) {
	this.series = series;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public Date getLastUsed() {
	return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
	this.lastUsed = lastUsed;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((series == null) ? 0 : series.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Credentials other = (Credentials) obj;
	if (lastUsed == null) {
	    if (other.lastUsed != null)
		return false;
	} else if (!lastUsed.equals(other.lastUsed))
	    return false;
	if (series == null) {
	    if (other.series != null)
		return false;
	} else if (!series.equals(other.series))
	    return false;
	if (token == null) {
	    if (other.token != null)
		return false;
	} else if (!token.equals(other.token))
	    return false;
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Credentials [series=" + series + ", username=" + username + ", lastUsed=" + lastUsed + "]";
    }

}
