package net.daum.android.my3dtetris;

import static android.opengl.GLES20.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import net.daum.android.my3dtetris.objects.BoxHole;
import net.daum.android.my3dtetris.programs.ColorShaderProgram;
import net.daum.android.my3dtetris.util.MatrixHelper;
import android.content.Context;
import android.opengl.Matrix;
import android.opengl.GLSurfaceView.Renderer;

public class MainRenderer implements Renderer {
	
	private Context mContext;
	
	private final float[] projectionMatrix = new float[16];
	private final float[] modelMatrix = new float[16];
	private final float[] viewMatrix = new float[16];
	private final float[] viewProjectionMatrix = new float[16];
	private final float[] modelViewProjectionMatrix = new float[16];
	
	private ColorShaderProgram mColorProgram;

	private BoxHole mBoxHole;
	
	public MainRenderer(Context context) {
		mContext = context;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glClearColor(0.f, 0.f, 0.f, 0.f);
		
		mBoxHole = new BoxHole();
		
		mColorProgram = new ColorShaderProgram(mContext);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		glViewport(0, 0, width, height);
		
		// surface의 width, height를 기반으로 perspective projection matrix를 생성 
		MatrixHelper.perspectiveM(projectionMatrix, 45f, (float) width / (float) height, 1f, 10f);
		
		// camera 위치
		Matrix.setLookAtM(viewMatrix, 0, 0f, 1.2f, 2.2f, 0f, 0f, 0f, 0f, 1f, 0f);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		glClear(GL_COLOR_BUFFER_BIT);
		
		// viewProjectionMatrix = projectionMatrix * viewMatrix
		Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
		
		
	}

	public void handleTouchPress(float normalizedX, float normalizedY) {
		
	}

	public void handleTouchDrag(float normalizedX, float normalizedY) {

	}
}
