package com.hdxinfo.util;
import java.awt.AlphaComposite; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import java.awt.RenderingHints; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.InputStream; 
import java.io.OutputStream; 
 
import javax.imageio.ImageIO;

public class ImageMarkLogoByText {
	/**
     * ��ͼƬ���ˮӡ
     * @param logoText
     * @param srcImgPath
     * @param targerPath
     */ 
    public static void markByText(String logoText, String srcImgPath, 
            String targerPath) { 
        markByText(logoText, srcImgPath, targerPath, null); 
    } 
 
    /**
     * ��ͼƬ���ˮӡ��������ˮӡ����ת�Ƕ�
     * @param logoText
     * @param srcImgPath
     * @param targerPath
     * @param degree
     */ 
    public static void markByText(String logoText, String srcImgPath, 
            String targerPath, Integer degree) { 
        // ��ͼƬ��·�� 
        InputStream is = null; 
        OutputStream os = null; 
        try { 
            Image srcImg = ImageIO.read(new File(srcImgPath)); 
 
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), 
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
 
            // �õ����ʶ��� 
            // Graphics g= buffImg.getGraphics(); 
            Graphics2D g = buffImg.createGraphics(); 
 
            // ���ö��߶εľ��״��Ե���� 
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
 
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg 
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 
 
            if (null != degree) { 
                // ����ˮӡ��ת 
                g.rotate(Math.toRadians(degree), 
                        (double) buffImg.getWidth() / 2, (double) buffImg 
                                .getHeight() / 2); 
            } 
 
            // ������ɫ 
            g.setColor(Color.red); 
 
            // ���� Font 
            g.setFont(new Font("����", Font.BOLD, 30)); 
 
            float alpha = 0.5f; 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 
                    alpha)); 
 
            // ��һ����->���õ����ݣ�������������->������ͼƬ�ϵ�����λ��(x,y) . 
            g.drawString(logoText, srcImg.getWidth(null)/4, srcImg.getHeight(null)/2); 
 
            g.dispose(); 
 
            os = new FileOutputStream(targerPath); 
 
            // ����ͼƬ 
            ImageIO.write(buffImg, "JPG", os); 
 
            System.out.println("ͼƬ����������ӡ�¡�����������"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (null != is) 
                    is.close(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
            try { 
                if (null != os) 
                    os.close(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
    } 

    public static void main(String[] args) { 
        String srcImgPath = "d:/test/mypic.jpg"; 
        String logoText = "[ Ӣ������ˮӡ http://wangyingda415.com ]"; 
        String targerPath = "d:/test/my3.jpg"; 
 
        String targerPath2 = "d:/test/my4.jpg"; 
 
        // ��ͼƬ���ˮӡ 
        ImageMarkLogoByText.markByText(logoText, srcImgPath, targerPath); 
 
        // ��ͼƬ���ˮӡ,ˮӡ��ת-45 
        ImageMarkLogoByText.markByText(logoText, srcImgPath, targerPath2, -45); 
    } 


}
