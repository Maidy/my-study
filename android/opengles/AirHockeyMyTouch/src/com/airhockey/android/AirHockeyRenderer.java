package com.airhockey.android;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;

import com.airhockey.android.objects.Mallet;
import com.airhockey.android.objects.Puck;
import com.airhockey.android.objects.Table;
import com.airhockey.android.programs.ColorShaderProgram;
import com.airhockey.android.programs.TextureShaderProgram;
import com.airhockey.android.util.Geometry;
import com.airhockey.android.util.MatrixHelper;
import com.airhockey.android.util.TextureHelper;

public class AirHockeyRenderer implements Renderer {
	
	private static final String TAG = AirHockeyRenderer.class.getSimpleName();
	
	// table boundary
	private final float leftBound = -0.5f;
	private final float rightBound = 0.5f;
	private final float farBound = -0.8f;
	private final float nearBound = 0.8f;

	private final Context context;
	
	private final float[] projectionMatrix = new float[16];
	private final float[] modelMatrix = new float[16];
	private final float[] viewMatrix = new float[16];
	private final float[] viewProjectionMatrix = new float[16];
	private final float[] modelViewProjectionMatrix = new float[16];
	
	private final float[] invertedViewProjectionMatrix = new float[16];
	
	private TextureShaderProgram textureProgram;
	private ColorShaderProgram colorProgram;
	
	private Table table;
	private Mallet mallet;
	private Puck puck;
	
	private int texture;
	
	private boolean malletPressed = false;
	private Geometry.Point blueMalletPosition;
	private Geometry.Point previousBlueMalletPosition;
	private Geometry.Point puckPosition;
	private Geometry.Vector puckVector;
	
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
		Matrix.invertM(invertedViewProjectionMatrix, 0, viewProjectionMatrix, 0);
		
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
		
		positionObjectInScene(blueMalletPosition.x, blueMalletPosition.y, blueMalletPosition.z);
		colorProgram.setUniforms(modelViewProjectionMatrix, 0f, 0f, 1f);
		mallet.draw();
		
		// puck이 받은 힘(puckVector) 만큼 이동하여 그린다.
		puckPosition = puckPosition.translate(puckVector);
		
		// boudary check - vector
		if (puckPosition.x < leftBound + puck.radius
				|| puckPosition.x > rightBound - puck.radius) {
			puckVector = new Geometry.Vector(-puckVector.x, puckVector.y, puckVector.z);
			puckVector = puckVector.scale(0.9f);
		}
		
		if (puckPosition.z < farBound + puck.radius
				|| puckPosition.z > nearBound - puck.radius) {
			puckVector = new Geometry.Vector(puckVector.x, puckVector.y, -puckVector.z);
			puckVector = puckVector.scale(0.9f);
		}
		
		// boundary check - position
		puckPosition = new Geometry.Point(
				clamp(puckPosition.x, leftBound + puck.radius, rightBound - puck.radius),
				puck.height / 2f,
				clamp(puckPosition.z, farBound + puck.radius, nearBound - puck.radius));
				
		positionObjectInScene(puckPosition.x, puckPosition.y, puckPosition.z);
		colorProgram.setUniforms(modelViewProjectionMatrix, 0.8f, 0.8f, 1f);
		puck.bindData(colorProgram);
		puck.draw();
		
		puckVector = puckVector.scale(0.99f);
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
		
		blueMalletPosition = new Geometry.Point(0f, mallet.height / 2f, 0.4f);
		
		puckPosition = new Geometry.Point(0f, puck.height / 2f, 0f);
		puckVector = new Geometry.Vector(0f, 0f, 0f);
		
		textureProgram = new TextureShaderProgram(context);
		colorProgram = new ColorShaderProgram(context);
		
		texture = TextureHelper.loadTexture(context, R.drawable.air_hockey_surface);
	}

	public void handleTouchPress(float normalizedX, float normalizedY) {
		
		Geometry.Ray ray = convertNormalized2DPointToRay(normalizedX, normalizedY);
		
		Geometry.Sphere malletBoundingSphere = new Geometry.Sphere(new Geometry.Point(
				blueMalletPosition.x,
				blueMalletPosition.y,
				blueMalletPosition.z),
				mallet.height / 2f);
		
		malletPressed = Geometry.intersects(malletBoundingSphere, ray);
	}

	public void handleTouchDrag(float normalizedX, float normalizedY) {
		
		if (!malletPressed) return;
		
		Geometry.Ray ray = convertNormalized2DPointToRay(normalizedX, normalizedY);
		
		Geometry.Plane plane = new Geometry.Plane(new Geometry.Point(0, 0, 0),
				new Geometry.Vector(0, 1, 0));
		
		Geometry.Point touchedPoint = Geometry.intersectionPoint(ray, plane);
		
		previousBlueMalletPosition = blueMalletPosition;
		blueMalletPosition = new Geometry.Point(
				clamp(touchedPoint.x, leftBound + mallet.radius, rightBound - mallet.radius),
				mallet.height / 2f,
				clamp(touchedPoint.z, 0f + mallet.radius, nearBound - mallet.radius));
		
		float distance = Geometry.vectorBetween(blueMalletPosition, puckPosition).length();
		
		// 충돌
		if (distance < mallet.radius + puck.radius) {
			puckVector = Geometry.vectorBetween(previousBlueMalletPosition, blueMalletPosition);
		}
		
	}
	
	private float clamp(float value, float min, float max) {
		return Math.min(max, Math.max(value, min));
	}

	private Geometry.Ray convertNormalized2DPointToRay(float normalizedX,
			float normalizedY) {
		
		// normalized device coordinate
		final float[] nearPointNDC = { normalizedX, normalizedY, -1f, 1f };
		final float[] farPointNDC = { normalizedX, normalizedY, 1f, 1f };
		
		float[] nearPointWorld = new float[4];
		float[] farPointWorld = new float[4];
		
		Matrix.multiplyMV(nearPointWorld, 0, invertedViewProjectionMatrix, 0, nearPointNDC, 0);
		Matrix.multiplyMV(farPointWorld, 0, invertedViewProjectionMatrix, 0, farPointNDC, 0);
		
		// 역행렬 계산 결과는 inverted w가 나오므로 돌려놓는다???
		divideByW(nearPointWorld);
		divideByW(farPointWorld);
		
		Geometry.Point nearRayPoint = new Geometry.Point(
				nearPointWorld[0], nearPointWorld[1], nearPointWorld[2]);
		Geometry.Point farRayPoint = new Geometry.Point(
				farPointWorld[0], farPointWorld[1], farPointWorld[2]);
		
		return new Geometry.Ray(nearRayPoint, Geometry.vectorBetween(nearRayPoint, farRayPoint));
	}
	
	private void divideByW(float[] vector) {
		vector[0] /= vector[3];
		vector[1] /= vector[3];
		vector[2] /= vector[3];
	}

}
