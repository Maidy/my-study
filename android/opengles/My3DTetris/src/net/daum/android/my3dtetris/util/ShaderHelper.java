package net.daum.android.my3dtetris.util;

import static android.opengl.GLES20.*;
import android.util.Log;

public class ShaderHelper {
	public static final String TAG = ShaderHelper.class.getSimpleName();
	
	public static int compileVertexShader(String src) {
		return compileShader(GL_VERTEX_SHADER, src);
	}
	
	public static int compileFragmentShader(String src) {
		return compileShader(GL_FRAGMENT_SHADER, src);
	}

	private static int compileShader(int type, String src) {
		final int shaderObjectId = glCreateShader(type);
		if (shaderObjectId == 0) {
			if (LoggerConfig.ON)
				Log.w(TAG, "Could not create new shader.");
			return 0;
		}
		
		glShaderSource(shaderObjectId, src);
		glCompileShader(shaderObjectId);
		
		final int[] compileStatus = new int[1];
		glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
		if (LoggerConfig.ON)
			Log.v(TAG, "Results of compiling source:\n" + src + "\n:"
					+ glGetShaderInfoLog(shaderObjectId));
		
		if (compileStatus[0] == 0) {
			glDeleteShader(shaderObjectId);
			if (LoggerConfig.ON)
				Log.w(TAG, "Compilation of shader failed.");
			return 0;
		}
		
		return shaderObjectId;
	}
	
	public static int linkProgram(int vertexShaderId, int fragmentShaderId,
			String[] attributes) {
		final int programObjectId = glCreateProgram();
		if (programObjectId == 0) {
			if (LoggerConfig.ON)
				Log.w(TAG, "Could not create new program");
			return 0;
		}
		
		glAttachShader(programObjectId, vertexShaderId);
		glAttachShader(programObjectId, fragmentShaderId);
		
		for (int i = 0; i < attributes.length; i++)
			glBindAttribLocation(programObjectId, i, attributes[i]);
		
		glLinkProgram(programObjectId);
		
		final int[] linkStatus = new int[1];
		glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);
		if (LoggerConfig.ON)
			Log.v(TAG, "Results of linking program:\n"
					+ glGetProgramInfoLog(programObjectId));
		
		if (linkStatus[0] == 0) {
			glDeleteProgram(programObjectId);
			if (LoggerConfig.ON)
				Log.w(TAG, "Linking of program failed.");
			return 0;
		}
		
		return programObjectId;
	}
	
	public static boolean validateProgram(int programObjectId) {
		glValidateProgram(programObjectId);
		final int[] validateStatus = new int[1];
		glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);
		Log.v(TAG, "Results of vlidating program:" + validateStatus[0]
				+ "\nLog: " + glGetProgramInfoLog(programObjectId));
		return validateStatus[0] != 0;
	}
	
	public static int buildProgram(String vertexShaderSource, String fragmentShaderSource,
			String[] attributes) {
		
		int program;
		
		// compile vertex shader source
		int vertexShaderId = compileVertexShader(vertexShaderSource);
		
		// compile fragment shader source
		int fragmentShaderId = compileFragmentShader(fragmentShaderSource);
		
		// vertex shader + fragment shader => program
		program = linkProgram(vertexShaderId, fragmentShaderId, attributes);
		
		if (LoggerConfig.ON)
			ShaderHelper.validateProgram(program);

		return program;
	}
	
}
