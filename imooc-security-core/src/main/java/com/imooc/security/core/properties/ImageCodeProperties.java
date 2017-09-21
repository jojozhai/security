/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * 图片验证码配置项
 * 
 * @author zhailiang
 *
 */
public class ImageCodeProperties extends SmsCodeProperties {
	
	public ImageCodeProperties() {
		setLength(4);
	}
	
	/**
	 * 图片宽
	 */
	private int width = 67;
	/**
	 * 图片高
	 */
	private int height = 23;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

}
