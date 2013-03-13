package com.airhockey.android.objects;

import com.airhockey.android.data.VertexArray;
import com.airhockey.android.programs.ColorShaderProgram;

import static android.opengl.GLES20.*;

public class Mallet {

	// float의 byte 크기
	private static final int BYTES_PER_FLOAT = 4;

	// position을 구성하는 요소 개수, 2 : x, y
	private static final int POSITION_COMPONENT_COUNT = 2;

	// color 요소 개수 2 : R, G, B
	private static final int COLOR_COMPONENT_COUNT = 3;

	// 다음번 데이터까지의 byte 수 : 한 개 데이터의 크기(byte)
	private static final int STRIDE = 
			(POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;

	// data
	float[] VERTEX_DATA = {
			// mallet 1
			   0f, -0.4f,   0f,   0f,   1f,
			// mallet 2
			   0f,  0.4f,   1f,   0f,   0f
			// X      Y     R     G     B
	};

	private final VertexArray vertexArray;
	
	public Mallet() {
		vertexArray = new VertexArray(VERTEX_DATA);
	}
	
	public void bindData(ColorShaderProgram colorProgram) {
		vertexArray.setVertexAttributePointer(
				0,
				colorProgram.getPositionAttributeLocation(),
				POSITION_COMPONENT_COUNT,
				STRIDE);
		vertexArray.setVertexAttributePointer(
				POSITION_COMPONENT_COUNT,
				colorProgram.getColorAttributeLocation(),
				COLOR_COMPONENT_COUNT,
				STRIDE);
	}
	
	public void draw() {
		glDrawArrays(GL_POINTS, 0, VERTEX_DATA.length /
				(POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT));
	}

}
