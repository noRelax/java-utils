/*****************************************************************************

 Description: This class is used to ceate the mesh
 Download by http://www.codefans.net
 Created By: Oscar Vivall 2006-01-09
 @file        MC.java

 COPYRIGHT All rights reserved Sony Ericsson Mobile Communications AB 2004.
 The software is the copyrighted work of Sony Ericsson Mobile Communications AB.
 The use of the software is subject to the terms of the end-user license 
 agreement which accompanies or is included with the software. The software is 
 provided "as is" and Sony Ericsson specifically disclaim any warranty or 
 condition whatsoever regarding merchantability or fitness for a specific 
 purpose, title or non-infringement. No warranty of any kind is made in 
 relation to the condition, suitability, availability, accuracy, reliability, 
 merchantability and/or non-infringement of the software provided herein.

 *****************************************************************************/

import javax.microedition.midlet.*;
import javax.microedition.m3g.*;

public class MC {
    public static Mesh createItem(){
    	short [] POINTS=new short[]{
		-4,-1, 1,     4,-1, 1,    4, 1, 1,  	-4, 1, 1, //前面
		 4,-1,-1,    -4,-1,-1,   -4, 1,-1,  	 4, 1,-1, //后面
		-4,-1,-1,    -4,-1, 1,   -4, 1, 1,  	-4, 1,-1, //左面
		 4,-1, 1,     4,-1,-1,    4, 1,-1,  	 4, 1, 1, //右面
		-4, 1, 1,     4, 1, 1,    4, 1,-1,  	-4, 1,-1, //上面
		-4,-1,-1,     4,-1,-1,    4,-1, 1,  	-4,-1, 1, //下面	
	};
        short []TEXTCOORDINATES = new short[] {0, 1,      1, 1,       1, 0,       0, 0,
                                               1, 0,      0, 0,       0, 1,       1, 1,
                                               0, 1,      1, 1,       1, 0,       0, 0,
                                               0, 1,      1, 1,       1, 0,       0, 0,
                                               0, 1,      1, 1,       1, 0,       0, 0,
                                               0, 1,      1, 1,       1, 0,       0, 0};
       System.out.println("text");
      int []FRONT_INDICES = new int[] {0, 1, 3, 2};
      int []BACK_INDICES = new int[] {4, 5, 7, 6};
      int []LEFT_INDICES = new int[] {8, 9, 11, 10};
      int []RIGHT_INDICES = new int[] {12, 13, 15, 14};
      int []TOP_INDICES = new int[] {16, 17, 19, 18};
      int []BOTTOM_INDICES = new int[] {20, 21, 23, 22};                                   
      int[] LENGTHS = new int[] {4}; 

        VertexArray POSITIONS_ARRAY, TEXTURE_ARRAY; 
        IndexBuffer []INDEX_BUFFER = new IndexBuffer[6];
       // float textureRepeat;
        // initialize the common arrays 
        POSITIONS_ARRAY = new VertexArray(POINTS.length/3, 3, 2); 
        POSITIONS_ARRAY.set(0, POINTS.length/3, POINTS); 
        TEXTURE_ARRAY = new VertexArray(TEXTCOORDINATES.length/2, 2, 2);
        TEXTURE_ARRAY.set(0, TEXTCOORDINATES.length/2, TEXTCOORDINATES);
        
        INDEX_BUFFER[0] = new TriangleStripArray(FRONT_INDICES, LENGTHS);
        INDEX_BUFFER[1] = new TriangleStripArray(BACK_INDICES, LENGTHS);
        INDEX_BUFFER[2] = new TriangleStripArray(LEFT_INDICES, LENGTHS);
        INDEX_BUFFER[3] = new TriangleStripArray(RIGHT_INDICES, LENGTHS);
        INDEX_BUFFER[4] = new TriangleStripArray(TOP_INDICES, LENGTHS);
        INDEX_BUFFER[5] = new TriangleStripArray(BOTTOM_INDICES, LENGTHS);
        
        VertexBuffer vertexBuffer = new VertexBuffer(); 
        vertexBuffer.setPositions(POSITIONS_ARRAY, 1.0f, null); 
        vertexBuffer.setTexCoords(0, TEXTURE_ARRAY, 1.0f, null);
        vertexBuffer.setDefaultColor(0x80FFFFFF);
        Mesh mesh = new Mesh(vertexBuffer, INDEX_BUFFER, null); 

        Appearance []appearance = new Appearance[6];
        PolygonMode polygonMode = new PolygonMode();
        polygonMode.setPerspectiveCorrectionEnable(true);
        polygonMode.setCulling(PolygonMode.CULL_NONE);

        CompositingMode cm = new CompositingMode();
        cm.setBlending(CompositingMode.ALPHA);
        
        for(int i=0; i<appearance.length; i++){
            appearance[i] = new Appearance();
            appearance[i].setPolygonMode(polygonMode);
//            appearance[i].setCompositingMode(cm); // Add transparancy to the mesh using the default color.
            mesh.setAppearance(i, appearance[i]);
        }

        return mesh;
    }
}
