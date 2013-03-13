package com.airhockey.android.programs;

import android.content.Context;
import static android.opengl.GLES20.*;

import com.airhockey.android.R;

public class TextureShaderProgram extends ShaderProgram {
	
	private static final String[] ATTRIBUTES = { A_POSITION, A_TEXTURE_COORDINATES };
	
	private static final int A_POSITION_LOCATION = 
			getAttributeLocation(ATTRIBUTES, A_POSITION);
	private static final int A_TEXTURE_COORDINATES_LOCATION = 
			getAttributeLocation(ATTRIBUTES, A_TEXTURE_COORDINATES);
	
	// uniform locations
	private final int uMatrixLocation;
	private final int uTextureUnitLocation;
	
	public TextureShaderProgram(Context context) {
		super(context, R.raw.texture_vertex_shader, R.raw.texture_fragment_shader, ATTRIBUTES);
		uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
		uTextureUnitLocation = glGetUniformLocation(program, U_TEXTURE_UNIT);
	}
	
	public void setUniforms(float[] matrix, int textureId) {
		glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
		
		glActiveTexture(GL_TEXTURE0);
		
		glBindTexture(GL_TEXTURE_2D, textureId);
		
		glUniform1i(uTextureUnitLocation, 0);
	}

	public int getPositionAttributeLocation() {
		return A_POSITION_LOCATION;
	}

	public int getTextureCoordinatesAttributeLocation() {
		return A_TEXTURE_COORDINATES_LOCATION;
	}
}
