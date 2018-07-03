package com.zjy.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kutop500")
public class KuGouTop500 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8796469320872071532L;
	@Id
	private int rank;
	private String fileHash;
	private String songName;
	private String img;
	private String lyrics;
	private String singerName;
	private String url;
	private String mvHash;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getFileHash() {
		return fileHash;
	}
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMvHash() {
		return mvHash;
	}
	public void setMvHash(String mvHash) {
		this.mvHash = mvHash;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileHash == null) ? 0 : fileHash.hashCode());
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
		KuGouTop500 other = (KuGouTop500) obj;
		if (fileHash == null) {
			if (other.fileHash != null)
				return false;
		} else if (!fileHash.equals(other.fileHash))
			return false;
		return true;
	}
	
}
