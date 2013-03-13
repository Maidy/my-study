package com.airhockey.android.programs;

import static android.opengl.GLES20.glUseProgram;

import java.util.Arrays;

import android.content.Context;

import com.airhockey.android.util.ShaderHelper;
import com.airhockey.android.util.TextResourceReader;

public class ShaderProgram {
	
	protected static final String U_MATRIX = "u_Matrix";
	protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
	
	protected static final String A_POSITION = "a_Position";
	protected static final String A_COLOR = "a_Color";
	protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
	
	protected final int program;
	
	protected ShaderProgram(Context context, int vertexShaderResourceId,
			int fragmentShaderResourceId, String[] attributes) {
		
		// read a vertex shader source from resource
		String vertexShaderSource = 
				TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId);
		
		// read a fragment shader source from a resource
		String fragmentShaderSource = 
				TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId);
		
		// build program from shader sources
		program = ShaderHelper.buildProgram(
				vertexShaderSource, fragmentShaderSource, attributes);
	}
	
	protected static int getAttributeLocation(String[] array, String attribute) {
		return Arrays.asList(array).indexOf(attribute);
	}
	
	public void useProgram() {
		// use program
		glUseProgram(program);
	}
}
