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
	
	private static final int POSITION_COMPONENT_COUNT = 2;
	private static final int BYTES_PER_FLOAT = 4;
	
	float[] tableVerticesWithTriangles = {
			// table triangle fan
			   0f,    0f,
			-0.5f, -0.5f,
			 0.5f, -0.5f,
			 0.5f,  0.5f,
			-0.5f,  0.5f,
			-0.5f, -0.5f,
			
			// center line
			-0.5f,  0.0f,
			 0.5f,  0.0f,
			
			// mallets
			 0.0f, -0.25f,
			 0.0f,  0.25f
			 
			 // center puck
			 
			 // table border
			 
	};

	private final Context context;
	
	private final FloatBuffer vertexData;
	
	private static final String A_POSITION = "a_Position";
	private static final String[] ATTRIBUTES = { A_POSITION };
	private static final int A_POSITION_LOCATION =
			Arrays.asList(ATTRIBUTES).indexOf(A_POSITION);
	
	private static final String U_COLOR = "u_Color";
	private int uColorLocation;
	
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
		
		glUniform4f(uColorLocation, 1.f, 1.f, 1.f, 1.f);
		glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
		
		glUniform4f(uColorLocation, 1.f, 0.f, 0.f, 1.f);
		glDrawArrays(GL_LINES, 6, 2);
		
		glUniform4f(uColorLocation, 0.f, 0.f, 1.f, 1.f);
		glDrawArrays(GL_POINTS, 8, 1);
		
		glUniform4f(uColorLocation, 1.f, 0.f, 0.f, 1.f);
		glDrawArrays(GL_POINTS, 9, 1);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glClearColor(0.f, 0.f, 0.f, 0.f);
		
		String vertexShaderSource = 
				TextResourceReader.readTextFileFromResource(context,
						R.raw.simple_vertex_shader);
		String fragmentShaderSource = 
				TextResourceReader.readTextFileFromResource(context,
						R.raw.simple_fragment_shader);
		
		int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
		int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);

		program = ShaderHelper.linkProgram(vertexShader, fragmentShader, ATTRIBUTES);
		if (LoggerConfig.ON)
			ShaderHelper.validateProgram(program);
		glUseProgram(program);
		
		uColorLocation = glGetUniformLocation(program, U_COLOR);
		
		vertexData.position(0);
		glVertexAttribPointer(A_POSITION_LOCATION, POSITION_COMPONENT_COUNT,
				GL_FLOAT, false, 0, vertexData);
		
		glEnableVertexAttribArray(A_POSITION_LOCATION);
		
	}
}
