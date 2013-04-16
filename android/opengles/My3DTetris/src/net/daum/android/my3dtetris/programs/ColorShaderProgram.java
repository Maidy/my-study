package net.daum.android.my3dtetris.programs;

import static android.opengl.GLES20.*;
import net.daum.android.my3dtetris.R;
import android.content.Context;

public class ColorShaderProgram extends ShaderProgram {
	
	private static final String[] ATTRIBUTES = { A_POSITION, A_COLOR };
	
	private static final int A_POSITION_LOCATION = getAttributeLocation(ATTRIBUTES, A_POSITION);
	
	// uniform locations
	private final int uMatrixLocation;
	private final int uColorLocation;
	
	public ColorShaderProgram(Context context) {
		super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader, ATTRIBUTES);
		uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
		uColorLocation = glGetUniformLocation(program, U_COLOR);
	}
	
	public void setUniforms(float[] matrix, float r, float g, float b) {
		glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
		glUniform4f(uColorLocation, r, g, b, 1f);
	}

	public int getPositionAttributeLocation() {
		return A_POSITION_LOCATION;
	}
}
