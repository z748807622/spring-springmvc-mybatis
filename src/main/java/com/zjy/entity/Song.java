package com.zjy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * song信息类
 * @author zjy
 *
 */
@Table(name = "songinfo")
public class Song implements Serializable {
	private static final long serialVersionUID = -5892227086731605460L;
	
	@Id
	@Column(name="fileHash")
	private String fileHash;
	
	@Column(name="songName")
	private String songName;
	
	private String img;
	
	private String lyrics;
	
	@Column(name="singerName")
	private String singerName;
	
	private String url;
	
	@Column(name="mvHash")
	private String mvHash;
	public Song(){}
	
	public Song(String fileHash, String songName, String img, String lyrics,
			String singerName, String url, String mvHash) {
		super();
		this.fileHash = fileHash;
		this.songName = songName;
		this.img = img;
		this.lyrics = lyrics;
		this.singerName = singerName;
		this.url = url;
		this.mvHash = mvHash;
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
	public String toString() {
		return "Song [fileHash=" + fileHash + ", songName=" + songName
				+ ", img=" + img + ", lyrics=" + lyrics + ", singerName="
				+ singerName + ", url=" + url + ", mvHash=" + mvHash + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((fileHash == null) ? 0 : fileHash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (fileHash == null) {
			if (other.fileHash != null)
				return false;
		} else if (!fileHash.equals(other.fileHash))
			return false;
		return true;
	}
	
}
