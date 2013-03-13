package com.airhockey.android.programs;

import static android.opengl.GLES20.*;
import android.content.Context;

import com.airhockey.android.R;

public class ColorShaderProgram extends ShaderProgram {
	
	private static final String[] ATTRIBUTES = { A_POSITION, A_COLOR };
	
	private static final int A_POSITION_LOCATION = getAttributeLocation(ATTRIBUTES, A_POSITION);
	private static final int A_COLOR_LOCATION = getAttributeLocation(ATTRIBUTES, A_COLOR);
	
	// uniform locations
	private final int uMatrixLocation;
	
	public ColorShaderProgram(Context context) {
		super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader, ATTRIBUTES);
		uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
	}
	
	public void setUniforms(float[] matrix) {
		glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
	}

	public int getPositionAttributeLocation() {
		return A_POSITION_LOCATION;
	}

	public int getColorAttributeLocation() {
		return A_COLOR_LOCATION;
	}
}
