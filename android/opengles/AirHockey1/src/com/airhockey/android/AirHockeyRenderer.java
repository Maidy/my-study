package com.airhockey.android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.*;
import android.opengl.GLSurfaceView.Renderer;

public class AirHockeyRenderer implements Renderer {
	
	private static final int POSITION_COMPONENT_COUNT = 2;
	private static final int BYTES_PER_FLOAT = 4;
	
	float[] tableVerticesWithTriangles = {
			// triangle 1
			0f, 0f,
			9f, 14f,
			0f, 14f,
			
			// triangle 2
			0f, 0f,
			9f, 0f,
			9f, 14f,
			
			// center line
			0f, 7f,
			9f, 7f,
			
			// mallets
			4.5f, 12f,
			4.5f, 2f
	};
	
	private final FloatBuffer vertexData;
	
	public AirHockeyRenderer() {
		vertexData = ByteBuffer
				.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(tableVerticesWithTriangles);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// gl 인자는 OpenGL 1.0 에서만 사용된다.
		// OpenGL 2.0에서는 android.opengl.GLES20 클래스의 static method 이용
		
		glClear(GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glClearColor(1.f, 0.f, 0.f, 0.f);
	}
}
