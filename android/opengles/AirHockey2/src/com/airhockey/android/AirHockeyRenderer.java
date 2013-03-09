package com.airhockey.android;

import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.airhockey.android.util.LoggerConfig;
import com.airhockey.android.util.ShaderHelper;
import com.airhockey.android.util.TextResourceReader;

public class AirHockeyRenderer implements Renderer {
	
	float[] tableVerticesWithTriangles = {
			
			// X, Y, R, G, B
			
			// table triangle fan
			   0f,     0f,   1f,   1f,   1f,
			-0.5f,  -0.5f, 0.7f, 0.7f, 0.7f,
			 0.5f,  -0.5f, 0.7f, 0.7f, 0.7f,
			 0.5f,   0.5f, 0.7f, 0.7f, 0.7f,
			-0.5f,   0.5f, 0.7f, 0.7f, 0.7f,
			-0.5f,  -0.5f, 0.7f, 0.7f, 0.7f,
			
			// center line
			-0.5f,     0f,   1f,   0f,   0f,
			 0.5f,     0f,   1f,   0f,   0f,   
			
			// mallet 1
			   0f, -0.25f,  0f,   0f,   1f,

			// mallet 2
			   0f,  0.25f,  1f,   0f,   0f
	};
	
	private static final String A_POSITION = "a_Position";
	private static final String A_COLOR = "a_Color";
	private static final String[] ATTRIBUTES = { A_POSITION, A_COLOR };
	private static final int A_POSITION_LOCATION = Arrays.asList(ATTRIBUTES).indexOf(A_POSITION);
	private static final int A_COLOR_LOCATION = Arrays.asList(ATTRIBUTES).indexOf(A_COLOR);
	
	// float의 byte 크기
	private static final int BYTES_PER_FLOAT = 4;
	
	// position을 구성하는 요소 개수, 2 : x, y
	private static final int POSITION_COMPONENT_COUNT = 2;
	
	// color를 구성하는 요소 개수, 3 : red, green, blue
	private static final int COLOR_COMPONENT_COUNT = 3;
	
	// 다음번 데이터까지의 byte 수 : 한 개 데이터의 크기(byte)
	private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;

	private final Context context;
	private final FloatBuffer vertexData;

	private int program;
	
	public AirHockeyRenderer(Context context) {
		this.context = context;
		
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
		
		glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
		glDrawArrays(GL_LINES, 6, 2);
		glDrawArrays(GL_POINTS, 8, 1);
		glDrawArrays(GL_POINTS, 9, 1);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glClearColor(0.f, 0.f, 0.f, 0.f);
		
		// read a vertex shader source from resource
		String vertexShaderSource = 
				TextResourceReader.readTextFileFromResource(context,
						R.raw.simple_vertex_shader);
		
		// read a fragment shader source from a resource
		String fragmentShaderSource = 
				TextResourceReader.readTextFileFromResource(context,
						R.raw.simple_fragment_shader);
		
		// compile vertex shader source
		int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
		
		// compile fragment shader source
		int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);

		// vertex shader + fragment shader => program
		program = ShaderHelper.linkProgram(vertexShader, fragmentShader, ATTRIBUTES);
		if (LoggerConfig.ON)
			ShaderHelper.validateProgram(program);
		
		// use program
		glUseProgram(program);
		
		// 포인터가 첫번째 position attribute를 가리킨다.
		vertexData.position(0);
		
		// 데이터와 shader의 a_Position을 연결한다.
		glVertexAttribPointer(A_POSITION_LOCATION, POSITION_COMPONENT_COUNT, GL_FLOAT, false, STRIDE, vertexData);
		glEnableVertexAttribArray(A_POSITION_LOCATION);
		
		// 포인터가 첫번째 color attribute를 가리킨다.
		vertexData.position(POSITION_COMPONENT_COUNT);
		
		// 데이터와 shader의 a_Color를 연결한다.
		glVertexAttribPointer(A_COLOR_LOCATION, COLOR_COMPONENT_COUNT, GL_FLOAT, false, STRIDE, vertexData);
		glEnableVertexAttribArray(A_COLOR_LOCATION);
	}
}
