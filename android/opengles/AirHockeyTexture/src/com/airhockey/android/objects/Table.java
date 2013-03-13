package com.airhockey.android.objects;

import com.airhockey.android.data.VertexArray;
import com.airhockey.android.programs.TextureShaderProgram;
import static android.opengl.GLES20.*;

public class Table {

	// float의 byte 크기
	private static final int BYTES_PER_FLOAT = 4;

	// position을 구성하는 요소 개수, 2 : x, y
	private static final int POSITION_COMPONENT_COUNT = 2;
	
	// texture coordinates 요소 개수 2 : S, T
	private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
	
	// 다음번 데이터까지의 byte 수 : 한 개 데이터의 크기(byte)
	private static final int STRIDE = 
			(POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * BYTES_PER_FLOAT;
	
	// data, 옴 책에서 설명하는것과 좀... 책에서 대상 이미지는 512x1024인데, 다운로드 받은 이미지는 512x512
	float[] VERTEX_DATA = {
			// table triangle fan
			   0f,    0f, 0.5f, 0.5f,
			-0.5f, -0.8f,   0f, 0.9f,
			 0.5f, -0.8f,   1f, 0.9f,
			 0.5f,  0.8f,   1f, 0.1f,
			-0.5f,  0.8f,   0f, 0.1f,
			-0.5f, -0.8f,   0f, 0.9f
			// X,     Y,    S,    T
	};
	
	private final VertexArray vertexArray;
	
	public Table() {
		vertexArray = new VertexArray(VERTEX_DATA);
	}
	
	public void bindData(TextureShaderProgram textureProgram) {
		vertexArray.setVertexAttributePointer(
				0,
				textureProgram.getPositionAttributeLocation(),
				POSITION_COMPONENT_COUNT,
				STRIDE);
		
		vertexArray.setVertexAttributePointer(
				POSITION_COMPONENT_COUNT,
				textureProgram.getTextureCoordinatesAttributeLocation(),
				TEXTURE_COORDINATES_COMPONENT_COUNT,
				STRIDE);
	}
	
	public void draw() {
		glDrawArrays(GL_TRIANGLE_FAN, 0, VERTEX_DATA.length /
				(POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT));
	}

	
}
