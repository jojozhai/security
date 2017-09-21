/**
 * 
 */
package com.imooc.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.imooc.security.core.validate.code.ValidateCode;


/**
 * 图片验证码
 * @author zhailiang
 *
 */
public class ImageCode extends ValidateCode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6020470039852318468L;
	
	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
