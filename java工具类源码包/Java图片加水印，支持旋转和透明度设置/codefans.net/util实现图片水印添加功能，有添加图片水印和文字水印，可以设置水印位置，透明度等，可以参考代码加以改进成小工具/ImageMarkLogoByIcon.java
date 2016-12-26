package com.hdxinfo.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
//Download by http://www.codefans.net
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/*
 * ͼƬ����ˮӡ����
 * 
 */
public class ImageMarkLogoByIcon {

	/**
	 * ��ͼƬ���ˮӡ
	 * @param iconPath ˮӡͼƬ·��
	 * @param srcImgPath ԴͼƬ·��
	 * @param tagertPath Ŀ��ͼƬ·��
	 */
	public static void markImageByIcon(String iconPath,String srcImgPath,String tagertPath){
	  
		markImageByIcon(iconPath,srcImgPath,tagertPath,null);
	}
 /**
  * ��ͼƬ���ˮӡ����������ͼƬˮӡ�ĽǶ�
  * @param iconPath ˮӡͼƬ·��
  * @param srcImgPath ԴͼƬ·��
  * @param tagertPath Ŀ��ͼƬ·��
  * @param degree  ˮӡͼƬ��ת�Ƕ�
  */
	public static void markImageByIcon(String iconPath,String srcImgPath,String tagertPath,Integer degree){
	
		OutputStream os = null;
		
		try{
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null),BufferedImage.TYPE_INT_RGB);
			
			//�õ����ʶ���
			Graphics2D g = buffImg.createGraphics();
			
			//���ö��߶ξ��״��Ե����
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,null);
			
			if(null!=degree){
			//����ˮӡͼƬ��ת
			g.rotate(Math.toRadians(degree),(double)buffImg.getWidth()/2,(double)buffImg.getHeight()/2);
			}
			//ˮӡͼƬ��·����ˮӡһ���ʽ��gif��png,����ͼƬ��������͸����
			ImageIcon imgIcon = new ImageIcon(iconPath);
			
			//�õ�Image����
			Image img = imgIcon.getImage();
			
			float alpha = 0.5f;  //͸����
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			
			//��ʾˮӡͼƬ��λ��
			g.drawImage(img,100,100,null);
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			
			g.dispose();
			
			os = new FileOutputStream(tagertPath);
			
			//����ͼƬ
			ImageIO.write(buffImg, "JPG", os);
			System.out.println("ͼƬˮӡ��ӳɹ�������");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try{if(null!=os){os.close();}}catch(Exception e){e.printStackTrace();}
		}
		
	}
	
	//���ܲ���
	public static void main(String[] args){
		
		 String srcImgPath = "d:/test/mypic.jpg"; 
	        String iconPath = "d:/test/51.gif"; 
	        String targerPath = "d:/test/my1.jpg"; 
	        String targerPath2 = "d:/test/my2.jpg"; 
	        // ��ͼƬ���ˮӡ 
	        ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath); 
	        // ��ͼƬ���ˮӡ,ˮӡ��ת-45 
	        ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath2, 
	                -45); 

	}
}
