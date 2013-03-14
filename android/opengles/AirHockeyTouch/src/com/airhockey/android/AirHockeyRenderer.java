package com.airhockey.android;

import static android.opengl.GLES20.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

import com.airhockey.android.objects.Mallet;
import com.airhockey.android.objects.Puck;
import com.airhockey.android.objects.Table;
import com.airhockey.android.programs.ColorShaderProgram;
import com.airhockey.android.programs.TextureShaderProgram;
import com.airhockey.android.util.MatrixHelper;
import com.airhockey.android.util.TextureHelper;

public class AirHockeyRenderer implements Renderer {
	
	private final Context context;
	
	private final float[] projectionMatrix = new float[16];
	private final float[] modelMatrix = new float[16];
	private final float[] viewMatrix = new float[16];
	private final float[] viewProjectionMatrix = new float[16];
	private final float[] modelViewProjectionMatrix = new float[16];
	
	private TextureShaderProgram textureProgram;
	private ColorShaderProgram colorProgram;
	
	private Table table;
	private Mallet mallet;
	private Puck puck;
	
	private int texture;
	
	public AirHockeyRenderer(Context context) {
		this.context = context;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// gl 인자는 OpenGL 1.0 에서만 사용된다.
		// OpenGL 2.0에서는 android.opengl.GLES20 클래스의 static method 이용
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		// viewProjectionMatrix = projectionMatrix * viewMatrix
		Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
		
		positionTableInScene();
		
		// draw table
		textureProgram.useProgram();
		textureProgram.setUniforms(modelViewProjectionMatrix, texture);
		table.bindData(textureProgram);
		table.draw();
		
		// draw mallet 
		positionObjectInScene(0f, mallet.height / 2f, -0.4f);
		colorProgram.useProgram();
		colorProgram.setUniforms(modelViewProjectionMatrix, 1f, 0f, 0f);
		mallet.bindData(colorProgram);
		mallet.draw();
		
		positionObjectInScene(0f, mallet.height / 2f, 0.4f);
		colorProgram.setUniforms(modelViewProjectionMatrix, 0f, 0f, 1f);
		mallet.draw();
		
		positionObjectInScene(0f, puck.height / 2f, 0f);
		colorProgram.setUniforms(modelViewProjectionMatrix, 0.8f, 0.8f, 1f);
		puck.bindData(colorProgram);
		puck.draw();
	}

	private void positionObjectInScene(float x, float y, float z) {
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.translateM(modelMatrix, 0, x, y, z);
		Matrix.multiplyMM(modelViewProjectionMatrix, 0, viewProjectionMatrix, 0, modelMatrix, 0);
	}

	private void positionTableInScene() {
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.rotateM(modelMatrix, 0, -90f, 1f, 0f, 0f);
		Matrix.multiplyMM(modelViewProjectionMatrix, 0, viewProjectionMatrix, 0, modelMatrix, 0);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		
		glViewport(0, 0, width, height);
		
		// surface의 width, height를 기반으로 perspective projection matrix를 생성 
		MatrixHelper.perspectiveM(projectionMatrix, 45f, (float) width / (float) height, 1f, 10f);
		
		Matrix.setLookAtM(viewMatrix, 0, 0f, 1.2f, 2.2f, 0f, 0f, 0f, 0f, 1f, 0f);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glClearColor(0.f, 0.f, 0.f, 0.f);
		
		table = new Table();
		mallet = new Mallet(0.08f, 0.15f, 32);
		puck = new Puck(0.06f, 0.02f, 32);
		
		textureProgram = new TextureShaderProgram(context);
		colorProgram = new ColorShaderProgram(context);
		
		texture = TextureHelper.loadTexture(context, R.drawable.air_hockey_surface);
	}
}
